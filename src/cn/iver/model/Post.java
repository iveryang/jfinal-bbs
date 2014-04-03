package cn.iver.model;

import cn.iver.common.Const;
import cn.iver.ext.jfinal.Model;
import cn.iver.kit.HtmlKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-26
 */
public class Post extends Model<Post> {
    public static final Post dao = new Post();
    private static final String POST_CACHE = "post";
    private static final String POST_PAGE_CACHE = "postPage";
    private static final String POST_PAGE_FOR_ADMIN_CACHE = "postPageForAdmin";

    public Post() {
        super(POST_CACHE);
    }

    /* get */
    public Post get(int id){
        return loadModel(id);
    }
    public Page<Post> getPage(int topicID, int pageNumber){
        Topic.dao.increaseTopicPV(topicID);
        String cacheName = POST_PAGE_CACHE;
        Page<Post> postPage = dao.paginateByCache(cacheName, topicID + "-" + pageNumber, pageNumber, Const.POST_PAGE_SIZE,
                "select id", "from post where topicID=?", topicID);
        return loadModelPage(postPage);
    }
    public Page<Post> getPageForAdmin(int pageNumber){
        String cacheName = POST_PAGE_FOR_ADMIN_CACHE;
        Page<Post> postPage = dao.paginateByCache(cacheName, pageNumber, pageNumber, Const.PAGE_SIZE_FOR_ADMIN,
                "select id", "from post order by createTime desc");
        return loadModelPage(postPage);
    }

    /* save and update */
    public void setHasReplyTrue(int postID){
        boolean hasReply = dao.findById(postID).getBoolean("hasReply");
        if ( ! hasReply){
            new Post().set("id", postID).set("hasReply", true).update();
        }
    }
    public void mySave(){
        this.filterBasicHtmlAndImage("content").save();
        int topicID = this.getInt("topicID");
        Topic.dao.increaseTopicPostCount(topicID);
        removeAllPageCache();
    }
    public void myUpdate(){
        this.filterBasicHtmlAndImage("content").update();
        this.removeThisCache();
    }
    public void deleteByID(int postID){
        dao.deleteById(postID);
        removeAllPageCache();
        Reply.dao.removeAllPageCache();
    }

    /* getter */
    public User getUser(){
        return User.dao.get(this.getInt("userID"));
    }
    public Page<Reply> getReplyPage() {
        return Reply.dao.getPage(this.getInt("id"), 1);
    }
    public Topic getTopic(){
        return Topic.dao.get(this.getInt("topicID"));
    }

    /* cache */
    public void removeThisCache() {
        CacheKit.remove(POST_CACHE, this.getInt("id"));
    }
    public void removeAllPageCache() {
        CacheKit.removeAll(POST_PAGE_CACHE);
        CacheKit.removeAll(POST_PAGE_FOR_ADMIN_CACHE);
    }
}
