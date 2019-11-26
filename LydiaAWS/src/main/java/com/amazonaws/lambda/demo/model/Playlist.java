package com.amazonaws.lambda.demo.model;

import java.util.ArrayList;

public class Playlist {
	
	String name;
	ArrayList<PlaylistEntry> playlistEntries; 
	
	public Playlist(String name) {
		this.name = name;
		playlistEntries = new ArrayList<PlaylistEntry>();
	}
	
	public String getName() { return name; }
	
	public ArrayList<PlaylistEntry> getPlaylistEntries() { return playlistEntries; }
	
	//returns true if segment appended
	public boolean appendSegmentToPlaylist(PlaylistEntry playlistEntry) {
		return playlistEntries.add(playlistEntry);
	}
	//same as above
	
//	public boolean appendSegmentToPlaylist(Segment segment) {
//		return playlistEntries.add(new PlaylistEntry(segment, this.playlistEntries.size()+1, name));
//	}
	
	//returns true if segment found and deleted segment from playlist
	public boolean deleteSegmentFromPlaylist(String segmentID) {
		boolean found = false;
		for(int i = 0; !found && i < playlistEntries.size(); i++) {
			if(playlistEntries.get(i).getSegmentID().equals(segmentID)) {
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
			if(playlistEntries.get(i).getSegmentID().equals(segment.getID())) {
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
			if(playlistEntries.get(i).getSegmentID().equals(playlistEntry.getSegmentID())) {
				found = true;
				playlistEntries.remove(i);
			}
		}
		return found;
	}


}
