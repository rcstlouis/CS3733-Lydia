package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChangeMarkSegmentRequest {
	List<String> segmentNames;
//	String segmentName;
	
//	public boolean getAvailable() {return isAvailable;}
//	public void setAvailable(boolean avail) {this.isAvailable = avail;}
	
//	public String getName() {return segmentName;}
//	public void setName(String name) {this.segmentName = name;}
	
//	public ChangeMarkSegmentRequest(String segmentName) {
//		this.segmentName = segmentName;
//		segmentNames = new LinkedList<String>();
//		
//	}
	
	public ChangeMarkSegmentRequest(String[] strings) {
		List<String> segmentNames = new ArrayList<String>();
		for(String s: strings) {
			segmentNames.add(s);
		}
		this.segmentNames = segmentNames;
//		this.isAvailable = avail;
	}
	
	public ChangeMarkSegmentRequest(List<String> segmentNames) {
		this.segmentNames = segmentNames;
	}
	
//	public ChangeMarkSegmentRequest(List<String> segmentNames, String segmentName) {
//		this.segmentNames = segmentNames;
//		this.segmentName = segmentName;
////		this.isAvailable = avail;
//	}
	
	public ChangeMarkSegmentRequest() {
		segmentNames = new LinkedList<String>();
	}
	
	public String toString() {
//		return "Segment(" + segmentName + "is available:" + isAvailable + ")";
		
		String returnString = "State of list: ";
		if(this.segmentNames.isEmpty()) {
			returnString += "Empty.";
		}
		else {
			returnString += this.segmentNames.toString() + " elements.";
		}
		return returnString;
	}
	
	public List<String> getNames(){
		return this.segmentNames;
	}

}
