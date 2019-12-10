package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;


import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.AddSegmentToPlaylistRequest;
import com.amazonaws.lambda.demo.http.AddSegmentToPlaylistResponse;
import com.amazonaws.services.lambda.runtime.Context;

public class AddSegmentToPlaylistHandlerTest {
	@Test
	public void testAddSegment() {
		AddSegmentToPlaylistRequest req = new AddSegmentToPlaylistRequest("7", "test");
		AddSegmentToPlaylistResponse res = new AddSegmentToPlaylistHandler().handleRequest(req, createContext("AddToPlaylist"));
		
		Assert.assertEquals(200, res.httpCode);
	}
	
	Context createContext(String apiCall) {
        TestContext2 ctx = new TestContext2();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

}
