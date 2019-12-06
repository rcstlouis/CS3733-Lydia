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
		logger.log("Loading Java Lambda handler to add segment " + req.getSegmentName() + "to playlist" + req.getPlaylistName());
		
		AddSegmentToPlaylistResponse response;
		try {
			PlaylistEntriesDAO dao = new PlaylistEntriesDAO();
			if (dao.addPlaylistEntry(new PlaylistEntry(req.getSegmentName(), -1, req.getPlaylistName()))) {
				response = new AddSegmentToPlaylistResponse("Added segment: " + req.getSegmentName() + "to playlist: " + req.getPlaylistName(), 200);
			}
			response = new AddSegmentToPlaylistResponse("Failed to add segment: " + req.getSegmentName() + "to playlist: " + req.getPlaylistName(), 409);
		} catch (Exception e) {
			response = new AddSegmentToPlaylistResponse(e.getMessage(), 403);
		}
		
		return response;
	}

}
