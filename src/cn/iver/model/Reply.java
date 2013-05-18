package cn.iver.model;

import cn.iver.common.MyConstants;
import cn.iver.kit.HtmlTagKit;
import cn.iver.kit.ModelKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-26
 */
public class Reply extends Model<Reply> {
    public static final Reply dao = new Reply();
    private static final String REPLY_CACHE = "reply";
    private static final String REPLY_PAGE_CACHE = "replyPage";
    private static final String REPLY_PAGE_FOR_ADMIN_CACHE = "replyPageForAdmin";

    public Reply getReply(int id){
        return ModelKit.getModel(id, REPLY_CACHE, dao);
    }
    public Page<Reply> getReplyPage(int postID, int pageNumber){
        String cacheName = REPLY_PAGE_CACHE;
        Page<Reply> replyPage = Reply.dao.paginateByCache(cacheName, postID + "-" + pageNumber, pageNumber, MyConstants.REPLY_PAGE_SIZE,
                "select id", "from reply where postID=?", postID);
        return ModelKit.loadModelPage(replyPage, cacheName, dao);
    }
    public Page<Reply> getReplyPageForAdmin(int pageNumber){
        String cacheName = REPLY_PAGE_FOR_ADMIN_CACHE;
        Page<Reply> replyPage = Reply.dao.paginateByCache(cacheName, pageNumber, pageNumber, MyConstants.PAGE_SIZE_FOR_ADMIN,
                "select id", "from reply order by createTime desc");
        return ModelKit.loadModelPage(replyPage, cacheName, dao);
    }
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
        return User.dao.getUser(this.getInt("userID"));
    }
    public Topic getTopic(){
        return Topic.dao.getTopic(this.getInt("topicID"));
    }

    /* private */
    private void removeAllReplyPageCache() {
        CacheKit.removeAll(REPLY_PAGE_CACHE);
    }
}
