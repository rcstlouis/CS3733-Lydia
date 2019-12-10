package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChangeMarkSegmentRequest {

	String segmentName;
	boolean isAvailable;
	
	public boolean getAvailable() {return isAvailable;}
	public void setAvailable(boolean avail) {this.isAvailable = avail;}
	
	public String getName() {return segmentName;}
	public void setName(String name) {this.segmentName = name;}
	
	public ChangeMarkSegmentRequest(String segmentName, boolean avail) {
		this.segmentName = segmentName;
	    this.isAvailable = avail;
		
	}
	
	public ChangeMarkSegmentRequest() {

	}
	
	public String toString() {
	return "Segment(" + segmentName + "is available:" + isAvailable + ")";
		
	}

}
