CREATE TABLE `innodb`.`playlists` (
  `name` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `idplaylists_UNIQUE` (`name` ASC));

CREATE TABLE `innodb`.`segments` (
  `id` VARCHAR(36) NOT NULL,
  `originFilePath` VARCHAR(300) NOT NULL,
  `originSite` VARCHAR(300) NULL,
  `remotelyAvailable` TINYINT NOT NULL,
  `character` VARCHAR(20) NULL,
  `sentence` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `originFilePath_UNIQUE` (`originFilePath` ASC));