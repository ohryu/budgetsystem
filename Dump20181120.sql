-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: budgetmanagement
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BBDETAIL`
--

DROP TABLE IF EXISTS `BBDETAIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BBDETAIL` (
  `BD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ALLOCATION_TIME` int(11) DEFAULT NULL,
  `AMOUNT` bigint(20) DEFAULT NULL,
  `EXPENSE` bigint(20) DEFAULT NULL,
  `NEW` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `START_TIME` date DEFAULT NULL,
  `BG_ID` int(11) DEFAULT NULL,
  `BL_ID` int(11) DEFAULT NULL,
  `BUDGET_ID` int(11) DEFAULT NULL,
  `DEPT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`BD_ID`),
  KEY `FKiqqekoeppt81sevytlaejam4b` (`BG_ID`),
  KEY `FKktj3m33uspacgp7vnytqve867` (`BL_ID`),
  KEY `FKai5aavbp96jaf5subcpvjqde3` (`BUDGET_ID`),
  KEY `FKpijv0xkwt5c0mmi8m8jpqrquh` (`DEPT_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BBDETAIL`
--

LOCK TABLES `BBDETAIL` WRITE;
/*!40000 ALTER TABLE `BBDETAIL` DISABLE KEYS */;
/*!40000 ALTER TABLE `BBDETAIL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BBG`
--

DROP TABLE IF EXISTS `BBG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BBG` (
  `BG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BG_CODE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BG_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WB_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`BG_ID`),
  KEY `FKfby15tgfyvl9g9bad5395s35o` (`WB_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BBG`
--

LOCK TABLES `BBG` WRITE;
/*!40000 ALTER TABLE `BBG` DISABLE KEYS */;
INSERT INTO `BBG` VALUES (13,'01','Project based: IT security - phase 2',7),(14,'02','Microsoft Licensing 2017',7),(15,'07','Enhance Skype for Business',7),(16,'08','Tape backup',7),(17,'09','Device for Backup',7),(18,'10','Myanmar Office Infrastructure',7),(19,'11','Tool for process',7),(20,'12','Firewall Sophos ',7),(21,'13','Renovation Data Center',7),(22,'14','Workplace devices',7),(23,'15','Upgrade IP PBX',7),(24,'16','Upgrade CPU/RAM (reserve)',7),(25,'17','HBA/Fiber Card',7),(26,'05','Meeting online system - Webex License',8),(27,'07','Bank Safebox Renting Fee',8),(28,'10','Data Center Rent at Binh Duong Viettel IDC',8),(29,'11','Firewall HCM Astaro SG230',8),(30,'12','Mandrill for marketing email',8),(31,'13','License antivirus software',8),(32,'14','Microsoft Azure Fee',8),(33,'15','GoDaddy Wildcard SSL',8),(34,'16','Vulnerability scanner',8),(35,'17','Penetration Test',8),(36,'18','LBS for HN Office',8),(37,'19','Technology Research and POC',8),(38,'20','Maintenance Fee',8),(39,'21','Domain/license Renewal',8),(40,'22','BCP Testing',8),(42,'01','New Corporate Website (outsource)',9),(43,'02','New VNHRA Website (outsource)',9),(44,'03','Mobility Solutions of POS (in-house + outsource)',9),(45,'04','Legal Advisor Chatbot (outsource)',9),(46,'05','POS Mass Recruitment Website',9),(47,'06','POS Flexi Report',9),(48,'07','Odoo (ERP Platform)',9),(49,'08','System integration (outsource)',9),(50,'09','Elearning Flatform',9),(51,'10','Single Pane of Glass ',9),(52,'11','Mobile App of Consultant ',9),(53,'12','Jobs/candidates Crawler ',9),(54,'01','Talentnet Unified Platform',10),(55,'02','Data Warehouse',10),(56,'03','Knowledge Management (outsource)',10),(57,'04','Marketing Digital Channels',10),(58,'05','Digitization - Mobile App',10),(59,'06','Digitization - Ecosystem',10),(60,'07','Network Management ',10),(61,'01','POS- Other IT Investments',11),(62,'02','FINANCE Other IT Investment',11),(63,'01','BU Events - ESS',15),(64,'02','BU Events - POS',15),(65,'03','BU Events - HCS',15),(66,'01','Brand refreshment',17),(67,'02','New Arrivals',17),(68,'03','VNHRA',17),(69,'04','Competitor Research ',17),(70,'01','Điện - HCM Office',20),(71,'02','Điện - Hanoi Office',20),(72,'01','Thuê văn phòng HCM Office',28),(73,'02','Thuê văn phòng Hanoi Office',28),(74,'03','Thuê văn phòng Danang + Can Tho (POS)',28),(75,'01','Employees attend events',45),(76,'02','Employee Relation Events (Corporate)',45),(77,'03','Corporate & Operation meetings & events',45),(78,'00','No detail',12),(79,'00','No detail',13),(80,'00','No detail',14),(81,'00','No detail',16),(82,'00','No detail',19),(83,'00','No detail',21),(84,'00','No detail',22),(85,'00','No detail',23),(86,'00','No detail',24),(87,'00','No detail',25),(88,'00','No detail',26),(89,'00','No detail',27),(90,'00','No detail',29),(91,'00','No detail',30),(92,'00','No detail',31),(93,'00','No detail',32),(94,'00','No detail',33),(95,'00','No detail',34),(96,'00','No detail',35),(97,'00','No detail',36),(98,'00','No detail',38),(99,'00','No detail',39),(100,'00','No detail',46),(101,'00','No detail',40),(102,'00','No detail',41),(103,'00','No detail',42),(104,'00','No detail',43),(105,'00','No detail',44),(106,'00','No detail',18);
/*!40000 ALTER TABLE `BBG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BBLINE`
--

DROP TABLE IF EXISTS `BBLINE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BBLINE` (
  `BL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BL_CODE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BL_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`BL_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BBLINE`
--

LOCK TABLES `BBLINE` WRITE;
/*!40000 ALTER TABLE `BBLINE` DISABLE KEYS */;
INSERT INTO `BBLINE` VALUES (6,'02-002-001','IT Tools'),(7,'02-003-002','ADSL'),(8,'03-002-001','Advert & Sponsor'),(9,'03-002-005','MKT Events'),(10,'03-006-003','Business Insurance'),(11,'03-002-003','Digital marketing'),(12,'03-006-008','Donation Fees'),(13,'02-001-003','Electricity'),(14,'03-005-002','External Entertainment'),(15,'03-003-001','Events/Seminars'),(16,'03-005-003','Gift for client'),(17,'03-005-001','Internal Entertainment'),(18,'03-001-003','Local travel for Business'),(19,'03-006-010','Misc Expenses'),(20,'02-003-003','Mobile phone'),(21,'02-001-001','Office rent'),(22,'02-001-004','Office tools'),(23,'03-002-002','Online & direct marketing'),(24,'03-006-009','Other taxes and fares'),(25,'01-002-003','Other training'),(26,'03-001-004','Overseas travel for Business'),(27,'03-004-002','Post survey'),(28,'02-003-004','Postage & courier'),(29,'03-002-004','PR'),(30,'03-004-001','Pre-post survey'),(31,'02-003-005','Printing & photocopy'),(32,'03-003-001','Events/Seminars'),(33,'03-006-004','Service & Consulting fee'),(34,'02-004-001','Stationery'),(35,'03-001-001','Taxi, car rental'),(36,'02-003-001','Telephone & faxes'),(37,'03-004-003','Other Events');
/*!40000 ALTER TABLE `BBLINE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BBUDGET`
--

DROP TABLE IF EXISTS `BBUDGET`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BBUDGET` (
  `BUDGET_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS` int(11) DEFAULT NULL,
  `DEPT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`BUDGET_ID`),
  KEY `FKo9y98wv5upehap6uv8stro7gx` (`DEPT_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BBUDGET`
--

LOCK TABLES `BBUDGET` WRITE;
/*!40000 ALTER TABLE `BBUDGET` DISABLE KEYS */;
/*!40000 ALTER TABLE `BBUDGET` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BCOMPANY`
--

DROP TABLE IF EXISTS `BCOMPANY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BCOMPANY` (
  `COMPANY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BCOMPANY`
--

LOCK TABLES `BCOMPANY` WRITE;
/*!40000 ALTER TABLE `BCOMPANY` DISABLE KEYS */;
INSERT INTO `BCOMPANY` VALUES (1,'Talentnet'),(4,'Yen Thanh'),(8,'VietHCMI');
/*!40000 ALTER TABLE `BCOMPANY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BCRITERIA`
--

DROP TABLE IF EXISTS `BCRITERIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BCRITERIA` (
  `CRITERIA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CRITERIA_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`CRITERIA_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BCRITERIA`
--

LOCK TABLES `BCRITERIA` WRITE;
/*!40000 ALTER TABLE `BCRITERIA` DISABLE KEYS */;
INSERT INTO `BCRITERIA` VALUES (1,'Square'),(3,'Headcounts');
/*!40000 ALTER TABLE `BCRITERIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BCRITERIADETAIL`
--

DROP TABLE IF EXISTS `BCRITERIADETAIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BCRITERIADETAIL` (
  `CD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AMOUNT` int(11) DEFAULT NULL,
  `CRITERIA_ID` int(11) DEFAULT NULL,
  `DEPT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CD_ID`),
  KEY `FKlkky2onjuyoj6jy9xfgylf6u7` (`CRITERIA_ID`),
  KEY `FKqj8fv32t1xjei2iushjnr2rtu` (`DEPT_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BCRITERIADETAIL`
--

LOCK TABLES `BCRITERIADETAIL` WRITE;
/*!40000 ALTER TABLE `BCRITERIADETAIL` DISABLE KEYS */;
INSERT INTO `BCRITERIADETAIL` VALUES (1,0,1,2),(2,450,1,3),(3,0,1,27),(5,0,1,32),(6,0,1,33),(13,200,1,34),(14,0,1,35),(15,0,1,36),(16,0,1,37),(19,100,1,40),(20,150,1,41),(62,0,3,56),(22,600,1,43),(23,550,1,44),(24,350,1,45),(25,500,1,46),(26,400,1,47),(27,300,1,48),(28,650,1,49),(29,10,3,2),(30,20,3,3),(31,90,3,27),(32,30,3,32),(33,100,3,33),(34,40,3,34),(35,50,3,35),(36,60,3,36),(37,70,3,37),(39,90,3,40),(40,100,3,41),(61,0,1,56),(42,20,3,43),(43,30,3,44),(44,40,3,45),(45,50,3,46),(46,60,3,47),(47,70,3,48),(48,80,3,49),(51,0,1,51),(52,0,3,51),(56,0,3,53),(55,0,1,53),(57,0,1,54),(58,0,3,54),(59,0,1,55),(60,0,3,55),(63,0,1,57),(64,0,3,57),(65,0,1,58),(66,0,3,58);
/*!40000 ALTER TABLE `BCRITERIADETAIL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BDEPT`
--

DROP TABLE IF EXISTS `BDEPT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BDEPT` (
  `DEPT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTROL` bit(1) DEFAULT NULL,
  `DEPT_CODE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DEPT_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SPONSOR` bit(1) DEFAULT NULL,
  `GROUP_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`DEPT_ID`),
  KEY `FKlm8slhia9xblroctms6ecoaqw` (`GROUP_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BDEPT`
--

LOCK TABLES `BDEPT` WRITE;
/*!40000 ALTER TABLE `BDEPT` DISABLE KEYS */;
INSERT INTO `BDEPT` VALUES (2,_binary '','PY2','PYRHNO',_binary '',1),(3,_binary '','FAD','FAD',_binary '',2),(27,_binary '','YTC','YTC',_binary '',8),(32,_binary '','PY1','PYRHCM',_binary '',1),(33,_binary '','VHC','VHC',_binary '',11),(34,_binary '\0','POS','POS',_binary '',1),(35,_binary '','OS1','OSSHCM',_binary '',1),(36,_binary '','OS2','OSSHNO',_binary '',1),(37,_binary '','PYM','PYRMGT',_binary '',1),(40,_binary '\0','ES1','ESSHCM',_binary '',12),(41,_binary '\0','ES2','ESSHNO',_binary '',12),(56,_binary '','HC2','Survey',_binary '',13),(43,_binary '','BIM','BIM',_binary '',14),(44,_binary '','BOM','BOM',_binary '',15),(45,_binary '','ITD','ITD',_binary '',16),(46,_binary '','HRD','HRD',_binary '',17),(47,_binary '','MKT','MKT',_binary '',18),(48,_binary '','PRJ','PRJ',_binary '',19),(49,_binary '\0','HNS','HN Support',_binary '',20),(51,_binary '','YTH','YenThanh',_binary '',1),(53,_binary '','VHC','VietHCMI',_binary '',21),(54,_binary '','INV','Investment',_binary '',22),(55,_binary '','HC1','Admin',_binary '',13),(57,_binary '','HC3','Reward',_binary '',13),(58,_binary '','HC4','Non Reward',_binary '',13);
/*!40000 ALTER TABLE `BDEPT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BGROUP`
--

DROP TABLE IF EXISTS `BGROUP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BGROUP` (
  `GROUP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GROUP_CODE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COMPANY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`GROUP_ID`),
  KEY `FK9jce8fjtxx2kev892x85bfvyb` (`COMPANY_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BGROUP`
--

LOCK TABLES `BGROUP` WRITE;
/*!40000 ALTER TABLE `BGROUP` DISABLE KEYS */;
INSERT INTO `BGROUP` VALUES (1,'POS',1),(2,'FAD',1),(8,'YTC',4),(11,'VHC',8),(12,'ESS',1),(13,'HCS',1),(14,'BIM',1),(15,'BOM',1),(16,'ITD',1),(17,'HRD',1),(18,'MKT',1),(19,'PRJ',1),(20,'HNS',1),(21,'VHC',1),(22,'INV',1);
/*!40000 ALTER TABLE `BGROUP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BHISAMOUNT`
--

DROP TABLE IF EXISTS `BHISAMOUNT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BHISAMOUNT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AMOUNT` bigint(20) DEFAULT NULL,
  `COMPANYID` int(11) DEFAULT NULL,
  `SPONSOR` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BL_CODE` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BL_NAME` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=885 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BHISAMOUNT`
--

LOCK TABLES `BHISAMOUNT` WRITE;
/*!40000 ALTER TABLE `BHISAMOUNT` DISABLE KEYS */;
/*!40000 ALTER TABLE `BHISAMOUNT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BMOREDETAIL`
--

DROP TABLE IF EXISTS `BMOREDETAIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BMOREDETAIL` (
  `MDT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AMOUNT` bigint(20) DEFAULT NULL,
  `DETAIL` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BD_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`MDT_ID`),
  KEY `FKjh93vmwcf34v2g8mk03p6aga9` (`BD_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BMOREDETAIL`
--

LOCK TABLES `BMOREDETAIL` WRITE;
/*!40000 ALTER TABLE `BMOREDETAIL` DISABLE KEYS */;
/*!40000 ALTER TABLE `BMOREDETAIL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BROLE`
--

DROP TABLE IF EXISTS `BROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BROLE` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BROLE`
--

LOCK TABLES `BROLE` WRITE;
/*!40000 ALTER TABLE `BROLE` DISABLE KEYS */;
INSERT INTO `BROLE` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `BROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BSYSROLE`
--

DROP TABLE IF EXISTS `BSYSROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BSYSROLE` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BSYSROLE`
--

LOCK TABLES `BSYSROLE` WRITE;
/*!40000 ALTER TABLE `BSYSROLE` DISABLE KEYS */;
INSERT INTO `BSYSROLE` VALUES (1,'REVIEWER'),(2,'REPORTER');
/*!40000 ALTER TABLE `BSYSROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BUSER`
--

DROP TABLE IF EXISTS `BUSER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BUSER` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `FULLNAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PASSWORD` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `USERNAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `FK1oyno29cme38vvtyaivgcd4gq` (`ROLE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BUSER`
--

LOCK TABLES `BUSER` WRITE;
/*!40000 ALTER TABLE `BUSER` DISABLE KEYS */;
INSERT INTO `BUSER` VALUES (14,_binary '','Dao Thi Luu','$2a$10$33jb43/TR9N/sKTz81zJxOccwrt78PHFKoRzqs8aVYeB7e6Rof2rO','dtluu',1),(15,_binary '','Nguyen Thi Minh Phuong','$2a$10$jP.JLUNacnKLlLgwz2dE/eIJWNJIKmTTKQnQOyCDPdq4JV7p2vo3.','NTMP',2),(18,_binary '\0','Nguyen Thi Thanh Huong','$2a$10$Y3DUytuVicHufDQKXS6jo.GECoFeqxK7hv6QfcuNOkftoOsQXAb4W','NTTH',2),(19,_binary '\0','Hoang Thi Thai Ha','$2a$10$2ZJ28zj8g4tK1rfHyekbgOYQRGrgHr/aSpncszTcoGM./PZH2AVbW','HTTH',2),(20,_binary '\0','Nguyen Thi Nhu Loan','$2a$10$RGA7/FAxvyVOZ7sqSewZIuaz2MqS4txA6OKbCcIiUtYGm3VpUGyfO','NTNL',2),(21,_binary '\0','Nguyen Thi An Ha','$2a$10$BZmaQT4ZlvgqCrLxhJlBKOPWnE1uLQEIqFXVkcP4JniGSGioYX9QK','NTAH',2),(22,_binary '\0','Wong Kum Wai','$2a$10$6MHadm7d8HYd.yeuFbYy7u5x9yiDJYEyjMFkroFYTQ4cNVppumrdG','WKW',2),(23,_binary '','Vo Thi Nhu Y','$2a$10$aTPFW.IfubOshUQvw5bx5.8P04IyqNdFLWtWXcrcZsbSlzngWuMri','VTNY',2),(24,_binary '','Tran Ngoc Thich','$2a$10$lcYiJwR5lGhK5naJy52n9ONjgp5ZVJObX4RUn/P7gLmKf0OrV.sYi','TNT',2),(25,_binary '\0','Ly Ngoc Tran','$2a$10$KX9TusdK6y1VB81BHVpzkuUgUSzUBNfjudqtVllLjAXRd3iBBt7mG','LNT',2),(26,_binary '\0','Tran Kim Oanh','$2a$10$v0bpSXQusoSd9OnFvxTpHuN8SgEBn4CiOXbftYx4mEGIr6miV.5LS','TKO',2),(27,_binary '\0','Ngo Ngoc Thuc Doan','$2a$10$dRgr/8twSExukvK4m4JOW.AMTJw9s7nLuS9l8KBMraPdeszR39oJ.','NNTD',2),(28,_binary '\0','Le Anh Thuy Chau','$2a$10$LlA5C5IjT1oLnQx8eIcILOg3e8VSvP/KezG5caqsc4hu8.nErKyzO','LATC',2),(29,_binary '\0','Tran Minh Thuong','$2a$10$d4lNQlK/96YFlmXKvLtLSeUzkDneRDPpZgxfcHzrQcW02Qzg/98YG','TMT',2),(30,_binary '\0','Dang Ngoc Khanh Van','$2a$10$kNDH1YYyoCc9bQSy1J08keyyNOGLaMCxCN/plylc42ujYPqK.pQrK','DNKV',2),(31,_binary '\0','Nguyen Hoang Kim Khanh','$2a$10$h7GUA6eG/CjrX4Mx.DSgVeV24aAylgXKQf1NM210vr9yfIWOKS5IO','NHKK',2),(32,_binary '\0','Tieu Yen Trinh','$2a$10$4I5WHTCPR.is52VRpoqaGOie6mZyphxluRr0C5HRumhYKm8AIzeVm','TYT',2);
/*!40000 ALTER TABLE `BUSER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BUSERROLE`
--

DROP TABLE IF EXISTS `BUSERROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BUSERROLE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BCD_ID` int(11) DEFAULT NULL,
  `GROUP_ID` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK8mvk199tij7fbqce9v1mdt91f` (`BCD_ID`),
  KEY `FKqhlcjws80g2q0cjbk3ivw1foh` (`GROUP_ID`),
  KEY `FKo76iqohcp9afamiblxhj2rm3w` (`ROLE_ID`),
  KEY `FKr1h1w880wje0nliyixadd5rfa` (`USER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BUSERROLE`
--

LOCK TABLES `BUSERROLE` WRITE;
/*!40000 ALTER TABLE `BUSERROLE` DISABLE KEYS */;
INSERT INTO `BUSERROLE` VALUES (76,NULL,1,1,18),(77,NULL,13,1,18),(78,NULL,12,1,19),(79,NULL,17,1,20),(80,NULL,18,1,21),(86,3,NULL,2,23),(87,43,NULL,2,24),(89,35,NULL,2,25),(90,36,NULL,2,25),(91,51,NULL,2,25),(93,2,NULL,2,26),(94,32,NULL,2,26),(95,37,NULL,2,26),(96,NULL,NULL,2,27),(97,NULL,NULL,2,28),(98,45,NULL,2,29),(99,46,NULL,2,30),(100,47,NULL,2,31),(101,NULL,2,1,15),(102,44,NULL,2,15),(103,NULL,14,1,15),(104,NULL,16,1,22),(105,48,NULL,2,22),(106,NULL,15,1,32),(107,NULL,19,1,32);
/*!40000 ALTER TABLE `BUSERROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BWB`
--

DROP TABLE IF EXISTS `BWB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BWB` (
  `WB_ID` int(11) NOT NULL AUTO_INCREMENT,
  `WB_CODE` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WB_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BL_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`WB_ID`),
  KEY `FKbr52nu21nsg2bpwg5rp6vymi0` (`BL_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BWB`
--

LOCK TABLES `BWB` WRITE;
/*!40000 ALTER TABLE `BWB` DISABLE KEYS */;
INSERT INTO `BWB` VALUES (7,'01',' INFRASTRUCTURE',6),(8,'04','ANNUAL (OPEX)',6),(9,'05','APPLICATION 2017',6),(10,'20','INVESTMENT (2017)',6),(11,'27','Other IT Investments',6),(12,'31','PC/LAP TOP',6),(13,'02','ADSL',7),(14,'03','PR and Advert & Sponsor',8),(15,'07','BU Events',9),(16,'08','Business Insurance',10),(17,'09','Corporate Events',9),(18,'10','Digital marketing',11),(19,'11','Donation Fees',12),(20,'12','Electricity',13),(21,'14','External Entertainment',14),(22,'15','External Events/Seminars',15),(23,'17','Gift for client',16),(24,'19','Internal Entertainment',17),(25,'21','Local travel for Business',18),(26,'22','Misc Expenses',19),(27,'23','Mobile phone',20),(28,'24','Office rent',21),(29,'25','Office tools',22),(30,'26','Online & direct marketing',23),(31,'28','Other taxes and fares',24),(32,'29','Other training',25),(33,'30','Overseas travel for Business',26),(34,'33','Post survey',27),(35,'32','Postage & courier',28),(36,'34','PR',29),(38,'35','Pre-survey',30),(39,'36','Printing & photocopy',31),(40,'38','Repairs & maintenance',32),(41,'39','Service & Consulting fee',33),(42,'40','Stationery',34),(43,'41','Taxi, car rental',35),(44,'42','Telephone & faxes',36),(45,'43','TLN internal events',37),(46,'56','Training materials',31);
/*!40000 ALTER TABLE `BWB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BYEAR`
--

DROP TABLE IF EXISTS `BYEAR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BYEAR` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `YEAR` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BYEAR`
--

LOCK TABLES `BYEAR` WRITE;
/*!40000 ALTER TABLE `BYEAR` DISABLE KEYS */;
INSERT INTO `BYEAR` VALUES (2,'2019-12-31');
/*!40000 ALTER TABLE `BYEAR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MAPBL_DEPT`
--

DROP TABLE IF EXISTS `MAPBL_DEPT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `MAPBL_DEPT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BL_ID` int(11) DEFAULT NULL,
  `DEPT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK59h7thvsic8dahq0ye1iug5r7` (`BL_ID`),
  KEY `FKos3eqmuheljsfo5qu8xct1vh6` (`DEPT_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=193 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MAPBL_DEPT`
--

LOCK TABLES `MAPBL_DEPT` WRITE;
/*!40000 ALTER TABLE `MAPBL_DEPT` DISABLE KEYS */;
INSERT INTO `MAPBL_DEPT` VALUES (8,6,45),(9,7,3),(10,13,3),(11,20,3),(12,21,3),(13,22,3),(14,24,3),(15,28,3),(16,31,3),(17,15,3),(18,34,3),(19,35,3),(20,36,3),(21,8,47),(22,9,47),(23,23,47),(24,29,47),(28,12,2),(29,12,32),(30,12,35),(31,12,36),(32,12,37),(33,12,3),(36,12,43),(37,12,44),(38,12,45),(39,12,46),(40,12,47),(41,12,48),(42,14,2),(43,14,32),(44,14,35),(45,14,36),(46,14,37),(47,14,3),(50,14,43),(51,14,44),(52,14,45),(53,14,46),(54,14,47),(55,14,48),(56,16,2),(57,16,32),(58,16,35),(59,16,36),(60,16,37),(61,16,3),(64,16,43),(65,16,44),(66,16,45),(67,16,46),(68,16,47),(69,16,48),(71,17,2),(72,17,32),(73,17,35),(74,17,36),(75,17,37),(76,17,3),(79,17,43),(80,17,44),(81,17,45),(82,17,46),(83,17,47),(84,17,48),(85,18,2),(86,18,32),(87,18,35),(88,18,36),(89,18,37),(90,18,3),(93,18,43),(94,18,44),(95,18,45),(96,18,46),(97,18,47),(98,18,48),(99,19,2),(100,19,32),(101,19,35),(102,19,36),(103,19,37),(104,19,3),(107,19,43),(108,19,44),(109,19,45),(110,19,46),(111,19,47),(112,19,48),(113,25,2),(114,25,32),(115,25,35),(116,25,36),(117,25,37),(118,25,3),(121,25,43),(122,25,44),(123,25,45),(124,25,46),(125,25,47),(126,25,48),(127,26,2),(128,37,2),(129,33,2),(130,26,32),(131,37,32),(132,33,32),(133,26,35),(134,37,35),(135,33,35),(136,26,36),(137,37,36),(138,33,36),(139,26,37),(140,37,37),(141,33,37),(142,26,3),(143,37,3),(144,33,3),(151,26,43),(152,37,43),(153,33,43),(154,26,44),(155,37,44),(156,33,44),(157,26,45),(158,37,45),(159,33,45),(160,26,46),(161,37,46),(162,33,46),(163,26,47),(164,37,47),(165,33,47),(166,26,48),(167,37,48),(168,33,48),(169,12,51),(170,14,51),(171,15,51),(172,16,51),(173,17,51),(174,18,51),(175,19,51),(176,25,51),(177,6,51),(178,26,51),(179,33,51),(180,37,51);
/*!40000 ALTER TABLE `MAPBL_DEPT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `SPONSOR_REPORT`
--

DROP TABLE IF EXISTS `SPONSOR_REPORT`;
/*!50001 DROP VIEW IF EXISTS `SPONSOR_REPORT`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `SPONSOR_REPORT` AS SELECT 
 1 AS `ID`,
 1 AS `AMOUNT`,
 1 AS `DEPT_ID`,
 1 AS `DEPT_CODE`,
 1 AS `BL_ID`,
 1 AS `BL_NAME`,
 1 AS `BL_CODE`,
 1 AS `BUDGET_ID`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'budgetmanagement'
--

--
-- Final view structure for view `SPONSOR_REPORT`
--

/*!50001 DROP VIEW IF EXISTS `SPONSOR_REPORT`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `SPONSOR_REPORT` AS select `BBDETAIL`.`BD_ID` AS `ID`,`BBDETAIL`.`AMOUNT` AS `AMOUNT`,`BBDETAIL`.`DEPT_ID` AS `DEPT_ID`,`BDEPT`.`DEPT_CODE` AS `DEPT_CODE`,`BBDETAIL`.`BL_ID` AS `BL_ID`,`BBLINE`.`BL_NAME` AS `BL_NAME`,`BBLINE`.`BL_CODE` AS `BL_CODE`,`BBDETAIL`.`BUDGET_ID` AS `BUDGET_ID` from ((`BBDETAIL` join `BBLINE` on((`BBDETAIL`.`BL_ID` = `BBLINE`.`BL_ID`))) join `BDEPT` on((`BBDETAIL`.`DEPT_ID` = `BDEPT`.`DEPT_ID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-20  8:44:45
