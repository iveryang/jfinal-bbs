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
public class Post extends Model<Post> {
    public static final Post dao = new Post();
    private static final String POST_PAGE_CACHE = "postPage";
    private static final String CACHE_KEY_SEPARATE = "-";

    public Page<Post> getPostPage(int topicID, int pageNumber){
        Topic.dao.increaseTopicPV(topicID);
        return dao.paginateByCache(POST_PAGE_CACHE, topicID + CACHE_KEY_SEPARATE + pageNumber,
                pageNumber, MyConstants.PAGE_SIZE, "select *", "from post where topicID=?", topicID);
    }
    public Page<Reply> getReplyPage() {
        return Reply.dao.getReplyPage(this.getInt("id"), 1);
    }
    public void setHasReplyTrue(int postID){
        boolean hasReply = dao.findById(postID).getBoolean("hasReply");
        if ( ! hasReply){
            new Post().set("id", postID).set("hasReply", true).update();
        }
    }
    public int save(Post post){
        post.save();
        int topicID = post.get("topicID");
        Topic.dao.increaseTopicPostCount(topicID);
        CacheKit.removeAll(POST_PAGE_CACHE);
        return topicID;
    }
    public int update(Post post){
        post.update();
        CacheKit.removeAll(POST_PAGE_CACHE);
        return post.get("topicID");
    }
}
