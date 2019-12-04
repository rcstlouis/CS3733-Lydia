package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.lambda.demo.model.RegisteredSite;

public class ListRegisteredSitesResponse {
	
	public final List<RegisteredSite> list;
	public final int statusCode;
	public final String error;
	
	public ListRegisteredSitesResponse(List<RegisteredSite> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public ListRegisteredSitesResponse(int code, String errMessage) {
		this.list = new ArrayList<RegisteredSite>();
		this.statusCode = code;
		this.error = errMessage;
	}
	
	public String toString() {
		if(list == null) {return "noRegisteredSites"; }
		return "AllRegisteredSites(" + list.size() + ")";
	}

}
