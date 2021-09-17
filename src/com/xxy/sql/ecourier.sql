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

 Date: 17/09/2021 18:49:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ecourier
-- ----------------------------
DROP TABLE IF EXISTS `ecourier`;
CREATE TABLE `ecourier`  (
  `number` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `couriername` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `courierphone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `idcard` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` int(11) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `registertime` timestamp(0) NULL DEFAULT NULL,
  `logintime` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ecourier
-- ----------------------------
INSERT INTO `ecourier` VALUES (1, '安伯', '12266666666', '411325188808080808', '123', 00000000666, '2021-09-04 15:31:35', '2021-09-11 15:31:49');
INSERT INTO `ecourier` VALUES (2, '刻晴', '16868686868', '411317199901010101', '123', 00000000888, '2021-09-06 17:29:57', '2021-09-12 17:30:06');
INSERT INTO `ecourier` VALUES (3, '琴', '12222222222', '411317199906090101', '123', 00000000678, '2021-09-01 17:31:22', '2021-09-12 17:31:28');
INSERT INTO `ecourier` VALUES (4, '魈', '14444444444', '411317199905040609', '123', 00000001999, '2021-09-02 17:32:52', '2021-09-12 17:32:57');
INSERT INTO `ecourier` VALUES (5, '莫娜', '12888888888', '411326199809161234', '123', 00000000222, '2021-09-03 17:33:56', '2021-09-11 17:33:59');
INSERT INTO `ecourier` VALUES (6, '公子', '13666666666', '411326199906155555', '123', 00000000365, '2021-09-03 17:34:54', '2021-09-12 17:34:58');
INSERT INTO `ecourier` VALUES (7, '七七', '17777777777', '411311177707070707', '123', 00000000777, '2021-09-07 17:35:56', '2021-09-12 17:36:03');
INSERT INTO `ecourier` VALUES (8, '行秋', '16666886688', '411325199909091234', '123', 00000000625, '2021-09-03 17:38:35', '2021-09-12 17:38:42');
INSERT INTO `ecourier` VALUES (9, '辛焱', '13333333333', '411323199809181567', '123', 00000000289, '2021-09-01 17:42:56', '2021-09-12 17:43:00');
INSERT INTO `ecourier` VALUES (10, '早柚', '16662626262', '415133199909091199', '123', 00000000999, '2021-09-03 17:44:21', '2021-09-12 17:44:25');
INSERT INTO `ecourier` VALUES (11, '砂糖', '13399999999', '419255200001011234', '123', 00000000688, '2021-09-02 17:45:19', '2021-09-12 17:45:22');
INSERT INTO `ecourier` VALUES (12, '九条', '18999999999', '419192199903061236', '123', 00000000908, '2021-09-01 17:46:17', '2021-09-12 17:46:20');
