package cn.iver.controller.admin;

import cn.iver.interceptor.AdminInterceptor;
import cn.iver.model.Post;
import cn.iver.model.Reply;
import cn.iver.model.Topic;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 */
@Before(AdminInterceptor.class)
public class AdminController extends Controller {
    public void index(){
        redirect("/admin/topicList");
    }
    public void topicList(){
        setAttr("topicPage", Topic.dao.getPageForAdmin(getParaToInt(0, 1)));
        render("/admin/topicList.html");
    }
    public void postList(){
        setAttr("postPage", Post.dao.getPageForAdmin(getParaToInt(0, 1)));
        render("/admin/postList.html");
    }
    public void replyList(){
        setAttr("replyPage", Reply.dao.getPageForAdmin(getParaToInt(0, 1)));
        render("/admin/replyList.html");
    }
}
