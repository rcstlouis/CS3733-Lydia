package com.amazonaws.lambda.demo.db;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.model.RegisteredSite;

public class RegisteredSitesDAOTest {

	   @Test
	    public void testAddRegisteredSite() {
	    	RegisteredSitesDAO cd = new RegisteredSitesDAO();
	    	try {
				RegisteredSite fake = new RegisteredSite("fakestnews.com");
				Assert.assertEquals(true, cd.addRegisteredSite(fake));
				Assert.assertEquals(false, cd.addRegisteredSite(fake));
	    	} catch (Exception e) {
				fail("didn't work:" + e.getMessage());
			}
	    }
	   
	   @Test
	    public void testDeleteRegisteredSite() {
	    	RegisteredSitesDAO cd = new RegisteredSitesDAO();
	    	try {
				RegisteredSite fake = new RegisteredSite("fakestnews.com");
				Assert.assertEquals(true, cd.deleteRegisteredSite(fake));
				Assert.assertEquals(false, cd.deleteRegisteredSite(fake));
	    	} catch (Exception e) {
				fail("didn't work:" + e.getMessage());
			}
	    }
	   
	   @Test
	    public void testGetAllRegisteredSites() {
	    	RegisteredSitesDAO cd = new RegisteredSitesDAO();
	    	try {
				Assert.assertEquals(1, cd.getAllRegisteredSites().size());
	    	} catch (Exception e) {
				fail("didn't work:" + e.getMessage());
			}
	    }

}
