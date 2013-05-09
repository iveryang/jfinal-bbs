/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2013-05-09 17:25:50
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('4', '屌丝一起来 ⊙ω⊙', null, '1');
INSERT INTO `module` VALUES ('5', '技术专区 (ΘｏΘ)', null, '2');
INSERT INTO `module` VALUES ('6', 'github ：）', null, '3');

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '1', '1', '222', '2013-04-26 20:04:11', '', null);
INSERT INTO `post` VALUES ('2', '1', '1', '<p>最近想在产品中加入即时通讯的功能.BS架构的程序.实现方式不外乎两大标准下的各种奇淫技巧.</p><p>这两大标准就是 HTML5 HTML4</p><p>为啥这两个呢..因为HTML5里面有websocket.这个彻底颠覆http请求的东西,使得请求不再是无状态的.</p><p>当然websocket目前支持不是很好.也没办法.看着好东西没法用.这是一种何种的煎熬....搞得我总是想在产品里面内嵌chromeFrame.然后强制给客户装上.哈哈...当然客户没准会和我拼命呢...</p><p>没办法,在现有的需求中基本上,实现思路只有一个了.也就是第一个让我头疼了一阵的关键词</p><p><br /></p>', '2013-04-26 20:28:50', '', null);
INSERT INTO `post` VALUES ('3', '2', '1', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0001.gif\" />^O^</p>', '2013-04-27 21:14:15', '', null);
INSERT INTO `post` VALUES ('4', '1', '1', '<p>^O^<img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\" /></p>', '2013-04-27 22:02:32', '', null);
INSERT INTO `post` VALUES ('5', '1', '1', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\" />^O^</p>', '2013-04-27 22:03:56', '', null);
INSERT INTO `post` VALUES ('6', '1', '1', '<p>^O^<img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\" /></p>', '2013-04-27 22:04:10', '', null);
INSERT INTO `post` VALUES ('7', '2', '1', '<p>^O^<img src=\"http://img.baidu.com/hi/jx2/j_0017.gif\" /></p>', '2013-04-28 21:10:33', '', null);
INSERT INTO `post` VALUES ('8', '3', '1', '<p>沧海一声笑　滔滔两岸潮<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />浮沉随浪只记今朝<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />苍天笑　纷纷世上潮<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />谁负谁胜出天知晓<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />江山笑　烟雨遥<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />涛浪淘尽红尘俗世几多娇<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />清风笑　竟惹寂寥<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />豪情还剩了一襟晚照<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />苍生笑　不再寂寥<br style=\"font-family:微软雅黑;color:#333333;font-size:13.63636302947998px;line-height:20px;background-color:#ffffff;\" />豪情仍在痴痴笑笑</p>', '2013-04-28 21:58:32', '', null);
INSERT INTO `post` VALUES ('9', '4', '1', '<p>本文作者Cliff Meyers是一个前端工程师，熟悉HTML5、JavaScript、J2EE开发，他在开发过程中总结了自己在应对JavaScript应用越来越庞大情况下的文件结构，深得其他开发者认可。以下为CSDN编译： 地板上堆放的衣服 首先，我们来看看angular-seed，它是AngularJS应用开发的官方入门项目，其文件结构是这样的：</p>', '2013-04-29 13:27:14', '', null);
INSERT INTO `post` VALUES ('10', '5', '1', '<p>本文作者Cliff Meyers是一个前端工程师，熟悉HTML5、JavaScript、J2EE开发，他在开发过程中总结了自己在应对JavaScript应用越来越庞大情况下的文件结构，深得其他开发者认可。以下为CSDN编译： 地板上堆放的衣服 首先，我们来看看angular-seed，它是AngularJS应用开发的官方入门项目，其文件结构是这样的：</p>', '2013-04-29 13:33:39', '', null);
INSERT INTO `post` VALUES ('11', '6', '1', '<p>我自己定义了一个指令text-tile 可以把一个普通的html变成一个metro风格的瓷贴（主要是做一些样式的计算） angular.module(&quot;metro.directive&quot;, []) .directive(&quot;textTile&quot;, function () { return { templateUrl: &#39;tpl/tile_text.html&#39;, transclude: true, link: function (scope, element, attrs) { var css …</p>', '2013-04-29 13:36:53', '', null);
INSERT INTO `post` VALUES ('12', '7', '1', '<p>俺注册的邮箱是 zoomquiet+js@gmail.com</p><p>点击邮件中的链接 不论 FF / Chrome 中,都无法完成验证! 报告错误如下:</p><p>{&quot;err&quot;:{ &quot;name&quot;:&quot;请求错误&quot; ,&quot;message&quot;:&quot;对不起，请求出错了！&quot; ,&quot;type&quot;:&quot;error&quot;,&quot;url&quot;:&quot;/&quot;} }</p><p>怀疑是不支持 gmail +缀形式邮箱 或是本身功能已经受损?</p>', '2013-04-29 13:42:38', '', null);
INSERT INTO `post` VALUES ('13', '7', '1', '<p>^O^<img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\" /></p>', '2013-04-29 22:29:30', '', null);
INSERT INTO `post` VALUES ('14', '6', '1', '<p>^O^<img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\" /></p>', '2013-05-06 16:28:01', '', null);
INSERT INTO `post` VALUES ('15', '8', '1', '<p>这是一个用来在浏览器上设计流程图的UI组件，基于Jquery开发。可用来设计各种流程图、逻辑流图，数据流图，或者是设计某个系统中需要走流程的功能应用。良好的用户体验使得操作界面很容易上手，不仅二次开发人员可用，最终用户也能用。 跨浏览器，可兼容IE7--IE10， FireFox， Chrome， Opera等。 本插件只包括前台UI,用在任何一种需要流程图的B/S系统应用上，流程图的详细实现逻辑完全交于后台程序开发者自己实现;对于后台,只要能返回/接收被本插件解析的JSON格式数据即可.所以本插件可用于各种JAVA，PHP，.net服务器上.</p>', '2013-05-06 17:35:20', '', null);
INSERT INTO `post` VALUES ('16', '9', '1', '<p>Rails4 在前天的 <a href=\"http://www.railsconf.com/\">RailsConf 2013</a> 釋出 <a href=\"http://weblog.rubyonrails.org/2013/5/1/Rails-4-0-release-candidate-1/\">Rails 4.0 RC1</a> 了，這也表示大家應該可以進場了。</p><p>上個月在 Rails 4.0 beta1 時為了練手感，把手上的一個中小 production 專案，也上了 rails4 branch。</p><p>大概有幾個感想：</p><ul style=\"font-family:微软雅黑;padding:5px 14px;margin:0px;list-style-position:inside;list-style-image:initial;color:#333333;font-size:14.399999618530273px;line-height:22.399999618530273px;background-color:#ffffff;\"><li><p><a href=\"http://www.upgradingtorails4.com/\">Upgrading to Rails4</a> 這本書強烈建議要買，才 $15 USD，可以節省你不少 debug 時間。</p></li><li><p>升 Rails4 建議不只開 branch，也用 rvm 開一個 gemset 出來作，因為 gem dependency 變更蠻多的。</p></li></ul><p><br /></p>', '2013-05-07 18:04:46', '', null);
INSERT INTO `post` VALUES ('17', '10', '1', '<p>Rails4 在前天的 <a href=\"http://www.railsconf.com/\">RailsConf 2013</a> 釋出 <a href=\"http://weblog.rubyonrails.org/2013/5/1/Rails-4-0-release-candidate-1/\">Rails 4.0 RC1</a> 了，這也表示大家應該可以進場了。</p><p>上個月在 Rails 4.0 beta1 時為了練手感，把手上的一個中小 production 專案，也上了 rails4 branch。</p><p>大概有幾個感想：</p><ul style=\"font-family:微软雅黑;padding:5px 14px;margin:0px;list-style-position:inside;list-style-image:initial;color:#333333;font-size:14.399999618530273px;line-height:22.399999618530273px;background-color:#ffffff;\"><li><p><a href=\"http://www.upgradingtorails4.com/\">Upgrading to Rails4</a> 這本書強烈建議要買，才 $15 USD，可以節省你不少 debug 時間。</p></li><li><p>升 Rails4 建議不只開 branch，也用 rvm 開一個 gemset 出來作，因為 gem dependency 變更蠻多的。</p></li></ul><p><br /></p>', '2013-05-07 18:08:53', '', null);
INSERT INTO `post` VALUES ('18', '11', '0', '<p>^O^</p><p>上個月在 Rails 4.0 beta1 時為了練手感，把手上的一個中小 production 專案，也上了 rails4 branch。</p><p>大概有幾個感想：</p><p><img src=\"http://img.baidu.com/hi/jx2/j_0015.gif\" /></p>', '2013-05-07 18:13:32', '', null);
INSERT INTO `post` VALUES ('19', '6', '0', '<p><span style=\"color: rgb(51, 51, 51); font-family: 微软雅黑; font-size: 14.399999618530273px; line-height: 20px; text-align: center; background-color: rgb(255, 255, 255);\">  CopyRight -- </span><a href=\"http://my.oschina.net/iveryang\" target=\"_blank\" style=\"font-family: 微软雅黑; color: rgb(0, 136, 204); text-decoration: none; font-size: 14.399999618530273px; line-height: 20px; text-align: center; white-space: normal; background-color: rgb(255, 255, 255);\">紫电清霜</a><span style=\"color: rgb(51, 51, 51); font-family: 微软雅黑; font-size: 14.399999618530273px; line-height: 20px; text-align: center; background-color: rgb(255, 255, 255);\"> &nbsp; 201</span><br></p>', '2013-05-08 14:56:06', '', null);
INSERT INTO `post` VALUES ('20', '5', '0', '<P>^O^<IMG src=\"http://img.baidu.com/hi/jx2/j_0001.gif\" data_ue_src=\"http://img.baidu.com/hi/jx2/j_0001.gif\"></P>', '2013-05-08 21:57:11', '', null);
INSERT INTO `post` VALUES ('21', '12', '9', '<P>^O^<IMG src=\"http://img.baidu.com/hi/jx2/j_0027.gif\" data_ue_src=\"http://img.baidu.com/hi/jx2/j_0027.gif\"></P>', '2013-05-08 22:00:54', '', null);
INSERT INTO `post` VALUES ('22', '12', '0', '<P>^O^<IMG src=\"http://img.baidu.com/hi/jx2/j_0003.gif\" data_ue_src=\"http://img.baidu.com/hi/jx2/j_0003.gif\"></P>', '2013-05-08 22:01:33', '', null);
INSERT INTO `post` VALUES ('23', '12', '9', '<P>^O^<IMG src=\"http://img.baidu.com/hi/jx2/j_0001.gif\" data_ue_src=\"http://img.baidu.com/hi/jx2/j_0001.gif\"></P>', '2013-05-08 22:04:17', '', null);
INSERT INTO `post` VALUES ('24', '13', '1', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\" data_ue_src=\"http://img.baidu.com/hi/jx2/j_0002.gif\">^O^</p>', '2013-05-09 15:01:15', '', null);
INSERT INTO `post` VALUES ('25', '14', '1', '<p>^O^<img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\" data_ue_src=\"http://img.baidu.com/hi/jx2/j_0003.gif\"></p>', '2013-05-09 17:01:00', '', null);
INSERT INTO `post` VALUES ('26', '15', '9', '<p>^O^我爱你<img src=\"http://img.baidu.com/hi/jx2/j_0019.gif\" data_ue_src=\"http://img.baidu.com/hi/jx2/j_0019.gif\"></p>', '2013-05-09 17:02:29', '', null);

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topicID` int(11) NOT NULL DEFAULT '1',
  `postID` int(11) NOT NULL,
  `userID` int(11) NOT NULL DEFAULT '0',
  `content` varchar(300) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('68', '6', '11', '1', '我自己定义了一个指令', '2013-05-06 10:51:40');
INSERT INTO `reply` VALUES ('69', '6', '11', '1', '&lt;script&gt;', '2013-05-06 11:16:12');
INSERT INTO `reply` VALUES ('70', '6', '11', '1', 'function (scope, element, attrs)', '2013-05-06 11:27:54');
INSERT INTO `reply` VALUES ('71', '6', '11', '1', '定义了一个指令', '2013-05-06 11:29:12');
INSERT INTO `reply` VALUES ('72', '6', '11', '1', '定义了一个指令dd', '2013-05-06 11:29:28');
INSERT INTO `reply` VALUES ('73', '6', '11', '1', '定义了一个指令', '2013-05-06 11:29:33');
INSERT INTO `reply` VALUES ('74', '6', '11', '1', 'function ', '2013-05-06 15:33:29');
INSERT INTO `reply` VALUES ('75', '6', '11', '1', '回复@管理员: ^O^', '2013-05-06 15:35:40');
INSERT INTO `reply` VALUES ('76', '6', '11', '1', '回复@管理员: 指令指令~', '2013-05-06 16:08:22');
INSERT INTO `reply` VALUES ('77', '6', '11', '1', '回复@管理员: 5小时前', '2013-05-06 16:19:55');
INSERT INTO `reply` VALUES ('78', '6', '11', '1', '回复@管理员: var css', '2013-05-06 16:23:26');
INSERT INTO `reply` VALUES ('79', '6', '11', '1', '回复@管理员: 不超过200字', '2013-05-06 16:27:08');
INSERT INTO `reply` VALUES ('80', '6', '11', '1', 'scope', '2013-05-08 14:56:51');
INSERT INTO `reply` VALUES ('81', '6', '11', '1', '回复@管理员: 呵呵~', '2013-05-08 14:57:32');
INSERT INTO `reply` VALUES ('82', '6', '14', '1', 'CopyRight ', '2013-05-08 18:20:59');
INSERT INTO `reply` VALUES ('83', '6', '14', '1', '回复@管理员: 嘻嘻嘻', '2013-05-08 18:21:08');
INSERT INTO `reply` VALUES ('84', '6', '19', '0', '我特么是来看看的', '2013-05-08 21:17:49');
INSERT INTO `reply` VALUES ('85', '6', '19', '0', '看看的', '2013-05-08 21:50:56');
INSERT INTO `reply` VALUES ('86', '6', '19', '0', '回复@游客啊: 刚刚的', '2013-05-08 21:52:06');
INSERT INTO `reply` VALUES ('87', '5', '10', '9', '它是AngularJS', '2013-05-08 21:56:33');
INSERT INTO `reply` VALUES ('88', '5', '10', '9', '回复@xxoo哦: rJS', '2013-05-08 21:56:44');
INSERT INTO `reply` VALUES ('89', '15', '26', '1', 'Welcome！：）', '2013-05-09 17:03:11');
INSERT INTO `reply` VALUES ('91', '15', '26', '9', '回复@管理员: Welcome！：）Welcome！：）', '2013-05-09 17:05:25');
INSERT INTO `reply` VALUES ('93', '15', '26', '0', '回复@xxoo哦: Welcome！：）', '2013-05-09 17:11:54');

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
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isNice` bit(1) NOT NULL DEFAULT b'0',
  `isUp` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('5', '1', '4', '2', '0', '5', '如何组织大型AngularJS应用中的代码？', null, '2013-04-29 13:33:38', '', '');
INSERT INTO `topic` VALUES ('6', '1', '4', '3', '0', '158', '哪位牛给解释下这段代码在干什么', null, '2013-04-29 13:36:53', '', '');
INSERT INTO `topic` VALUES ('7', '1', '4', '2', '0', '10', '无法完成邮箱认证?!', null, '2013-03-29 13:42:38', '', '');
INSERT INTO `topic` VALUES ('8', '1', '5', '1', '0', '0', '在线流程图设计器GooFlow', null, '2013-05-06 17:35:19', '', '');
INSERT INTO `topic` VALUES ('9', '1', '4', '1', '0', '1', 'Upgrade 到 Rails4 的一些感想', null, '2013-05-07 18:04:45', '', '');
INSERT INTO `topic` VALUES ('10', '1', '4', '1', '0', '20', 'Upgrade 到 Rails4 的一些感想', null, '2013-05-07 18:08:52', '', '');
INSERT INTO `topic` VALUES ('11', '0', '4', '1', '0', '3', 'Upgrade 到 Rails4 ', null, '2013-05-07 18:13:32', '', '');
INSERT INTO `topic` VALUES ('12', '9', '4', '3', '0', '8', '我去去就来', null, '2013-05-08 22:00:54', '', '');
INSERT INTO `topic` VALUES ('13', '1', '4', '1', '0', '6', '题不能为空且长度不超过5000', null, '2013-05-09 15:01:15', '', '');
INSERT INTO `topic` VALUES ('14', '1', '5', '1', '0', '3', 'CSS选择器笔记', null, '2013-05-09 17:01:00', '', '');
INSERT INTO `topic` VALUES ('15', '9', '4', '1', '0', '8', 'love you', null, '2013-05-09 17:02:29', '', '');

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
  `headImg` varchar(120) DEFAULT 'http://static.tieba.baidu.com/tb/editor/images/ali/ali_008.gif',
  `blogUrl` varchar(80) DEFAULT NULL,
  `feeling` varchar(300) DEFAULT NULL,
  `registDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0', '游客啊', 'bfd59291e825b5f2bbf1eb76555f8fe7', '', 'iver@qq.com', null, null, null, '2013-05-06');
INSERT INTO `user` VALUES ('1', '管理员', 'bfd59291e825b5f2bbf1eb76555f8fe7', '', 'iveryang@sina.cn', 'http://tb.himg.baidu.com/sys/portrait/item/5b076976657279616e67303037303038f306', 'http://iver.cloudfoundry.com', null, '2013-04-10');
INSERT INTO `user` VALUES ('5', '呵呵', 'bfd59291e825b5f2bbf1eb76555f8fe7', '', 'iveryang@sina.c', null, null, null, '2013-05-08');
INSERT INTO `user` VALUES ('6', '呵呵呵', 'bfd59291e825b5f2bbf1eb76555f8fe7', '', 'iveryxx@sina.cn', null, null, null, '2013-05-08');
INSERT INTO `user` VALUES ('7', '称称称', 'bfd59291e825b5f2bbf1eb76555f8fe7', '', 'iveryag@sina.cn', null, null, null, '2013-05-08');
INSERT INTO `user` VALUES ('8', 'xx', 'bfd59291e825b5f2bbf1eb76555f8fe7', '', 'xx@qq.com', 'http://static.tieba.baidu.com/tb/editor/images/ali/ali_008.gif', null, null, '2013-05-08');
INSERT INTO `user` VALUES ('9', 'xxoo哦', 'bfd59291e825b5f2bbf1eb76555f8fe7', '', 'xxoo@qq.com', 'http://static.tieba.baidu.com/tb/editor/images/ali/ali_008.gif', null, '基情提', '2013-05-08');
