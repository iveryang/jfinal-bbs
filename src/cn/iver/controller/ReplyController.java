package cn.iver.controller;

import cn.iver.interceptor.AdminInterceptor;
import cn.iver.interceptor.LoginInterceptor;
import cn.iver.validator.ReplyValidator;
import cn.iver.model.Reply;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-4-2
 */
public class ReplyController extends Controller {
    public void index(){
        setAttr("replyPage", Reply.dao.getPage(getParaToInt(0), getParaToInt(1, 1)));
        setAttr("postID", getParaToInt(0));
        render("/reply/_reply.html");
    }

    @Before({LoginInterceptor.class, ReplyValidator.class})
    public void save(){
        Reply reply = getModel(Reply.class);
        int postID = reply.getInt("postID");
        reply.mySave(postID);
        forwardAction("/reply/" + postID);
    }

    /* ----------------------admin---------------------- */

    @Before(AdminInterceptor.class)
    public void delete(){
        int id = getParaToInt(1);
        int postID = getParaToInt(0);
        Reply.dao.deleteByID(id);
        forwardAction("/reply/" + postID);
    }
}
