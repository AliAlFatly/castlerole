DROP DATABASE IF EXISTS `castleroledb`;
CREATE DATABASE `castleroledb`;
USE `castleroledb`;


CREATE TABLE `user` (
		`id` int not null AUTO_INCREMENT PRIMARY KEY,
        `username` varchar(50) not null,
        `password` varchar(128) not null,
        `joinDate` date,
        `coordinateX` int,
        `coordinateY` int,
        `pictureReference` varchar(50),
        `wood` int,
        `iron` int,
        `stone` int,
        `food` int,
        `troops` int
        );

CREATE TABLE `node` (
		`id` int not null AUTO_INCREMENT PRIMARY KEY,
        `type` varchar(50) not null,
        `pictureReference` varchar(50),
        `troops` int,
        `yieldMin` int,
        `yieldMax` int,
        `yieldType` varchar(50),
		`coordinateX` int,
        `coordinateY` int
        );