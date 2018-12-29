UNLOCK TABLES;
DROP SCHEMA IF EXISTS `kustwacht`;

CREATE DATABASE  IF NOT EXISTS `kustwacht`;
USE `kustwacht`;


CREATE TABLE `type_actor` (
  `EnumID` int NOT NULL AUTO_INCREMENT,
  `Naam` char(40) NOT NULL,
  `IsSchip` int NULL DEFAULT '0' COMMENT 'Indien van categorie schepen is dit 1.',
  PRIMARY KEY (`EnumID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `status_vervoermiddel` (
  `StatusID` int NOT NULL AUTO_INCREMENT,
  `Situatie` char(30) NOT NULL,
  PRIMARY KEY (`StatusID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `actoren` (
  `ActorID` int NOT NULL AUTO_INCREMENT,
  `Naam` char(30) NOT NULL,
  `EnumID` int NOT NULL,
  PRIMARY KEY (`ActorID`),
  KEY `FK_TypeActor` (`EnumID`),
  CONSTRAINT `FK_TypeActorActoren` FOREIGN KEY (`EnumID`) REFERENCES `type_actor` (`EnumID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




CREATE TABLE `vervoermiddel` (
  `VoertuigID` int NOT NULL AUTO_INCREMENT,
  `ActorID` int NOT NULL,
  `Snelheid` double(3,0) NULL DEFAULT '0' COMMENT 'Snelheid wordt uitgedrukt in knopen (zeemijl/uur): 1,852 km/u',
  `Reactietijd` double(2,0) NULL DEFAULT '0' COMMENT 'tijd die nodig is om de actor zich te laten verplaatsen naar het schip in nood. Inclusief de wendbaarheid indien schip van koers moet veranderen.',
  `Wendbaarheid` decimal(5,2) NULL DEFAULT '0' COMMENT 'bocht uitgedrukt in decimale graden',
  `Grootte` double(3,0) NOT NULL COMMENT '1m2 moet er voorzien zijn per persoon',
  `Capaciteit` double(6,2) NULL DEFAULT '0' COMMENT 'aantal opvarenden in functie van de oppervlakte, 1m2 = 1 persoon',
  `Koers` smallint(3) NOT NULL COMMENT 'De koers van een vaartuig of luchtvaartuig is de hoek tussen een noordrichting en de koersrichting. De koers kan worden afgelezen van een kompas.',
  `Longitude` char(15) NULL DEFAULT '0',
  `Latitude` char(15) NULL DEFAULT '0',
  `StatusID` int NOT NULL,
   PRIMARY KEY (`VoertuigID`),
   KEY `FK_Actoren` (`ActorID`),
   CONSTRAINT `FK_ActorenVervoermiddel` FOREIGN KEY (`ActorID`) REFERENCES `Actoren` (`ActorID`) ON DELETE CASCADE,
   KEY `FK_Status` (`StatusID`),
   CONSTRAINT `FK_StatusVervoermiddel` FOREIGN KEY (`StatusID`) REFERENCES `status_vervoermiddel` (`StatusID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




CREATE TABLE `verkeerstorens` (
  `VerkeerstorenID` int NOT NULL AUTO_INCREMENT,
  `ActorID` int NOT NULL,
  `Longitude` char(15) NULL DEFAULT '0',
  `Latitude` char(15) NULL DEFAULT '0',
   PRIMARY KEY (`VerkeerstorenID`),
   KEY `FK_Actoren` (`ActorID`),
   CONSTRAINT `FK_ActorenVerkeerstoren` FOREIGN KEY (`ActorID`) REFERENCES `Actoren` (`ActorID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
