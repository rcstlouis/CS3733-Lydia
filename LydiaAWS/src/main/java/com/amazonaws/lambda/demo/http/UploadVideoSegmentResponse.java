package com.amazonaws.lambda.demo.http;

public class UploadVideoSegmentResponse {
	public final String response;
	public final int httpCode;
	
	public UploadVideoSegmentResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public UploadVideoSegmentResponse(String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
	
	public int getCode() {
		return this.httpCode;
	}

}
