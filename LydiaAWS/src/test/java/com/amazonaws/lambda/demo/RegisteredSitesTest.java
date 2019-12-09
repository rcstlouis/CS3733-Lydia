package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.db.RegisteredSitesDAO;
import com.amazonaws.lambda.demo.http.ListPlaylistsResponse;
import com.amazonaws.lambda.demo.http.ListRegisteredSitesResponse;
import com.amazonaws.lambda.demo.http.RegisterRemoteSiteRequest;
import com.amazonaws.lambda.demo.http.RegisterRemoteSiteResponse;
import com.amazonaws.lambda.demo.http.UnregisterRemoteSiteRequest;
import com.amazonaws.lambda.demo.http.UnregisterRemoteSiteResponse;
import com.amazonaws.lambda.demo.model.RegisteredSite;
import com.amazonaws.services.lambda.runtime.Context;


public class RegisteredSitesTest {
	Context createContext(String apiCall) {
		TestContext2 ctx = new TestContext2();
		ctx.setFunctionName(apiCall);
		return ctx;
	}

	@Test
	public void testGetAllRegisteredSites() {

		ListSitesHandler handler = new ListSitesHandler();
		ListRegisteredSitesResponse res = handler.handleRequest(null, createContext("GetAllRegisteredSites"));



		//  System.out.println(""+output);
		// System.out.println(""+CONTENT_TYPE);
		// TODO: validate output here if needed.
		Assert.assertEquals(200, res.statusCode);
	}
	@Test
	public void testAddRegisteredSites() {
		RegisterRemoteSiteRequest req = new RegisterRemoteSiteRequest("I want to fucking shoot myself");
		RegisterRemoteSiteResponse res = new RegisterSiteHandler().handleRequest(req, createContext("AddRegisteredSite"));

		Assert.assertEquals(200, res.httpCode);
	}
	
	@Test
	public void testDeleteRegisteredSites() {
		UnregisterRemoteSiteRequest req = new UnregisterRemoteSiteRequest("I want to fucking shoot myself");
		UnregisterRemoteSiteResponse res = new UnregisterSiteHandler().handleRequest(req, createContext("DeleteRegisteredSite"));

		Assert.assertEquals(200, res.httpCode);
	}
	
	@Test
	public void testGetSites() {

		ListSitesHandler handler = new ListSitesHandler();
		ListRegisteredSitesResponse res = handler.handleRequest(null, createContext("GetSites"));



		//  System.out.println(""+output);
		// System.out.println(""+CONTENT_TYPE);
		// TODO: validate output here if needed.
		Assert.assertEquals(200, res.statusCode);
	}
}
