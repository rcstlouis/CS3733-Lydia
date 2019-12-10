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
//import com.amazonaws.lambda.demo.model.PlaylistEntry;
import com.amazonaws.lambda.demo.model.Segment;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

public class GetAllSegmentsHandlerTest {
	
	Context createContext(String apiCall) {
        TestContext2 ctx = new TestContext2();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

    @Test
    public void testGetAllSegments() {
    	
        GetAllSegmentsHandler handler = new GetAllSegmentsHandler();
		ListSegmentsResponse res = handler.handleRequest(null, createContext("GetAllPlaylists"));



      //  System.out.println(""+output);
       // System.out.println(""+CONTENT_TYPE);
        // TODO: validate output here if needed.
        Assert.assertEquals(200, res.statusCode);
    }
    
    @Test
    public void testSegmentStuff() {
    	Segment test = new Segment("Test", "Tyler", "I am so tired right now");
    	Segment test2 = new Segment("Test", "Tyler", "I am so tired right now", "The crawlspace between FlUpper and FLower", "Wooly Pollytestic Institute", false);
    	Assert.assertNotEquals(test.getID(), test2.getID());
    	Assert.assertEquals(test2.getName(), test.getName());
    	Assert.assertEquals(test.getCharacter(), test2.getCharacter());
    	Assert.assertEquals(test.getSentence(), test2.getSentence());
    	Assert.assertEquals(test2.getOriginFilePath(), "The crawlspace between FlUpper and FLower");
    	Assert.assertEquals(test2.getOriginSite(), "Wooly Pollytestic Institute");
    	Assert.assertEquals(test2.isRemotelyAvailable(), false);
    	//Assert.assertEquals(test.getCharacter(), test2.getCharacter());

    	//Assert.assertEquals("Test", test.getID());
    	//Assert.assertEquals("Test", test.getID());
    }
}
