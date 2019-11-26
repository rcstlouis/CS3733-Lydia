package com.amazonaws.lambda.demo.http;

public class RemoveSegmentFromPlaylistRequest {
	public String name;
	
	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	
	public RemoveSegmentFromPlaylistRequest(String n) {
		this.name = n;
	}
	
	public RemoveSegmentFromPlaylistRequest() {
		
	}
	
	public String toString() {
		return "RemoveSegment(" + name + ")";
	}

}
