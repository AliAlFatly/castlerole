DROP DATABASE IF EXISTS `sql_castlerole_db`;
CREATE DATABASE `sql_castlerole_db`;
USE `sql_castlerole_db`;


CREATE TABLE `user` (
		`id` int not null,
        `username` varchar(50) not null,
        `password` varchar(50) not null,
        PRIMARY KEY (`id`)
        )