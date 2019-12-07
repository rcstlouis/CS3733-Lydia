package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;


import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.UploadVideoSegmentRequest;
import com.amazonaws.lambda.demo.http.UploadVideoSegmentResponse;
import com.amazonaws.services.lambda.runtime.Context;



public class UploadSegmentHandlerTest {
	
	@Test
	public void testUploadSegment() {
		UploadVideoSegmentRequest req = new UploadVideoSegmentRequest("bowling.ogg", "Riley", "There will be a formal dance in the bowling alley", "Mi43MTgyODE4Mjg=");
		UploadVideoSegmentResponse res = new UploadSegmentHandler().handleRequest(req, createContext("Upload"));
		
		Assert.assertEquals(200, res.httpCode);
	}
	
	Context createContext(String apiCall) {
        TestContext2 ctx = new TestContext2();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

}
