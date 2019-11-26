package com.amazonaws.lambda.demo.http;

public class RemoveSegmentFromPlaylistRequest {
	public String segmentName;
	public String playlistName;
	
	public void setSegmentName(String name) {this.segmentName = name;}
	public String getSegmentName() {return segmentName;}
	
	public void setPlaylistName(String name) {this.playlistName = name;}
	public String getPlaylistName() {return playlistName;}
	
	public RemoveSegmentFromPlaylistRequest(String segName, String playName) {
		this.segmentName = segName;
		this.playlistName = playName;
	}
	
	public RemoveSegmentFromPlaylistRequest() {
		
	}
	
	public String toString() {
		return "RemoveSegment(" + segmentName + "from" + playlistName + ")";
	}

}
