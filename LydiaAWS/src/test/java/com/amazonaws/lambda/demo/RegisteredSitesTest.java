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

		ListRegisteredSitesResponse weGood = new ListRegisteredSitesResponse(200, "okeydokey");


		//  System.out.println(""+output);
		// System.out.println(""+CONTENT_TYPE);
		// TODO: validate output here if needed.
		Assert.assertEquals(200, res.statusCode);
	}
	@Test
	public void testAddRegisteredSites() {
		RegisterRemoteSiteRequest req = new RegisterRemoteSiteRequest("Let's not and say we did");
		RegisterRemoteSiteResponse res = new RegisterSiteHandler().handleRequest(req, createContext("AddRegisteredSite"));
		
		RegisterRemoteSiteRequest req2 = new RegisterRemoteSiteRequest();
		req2.setUrl("I prefer fruit teas to herbal ones");
		RegisterRemoteSiteResponse res2 = new RegisterSiteHandler().handleRequest(req2, createContext("AddRegisteredSite"));

		Assert.assertEquals(200, res.httpCode);
		Assert.assertEquals(409, res2.httpCode);
		Assert.assertEquals("RegisterSite(I prefer fruit teas to herbal ones)", req2.toString());


	}
	
	@Test
	public void testDeleteRegisteredSites() {
		UnregisterRemoteSiteRequest req = new UnregisterRemoteSiteRequest("Let's not and say we did");
		UnregisterRemoteSiteResponse res = new UnregisterSiteHandler().handleRequest(req, createContext("DeleteRegisteredSite"));
		
		UnregisterRemoteSiteRequest req2 = new UnregisterRemoteSiteRequest();
		req2.setUrl("I prefer fruit teas to herbal ones");
		Assert.assertEquals("RegisterSite(I prefer fruit teas to herbal ones)", req2.toString());
		UnregisterRemoteSiteResponse res2 = new UnregisterSiteHandler().handleRequest(req, createContext("DeleteRegisteredSite"));
		UnregisterRemoteSiteResponse res4 = new UnregisterRemoteSiteResponse("Everything okay?");

		RegisterRemoteSiteResponse res3 = new RegisterRemoteSiteResponse("We good");


		Assert.assertEquals(200, res.httpCode);
		Assert.assertEquals(409, res2.httpCode);
		Assert.assertEquals("Response(We good)", res3.toString());
		Assert.assertEquals("Response(Everything okay?)", res4.toString());
		//Assert.assertEquals("", re);

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
