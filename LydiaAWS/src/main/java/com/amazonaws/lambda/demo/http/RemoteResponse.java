package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.model.*;

public class RemoteResponse {
	public final List<SegmentRemote> segments;
	public final int statusCode;
	public final String error;
	
	public RemoteResponse (List<SegmentRemote> list, int code) {
		this.segments = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public RemoteResponse (int code, String errorMessage) {
		this.segments = new ArrayList<SegmentRemote>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (segments == null) { return "Empty Segment List"; }
		return "AllConstants(" + segments.size() + ")";
	}
	
	public int getStatusCode() {
		return this.statusCode;
	}
	
	public int getHTTPCode() {
		return this.statusCode;
	}
}
