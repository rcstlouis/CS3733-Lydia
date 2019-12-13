package com.amazonaws.lambda.demo;

//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.IOException;
//import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

//import com.amazonaws.lambda.demo.db.PlaylistEntriesDAO;
import com.amazonaws.lambda.demo.db.SegmentsDAO;
import com.amazonaws.lambda.demo.http.ListPlaylistsResponse;
import com.amazonaws.lambda.demo.http.ListSegmentsResponse;
import com.amazonaws.lambda.demo.http.ReceiveRemoteSegmentsRequest;
import com.amazonaws.lambda.demo.http.ReceiveRemoteSegmentsResponse;
//import com.amazonaws.lambda.demo.model.PlaylistEntry;
import com.amazonaws.lambda.demo.model.Segment;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

public class GetRemoteSegmentsHandlerTest {

	Context createContext(String apiCall) {
		TestContext3 ctx = new TestContext3();
		ctx.setFunctionName(apiCall);
		return ctx;
	}

	@Test
	public void test() {

		GetRemoteSegmentsHandler handler = new GetRemoteSegmentsHandler();
		ReceiveRemoteSegmentsRequest req = new ReceiveRemoteSegmentsRequest(
				"https://b19dramaticexit.s3.us-east-2.amazonaws.com/Videos/2019.12.07.01.05.18.ogg",
				"TRELANE",
				"Remember, you must try not to let me find you too quickly.",
				"https://r07mqx6cr7.execute-api.us-east-2.amazonaws.com/RemoteSite/publicsegments?apikey=KMpeIqfW7v6xWf7IEoBVh862r2wm8kJ73EjSQVHQ"
		);
		ReceiveRemoteSegmentsResponse res = handler.handleRequest(req, createContext("GetAllPlaylists"));
		Assert.assertTrue(res.httpCode == 200);
	}
}