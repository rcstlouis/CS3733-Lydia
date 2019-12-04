package com.amazonaws.lambda.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.model.Constant;
import com.amazonaws.lambda.demo.model.Playlist;
import com.amazonaws.lambda.demo.model.Segment;

public class PlaylistsDAO {
	java.sql.Connection conn;

	public PlaylistsDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
	
	public boolean addPlaylist(Playlist playlist) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlists WHERE id = ?;");
            ps.setString(1, playlist.getName());
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Playlist p = generatePlaylist(resultSet);
                resultSet.close();
                return false;
            }
            
            ps = conn.prepareStatement("INSERT INTO playlists values(?);");
            ps.setString(1,  playlist.getName());
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert playlist: " + e.getMessage());
        }
    }
	
	public boolean deletePlaylist(Playlist playlist) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM playlists WHERE name = ?;");
            ps.setString(1, playlist.getName());
            int numAffected = ps.executeUpdate();
            ps.close();
            
            PreparedStatement ps1 = conn.prepareStatement("DELETE FROM playlistsEntries WHERE playlistName = ?;");
            ps1.setString(1, playlist.getName());
            ps1.executeUpdate();
            ps1.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete playlist: " + e.getMessage());
        }
    }
	
	public List<Playlist> getAllPlaylists() throws Exception {
        
        List<Playlist> allPlaylists = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM playlists";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Playlist p = generatePlaylist(resultSet);
                allPlaylists.add(p);
            }
            resultSet.close();
            statement.close();
            return allPlaylists;

        } catch (Exception e) {
            throw new Exception("Failed in getting playlists: " + e.getMessage());
        }
    }
	
	private Playlist generatePlaylist(ResultSet resultSet) throws Exception {
		String name = resultSet.getString("name");
	    //Construct a playlist
	    return new Playlist (name);
	}
}
