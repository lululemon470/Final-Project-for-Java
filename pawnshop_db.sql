/*
 Navicat Premium Data Transfer

 Source Server         : MySQLST6
 Source Server Type    : MySQL
 Source Server Version : 100427 (10.4.27-MariaDB)
 Source Host           : localhost:3308
 Source Schema         : pawnshop_db

 Target Server Type    : MySQL
 Target Server Version : 100427 (10.4.27-MariaDB)
 File Encoding         : 65001

 Date: 08/11/2024 08:08:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Interest
-- ----------------------------
DROP TABLE IF EXISTS `Interest`;
CREATE TABLE `Interest` (
  `id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for Item
-- ----------------------------
DROP TABLE IF EXISTS `Item`;
CREATE TABLE `Item` (
  `item_id` varchar(255) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `type` enum('car','motorcycle','others') DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for Loan
-- ----------------------------
DROP TABLE IF EXISTS `Loan`;
CREATE TABLE `Loan` (
  `loan_id` int(11) NOT NULL AUTO_INCREMENT,
  `pawn_id` varchar(255) DEFAULT NULL,
  `principal` decimal(10,2) NOT NULL,
  `interest_month` decimal(10,2) NOT NULL,
  `duration` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`loan_id`),
  KEY `loan_ibfk_1` (`pawn_id`),
  CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`pawn_id`) REFERENCES `Pawn` (`pawn_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for Pawn
-- ----------------------------
DROP TABLE IF EXISTS `Pawn`;
CREATE TABLE `Pawn` (
  `pawn_id` varchar(255) NOT NULL,
  `item_id` varchar(255) NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `loan_amount` decimal(10,2) NOT NULL,
  `interest_rate` decimal(4,2) NOT NULL,
  `duration` int(10) unsigned NOT NULL,
  `loan_start` date DEFAULT NULL,
  `loan_due` date DEFAULT NULL,
  `debt_amount` double NOT NULL,
  `loan_status` enum('active','closed') NOT NULL DEFAULT 'active',
  `paid_count` int(11) DEFAULT 0,
  PRIMARY KEY (`pawn_id`) USING BTREE,
  KEY `item_id` (`item_id`),
  CONSTRAINT `pawn_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for Redeemed
-- ----------------------------
DROP TABLE IF EXISTS `Redeemed`;
CREATE TABLE `Redeemed` (
  `id` int(11) NOT NULL,
  `pawn_id` int(11) NOT NULL,
  `amount` double DEFAULT NULL,
  `reedeemed_date` date DEFAULT curdate(),
  PRIMARY KEY (`id`,`pawn_id`) USING BTREE,
  KEY `pawn_id` (`pawn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for Staff
-- ----------------------------
DROP TABLE IF EXISTS `Staff`;
CREATE TABLE `Staff` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Admin','Clerk') NOT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`staff_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Procedure structure for UpdateLoanStatus
-- ----------------------------
DROP PROCEDURE IF EXISTS `UpdateLoanStatus`;
delimiter ;;
CREATE PROCEDURE `UpdateLoanStatus`()
BEGIN
    UPDATE Pawn
    SET loan_status = 'closed', debt_amount = 0
    WHERE debt_amount <= 0;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
