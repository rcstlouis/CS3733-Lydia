package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.ListRegisteredSitesRequest;
import com.amazonaws.lambda.demo.http.ListRegisteredSitesResponse;
import com.amazonaws.services.lambda.runtime.Context;

public class ListSitesHandlerTest {

	Context createContext(String apiCall) {
		TestContext2 ctx = new TestContext2();
		ctx.setFunctionName(apiCall);
		return ctx;
	}
	
	@Test
	public void testListSites() {
	
	ListRegisteredSitesRequest req = new ListRegisteredSitesRequest();
	ListRegisteredSitesResponse res = new ListSitesHandler().handleRequest(req, createContext("ListSites"));
	
	Assert.assertEquals(200, res.statusCode);
	Assert.assertEquals("List()", req.toString());
	Assert.assertEquals("AllRegisteredSites(2)", res.toString());

	}
}
