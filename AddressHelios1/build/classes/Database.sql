DROP TABLE IF EXISTS `employees`.`address`;
CREATE TABLE  `employees`.`address` (
  `ADDRESS_ID` int(32) NOT NULL auto_increment,
  `ADDRESS_STREET` varchar(250) default NULL,
  `ADDRESS_CITY` varchar(50) default NULL,
  `ADDRESS_STATE` varchar(50) default NULL,
  `ADDRESS_ZIPCODE` varchar(10) default NULL,
  PRIMARY KEY  (`ADDRESS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

ALTER TABLE Address ADD ADDRESS_COUNTRY varchar(255) default NULL
ALTER TABLE STUDENT ADD STUDENT_TYPE varchar(100) default NULL
ALTER TABLE STUDENT DROP COLUMN STUDENT_INFO
ALTER TABLE STUDENT ADD STUDENT_INFO text default NULL

DROP TABLE IF EXISTS `employees`.`student`;
CREATE TABLE  `employees`.`student` (
  `STUDENT_ID` int(32) NOT NULL auto_increment,
  `STUDENT_NAME` varchar(100) default NULL,
  `STUDENT_ADDRESS` int(11) NOT NULL,
  `STUDENT_TYPE` varchar(100) default NULL,
  `STUDENT_INFO` text,
  PRIMARY KEY  (`STUDENT_ID`),
  KEY `fk_PerAddress` (`STUDENT_ADDRESS`),
  CONSTRAINT `fk_PerAddress` FOREIGN KEY (`STUDENT_ADDRESS`) REFERENCES `address` (`ADDRESS_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employees`.`student_phone`;
CREATE TABLE  `employees`.`student_phone` (
  `STUDENT_ID` int(32) NOT NULL,
  `PHONE_ID` int(32) NOT NULL,
  KEY `fk_PerStudentPhone` (`STUDENT_ID`),
  CONSTRAINT `fk_PerStudentPhone` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- This index was created as an attempt to create the foreign key from phone to student_phone
CREATE UNIQUE INDEX PHONE_ID_INDEX
ON STUDENT_PHONE (PHONE_ID);
	

DROP TABLE IF EXISTS `employees`.`phone`;
CREATE TABLE  `employees`.`phone` (
  `PHONE_ID` int(32) NOT NULL auto_increment,
  `PHONE_TYPE` varchar(10) default NULL,
  `PHONE_NUMBER` varchar(20) default NULL,
  PRIMARY KEY  (`PHONE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `employees`.`student_email`;
CREATE TABLE  `employees`.`student_email` (
  `STUDENT_ID` int(32) NOT NULL,
  `EMAIL_ID` int(32) NOT NULL,
 KEY `fk_PerStudentEmail` (`STUDENT_ID`),
 CONSTRAINT `fk_PerStudentEmail` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`) ON DELETE CASCADE  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employees`.`email`;
CREATE TABLE  `employees`.`email` (
  `EMAIL_ID` int(32) NOT NULL auto_increment,
  `EMAIL_TYPE` varchar(10) default NULL,
  `EMAIL_ADDR` varchar(100) default NULL,
  PRIMARY KEY  (`EMAIL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `employees`.`student_web`;
CREATE TABLE  `employees`.`student_web` (
  `STUDENT_ID` int(32) NOT NULL,
  `WEB_ID` int(32) NOT NULL,
 KEY `fk_PerStudentWeb` (`STUDENT_ID`),
 CONSTRAINT `fk_PerStudentWeb` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`) ON DELETE CASCADE  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employees`.`web`;
CREATE TABLE  `employees`.`web` (
  `WEB_ID` int(32) NOT NULL auto_increment,
  `WEB_URL` varchar(255) default NULL,
  PRIMARY KEY  (`WEB_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employees`.`state`;
CREATE TABLE  `employees`.`state` (
  `STATE_ID` int(32) NOT NULL auto_increment,
  `STATE_ABREV` varchar(5) default NULL,
  PRIMARY KEY  (`STATE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employees`.`country`;
CREATE TABLE  `employees`.`country` (
  `COUNTRY_ID` int(32) NOT NULL auto_increment,
  `COUNTRY_NAME` varchar(255) default NULL,
  PRIMARY KEY  (`COUNTRY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employees`.`student_type`;
CREATE TABLE  `employees`.`student_type` (
  `STUDENT_TYPE_ID` int(32) NOT NULL auto_increment,
  `STUDENT_TYPE` varchar(100) default NULL,
  PRIMARY KEY  (`STUDENT_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;



-- The primary key must be unsigned or you will get the err no 150

-- CREATE TABLE  `employees`.`radioshow` (
--  `SHOW_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
--  `TITLE` VARCHAR(255) NOT NULL,
--  PRIMARY KEY (`SHOW_ID`) USING BTREE
-- ) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

--CREATE TABLE  `employees`.`show_detail` (
 -- `SHOW_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
-- `DESCRIPTION` TEXT NOT NULL,
-- `ARCHIVE_LISTENS` int NOT NULL,
-- `LIVE_LISTENS` int NOT NULL,
-- `LISTED_DATE` DATE NOT NULL,
-- PRIMARY KEY (`SHOW_ID`) USING BTREE,
-- CONSTRAINT `FK_STOCK_ID` FOREIGN KEY (`SHOW_ID`) REFERENCES `radioshow` (`SHOW_ID`) ON DELETE CASCADE
--) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `employees`.`student_radioshow`;
CREATE TABLE  `employees`.`student_radioshow` (
  `STUDENT_ID` int(32) NOT NULL,
  `SHOW_ID` int(32) NOT NULL,
 KEY `fk_PerStudentEmail` (`STUDENT_ID`),
 CONSTRAINT `fk_PerStudentRadioShow` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`) ON DELETE CASCADE  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `employees`.`radioshow`;
CREATE TABLE  `employees`.`radioshow` (
  `SHOW_ID` int(32) NOT NULL auto_increment,
  `TITLE` varchar(255) default NULL,
  `DESCRIPTION` text default NULL,
  `ARCHIVE_LISTENS` int NOT NULL,
  `LIVE_LISTENS` int NOT NULL,
  `LISTED_DATE` DATE NOT NULL,
  PRIMARY KEY  (`SHOW_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

CREATE TABLE  `employees`.`keyinfo251` (
  `KEYINFO251_ID` int(32) NOT NULL auto_increment,
  `KEYNUMBER` int NOT NULL,
  `KEYWORD` varchar(128) default NULL,
  PRIMARY KEY  (`KEYINFO251_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

CREATE TABLE  `employees`.`contact251` (
  `CONTACT251_ID` int(32) NOT NULL auto_increment,
  `KEYNUMBER1` int default NULL,
  `KEYNUMBER2` int default NULL,
  `LINENUMBER` int NOT NULL,
  `INFO` text default NULL,
  PRIMARY KEY  (`CONTACT251_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

CREATE TABLE  `employees`.`pleiadean_mission` (
  `pleiadean_mission_id` int(32) NOT NULL auto_increment,
  `KEYNUMBER1` int default NULL,
  `KEYNUMBER2` int default NULL,
  `LINENUMBER` int NOT NULL,
  `INFO` text default NULL,
  PRIMARY KEY  (`pleiadean_mission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
