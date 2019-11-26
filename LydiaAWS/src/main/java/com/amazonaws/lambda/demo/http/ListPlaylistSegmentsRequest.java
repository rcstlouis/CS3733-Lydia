package com.amazonaws.lambda.demo.http;

public class ListPlaylistSegmentsRequest {
	
	public String playlistName;
	
	public ListPlaylistSegmentsRequest() {
		
	}
	
	public ListPlaylistSegmentsRequest(String playlistName) {
		this.playlistName = playlistName;
	}
	
	public String getName() {
		return this.playlistName;
	}
	
	public String toString() {
		return "ListPlaylistSegments("+playlistName+")";
	}

}
