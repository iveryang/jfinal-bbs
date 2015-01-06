### 采用[JFinal](https://github.com/jfinal/jfinal), [Beetl](http://ibeetl.com)/[Rythm](http://rythmengine.org)开发的一个简单的BBS， ^O^

希望它作为一个JFinal, Beetl和Rythm入门的DEMO，可以帮到新人。

最后，向开源前辈们致敬。

### 常见问题：
#### 如何运行：导入sql，配置相应的参数（Const.java），运行Config.java里的main方法即可；
#### 如何进入管理员后台：配置一下Const.java里的ADMIN_EMAIL，比如配置为xiaoming@qq.com，之后注册一个该账户的用户，再登录"项目名称/admin"这个URL即可。

### 1.1
修复了几处bug；增加了外键和删除级联；重构了部分model代码，配合ehcache更省代码，更加方便读取数据；取消了游客的留言权限；增加了后台管理的操作；

### 1.2
修复了小bug；加入了jsoup以增强xss攻击过滤；加入了自定义路径的支持；

### 关于jfinal

* 项目站点：http://www.jfinal.com/

### 关于Beetl

* 项目站点：http://ibeetl.com/
* 交互式体验：http://www.ibeetl.com/beetlonline
* 源代码：https://github.com/javamonkey/beetl1.2

### 关于Rythm

* 项目站点：http://rythmengine.org
* 交互式体验：http://fiddle.rythmengine.org
* 源代码：https://github.com/greenlaw110/rythm
