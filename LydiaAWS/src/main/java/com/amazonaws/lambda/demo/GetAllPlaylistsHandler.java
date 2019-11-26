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

import com.amazonaws.lambda.demo.db.PlaylistsDAO;
import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Playlist;

/**
 * Eliminated need to work with JSON
 */
public class GetAllPlaylistsHandler implements RequestHandler<Object,ListPlaylistsResponse> {

	public LambdaLogger logger;

	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Playlist> getPlaylists() throws Exception {
		logger.log("in getPlaylists");
		PlaylistsDAO dao = new PlaylistsDAO();
		
		return dao.getAllPlaylists();
	}
	
	// I am leaving in this S3 code so it can be a LAST RESORT if the constant is not in the database
	private AmazonS3 s3 = null;
	
	/**
	 * Retrieve all SYSTEM constants. This code is surprisingly dangerous since there could
	 * be an incredible number of objects in the bucket. Ignoring this for now.
	 */
//	List<Plyalist> systemConstants() throws Exception {
//		logger.log("in systemConstants");
//		if (s3 == null) {
//			logger.log("attach to S3 request");
//			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
//			logger.log("attach to S3 succeed");
//		}
//		ArrayList<Playlist> sysConstants = new ArrayList<>();
//	    
//		// retrieve listing of all objects in the designated bucket
//		ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request()
//				  .withBucketName("3733lydia")    // top-level bucket
//				  .withPrefix("segments");       // sub-folder declarations here (i.e., a/b/c)
//												  
//		
//		// request the s3 objects in the s3 bucket 'cs3733wpi/constants' -- change based on your bucket name
//		logger.log("process request");
//		ListObjectsV2Result result = s3.listObjectsV2(listObjectsRequest);
//		logger.log("process request succeeded");
//		List<S3ObjectSummary> objects = result.getObjectSummaries();
//		
//		for (S3ObjectSummary os: objects) {
//	      String name = os.getKey();
//		  logger.log("S3 found:" + name);
//
//	      // If name ends with slash it is the 'constants/' bucket itself so you skip
//	      if (name.endsWith("/")) { continue; }
//			
//	      S3Object obj = s3.getObject("3733lydia", name);
//	    	
//	    	try (S3ObjectInputStream constantStream = obj.getObjectContent()) {
//				Scanner sc = new Scanner(constantStream);
//				String val = sc.nextLine();
//				sc.close();
//				
//				// just grab name *after* the slash. Note this is a SYSTEM constant
//				int postSlash = name.indexOf('/');
//				sysConstants.add(new Constant(name.substring(postSlash+1), Double.valueOf(val), true));
//			} catch (Exception e) {
//				logger.log("Unable to parse contents of " + name);
//			}
//	    }
//		
//		return sysConstants;
//	}
	
	@Override
	public ListPlaylistsResponse handleRequest(Object input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all constants");

		ListPlaylistsResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<Playlist> list = getPlaylists();
			//Implement this later to retrieve constants from the bucket
//			for (Playlist p : systemConstants()) {
//				if (!list.contains(p)) {
//					list.add(p);
//				}
//			}
			response = new ListPlaylistsResponse(list, 200);
		} catch (Exception e) {
			response = new ListPlaylistsResponse(403, e.getMessage());
		}
		
		return response;
	}
}
