package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.db.PlaylistsDAO;
import com.amazonaws.lambda.demo.http.CreatePlaylistRequest;
import com.amazonaws.lambda.demo.http.CreatePlaylistResponse;
import com.amazonaws.lambda.demo.model.Playlist;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

public class CreatePlaylistHandler implements RequestHandler<CreatePlaylistRequest, CreatePlaylistResponse> {
	
	public LambdaLogger logger;
	
	private AmazonS3 s3 = null;
	
	boolean createPlaylist(CreatePlaylistRequest req) throws Exception {
		if (logger != null) {logger.log("in createPlaylist"); }
		PlaylistsDAO dao = new PlaylistsDAO();
		boolean exist = dao.playlistExists(req.getName());
		logger.log("Alaready exists: " + exist);
		Playlist playlist = new Playlist(req.getName());
		if(!exist) {
			return dao.addPlaylist(playlist);
		}
		logger.log("Not successfully added to the database.");
		return false;
	}
	
	@Override
	public CreatePlaylistResponse handleRequest(CreatePlaylistRequest req, Context context) {
		this.logger = context.getLogger();
		logger.log(req.toString());
		
		CreatePlaylistResponse response;
		try {
			if(req.getName() == null) {
				logger.log("New playlist name is Null");
			}
			else {
				logger.log("New playlist name is " + req.getName());
			}
			PlaylistsDAO dao = new PlaylistsDAO();
			if (createPlaylist(req)) {
				logger.log("Creted new playlist successfully!!");
				response = new CreatePlaylistResponse("Created playlist " + req.getName(), 200);
			}
			else {
				logger.log("Failed to create new playlist, but no exception was raiesd.");
				response = new CreatePlaylistResponse("Unable to create playlist " + req.getName(), 409);
			}
		} catch (Exception e) {
			response = new CreatePlaylistResponse(e.getMessage(), 403);
		}
		
		return response;
	}

}
