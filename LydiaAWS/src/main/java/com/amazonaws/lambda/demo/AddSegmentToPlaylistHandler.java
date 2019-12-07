package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.db.PlaylistEntriesDAO;
import com.amazonaws.lambda.demo.http.AddSegmentToPlaylistRequest;
import com.amazonaws.lambda.demo.http.AddSegmentToPlaylistResponse;
import com.amazonaws.lambda.demo.model.PlaylistEntry;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddSegmentToPlaylistHandler implements RequestHandler<AddSegmentToPlaylistRequest, AddSegmentToPlaylistResponse> {
	
	public LambdaLogger logger;
	
	
	
	@Override
	public AddSegmentToPlaylistResponse handleRequest(AddSegmentToPlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to add segment " + req.getSegmentID() + "to playlist" + req.getPlaylistName());
		
		AddSegmentToPlaylistResponse response;
		try {
			PlaylistEntriesDAO dao = new PlaylistEntriesDAO();
			logger.log("Adding segment [" + req.getSegmentID() + "] to playlist [" + req.getPlaylistName() + "]");
			if (dao.addPlaylistEntry(new PlaylistEntry(req.getSegmentID(), -1, req.getPlaylistName()))) { //-1 is a dummy value
				logger.log("Add Success!!");
				response = new AddSegmentToPlaylistResponse("Added segment: " + req.getSegmentID() + " to playlist: " + req.getPlaylistName(), 200);
			}
			else {
				logger.log("Add failed, no exception.");
				response = new AddSegmentToPlaylistResponse("Failed to add segment: " + req.getSegmentID() + " to playlist: " + req.getPlaylistName(), 409);
			}
		} catch (Exception e) {
			logger.log("Add failed with exception: " + e.getMessage() +"\nStack Trace:"+ e.getStackTrace());
			response = new AddSegmentToPlaylistResponse(e.getMessage(), 403);
		}
		
		return response;
	}

}
