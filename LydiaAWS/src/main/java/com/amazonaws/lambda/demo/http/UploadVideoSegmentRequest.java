package com.amazonaws.lambda.demo.http;

public class UploadVideoSegmentRequest {
	String name;
	String character;
	String sentence;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getCharacter() { return character; }
	public void setCharacter(String character) {this.character = character; }
	
	public String getSentence() { return sentence; }
	public void setSentence(String sentence) { this.sentence = sentence; }
	
	public UploadVideoSegmentRequest(String name, String character, String sentence) {
		this.name = name;
		this.character = character;
		this.sentence = sentence;
	}
	
	public String toString() {
		return "UploadSegment(" + name + "," + character + "," + sentence + ")";
	}
	
	public UploadVideoSegmentRequest() {
		
	}

}
