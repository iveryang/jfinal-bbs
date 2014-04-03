package cn.iver.controller;

import cn.iver.interceptor.AdminInterceptor;
import cn.iver.interceptor.LoginInterceptor;
import cn.iver.validator.ReplyValidator;
import cn.iver.model.Reply;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 */
public class ReplyController extends Controller {
    public void index(){
        if(0 == getParaToInt(1, 1)){
            setAttr("replyPage", Reply.dao.getLastPage(getParaToInt(0)));
        }else{
            setAttr("replyPage", Reply.dao.getPage(getParaToInt(0), getParaToInt(1, 1)));
        }
        setAttr("postID", getParaToInt(0));
        render("/reply/_reply.html");
    }

    @Before({LoginInterceptor.class, ReplyValidator.class})
    public void save(){
        Reply reply = getModel(Reply.class).set("userID", getSessionAttr("userID"));
        int postID = reply.getInt("postID");
        reply.mySave(postID);
        forwardAction("/reply/" + postID + "-0");
    }

    /* ----------------------admin---------------------- */

    @Before(AdminInterceptor.class)
    public void delete(){
        Reply.dao.deleteByID(getParaToInt(0));
        forwardAction("/admin/replyList/" + getParaToInt(1));
    }
}
