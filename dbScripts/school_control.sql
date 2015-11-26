CREATE DATABASE  IF NOT EXISTS `school_control` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;
USE `school_control`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: school_control
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
-- Table structure for table `cat_assignment`
--

DROP TABLE IF EXISTS `cat_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_assignment` (
  `id_assignment` int(11) NOT NULL AUTO_INCREMENT,
  `assignment_name` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_assignment`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cat_career`
--

DROP TABLE IF EXISTS `cat_career`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_career` (
  `id_career` int(11) NOT NULL AUTO_INCREMENT,
  `name_career` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `code` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_career`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `grade` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `mode` varchar(25) COLLATE latin1_spanish_ci DEFAULT NULL,
  `day_trip` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `id_career` int(11) DEFAULT NULL,
  `id_period` int(11) NOT NULL,
  PRIMARY KEY (`id_group`),
  KEY `fk_id_career_idx` (`id_career`),
  KEY `fk_id_period_idx` (`id_period`),
  CONSTRAINT `fk_id_period` FOREIGN KEY (`id_period`) REFERENCES `cat_period` (`id_period`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_career` FOREIGN KEY (`id_career`) REFERENCES `cat_career` (`id_career`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
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
-- Table structure for table `cat_period`
--

DROP TABLE IF EXISTS `cat_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_period` (
  `id_period` int(11) NOT NULL AUTO_INCREMENT,
  `period_name` varchar(45) COLLATE latin1_spanish_ci NOT NULL,
  `period_year` int(11) NOT NULL,
  PRIMARY KEY (`id_period`),
  UNIQUE KEY `ux_period_name_year` (`period_name`,`period_year`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cat_sie_group`
--

DROP TABLE IF EXISTS `cat_sie_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_sie_group` (
  `cat_sie_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_sie_group_name` varchar(10) COLLATE latin1_spanish_ci NOT NULL,
  `id_period` int(11) NOT NULL,
  PRIMARY KEY (`cat_sie_group_id`),
  UNIQUE KEY `ux_period_group_sie` (`id_period`,`cat_sie_group_name`),
  KEY `fk_cat_period_sie_group_idx` (`id_period`),
  CONSTRAINT `fk_cat_period_sie_group` FOREIGN KEY (`id_period`) REFERENCES `cat_period` (`id_period`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cat_status`
--

DROP TABLE IF EXISTS `cat_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_status` (
  `id_status` int(11) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cat_student`
--

DROP TABLE IF EXISTS `cat_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_student` (
  `Id_student` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(80) COLLATE latin1_spanish_ci DEFAULT NULL,
  `lastname` varchar(80) COLLATE latin1_spanish_ci DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `address` varchar(150) COLLATE latin1_spanish_ci DEFAULT NULL,
  `gender` varchar(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enroll_number` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `enrollment_date` date DEFAULT NULL,
  `id_company` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `clock_id` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`Id_student`),
  UNIQUE KEY `enroll_number_UNIQUE` (`enroll_number`),
  KEY `fk_student_cat_company1_idx` (`id_company`),
  KEY `fk_student_status_idx` (`status`),
  CONSTRAINT `fk_student_cat_company1` FOREIGN KEY (`id_company`) REFERENCES `cat_company` (`id_company`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_student_status` FOREIGN KEY (`status`) REFERENCES `cat_status` (`id_status`)
) ENGINE=InnoDB AUTO_INCREMENT=1615 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
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
  `address` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `phone` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  `user_name` varchar(45) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_teacher`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cross_group_assignment`
--

DROP TABLE IF EXISTS `cross_group_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cross_group_assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_assignment` int(11) NOT NULL,
  `id_group` int(11) NOT NULL,
  `id_teacher` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_group_assignment` (`id_assignment`,`id_group`),
  KEY `fk_group_assignment_cat_teachers1_idx` (`id_teacher`),
  KEY `fk_group_assignment_groups1_idx` (`id_group`),
  KEY `fk_assignment_idx` (`id_assignment`),
  CONSTRAINT `fk_assignment` FOREIGN KEY (`id_assignment`) REFERENCES `cat_assignment` (`id_assignment`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_group` FOREIGN KEY (`id_group`) REFERENCES `cat_group` (`id_group`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_teacher` FOREIGN KEY (`id_teacher`) REFERENCES `cat_teacher` (`id_teacher`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=383 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cross_sie_student`
--

DROP TABLE IF EXISTS `cross_sie_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cross_sie_student` (
  `id_student` int(11) NOT NULL,
  `cat_sie_group_id` int(11) NOT NULL,
  PRIMARY KEY (`id_student`,`cat_sie_group_id`),
  KEY `fk_cross_sie_student_cat_student` (`id_student`),
  KEY `fk_cross_sie_student_cat_sie_group` (`cat_sie_group_id`),
  CONSTRAINT `fk_cross_sie_student_cat_student` FOREIGN KEY (`id_student`) REFERENCES `cat_student` (`Id_student`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cross_sie_student_cat_sie_group` FOREIGN KEY (`cat_sie_group_id`) REFERENCES `cat_sie_group` (`cat_sie_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cross_student_group_assignment`
--

DROP TABLE IF EXISTS `cross_student_group_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cross_student_group_assignment` (
  `id_student` int(11) NOT NULL,
  `id_group_assignment` int(11) NOT NULL,
  PRIMARY KEY (`id_student`,`id_group_assignment`),
  KEY `fk_group_assignment_idx` (`id_group_assignment`),
  CONSTRAINT `fk_group_assignment` FOREIGN KEY (`id_group_assignment`) REFERENCES `cross_group_assignment` (`id`),
  CONSTRAINT `fk_student` FOREIGN KEY (`id_student`) REFERENCES `cat_student` (`Id_student`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ctrl_assistance`
--

DROP TABLE IF EXISTS `ctrl_assistance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctrl_assistance` (
  `ctrl_assistance_id` int(11) NOT NULL AUTO_INCREMENT,
  `ctrl_group_assignment_id` int(11) NOT NULL,
  `id_student` int(11) NOT NULL,
  `assistance_when` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ctrl_assistance_id`),
  KEY `fk_croos_group_assignment_ctrl_assistance_idx` (`ctrl_group_assignment_id`),
  KEY `fk_student_assistance_idx` (`id_student`),
  CONSTRAINT `fk_cross_group_assignment_ctrl_assistance` FOREIGN KEY (`ctrl_group_assignment_id`) REFERENCES `cross_group_assignment` (`id`),
  CONSTRAINT `fk_student_assistance` FOREIGN KEY (`id_student`) REFERENCES `cat_student` (`Id_student`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ctrl_charge`
--

DROP TABLE IF EXISTS `ctrl_charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctrl_charge` (
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
  CONSTRAINT `fk_charges_students1` FOREIGN KEY (`id_student`) REFERENCES `cat_student` (`Id_student`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ctrl_score`
--

DROP TABLE IF EXISTS `ctrl_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ctrl_score` (
  `id_score` int(11) NOT NULL AUTO_INCREMENT,
  `id_student` int(11) NOT NULL,
  `id_group_assignment` int(11) NOT NULL,
  `comments` varchar(250) COLLATE latin1_spanish_ci DEFAULT NULL,
  `partial_one` int(11) DEFAULT NULL,
  `partial_two` int(11) DEFAULT NULL,
  `partial_three` int(11) DEFAULT NULL,
  `partial_four` int(11) DEFAULT NULL,
  `partial_five` int(11) DEFAULT NULL,
  `partial_six` int(11) DEFAULT NULL,
  `extraordinary` int(11) DEFAULT NULL,
  `extraordinary_two` int(11) DEFAULT NULL,
  `special` int(11) DEFAULT NULL,
  `final` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_score`),
  UNIQUE KEY `ux_student_group` (`id_student`,`id_group_assignment`),
  KEY `fk_note_metter_course1_idx` (`id_group_assignment`),
  CONSTRAINT `fk_score_assignment` FOREIGN KEY (`id_group_assignment`) REFERENCES `cross_group_assignment` (`id`),
  CONSTRAINT `fk_score_student` FOREIGN KEY (`id_student`) REFERENCES `cat_student` (`Id_student`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6265 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-25 13:50:08
