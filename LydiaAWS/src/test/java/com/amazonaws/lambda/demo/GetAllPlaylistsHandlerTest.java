package com.amazonaws.lambda.demo;



import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.ListPlaylistsResponse;
import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class GetAllPlaylistsHandlerTest {


	Context createContext(String apiCall) {
        TestContext2 ctx = new TestContext2();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

    @Test
    public void testGetAllPlaylistsHandler() {
    	
        GetAllPlaylistsHandler handler = new GetAllPlaylistsHandler();
		ListPlaylistsResponse res = handler.handleRequest(null, createContext("GetAllPlaylists"));



      //  System.out.println(""+output);
       // System.out.println(""+CONTENT_TYPE);
        // TODO: validate output here if needed.
        Assert.assertEquals(200, res.statusCode);

    }
}
