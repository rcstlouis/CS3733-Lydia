package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.DeletePlaylistRequest;
import com.amazonaws.lambda.demo.http.DeletePlaylistResponse;
import com.amazonaws.services.lambda.runtime.Context;

public class DeletePlaylistHandlerTest {

	Context createContext(String apiCall) {
        TestContext2 ctx = new TestContext2();
        ctx.setFunctionName(apiCall);
        return ctx;
    }
	
	@Test
	public void testDeletePlaylist() {
		DeletePlaylistRequest req = new DeletePlaylistRequest("test2: Electric Testaloo");
		DeletePlaylistResponse res = new DeletePlaylistHandler().handleRequest(req, createContext("DeletePlaylist"));
		
		Assert.assertEquals(200, res.statusCode);
	}
}
