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
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon` (
  `COUPON_NO` int NOT NULL AUTO_INCREMENT,
  `COUPON_TITLE` varchar(400) NOT NULL,
  `COUPON_TYPE` tinyint NOT NULL,
  `COUPON_CONTENT` varchar(2000) NOT NULL,
  `COUPON_START` datetime NOT NULL,
  `COUPON_END` datetime NOT NULL,
  `COUPON_IDENTITY` tinyint NOT NULL,
  `COUPON_DISCOUNT` float NOT NULL,
  `COUPON_COUNT` int NOT NULL,
  `COUPON_SELECTOR` int NOT NULL,
  `COUPON_PER_AMOUNT` int NOT NULL,
  PRIMARY KEY (`COUPON_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'母親節八折優惠券',1,'本券限會員兌換，一張限換一次。','2022-05-01 00:00:00','2022-05-31 11:59:59',1,0.8,500,500,1),(2,'清明節七五折優惠券',1,'本券全台門市均可使用，且不限商品使用','2022-04-01 00:00:00','2022-04-30 11:59:59',0,0.75,5000,1000,1),(3,'父親節500元優惠券',0,'可於消費時選擇是否抵扣商品金額，使用時一筆訂單限抵用一張折價券，同時折價券不能與購物金合併使用。','2022-08-01 00:00:00','2022-09-30 11:59:59',2,500,500,100,1),(4,'春節500元優惠券',0,'使用方式等同折抵現金。若有異動，修改後的活動內容及約定條款將公布在網站上，不另行個別通知。','2023-02-01 00:00:00','2023-02-28 11:59:59',0,500,5000,0,1),(5,'雙11，500元優惠券',0,'如遇不可抗力之事由致內容需變更，本公司保有解釋、修改與終止活動之權利，並將訊息於本網站上公佈，不另行通知，詳情請以官網說明為準。','2023-11-11 00:00:00','2023-11-30 11:59:59',0,500,5000,200,1),(6,'聖誕節九折優惠券',1,'優惠券限網站線上折抵商品價格，每個商品限用一張優惠券，購物車中會顯示可折抵之。','2023-12-01 00:00:00','2023-12-31 11:59:59',2,0.9,500,10000,5),(7,'中秋節八折優惠券',1,'優惠券不得轉讓與其他人使用，不得兌換現金，也不得兌換其他活動之贈品等服務。','2023-09-01 00:00:00','2023-09-30 11:59:59',1,0.8,500,2000,1),(8,'兒童節200元優惠券',0,'每張優惠券包含券號、活動規則及使用效期，提醒您於使用效期前使用，逾期則視為棄權。','2023-02-01 00:00:00','2023-02-28 11:59:59',1,200,2000,500,2),(9,'情人節九五優惠券',1,'每張優惠券不得要求折抵現金，恕不找零；逾期視同作廢。','2023-07-01 00:00:00','2023-07-31 11:59:59',0,0.95,8000,1000,1),(10,'中元節1000元優惠券',1,'每張優惠券可抵全店開立發票之商品消費，唯需單次使用超過該抵用券金額。','2023-07-01 00:00:00','2023-07-31 11:59:59',0,0.95,8000,1000,1),(11,'春節500元優惠券',0,'每張優惠券不得要求折抵現金，恕不找零；逾期視同作廢。','2023-01-21 00:00:00','2023-02-28 00:00:00',0,500,20,5000,1),(12,'春節95折優惠券',1,'每張優惠券包含券號、活動規則及使用效期，提醒您於使用效期前使用，逾期則視為棄權。','2023-01-21 00:00:00','2023-02-28 00:00:00',0,0.95,20,1500,1);
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-14 16:27:45
