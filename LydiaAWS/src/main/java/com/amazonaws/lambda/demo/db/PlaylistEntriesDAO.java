package com.amazonaws.lambda.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.model.Constant;

public class PlaylistEntriesDAO {

	java.sql.Connection conn;

    public PlaylistEntriesDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    public int getPlaylistLength(String playlistName) {
    	return getAllPlaylistEntries(playlistName).length;
    }
    
    public PlaylistEntry getPlaylistEntry(String playlistName, String segmentID) throws Exception {
        
        try {
            PlaylistEntry playlistEntry = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlistEntries WHERE segmentID=? AND playlistName=?;");
            ps.setString(1, segmentID);
            ps.setString(2, playlistName);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                playlistEntry = generatePlaylistEntry(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return playlistEntry;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting playlistEntry: " + e.getMessage());
        }
    }
    
    public List<PlaylistEntry> getAllPlaylistEntries(String playlistName) throws Exception {
        
        List<PlaylistEntry> allPlaylistEntries = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlistEntries WHERE playlistName=?");
            ps.setString(1,playlistName);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                PlaylistEntry pe = generatePlaylistEntry(resultSet);
                allPlaylistEntries.add(pe);
            }
            resultSet.close();
            return allPlaylistEntries;

        } catch (Exception e) {
            throw new Exception("Failed in getting playlist entries: " + e.getMessage());
        }
    }
    
    public boolean addPlaylistEntry(PlaylistEntry playlistEntry) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlistEntries WHERE segmentID = ? AND playlistName = ?;");
            ps.setString(1, playlistEntry.getSegmentID());
            ps.setString(2, playlistEntry.getPlaylistName());
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                PlaylistEntry pe = generatePlaylistEntry(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO playlistEntries values(?,?,?);");
            ps.setString(1,  playlistEntry.getSegmentID());
            ps.setString(2,  playlistEntry.getPlaylistName());
            ps.setInt(3, getPlaylistLength(playlistLength.getPlaylistName())+1); 
            //This last one may take some tweaking depending on how we do things
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }
    
    private PlaylistEntry generateConstant(ResultSet resultSet) throws Exception {
        String segmentID = resultSet.getString("segmentID");
        String playlistName = resultSet.getString("playlistName");
        int playlistEntryNumber = resultSet.getInt("playlistEntryNumber");
        return new PlaylistEntry
    }
}
