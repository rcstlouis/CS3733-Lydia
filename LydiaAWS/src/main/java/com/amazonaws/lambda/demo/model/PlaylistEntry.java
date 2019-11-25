package com.amazonaws.lambda.demo.model;

public class PlaylistEntry {
	
	Segment segment;
	String segmentID;
	String playlistName;
	int entryNumber;

	public PlaylistEntry(Segment segment, int entryNumber, String playlistName) {
		this.segment = segment;
		segmentID = this.segment.getID();
		this.playlistName = playlistName;
		this.entryNumber = entryNumber; 
	}
	
	public Segment getSegment() { return segment; }
	public String getSegmentID() { return segmentID; }
	public String getPlaylistName() { return playlistName; }
	public int getEntryNumber() { return entryNumber; }
	
}
