package com.amazonaws.lambda.demo.db;

public class SegmentsDAO {
	java.sql.Connection conn;

    public SegmentsDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
}
