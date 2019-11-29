DROP DATABASE IF EXISTS `castleroledb`;
CREATE DATABASE `castleroledb`;
USE `castleroledb`;


CREATE TABLE `user` (
		`id` int not null AUTO_INCREMENT PRIMARY KEY,
        `username` varchar(50) not null,
        `password` varchar(128) not null,
        `joinDate` date,
        `xCoordinate` int,
        `yCoordinate` int,
        `wood` int,
        `iron` int,
        `stone` int,
        `food` int,
        `troops` int
        );

CREATE TABLE `node` (
		`id` int not null AUTO_INCREMENT PRIMARY KEY,
        `type` varchar(50) not null,
        `pictureName` varchar(50),
        `ownerId` int,
        `troops` date,
        `yieldMin` int,
        `yieldMax` int,
        `yieldType` varchar(50),
		`xCoordinate` int,
        `yCoordinate` int,
        FOREIGN KEY (`ownerId`) REFERENCES user(`id`)
        );