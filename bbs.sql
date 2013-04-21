/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2013-04-20 21:23:41
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
  `userID` int(11) NOT NULL,
  `content` text NOT NULL,
  `createdTime` timestamp NULL DEFAULT NULL,
  `hasReply` bit(1) NOT NULL DEFAULT b'0',
  `updatedTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('14', '10', '0', '<p>高效的CSS已经不是一个新话题，也不是一个我非得重拾的话题，但是，它却是自我在SKY工作以后，真正感兴趣并始终关注的一个话题。</p><p>很多人或者忘记了，或者仅仅是没有意识到，CSS可以是高效的也可能导致低能。然而，我们可以不考虑当你自认为会的太少而使用了低效的CSS这种情况。</p><p>这些规则只真正用在性能要求很高的网站上，这些网站对速度要求很高，任何一个页面可能含有成百上千个DOM元素。但是实践出真理，不管你是在打造下一个facebook 还是在开发一个本地的展示网页，多学点总是好的</p><p><br /></p>', '2013-04-09 10:08:07', '', null);
INSERT INTO `post` VALUES ('15', '10', '0', '<p>欢迎使用ueditor!<img src=\"http://img.baidu.com/hi/jx2/j_0015.gif\" /></p>', '2013-04-10 20:28:23', '', null);
INSERT INTO `post` VALUES ('16', '11', '0', '<p>^O^<span style=\"color:#333333;font-family:微软雅黑;font-size:14px;line-height:20px;background-color:#ffffff;\">是否发布<span style=\"color:#333333;font-family:微软雅黑;font-size:14px;line-height:20px;background-color:#ffffff;\">是否发布<img src=\"http://img.baidu.com/hi/jx2/j_0005.gif\" />11</span></span></p>', '2013-04-13 15:31:23', '', null);
INSERT INTO `post` VALUES ('17', '12', '0', '<p>^O^^O^^O^</p>', '2013-04-13 15:53:28', '', null);
INSERT INTO `post` VALUES ('18', '13', '0', '<p>由于微软已经确认对 Sliverlight 插件的支持将结束，因此 Netflix 在 2008 年建立的这套到 PC 的流数据解决方案就必须寻找替代方案。该公司已经基于 <a href=\"http://www.engadget.com/2013/03/11/samsung-chromebook-netflix-html5-streaming/\">Samsung&#39;s ARM Chromebooks</a> 实现了一个新的实例。并计划将这项技术带到 PC 和 Mac 上的 Chrome 浏览器。目前 Netflix 正在等待很多 W3C 的举措来处理 HTML5 视频扩展可支持高清和 DRM 的完全实现。一旦最新的 (Web Cryptography API 推出，就可以放弃定制 API 插件的方式</p>', '2013-04-16 20:38:21', '', null);
INSERT INTO `post` VALUES ('19', '14', '0', '<p style=\"color:#333333;font-family:&#39;helvetica neue&#39;, helvetica, arial, sans-serif;font-size:14px;line-height:20px;background-color:#ffffff;margin-top:0px;margin-bottom:10px;\">Bootstrap sets basic global display, typography, and link styles. Specifically, we:</p><ul style=\"padding:0px;margin:0px 0px 10px 25px;color:#333333;font-family:&#39;helvetica neue&#39;, helvetica, arial, sans-serif;font-size:14px;line-height:20px;background-color:#ffffff;\"><li style=\"margin-bottom:5px;\"><p>Remove <code style=\"padding:2px 4px;font-family:monaco, menlo, consolas, &#39;courier new&#39;, monospace;font-size:12px;color:#dd1144;border-top-left-radius:3px;border-top-right-radius:3px;border-bottom-right-radius:3px;border-bottom-left-radius:3px;background-color:#f7f7f9;border:1px solid #e1e1e8;\">margin</code> on the body</p></li><li style=\"margin-bottom:5px;\"><p>Set <code style=\"padding:2px 4px;font-family:monaco, menlo, consolas, &#39;courier new&#39;, monospace;font-size:12px;color:#dd1144;border-top-left-radius:3px;border-top-right-radius:3px;border-bottom-right-radius:3px;border-bottom-left-radius:3px;background-color:#f7f7f9;border:1px solid #e1e1e8;\">background-color: white;</code> on the <code style=\"padding:2px 4px;font-family:monaco, menlo, consolas, &#39;courier new&#39;, monospace;font-size:12px;color:#dd1144;border-top-left-radius:3px;border-top-right-radius:3px;border-bottom-right-radius:3px;border-bottom-left-radius:3px;background-color:#f7f7f9;border:1px solid #e1e1e8;\">body</code></p></li><li style=\"margin-bottom:5px;\"><p>Use the <code style=\"padding:2px 4px;font-family:monaco, menlo, consolas, &#39;courier new&#39;, monospace;font-size:12px;color:#dd1144;border-top-left-radius:3px;border-top-right-radius:3px;border-bottom-right-radius:3px;border-bottom-left-radius:3px;background-color:#f7f7f9;border:1px solid #e1e1e8;\">@baseFontFamily</code>, <code style=\"padding:2px 4px;font-family:monaco, menlo, consolas, &#39;courier new&#39;, monospace;font-size:12px;color:#dd1144;border-top-left-radius:3px;border-top-right-radius:3px;border-bottom-right-radius:3px;border-bottom-left-radius:3px;background-color:#f7f7f9;border:1px solid #e1e1e8;\">@baseFontSize</code>, and <code style=\"padding:2px 4px;font-family:monaco, menlo, consolas, &#39;courier new&#39;, monospace;font-size:12px;color:#dd1144;border-top-left-radius:3px;border-top-right-radius:3px;border-bottom-right-radius:3px;border-bottom-left-radius:3px;background-color:#f7f7f9;border:1px solid #e1e1e8;\">@baseLineHeight</code> attributes as our typographyic base</p></li><li style=\"margin-bottom:5px;\"><p>Set the global link color via <code style=\"padding:2px 4px;font-family:monaco, menlo, consolas, &#39;courier new&#39;, monospace;font-size:12px;color:#dd1144;border-top-left-radius:3px;border-top-right-radius:3px;border-bottom-right-radius:3px;border-bottom-left-radius:3px;background-color:#f7f7f9;border:1px solid #e1e1e8;\">@linkColor</code> and apply link underlines only on <code style=\"padding:2px 4px;font-family:monaco, menlo, consolas, &#39;courier new&#39;, monospace;font-size:12px;color:#dd1144;border-top-left-radius:3px;border-top-right-radius:3px;border-bottom-right-radius:3px;border-bottom-left-radius:3px;background-color:#f7f7f9;border:1px solid #e1e1e8;\">:hover</code></p></li></ul><p style=\"color:#333333;font-family:&#39;helvetica neue&#39;, helvetica, arial, sans-serif;font-size:14px;line-height:20px;background-color:#ffffff;margin-top:0px;margin-bottom:10px;\">These styles can be found within <strong>scaffolding.less</strong>.</p><p><br /></p>', '2013-04-16 20:42:54', '', null);
INSERT INTO `post` VALUES ('20', '15', '0', '<p>^O^<span style=\"color:#333333;font-family:微软雅黑;line-height:32px;background-color:#ffffff;\">沧海一声笑　滔滔两岸潮</span><br style=\"font-family:微软雅黑;color:#333333;line-height:32px;background-color:#ffffff;\" /><span style=\"color:#333333;font-family:微软雅黑;line-height:32px;background-color:#ffffff;\">浮沉随浪只记今朝</span><br style=\"font-family:微软雅黑;color:#333333;line-height:32px;background-color:#ffffff;\" /><span style=\"color:#333333;font-family:微软雅黑;line-height:32px;background-color:#ffffff;\">苍天笑　纷纷世上潮</span><br style=\"font-family:微软雅黑;color:#333333;line-height:32px;background-color:#ffffff;\" /><span style=\"color:#333333;font-family:微软雅黑;line-height:32px;background-color:#ffffff;\">谁负谁胜出天知晓</span><br style=\"font-family:微软雅黑;color:#333333;line-height:32px;background-color:#ffffff;\" /><span style=\"color:#333333;font-family:微软雅黑;line-height:32px;background-color:#ffffff;\">江山笑　烟雨遥</span><br style=\"font-family:微软雅黑;color:#333333;line-height:32px;background-color:#ffffff;\" /><span style=\"color:#333333;font-family:微软雅黑;line-height:32px;background-color:#ffffff;\">涛浪淘尽红尘俗世几多娇</span><br style=\"font-family:微软雅黑;color:#333333;line-height:32px;background-color:#ffffff;\" /><span style=\"color:#333333;font-family:微软雅黑;line-height:32px;background-color:#ffffff;\">清风笑　竟惹寂寥</span><br style=\"font-family:微软雅黑;color:#333333;line-height:32px;background-color:#ffffff;\" /><span style=\"color:#333333;font-family:微软雅黑;line-height:32px;background-color:#ffffff;\">豪情还剩了一襟晚照</span><br style=\"font-family:微软雅黑;color:#333333;line-height:32px;background-color:#ffffff;\" /><span style=\"color:#333333;font-family:微软雅黑;line-height:32px;background-color:#ffffff;\">苍生笑　不再寂寥</span><br style=\"font-family:微软雅黑;color:#333333;line-height:32px;background-color:#ffffff;\" /><span style=\"color:#333333;font-family:微软雅黑;line-height:32px;background-color:#ffffff;\">豪情仍在痴痴笑笑</span></p>', '2013-04-16 20:43:25', '', null);
INSERT INTO `post` VALUES ('21', '16', '0', '<p>^O^O^O^O^</p><div><p>谁负谁胜出天知晓<br />江山笑　烟雨遥<br />涛浪淘尽红尘俗世几多娇<br />清风笑　竟惹寂寥<br />豪情还剩了一襟晚照<br />苍生笑　不再寂寥<br />豪情仍在痴痴笑笑</p></div>', '2013-04-16 20:44:03', '', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

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
  `userID` int(11) NOT NULL,
  `moduleID` int(11) NOT NULL,
  `postCount` int(11) NOT NULL DEFAULT '1',
  `replyCount` int(11) NOT NULL DEFAULT '0',
  `pv` int(11) NOT NULL DEFAULT '0',
  `content` varchar(60) NOT NULL,
  `emotion` tinyint(2) DEFAULT NULL,
  `tag` varchar(80) DEFAULT NULL,
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isPublished` bit(1) NOT NULL DEFAULT b'1',
  `isUp` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('10', '0', '6', '2', '0', '37', '每个程序员都应该学习使用Python或Ruby', null, 'ddr', '2013-04-09 10:08:07', '', '');
INSERT INTO `topic` VALUES ('11', '0', '6', '1', '0', '75', '每个程序员都应该学习使用Python或Ruby', null, '老北', '2013-04-13 15:31:23', '', '');
INSERT INTO `topic` VALUES ('12', '0', '6', '1', '0', '1', '每个程序员都应该学习使用Python或Ruby', null, '嫌大冰', '2013-04-13 15:53:28', '', '');
INSERT INTO `topic` VALUES ('13', '0', '4', '1', '0', '1', '每个程序员都应该学习使用Python或Ruby', null, 'jfinal', '2013-04-16 20:38:21', '', '');
INSERT INTO `topic` VALUES ('14', '0', '4', '1', '0', '1', '每个程序员都应该学习使用Python或Ruby', null, 'beetl，嫌大冰', '2013-04-16 20:42:54', '', '');
INSERT INTO `topic` VALUES ('15', '0', '4', '1', '0', '1', '每个程序员都应该学习使用Python或Ruby', null, 'hello', '2013-04-16 20:43:25', '', '');
INSERT INTO `topic` VALUES ('16', '0', '4', '1', '0', '7', '每个程序员都应该学习使用Python或Ruby', null, 'world', '2013-04-16 20:44:02', '', '');
INSERT INTO `topic` VALUES ('17', '0', '4', '1', '0', '7', '每个程序员都应该学习使用Python或Ruby', null, 'world', '2013-04-16 20:44:02', '', '');
INSERT INTO `topic` VALUES ('18', '0', '4', '1', '0', '7', '每个程序员都应该学习使用Python或Ruby', null, 'world', '2013-04-16 20:44:02', '', '');
INSERT INTO `topic` VALUES ('19', '0', '4', '1', '0', '7', '每个程序员都应该学习使用Python或Ruby', null, 'world', '2013-04-16 20:44:02', '', '');
INSERT INTO `topic` VALUES ('20', '0', '4', '1', '0', '7', '每个程序员都应该学习使用Python或Ruby', null, 'world', '2013-04-16 20:44:02', '', '');
INSERT INTO `topic` VALUES ('21', '0', '4', '1', '0', '8', '每个程序员都应该学习使用Python或Ruby', null, 'world', '2013-04-16 20:44:02', '', '');
INSERT INTO `topic` VALUES ('22', '0', '4', '1', '0', '9', '每个程序员都应该学习使用Python或Ruby', null, 'world', '2013-04-16 20:44:02', '', '');

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
