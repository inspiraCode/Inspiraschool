SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `enrollment` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci ;
USE `enrollment` ;

-- -----------------------------------------------------
-- Table `enrollment`.`enrollment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enrollment`.`enrollment` (
  `id_enrollment` INT(11) NOT NULL AUTO_INCREMENT,
  `enrollment_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shift` VARCHAR(1) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NOT NULL,
  `course` VARCHAR(2) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NOT NULL,
  `course_plan` VARCHAR(4) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NOT NULL,
  `first_name` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NOT NULL,
  `last_name` VARCHAR(50) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NOT NULL,
  `gender` VARCHAR(1) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NOT NULL,
  `birth_place` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL,
  `birth_date` DATETIME NULL DEFAULT NULL,
  `address_1` VARCHAR(80) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL,
  `address_2` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL,
  `city` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL,
  `state` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL,
  `zip` VARCHAR(10) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL,
  `phone_home` VARCHAR(20) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL,
  `phone_mobile` VARCHAR(20) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL,
  `person_unique_id` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL,
  `from_school` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL,
  `email` VARCHAR(80) CHARACTER SET 'latin1' COLLATE 'latin1_spanish_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id_enrollment`),
  UNIQUE INDEX `NAME` (`first_name` ASC, `last_name` ASC),
  UNIQUE INDEX `idenrollment_UNIQUE` (`id_enrollment` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci
COMMENT = 'student enrollment form';


-- -----------------------------------------------------
-- Table `enrollment`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enrollment`.`group` (
  `id_group` INT NOT NULL AUTO_INCREMENT,
  `grade` VARCHAR(45) NULL,
  `period` VARCHAR(45) NULL,
  `mode` VARCHAR(45) NULL,
  `day_trip` VARCHAR(45) NULL,
  `year_of_course` VARCHAR(45) NULL,
  PRIMARY KEY (`id_group`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enrollment`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enrollment`.`student` (
  `Id_student` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `birthdate` VARCHAR(45) NULL,
  `addres` VARCHAR(45) NULL,
  `sex` VARCHAR(45) NULL,
  `id_group` INT NULL,
  `enrollment_id` VARCHAR(45) NULL,
  `enrollment_date` DATE NULL,
  `grade_SIE` VARCHAR(45) NULL,
  `groups_id_group` INT NOT NULL,
  PRIMARY KEY (`Id_student`),
  INDEX `fk_students_groups1_idx` (`groups_id_group` ASC),
  CONSTRAINT `fk_students_groups1`
    FOREIGN KEY (`groups_id_group`)
    REFERENCES `enrollment`.`group` (`id_group`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enrollment`.`cat_teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enrollment`.`cat_teacher` (
  `id_teacher` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `addres` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id_teacher`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enrollment`.`metter_course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enrollment`.`metter_course` (
  `id_metter_course` INT NOT NULL AUTO_INCREMENT,
  `id_metter` INT NULL,
  `id_group` INT NULL,
  `id_teacher` INT NULL,
  `cat_teachers_id_teacher` INT NOT NULL,
  `groups_id_group` INT NOT NULL,
  PRIMARY KEY (`id_metter_course`),
  INDEX `fk_metter_curse_cat_teachers1_idx` (`cat_teachers_id_teacher` ASC),
  INDEX `fk_metter_course_groups1_idx` (`groups_id_group` ASC),
  CONSTRAINT `fk_metter_curse_cat_teachers1`
    FOREIGN KEY (`cat_teachers_id_teacher`)
    REFERENCES `enrollment`.`cat_teacher` (`id_teacher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_metter_course_groups1`
    FOREIGN KEY (`groups_id_group`)
    REFERENCES `enrollment`.`group` (`id_group`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enrollment`.`note`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enrollment`.`note` (
  `id_student` INT NULL,
  `id_group` INT NULL,
  `id_metter` INT NULL,
  `p1` INT NULL,
  `p2` INT NULL,
  `average` INT NULL,
  `special1` INT NULL,
  `special2` INT NULL,
  `groups_id_group` INT NOT NULL,
  `students_Id_student` INT NOT NULL,
  `metter_course_id_metter_course` INT NOT NULL,
  PRIMARY KEY (`id_student`, `id_group`, `id_metter`),
  INDEX `fk_notes_groups1_idx` (`groups_id_group` ASC),
  INDEX `fk_notes_students1_idx` (`students_Id_student` ASC),
  INDEX `fk_note_metter_course1_idx` (`metter_course_id_metter_course` ASC),
  CONSTRAINT `fk_notes_groups1`
    FOREIGN KEY (`groups_id_group`)
    REFERENCES `enrollment`.`group` (`id_group`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notes_students1`
    FOREIGN KEY (`students_Id_student`)
    REFERENCES `enrollment`.`student` (`Id_student`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_note_metter_course1`
    FOREIGN KEY (`metter_course_id_metter_course`)
    REFERENCES `enrollment`.`metter_course` (`id_metter_course`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enrollment`.`cat_payment_concept`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enrollment`.`cat_payment_concept` (
  `id_payment_concept` INT NOT NULL AUTO_INCREMENT,
  `concept` VARCHAR(45) NULL,
  `price` DECIMAL(2) NULL,
  PRIMARY KEY (`id_payment_concept`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enrollment`.`charge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enrollment`.`charge` (
  `id_charge` INT NOT NULL AUTO_INCREMENT,
  `id_student` INT NULL,
  `id_concept` INT NULL,
  `price` DECIMAL(2) NULL,
  `quantity` INT NULL,
  `total` DECIMAL(2) NULL,
  `charge_date` DATETIME NULL,
  `id_employee` INT NULL,
  `students_Id_student` INT NOT NULL,
  `cat_payment_concept_id_payment_concept` INT NOT NULL,
  PRIMARY KEY (`id_charge`),
  INDEX `fk_charges_students1_idx` (`students_Id_student` ASC),
  INDEX `fk_charges_cat_payment_concept1_idx` (`cat_payment_concept_id_payment_concept` ASC),
  CONSTRAINT `fk_charges_students1`
    FOREIGN KEY (`students_Id_student`)
    REFERENCES `enrollment`.`student` (`Id_student`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_charges_cat_payment_concept1`
    FOREIGN KEY (`cat_payment_concept_id_payment_concept`)
    REFERENCES `enrollment`.`cat_payment_concept` (`id_payment_concept`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
