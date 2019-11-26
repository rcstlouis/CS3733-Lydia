package com.amazonaws.lambda.demo.http;

public class AddSegmentToPlaylistRequest {
	String segmentName;
	String playlistName;
	
	public String getSegmentName() {return segmentName;}
	public void setSegmentName(String segName) {this.segmentName = segName;}
	
	public String getPlaylistName() {return playlistName;}
	public void setPlaylistName(String playName) {this.playlistName = playName;}
	
	public String toString() {
		return "AddSegment(" + segmentName + "to" + playlistName + ")";
	}
	
	public AddSegmentToPlaylistRequest(String segName, String playName) {
		this.segmentName = segName;
		this.playlistName = playName;
	}
	
	public AddSegmentToPlaylistRequest() {
		
	}

}
