/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : 47.113.231.122:3306
 Source Schema         : api_scheduler

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 22/10/2023 20:19:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api
-- ----------------------------
DROP TABLE IF EXISTS `api`;
CREATE TABLE `api`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接口名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求地址',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求参数',
  `body` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求体',
  `headers` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求头',
  `response_type` tinyint NULL DEFAULT NULL COMMENT '响应类型（0：text）',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '接口api表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api
-- ----------------------------
INSERT INTO `api` VALUES (1, '获取bilibili用户信息接口', 'https://api.bilibili.com/x/space/wbi/acc/info', 'GET', '{\"params\":{\"mid\":[\"1366330988\"]}}', NULL, '{\"headers\":{\"User-Agent\":[\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0\"]}}', 0, '2023-10-21 10:53:24');
INSERT INTO `api` VALUES (2, '获取bilibili用户信息接口', 'https://api.bilibili.com/x/space/wbi/acc/info', 'GET', '{\"params\":{\"mid\":[\"1366330988\"]}}', NULL, '{\"headers\":{\"User-Agent\":[\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0\"]}}', 0, '2023-10-21 10:53:24');
INSERT INTO `api` VALUES (3, '获取bilibili用户信息接口', 'https://api.bilibili.com/x/space/wbi/acc/info', 'GET', '{\"params\":{\"mid\":[\"1366330988\"]}}', NULL, '{\"headers\":{\"User-Agent\":[\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0\"]}}', 0, '2023-10-21 10:53:24');
INSERT INTO `api` VALUES (4, '获取bilibili用户信息接口', 'https://api.bilibili.com/x/space/wbi/acc/info', 'GET', '{\"params\":{\"mid\":[\"1366330988\"]}}', NULL, '{\"headers\":{\"User-Agent\":[\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0\"]}}', 0, '2023-10-21 10:53:24');
INSERT INTO `api` VALUES (5, '获取bilibili用户信息接口', 'https://api.bilibili.com/x/space/wbi/acc/info', 'GET', '{\"params\":{\"mid\":[\"1366330988\"]}}', NULL, '{\"headers\":{\"User-Agent\":[\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0\"]}}', 0, '2023-10-21 10:53:24');
INSERT INTO `api` VALUES (6, '获取bilibili用户信息接口', 'https://api.bilibili.com/x/space/wbi/acc/info', 'GET', '{\"params\":{\"mid\":[\"1366330988\"]}}', NULL, '{\"headers\":{\"User-Agent\":[\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0\"]}}', 0, '2023-10-21 10:53:24');
INSERT INTO `api` VALUES (7, '获取bilibili用户信息接口', 'https://api.bilibili.com/x/space/wbi/acc/info', 'GET', '{\"params\":{\"mid\":[\"1366330988\"]}}', NULL, '{\"headers\":{\"User-Agent\":[\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0\"]}}', 0, '2023-10-21 10:53:24');
INSERT INTO `api` VALUES (8, '获取bilibili用户信息接口', 'https://api.bilibili.com/x/space/wbi/acc/info', 'GET', '{\"params\":{\"mid\":[\"1366330988\"]}}', NULL, '{\"headers\":{\"User-Agent\":[\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0\"]}}', 0, '2023-10-21 10:53:24');
INSERT INTO `api` VALUES (9, '获取bilibili用户信息接口', 'https://api.bilibili.com/x/space/wbi/acc/info', 'GET', '{\"params\":{\"mid\":[\"1366330988\"]}}', NULL, '{\"headers\":{\"User-Agent\":[\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0\"]}}', 0, '2023-10-21 10:53:24');
INSERT INTO `api` VALUES (10, '获取bilibili用户信息接口', 'https://api.bilibili.com/x/space/wbi/acc/info', 'GET', '{\"params\":{\"mid\":[\"1366330988\"]}}', NULL, '{\"headers\":{\"User-Agent\":[\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0\"]}}', 0, '2023-10-21 10:53:24');

-- ----------------------------
-- Table structure for api_trigger
-- ----------------------------
DROP TABLE IF EXISTS `api_trigger`;
CREATE TABLE `api_trigger`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `api_id` int UNSIGNED NOT NULL COMMENT '接口id',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户id',
  `cron` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `last_exec` datetime NULL DEFAULT NULL COMMENT '上次执行时间',
  `trigger_type` tinyint NULL DEFAULT NULL COMMENT '触发类型（-1：任何时候不触发，0：任何时候触发，1：根据Http状态码触发，2：根据Json指定值触发）',
  `expect_data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '预期结果',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态（0：未启用，1：启用）',
  `expected_mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通知邮箱',
  `expected_subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通知主题',
  `expected_text` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通知信息',
  `expected_var` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存放变量',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_trigger
-- ----------------------------
INSERT INTO `api_trigger` VALUES (1, 1, 1, '*/20 * * * * *', '2023-10-22 12:42:20', 2, '{\"expectData\":{\"[data, mid]\":\"1366330988\"},\"headers\":{\"User-Agent\":[\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0\"]}}\r\n', 0, '1829462342@qq.com', NULL, NULL, NULL, '2023-10-20 15:20:56');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id-雪花id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '罗雲霄', '1829462342@qq.com', '2023-10-21 13:37:18');

SET FOREIGN_KEY_CHECKS = 1;
