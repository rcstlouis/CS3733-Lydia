package com.amazonaws.lambda.demo.http;

import java.util.List;

public class ChangeMarkSegmentRequest {
	List<String> segmentNames;
	String segmentName;
	
//	public boolean getAvailable() {return isAvailable;}
//	public void setAvailable(boolean avail) {this.isAvailable = avail;}
	
	public String getName() {return segmentName;}
	public void setName(String name) {this.segmentName = name;}
	
	public ChangeMarkSegmentRequest(List<String> segmentNames) {
		this.segmentNames = segmentNames;
//		this.isAvailable = avail;
	}
	
	public ChangeMarkSegmentRequest() {
		
	}
	
	public String toString() {
//		return "Segment(" + segmentName + "is available:" + isAvailable + ")";
		return "list()";
	}
	
	public List<String> getNames(){
		return this.segmentNames;
	}

}
