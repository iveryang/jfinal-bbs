package cn.iver.model;

import cn.iver.common.MyConstants;
import cn.iver.kit.ModelKit;
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
    private static final String POST_CACHE = "post";
    private static final ModelKit mk = new ModelKit(dao, POST_CACHE);
    private static final String POST_PAGE_CACHE = "postPage";
    private static final String POST_PAGE_FOR_ADMIN_CACHE = "postPageForAdmin";

    /* get */
    public Post get(int id){
        return mk.loadModel(id);
    }
    public Page<Post> getPage(int topicID, int pageNumber){
        Topic.dao.increaseTopicPV(topicID);
        String cacheName = POST_PAGE_CACHE;
        Page<Post> postPage = dao.paginateByCache(cacheName, topicID + "-" + pageNumber, pageNumber, MyConstants.POST_PAGE_SIZE,
                "select id", "from post where topicID=?", topicID);
        return mk.loadModelPage(postPage);
    }
    public Page<Post> getPageForAdmin(int pageNumber){
        String cacheName = POST_PAGE_FOR_ADMIN_CACHE;
        Page<Post> postPage = dao.paginateByCache(cacheName, pageNumber, pageNumber, MyConstants.PAGE_SIZE_FOR_ADMIN,
                "select id", "from post order by createTime desc");
        return mk.loadModelPage(postPage);
    }

    /* save and update */
    public void setHasReplyTrue(int postID){
        boolean hasReply = dao.findById(postID).getBoolean("hasReply");
        if ( ! hasReply){
            new Post().set("id", postID).set("hasReply", true).update();
        }
    }
    public void mySave(){
        this.save();
        int topicID = this.getInt("topicID");
        Topic.dao.increaseTopicPostCount(topicID);
        removeAllPostPageCache();
    }
    public void myUpdate(){
        this.update();
        this.removeThisPostCache();
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

    /* private */
    private void removeThisPostCache() {
        CacheKit.remove(POST_CACHE, this.getInt("id"));
    }
    private void removeAllPostPageCache() {
        CacheKit.removeAll(POST_PAGE_CACHE);
    }
}
