package com.amazonaws.lambda.demo.http;

public class RegisterRemoteSiteResponse {
	public final String response;
	public final int httpCode;
	
	public RegisterRemoteSiteResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public RegisterRemoteSiteResponse(String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}

}
