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
                pageNumber, MyConstants.PAGE_SIZE_OF_REPLY, "select *", "from reply where postID=?", postID);
    }
    public void saveReply(int postID){
        Post.dao.setHasReplyTrue(postID);
        CacheKit.removeAll(REPLY_PAGE_CACHE);
        this.set("content", HtmlTagKit.processHtmlSpecialTag(this.getStr("content")));
        this.set("userName", HtmlTagKit.processHtmlSpecialTag(this.getStr("userName")));
        this.save();
    }
    public void deleteByID(int id){
        CacheKit.removeAll(REPLY_PAGE_CACHE);
        dao.deleteById(id);
    }
    public Page<Reply> getReplyPageForAdmin(int pageNumber){
        return dao.paginate(pageNumber, MyConstants.PAGE_SIZE_FOR_ADMIN, "select *", "from reply order by createTime desc");
    }
}
