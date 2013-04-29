package cn.iver.controller;

import cn.iver.model.Topic;
import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-4-29
 */
public class TagController extends Controller {
    public void index(){
        int tagID = getParaToInt(0);
        int pageNumber = getParaToInt(1, 1);
        setAttr("topicPage", Topic.dao.getTopicPageByTagID(tagID, pageNumber));
        render("/common/index.html");
    }
}
