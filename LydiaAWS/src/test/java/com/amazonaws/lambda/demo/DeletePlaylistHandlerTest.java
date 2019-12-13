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

		DeletePlaylistResponse res2 = new DeletePlaylistHandler().handleRequest(req, createContext("DeletePlaylist"));

		DeletePlaylistRequest res3 = new DeletePlaylistRequest();
		res3.setName("(-)_(-)");
		Assert.assertEquals(200, res.statusCode);
		Assert.assertEquals("DeletePlaylist(test2: Electric Testaloo)", res.toString());
		Assert.assertEquals(422, res2.statusCode);
		Assert.assertEquals("Error(test2: Electric Testaloo, statusCode=422, err=Unable to delete playlist)", res2.toString());
	}
}
