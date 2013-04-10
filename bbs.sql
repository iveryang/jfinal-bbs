/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2013-04-10 20:50:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `module`
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `detail` varchar(100) DEFAULT NULL,
  `turn` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('4', 'one', 'one...', '1');

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topicID` int(11) NOT NULL,
  `content` text NOT NULL,
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hasReply` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('14', '10', '<p>高效的CSS已经不是一个新话题，也不是一个我非得重拾的话题，但是，它却是自我在SKY工作以后，真正感兴趣并始终关注的一个话题。</p><p>很多人或者忘记了，或者仅仅是没有意识到，CSS可以是高效的也可能导致低能。然而，我们可以不考虑当你自认为会的太少而使用了低效的CSS这种情况。</p><p>这些规则只真正用在性能要求很高的网站上，这些网站对速度要求很高，任何一个页面可能含有成百上千个DOM元素。但是实践出真理，不管你是在打造下一个facebook 还是在开发一个本地的展示网页，多学点总是好的</p><p><br /></p>', '2013-04-09 10:08:07', '');
INSERT INTO `post` VALUES ('15', '10', '<p>欢迎使用ueditor!<img src=\"http://img.baidu.com/hi/jx2/j_0015.gif\" /></p>', '2013-04-10 20:28:23', '');

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topicID` int(11) NOT NULL DEFAULT '1',
  `postID` int(11) NOT NULL,
  `userName` varchar(15) NOT NULL DEFAULT '0',
  `content` varchar(300) NOT NULL,
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('48', '10', '14', '展示网页', '展示网页展示网页', '2013-04-09 10:08:15');
INSERT INTO `reply` VALUES ('49', '10', '14', '痴笑笑', '痴笑笑痴笑笑', '2013-04-09 10:39:11');
INSERT INTO `reply` VALUES ('50', '10', '14', '必填', '必填必填', '2013-04-10 20:28:04');
INSERT INTO `reply` VALUES ('51', '10', '15', '长度', '长度长度', '2013-04-10 20:28:33');
INSERT INTO `reply` VALUES ('52', '10', '14', '评', '评评', '2013-04-10 20:29:48');
INSERT INTO `reply` VALUES ('53', '10', '14', '，长度', '，长度，长度', '2013-04-10 20:34:43');
INSERT INTO `reply` VALUES ('54', '10', '14', '必填', '必填必填', '2013-04-10 20:46:05');
INSERT INTO `reply` VALUES ('55', '10', '14', '必填', '必填必填', '2013-04-10 20:46:09');
INSERT INTO `reply` VALUES ('56', '10', '14', '必填', '必填必填', '2013-04-10 20:46:14');

-- ----------------------------
-- Table structure for `sub_module`
-- ----------------------------
DROP TABLE IF EXISTS `sub_module`;
CREATE TABLE `sub_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `detail` varchar(100) DEFAULT NULL,
  `turn` tinyint(2) DEFAULT NULL,
  `moduleID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_module
-- ----------------------------
INSERT INTO `sub_module` VALUES ('9', 'one_1', 'one_1...', '1', '4');

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moduleID` int(11) NOT NULL,
  `subModuleID` int(11) NOT NULL,
  `postCount` int(11) NOT NULL DEFAULT '1',
  `content` varchar(50) DEFAULT NULL,
  `firstPostPreview` varchar(300) DEFAULT NULL,
  `pv` int(11) DEFAULT '0',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isPublished` bit(1) NOT NULL DEFAULT b'1',
  `isUp` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('10', '4', '9', '2', '编写高效的CSS选择器', '<p>高效的CSS已经不是一个新话题，也不是一个我非得重拾的话题，但是，它却是自我在SKY工作以后，真正感兴趣并始终关注的一个话题。</p><p>很多人或者忘记了，或者仅仅是没有意识到，CSS可以是高效的也可能导致低能。然而，我们可以不考虑当你自认为会的太少而使用了低效的CSS这种情况。</p><p>这些规则只真正用在性能要求很高的网站上，这些网站对速度要求很高，任何一个页面可能含有成百上千个DOM元素。但是实践出真理，不管你是在打造下一个facebook 还是在开发一个本地的展示网页，多学点总是好的</p><p><br /></p>', '20', '2013-04-09 10:08:07', '', '');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'iver', 'A040B1E5BD598DEE34F8B6D954EB657B');
