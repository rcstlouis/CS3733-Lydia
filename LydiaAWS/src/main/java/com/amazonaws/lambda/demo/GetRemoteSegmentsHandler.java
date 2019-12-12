package com.amazonaws.lambda.demo;

import java.io.ByteArrayInputStream;

import com.amazonaws.lambda.demo.http.UploadVideoSegmentRequest;
import com.amazonaws.lambda.demo.http.UploadVideoSegmentResponse;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import com.amazonaws.lambda.demo.db.SegmentsDAO;
import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Segment;
import com.amazonaws.lambda.demo.model.SegmentRemote;

/**
 * Create a new constant and store in S3 bucket.

 * @author heineman
 */
public class GetRemoteSegmentsHandler implements RequestHandler<GetRemoteSegmentsRequest,GetRemoteSegmentsResponse> {

	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null; 
	
	public static final String REAL_BUCKET = "segments/";
	public static final String TEST_BUCKET = "testsegments/";
		
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean createSegmentEntry(GetRemoteSegmentsRequest req) throws Exception {
		if (logger != null) { logger.log("in createConstant"); }
		SegmentsDAO dao = new SegmentsDAO();
		
		// check if present
//		Segment exist = dao.getSegment(req.getName());
		SegmentRemote segment = new SegmentRemote (req.getUrl(), req.getCharacter(), req.getText());
		if (/*exist == null*/ true) {
			return dao.addSegment(segment, req.getOriginSite());
		} else {
			return false;
		}
	}
	
	@Override 
	public GetRemoteSegmentsResponse handleRequest(GetRemoteSegmentsRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());
		logger.log("Request file path: " + req.getUrl());
		logger.log("Request character name: " + req.getCharacter());
		logger.log("Request sentence: " + req.getText());
		logger.log("Request originSite: " + req.getOriginSite());

		GetRemoteSegmentsResponse response;
		try {
			if (createSegmentEntry(req)) {
				response = new UploadVideoSegmentResponse(req.getName(), 200);
			} else {
				response = new UploadVideoSegmentResponse(req.getName(), 422);
			}
		} catch (Exception e) {
			logger.log("Upload failed. Raised excpetion: " + e.getMessage());
			logger.log("Stack Trace: " + e.getMessage());
			response = new UploadVideoSegmentResponse("Unable to upload segment: " + req.getName() + "(" + e.getMessage() + ")" + " Stack Trace: " + e.getStackTrace(), 400);
		}

		return response;
	}
}
