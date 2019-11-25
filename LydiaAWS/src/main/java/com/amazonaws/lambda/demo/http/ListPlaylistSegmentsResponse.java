package com.amazonaws.lambda.demo.http;

public class ListPlaylistSegmentsResponse {
	public final List<Segment> list;
	public final int statusCode;
	public final String error;
	
	public ListPlaylistSegmentsResponse(List<Segment> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public ListPlaylistSegmentsResponse(int code, String errMessage) {
		this.list = new List<Segment>();
		this.statusCode = code;
		this.error = errMessage;
	}
	
	public String toString() {
		if(list == null) { return "NoSegments"; }
		return "AllSegments(" + list.size() + ")";
	}

}
