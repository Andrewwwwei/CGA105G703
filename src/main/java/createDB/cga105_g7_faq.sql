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
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq` (
  `FAQ_ID` int NOT NULL AUTO_INCREMENT,
  `FAQ_CONTENT` varchar(2000) NOT NULL,
  `FAQ_TITLE` varchar(800) NOT NULL,
  PRIMARY KEY (`FAQ_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` VALUES (87,'根據各住宿而定。預訂時，Klook 將告知是否有線上付款未包含的稅費或額外費用，請於現場支付。','有含稅及房價有含稅及服務費嗎？'),(88,'房價採客房計價，但訂單內未經確認的額外房客（含兒童）可能需酌收額外費用。 預訂時請務必輸入正確的房客人數。','房價是採人數還是客房計價？'),(89,'加床及兒童預訂政策因各住宿而異，預訂前請查看住宿規定。','我可以在客房內加一張床／嬰兒床嗎？'),(90,'視訂單取消政策而定。例：免費取消意指訂單可於指定時間內取消，部分住宿可於入住前48小時取消。超過指定時間後取消，可能需酌收費用，詳情請查看住宿取消政策。','如果我取消訂單，需要額外付費嗎？'),(91,'請注意，若臨時取消行程，可能會因與店家的約定而扣除部分或全額費用，請詳閱您行程的取消規定，若要取消行程，請至『訂單管理』，進行取消即可。','若臨時行程變更，該如何取消呢?'),(95,'是的，預定住宿需先加入7 TOUR會員。我們加入會員的方式十分簡單，而且完全免費!! ','預定住宿需要先入會員嗎？');
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-14 16:27:40
