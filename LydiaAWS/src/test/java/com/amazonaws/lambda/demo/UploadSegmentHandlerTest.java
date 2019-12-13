package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.DeleteVideoSegmentRequest;
import com.amazonaws.lambda.demo.http.DeleteVideoSegmentResponse;
import com.amazonaws.lambda.demo.http.UploadVideoSegmentRequest;
import com.amazonaws.lambda.demo.http.UploadVideoSegmentResponse;
import com.amazonaws.services.lambda.runtime.Context;



public class UploadSegmentHandlerTest {
	
	@Test
    public void uploadVideoSegment() {
        UploadVideoSegmentRequest req = new UploadVideoSegmentRequest("555", "Tyler", "I want to ride my bicycle", "noideawhatgoeshere");
        UploadVideoSegmentResponse res = new UploadSegmentHandler().handleRequest(req, createContext("Upload"));
        // now delete
        Assert.assertEquals(200, res.httpCode);
    }
	
	Context createContext(String apiCall) {
        TestContext2 ctx = new TestContext2();
        ctx.setFunctionName(apiCall);
        return ctx;
    }
}
