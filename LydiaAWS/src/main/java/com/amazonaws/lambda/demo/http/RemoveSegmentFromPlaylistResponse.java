package com.amazonaws.lambda.demo.http;

public class RemoveSegmentFromPlaylistResponse {
	public final String segmentName;
	public final String playlistName;
	public final int playlistEntryNum;
	public final int statusCode;
	public final String error;
	
	public RemoveSegmentFromPlaylistResponse(String segName, String playName, int playNum, int code) {
		this.segmentName = segName;
		this.playlistName = playName;
		this.statusCode = code;
		this.playlistEntryNum = playNum;
		this.error = "";
	}
	
	public RemoveSegmentFromPlaylistResponse(String segName, String playName, int playNum, int code, String errMessage) {
		this.statusCode = code;
		this.error = errMessage;
		this.segmentName = segName;
		this.playlistName = playName;
		this.playlistEntryNum = playNum;

	}
	
	public String toString() {
		if (statusCode / 100 == 2) {
			return "RemoveSegment(" + segmentName + "from" + playlistName + ")";
		}
		else {
			return "Error(" + segmentName + "from" + playlistName + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}

}
