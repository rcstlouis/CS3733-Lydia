-- Playlists
-- Schema: (name)
insert into innodb.playlists(name) values ("spockFlirting"), ("Kirk");
select * from innodb.playlists;

-- Segments
-- Schema: (id, name, originFilePath, originSite, remotelyAvailable, character, sentence)
insert into innodb.segments values ("3", "salt", "salt.ogg", "oursiteurl", true, "McCoy", "This needs love as much as it needs salt.");
select * from innodb.segments;

-- playlistEntries
-- Schema: (segmentID, playlistName, playlistEntryNumber)
insert into innodb.playlistEntries values ("3", "spockFlirting", "1");
select * from innodb.playlistEntries;

-- registeredSites
-- Schema: (url)
insert into innodb.registeredSites values ("https://www.wpi.edu");
select * from innodb.registeredSites;