package com.amazonaws.lambda.demo.http;

public class ReceiveRemoteSegmentsResponse {
	public final String response;
	public final int httpCode;
	
	public ReceiveRemoteSegmentsResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public ReceiveRemoteSegmentsResponse(String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response( " + response + ", " + httpCode + ")";
	}
	
	public int getCode() {
		return this.httpCode;
	}

}
