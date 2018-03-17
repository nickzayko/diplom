DROP DATABASE IF EXISTS diploma_project;

CREATE DATABASE IF NOT EXISTS diploma_project;

USE diploma_project;

CREATE TABLE `diploma_project`.`users` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(65) NOT NULL,
  `user_surname` VARCHAR(65) NOT NULL,
  `user_email` VARCHAR(65) NOT NULL,
  `user_login` VARCHAR(65) NOT NULL,
  `user_password` VARCHAR(65) NOT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `diploma_project`.`topics` (
  `id_topics` INT NOT NULL AUTO_INCREMENT,
  `topic_name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id_topics`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `diploma_project`.`messages` (
  `id_messages` INT NOT NULL AUTO_INCREMENT,
  `text_of_message` VARCHAR(250) NOT NULL,
  `user_id` INT NOT NULL,
  `topic_id` INT NOT NULL,
  `localeDateTime` DATETIME NOT NULL,
  PRIMARY KEY (`id_messages`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


