package com.amazonaws.lambda.demo.model;

public class PlaylistEntry {
	
	Segment segment;
	String segmentID;
	String playlistName;
	int entryNumber;

	public PlaylistEntry(Segment segment, int entryNumber) {
		this.segment = segment;
		segmentID = this.segment.getID();
		this.entryNumber = entryNumber; 
	}
	
	public Segment getSegment() { return segment; }
	
	
}
