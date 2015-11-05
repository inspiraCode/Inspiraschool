-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema enrollment
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `enrollment` ;

-- -----------------------------------------------------
-- Schema enrollment
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enrollment` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci ;
USE `enrollment` ;

-- -----------------------------------------------------
-- Table `enrollment`.`enrollment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enrollment`.`enrollment` ;

CREATE TABLE IF NOT EXISTS `enrollment`.`enrollment` (
  `idenrollment` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `enrollment_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `shift` VARCHAR(1) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NOT NULL COMMENT '',
  `course` VARCHAR(2) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NOT NULL COMMENT '',
  `course_plan` VARCHAR(4) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NOT NULL COMMENT '',
  `first_name` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NOT NULL COMMENT '',
  `last_name` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NOT NULL COMMENT '',
  `gender` VARCHAR(1) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NOT NULL COMMENT '',
  `birth_place` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL COMMENT '',
  `birth_date` DATETIME NULL DEFAULT NULL COMMENT '',
  `address_1` VARCHAR(80) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL COMMENT '',
  `address_2` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL COMMENT '',
  `city` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL COMMENT '',
  `state` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL COMMENT '',
  `zip` VARCHAR(10) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL COMMENT '',
  `phone_home` VARCHAR(20) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL COMMENT '',
  `phone_mobile` VARCHAR(20) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL COMMENT '',
  `person_unique_id` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL COMMENT '',
  `from_school` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL COMMENT '',
  `email` VARCHAR(80) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idenrollment`)  COMMENT '',
  UNIQUE INDEX `idenrollment_UNIQUE` (`idenrollment` ASC)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci
COMMENT = 'student enrollment form';


-- -----------------------------------------------------
-- Table `enrollment`.`sys_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enrollment`.`sys_user` ;

CREATE TABLE IF NOT EXISTS `enrollment`.`sys_user` (
  `idsys_user` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `user_name` VARCHAR(45) NOT NULL COMMENT '',
  `user_password` VARCHAR(45) NULL COMMENT '',
  `is_enabled` TINYINT(1) NULL COMMENT '',
  `fail_login_count` INT NULL COMMENT '',
  PRIMARY KEY (`idsys_user`)  COMMENT '',
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC)  COMMENT '')
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
