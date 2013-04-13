package cn.iver.controller;

import cn.iver.model.Topic;
import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-26
 */
public class IndexController extends Controller {
    public void index (){
        setAttr("topicPage", Topic.dao.getTopicPage(getParaToInt(0, 1)));
        render("/common/index.html");
    }
    public void leaveMsg (){
        render("/user/leaveMsg.html");
    }
}
