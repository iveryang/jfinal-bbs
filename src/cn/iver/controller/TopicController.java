package cn.iver.controller;

import cn.iver.interceptor.AdminInterceptor;
import cn.iver.interceptor.LoginInterceptor;
import cn.iver.interceptor.PostValidator;
import cn.iver.interceptor.TopicValidator;
import cn.iver.model.Module;
import cn.iver.model.Post;
import cn.iver.model.Topic;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-28
 */
public class TopicController extends Controller {
    public void index(){
        forwardAction("/post/" + getParaToInt(0));
    }
    public void module(){
        setAttr("topicPage", Topic.dao.getTopicPageForModule(getParaToInt(0), getParaToInt(1, 1)));
        setAttr("actionUrl", "/topic/module/" + getParaToInt(0) + "-");
        render("/common/index.html");
    }
    public void hotTopic(){
        setAttr("topicPage", Topic.dao.getHotTopicPage(getParaToInt(0, 1)));
        setAttr("actionUrl", "/topic/hotTopic/");
        render("/common/index.html");
    }
    public void niceTopic(){
        setAttr("topicPage", Topic.dao.getNiceTopicPage(getParaToInt(0, 1)));
        setAttr("actionUrl", "/topic/niceTopic/");
        render("/common/index.html");
    }

    @Before(LoginInterceptor.class)
    public void add(){
        render("/topic/addTopic.html");
    }

    @Before({LoginInterceptor.class, TopicValidator.class, PostValidator.class})
    public void save(){
        Topic topic = getModel(Topic.class);
        topic.mySave(getModel(Post.class));
        redirect("/post/" + topic.getInt("id"));
    }

    @Before(AdminInterceptor.class)
    public void edit(){
        Topic topic = Topic.dao.getTopic(getParaToInt(0));
        setAttr("topic", topic);
        render("/topic/editTopic.html");
    }

    @Before({AdminInterceptor.class, TopicValidator.class})
    public void update(){
        Topic topic = getModel(Topic.class);
        topic.myUpdate();
        redirect("/post/" + topic.getInt("id"));
    }
}
