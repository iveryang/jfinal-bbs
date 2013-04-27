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
        render("/post/post.html");
    }

    /* ----------------------admin---------------------- */

    @Before(AdminInterceptor.class)
    public void save(){
        Post post = getModel(Post.class);
        post.mySave();
        redirect("/post/" + post.getStr("topicID"));
    }

    @Before(AdminInterceptor.class)
    public void edit(){
        setAttr("post", Post.dao.findById(getParaToInt(0)));
        render("/post/editPost.html");
    }

    @Before(AdminInterceptor.class)
    public void update(){
        Post post = getModel(Post.class);
        post.myUpdate();
        redirect("/post/" + post.getStr("topicID"));
    }
}
