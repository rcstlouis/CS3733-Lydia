package com.amazonaws.lambda.demo;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.ListPlaylistSegmentsRequest;
import com.amazonaws.lambda.demo.http.ListPlaylistSegmentsResponse;
import com.amazonaws.lambda.demo.http.RemoveSegmentFromPlaylistRequest;
import com.amazonaws.lambda.demo.http.RemoveSegmentFromPlaylistResponse;
import com.amazonaws.services.lambda.runtime.Context;

public class ListPlaylistSegmentsHandlerTest {
	
	Context createContext(String apiCall) {
		TestContext2 ctx = new TestContext2();
		ctx.setFunctionName(apiCall);
		return ctx;
	}

	@Test
	public void testListPlaylistSegments() {

		ListPlaylistSegmentsHandler handler = new ListPlaylistSegmentsHandler();
		ListPlaylistSegmentsResponse res = handler.handleRequest(new ListPlaylistSegmentsRequest("spockFlirting"), createContext("GetAllPlaylistSegments"));



		//  System.out.println(""+output);
		// System.out.println(""+CONTENT_TYPE);
		// TODO: validate output here if needed.
		Assert.assertEquals(200, res.statusCode);

	}
}
