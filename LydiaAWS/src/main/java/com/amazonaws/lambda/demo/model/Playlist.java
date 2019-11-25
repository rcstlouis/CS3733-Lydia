package com.amazonaws.lambda.demo.model;

import java.util.ArrayList;
import java.util.UUID;

public class Playlist {
	
	String id;
	String name;
	ArrayList<PlaylistEntry> playlistEntries; 
	
	public Playlist(String n) {
		setNewId();
		name = n;
		playlistEntries = new ArrayList<PlaylistEntry>();
	}
	
	public String getID() { return id; }
	public String getName() { return name; }
	public String
	
	//returns true if segment appended
	public boolean appendSegmentToPlaylist(PlaylistEntry playlistEntry) {
		return playlistEntries.add(playlistEntry);
	}
	//same as above
	public boolean appendSegmentToPlaylist(Segment segment) {
		return playlistEntries.add(new PlaylistEntry(segment));
	}
	
	//returns true if segment found and deleted segment from playlist
	public boolean deleteSegmentFromPlaylist(String segmentID) {
		boolean found = false;
		for(int i = 0; !found && i < playlistEntries.size(); i++) {
			if(playlistEntries.get(i).getSegment().getID().equals(segmentID)) {
				found = true;
				playlistEntries.remove(i);
			}
		}
		return found;
	}
	//same as above
	public boolean deleteSegmentFromPlaylist(Segment segment) {
		boolean found = false;
		for(int i = 0; !found && i < playlistEntries.size(); i++) {
			if(playlistEntries.get(i).getSegment().getID().equals(segment.getID())) {
				found = true;
				playlistEntries.remove(i);
			}
		}
		return found;
	}
	//same as above
	public boolean deleteSegmentFromPlaylist(PlaylistEntry playlistEntry) {
		boolean found = false;
		for(int i = 0; !found && i < playlistEntries.size(); i++) {
			if(playlistEntries.get(i).getSegment().getID().equals(playlistEntry.getSegment().getID())) {
				found = true;
				playlistEntries.remove(i);
			}
		}
		return found;
	}
	
	public void setNewId() {
		this.id = UUID.randomUUID().toString();
	}

}
