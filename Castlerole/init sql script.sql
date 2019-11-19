DROP DATABASE IF EXISTS `castleroledb`;
CREATE DATABASE `castleroledb`;
USE `castleroledb`;


CREATE TABLE `user` (
		`id` int not null,
        `username` varchar(50) not null,
        `password` varchar(50) not null,
        PRIMARY KEY (`id`)
        )