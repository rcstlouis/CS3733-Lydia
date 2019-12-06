package com.amazonaws.lambda.demo;

import java.util.List;

import com.amazonaws.lambda.demo.db.RegisteredSitesDAO;
import com.amazonaws.lambda.demo.http.ListRegisteredSitesResponse;
import com.amazonaws.lambda.demo.http.RegisterRemoteSiteRequest;
import com.amazonaws.lambda.demo.http.RegisterRemoteSiteResponse;
import com.amazonaws.lambda.demo.model.RegisteredSite;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

public class RegisterSiteHandler implements RequestHandler <RegisterRemoteSiteRequest, RegisterRemoteSiteResponse> {
	
	public LambdaLogger logger;
	
	List<RegisteredSite> getSites() throws Exception {
		logger.log("in getSites");
		RegisteredSitesDAO dao = new RegisteredSitesDAO();
		
		return dao.getAllRegisteredSites();
	}
	
	private AmazonS3 s3 = null;
	
	@Override
	public RegisterRemoteSiteResponse handleRequest(RegisterRemoteSiteRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to register site " + req.getUrl());
		
		RegisterRemoteSiteResponse response;
		try {
			RegisteredSitesDAO dao = new RegisteredSitesDAO();
			if (dao.addRegisteredSite(new RegisteredSite(req.getUrl()))) {
				response = new RegisterRemoteSiteResponse("Registered site: " + req.getUrl(), 200);
			}
			else{
				response = new RegisterRemoteSiteResponse("Failed to Register site: " + req.getUrl(), 409);
			}
		} catch (Exception e) {
			response = new RegisterRemoteSiteResponse(e.getMessage(), 403);
		}
		
		return response;
	}

}