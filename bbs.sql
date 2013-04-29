/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2013-04-29 22:37:57
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '1', '0', '222', '2013-04-26 20:04:11', '', null);
INSERT INTO `post` VALUES ('2', '1', '0', '<p>最近想在产品中加入即时通讯的功能.BS架构的程序.实现方式不外乎两大标准下的各种奇淫技巧.</p><p>这两大标准就是 HTML5 HTML4</p><p>为啥这两个呢..因为HTML5里面有websocket.这个彻底颠覆http请求的东西,使得请求不再是无状态的.</p><p>当然websocket目前支持不是很好.也没办法.看着好东西没法用.这是一种何种的煎熬....搞得我总是想在产品里面内嵌chromeFrame.然后强制给客户装上.哈哈...当然客户没准会和我拼命呢...</p><p>没办法,在现有的需求中基本上,实现思路只有一个了.也就是第一个让我头疼了一阵的关键词</p><p><br /></p>', '2013-04-26 20:28:50', '', null);
INSERT INTO `post` VALUES ('3', '2', '0', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0001.gif\" />^O^</p>', '2013-04-27 21:14:15', '', null);
INSERT INTO `post` VALUES ('4', '1', '0', '<p>^O^<img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\" /></p>', '2013-04-27 22:02:32', '', null);
INSERT INTO `post` VALUES ('5', '1', '0', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\" />^O^</p>', '2013-04-27 22:03:56', '', null);
INSERT INTO `post` VALUES ('6', '1', '0', '<p>^O^<img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\" /></p>', '2013-04-27 22:04:10', '', null);
INSERT INTO `post` VALUES ('7', '2', '0', '<p>^O^<img src=\"http://img.baidu.com/hi/jx2/j_0017.gif\" /></p>', '2013-04-28 21:10:33', '', null);
INSERT INTO `post` VALUES ('8', '3', '0', '<p>沧海一声笑　滔滔两岸潮<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />浮沉随浪只记今朝<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />苍天笑　纷纷世上潮<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />谁负谁胜出天知晓<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />江山笑　烟雨遥<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />涛浪淘尽红尘俗世几多娇<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />清风笑　竟惹寂寥<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />豪情还剩了一襟晚照<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />苍生笑　不再寂寥<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />豪情仍在痴痴笑笑</p>', '2013-04-28 21:58:32', '', null);
INSERT INTO `post` VALUES ('9', '4', '0', '<p>本文作者Cliff Meyers是一个前端工程师，熟悉HTML5、JavaScript、J2EE开发，他在开发过程中总结了自己在应对JavaScript应用越来越庞大情况下的文件结构，深得其他开发者认可。以下为CSDN编译： 地板上堆放的衣服 首先，我们来看看angular-seed，它是AngularJS应用开发的官方入门项目，其文件结构是这样的：</p>', '2013-04-29 13:27:14', '', null);
INSERT INTO `post` VALUES ('10', '5', '0', '<p>本文作者Cliff Meyers是一个前端工程师，熟悉HTML5、JavaScript、J2EE开发，他在开发过程中总结了自己在应对JavaScript应用越来越庞大情况下的文件结构，深得其他开发者认可。以下为CSDN编译： 地板上堆放的衣服 首先，我们来看看angular-seed，它是AngularJS应用开发的官方入门项目，其文件结构是这样的：</p>', '2013-04-29 13:33:39', '', null);
INSERT INTO `post` VALUES ('11', '6', '0', '<p>我自己定义了一个指令text-tile 可以把一个普通的html变成一个metro风格的瓷贴（主要是做一些样式的计算） angular.module(&quot;metro.directive&quot;, []) .directive(&quot;textTile&quot;, function () { return { templateUrl: &#39;tpl/tile_text.html&#39;, transclude: true, link: function (scope, element, attrs) { var css …</p>', '2013-04-29 13:36:53', '', null);
INSERT INTO `post` VALUES ('12', '7', '0', '<p>俺注册的邮箱是 zoomquiet+js@gmail.com</p><p>点击邮件中的链接 不论 FF / Chrome 中,都无法完成验证! 报告错误如下:</p><p>{&quot;err&quot;:{ &quot;name&quot;:&quot;请求错误&quot; ,&quot;message&quot;:&quot;对不起，请求出错了！&quot; ,&quot;type&quot;:&quot;error&quot;,&quot;url&quot;:&quot;/&quot;} }</p><p>怀疑是不支持 gmail +缀形式邮箱 或是本身功能已经受损?</p>', '2013-04-29 13:42:38', '', null);
INSERT INTO `post` VALUES ('13', '7', '0', '<p>^O^<img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\" /></p>', '2013-04-29 22:29:30', '', null);

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
  `tagName` varchar(20) NOT NULL,
  `topicCount` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('2', 'js', '2');
INSERT INTO `tag` VALUES ('3', 'java', '1');
INSERT INTO `tag` VALUES ('4', 'ruby', '1');
INSERT INTO `tag` VALUES ('5', '屌丝', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('5', '0', '4', '1', '0', '0', '如何组织大型AngularJS应用中的代码？', null, 'js', '2013-04-29 13:33:38', '', '');
INSERT INTO `topic` VALUES ('6', '0', '4', '1', '0', '2', '哪位牛给解释下这段代码在干什么', null, 'js', '2013-04-29 13:36:53', '', '');
INSERT INTO `topic` VALUES ('7', '0', '4', '2', '0', '6', '无法完成邮箱认证?!', null, 'js', '2013-04-29 13:42:38', '', '');

-- ----------------------------
-- Table structure for `topic_tag`
-- ----------------------------
DROP TABLE IF EXISTS `topic_tag`;
CREATE TABLE `topic_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topicID` int(11) NOT NULL,
  `tagID` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic_tag
-- ----------------------------
INSERT INTO `topic_tag` VALUES ('1', '5', '2');
INSERT INTO `topic_tag` VALUES ('2', '5', '3');
INSERT INTO `topic_tag` VALUES ('3', '6', '2');
INSERT INTO `topic_tag` VALUES ('4', '6', '4');
INSERT INTO `topic_tag` VALUES ('5', '7', '2');
INSERT INTO `topic_tag` VALUES ('6', '7', '5');

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
