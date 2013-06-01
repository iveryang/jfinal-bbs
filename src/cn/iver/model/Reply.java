package cn.iver.model;

import cn.iver.common.MyConstants;
import cn.iver.kit.HtmlTagKit;
import cn.iver.kit.ModelKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-26
 */
public class Reply extends Model<Reply> {
    public static final Reply dao = new Reply();
    private static final String REPLY_CACHE = "reply";
    private static final ModelKit mk = new ModelKit(dao, REPLY_CACHE);
    private static final String REPLY_PAGE_CACHE = "replyPage";
    private static final String REPLY_PAGE_FOR_ADMIN_CACHE = "replyPageForAdmin";

    /* get */
    public Reply get(int id){
        return mk.getModel(id);
    }
    public Page<Reply> getPage(int postID, int pageNumber){
        String cacheName = REPLY_PAGE_CACHE;
        Page<Reply> replyPage = Reply.dao.paginateByCache(cacheName, postID + "-" + pageNumber, pageNumber, MyConstants.REPLY_PAGE_SIZE,
                "select id", "from reply where postID=?", postID);
        return mk.loadModelPage(replyPage);
    }
    public Page<Reply> getPageForAdmin(int pageNumber){
        String cacheName = REPLY_PAGE_FOR_ADMIN_CACHE;
        Page<Reply> replyPage = Reply.dao.paginateByCache(cacheName, pageNumber, pageNumber, MyConstants.PAGE_SIZE_FOR_ADMIN,
                "select id", "from reply order by createTime desc");
        return mk.loadModelPage(replyPage);
    }

    /* other */
    public void mySave(int postID){
        Post.dao.setHasReplyTrue(postID);
        HtmlTagKit.processHtmlSpecialTag(this, "content");
        this.set("createTime", new Date());
        this.save();
        removeAllReplyPageCache();
    }
    public void deleteByID(int id){
        dao.deleteById(id);
        removeAllReplyPageCache();
    }

    /* getter */
    public User getUser(){
        return User.dao.get(this.getInt("userID"));
    }
    public Topic getTopic(){
        return Topic.dao.get(this.getInt("topicID"));
    }

    /* private */
    private void removeAllReplyPageCache() {
        CacheKit.removeAll(REPLY_PAGE_CACHE);
    }
}
