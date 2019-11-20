DROP DATABASE IF EXISTS `castleroledb`;
CREATE DATABASE `castleroledb`;
USE `castleroledb`;


CREATE TABLE `user` (
		`id` int not null AUTO_INCREMENT PRIMARY KEY,
        `username` varchar(50) not null,
        `password` varchar(128) not null
        );
