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

 Date: 17/07/2019 18:55:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for page_process
-- ----------------------------
DROP TABLE IF EXISTS `page_process`;
CREATE TABLE `page_process`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spider_id` int(11) NULL DEFAULT NULL COMMENT '爬虫id',
  `rule` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '提取规则   策略列表  json存储',
  `attribute` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '属性名称',
  `field_en` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '持久化字段名',
  `field_cn` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字段中文名,用户界面展示',
  `field_length` smallint(6) NOT NULL DEFAULT 0 COMMENT '字段长度',
  `field_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字段类型',
  `field_is_show` smallint(255) NOT NULL DEFAULT 1 COMMENT '字段是否展示',
  `skip_condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '页面跳过规则   策略列表  json存储',
  `target_requests_regex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '后续抓取url',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '属性提取配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pipeline
-- ----------------------------
DROP TABLE IF EXISTS `pipeline`;
CREATE TABLE `pipeline`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spider_id` int(11) NOT NULL DEFAULT 0,
  `insert_sql` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `table_sql` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for site
-- ----------------------------
DROP TABLE IF EXISTS `site`;
CREATE TABLE `site`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spider_id` int(11) NOT NULL DEFAULT 0 COMMENT '爬虫id',
  `charset` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '为空默认是utf-8',
  `user_agent` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `time_out` smallint(6) NOT NULL DEFAULT 3000 COMMENT '超市时间 默认3秒',
  `retry_time` smallint(6) NOT NULL DEFAULT 3 COMMENT '重试次数 默认3次',
  `cycle_retry_time` smallint(6) NOT NULL DEFAULT 3 COMMENT '循环重试次数 默认3次',
  `cookies` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'cookies 列表  用json存储',
  `domain` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '域名',
  `headers` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'headers 用json存储',
  `http_proxy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'Http代理  如127.0.0.1:8080',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '网站配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for spider
-- ----------------------------
DROP TABLE IF EXISTS `spider`;
CREATE TABLE `spider`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `table_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '表名',
  `thread` smallint(1) NOT NULL DEFAULT 5 COMMENT '线程数 默认5',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '初始的URL',
  `test_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '测试网页',
  `data_source` smallint(1) NOT NULL DEFAULT 1 COMMENT '数据来源  1 html 2 ajax',
  `start_type` smallint(1) NOT NULL DEFAULT 1 COMMENT '启动类型  1 同步启动 2异步启动',
  `persist_pattern` smallint(1) NOT NULL DEFAULT 1 COMMENT '持久化方式  1 mysql 2 json文件',
  `stat` smallint(1) NOT NULL COMMENT '状态  0 初始化 1 运行中 2停止',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for spider_log
-- ----------------------------
DROP TABLE IF EXISTS `spider_log`;
CREATE TABLE `spider_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spider_id` int(11) NOT NULL,
  `start_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `type` smallint(1) NOT NULL DEFAULT 1 COMMENT '1 爬虫 2数据清洗 ',
  `thread` smallint(3) NOT NULL DEFAULT 5 COMMENT '线程数',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `uptime_time` timestamp(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
