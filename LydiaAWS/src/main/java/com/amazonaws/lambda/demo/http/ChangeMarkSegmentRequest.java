package com.amazonaws.lambda.demo.http;

public class ChangeMarkSegmentRequest {
	boolean isAvailable;
	String segmentName;
	
	public boolean getAvailable() {return isAvailable;}
	public void setAvailable(boolean avail) {this.isAvailable = avail;}
	
	public String getName() {return segmentName;}
	public void setName(String name) {this.segmentName = name;}
	
	public ChangeMarkSegmentRequest(String segName, boolean avail) {
		this.segmentName = segName;
		this.isAvailable = avail;
	}
	
	public ChangeMarkSegmentRequest() {
		
	}
	
	public String toString() {
		return "Segment(" + segmentName + "is available:" + isAvailable + ")";
	}

}
