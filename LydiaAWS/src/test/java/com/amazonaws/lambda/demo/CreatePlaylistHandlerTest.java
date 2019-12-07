package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;


import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.CreatePlaylistRequest;
import com.amazonaws.lambda.demo.http.CreatePlaylistResponse;
import com.amazonaws.services.lambda.runtime.Context;


public class CreatePlaylistHandlerTest {
	
	@Test
	public void testCreatePlaylist() {
		CreatePlaylistRequest req = new CreatePlaylistRequest("test");
		CreatePlaylistResponse res = new CreatePlaylistHandler().handleRequest(req, createContext("CreatePlaylist"));
		
		Assert.assertEquals(200, res.httpCode);
	}
	
	Context createContext(String apiCall) {
        TestContext2 ctx = new TestContext2();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

}
