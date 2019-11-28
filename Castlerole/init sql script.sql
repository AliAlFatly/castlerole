nodesDROP DATABASE IF EXISTS `castleroledb`;
CREATE DATABASE `castleroledb`;
USE `castleroledb`;


CREATE TABLE `user` (
		`id` int not null AUTO_INCREMENT PRIMARY KEY,
        `username` varchar(50) not null,
        `password` varchar(128) not null,
        `join_date` date,
        `wood` int,
        `iron` int,
        `stone` int,
        `food` int,
        `troops` int
        );

CREATE TABLE `Nodes` (
		`id` int not null AUTO_INCREMENT PRIMARY KEY,
        `type` varchar(50) not null,
        `ownerId` int,
        `troops` date,
        `yield_min` int,
        `yield_max` int,
        FOREIGN KEY (`ownerId`) REFERENCES user(`id`)
        );