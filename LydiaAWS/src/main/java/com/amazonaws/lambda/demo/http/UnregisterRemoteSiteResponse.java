package com.amazonaws.lambda.demo.http;

public class UnregisterRemoteSiteResponse {
	public final String response;
	public final int httpCode;
	
	public UnregisterRemoteSiteResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public UnregisterRemoteSiteResponse(String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}

}
