package com.amazonaws.lambda.demo.model;

import java.util.UUID;

public class Segment {

	String id;
	String name;
	String character;
	String sentence;
	String originFilePath;
	String originSite;
	boolean remotelyAvailable;
	
	public Segment(String name, String character, String sentence, String originFilePath, String originSite, boolean remotelyAvailable) {
		this.setNewId();
		this.name = name;
		this.character = character;
		this.sentence = sentence;
		this.originFilePath = originFilePath;
		this.originSite = originSite;
		this.remotelyAvailable = remotelyAvailable;
	}
	
	public Segment(String id, String name, String character, String sentence, String originFilePath, String originSite, boolean remotelyAvailable) {
		this.id = id;
		this.name = name;
		this.character = character;
		this.sentence = sentence;
		this.originFilePath = originFilePath;
		this.originSite = originSite;
		this.remotelyAvailable = remotelyAvailable;
	}
	
	public String getID() { return id; }
	public String getName() { return name; }
	public String getCharacter() { return character; }
	public String getSentence() { return sentence; }
	public String getOriginFilePath() { return originFilePath;} 
	public String getOriginSite() { return originSite; }
	public boolean isRemotelyAvailable() { return remotelyAvailable; }
	
	public void setNewId() {
		this.id = UUID.randomUUID().toString();
	}
}
