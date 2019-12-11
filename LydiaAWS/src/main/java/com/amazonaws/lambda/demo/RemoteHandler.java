package com.amazonaws.lambda.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import com.amazonaws.lambda.demo.db.SegmentsDAO;
import com.amazonaws.lambda.demo.model.Segment;
import com.amazonaws.lambda.demo.model.SegmentRemote;
import com.amazonaws.lambda.demo.http.*;


public class RemoteHandler implements RequestHandler <Object, RemoteResponse> {
	
	public LambdaLogger logger;
	
	/** Load from RDS, if it exists
	 * 
	 * @throws Exception
	 */
	List<Segment> getSegments() throws Exception {
		logger.log("in getSegments");
		SegmentsDAO dao = new SegmentsDAO();
		
		return dao.getAllSegments();
	}
	
	private AmazonS3 s3 = null;
	
	@Override
	public RemoteResponse handleRequest(Object input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all segments");
		
		RemoteResponse response;
		try {
			// get all remote segments
			
			SegmentsDAO dao = new SegmentsDAO();
			logger.log("Database connection established!");
			List<SegmentRemote> list= dao.exportAvailableSegments();
			if(list.size() > 0) {
				response = new RemoteResponse(list, 200);
			}
			else {
				logger.log("Zero remotely available segments found.");
				response = new RemoteResponse(409, "Database connection established, but no remotely available segments found.");
			}
			
		} catch (Exception e) {
			response = new RemoteResponse(403, e.getMessage());
		}
		
		return response;
	}
	
}