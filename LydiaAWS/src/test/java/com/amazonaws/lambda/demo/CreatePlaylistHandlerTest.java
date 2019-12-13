package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;


import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.CreatePlaylistRequest;
import com.amazonaws.lambda.demo.http.CreatePlaylistResponse;
import com.amazonaws.lambda.demo.http.DeletePlaylistRequest;
import com.amazonaws.lambda.demo.http.DeletePlaylistResponse;
import com.amazonaws.services.lambda.runtime.Context;


public class CreatePlaylistHandlerTest {
	@Test
	public void testCreatePlaylist() {
		CreatePlaylistRequest req = new CreatePlaylistRequest("test2: Electric Testaloo");
		CreatePlaylistResponse res = new CreatePlaylistHandler().handleRequest(req, createContext("CreatePlaylist"));
		
		CreatePlaylistResponse res2 = new CreatePlaylistHandler().handleRequest(req, createContext("CreatePlaylist"));

		CreatePlaylistRequest req3 = new CreatePlaylistRequest();
		req3.setName("(-)_(-)");
		CreatePlaylistResponse res3 = new CreatePlaylistResponse("What is that?");
		
		Assert.assertEquals(200, res.httpCode);
		Assert.assertEquals(409, res2.httpCode);
		Assert.assertEquals("Response(Unable to create playlist test2: Electric Testaloo)", res2.toString());
	}
	
	Context createContext(String apiCall) {
        TestContext2 ctx = new TestContext2();
        ctx.setFunctionName(apiCall);
        return ctx;
    }
}
