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

 Date: 17/09/2021 18:52:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for euser
-- ----------------------------
DROP TABLE IF EXISTS `euser`;
CREATE TABLE `euser`  (
  `number` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userphone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idcard` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `registertime` timestamp(0) NULL DEFAULT NULL,
  `logintime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of euser
-- ----------------------------
INSERT INTO `euser` VALUES (1, '安伯', '12266666666', '411325199911111111', '123', '2021-09-02 10:50:12', '2021-09-16 10:50:16');
INSERT INTO `euser` VALUES (2, '凯亚', '16565656565', '411325199911111112', '123', '2021-09-01 10:51:05', '2021-09-15 10:51:09');
INSERT INTO `euser` VALUES (3, '丽莎', '13888888888', '411325199911111113', '123', '2021-09-01 10:51:29', '2021-09-16 10:51:33');
INSERT INTO `euser` VALUES (4, '重云', '17676767676', '411325199911111114', '123', '2021-09-01 10:52:20', '2021-09-15 10:52:24');
INSERT INTO `euser` VALUES (5, '香菱', '16565656565', '411325199911111115', '123', '2021-09-01 10:52:54', '2021-09-16 10:52:57');
INSERT INTO `euser` VALUES (6, '芭芭拉', '18282828282', '411325199911111116', '123', '2021-09-01 10:53:19', '2021-09-16 10:53:22');
INSERT INTO `euser` VALUES (7, '迪奥娜', '18686868686', '411325199911111117', '123', '2021-09-01 10:54:11', '2021-09-16 10:55:31');
INSERT INTO `euser` VALUES (8, '班尼特', '18989898989', '411325199911111118', '123', '2021-09-01 10:59:09', '2021-09-16 10:59:12');
INSERT INTO `euser` VALUES (9, '菲谢尔', '17575757575', '411325199911111119', '123', '2021-09-01 11:00:35', '2021-09-16 11:00:38');
INSERT INTO `euser` VALUES (10, '砂糖', '13399999999', '419255200001011234', '123', '2021-09-01 11:01:24', '2021-09-16 11:01:28');
INSERT INTO `euser` VALUES (11, '行秋', '16666886688', '411325199909091234', '123', '2021-09-01 11:02:20', '2021-09-16 11:02:23');

SET FOREIGN_KEY_CHECKS = 1;
