CREATE DATABASE IF NOT EXISTS `userdb`;
USE `userdb`;


CREATE TABLE IF NOT EXISTS`user` (
  `Firstname` VARCHAR(30),
  `Lastname` VARCHAR(30),
  `email` VARCHAR(75),
  `username` VARCHAR(30),
  `password` VARCHAR(30)
);

INSERT INTO user (Firstname, Lastname, email, username, password) VALUES ('amena', 'afreen', 'aamenaaf@gmail.com', 'amenaafreen', 'afreen');

