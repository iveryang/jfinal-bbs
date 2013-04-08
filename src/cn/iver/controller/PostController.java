package cn.iver.controller;

import cn.iver.interceptor.AdminInterceptor;
import cn.iver.model.Post;
import cn.iver.model.Topic;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-3-30
 */
public class PostController extends Controller {
    public void index(){
        int topicID = getParaToInt(0);
        int pageNumber = getParaToInt(1, 1);
        Page<Post> postPage = Post.dao.getPostPage(topicID, pageNumber);
        setAttr("postPage", postPage);
        setAttr("topic", Topic.dao.getTopicByID(topicID));
        render("/user/post.html");
    }

    /* ----------------------admin---------------------- */

    @Before(AdminInterceptor.class)
    public void save(){
        int topicID = Post.dao.save(getModel(Post.class));
        redirect("/post/" + topicID);
    }

    @Before(AdminInterceptor.class)
    public void edit(){
        setAttr("post", Post.dao.findById(getParaToInt(0)));
        render("/admin/post.html");
    }

    @Before(AdminInterceptor.class)
    public void update(){
        int topicID = Post.dao.update(getModel(Post.class));
        redirect("/post/" + topicID);
    }
}
