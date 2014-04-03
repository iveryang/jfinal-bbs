package cn.iver.model;

import cn.iver.common.Const;
import cn.iver.ext.jfinal.Model;
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
    private static final String REPLY_PAGE_CACHE = "replyPage";
    private static final String REPLY_PAGE_FOR_ADMIN_CACHE = "replyPageForAdmin";

    public Reply() {
        super(REPLY_CACHE);
    }

    /* get */
    public Reply get(int id){
        return loadModel(id);
    }
    public Page<Reply> getPage(int postID, int pageNumber){
        String cacheName = REPLY_PAGE_CACHE;
        Page<Reply> replyPage = Reply.dao.paginateByCache(cacheName, postID + "-" + pageNumber, pageNumber, Const.REPLY_PAGE_SIZE,
                "select id", "from reply where postID=?", postID);
        return loadModelPage(replyPage);
    }
    public Page<Reply> getLastPage(int postID){
        int totalPage = getPage(postID, 1).getTotalPage();
        return loadModelPage(getPage(postID, totalPage));
    }
    public Page<Reply> getPageForAdmin(int pageNumber){
        String cacheName = REPLY_PAGE_FOR_ADMIN_CACHE;
        Page<Reply> replyPage = Reply.dao.paginateByCache(cacheName, pageNumber, pageNumber, Const.PAGE_SIZE_FOR_ADMIN,
                "select id", "from reply order by createTime desc");
        return loadModelPage(replyPage);
    }

    /* other */
    public void mySave(int postID){
        Post.dao.setHasReplyTrue(postID);
        this.set("createTime", new Date()).filterText("content").save();
        removeAllPageCache();
    }
    public void deleteByID(int id){
        dao.deleteById(id);
        removeAllPageCache();
    }

    /* getter */
    public User getUser(){
        return User.dao.get(this.getInt("userID"));
    }
    public Topic getTopic(){
        return Topic.dao.get(this.getInt("topicID"));
    }

    /* cache */
    public void removeAllPageCache() {
        CacheKit.removeAll(REPLY_PAGE_CACHE);
        CacheKit.removeAll(REPLY_PAGE_FOR_ADMIN_CACHE);
    }
}
