UPDATE innodb.playlistEntries 
SET playlistEntryNumber = playlistEntryNumber - 1 
WHERE `playlistName` = 'spockFlirting' and playlistEntryNumber > 1
ORDER BY playlistEntryNumber ASC;
