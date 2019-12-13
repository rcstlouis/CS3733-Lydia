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
		String id = "8";
		ChangeMarkSegmentRequest req = new ChangeMarkSegmentRequest(id, false);
		MarkUnmarkLocalHandler handler = new MarkUnmarkLocalHandler();
		ChangeMarkSegmentResponse resp = handler.handleRequest(req, createContext("Mark/Unmark"));
		
		ChangeMarkSegmentRequest req2 = new ChangeMarkSegmentRequest();
		ChangeMarkSegmentResponse res2 = new MarkUnmarkLocalHandler().handleRequest(req2, createContext("Mark/Unmark"));
		ChangeMarkSegmentResponse res3 = new ChangeMarkSegmentResponse("Hi");
		req2.setAvailable(true);
		req2.setSegmentID("13");
		Assert.assertEquals(true, req2.getAvailable());
		Assert.assertEquals("Response(Unable to change availability. Database access failed.)", res2.toString());
		Assert.assertEquals("13", req2.getSegmentID());
		Assert.assertEquals(409, res2.httpCode);

		Assert.assertEquals(200, resp.getStatusCode());
		Assert.assertEquals(false, req.getAvailable());
		
		//Reset
		handler.handleRequest(req, createContext("Mark/Unmark"));
	}
	
	
	Context createContext(String apiCall) {
        TestContext2 ctx = new TestContext2();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

}
