package com.amazonaws.lambda.demo.model;

import java.util.UUID;

public class Segment {

	String id;
	String character;
	String originFilePath;
	String originSite;
	
	public void setNewId() {
		this.id = UUID.randomUUID().toString();
	}
}
