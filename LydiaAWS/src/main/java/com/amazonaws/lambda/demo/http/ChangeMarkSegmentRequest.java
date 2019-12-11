package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChangeMarkSegmentRequest {

	String segmentID;
	boolean isAvailable;
	
	public boolean getAvailable() {return isAvailable;}
	public void setAvailable(boolean avail) {this.isAvailable = avail;}
	
	public String getSegmentID() {return segmentID;}
	public void setSegmentID(String segmentID) {this.segmentID = segmentID;}
	
	public ChangeMarkSegmentRequest(String segmentID, boolean avail) {
		this.segmentID = segmentID;
	    this.isAvailable = avail;
		
	}
	
	public ChangeMarkSegmentRequest() {

	}
	
	public String toString() {
	return "Segment(" + segmentID + " is available:" + isAvailable + ")";
		
	}

}
