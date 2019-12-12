package com.amazonaws.lambda.demo.http;

public class RemoveSegmentFromPlaylistRequest {
	public String segmentID;
	public String playlistName;
	public int playlistEntryNum;
	
	public void setSegmentName(String ID) {this.segmentID = ID;}
	public String getSegmentName() {return segmentID;}
	
	public void setPlaylistName(String name) {this.playlistName = name;}
	public String getPlaylistName() {return playlistName;}
	
	public void setPlaylistEntryNum(int num) {this.playlistEntryNum = num;}
	public int getPlaylistEntryNum() {return playlistEntryNum;}
	
	public RemoveSegmentFromPlaylistRequest(String segID, String playName, int entryNum) {
		this.segmentID = segID;
		this.playlistName = playName;
		this.playlistEntryNum = entryNum;
	}
	
	public RemoveSegmentFromPlaylistRequest() {
		
	}
	
	public String toString() {
		return "RemoveSegment(" + segmentID + "from" + playlistName + ")";
	}

}
