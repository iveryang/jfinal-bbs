package cn.iver.common;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-28
 */
public class Const {
    // page size
    public static int TOPIC_PAGE_SIZE = 12;     // 首页帖子分页大小
    public static int POST_PAGE_SIZE = 8;       // 跟帖分页大小
    public static int REPLY_PAGE_SIZE = 5;      // 帖子回复分页大小
    public static int PAGE_SIZE_FOR_ADMIN = 30; // 管理员后台（查看帖子，回帖，跟帖）的分页大小
    // others
    public static String ADMIN_EMAIL = "XXOO@sina.cn";  // 注册一个该email，登陆，即可成为管理员
    public static String BBS_ID_SEPARATOR = "###";
    public static String TIMESTAMP = System.currentTimeMillis() + "";
    // 本地开发环境上 data source 的配置
    public static String DEV_JDBC_URL = "jdbc:mysql://localhost:3306/bbs";
    public static String DEV_USER = "root";
    public static String DEV_PASSWORD = "XXOO";
    // 运行环境上 data source 的配置
    public static String JDBC_URL = "XXOO";
    public static String USER = "XXOO";
    public static String PASSWORD = "XXOO";
    // beetl
    public static String BEETL_ROOT_DIR = "/WEB-INF/beetl";

}
