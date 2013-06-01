package cn.iver.controller;

import cn.iver.interceptor.AdminInterceptor;
import cn.iver.interceptor.LoginInterceptor;
import cn.iver.interceptor.PostValidator;
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
        Page<Post> postPage = Post.dao.getPage(topicID, pageNumber);
        setAttr("postPage", postPage);
        setAttr("topic", Topic.dao.get(topicID));
        render("/post/post.html");
    }

    @Before({LoginInterceptor.class, PostValidator.class})
    public void save(){
        Post post = getModel(Post.class);
        post.mySave();
        redirect("/post/" + post.getInt("topicID"));
    }

    @Before(AdminInterceptor.class)
    public void edit(){
        setAttr("post", Post.dao.get(getParaToInt(0)));
        render("/post/editPost.html");
    }

    @Before({AdminInterceptor.class, PostValidator.class})
    public void update(){
        Post post = getModel(Post.class);
        post.myUpdate();
        redirect("/post/" + post.getInt("topicID"));
    }
}
