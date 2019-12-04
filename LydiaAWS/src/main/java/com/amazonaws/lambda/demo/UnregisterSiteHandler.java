package com.amazonaws.lambda.demo;

import java.util.List;

import com.amazonaws.lambda.demo.db.RegisteredSitesDAO;
import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.RegisteredSite;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

public class UnregisterSiteHandler implements RequestHandler <UnregisterRemoteSiteRequest, UnregisterRemoteSiteResponse> {
	
	public LambdaLogger logger;
	
	List<RegisteredSite> getSites() throws Exception {
		logger.log("in getSites");
		RegisteredSitesDAO dao = new RegisteredSitesDAO();
		
		return dao.getAllRegisteredSites();
	}
	
	private AmazonS3 s3 = null;
	
	@Override
	public UnregisterRemoteSiteResponse handleRequest(UnregisterRemoteSiteRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to unregister site " + req.getUrl());
		
		UnregisterRemoteSiteResponse response;
		try {
			RegisteredSitesDAO dao = new RegisteredSitesDAO();
			if (dao.deleteRegisteredSite(new RegisteredSite(req.getUrl()))) {
				response = new UnregisterRemoteSiteResponse("Unregistered site: " + req.getUrl(), 200);
			}
			response = new UnregisterRemoteSiteResponse("Failed to Unregister site: " + req.getUrl(), 409);
		} catch (Exception e) {
			response = new UnregisterRemoteSiteResponse(e.getMessage(), 403);
		}
		
		return response;
	}

}