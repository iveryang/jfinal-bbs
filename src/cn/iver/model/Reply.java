package cn.iver.model;

import cn.iver.common.MyConstants;
import cn.iver.kit.HtmlTagKit;
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
    private static final String CACHE_KEY_SEPARATE = "-";

    public Reply getReply(int replyID){
        final int REPLY_ID = replyID;
        return CacheKit.get(REPLY_CACHE, REPLY_ID, new IDataLoader() {
            @Override
            public Object load() {
                return dao.findById(REPLY_ID);
            }
        });
    }
    public Page<Reply> getReplyPage(int postID, int pageNumber){
        Page<Reply> replyPage = Reply.dao.paginateByCache(REPLY_PAGE_CACHE, postID + CACHE_KEY_SEPARATE + pageNumber,
                pageNumber, MyConstants.REPLY_PAGE_SIZE, "select id", "from reply where postID=?", postID);
        loadReplyPage(replyPage);
        return replyPage;
    }
    public Page<Reply> getReplyPageForAdmin(int pageNumber){
        Page<Reply> replyPage = Reply.dao.paginateByCache(REPLY_PAGE_FOR_ADMIN_CACHE, pageNumber,
                pageNumber, MyConstants.PAGE_SIZE_FOR_ADMIN, "select id", "from reply order by createTime desc");
        loadReplyPage(replyPage);
        return replyPage;
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
    private void loadReplyPage(Page<Reply> replyPage) {
        List<Reply> replyList = replyPage.getList();
        for(int i = 0; i < replyList.size(); i++){
            Reply reply = getReply(replyList.get(i).getInt("id"));
            replyList.set(i, reply);
        }
    }
}
