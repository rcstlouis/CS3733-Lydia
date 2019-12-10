package com.amazonaws.lambda.demo.http;

public class DeleteVideoSegmentRequest {
	public String segmentID;
	
	public void setName(String segmentID) {this.segmentID = segmentID;}
	public String getName() {return segmentID;}
	
	public DeleteVideoSegmentRequest(String n) {
		this.segmentID = n;
	}
	
	public DeleteVideoSegmentRequest() {
		
	}
	
	public String toString() {
		return "Delete(" + segmentID +")";
	}

}
