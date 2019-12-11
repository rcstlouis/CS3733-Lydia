package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.db.PlaylistEntriesDAO;
import com.amazonaws.lambda.demo.http.RemoveSegmentFromPlaylistRequest;
import com.amazonaws.lambda.demo.http.RemoveSegmentFromPlaylistResponse;
import com.amazonaws.lambda.demo.model.PlaylistEntry;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class RemoveSegmentFromPlaylistHandler implements RequestHandler<RemoveSegmentFromPlaylistRequest, RemoveSegmentFromPlaylistResponse> {
	
	public LambdaLogger logger = null;
	
	@Override
	public RemoveSegmentFromPlaylistResponse handleRequest(RemoveSegmentFromPlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Handler to " + req.toString());
		
		RemoveSegmentFromPlaylistResponse response = null;
		
		PlaylistEntriesDAO dao = new PlaylistEntriesDAO();
		
		PlaylistEntry playlistEntry = new PlaylistEntry(req.segmentName, -1, req.playlistName);
		try {
			if(dao.deletePlaylistEntry(playlistEntry)) {
				response = new RemoveSegmentFromPlaylistResponse(playlistEntry.getSegmentID(), playlistEntry.getPlaylistName(), 200);
			} else {
				response = new RemoveSegmentFromPlaylistResponse(playlistEntry.getSegmentID(), playlistEntry.getPlaylistName(), 422, "Unable to remove segment");
			}
		} catch (Exception e) {
			response = new RemoveSegmentFromPlaylistResponse(playlistEntry.getSegmentID(), playlistEntry.getPlaylistName(), 403, "Unable to remove segment: " + req.segmentName + "from playlist: " + req.playlistName);
		}
		
		return response;
	}

}
