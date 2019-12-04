package com.amazonaws.lambda.demo.db;

import static org.junit.Assert.*;

import org.junit.Test;

import com.amazonaws.lambda.demo.model.Playlist;
import com.amazonaws.lambda.demo.model.Segment;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import java.util.List;

public class DBTest {
	
	@Test
	public void testCreate() {
	    PlaylistsDAO cd = new PlaylistsDAO();
	    try {
	    	// can add it
	    	List<Playlist> playlists = cd.getAllPlaylists();

	    	for(Playlist p: playlists) {
	    		System.out.println("Playlist found: " + p.getName());
	    	}
	    	
	    	// can delete it
	    	assertTrue (playlists.size() > 0);
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
}