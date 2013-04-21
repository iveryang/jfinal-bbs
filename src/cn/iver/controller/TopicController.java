package cn.iver.controller;

import cn.iver.interceptor.AdminInterceptor;
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
    public void show(){
        setAttr("topicPage", Topic.dao.getTopicPageForModule(getParaToInt(0), getParaToInt(1, 1)));
        render("/common/index.html");
    }
    public void add(){
        setAttr("moduleList", Module.dao.getModuleList());
        render("/topic/addTopic.html");
    }
    public void save(){
        Topic topic = getModel(Topic.class);
        Post post = getModel(Post.class);
        int topicID = Topic.dao.saveTopicAndPost(topic, post);
        redirect("/post/" + topicID);
    }

    /* ----------------------admin---------------------- */

    @Before(AdminInterceptor.class)
    public void showTopicList(){
        setAttr("unPublishedTopicList", Topic.dao.getAllUnPublishedTopic());
        setAttr("topicPage", Topic.dao.getTopicPageForAdmin(getParaToInt(0, 1)));
        render("/admin/topicList.html");
    }
    @Before(AdminInterceptor.class)
    public void edit(){
        Topic topic = Topic.dao.findById(getParaToInt(0));
        setAttr("topic", topic);
        render("/admin/editTopic.html");
    }
    @Before(AdminInterceptor.class)
    public void update(){
        Topic topic = getModel(Topic.class);
        int topicID = Topic.dao.updateTopic(topic);
        redirect("/post/" + topicID);
    }
}
