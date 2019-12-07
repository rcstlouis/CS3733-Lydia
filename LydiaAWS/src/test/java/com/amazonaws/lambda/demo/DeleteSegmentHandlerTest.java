package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.DeleteVideoSegmentRequest;
import com.amazonaws.lambda.demo.http.DeleteVideoSegmentResponse;
import com.amazonaws.services.lambda.runtime.Context;

public class DeleteSegmentHandlerTest {

	@Test
    public void deleteVideoSegment() {
        DeleteVideoSegmentRequest req = new DeleteVideoSegmentRequest("6");
        DeleteVideoSegmentResponse res = new DeleteSegmentHandler().handleRequest(req, createContext("Delete"));
        // now delete
        Assert.assertEquals(422, res.statusCode);
    }
	
	Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

}
