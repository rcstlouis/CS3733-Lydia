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
import com.amazonaws.lambda.demo.http.RemoteResponse;
//import com.amazonaws.lambda.demo.model.PlaylistEntry;
import com.amazonaws.lambda.demo.model.Segment;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

public class RemoteHandlerTest {

	Context createContext(String apiCall) {
		TestContext3 ctx = new TestContext3();
		ctx.setFunctionName(apiCall);
		return ctx;
	}

	@Test
	public void testGetAllSegments() {

		RemoteHandler handler = new RemoteHandler();
		RemoteResponse res = handler.handleRequest(null, createContext("GetAllPlaylists"));



		//  System.out.println(""+output);
		// System.out.println(""+CONTENT_TYPE);
		// TODO: validate output here if needed.
		Assert.assertEquals(200, res.statusCode);
	}

	@Test
	public void testSegmentStuff() {
		SegmentsDAO cd = new SegmentsDAO();
		try {
			Segment cowards = cd.getSegment("cowards");	
			Segment test = new Segment("Test", "Tyler", "I am so tired right now");
			Segment test2 = new Segment("Test", "Tyler", "I am so tired right now", "The crawlspace between FlUpper and FLower", "Wooly Pollytestic Institute", false);
			Assert.assertEquals(cowards.getCharacter(), "guy");
			Assert.assertEquals(cowards.getID(), "12");
			Assert.assertEquals(cowards.getName(), "cowards");
			Assert.assertEquals(cowards.getSentence(), "COWARDS!");
			Assert.assertEquals(cowards.getOriginFilePath(), "https://3733lydia.s3.us-east-2.amazonaws.com/segments/COWARDS.ogg");
			Assert.assertEquals(cowards.getOriginSite(), "https://3733lydia.s3.us-east-2.amazonaws.com/segments/");
			Assert.assertEquals(cowards.isRemotelyAvailable(), false);

			Assert.assertNotEquals(test, test2);
		} catch (Exception e) {
			fail("didn't work:" + e.getMessage());
		}
	}
}