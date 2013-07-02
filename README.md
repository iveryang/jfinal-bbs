### 采用[JFinal](https://github.com/jfinal/jfinal), [Beetl](http://ibeetl.com)/[Rythm](http://rythmengine.org)开发的一个简单的BBS，她的名字叫“JFinal-BBS” ^O^

希望她作为一个JFinal, Beetl和Rythm入门的DEMO，可以帮到新人。

最后，向开源前辈们致敬。

### 部署完毕的效果地址是：http://jfinalbbs.duapp.com/ （一个屌丝论坛）

### 运行方法：

1 导入bbs.sql到mysql中

2 配置一下config.txt（配置项都有注释的），注：config文件夹必须设置为项目的源码目录，
以便编译后所有config文件夹下的配置文件可以被自动放到WEB-INF下的classes目录；

2.1 设置模版引擎：

```
view.engine=beetl #使用beetl
view.engine=rythm #使用rythm
```

2.2 设置Rythm引擎：

```
rythm.home.template=../rythm #设置rythm引擎的模版根目录
rythm.i18n.enabled=true #允许国际化。参见http://www.screenr.com/T7NH
```

2.3 如果使用Rythm模版运行时需要将`config/`下面的`messages.*.properties`文件拷贝的哦WEB-INF下面的classes里面

3.运行MyConfig.java里的main方法即可；

#### 现在JFinal-BBS还有很多地方有待完善，我会不断的完善她。如果有建议：可以到 http://iver.cloudfoundry.com/leaveMsg 给我留言：）

### beta1.1
修复了几处bug；重构了部分model代码，配合ehcache更省代码，更加方便读取数据；取消了游客的留言权限；增强了后台管理；

### 关于Beetl

* 项目站点：http://ibeetl.com/
* 交互式体验：http://www.ibeetl.com/beetlonline
* 源代码：https://github.com/javamonkey/beetl1.2

### 关于Rythm

* 项目站点：http://rythmengine.org
* 交互式体验：http://fiddle.rythmengine.org
* 源代码：https://github.com/greenlaw110/rythm
