UPDATE innodb.playlistEntries 
SET playlistEntryNumber = playlistEntryNumber - 1 
WHERE `playlistName` = 'spockFlirting' and playlistEntryNumber > 1
ORDER BY playlistEntryNumber ASC;


UPDATE innodb.segments
SET remotelyAvailable = NOT remotelyAvailable
WHERE originSite = 'https://3733lydia.s3.us-east-2.amazonaws.com/segments/' AND name = 'spockFlirting';

