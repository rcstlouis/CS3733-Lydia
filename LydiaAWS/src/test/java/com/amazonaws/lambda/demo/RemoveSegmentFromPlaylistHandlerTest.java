package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.ListPlaylistSegmentsRequest;
import com.amazonaws.lambda.demo.http.ListPlaylistSegmentsResponse;
import com.amazonaws.lambda.demo.http.RemoveSegmentFromPlaylistRequest;
import com.amazonaws.lambda.demo.http.RemoveSegmentFromPlaylistResponse;
import com.amazonaws.services.lambda.runtime.Context;

public class RemoveSegmentFromPlaylistHandlerTest {

	Context createContext(String apiCall) {
		TestContext2 ctx = new TestContext2();
		ctx.setFunctionName(apiCall);
		return ctx;
	}
	
	@Test
	public void testRemoveSegmentFromPlaylist() {
		RemoveSegmentFromPlaylistHandler handler = new RemoveSegmentFromPlaylistHandler();
		RemoveSegmentFromPlaylistResponse res = handler.handleRequest(new RemoveSegmentFromPlaylistRequest("7", "test2: Electric Testaloo", 1), createContext("RemoveSegmentFromPlaylist"));
		
		RemoveSegmentFromPlaylistRequest req2 = new RemoveSegmentFromPlaylistRequest();
		RemoveSegmentFromPlaylistResponse res2 = new RemoveSegmentFromPlaylistHandler().handleRequest(req2, createContext("RemoveSegmentFromPlaylistFail"));
		
		RemoveSegmentFromPlaylistRequest req3 = new RemoveSegmentFromPlaylistRequest();
		req3.setSegmentName("5726");
		req3.setPlaylistName("For legal reasons, this playlist name is a joke");
		req3.setPlaylistEntryNum(123456);
		
		Assert.assertEquals(200,  res.statusCode);
		Assert.assertEquals(422, res2.statusCode);
		Assert.assertEquals("5726", req3.getSegmentName());
		Assert.assertEquals("For legal reasons, this playlist name is a joke", req3.getPlaylistName());
		Assert.assertEquals(123456, req3.getPlaylistEntryNum());
	}

}
