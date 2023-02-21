-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: cga105_g7
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `ROOM_ID` int NOT NULL AUTO_INCREMENT,
  `VEN_ID` int NOT NULL,
  `ROOM_NAME` varchar(20) NOT NULL,
  `ROOM_AMOUNT` int NOT NULL,
  `ROOM_PEOPLE` tinyint NOT NULL,
  `ROOM_PRICE` int NOT NULL,
  `ROOM_AREA` tinyint NOT NULL,
  `ROOM_INTRO` varchar(200) NOT NULL,
  `ROOM_UPDATE` tinyint(1) NOT NULL DEFAULT '0',
  `ROOM_VIEW` int NOT NULL DEFAULT '0',
  `BREAKFAST` tinyint(1) NOT NULL DEFAULT '0',
  `AIR_CONDOTIONER` tinyint(1) NOT NULL DEFAULT '0',
  `WIFI` tinyint(1) NOT NULL DEFAULT '0',
  `TELEVISION` tinyint(1) NOT NULL DEFAULT '0',
  `SAFEBOX` tinyint(1) NOT NULL DEFAULT '0',
  `FRIDGE` tinyint(1) NOT NULL DEFAULT '0',
  `WATER_BOILER` tinyint(1) NOT NULL DEFAULT '0',
  `BATHROOM` tinyint(1) NOT NULL DEFAULT '0',
  `TOILETRIES` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ROOM_ID`),
  KEY `FK_VENID_ROOM` (`VEN_ID`),
  CONSTRAINT `FK_VENID_ROOM` FOREIGN KEY (`VEN_ID`) REFERENCES `vendor` (`VEN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,500000,'經濟雙人房',10,2,3500,7,'1張加大雙人床',1,173,0,1,1,1,0,0,1,1,1),(2,500000,'高級雙人房',5,2,5000,8,'1張加大雙人床',1,73,0,1,1,1,1,1,1,1,1),(3,500000,'豪華雙人房',5,2,8000,13,'1張加大雙人床',1,73,0,1,1,1,1,1,1,1,1),(4,500000,'高級四人房',8,4,7000,8,'1張加大雙人床和2張單人床',1,73,0,1,1,1,1,1,1,1,1),(5,500001,'兩床客房',15,2,5500,10,'1張沙發床和2張單人床',1,26,1,1,1,1,1,1,1,1,1),(6,500001,'標準四人間',10,4,7000,13,'4張單人床',1,28,1,1,1,1,1,1,1,1,1),(7,500001,'榻榻米高級間',4,2,7500,15,'2張單人床和2張日式床墊',1,26,1,1,1,1,1,1,1,1,1),(8,500001,'山景兩臥室複式間',2,4,15000,60,'1張加大雙人床和1張雙人床',1,27,1,1,1,1,1,1,1,1,1),(9,500002,'高級雙人房',8,2,4000,10,'1張加大雙人床',1,40,0,1,1,1,1,1,1,1,1),(10,500002,'頂級雙人房',6,2,9000,12,'2張雙人床',1,30,0,1,1,1,1,1,1,1,1),(11,500002,'尊爵雙床房',2,2,16000,15,'2張單人床',1,30,0,1,1,1,1,1,1,1,1),(12,500002,'海景四人房',5,4,11000,10,'2張雙人床',1,31,0,1,1,1,1,1,1,1,1),(13,500003,'單人房',5,1,850,2,'1張單人床',1,6,0,1,1,1,0,1,0,1,1),(14,500003,'雙床房',8,2,1000,3,'2張單人床',1,29,0,1,1,1,0,1,0,1,1),(15,500003,'標準雙人房',8,2,1000,3,'1張雙人床',1,9,0,1,1,1,0,1,0,1,1),(16,500003,'三人房',3,3,1500,5,'1張單人床和1張雙人床',1,9,0,1,1,1,0,1,0,1,1),(17,500003,'家庭房',5,4,2000,6,'2張雙人床',1,9,0,1,1,1,1,1,1,1,1),(18,500004,'摩登雙人房',5,2,2000,17,'1張加大雙人床',1,0,0,1,1,1,1,1,1,1,1),(19,500004,'薰衣草雙人房',2,2,2500,20,'1張加大雙人床',1,0,0,1,1,1,1,1,1,1,1),(20,500004,'市景雙床房',5,4,1600,15,'2張加大雙人床',1,0,0,1,1,1,1,1,1,1,1),(21,500004,'豪華雙床套房',2,4,3000,20,'2張加大雙人床',1,0,0,1,1,1,1,1,1,1,1),(22,500005,'標準雙人房',5,2,2000,8,'1張加大雙人床',1,1,0,1,1,1,1,1,1,1,1),(23,500005,'標準家庭房',5,4,4000,8,'2張加大雙人床',1,1,0,1,1,1,1,1,1,1,1),(24,500006,'商務雙人房',5,2,3000,8,'1張加大雙人床',1,0,1,1,1,1,1,1,1,1,1),(25,500006,'溫馨家庭房',5,4,6000,8,'2張加大雙人床',1,0,1,1,1,1,1,1,1,1,1),(26,500007,'經典雙人房',5,2,1500,8,'1張雙人床',1,0,0,1,1,1,1,1,1,1,1),(27,500007,'經典四人房',5,4,4500,8,'2張雙人床',1,0,0,1,1,1,1,1,1,1,1),(28,500008,'雙人房',5,2,1800,8,'1張雙人床',1,3,0,1,1,1,1,1,1,1,1),(29,500008,'四人房',5,4,3600,8,'2張雙人床',1,3,0,1,1,1,1,1,1,1,1),(30,500009,'豪華雙人房',5,2,8000,8,'1張雙人床',1,0,1,1,1,1,1,1,1,1,1),(31,500009,'豪華四人房',5,4,16000,8,'2張雙人床',1,0,1,1,1,1,1,1,1,1,1),(32,500010,'豪華四人房',5,4,8000,8,'2張雙人床',1,1,1,1,1,1,1,1,1,1,1),(33,500011,'豪華四人房',5,4,8000,8,'2張雙人床',1,3,1,1,1,1,1,1,1,1,1),(34,500012,'豪華四人房',5,4,8000,8,'2張雙人床',1,3,1,1,1,1,1,1,1,1,1),(35,500013,'豪華四人房',5,4,8000,8,'2張雙人床',1,4,1,1,1,1,1,1,1,1,1),(36,500014,'豪華四人房',5,4,8000,8,'2張雙人床',1,6,1,1,1,1,1,1,1,1,1),(37,500015,'豪華四人房',5,4,8000,8,'2張雙人床',1,7,1,1,1,1,1,1,1,1,1),(38,500016,'豪華四人房',5,4,8000,8,'2張雙人床',1,7,1,1,1,1,1,1,1,1,1),(39,500017,'豪華四人房',5,4,8000,8,'2張雙人床',1,8,1,1,1,1,1,1,1,1,1),(40,500000,'溫馨家庭房',5,4,3000,5,'二張大雙人床',0,0,0,1,1,1,0,1,1,1,1),(42,500000,'膠囊單人房',5,1,1000,2,'一張單人床',1,2,0,1,1,0,0,0,0,0,0);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-14 16:27:43
