###采用JFinal和Beetl开发的一个简单的BBS，她的名字叫“JFinal-BBS(beta1.0)” ^O^

希望她作为一个JFinal、Beetl入门的DEMO，可以帮到新人。

最后，向开源前辈们致敬。

### 部署完毕的效果地址是：http://iver.cloudfoundry.com （一个屌丝论坛）

### 运行方法：
1.导入bbs.sql到mysql中；

2.配置一下config.txt（配置项都有注释的），注：config文件夹必须设置为项目的源码目录，
以便编译后所有config文件夹下的配置文件可以被自动放到WEB-INF下的classes目录；

3.运行MyConfig.java里的main方法即可；

#### 现在JFinal-BBS还有很多地方有待完善，我会不断的完善她。如果有建议：可以到 http://iver.cloudfoundry.com/leaveMsg 给我留言：）

### beta1.1
修复了几处bug；重构了部分model代码，配合ehcache更省代码，更加方便读取数据；取消了游客的留言权限；增强了后台管理；