-- Schema: (name)
CREATE TABLE `playlists` (
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `idplaylists_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- Schema: (id, name, originFilePath, originSite, remotelyAvailable, character, sentence)
CREATE TABLE `segments` (
  `id` varchar(36) NOT NULL,
  `name` varchar(36) NOT NULL,
  `originFilePath` varchar(300) NOT NULL, 
  -- the exact file path for the particular segment
  `originSite` varchar(300) DEFAULT NULL, 
  -- the registered site
  `remotelyAvailable` tinyint(4) NOT NULL,
  -- tinyint is what it gave me for a boolean
  `character` varchar(20) DEFAULT NULL,
  `sentence` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `originFilePath_UNIQUE` (`originFilePath`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- Schema: (segmentID, playlistName, playlistEntryNumber)
CREATE TABLE `playlistEntries` (
  `segmentID` varchar(36) NOT NULL,
  `playlistName` varchar(25) NOT NULL,
  `playlistEntryNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`segmentID`,`playlistName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- Schema: (url)
CREATE TABLE `registeredSites` (
  `url` varchar(300) NOT NULL,
  PRIMARY KEY (`url`),
  UNIQUE KEY `idregisteredSites_UNIQUE` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- The id from the segments table will correspond to the ids in the playlistEntry table
-- This id will be generated using uuid
-- The playlistName from playlistEntries will correspond to the name in playlists
-- The url in registeredSites will correspond to the originSite in segments if the origin is remote