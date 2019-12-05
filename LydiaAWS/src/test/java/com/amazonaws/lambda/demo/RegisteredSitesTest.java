package com.amazonaws.lambda.demo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import com.amazonaws.lambda.demo.db.RegisteredSitesDAO;
import com.amazonaws.lambda.demo.model.RegisteredSite;


public class RegisteredSitesTest {

	@Test
	public void testGetAllRegisteredSites() {
    	RegisteredSitesDAO cd = new RegisteredSitesDAO();
		try {
			List<RegisteredSite> hold = cd.getAllRegisteredSites();
			List<RegisteredSite> list = new ArrayList<RegisteredSite>();
			ListIterator<RegisteredSite> holdIterator = hold.listIterator();
			RegisteredSite hello = new RegisteredSite("hello");
			RegisteredSite url = new RegisteredSite("url.com");
			RegisteredSite fraud = new RegisteredSite("fraudguarantee.com");
			RegisteredSite heineman = new RegisteredSite("https://web.cs.wpi.edu/~heineman/cs3733/");
			RegisteredSite wpi = new RegisteredSite("https://www.wpi.edu/");
			
			list.add(fraud);
			list.add(hello);
			list.add(heineman);
			list.add(wpi);
			list.add(url);
			ListIterator<RegisteredSite> listIterator = list.listIterator();

			assertTrue(hold.get(0).getURL().equals(list.get(0).getURL()));
			assertTrue(hold.get(1).getURL().equals(list.get(1).getURL()));
			assertTrue(hold.get(2).getURL().equals(list.get(2).getURL()));
			assertTrue(hold.get(3).getURL().equals(list.get(3).getURL()));
			assertTrue(hold.get(4).getURL().equals(list.get(4).getURL()));

			
				
			
		} catch (Exception e) {
			fail("didn't work:" + e.getMessage());
		}
	}

/*	
	@Test
	public void testAddRegisteredSites() {
    	RegisteredSitesDAO cd = new RegisteredSitesDAO();
		try {
			List<RegisteredSite> hold = cd.getAllRegisteredSites();
			List<RegisteredSite> list = new ArrayList<RegisteredSite>();
			ListIterator<RegisteredSite> holdIterator = hold.listIterator();
			RegisteredSite hello = new RegisteredSite("hello");
			RegisteredSite url = new RegisteredSite("url.com");
			RegisteredSite fraud = new RegisteredSite("fraudguarantee.com");
			RegisteredSite heineman = new RegisteredSite("https://web.cs.wpi.edu/~heineman/cs3733/");
			RegisteredSite wpi = new RegisteredSite("https://www.wpi.edu/");
			RegisteredSite test = new RegisteredSite("jonbrownlowsbowlcut.info");
			list.add(fraud);
			list.add(hello);
			list.add(heineman);
			list.add(wpi);
			list.add(url);
			list.add(test);
			
			cd.addRegisteredSite(test);
			cd.addRegisteredSite(fraud);
			ListIterator<RegisteredSite> listIterator = list.listIterator();

			for (int i = 0; i < hold.size(); i++) {
				assertTrue(hold.get(i).getURL().equals(list.get(i).getURL()));

			}
				
			
		} catch (Exception e) {
			fail("didn't work:" + e.getMessage());
		}
	}
	
	@Test
	public void testDeleteRegisteredSites() {
    	RegisteredSitesDAO cd = new RegisteredSitesDAO();
		try {
			List<RegisteredSite> hold = cd.getAllRegisteredSites();
			List<RegisteredSite> list = new ArrayList<RegisteredSite>();
			ListIterator<RegisteredSite> holdIterator = hold.listIterator();
			RegisteredSite hello = new RegisteredSite("hello");
			RegisteredSite url = new RegisteredSite("url.com");
			RegisteredSite fraud = new RegisteredSite("fraudguarantee.com");
			RegisteredSite heineman = new RegisteredSite("https://web.cs.wpi.edu/~heineman/cs3733/");
			RegisteredSite wpi = new RegisteredSite("https://www.wpi.edu/");
			RegisteredSite test = new RegisteredSite("jonbrownlowsbowlcut.info");
			list.add(fraud);
			list.add(hello);
			list.add(heineman);
			list.add(wpi);
			list.add(url);
			list.remove(test);
			
			cd.deleteRegisteredSite(test);
			
			ListIterator<RegisteredSite> listIterator = list.listIterator();

			for (int i = 0; i < hold.size(); i++) {
				assertTrue(hold.get(i).getURL().equals(list.get(i).getURL()));

			}
				
			
		} catch (Exception e) {
			fail("didn't work:" + e.getMessage());
		}
	}
	*/
	
}
