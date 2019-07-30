/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : tsc

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 30/07/2019 18:23:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for spider
-- ----------------------------
DROP TABLE IF EXISTS `spider`;
CREATE TABLE `spider`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `table_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `thread` smallint(1) NULL DEFAULT NULL COMMENT '线程数 默认5',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '初始的URL',
  `test_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '测试网页',
  `data_source` smallint(1) NULL DEFAULT NULL COMMENT '数据来源  1 html 2 ajax',
  `start_type` smallint(1) NULL DEFAULT NULL COMMENT '启动类型  1 同步启动 2异步启动',
  `persist_pattern` smallint(1) NULL DEFAULT NULL COMMENT '持久化方式  1 mysql 2 json文件',
  `stat` smallint(1) NULL DEFAULT NULL COMMENT '状态  0 初始化 1 运行中 2停止',
  `page_process_configs` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `pipeline_config` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `site_config` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for spider_log
-- ----------------------------
DROP TABLE IF EXISTS `spider_log`;
CREATE TABLE `spider_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spider_id` int(11) NULL DEFAULT NULL,
  `start_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
