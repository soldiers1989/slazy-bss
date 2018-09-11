/*
Navicat MySQL Data Transfer

Source Server         : lime
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : slazy

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2018-03-08 23:50:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_activity`
-- ----------------------------
DROP TABLE IF EXISTS `sys_activity`;
CREATE TABLE `sys_activity` (
  `id` int(11) NOT NULL auto_increment,
  `state` varchar(1) default NULL COMMENT '状态 0:进行中 1：结束',
  `content` varchar(1000) default NULL COMMENT '活动内容',
  `create_Time` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_activity
-- ----------------------------
INSERT INTO `sys_activity` VALUES ('1', '0', 'test', '2018-03-08 23:36:21');
INSERT INTO `sys_activity` VALUES ('2', '0', 'test', '2018-03-08 23:36:40');
