package com.amazonaws.lambda.demo.http;

public class AddSegmentToPlaylistRequest {
	String segmentID;
	String playlistName;
	
	public String getSegmentID() {return segmentID;}
	public void setSegmentID(String segName) {this.segmentID = segName;}
	
	public String getPlaylistName() {return playlistName;}
	public void setPlaylistName(String playName) {this.playlistName = playName;}
	
	public String toString() {
		return "AddSegment(" + segmentID + "to" + playlistName + ")";
	}
	
	public AddSegmentToPlaylistRequest(String segmentID, String playlistName) {
		this.segmentID = segmentID;
		this.playlistName = playlistName;
	}
	
	public AddSegmentToPlaylistRequest() {
		
	}

}
