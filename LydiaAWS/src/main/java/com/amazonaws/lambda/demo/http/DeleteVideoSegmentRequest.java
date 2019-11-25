package com.amazonaws.lambda.demo.http;

public class DeleteVideoSegmentRequest {
	public String name;
	
	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	
	public DeleteVideoSegmentRequest(String n) {
		this.name = n;
	}
	
	public DeleteVideoSegmentRequest() {
		
	}
	
	public String toString() {
		return "Delete(" + name +")";
	}

}
