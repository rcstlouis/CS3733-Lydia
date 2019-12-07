package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.http.UploadVideoSegmentRequest;
import com.amazonaws.lambda.demo.http.UploadVideoSegmentResponse;
import com.amazonaws.services.lambda.runtime.Context;



public class UploadSegmentHandlerTest {
	
	@Test
	public void testUploadSegment() {
		File file = new File("C:\\Users\\Maggie.Raque\\Downloads\\woman.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			String base64encoding = "";
			while(scan.hasNext()) {
				base64encoding = base64encoding + scan.next();
			}
			UploadVideoSegmentRequest req = new UploadVideoSegmentRequest("woman.ogg", "Kirk", "There's no right way to hit a woman", base64encoding);
			UploadVideoSegmentResponse res = new UploadSegmentHandler().handleRequest(req, createContext("Upload"));
			
			Assert.assertEquals(200, res.httpCode);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	Context createContext(String apiCall) {
        TestContext2 ctx = new TestContext2();
        ctx.setFunctionName(apiCall);
        return ctx;
    }

}
