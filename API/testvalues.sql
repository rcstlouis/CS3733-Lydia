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
insert into innodb.playlistEntries values ("4", "spockFlirting", "1");
insert into innodb.playlistEntries values ("5", "spockFlirting", "2");
select * from innodb.playlistEntries;

-- registeredSites
-- Schema: (url)
insert into innodb.registeredSites values ("https://www.wpi.edu/");
insert into innodb.registeredSites values ("https://web.cs.wpi.edu/~heineman/cs3733/");
select * from innodb.registeredSites;

-- Test Queries

-- Getting all the segments in a playlist
SELECT * FROM innodb.segments WHERE id in (
    SELECT segmentID FROM innodb.playlistEntries WHERE playlistName="spockFlirting"
);

DELETE FROM innodb.playlistEntries WHERE segmentID = (
    SELECT id FROM innodb.segements WHERE name = ? and originSite = ?
)

insert into innodb.segments values ("7", "ice-cream", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/ice-cream.ogg", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/", false, "Riley", "I would like double portions of ice cream for the entire crew.");
insert into innodb.segments values ("8", "wrigleys", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/wrigleys.ogg", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/", false,"Darnell", "I would swear you were someone I left behind on Wrigley's Pleasure Planet.");
insert into innodb.segments values ("9", "no-salt", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/no-salt.ogg", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/", false,"McCoy", "This man has no salt in his body at all.");
insert into innodb.segments values ("10", "saturn", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/saturn.ogg", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/", false,"Spock", "Saturn rigns around my head on a road that's Martian red.");
insert into innodb.segments values ("11", "woman", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/woman.ogg", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/", false,"Kirk", "Uh, well, there's no right way to hit a woman.");
insert into innodb.segments values ("12", "cowards", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/cowards.ogg", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/", false, "COWARDS!");
insert into innodb.segments values ("13", "bowling", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/bowling.ogg", "https://3733lydia.s3.us-east-2.amazonaws.com/segments/", false,"Riley", "There will be a formal dance in the bowling alley.");