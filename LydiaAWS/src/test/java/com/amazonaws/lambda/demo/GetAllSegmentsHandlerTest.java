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
//import com.amazonaws.lambda.demo.model.PlaylistEntry;
import com.amazonaws.lambda.demo.model.Segment;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

@RunWith(MockitoJUnitRunner.class)
public class GetAllSegmentsHandlerTest {

	 private final String CONTENT_TYPE = "video/ogg";
	    private S3Event event;

	    @Mock
	    private AmazonS3 s3Client;
	    @Mock
	    private S3Object s3Object;

	    @Captor
	    private ArgumentCaptor<GetObjectRequest> getObjectRequest;

	    @Before
	    public void setUp() throws IOException {
	        event = TestUtils.parse("/s3-event.put.json", S3Event.class);

	        // TODO: customize your mock logic for s3 client
	        ObjectMetadata objectMetadata = new ObjectMetadata();
	        objectMetadata.setContentType(CONTENT_TYPE);
	        when(s3Object.getObjectMetadata()).thenReturn(objectMetadata);
	        when(s3Client.getObject(getObjectRequest.capture())).thenReturn(s3Object);
	    }

	    private Context createContext() {
	        TestContext ctx = new TestContext();

	        // TODO: customize your context here if needed.
	        ctx.setFunctionName("Your Function Name");

	        return ctx;
	    }

	    @Test
	    public void testGetAllSegmentsHandler() {
	        GetAllSegmentsHandler handler = new GetAllSegmentsHandler();
	        Context ctx = createContext();

	        String output = handler.handleRequest(event, ctx).toString();

	        System.out.println(""+output);
	        System.out.println(""+CONTENT_TYPE);
	        // TODO: validate output here if needed.
	        Assert.assertEquals(CONTENT_TYPE, output);
	        
	        
	    }
	    
	    @Test
	    public void testGetSegment() {
	    	SegmentsDAO cd = new SegmentsDAO();
			try {
				Segment s = cd.getSegment("salt");
				System.out.println(s.getID());
				assertTrue (s.getID().equals("3"));
				assertTrue(s.getName().equals("salt"));
				assertTrue(s.getCharacter().equals("Crater"));
				assertTrue(s.getSentence().equals("It needs love as much as it needs salt."));
				assertTrue(s.isRemotelyAvailable() == true);
			} catch (Exception e) {
				fail("didn't work:" + e.getMessage());
			}
	    }

}
