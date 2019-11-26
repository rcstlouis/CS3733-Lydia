package com.amazonaws.lambda.demo.http;

public class AddSegmentToPlaylistResponse {
	public final String response;
	public final int httpCode;
	
	public AddSegmentToPlaylistResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public AddSegmentToPlaylistResponse(String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}

}
