package com.amazonaws.lambda.demo.http;

import java.util.List;

import com.amazonaws.lambda.demo.model.Playlist;

import java.util.ArrayList;

public class ListPlaylistsResponse {
	
	public final List<Playlist> list;
	public final int statusCode;
	public final String error;
	
	public ListPlaylistsResponse(List<Playlist> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public ListPlaylistsResponse(int code, String errMessage) {
		this.list = new ArrayList<Playlist>();
		this.statusCode = code;
		this.error = errMessage;
	}
	
	public String toString() {
		if(list == null) { return "NoPlaylists"; }
		return "AllPlaylists(" + list.size() + ")";
	}

}
