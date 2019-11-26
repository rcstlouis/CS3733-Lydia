package com.amazonaws.lambda.demo.http;

public class CreatePlaylistRequest {
	String name;
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String toString() {
		return "CreatePlaylist(" + name + ")";
	}
	
	public CreatePlaylistRequest(String name) {
		this.name = name;
	}
	
	public CreatePlaylistRequest() {
		
	}

}
