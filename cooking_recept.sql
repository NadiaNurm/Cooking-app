-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: cooking
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `recept`
--

DROP TABLE IF EXISTS `recept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recept` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `time` varchar(45) NOT NULL,
  `instruction` varchar(45) NOT NULL,
  `path` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recept`
--

LOCK TABLES `recept` WRITE;
/*!40000 ALTER TABLE `recept` DISABLE KEYS */;
INSERT INTO `recept` VALUES (1,'Яичница','15','some instruction',NULL),(2,'Кофе','5','some',NULL),(3,'Помидор с солью','2','Посыпьте помидор солью',NULL),(5,'р','р','р','C:\\Users\\User\\Desktop\\Картинки\\йогурт.jpeg'),(6,'1','1','1','C:\\Users\\User\\Desktop\\Картинки\\персики.jpg'),(7,'2','2','2',NULL),(8,'ооо','ооо','ооо',NULL),(9,'рецепт','1','1','C:\\Users\\User\\Downloads\\WhatsApp Image 2023-02-03 at 12.25.12 PM-PhotoRoom.png-PhotoRoom.png'),(10,'бекон','2 часа','...','C:\\Users\\User\\Desktop\\Картинки\\бекон.jpeg'),(11,'зефир','16','---','C:\\Users\\User\\Desktop\\Картинки\\зефир.jpeg'),(12,'Мой рецепт','10','Сделайте все, как в инструкции','C:\\Users\\User\\Desktop\\Картинки\\тофу.jpeg'),(13,'new','6','houtfgiy','C:\\Users\\User\\Desktop\\Картинки\\суши.jpg'),(14,'Манго','123','цфпвкргонеелшшдщшг','C:\\Users\\User\\Desktop\\Картинки\\салат.jpeg');
/*!40000 ALTER TABLE `recept` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-09  8:12:49
