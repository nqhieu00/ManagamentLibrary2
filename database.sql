-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: us-cdbr-east-05.cleardb.net    Database: heroku_1a35764d7ede38c
-- ------------------------------------------------------
-- Server version	5.6.50-log

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_or6k6jmywerxbme223c988bmg` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=384 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (34,'2022-03-19 07:28:45',1,'2022-03-19 07:28:45',1,'Ho_Chi_Minh'),(44,'2022-03-19 07:38:32',1,'2022-03-19 07:38:32',1,'Tran Manh Tuan'),(54,'2022-03-19 14:45:18',1,'2022-03-19 14:45:18',1,'Tran Thi Ngan'),(64,'2022-03-19 14:47:50',1,'2022-03-19 14:47:50',1,'Nguyen Van Nam'),(74,'2022-03-20 16:27:29',114,'2022-03-20 16:27:29',114,'davin C'),(84,'2022-03-21 00:22:52',114,'2022-03-21 00:22:52',114,'Linh'),(254,'2022-03-21 06:12:14',114,'2022-03-21 06:12:14',114,'aaaaaaaaa aaaaaaaaa aaaaaaaaa aaaaaaaaaa aaaa'),(264,'2022-03-21 06:12:57',114,'2022-03-21 06:12:57',114,'aaaaaaaaa aaaaaaaaa aaaaaaaaa aaaaaaaaa aaaaaaaaa'),(274,'2022-03-21 06:14:10',114,'2022-03-21 06:14:10',114,'* Linh'),(284,'2022-03-21 06:14:43',114,'2022-03-21 06:14:43',114,'   Linh   '),(294,'2022-03-21 06:15:52',114,'2022-03-21 06:15:52',114,'24 Linh'),(304,'2022-03-21 10:06:57',114,'2022-03-21 10:06:57',114,'Quỳnh Lan'),(314,'2022-03-21 10:27:10',384,'2022-03-21 10:27:10',384,'Lan Quỳnh'),(324,'2022-03-21 11:57:20',114,'2022-03-21 11:57:20',114,'linhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh'),(334,'2022-03-21 11:58:06',114,'2022-03-21 11:58:06',114,'linh@'),(344,'2022-03-21 11:59:01',114,'2022-03-21 11:59:01',114,'     linh'),(364,'2022-03-21 15:33:11',114,'2022-03-21 15:33:11',114,'Hihi'),(374,'2022-03-22 20:57:04',114,'2022-03-22 20:57:04',114,'Xuân Diệu');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `count` bigint(20) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `publish_at` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `publisher_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKam9riv8y6rjwkua1gapdfew4j` (`category_id`),
  KEY `FKgtvt7p649s4x80y6f4842pnfq` (`publisher_id`),
  CONSTRAINT `FKam9riv8y6rjwkua1gapdfew4j` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKgtvt7p649s4x80y6f4842pnfq` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'2022-03-18 21:14:19',1,'2022-03-22 17:36:08',384,'book',12,'book.jpeg','book',2000,1,1),(4,'2022-03-18 16:45:59',114,'2022-03-22 11:01:24',114,'string',95,'book.jpeg','Dế mèn',2000,4,4),(14,'2022-03-18 16:46:14',114,'2022-03-22 10:59:08',114,'string',81,'book.jpeg','Dế choắt',2000,4,4),(104,'2022-03-23 13:26:48',1,'2022-03-23 13:26:48',1,'string',10,'book2.jpg','Test',2000,1,1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_author`
--

DROP TABLE IF EXISTS `book_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_author` (
  `book_id` bigint(20) NOT NULL,
  `author_id` bigint(20) NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`),
  KEY `FKbjqhp85wjv8vpr0beygh6jsgo` (`author_id`),
  CONSTRAINT `FKbjqhp85wjv8vpr0beygh6jsgo` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  CONSTRAINT `FKhwgu59n9o80xv75plf9ggj7xn` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_author`
--

LOCK TABLES `book_author` WRITE;
/*!40000 ALTER TABLE `book_author` DISABLE KEYS */;
INSERT INTO `book_author` VALUES (104,34),(14,64);
/*!40000 ALTER TABLE `book_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowing`
--

DROP TABLE IF EXISTS `borrowing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `borrowed_date` datetime DEFAULT NULL,
  `status` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeu12r38wvjgkybkcnyb09j3f5` (`user_id`),
  CONSTRAINT `FKeu12r38wvjgkybkcnyb09j3f5` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=504 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowing`
--

LOCK TABLES `borrowing` WRITE;
/*!40000 ALTER TABLE `borrowing` DISABLE KEYS */;
INSERT INTO `borrowing` VALUES (24,'2022-03-18 16:48:50',114,'2022-03-20 14:40:41',114,'2022-03-18 16:48:50',1,44),(244,'2022-03-19 23:48:39',84,'2022-03-21 04:34:06',114,'2022-03-19 23:48:39',1,84),(254,'2022-03-20 00:21:08',84,'2022-03-21 07:53:14',114,'2022-03-20 00:21:08',1,84),(264,'2022-03-20 00:25:34',84,'2022-03-21 07:59:32',114,'2022-03-20 00:25:34',2,84),(364,'2022-03-20 13:07:38',84,'2022-03-21 11:32:15',114,'2022-03-20 13:07:38',1,84),(474,'2022-03-22 13:20:54',114,'2022-03-22 17:36:08',384,'2022-03-22 13:20:54',1,114),(484,'2022-03-22 21:06:01',114,'2022-03-22 21:06:01',114,'2022-03-22 21:06:01',0,114),(494,'2022-03-22 21:06:39',114,'2022-03-22 21:06:39',114,'2022-03-22 21:06:39',0,114);
/*!40000 ALTER TABLE `borrowing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowing_item`
--

DROP TABLE IF EXISTS `borrowing_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowing_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `note` varchar(255) DEFAULT NULL,
  `payday` date DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `book_id` bigint(20) NOT NULL,
  `borrowing_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK67y8osox2vei42ybq22cyh6sh` (`book_id`),
  KEY `FK7r0vyeu4sr6ykd2jsfx6n7vtj` (`borrowing_id`),
  CONSTRAINT `FK67y8osox2vei42ybq22cyh6sh` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK7r0vyeu4sr6ykd2jsfx6n7vtj` FOREIGN KEY (`borrowing_id`) REFERENCES `borrowing` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=344 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowing_item`
--

LOCK TABLES `borrowing_item` WRITE;
/*!40000 ALTER TABLE `borrowing_item` DISABLE KEYS */;
INSERT INTO `borrowing_item` VALUES (4,'string','2022-03-18',_binary '',4,24),(14,'string','2022-03-18',_binary '',4,24),(174,NULL,NULL,_binary '',1,244),(184,NULL,NULL,_binary '',1,254),(194,NULL,NULL,_binary '\0',4,264),(254,NULL,NULL,_binary '',1,364),(314,'Test','2022-03-22',_binary '\0',1,474),(324,NULL,'2022-03-25',_binary '\0',14,484),(334,NULL,'2022-03-25',_binary '\0',4,494);
/*!40000 ALTER TABLE `borrowing_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKi5tbwrjiam9ubfubf13u9jqbg` FOREIGN KEY (`id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (44),(84),(104),(114),(144),(234),(244),(284),(304),(324),(334),(374),(384),(404),(414),(474),(654),(664),(724),(734),(744),(764),(774),(784),(794),(834),(864);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `book_id` bigint(20) NOT NULL,
  `cart_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKis5hg85qbs5d91etr4mvd4tx6` (`book_id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKis5hg85qbs5d91etr4mvd4tx6` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=524 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (444,1,44),(484,1,84),(494,14,84),(514,4,114);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_46ccwnsi9409t36lurvtyljak` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=344 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'2022-03-18 21:13:37',1,'2022-03-21 09:00:13',384,'Văn học cách mạng '),(4,'2022-03-18 16:44:16',114,'2022-03-21 05:14:48',384,'Truyện cười dân gian'),(34,'2022-03-21 00:22:56',114,'2022-03-22 17:51:43',384,'Toán Học'),(74,'2022-03-21 08:04:55',384,'2022-03-21 08:04:55',384,'Truyện Ngụ Ngôn '),(294,'2022-03-22 17:46:43',384,'2022-03-22 17:46:43',384,'Văn Học'),(304,'2022-03-22 20:57:43',114,'2022-03-22 20:57:43',114,'Truyện ngắn');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `book_id` bigint(20) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkko96rdq8d82wm91vh2jsfak7` (`book_id`),
  KEY `FKde3rfu96lep00br5ov0mdieyt` (`parent_id`),
  KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`),
  CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FKde3rfu96lep00br5ov0mdieyt` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FKkko96rdq8d82wm91vh2jsfak7` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=344 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (54,'sách hay','2022-03-21 10:12:25',1,NULL,44),(64,'sách hay','2022-03-21 10:12:26',1,NULL,44),(74,'sách hay','2022-03-21 10:12:26',1,NULL,44),(84,'sách hay','2022-03-21 10:12:27',1,NULL,44),(94,'sách rất hay','2022-03-21 10:12:34',1,NULL,44),(104,'sách hay','2022-03-21 10:12:48',1,NULL,44),(114,'hay','2022-03-21 23:24:06',1,NULL,834),(124,'hay','2022-03-21 23:24:29',1,NULL,834),(144,'Nga xinh gái','2022-03-22 03:07:04',1,NULL,44),(154,'Sach deu','2022-03-22 08:25:39',1,NULL,44),(164,'Sach deu 2','2022-03-22 15:27:50',1,154,104),(174,'hY','2022-03-22 21:51:41',1,NULL,834),(184,'hihi','2022-03-22 23:52:07',4,NULL,84),(194,'hay lam','2022-03-22 23:52:28',4,NULL,84),(204,'hay qua nha','2022-03-23 00:05:26',4,NULL,84),(214,'hay qua post','2022-03-23 00:06:55',4,NULL,84),(224,'Test','2022-03-23 00:08:37',1,NULL,84),(234,'hay nhi','2022-03-23 00:09:57',4,NULL,84),(244,'hay waa','2022-03-23 00:12:09',4,NULL,84),(254,'string','2022-03-23 00:13:17',4,NULL,84),(264,'string','2022-03-23 00:13:39',4,NULL,84),(274,'Test','2022-03-23 00:14:10',1,NULL,84),(284,'Test','2022-03-23 00:15:14',1,NULL,84),(294,'Test','2022-03-23 00:15:21',1,NULL,84),(304,'Test','2022-03-23 00:15:40',1,NULL,84),(314,'hay quá luôn','2022-03-23 00:15:45',14,NULL,84),(324,'tuyet voi luon','2022-03-23 00:16:29',14,NULL,84),(334,'hihi','2022-03-23 11:14:24',1,NULL,84);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h9trv4xhmh6s68vbw9ba6to70` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'2022-03-18 21:13:51',1,'2022-03-19 04:36:35',114,'Kim Đồng'),(4,'2022-03-18 16:42:30',114,'2022-03-18 16:43:38',114,'HCM'),(54,'2022-03-21 09:57:19',384,'2022-03-21 09:57:19',384,'aaaaa'),(64,'2022-03-21 10:12:21',114,'2022-03-23 10:32:14',384,'Nguyễn Trang'),(74,'2022-03-21 10:23:49',384,'2022-03-21 10:23:49',384,'Ngọc Nguyễn'),(84,'2022-03-22 20:57:27',114,'2022-03-22 20:57:27',114,'Tuổi trẻ');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `user_id` bigint(20) NOT NULL,
  `expiry_date` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_r4k4edos30bx9neoq81mdvwph` (`token`),
  CONSTRAINT `FKfgk1klcib7i15utalmcqo7krt` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token`
--

LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token` VALUES (1,'2022-03-24 13:25:00','2aa72b4c-acdb-4247-b088-836fbe4aa767'),(44,'2022-03-23 03:06:24','a6c033f5-b469-4ecb-82ab-f34170949fb0'),(84,'2022-03-24 11:12:28','57eefb13-9f37-4b24-a045-97d65f389f87'),(104,'2022-03-19 15:17:21','52933234-a351-4e98-b027-2c9f015cded9'),(114,'2022-03-24 00:26:58','baf22a8c-1350-4ad7-afe6-16aa26776e9e'),(144,'2022-03-24 00:26:41','7b9fd0cf-5a43-48aa-9710-74568ed1f0df'),(234,'2022-03-23 22:54:36','49741d63-e8a1-4228-a99e-298d38e68280'),(244,'2022-03-22 10:24:49','ae05cbd4-8803-41b3-ad89-eaa67feae16d'),(384,'2022-03-24 10:29:31','566fe2eb-cbbd-436a-922d-a5dd8b5c4f32'),(834,'2022-03-22 16:19:33','908a545f-e049-4347-a933-a7da593160ed');
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_SUPERADMIN'),(3,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `addr` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `is_none_locked` bit(1) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_589idila9li6a4arw1t8ht1gx` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=914 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,NULL,NULL,'2022-03-20 15:10:50',1,NULL,NULL,'admin',NULL,NULL,_binary '',NULL,'$2a$10$P4NYItQoEZG7N0TFyimUFusBLecmK.fffafwuX867xdR6Y.8j0D.2',NULL,NULL),(44,'2022-03-18 15:03:19',NULL,'2022-03-18 15:04:40',NULL,'Hà Nội','2000-10-02','phamnga.bibi89@gmail.com','Nữ',NULL,_binary '','Phạm Nga','$2a$10$U41cj.dMkUocz9/A03V5He1WWZjKfp5tv4xveS.a1ffs72Q/NhDXy','0334972038',NULL),(84,'2022-03-18 15:10:16',NULL,'2022-03-18 15:14:25',NULL,'Ninh Binh','2000-04-24','phamdiep2442000@gmail.com','Nữ',NULL,_binary '','Diep','$2a$10$c5rSPg/XcgdP7aRYKWog.uDenfBJFzHWKqP4GIjkHaMQ0E7yEBNE6','0868265392',NULL),(104,'2022-03-18 15:13:24',NULL,'2022-03-18 15:13:37',NULL,'Thái Bình','2000-09-20','hoangmaimai2009@gmail.com','Nữ',NULL,_binary '','Hoàng Thị Mai','$2a$10$tYz5VFfTwR1W2CocEWWpuOkndBcnui8MOQiX2Z9d.atrfAObV0MMq','0977019702',NULL),(114,'2022-03-18 15:22:26',1,'2022-03-22 13:15:43',1,'string','2022-03-22','admin@admin.com','Nam','string',_binary '','string','$2a$10$nfpFZeselOyIxMnisEs4Eer0eBzuYn9pHZbK1hre4fDJ1zwfqdDPu','0123456381',NULL),(144,'2022-03-19 05:34:38',NULL,'2022-03-19 05:34:54',NULL,'hn','2022-03-25','1851061669@e.tlu.edu.vn','Nữ',NULL,_binary '','thuy','$2a$10$gG5vaU0S0OxMw/qVytnnROYnwCKkhkkPyTYSNfVg1TMNjHELka/KC','0978554123',NULL),(234,'2022-03-19 23:47:18',NULL,'2022-03-22 23:18:50',234,'Hà Nội','2000-01-27','ngthduongg271@gmail.com','Nữ',NULL,_binary '','Thuỳ Dương','$2a$10$3Kx3/hO5VtQ6NSJrvVL9Ou/nE9WDlqTfFndPWGNYihI4xQbT1SXmS','0866897100',NULL),(244,'2022-03-20 01:16:00',NULL,'2022-03-20 01:16:20',NULL,'dâdadadadada','2000-12-07','cao2000nt@gmail.com','Nam',NULL,_binary '','Phạm Văn Cao','$2a$10$zpY8UnYwpgNajM5E5lNP0.B92wvPzclI7baY575tBnQVd5c/r0Way','0942790283',NULL),(284,'2022-03-20 12:49:52',NULL,'2022-03-20 12:49:52',NULL,'hn','2022-03-23','1851061629@e.tlu.edu.vn','Nam',NULL,_binary '\0','thuy','$2a$10$P45wJqGChwJwqhlJ47JOK.52wpuESZHB20vxaaImx0s/oWDnfd/cu','0988765441','jStgkoqyY2Q3Ee216X9p7Sqlvq7G4b'),(304,'2022-03-20 13:02:00',NULL,'2022-03-20 13:02:00',NULL,'hn','2022-03-24','1851061569@e.tlu.edu.vn','Nam',NULL,_binary '\0','thuy','$2a$10$rDvsHvjOBApzaAE22UKhHO8XVS/Z3e1VW33MpZZ9EqfDFP70h3YYa','0916762131','5yISFCbXJT4F2RrDpyBGv6td1teQv5'),(324,'2022-03-20 14:13:10',NULL,'2022-03-20 14:13:10',NULL,'Hà Lội','2000-04-04','conma003@gmail.com','Nam',NULL,_binary '\0','Hẹo hẹo','$2a$10$DR9tT/Wqg0uF3Z3hcjM5geQ6y7RKJ6vt33YADKCCVwNTLeUMAv7MC','0977479565','fmbMOFbNCtZPqJvbKTk1na3Ue7hkVg'),(334,'2022-03-20 16:02:19',NULL,'2022-03-20 16:02:19',NULL,'Thái Bình','2022-03-04','1851061322@e.tlu.edu.vn','Nam',NULL,_binary '\0','TestCase','$2a$10$xwvO9/IdDdQgG3f3hyKpUu6CNhXtJG83R.ktOrf.BHyoS8hhp8IAW','0346405057','J2VOvPnMNnftt7pe0iQTLg35Sbl217'),(374,'2022-03-20 16:14:09',NULL,'2022-03-20 16:14:09',NULL,'Thái Bình','2022-03-01','loinguyen12344322@gmail.com','Nam',NULL,_binary '\0','Testcase','$2a$10$yznjiYiD3xEdC./mIDYnl.uAq2x4cxj3xWZSPaHxeVFPemXcgGUz.','0346405051','sMKo8njuVI696Y9hRydRFjO6qCHNvL'),(384,'2022-03-20 20:05:14',114,'2022-03-20 23:27:32',384,'Nghệ An','2022-03-20','hiep@gmail.com','Nam','string',_binary '','Phong Thần','$2a$10$PAnsV/wkxddCdEqlv7nxRel6Od8jLibBeie6Txi3gQ.1x73PNtN76','0375713288',NULL),(404,'2022-03-20 20:11:56',NULL,'2022-03-20 20:11:56',NULL,'hn','2022-03-31','1851061699@e.tlu.edu.vn','Nam',NULL,_binary '\0','thuy','$2a$10$IuAoCDq6NTtvDoKXWshc2OSidrskZF659NmX7.uGAGISNWOis.5Gq','0999868912','iSlRJEaNqS8PTE1ivirMM4ok57iith'),(414,'2022-03-20 20:12:19',NULL,'2022-03-20 20:12:19',NULL,'hn','2022-03-23','1851061769@e.tlu.edu.vn','Nam',NULL,_binary '\0','5676','$2a$10$NGeco31qlca63JH96VHnFuNlUZ5vdJgWkHqrE0KO0UP0icMxAj4MK','0911234311','4RXT13nVxGMxQnzpCBF8Ew8qoOwKMn'),(474,'2022-03-20 23:10:30',NULL,'2022-03-20 23:10:30',NULL,'faufd','2000-03-23','1851061640@e.tlu.edu.vn','Nữ',NULL,_binary '\0','linh','$2a$10$AcPfREjeH2nS9ZzPjQal1.kMPwomAyS4aQpG1gd3e.trlEOdl9fhG','0962108812','Air9ZIQrKrdepwQ43XJ2SyWOoRYs40'),(654,'2022-03-21 09:27:30',NULL,'2022-03-21 09:27:30',NULL,'Hà Nội','2000-08-13','duonglongvujvcr@gmail.com','Nam',NULL,_binary '\0','Dương Long Vũ','$2a$10$XdeaXj0wu3k8IwczoHXgheCSa8yQfY43tEmju/d9fpxPzLGZN/uMq','0368915189','GNmEVspNI5yWN5urP9wlz7EKgJfbYR'),(664,'2022-03-21 09:29:25',NULL,'2022-03-21 09:29:25',NULL,'hà nội','2022-03-18','dangxuanchien000@gmail.com','Nam',NULL,_binary '\0','Đặng Xuân Chiến','$2a$10$BYPBA58QrVkl6D6SSgPtv.tLgVykLdMpO/BP.A4Gt..lB1Ms7sh7K','0123456789','dga6sUhhJTbrhkRqi7HdkdbGzkPJac'),(724,'2022-03-21 10:54:38',NULL,'2022-03-21 10:54:38',NULL,'hn','2022-03-10','1851061655@e.tlu.edu.vn','Nữ',NULL,_binary '\0','thuy','$2a$10$svffcOY7EfUx/usWRgHcZOSxVaCjkeirmKiI9bpfdv9wnqhiaQoTK','0917349341','ItZW61Tm4HgALSt8RXZDdjOka1NGTP'),(734,'2022-03-21 11:00:18',NULL,'2022-03-21 11:00:18',NULL,'hn','2022-03-16','1851061669@e.tlu','Nam',NULL,_binary '\0',' thuy-- ','$2a$10$cXgf8ETDor5LodacwCbVAON0734Q/pAMIN5HWF/i9pLcL7aoelnpW','0917998654','Y6jTSHsfk3dMkQ5ept1uZZFDiXTy1w'),(744,'2022-03-21 11:03:38',NULL,'2022-03-21 11:03:38',NULL,'hn','2022-03-18','thjffggbuuuuuuubv@gmail.com','Nam',NULL,_binary '\0','thuy','$2a$10$wBJ7slU8d8WLu.rNCH28auUkHHz44kUh10wt3eBg3lwJW9NrL2cJi','0999677321','BNTv4uCrNK8gcK3aWuEWL7BdDnpv2V'),(764,'2022-03-21 11:08:34',NULL,'2022-03-21 11:08:34',NULL,'hnnh','2020-02-29','1851061069@e.tlu.edu.vn','Nam',NULL,_binary '\0','thuy','$2a$10$GAamkAdDyPycjtCVQLanpeyv2.ILLWwNwlT4.SKH6.k12jl4KXp8y','0976564532','4j3WzFvbCQ4szHGsv3VBH3Zp2P9yE9'),(774,'2022-03-21 11:09:40',NULL,'2022-03-21 11:09:40',NULL,'hn','2022-03-16','1851061009@e.tlu.edu.vn','Nam',NULL,_binary '\0','thuy','$2a$10$hQXiKj6iIjFMtYATH.eCsO6EvegPx.McMaGmF.5cagHPlDtx0H5ma','0977654321','D8FguSoUEOq5gnBSBtug8Y5kIru6oG'),(784,'2022-03-21 11:11:20',NULL,'2022-03-21 11:11:20',NULL,'hn','2022-03-09','1851060019@e.tlu.edu.vn','Nam',NULL,_binary '\0','thuy','$2a$10$eb1H/qJa1JfOkcpWeewh5.c22u2O.WtDSregvkY9jKxERbnb4GTrW','0988678009','uNEgj2lBqNHmiyk10OaHgmjpsRn3yc'),(794,'2022-03-21 21:50:03',NULL,'2022-03-21 21:50:03',NULL,'hn','2022-03-09','1851060069@e.tlu.edu.vn','Nam',NULL,_binary '\0',' thuyt ','$2a$10$NHHMi7rELVvxzk3tgoyfT.dasgWS.Q6zYb03QOKDytH63a/pBGRw6','0977876101','nU1KnJNRHz05GXr1DDqk1Je5sooCC1'),(834,'2022-03-21 22:48:42',NULL,'2022-03-21 23:16:41',NULL,'ha nam','2022-03-07','sondongnguyen2k@gmail.com','Nam',NULL,_binary '','son dong','$2a$10$2tJzNDnLteKZdDgaLk1k9eNmX80iINbou37TcvCO7Wk4nomrq1E7.','0375716523',NULL),(864,'2022-03-21 22:59:07',NULL,'2022-03-21 22:59:07',NULL,'thai binh','2022-03-18','huyhiep4520@gmail.com','Nam',NULL,_binary '\0','huy hiep','$2a$10$ez6RelStBybRoxRfXLKXZuk.I8riSLq//j5e0mKlt9Pn8dh92yRq.','0375713278','2HjlW0VWT2nIN4PEAJG5Lbk987fw4L');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (384,1),(1,2),(114,2),(44,3),(84,3),(104,3),(144,3),(234,3),(244,3),(284,3),(304,3),(324,3),(334,3),(374,3),(404,3),(414,3),(474,3),(654,3),(664,3),(724,3),(734,3),(744,3),(764,3),(774,3),(784,3),(794,3),(834,3),(864,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-23 13:55:31
