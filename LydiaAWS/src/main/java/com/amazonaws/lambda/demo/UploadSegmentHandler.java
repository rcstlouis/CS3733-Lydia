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

/**
 * Create a new constant and store in S3 bucket.

 * @author heineman
 */
public class UploadSegmentHandler implements RequestHandler<UploadVideoSegmentRequest,UploadVideoSegmentResponse> {

	LambdaLogger logger;
	
	// To access S3 storage
	private AmazonS3 s3 = null; 
	
	public static final String REAL_BUCKET = "segments/";
	public static final String TEST_BUCKET = "testsegments/";
		
	/** Store into RDS.
	 * 
	 * @throws Exception 
	 */
	boolean createSegmentEntry(UploadVideoSegmentRequest req) throws Exception {
		if (logger != null) { logger.log("in createConstant"); }
		SegmentsDAO dao = new SegmentsDAO();
		
		// check if present
		Segment exist = dao.getSegment(req.getName());
		Segment segment = new Segment (req.getName(), req.getCharacter(), req.getSentence());
		if (exist == null) {
			return dao.addSegment(segment);
		} else {
			return false;
		}
	}
	
	/** Create S3 bucket
	 * 
	 * @throws Exception 
	 */
	boolean createBucketSegment(String name, byte[] contents) throws Exception {
		if (logger != null) { logger.log("in createBucketSegment"); }
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeed");
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);
		
		PutObjectResult res = s3.putObject(new PutObjectRequest("3733lydia", "segments/" + name + ".ogg", bais, omd).withCannedAcl(CannedAccessControlList.PublicRead));
		
		// if we ever get here, then whole thing was stored
		return true;
	}
	
	@Override 
	public UploadVideoSegmentResponse handleRequest(UploadVideoSegmentRequest req, Context context)  {
		logger = context.getLogger();
		logger.log(req.toString());
		logger.log("Request file name: " + req.getName());
		logger.log("Request character name: " + req.getCharacter());
		logger.log("Request sentence: " + req.getSentence());
		logger.log("Request base64EncodedValue: " + req.getBase64EncodedValue());

		UploadVideoSegmentResponse response;
		try {
			byte[] encoded = java.util.Base64.getDecoder().decode(req.getBase64EncodedValue());
			if (createBucketSegment(req.getName(), encoded)) {
				response = new UploadVideoSegmentResponse(req.getName());
			} else {
				response = new UploadVideoSegmentResponse(req.getName(), 422);
			}
			logger.log("Segment has made it into the bucket.");
			if(response.getCode() == 200) {
				if (createSegmentEntry(req)) {
					response = new UploadVideoSegmentResponse(req.getName());
				} else {
					response = new UploadVideoSegmentResponse(req.getName(), 422);
				}
			}
		} catch (Exception e) {
			logger.log("Upload failed. Raised excpetion: " + e.getMessage());
			logger.log("Stack Trace: " + e.getMessage());
			response = new UploadVideoSegmentResponse("Unable to upload segment: " + req.getName() + "(" + e.getMessage() + ")" + " Stack Trace: " + e.getStackTrace(), 400);
		}

		return response;
	}
}
