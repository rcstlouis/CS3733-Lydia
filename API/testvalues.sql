-- Playlists
-- Schema: (name)
insert into innodb.playlists(name) values ("spockFlirting"), ("Kirk");
select * from innodb.playlists;

-- Segments
-- Schema: (id, name, originFilePath, originSite, remotelyAvailable, character, sentence)
delete from innodb.segments where remotelyAvailable = true;
delete from innodb.segments where remotelyAvailable = false;
insert into innodb.segments values ("3", "salt", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/salt.ogg", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/", true, "Crater", "It needs love as much as it needs salt.");
insert into innodb.segments values ("4", "emotion", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/emotion.ogg", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/", true, "Spock", "Ah, yes. One of your Earth emotions.");
insert into innodb.segments values ("5", "shower", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/shower.ogg", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/", false, "Tormolen", "One man was taking a shower fully clothed.");
select * from innodb.segments;

-- playlistEntries
-- Schema: (segmentID, playlistName, playlistEntryNumber)
insert into innodb.playlistEntries values ("3", "spockFlirting", "1");
select * from innodb.playlistEntries;

-- registeredSites
-- Schema: (url)
insert into innodb.registeredSites values ("https://www.wpi.edu");
select * from innodb.registeredSites;