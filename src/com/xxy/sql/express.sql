/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : ems

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 17/09/2021 18:52:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for express
-- ----------------------------
DROP TABLE IF EXISTS `express`;
CREATE TABLE `express`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userphone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `intime` timestamp(0) NULL DEFAULT NULL,
  `outtime` timestamp(0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `sysPhone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `number`(`number`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of express
-- ----------------------------
INSERT INTO `express` VALUES (1, '100', '胡桃', '13838411438', '京东快递', '123456', '2021-09-06 20:44:02', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (3, '102', '胡桃', '13838411438', '京东快递', '122222', '2021-09-05 20:46:04', '2021-09-06 20:46:10', 1, '18888888888');
INSERT INTO `express` VALUES (4, '103', '钟离', '15555555555', '韵达快递', NULL, '2021-09-04 20:47:51', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (5, '104', '钟离', '15555555555', '京东快递', '331245', '2021-09-06 20:48:44', NULL, 0, '16666666666');
INSERT INTO `express` VALUES (8, '105', '枫原万叶', '12323232323', '百世快递', NULL, '2021-09-08 10:53:40', '2021-09-09 10:53:48', 1, '16666666666');
INSERT INTO `express` VALUES (9, '106', '枫原万叶', '12323232323', '京东快递', '123458', '2021-09-08 10:54:50', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (10, '107', '枫原万叶', '12323232323', '韵达快递', '123459', '2021-09-09 10:55:33', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (11, '108', '迪卢克', '12424242424', '京东快递', '133001', '2021-09-09 10:57:12', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (12, '109', '迪卢克', '12424242424', '京东快递', '133002', '2021-09-09 10:58:31', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (13, '110', '迪卢克', '12424242424', '京东快递', '133003', '2021-09-09 10:58:35', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (14, '111', '迪卢克', '12424242424', '京东快递', '133004', '2021-09-09 10:58:40', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (15, '112', '迪卢克', '12424242424', '京东快递', '133005', '2021-09-09 10:58:44', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (16, '113', '温迪', '16662626262', '京东快递', '133006', '2021-09-02 10:59:47', '2021-09-04 10:59:52', 1, '18888888888');
INSERT INTO `express` VALUES (17, '114', '温迪', '16662626262', '京东快递', '133007', '2021-09-03 11:01:12', '2021-09-04 11:01:18', 1, '18888888888');
INSERT INTO `express` VALUES (18, '115', '温迪', '16662626262', '京东快递', '133008', '2021-09-06 11:01:23', '2021-09-07 11:01:28', 1, '18888888888');
INSERT INTO `express` VALUES (19, '116', '温迪', '16662626262', '京东快递', '133009', '2021-09-09 11:01:34', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (20, '117', '温迪', '16662626262', '京东快递', '133010', '2021-09-09 11:01:37', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (23, '122', '雷电将军', '16668686868', '顺丰速运', '193302', '2021-09-09 17:53:16', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (31, '166', '可莉', '18866666666', '顺丰速运', '343876', '2021-09-11 16:50:29', NULL, 0, '18888888888');
INSERT INTO `express` VALUES (33, '123', 'dddd', '', '顺丰速运', '692063', '2021-09-14 21:23:12', NULL, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
