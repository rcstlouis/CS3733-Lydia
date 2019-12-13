package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.Segment;

public class ReceiveRemoteSegmentsResponse {
	public final String response;
	public final int httpCode;
	public final String segmentID;
	public final String name;
	public final String character;
	public final String sentence;
	public final String	originFilePath;
	public final String originSite;
	
	public ReceiveRemoteSegmentsResponse(String s, int code, Segment segment) {
		this.response = s;
		this.httpCode = code;
		this.segmentID = segment.getID();
		this.name = segment.getName();
		this.character = segment.getCharacter();
		this.sentence = segment.getSentence();
		this.originFilePath = segment.getOriginFilePath();
		this.originSite = segment.getOriginSite();
	}
	
	public ReceiveRemoteSegmentsResponse(String s, int code) {
		this.response = s;
		this.httpCode = code;
		this.segmentID = null;
		this.name = null;
		this.character = null;
		this.sentence = null;
		this.originFilePath = null;
		this.originSite = null;
	}
	
	public ReceiveRemoteSegmentsResponse(String s) {
		this.response = s;
		this.httpCode = 200;
		this.segmentID = null;
		this.name = null;
		this.character = null;
		this.sentence = null;
		this.originFilePath = null;
		this.originSite = null;
	}
	
	public String toString() {
		return "Response( " + response + ", " + httpCode + ")";
	}
	
	public int getCode() {
		return this.httpCode;
	}

}
