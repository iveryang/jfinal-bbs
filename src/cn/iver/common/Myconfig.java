package cn.iver.common;

import cn.iver.controller.admin.ModuleController;
import cn.iver.controller.IndexController;
import cn.iver.controller.PostController;
import cn.iver.controller.ReplyController;
import cn.iver.controller.TopicController;
import cn.iver.controller.admin.WelcomeController;
import cn.iver.interceptor.GlobalInterceptor;
import cn.iver.kit.BeetlFunctionKit;
import cn.iver.model.*;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.StringKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import org.bee.tl.core.GroupTemplate;
import org.bee.tl.ext.jfinal.BeetlRenderFactory;

import java.util.TimeZone;

/**
 * 感谢对开源热心的少年们提供的各种代码，这个DD是站在巨人的肩膀上的
 * 感谢 @波总 的JFinal，@闲.大赋 的beetl，向你们致敬！ ：）
 */
public class Myconfig extends JFinalConfig {
    private String json = java.lang.System.getenv("VCAP_SERVICES");
    private boolean isLocal = StringKit.isBlank(json);
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		loadPropertyFile("classes/config.txt");
        if (isLocal) {
            me.setDevMode(true);
        }
        me.setError404View("/common/404.html");
        me.setError500View("/common/500.html");
		me.setMainRenderFactory(new BeetlRenderFactory());
		GroupTemplate gt = BeetlRenderFactory.groupTemplate;
        gt.registerFunction("isSame", new BeetlFunctionKit());
		gt.setStatementStart("@");
        gt.setStatementEnd(null);
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
        me.add("/", IndexController.class).add("/topic", TopicController.class);
        me.add("/post", PostController.class).add("/reply", ReplyController.class);
        me.add("/admin/welcome", WelcomeController.class).add("/admin/module", ModuleController.class);
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// [ copy from @ mike 的适配器 ：） ]
        String dbname, username, password, host, port, driver;
        driver = getProperty("driverClass");
        if (isLocal) {
            dbname = getProperty("dbname");     username = getProperty("username");     port = getProperty("port");
            host = getProperty("host");         password = getProperty("password");
        } else {
            JSONObject credentials = JSONObject.parseObject(json).getJSONArray("mysql-5.1")
                    .getJSONObject(0).getJSONObject("credentials");
            host = credentials.getString("host");
            port = credentials.getString("port");
            dbname = credentials.getString("name");
            username = credentials.getString("username");
            password = credentials.getString("password");
        }
        DruidPlugin druidPlugin = new DruidPlugin("jdbc:mysql://" + host + ":" + port + "/" + dbname, username, password, driver);
        druidPlugin.setInitialSize(3).setMaxActive(10);
        me.add(druidPlugin);
        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        if (isLocal){
            arp.setShowSql(true);
        }
        arp.addMapping("module", Module.class).addMapping("topic", Topic.class).addMapping("post", Post.class);
        arp.addMapping("reply", Reply.class).addMapping("user", User.class).addMapping("tag", Tag.class);
        arp.addMapping("topic_tag", TopicTag.class);
		me.add(arp);
        // 缓存插件
        me.add(new EhCachePlugin());
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
        //支持在jfinal中用sesion
        me.add(new SessionInViewInterceptor());
        me.add(new GlobalInterceptor());
    }
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}

    /**
     * 初始化常量
     */
    public void afterJFinalStart(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        MyConstants.PAGE_SIZE = getPropertyToInt("page_size", 10);
        MyConstants.PAGE_SIZE_OF_REPLY = getPropertyToInt("page_size_of_reply", 3);
        MyConstants.SIDEBAR_TOPIC_SIZE = getPropertyToInt("sidebar_topic_size", 6);
        MyConstants.PAGE_SIZE_FOR_ADMIN = getPropertyToInt("page_size_for_admin", 30);
    }
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) throws Exception{
		JFinal.start("Web", 80, "/", 5);
	}
}
