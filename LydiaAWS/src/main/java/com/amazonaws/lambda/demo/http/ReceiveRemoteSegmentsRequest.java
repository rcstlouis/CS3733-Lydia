package com.amazonaws.lambda.demo.http;

public class ReceiveRemoteSegmentsRequest {
	String originSite;
	String url;
	String text;
	String character;
	
	public String getUrl() {return url;}
	public void setUrl(String url) {this.url = url;}
	
	public String getOriginSite() {return originSite;}
	public void setOriginSite(String site) {this.originSite = site;}
	
	public String getText() {return text;}
	public void setText(String text) {this.text = text;}
	
	public String getCharacter() {return character;}
	public void setCharacter(String character) {this.character = character;}
	
	public ReceiveRemoteSegmentsRequest() {
		this.originSite = null;
		this.url = null;
		this.text = null;
		this.character = null;
	}
	
	public ReceiveRemoteSegmentsRequest(String url, String character, String text, String originSite) {
		this.url = url;
		this.character = character;
		this.text = text;
		this.originSite = originSite;
	}
	
	public String toString() {
		return "receive segment:" + url + "from site(" + originSite + ")" + "text: " + text + " character: " + character;
	}

}
