CREATE DATABASE  IF NOT EXISTS `specializedbikefit` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `specializedbikefit`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: specializedbikefit
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `bicycles`
--

DROP TABLE IF EXISTS `bicycles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bicycles` (
  `bike_id` int NOT NULL AUTO_INCREMENT,
  `bike_brand` varchar(255) DEFAULT NULL,
  `bike_model` varchar(255) DEFAULT NULL,
  `bike_size` varchar(255) DEFAULT NULL,
  `bike_year` int DEFAULT NULL,
  `bike_reach` float NOT NULL,
  `bike_stack` float NOT NULL,
  `bike_seattubelength` float NOT NULL,
  `bike_toptubelength` float NOT NULL,
  `bike_isEbike` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`bike_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bicycles`
--

LOCK TABLES `bicycles` WRITE;
/*!40000 ALTER TABLE `bicycles` DISABLE KEYS */;
INSERT INTO `bicycles` VALUES (1,'Specialized','Status 160','S3',2021,462.607,623.496,420,580,0),(2,'Triban','RC 520','S3',2019,423.066,599.427,444.642,580,0),(3,'Giant','Reign 2 LTD','S4',2024,445.415,605.158,458.395,586.877,0);
/*!40000 ALTER TABLE `bicycles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bikefits`
--

DROP TABLE IF EXISTS `bikefits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bikefits` (
  `bikefit_id` int NOT NULL AUTO_INCREMENT,
  `bikefit_date` date DEFAULT NULL,
  `bikefit_name` varchar(255) DEFAULT NULL,
  `bikefit_saddleHeight` float NOT NULL,
  `bikefit_saddleSetBack` float NOT NULL,
  `bikefit_saddleToBar` float NOT NULL,
  `bike_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`bikefit_id`),
  KEY `FK7vjprt9446vp4irgetc145apb` (`bike_id`),
  KEY `FKhibmynlgahlncnbxi9ep1auqa` (`user_id`),
  CONSTRAINT `FK7vjprt9446vp4irgetc145apb` FOREIGN KEY (`bike_id`) REFERENCES `bicycles` (`bike_id`),
  CONSTRAINT `FKhibmynlgahlncnbxi9ep1auqa` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bikefits`
--

LOCK TABLES `bikefits` WRITE;
/*!40000 ALTER TABLE `bikefits` DISABLE KEYS */;
INSERT INTO `bikefits` VALUES (1,'2025-12-08','Bikefit Triban Verano',75.055,8.5,62.4,2,1),(2,'2025-12-08','Bikefit Status 160',75.055,8.5,62.4,1,1);
/*!40000 ALTER TABLE `bikefits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `user_surname` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_height` float NOT NULL,
  `user_torsolength` float NOT NULL,
  `user_armlength` float NOT NULL,
  `user_inseamlength` float NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Alberto','González','alber@gmail.com','1234',178,60,70,85);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_bicycle`
--

DROP TABLE IF EXISTS `users_bicycle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_bicycle` (
  `bike_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`bike_id`,`user_id`),
  KEY `FKtl0xis3qos42qfmsmoedjkceg` (`user_id`),
  CONSTRAINT `FKba8tk60aqbckvjuj900molqbu` FOREIGN KEY (`bike_id`) REFERENCES `bicycles` (`bike_id`),
  CONSTRAINT `FKtl0xis3qos42qfmsmoedjkceg` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_bicycle`
--

LOCK TABLES `users_bicycle` WRITE;
/*!40000 ALTER TABLE `users_bicycle` DISABLE KEYS */;
INSERT INTO `users_bicycle` VALUES (1,1),(2,1),(3,1);
/*!40000 ALTER TABLE `users_bicycle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-08 21:21:15
