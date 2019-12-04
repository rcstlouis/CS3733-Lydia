package com.amazonaws.lambda.demo;

import java.util.List;

import com.amazonaws.lambda.demo.db.RegisteredSitesDAO;
import com.amazonaws.lambda.demo.http.ListRegisteredSitesResponse;
import com.amazonaws.lambda.demo.model.RegisteredSite;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

public class ListSitesHandler implements RequestHandler <Object, ListRegisteredSitesResponse> {
	
	public LambdaLogger logger;
	
	List<RegisteredSite> getSites() throws Exception {
		logger.log("in getSites");
		RegisteredSitesDAO dao = new RegisteredSitesDAO();
		
		return dao.getAllRegisteredSites();
	}
	
	private AmazonS3 s3 = null;
	
	@Override
	public ListRegisteredSitesResponse handleRequest(Object input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all registered sites");
		
		ListRegisteredSitesResponse response;
		try {
			List<RegisteredSite> list = getSites();
			response = new ListRegisteredSitesResponse(list, 200);
		} catch (Exception e) {
			response = new ListRegisteredSitesResponse(403, e.getMessage());
		}
		
		return response;
	}

}
