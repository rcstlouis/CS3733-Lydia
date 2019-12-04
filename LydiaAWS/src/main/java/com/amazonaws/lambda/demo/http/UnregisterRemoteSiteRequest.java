package com.amazonaws.lambda.demo.http;

public class UnregisterRemoteSiteRequest {
	String url;
	
	public String getUrl() {return url;}
	public void setUrl(String url) {this.url = url;}
	
	public UnregisterRemoteSiteRequest(String url) {
		this.url = url;
	}
	
	public UnregisterRemoteSiteRequest() {
		
	}
	
	public  String toString() {
		return "RegisterSite(" + url + ")";
	}

}
