package com.amazonaws.lambda.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.model.Constant;

public class RegisteredSitesDAO {
	java.sql.Connection conn;

    public RegisteredSitesDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    public boolean addRegisteredSite(RegisteredSite registeredSite) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM registeredSites WHERE name = ?;");
            ps.setString(1, registeredSite.getUrl());
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                RegisteredSite r = generateRegisteredSite(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO registeredSites values(?);");
            ps.setString(1,  registeredSite.getURL());
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }
    
    public List<RegisteredSite> getAllRegisteredSites() throws Exception {
        
        List<RegisteredSite> allRegisteredSites = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM registeredSites";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Constant c = generateRegisteredSite(resultSet);
                allRegisteredSites.add(c);
            }
            resultSet.close();
            statement.close();
            return allRegisteredSites;

        } catch (Exception e) {
            throw new Exception("Failed in getting books: " + e.getMessage());
        }
    }
    
    private RegisteredSite generateRegisteredSite(ResultSet resultSet) throws Exception {
		String url = resultSet.getString("url");
	    //Construct a playlist
	    return new RegisteredSite ();
	}
    
}