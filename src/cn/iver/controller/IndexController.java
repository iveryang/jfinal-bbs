package cn.iver.controller;

import cn.iver.interceptor.UserValidator;
import cn.iver.model.Topic;
import cn.iver.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-26
 */
public class IndexController extends Controller {
    public void index(){
        setAttr("topicPage", Topic.dao.getTopicPage(getParaToInt(0, 1)));
        render("/common/index.html");
    }
    public void leaveMsg(){
        render("/common/leaveMsg.html");
    }
    public void regist(){
        render("/user/regist.html");
    }
    public void test(){
        render("/common/test.html");
    }

    @Before(UserValidator.class)
    public void newUser(){
        User user = getModel(User.class);
        user.createNewUser();
        setSessionAttr("user", user);
        redirect("/");
    }
}
