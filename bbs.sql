/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2013-04-27 22:05:36
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('4', '屌丝一起来 ⊙ω⊙', null, '1');
INSERT INTO `module` VALUES ('5', '技术专区 (ΘｏΘ)', null, '2');

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topicID` int(11) NOT NULL,
  `userID` int(11) NOT NULL DEFAULT '0',
  `content` text NOT NULL,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `hasReply` bit(1) NOT NULL DEFAULT b'0',
  `updateTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '1', '0', '222', '2013-04-26 20:04:11', '', null);
INSERT INTO `post` VALUES ('2', '1', '0', '<p>最近想在产品中加入即时通讯的功能.BS架构的程序.实现方式不外乎两大标准下的各种奇淫技巧.</p><p>这两大标准就是 HTML5 HTML4</p><p>为啥这两个呢..因为HTML5里面有websocket.这个彻底颠覆http请求的东西,使得请求不再是无状态的.</p><p>当然websocket目前支持不是很好.也没办法.看着好东西没法用.这是一种何种的煎熬....搞得我总是想在产品里面内嵌chromeFrame.然后强制给客户装上.哈哈...当然客户没准会和我拼命呢...</p><p>没办法,在现有的需求中基本上,实现思路只有一个了.也就是第一个让我头疼了一阵的关键词</p><p><br /></p>', '2013-04-26 20:28:50', '', null);
INSERT INTO `post` VALUES ('3', '2', '0', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0001.gif\" />^O^</p>', '2013-04-27 21:14:15', '', null);
INSERT INTO `post` VALUES ('4', '1', '0', '<p>^O^<img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\" /></p>', '2013-04-27 22:02:32', '', null);
INSERT INTO `post` VALUES ('5', '1', '0', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\" />^O^</p>', '2013-04-27 22:03:56', '', null);
INSERT INTO `post` VALUES ('6', '1', '0', '<p>^O^<img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\" /></p>', '2013-04-27 22:04:10', '', null);

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
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

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
INSERT INTO `reply` VALUES ('58', '10', '14', '，长度不', '，长度不，长度不', '2013-04-10 21:23:54');
INSERT INTO `reply` VALUES ('59', '10', '15', 'ueditor', 'ueditorueditor', '2013-04-10 21:24:29');
INSERT INTO `reply` VALUES ('60', '11', '16', '发布', '发布发布', '2013-04-13 15:50:06');
INSERT INTO `reply` VALUES ('61', '11', '16', '发布', '沧海一沧海一', '2013-04-13 15:50:23');
INSERT INTO `reply` VALUES ('62', '11', '16', '发布', '声笑', '2013-04-13 15:50:33');
INSERT INTO `reply` VALUES ('63', '11', '16', '发布', '天', '2013-04-13 15:51:02');
INSERT INTO `reply` VALUES ('64', '11', '16', '发布', '胜出', '2013-04-13 15:51:11');
INSERT INTO `reply` VALUES ('65', '11', '16', '发布', '胜出胜出', '2013-04-13 15:51:17');
INSERT INTO `reply` VALUES ('66', '1', '1', '22', '2222', '2013-04-26 20:30:20');
INSERT INTO `reply` VALUES ('67', '1', '1', '昵称', '昵称昵称', '2013-04-27 22:04:25');

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(20) DEFAULT NULL,
  `topicCount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL DEFAULT '0',
  `moduleID` int(11) NOT NULL,
  `postCount` int(11) NOT NULL DEFAULT '1',
  `replyCount` int(11) NOT NULL DEFAULT '0',
  `pv` int(11) NOT NULL DEFAULT '0',
  `content` varchar(60) NOT NULL,
  `emotion` tinyint(2) DEFAULT NULL,
  `tag` varchar(80) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isPublished` bit(1) NOT NULL DEFAULT b'1',
  `isUp` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('1', '0', '4', '4', '0', '6', 'omet 异步请求技术中相关关', null, 'xx', '2013-04-26 20:28:50', '', '');
INSERT INTO `topic` VALUES ('2', '0', '4', '1', '0', '4', 'aaa', null, 'aaa,bbb,ccc', '2013-04-27 21:14:15', '', '');

-- ----------------------------
-- Table structure for `topic_tag`
-- ----------------------------
DROP TABLE IF EXISTS `topic_tag`;
CREATE TABLE `topic_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topicID` int(11) NOT NULL,
  `tagID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic_tag
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL,
  `sex` bit(1) NOT NULL DEFAULT b'1',
  `email` varchar(30) DEFAULT NULL,
  `headImg` varchar(80) DEFAULT NULL,
  `blogUrl` varchar(80) DEFAULT NULL,
  `selfIntroduce` varchar(300) DEFAULT NULL,
  `feeling` varchar(100) DEFAULT NULL,
  `registDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'iver', 'A040B1E5BD598DEE34F8B6D954EB657B', '', 'iver@qq.com', null, null, 'hello world.', 'happy.', '2013-04-10');
