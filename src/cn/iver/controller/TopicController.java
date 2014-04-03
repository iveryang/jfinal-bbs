package cn.iver.controller;

import cn.iver.interceptor.AdminInterceptor;
import cn.iver.interceptor.LoginInterceptor;
import cn.iver.model.Post;
import cn.iver.model.Topic;
import cn.iver.validator.PostValidator;
import cn.iver.validator.TopicValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 */
public class TopicController extends Controller {
    public void index(){
        forwardAction("/post/" + getParaToInt(0));
    }
    public void module(){
        setAttr("topicPage", Topic.dao.getPageForModule(getParaToInt(0), getParaToInt(1, 1)));
        setAttr("actionUrl", "/topic/module/" + getParaToInt(0) + "-");
        render("/common/index.html");
    }
    public void hot(){
        setAttr("topicPage", Topic.dao.getHotPage(getParaToInt(0, 1)));
        setAttr("actionUrl", "/topic/hot/");
        render("/common/index.html");
    }
    public void nice(){
        setAttr("topicPage", Topic.dao.getNicePage(getParaToInt(0, 1)));
        setAttr("actionUrl", "/topic/nice/");
        render("/common/index.html");
    }

    @Before(LoginInterceptor.class)
    public void add(){
        render("/topic/add.html");
    }

    @Before({LoginInterceptor.class, TopicValidator.class, PostValidator.class})
    public void save(){
        Topic topic = getModel(Topic.class);
        topic.set("userID", getSessionAttr("userID")).set("id", null);
        Post post = getModel(Post.class);
        post.set("userID", getSessionAttr("userID"));
        topic.save(post);
        redirect("/post/" + topic.getInt("id"));
    }

    @Before(AdminInterceptor.class)
    public void edit(){
        Topic topic = Topic.dao.get(getParaToInt(0));
        setAttr("topic", topic);
        render("/topic/edit.html");
    }

    @Before({AdminInterceptor.class, TopicValidator.class})
    public void update(){
        getModel(Topic.class).myUpdate();
        redirect("/post/" + getParaToInt("topic.id"));
    }

    @Before(AdminInterceptor.class)
    public void delete(){
        Topic.dao.deleteByID(getParaToInt(0));
        forwardAction("/admin/topicList/" + getParaToInt(1));
    }
}
