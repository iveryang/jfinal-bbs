package cn.iver.controller;

import cn.iver.interceptor.AdminInterceptor;
import cn.iver.interceptor.ReplyValidator;
import cn.iver.model.Post;
import cn.iver.model.Reply;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheKit;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-4-2
 */
public class ReplyController extends Controller {
    public void index(){
        setAttr("replyPage", Reply.dao.getReplyPage(getParaToInt(0), getParaToInt(1, 1)));
        setAttr("postID", getPara(0));
        render("/user/parts/_ajax_reply.html");
    }

    @Before(ReplyValidator.class)
    public void save(){
        Reply reply = getModel(Reply.class);
        int postID = reply.get("postID");
        reply.saveReply(postID);
        forwardAction("/reply/" + postID);
    }

    /* ----------------------admin---------------------- */

    @Before(AdminInterceptor.class)
    public void delete(){
        int id = getParaToInt(1);
        int postID = getParaToInt(0);
        Reply.dao.deleteByID(postID, id);
        forwardAction("/reply/" + postID);
    }

    @Before(AdminInterceptor.class)
    public void showReplyList(){
        setAttr("replyPage", Reply.dao.getReplyPageForAdmin(getParaToInt(0, 1)));
        render("/admin/replyList.html");
    }
}
