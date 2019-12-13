package com.amazonaws.lambda.demo.db;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.lambda.demo.model.Playlist;

public class PlaylistsDAOTest {

	   @Test
	    public void testPlaylistExists() {
	    	PlaylistsDAO cd = new PlaylistsDAO();
	    	try {
				Assert.assertEquals(true, cd.playlistExists("spockFlirting"));
				Assert.assertEquals(false, cd.playlistExists("softEng"));
			} catch (Exception e) {
				fail("didn't work:" + e.getMessage());
			}
	    }
	   
	   @Test
	   public void testPlaylistAddAndDelete() {
		   PlaylistsDAO cd = new PlaylistsDAO();
		   try {
			   Playlist softEng = new Playlist("softEng");
			   Playlist spock = new Playlist("spockFlirting");
			   Assert.assertEquals("softEng", softEng.getName());
			   cd.addPlaylist(softEng);
			   cd.addPlaylist(spock);
			   Assert.assertEquals(true, cd.playlistExists("softEng"));
			   cd.deletePlaylist(softEng);
			   Assert.assertEquals(false, cd.playlistExists("softEng"));
		   } catch (Exception e) {
			   fail("didn't work:" + e.getMessage());
		   }
	   }
}
