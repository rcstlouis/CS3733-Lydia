package com.amazonaws.lambda.demo.model;

public class PlaylistEntry {
	
	String segmentID;
	String playlistName;
	int entryNumber;

	public PlaylistEntry(String segmentID, int entryNumber, String playlistName) {
		this.segmentID = segmentID;
		this.playlistName = playlistName;
		this.entryNumber = entryNumber; 
	}
	
	public String getSegmentID() { return segmentID; }
	public String getPlaylistName() { return playlistName; }
	public int getEntryNumber() { return entryNumber; }
	
}
