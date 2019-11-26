package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;
import java.util.List;
import com.amazonaws.lambda.demo.model.Segment;

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
		this.list = new ArrayList<Segment>();
		this.statusCode = code;
		this.error = errMessage;
	}
	
	public String toString() {
		if(list == null) { return "NoSegments"; }
		return "AllSegments(" + this.list.size() + ")";
	}
	
	public int getStatusCode() {
		return this.statusCode;
	}

}
