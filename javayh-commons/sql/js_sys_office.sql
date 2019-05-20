/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : jeesite

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-05-20 21:32:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for js_sys_office
-- ----------------------------
DROP TABLE IF EXISTS `js_sys_office`;
CREATE TABLE `js_sys_office` (
  `office_code` varchar(64) NOT NULL COMMENT '机构编码',
  `parent_code` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_codes` varchar(1000) NOT NULL COMMENT '所有父级编号',
  `tree_sort` decimal(10,0) NOT NULL COMMENT '本级排序号（升序）',
  `tree_sorts` varchar(1000) NOT NULL COMMENT '所有级别排序号',
  `tree_leaf` char(1) NOT NULL COMMENT '是否最末级',
  `tree_level` decimal(4,0) NOT NULL COMMENT '层次级别',
  `tree_names` varchar(1000) NOT NULL COMMENT '全节点名',
  `view_code` varchar(100) NOT NULL COMMENT '机构代码',
  `office_name` varchar(100) NOT NULL COMMENT '机构名称',
  `full_name` varchar(200) NOT NULL COMMENT '机构全称',
  `office_type` char(1) NOT NULL COMMENT '机构类型',
  `leader` varchar(100) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(100) DEFAULT NULL COMMENT '办公电话',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `email` varchar(300) DEFAULT NULL COMMENT '电子邮箱',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1删除 2停用）',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) NOT NULL DEFAULT '0' COMMENT '租户代码',
  `corp_name` varchar(100) NOT NULL DEFAULT 'JeeSite' COMMENT '租户名称',
  `extend_s1` varchar(500) DEFAULT NULL COMMENT '扩展 String 1',
  `extend_s2` varchar(500) DEFAULT NULL COMMENT '扩展 String 2',
  `extend_s3` varchar(500) DEFAULT NULL COMMENT '扩展 String 3',
  `extend_s4` varchar(500) DEFAULT NULL COMMENT '扩展 String 4',
  `extend_s5` varchar(500) DEFAULT NULL COMMENT '扩展 String 5',
  `extend_s6` varchar(500) DEFAULT NULL COMMENT '扩展 String 6',
  `extend_s7` varchar(500) DEFAULT NULL COMMENT '扩展 String 7',
  `extend_s8` varchar(500) DEFAULT NULL COMMENT '扩展 String 8',
  `extend_i1` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 1',
  `extend_i2` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 2',
  `extend_i3` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 3',
  `extend_i4` decimal(19,0) DEFAULT NULL COMMENT '扩展 Integer 4',
  `extend_f1` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 1',
  `extend_f2` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 2',
  `extend_f3` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 3',
  `extend_f4` decimal(19,4) DEFAULT NULL COMMENT '扩展 Float 4',
  `extend_d1` datetime DEFAULT NULL COMMENT '扩展 Date 1',
  `extend_d2` datetime DEFAULT NULL COMMENT '扩展 Date 2',
  `extend_d3` datetime DEFAULT NULL COMMENT '扩展 Date 3',
  `extend_d4` datetime DEFAULT NULL COMMENT '扩展 Date 4',
  PRIMARY KEY (`office_code`),
  KEY `idx_sys_office_cc` (`corp_code`),
  KEY `idx_sys_office_pc` (`parent_code`),
  KEY `idx_sys_office_pcs` (`parent_codes`(255)),
  KEY `idx_sys_office_status` (`status`),
  KEY `idx_sys_office_ot` (`office_type`),
  KEY `idx_sys_office_vc` (`view_code`),
  KEY `idx_sys_office_ts` (`tree_sort`),
  KEY `idx_sys_office_tss` (`tree_sorts`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构表';

-- ----------------------------
-- Records of js_sys_office
-- ----------------------------
INSERT INTO `js_sys_office` VALUES ('SD', '0', '0,', '40', '0000000040,', '0', '0', '山东公司', 'SD', '山东公司', '山东公司', '1', null, null, null, null, null, '0', 'system', '2018-09-17 23:22:01', 'system', '2018-09-17 23:22:01', null, '0', 'JeeSite', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `js_sys_office` VALUES ('SDJN', 'SD', '0,SD,', '30', '0000000040,0000000030,', '0', '1', '山东公司/济南公司', 'SDJN', '济南公司', '山东济南公司', '2', null, null, null, null, null, '0', 'system', '2018-09-17 23:22:01', 'system', '2018-09-17 23:22:01', null, '0', 'JeeSite', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `js_sys_office` VALUES ('SDJN01', 'SDJN', '0,SD,SDJN,', '30', '0000000040,0000000030,0000000030,', '1', '2', '山东公司/济南公司/企管部', 'SDJN01', '企管部', '山东济南企管部', '3', null, null, null, null, null, '0', 'system', '2018-09-17 23:22:01', 'system', '2018-09-17 23:22:01', null, '0', 'JeeSite', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `js_sys_office` VALUES ('SDJN02', 'SDJN', '0,SD,SDJN,', '40', '0000000040,0000000030,0000000040,', '1', '2', '山东公司/济南公司/财务部', 'SDJN02', '财务部', '山东济南财务部', '3', null, null, null, null, null, '0', 'system', '2018-09-17 23:22:01', 'system', '2018-09-17 23:22:01', null, '0', 'JeeSite', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `js_sys_office` VALUES ('SDJN03', 'SDJN', '0,SD,SDJN,', '50', '0000000040,0000000030,0000000050,', '1', '2', '山东公司/济南公司/研发部', 'SDJN03', '研发部', '山东济南研发部', '3', null, null, null, null, null, '0', 'system', '2018-09-17 23:22:01', 'system', '2018-09-17 23:22:01', null, '0', 'JeeSite', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `js_sys_office` VALUES ('SDQD', 'SD', '0,SD,', '40', '0000000040,0000000040,', '0', '1', '山东公司/青岛公司', 'SDQD', '青岛公司', '山东青岛公司', '2', null, null, null, null, null, '0', 'system', '2018-09-17 23:22:01', 'system', '2018-09-17 23:22:01', null, '0', 'JeeSite', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `js_sys_office` VALUES ('SDQD01', 'SDQD', '0,SD,SDQD,', '30', '0000000040,0000000040,0000000030,', '1', '2', '山东公司/青岛公司/企管部', 'SDQD01', '企管部', '山东青岛企管部', '3', null, null, null, null, null, '0', 'system', '2018-09-17 23:22:01', 'system', '2018-09-17 23:22:01', null, '0', 'JeeSite', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `js_sys_office` VALUES ('SDQD02', 'SDQD', '0,SD,SDQD,', '40', '0000000040,0000000040,0000000040,', '1', '2', '山东公司/青岛公司/财务部', 'SDQD02', '财务部', '山东青岛财务部', '3', null, null, null, null, null, '0', 'system', '2018-09-17 23:22:01', 'system', '2018-09-17 23:22:01', null, '0', 'JeeSite', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `js_sys_office` VALUES ('SDQD03', 'SDQD', '0,SD,SDQD,', '50', '0000000040,0000000040,0000000050,', '1', '2', '山东公司/青岛公司/研发部', 'SDQD03', '研发部', '山东青岛研发部', '3', null, null, null, null, null, '0', 'system', '2018-09-17 23:22:01', 'system', '2018-09-17 23:22:01', null, '0', 'JeeSite', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
