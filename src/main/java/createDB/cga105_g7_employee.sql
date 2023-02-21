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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EMP_NO` int NOT NULL AUTO_INCREMENT,
  `EMP_PW` varchar(12) NOT NULL,
  `EMP_NAME` varchar(20) NOT NULL,
  `EMP_DEP` varchar(20) NOT NULL,
  `JOB_TITLE` varchar(15) NOT NULL,
  `EMP_IDNUM` varchar(10) NOT NULL,
  `EMP_EMAIL` varchar(50) NOT NULL,
  `EMP_TEL` varchar(15) NOT NULL,
  `EMP_STATUS` tinyint NOT NULL,
  `EMP_HIREDATE` date NOT NULL,
  PRIMARY KEY (`EMP_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=300075 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (300001,'1234','劉昀亘','管理部','專員','H111222333','hsuan@gmail.com','0933467899',1,'2019-04-01'),(300002,'123456','曹政奭','產品部','主任','H134566899','seok@gmail.com','0936159001',1,'2019-02-14'),(300003,'778','李到晛','管理部','資深專員','H114566734','yan@gmail.com','0932933610',1,'2019-12-03'),(300004,'123456','朴栖含','開發部','專員','H157955733','sihan@gmail.com','0954677455',1,'2020-02-02'),(300005,'a000001','高庚杓','客服部','經理','H167466843','ganseok@gmail.com','0911475677',2,'2020-05-04'),(300006,'b000002','全智賢','行政部','經理','H234665423','jihung@gmail.com','0945633786',1,'2020-06-16'),(300007,'c000003','林允兒','行政管理部','專員','H223884203','yona@gmail.com','0934522744',1,'2020-10-12'),(300008,'d000004','鄭秀晶','管理部','專員','H223444733','krystal@gmail.com','0934266366',2,'2020-08-26'),(300012,'h000008','宋仲基','電商部','副理','H182474366','junki@gmail.com','0937477596',1,'2021-01-04'),(300013,'i000009','李孝利','行政管理部','主任','H223884209','huoli@gmail.com','0964733854',1,'2021-04-13'),(300014,'j0000010','李東海','電商部','主任','H163473556','donhea@gmail.com','0914736367',1,'2021-05-05'),(300015,'123456','姜智嘉','行政部','專員','H223884273','chia7593672@gmail.com','0911406280',1,'2023-01-02');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-14 16:27:39
