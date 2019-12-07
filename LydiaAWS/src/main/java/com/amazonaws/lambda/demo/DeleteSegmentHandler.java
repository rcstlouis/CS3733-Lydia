package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.lambda.demo.db.SegmentsDAO;
import com.amazonaws.lambda.demo.http.*;
import com.amazonaws.lambda.demo.model.Segment;

/**
 * No more JSON parsing
 */
public class DeleteSegmentHandler implements RequestHandler<DeleteVideoSegmentRequest,DeleteVideoSegmentResponse> {

	public LambdaLogger logger = null;

	@Override
	public DeleteVideoSegmentResponse handleRequest(DeleteVideoSegmentRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete");

		DeleteVideoSegmentResponse response = null;
		logger.log(req.toString());

		SegmentsDAO dao = new SegmentsDAO();

		// See how awkward it is to call delete with an object, when you only
		// have one part of its information?
		try {
			if (dao.deleteSegment(req.getName())) {
				response = new DeleteVideoSegmentResponse(req.name, 200);
			} else {
				response = new DeleteVideoSegmentResponse(req.name, 422, "Unable to delete segment.");
			}
		} catch (Exception e) {
			response = new DeleteVideoSegmentResponse(req.name, 403, "Unable to delete segment: " + req.name + "(" + e.getMessage() + ")");
		}

		return response;
	}
}
