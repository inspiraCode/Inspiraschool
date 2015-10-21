CREATE DATABASE  IF NOT EXISTS `enrollment` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;
USE `enrollment`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: enrollment
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cat_company`
--

DROP TABLE IF EXISTS `cat_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_company` (
  `id_company` int(11) NOT NULL,
  `company_name` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_company`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cat_group`
--

DROP TABLE IF EXISTS `cat_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_group` (
  `id_group` int(11) NOT NULL AUTO_INCREMENT,
  `grade` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `period` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `mode` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `day_trip` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `year_of_course` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_group`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cat_metter`
--

DROP TABLE IF EXISTS `cat_metter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_metter` (
  `id_metter` int(11) NOT NULL AUTO_INCREMENT,
  `metter_name` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_metter`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cat_payment_concept`
--

DROP TABLE IF EXISTS `cat_payment_concept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_payment_concept` (
  `id_payment_concept` int(11) NOT NULL AUTO_INCREMENT,
  `concept` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `price` decimal(2,0) DEFAULT NULL,
  PRIMARY KEY (`id_payment_concept`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cat_teacher`
--

DROP TABLE IF EXISTS `cat_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_teacher` (
  `id_teacher` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `lastname` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `addres` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `phone` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_teacher`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `charge`
--

DROP TABLE IF EXISTS `charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `charge` (
  `id_charge` int(11) NOT NULL AUTO_INCREMENT,
  `id_student` int(11) NOT NULL,
  `id_concept` int(11) NOT NULL,
  `price` decimal(2,0) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` decimal(2,0) DEFAULT NULL,
  `charge_date` datetime DEFAULT NULL,
  `id_employee` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_charge`),
  KEY `fk_charges_students1_idx` (`id_student`),
  KEY `fk_charges_cat_payment_concept1_idx` (`id_concept`),
  CONSTRAINT `fk_charges_cat_payment_concept1` FOREIGN KEY (`id_concept`) REFERENCES `cat_payment_concept` (`id_payment_concept`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_charges_students1` FOREIGN KEY (`id_student`) REFERENCES `student` (`Id_student`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enrollment` (
  `id_enrollment` int(11) NOT NULL AUTO_INCREMENT,
  `enrollment_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shift` varchar(1) COLLATE latin1_spanish_ci NOT NULL,
  `course` varchar(2) COLLATE latin1_spanish_ci NOT NULL,
  `course_plan` varchar(4) COLLATE latin1_spanish_ci NOT NULL,
  `first_name` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `last_name` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `gender` varchar(1) COLLATE latin1_spanish_ci NOT NULL,
  `birth_place` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `address_1` varchar(80) COLLATE latin1_spanish_ci DEFAULT NULL,
  `address_2` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `city` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `state` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `zip` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `phone_home` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `phone_mobile` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `person_unique_id` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `from_school` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(80) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_enrollment`),
  UNIQUE KEY `NAME` (`first_name`,`last_name`),
  UNIQUE KEY `idenrollment_UNIQUE` (`id_enrollment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='student enrollment form';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `metter_course`
--

DROP TABLE IF EXISTS `metter_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `metter_course` (
  `id_metter_course` int(11) NOT NULL AUTO_INCREMENT,
  `id_metter` int(11) NOT NULL,
  `id_group` int(11) NOT NULL,
  `id_teacher` int(11) NOT NULL,
  PRIMARY KEY (`id_metter_course`,`id_group`,`id_metter`,`id_teacher`),
  KEY `fk_metter_curse_cat_teachers1_idx` (`id_teacher`),
  KEY `fk_metter_course_groups1_idx` (`id_group`),
  CONSTRAINT `fk_metter_course_groups1` FOREIGN KEY (`id_group`) REFERENCES `cat_group` (`id_group`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_metter_curse_cat_teachers1` FOREIGN KEY (`id_teacher`) REFERENCES `cat_teacher` (`id_teacher`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `note` (
  `id_student` int(11) NOT NULL,
  `id_group` int(11) NOT NULL,
  `id_metter_course` int(11) NOT NULL,
  `p1` int(11) DEFAULT NULL,
  `p2` int(11) DEFAULT NULL,
  `average` int(11) DEFAULT NULL,
  `special1` int(11) DEFAULT NULL,
  `special2` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_student`,`id_group`,`id_metter_course`),
  KEY `fk_notes_groups1_idx` (`id_group`),
  KEY `fk_note_metter_course1_idx` (`id_metter_course`),
  CONSTRAINT `fk_notes_groups1` FOREIGN KEY (`id_group`) REFERENCES `cat_group` (`id_group`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_notes_students1` FOREIGN KEY (`id_student`) REFERENCES `student` (`Id_student`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_note_metter_course1` FOREIGN KEY (`id_metter_course`) REFERENCES `metter_course` (`id_metter_course`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `Id_student` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `lastname` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `birthdate` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `addres` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `gender` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_group` int(11) NOT NULL,
  `enroll_number` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enrollment_date` date DEFAULT NULL,
  `grade_SIE` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_company` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `clock_id` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`Id_student`),
  KEY `fk_students_groups1_idx` (`id_group`),
  KEY `fk_student_cat_company1_idx` (`id_company`),
  CONSTRAINT `fk_students_groups1` FOREIGN KEY (`id_group`) REFERENCES `cat_group` (`id_group`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_student_cat_company1` FOREIGN KEY (`id_company`) REFERENCES `cat_company` (`id_company`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=989 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `idsys_user` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `user_password` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `is_enabled` tinyint(1) DEFAULT NULL,
  `fail_login_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`idsys_user`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-16 16:04:05
