package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.db.PlaylistsDAO;
import com.amazonaws.lambda.demo.http.DeletePlaylistRequest;
import com.amazonaws.lambda.demo.http.DeletePlaylistResponse;
import com.amazonaws.lambda.demo.model.Playlist;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeletePlaylistHandler implements RequestHandler<DeletePlaylistRequest, DeletePlaylistResponse> {
	
	public LambdaLogger logger = null;
	
	@Override
	public DeletePlaylistResponse handleRequest(DeletePlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete playlist");
		
		DeletePlaylistResponse response = null;
		logger.log(req.toString());
		
		PlaylistsDAO dao = new PlaylistsDAO();
		
		Playlist playlist = new Playlist(req.getName());
		try {
			if (dao.deletePlaylist(playlist)) {
				response = new DeletePlaylistResponse(playlist.getName(), 200);
			} else {
				response = new DeletePlaylistResponse(playlist.getName(), 422, "Unable to delete playlist");
			}
		} catch (Exception e) {
			response = new DeletePlaylistResponse(playlist.getName() , 403, "Unable to delete playlist: " + playlist.getName() + "(" + e.getMessage() + ")");
		}
		
		return response;
	}

}
