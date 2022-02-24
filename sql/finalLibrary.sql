-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8 ;
USE `library` ;

-- -----------------------------------------------------
-- Table `library`.`book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`book` ;

CREATE TABLE IF NOT EXISTS `library`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  `edition` VARCHAR(45) NOT NULL,
  `year_edition` VARCHAR(4) NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`id`));

insert into book value (default,'book1','author1','edition1',2010,2);
insert into book value (default,'book2','author2','edition2',2011,2);
insert into book value (default,'book3','author3','edition3',2012,2);
insert into book value (default,'book4','author1','edition4',2018,1);
insert into book value (default,'book5','author2','edition4',2019,1);
insert into book value (default,'book6','author3','edition14',2020,3);

-- -----------------------------------------------------
-- Table `library`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`role` ;

CREATE TABLE IF NOT EXISTS `library`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

  insert into role(id,name) values
  (default,'admin'),
  (default,'worker'),
  (default,'customer');

-- -----------------------------------------------------
-- Table `library`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`user` ;

CREATE TABLE IF NOT EXISTS `library`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(90) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `blocked` TINYINT NOT NULL DEFAULT 0,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role_idx` (`role_id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `library`.`role` (`id`)
    ON DELETE cascade
    ON UPDATE cascade);
    
insert into user value (default,'papijk@gmail.com','password','Danilo','Papizhuk',default,1);
insert into user value (default,'karasax@gmail.com','password1','Alexander','Karas',default,2);
insert into user value (default,'darkmisha2012@gmail.com','darkmisha2012','Mykhailo','Gienko',default,3);

-- -----------------------------------------------------
-- Table `library`.`receipt_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`receipt_status` ;

CREATE TABLE IF NOT EXISTS `library`.`receipt_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));
  
insert into receipt_status(id,name) value (default,'expected');
insert into receipt_status(id,name) value (default,'subscription');
insert into receipt_status(id,name) value (default,'completed');
insert into receipt_status(id,name) value (default,'denied');


-- -----------------------------------------------------
-- Table `library`.`receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`receipt` ;

CREATE TABLE IF NOT EXISTS `library`.`receipt` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `receipt_status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_receipt_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_receipt_book1_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_receipt_receipt_status1_idx` (`receipt_status_id` ASC) VISIBLE,
  CONSTRAINT `fk_receipt_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `library`.`user` (`id`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_receipt_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`book` (`id`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_receipt_receipt_status1`
    FOREIGN KEY (`receipt_status_id`)
    REFERENCES `library`.`receipt_status` (`id`)
    ON DELETE cascade
    ON UPDATE cascade);


-- -----------------------------------------------------
-- Table `library`.`subscription`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`subscription` ;

CREATE TABLE IF NOT EXISTS `library`.`subscription` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start` DATETIME NOT NULL,
  `end` DATETIME NULL,
  `debt` INT NULL,
  `user_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_subscription_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_subscription_book1_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `fk_subscription_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `library`.`user` (`id`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_subscription_book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`book` (`id`)
    ON DELETE cascade
    ON UPDATE cascade);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
