package com.amazonaws.lambda.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.amazonaws.lambda.demo.model.Constant;
import com.amazonaws.lambda.demo.model.RegisteredSite;

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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM registeredSites WHERE url = ?;");
            ps.setString(1, registeredSite.getURL());
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
            throw new Exception("Failed to register site: " + e.getMessage());
        }
    }
    
    public boolean deleteRegisteredSite(RegisteredSite rs) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM registeredSites WHERE url = ?;");
            ps.setString(1, rs.getURL());
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete registered site: " + e.getMessage());
        }
    }
    
    public List<RegisteredSite> getAllRegisteredSites() throws Exception {
        
        List<RegisteredSite> allRegisteredSites = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM registeredSites";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                RegisteredSite c = generateRegisteredSite(resultSet);
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
	    return new RegisteredSite (url);
	}
    
}
