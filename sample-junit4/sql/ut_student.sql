/*
 Navicat Premium Data Transfer

 Source Server         : local-mysql
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : feature_unittest

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 04/12/2022 17:18:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ut_student
-- ----------------------------
DROP TABLE IF EXISTS `ut_student`;
CREATE TABLE `ut_student`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `grade` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年级，例如2021',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
