package com.amazonaws.lambda.demo;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.lambda.demo.http.ChangeMarkSegmentRequest;
import com.amazonaws.lambda.demo.http.ChangeMarkSegmentResponse;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.lambda.demo.db.RegisteredSitesDAO;
import com.amazonaws.lambda.demo.db.SegmentsDAO;
import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Segment;

/**
 * Create a new constant and store in S3 bucket.

 * @author heineman
 */
public class MarkUnmarkLocalHandler implements RequestHandler<ChangeMarkSegmentRequest,ChangeMarkSegmentResponse> {

	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null;
	
	@Override 
	public ChangeMarkSegmentResponse handleRequest(ChangeMarkSegmentRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());

		SegmentsDAO dao = new SegmentsDAO();
		ChangeMarkSegmentResponse response = null;
		try {
			if(dao.toggleRemotelyAvailable(req.getSegmentID())) {
				return new ChangeMarkSegmentResponse("Availability changed for segment: " + req.getSegmentID(), 200);
			} 
			else {
				return new ChangeMarkSegmentResponse("Unable to change availability. Database access failed.", 409);
			}
		} catch (Exception e) {
			response = new ChangeMarkSegmentResponse("Unable to change availability: " + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}
}