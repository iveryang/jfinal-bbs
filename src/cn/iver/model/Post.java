package cn.iver.model;

import cn.iver.common.MyConstants;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-26
 */
public class Post extends Model<Post> {
    public static final Post dao = new Post();
    private static final String POST_CACHE = "post";
    private static final String POST_PAGE_CACHE = "postPage";
    private static final String CACHE_KEY_SEPARATE = "-";

    public Post getPost(int postID){
        final int POST_ID = postID;
        return CacheKit.get(POST_CACHE, POST_ID, new IDataLoader() {
            @Override
            public Object load() {
                return dao.findById(POST_ID);
            }
        });
    }
    public Page<Post> getPostPage(int topicID, int pageNumber){
        if (pageNumber == 1){
            Topic.dao.increaseTopicPV(topicID);
        }
        Page<Post> postPage = dao.paginateByCache(POST_PAGE_CACHE, topicID + CACHE_KEY_SEPARATE + pageNumber,
                pageNumber, MyConstants.POST_PAGE_SIZE,
                "select id", "from post where topicID=?", topicID);
        loadPostPage(postPage);
        return postPage;
    }
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
        return User.dao.getUser(this.getInt("userID"));
    }
    public Page<Reply> getReplyPage() {
        return Reply.dao.getReplyPage(this.getInt("id"), 1);
    }

    /* private */
    private void removeThisPostCache() {
        CacheKit.remove(POST_CACHE, this.getInt("id"));
    }
    private void removeAllPostPageCache() {
        CacheKit.removeAll(POST_PAGE_CACHE);
    }
    private void loadPostPage(Page<Post> postPage) {
        List<Post> postList = postPage.getList();
        for(int i = 0; i < postList.size(); i++){
            Post post = getPost(postList.get(i).getInt("id"));
            postList.set(i, post);
        }
    }
}
