package com.amazonaws.lambda.demo.http;

public class RegisterRemoteSiteRequest {
	String url;
	
	public String getUrl() {return url;}
	public void setUrl(String url) {this.url = url;}
	
	public RegisterRemoteSiteRequest(String url) {
		this.url = url;
	}
	
	public RegisterRemoteSiteRequest() {
		
	}
	
	public  String toString() {
		return "RegisterSite(" + url + ")";
	}

}
