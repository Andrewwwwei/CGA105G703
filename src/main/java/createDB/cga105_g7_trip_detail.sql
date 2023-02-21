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
-- Table structure for table `trip_detail`
--

DROP TABLE IF EXISTS `trip_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip_detail` (
  `TRIP_DETAIL_ID` int NOT NULL AUTO_INCREMENT,
  `TRIP_ID` int NOT NULL,
  `LOC_ID` int NOT NULL,
  `ARRIVAL_TIME` datetime NOT NULL,
  `LEAVE_TIME` datetime NOT NULL,
  PRIMARY KEY (`TRIP_DETAIL_ID`),
  KEY `FK_TRIPDET_TRIPID` (`TRIP_ID`),
  KEY `FK_TRIPDET_LOCID` (`LOC_ID`),
  CONSTRAINT `FK_TRIPDET_LOCID` FOREIGN KEY (`LOC_ID`) REFERENCES `location` (`LOC_ID`),
  CONSTRAINT `FK_TRIPDET_TRIPID` FOREIGN KEY (`TRIP_ID`) REFERENCES `trip` (`TRIP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_detail`
--

LOCK TABLES `trip_detail` WRITE;
/*!40000 ALTER TABLE `trip_detail` DISABLE KEYS */;
INSERT INTO `trip_detail` VALUES (1,1,1,'2022-01-01 10:00:00','2020-01-01 10:30:00'),(2,1,2,'2022-01-01 11:00:00','2020-01-01 11:30:00'),(3,1,3,'2022-01-01 13:00:00','2020-01-01 13:30:00'),(4,1,4,'2022-01-02 13:00:00','2020-01-02 13:30:00'),(5,1,5,'2022-01-02 14:00:00','2020-01-02 14:30:00'),(6,1,6,'2022-01-02 15:00:00','2020-01-02 15:30:00'),(7,1,5,'2022-01-02 16:00:00','2020-01-02 16:30:00'),(8,1,7,'2022-01-03 10:00:00','2020-01-03 10:30:00'),(9,1,9,'2022-01-03 13:00:00','2020-01-03 13:30:00'),(10,1,10,'2022-01-03 15:00:00','2020-01-03 15:30:00'),(11,1,8,'2022-01-04 10:00:00','2020-01-04 10:30:00'),(12,9,1,'2022-01-01 10:00:00','2020-01-01 10:30:00'),(13,9,2,'2022-01-01 14:00:00','2020-01-01 16:30:00'),(14,9,10,'2022-01-02 10:00:00','2020-01-01 11:30:00'),(15,9,9,'2022-01-02 14:00:00','2020-01-02 16:30:00'),(16,9,1,'2022-01-02 17:00:00','2020-01-02 17:30:00'),(17,11,2,'2023-02-15 16:10:00','2023-02-15 16:30:00'),(18,11,11,'2023-02-13 16:30:00','2023-02-13 16:40:00'),(21,11,10,'2023-02-16 12:00:00','2023-02-16 13:10:00');
/*!40000 ALTER TABLE `trip_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-14 16:27:44