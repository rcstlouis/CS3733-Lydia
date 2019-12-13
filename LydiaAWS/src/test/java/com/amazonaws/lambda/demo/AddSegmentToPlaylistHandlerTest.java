package com.amazonaws.lambda.demo;



import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.AddSegmentToPlaylistRequest;
import com.amazonaws.lambda.demo.http.AddSegmentToPlaylistResponse;
import com.amazonaws.services.lambda.runtime.Context;

public class AddSegmentToPlaylistHandlerTest {
	@Test
	public void testAddSegment() {
		AddSegmentToPlaylistRequest req = new AddSegmentToPlaylistRequest("7", "test2: Electric Testaloo");
		AddSegmentToPlaylistResponse res = new AddSegmentToPlaylistHandler().handleRequest(req, createContext("AddToPlaylist"));
		
		
		AddSegmentToPlaylistRequest req2 = new AddSegmentToPlaylistRequest();
		AddSegmentToPlaylistResponse res2 = new AddSegmentToPlaylistResponse("Success!");
		
		AddSegmentToPlaylistRequest req3 = new AddSegmentToPlaylistRequest("7", "test2: Electric Testaloo");
		AddSegmentToPlaylistResponse res3 = new AddSegmentToPlaylistHandler().handleRequest(req3, createContext("AddToPlaylistFail"));

		AddSegmentToPlaylistRequest req4 = new AddSegmentToPlaylistRequest();
		req4.setSegmentID("432");
		req4.setPlaylistName("This Playlist Doesn't Exist");
		AddSegmentToPlaylistResponse res4 = new AddSegmentToPlaylistHandler().handleRequest(req4, createContext("AddToPlaylistFailThrow"));
	
		
		Assert.assertEquals(200, res.httpCode);
		Assert.assertEquals(200, res2.httpCode);
		Assert.assertEquals(409, res3.httpCode);
		Assert.assertEquals(req4.toString(), "AddSegment(432toThis Playlist Doesn't Exist)");
		Assert.assertEquals(403, res4.httpCode);
		Assert.assertEquals(res4.toString(), "Response(Failed to add segment to playlist: Data truncation: Data too long for column 'playlistName' at row 1)");
	}
	
	Context createContext(String apiCall) {
        TestContext2 ctx = new TestContext2();
        ctx.setFunctionName(apiCall);
        return ctx;
    }
}
