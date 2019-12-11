package com.amazonaws.lambda.demo.model;

public class SegmentRemote {
	String url;
	String character;
	String text;
	
	public SegmentRemote(String url, String character, String text){
		this.url = url;
		this.character = character;
		this.text = text;
	}
	
	public String getUrl(){
		return this.url;
	}
	public String getCharacter() {
		return this.character;
	}
	public String getText() {
		return this.text;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		return "URL: " + this.url + " CHARACTER: " + this.character + " TEXT: " + this.text;
	}

}
