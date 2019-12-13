package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;


import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.db.SegmentsDAO;
import com.amazonaws.lambda.demo.http.DeleteVideoSegmentRequest;
import com.amazonaws.lambda.demo.http.DeleteVideoSegmentResponse;
import com.amazonaws.lambda.demo.model.Segment;
import com.amazonaws.services.lambda.runtime.Context;

public class DeleteSegmentHandlerTest {

	@Test
    public void deleteVideoSegment() {
		SegmentsDAO cd = new SegmentsDAO();
		try {
		Segment tyler = cd.getSegment("555");
        DeleteVideoSegmentRequest req = new DeleteVideoSegmentRequest(tyler.getID());
        DeleteVideoSegmentResponse res = new DeleteSegmentHandler().handleRequest(req, createContext("Delete"));
        // now delete
        Assert.assertEquals(200, res.statusCode);
		}catch (Exception e) {
			fail("didn't work:" + e.getMessage());
			

		
		}
    }
	
	Context createContext(String apiCall) {
        TestContext2 ctx = new TestContext2();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

}
