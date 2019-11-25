package com.amazonaws.lambda.demo.http;

public class DeleteVideoSegmentResponse {
	public final String name;
	public final int statusCode;
	public final String error;
	
	public DeleteVideoSegmentResponse(String name, int code) {
		this.name = name;
		this.statusCode = code;
		this.error = "";
	}
	
	public DeleteVideoSegmentResponse(String name, int code, String errMessage) {
		this.statusCode = code;
		this.error = errMessage;
		this.name = name;
	}
	
	public String toString() {
		if(statusCode / 100 == 2) {
			return "DeleteSegment(" + name + ")";
		}
		else {
			return "Error(" + name + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}

}
