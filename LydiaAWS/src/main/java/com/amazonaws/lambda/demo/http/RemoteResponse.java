package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.model.*;

public class RemoteResponse {
	public final List<SegmentRemote> list;
	public final int statusCode;
	public final String error;
	
	public RemoteResponse (List<SegmentRemote> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public RemoteResponse (int code, String errorMessage) {
		this.list = new ArrayList<SegmentRemote>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyConstants"; }
		return "AllConstants(" + list.size() + ")";
	}
	
	public int getStatusCode() {
		return this.statusCode;
	}
	
	public int getHTTPCode() {
		return this.statusCode;
	}
}
