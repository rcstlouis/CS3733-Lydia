package com.amazonaws.lambda.demo.http;

public class ChangeMarkSegmentResponse {
	public final String response;
	public final int httpCode;
	
	public ChangeMarkSegmentResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public ChangeMarkSegmentResponse(String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}

	public int getStatusCode() {
		return this.httpCode;
	}

}
