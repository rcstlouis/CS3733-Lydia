package com.amazonaws.lambda.demo.db;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.model.PlaylistEntry;
import com.amazonaws.lambda.demo.model.Segment;

public class SegmentsDAOTest {

	   
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
				Assert.assertEquals(cowards.isRemotelyAvailable(), true);

				Assert.assertNotEquals(test, test2);
				Assert.assertEquals(11, cd.getAllLocalSegments().size());
				Assert.assertEquals(9, cd.getAllRemoteSegments().size());
				Assert.assertEquals(5, cd.exportAvailableSegments().size());
				cd.addSegment(test2);
				cd.addSegment(test2);
				cd.deleteSegment(test2);
			} catch (Exception e) {
				fail("didn't work:" + e.getMessage());
			}
		}
		
}
