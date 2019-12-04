package com.amazonaws.lambda.demo.http;

public class UploadVideoSegmentRequest {
	String name;
	String character;
	String sentence;
	String base64EncodedValue;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getCharacter() { return character; }
	public void setCharacter(String character) {this.character = character; }
	
	public String getSentence() { return sentence; }
	public void setSentence(String sentence) { this.sentence = sentence; }
	
	public String getBase64EncodedValue() { return base64EncodedValue; }
	public void setBase(String base64EncodedValue) { this.base64EncodedValue = base64EncodedValue; }
	
	public UploadVideoSegmentRequest(String name, String character, String sentence, String base64EncodedValue) {
		this.name = name;
		this.character = character;
		this.sentence = sentence;
		this.base64EncodedValue = base64EncodedValue;
	}
	
	public String toString() {
		return "UploadSegment(" + name + "," + character + "," + sentence + ")";
	}
	
	public UploadVideoSegmentRequest() {
		
	}

}
