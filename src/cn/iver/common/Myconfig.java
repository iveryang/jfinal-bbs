package cn.iver.common;

import cn.iver.controller.*;
import cn.iver.controller.admin.AdminController;
import cn.iver.controller.admin.ModuleController;
import cn.iver.ext.beetlfunction.IsSame;
import cn.iver.ext.beetlfunction.PrintTime;
import cn.iver.interceptor.GlobalInterceptor;
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
        gt.registerFunction("isSame", new IsSame());
        gt.registerFunction("printTime", new PrintTime());
		gt.setStatementStart("@");
        gt.setStatementEnd(null);
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
        me.add("/", IndexController.class).add("/topic", TopicController.class);
        me.add("/post", PostController.class).add("/reply", ReplyController.class);
        me.add("/user", UserController.class);
        me.add("/admin/welcome", AdminController.class).add("/admin/module", ModuleController.class);
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
        String jdbcUrl, username, password, driver;
        driver = getProperty("driverClass");
        if (isLocal) {
            jdbcUrl = getProperty("jdbcUrl");     username = getProperty("username");   password = getProperty("password");
        } else {
            JSONObject json = JSONObject.parseObject(this.json).getJSONArray("mysql-5.1").getJSONObject(0).getJSONObject("credentials");
            username = json.getString("username");      password = json.getString("password");
            jdbcUrl = "jdbc:mysql://" + json.getString("host") + ":" + json.getString("port") + "/" + json.getString("name");
        }
        DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, username, password, driver);
        druidPlugin.setInitialSize(3).setMaxActive(10);
        me.add(druidPlugin);
        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        if (isLocal){
            arp.setShowSql(true);
        }
        arp.addMapping("module", Module.class).addMapping("topic", Topic.class).addMapping("post", Post.class);
        arp.addMapping("reply", Reply.class).addMapping("user", User.class);
		me.add(arp);
        // 缓存插件
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
	public void configHandler(Handlers me) {}

    /**
     * 初始化常量
     */
    public void afterJFinalStart(){
        MyConstants.TOPIC_PAGE_SIZE = getPropertyToInt("topic_page_size", 12);
        MyConstants.POST_PAGE_SIZE = getPropertyToInt("post_page_size", 8);
        MyConstants.REPLY_PAGE_SIZE = getPropertyToInt("reply_page_size", 5);
        MyConstants.SIDEBAR_TOPIC_SIZE = getPropertyToInt("sidebar_topic_size", 10);
        MyConstants.ADMIN_EMAIL = getProperty("admin_email");
    }
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     * 第一个参数填写的是“WEB-INF”文件夹的父文件夹名称
     * 第二个参数是设置访问的端口号
     * 第三个参数是设置该项目的访问根目录
     * 第四个参数是设置jetty每隔几秒钟扫描文件变化并重启应用
	 */
	public static void main(String[] args) throws Exception{
		JFinal.start("web", 80, "/", 5);
	}
}
