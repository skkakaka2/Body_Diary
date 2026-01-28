CREATE DATABASE IF NOT EXISTS `body_diary`;

USE `body_diary`;

CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(100) NOT NULL,
    `created_at` DATETIME,
    `updated_at` DATETIME
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;