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
import com.amazonaws.lambda.demo.http.*;


public class GetAllSegmentsHandler implements RequestHandler <Object, AllSegmentsResponse> {
	
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
	
//	/**
//	 * Retrieve all SYSTEM segments
//	 */
//	List<Segment> systemSegments() throws Exception {
//		logger.log("in systemSegments");
//		if (s3 == null) {
//			logger.log("attach to S3 request");
//			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
//			logger.log("attack to S3 succeed");
//		}
//		
//		ArrayList<Segment> sysSegments = new ArrayList<>();
//		
//		// retrieve listing of all objects in the designated bucket
//		ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request()
//				.withBucketName("3733lydia")
//				.withPrefix("segments");
//		
//		// request the s3 objects in the s3 bucket '3733lydia/segments'
//		logger.log("process request");
//		ListObjectsV2Result result = s3.listObjectsV2(listObjectsRequest);
//		logger.log("process request succeeded");
//		List<S3ObjectSummary> objects = result.getObjectSummaries();
//		
//		for (S3ObjectSummary os: objects) {
//			String name = os.getKey();
//			logger.log("S3 found:" + name);
//			
//			// If name ends with slash it is the 'segments/' bucket itself so you skip
//			if (name.endsWith("/")) {
//				continue;
//			}
//			
//			S3Object obj = s3.getObject("3733lydia", name);
//			
//			try (S3ObjectInputStream constantStream = obj.getObjectContent()){
//				Scanner sc = new Scanner(constantStream);
//				String val = sc.nextLine();
//				sc.close();
//				
//				// just grab name after the slash
//				int postSlash = name.indexOf("/");
//				//sysSegments.add(new Segment(name.substring(postSlash+1), Double.valueOf(val), true));
//				sysSegments.add(new)
//			} catch (Exception e) {
//				logger.log("Unable to parse contents of " + name);
//			}
//		}
//		
//		return sysSegments;
//	}
	
	@Override
	public AllSegmentsResponse handleRequest(Object input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all segments");
		
		AllSegmentsResponse response;
		try {
			// get all segments
			List<Segment> list = getSegments();
//			for (Segment s : systemSegments()) {
//				if (!list.contains(s)) {
//					list.add(s);
//				}
//			}
			response = new AllSegmentsResponse(list, 200);
		} catch (Exception e) {
			response = new AllSegmentsResponse(403, e.getMessage());
		}
		
		return response;
	}
	
}