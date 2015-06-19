CREATE SCHEMA `enrollment` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci ;

CREATE TABLE `enrollment` (
  `idenrollment` int(11) NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`idenrollment`),
  UNIQUE KEY `NAME` (`first_name`,`last_name`),
  UNIQUE KEY `idenrollment_UNIQUE` (`idenrollment`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci COMMENT='student enrollment form';
