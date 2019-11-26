package com.amazonaws.lambda.demo;

import org.junit.Assert;
import static org.junit.Assert.*;

import org.junit.Test;

import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.*;
import com.amazonaws.services.lambda.runtime.Context;

public class ListPlaylistSegmentsHandlerTest {
	
	@Test
    public void testCreateSystemConstant() {
    	// create constant
        ListPlaylistSegmentsRequest lpsr = new ListPlaylistSegmentsRequest("spockFlirting");
        
        ListPlaylistSegmentsResponse resp = new ListPlaylistSegmentsHandler().handleRequest(lpsr, createContext("create"));
        Assert.assertEquals(200, resp.getStatusCode());
    }
	
	Context createContext(String apiCall) {
        TestContext ctx = new TestContext();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

}
