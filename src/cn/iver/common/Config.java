package cn.iver.common;

import cn.iver.controller.*;
import cn.iver.controller.admin.AdminController;
import cn.iver.controller.admin.ModuleController;
import cn.iver.ext.beetl.BeetlRenderFactory;
import cn.iver.interceptor.GlobalInterceptor;
import cn.iver.model.*;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Created with IntelliJ IDEA.
 * 感谢 @波总 的JFinal，@闲.大赋 的beetl，向你们致敬！ ：）
 * 感谢 mike_liu 和 dream_lu，和众群友支持。
 * 如有问题，可以加 JFinal-BBS QQ群：206034609 讨论
 * 【4-3日，1.2版本】更新情况：
 * beetl和的JFinal都升级为最新版；加入了对自定义项目路径的支持；重构了部分代码；
 * 后续未来计划：
 * 取消module，用tag取代他；加入七牛存储支持；升级bootstrap版本；界面的改写；
 */
public class Config extends JFinalConfig {
    private boolean isLocal = isDevMode();

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        if (isLocal) {
            me.setDevMode(true);
        }
        me.setError404View("/common/404.html");
        me.setError500View("/common/500.html");
        me.setMainRenderFactory(new BeetlRenderFactory(isLocal));
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add("/", IndexController.class).add("/topic", TopicController.class);
        me.add("/post", PostController.class).add("/reply", ReplyController.class);
        me.add("/user", UserController.class);
        me.add("/admin", AdminController.class).add("/admin/module", ModuleController.class);
    }

    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        String jdbcUrl, user, password;
        if (isLocal) {
            jdbcUrl = Const.DEV_JDBC_URL;   user = Const.DEV_USER;      password = Const.DEV_PASSWORD;
        } else {
            jdbcUrl = Const.JDBC_URL;       user = Const.USER;          password = Const.PASSWORD;
        }
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl(jdbcUrl);     ds.setUser(user);       ds.setPassword(password);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(ds);
        if (isLocal) {
            arp.setShowSql(true);
        }
        arp.addMapping("module", Module.class).addMapping("topic", Topic.class).addMapping("post", Post.class);
        arp.addMapping("reply", Reply.class).addMapping("user", User.class);
        me.add(arp);
        me.add(new EhCachePlugin());
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {
        me.add(new SessionInViewInterceptor());
        me.add(new GlobalInterceptor());
    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {
        me.add(new ContextPathHandler("base"));
    }

    /**
     * 初始化常量
     */
    public void afterJFinalStart() { }

    private boolean isDevMode(){
        String osName = System.getProperty("os.name");
        return osName.indexOf("Windows") != -1;
    }

    public static void main(String[] args) throws Exception {
        JFinal.start("WebRoot", 80, "/bbs", 5);
    }
}