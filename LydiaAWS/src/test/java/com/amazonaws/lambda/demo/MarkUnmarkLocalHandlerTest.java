package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class MarkUnmarkLocalHandlerTest {

	@Test
	public void test() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("shower");
		names.add("emotion");
		ChangeMarkSegmentRequest req = new ChangeMarkSegmentRequest(names);
		MarkUnmarkLocalHandler handler = new MarkUnmarkLocalHandler();
		ChangeMarkSegmentResponse resp = handler.handleRequest(req, createContext("Mark/Unmark"));
		Assert.assertEquals(200, resp.getStatusCode());
		//Reset
		handler.handleRequest(req, createContext("Mark/Unmark"));
	}
	
	@Test
	public void testFromJSON() {
		String testJSON = "{\"segmentNames\":[\"emotion\",\"shower\"]}";
		ChangeMarkSegmentRequest req = new Gson().fromJson(testJSON, ChangeMarkSegmentRequest.class);
		MarkUnmarkLocalHandler handler = new MarkUnmarkLocalHandler();
		ChangeMarkSegmentResponse resp = handler.handleRequest(req, createContext("Mark/Unmark"));
		Assert.assertEquals(200, resp.getStatusCode());
		//Reset
		handler.handleRequest(req, createContext("Mark/Unmark"));
	}
	
	Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

}
