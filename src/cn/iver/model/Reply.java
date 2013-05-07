package cn.iver.model;

import cn.iver.common.MyConstants;
import cn.iver.kit.HtmlTagKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-26
 */
public class Reply extends Model<Reply> {
    public static final Reply dao = new Reply();
    private static final String REPLY_PAGE_CACHE = "replyPage";
    private static final String CACHE_KEY_SEPARATE = "-";

    public Page<Reply> getReplyPage(int postID, int pageNumber){
        return Reply.dao.paginateByCache(REPLY_PAGE_CACHE, postID + CACHE_KEY_SEPARATE + pageNumber,
                pageNumber, MyConstants.PAGE_SIZE_OF_REPLY, "select reply.*, user.username", "from reply, user where reply.postID=? and reply.userID=user.id", postID);
    }
    public void mySave(int postID){
        Post.dao.setHasReplyTrue(postID);
        CacheKit.removeAll(REPLY_PAGE_CACHE);
        this.set("content", HtmlTagKit.processHtmlSpecialTag(this.getStr("content")));
        this.save();
    }
    public void deleteByID(int id){
        CacheKit.removeAll(REPLY_PAGE_CACHE);
        dao.deleteById(id);
    }
}
