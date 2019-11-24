package com.amazonaws.lambda.demo.db;

import java.sql.ResultSet;

public class RegisteredSitesDAO {
	java.sql.Connection conn;

    public RegisteredSitesDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    private RegisteredSite generateRegisteredSite(ResultSet resultSet) throws Exception {
		String url = resultSet.getString("url");
	    //Construct a playlist
	    return new RegisteredSite ();
	}
    
}
