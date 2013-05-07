package cn.iver.model;

import cn.iver.common.MyConstants;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-31
 */
public class Topic extends Model<Topic>{
    public static final Topic dao = new Topic();
    private static final String TOPIC_CACHE = "topic";
    private static final String TOPIC_PAGE_FOR_INDEX_CACHE = "topicPageForIndex";
    private static final String TOPIC_PAGE_FOR_MODULE_CACHE = "topicPageForModule";
    private static final String HOT_TOPIC_PAGE_CACHE = "hotTopicPage";
    private static final String NICE_TOPIC_PAGE_CACHE = "niceTopicPage";
    private static final String CACHE_KEY_SEPARATE = "-";

    public Topic getTopic(int id){
        final int ID = id;
        return CacheKit.get(TOPIC_CACHE, ID, new IDataLoader() {
            @Override
            public Object load() {
                return dao.findById(ID);
            }
        });
    }
    public Page<Topic> getTopicPage(int pageNumber){
        return dao.paginateByCache(TOPIC_PAGE_FOR_INDEX_CACHE, pageNumber,
                pageNumber, MyConstants.PAGE_SIZE,
                "select *", "from topic order by createTime desc");
    }
    public Page<Topic> getTopicPageForModule(int moduleID, int pageNumber){
        return dao.paginateByCache(TOPIC_PAGE_FOR_MODULE_CACHE, moduleID + CACHE_KEY_SEPARATE + pageNumber,
                pageNumber, MyConstants.PAGE_SIZE,
                "select *", "from topic where moduleID=? order by createTime desc", moduleID);
    }
    public Page<Topic> getHotTopicPage(int pageNumber){
        return dao.paginateByCache(HOT_TOPIC_PAGE_CACHE, pageNumber,
                pageNumber, MyConstants.PAGE_SIZE,
                "select *", "from topic order by pv desc");
    }
    public Page<Topic> getNiceTopicPage(int pageNumber){
        return dao.paginateByCache(NICE_TOPIC_PAGE_CACHE, pageNumber,
                pageNumber, MyConstants.PAGE_SIZE,
                "select *", "from topic where isNice=true order by createTime desc");
    }
    public void increaseTopicPV(int topicID){
        Topic topic = dao.findFirst("select pv from topic where id=?", topicID);
        if (topic != null){
            int pv = topic.getInt("pv");
            new Topic().set("id", topicID).set("pv", pv + 1).update();
        }
    }
    public void increaseTopicPostCount(int topicID){
        int postCount = dao.findFirst("select postCount from topic where id=?", topicID).getInt("postCount");
        new Topic().set("id", topicID).set("postCount", postCount + 1).update();
    }
    public void myUpdate() {
        this.update();
        removeAllCache();
    }
    public void mySave(Post post){
        this.save();
        int topicID = this.getInt("id");
        post.set("topicID", topicID);
        post.save();
        removeAllCache();
    }

    /* getter */
    public User getUser(){
        return User.dao.getUser(this.getInt("userID"));
    }
    public Module getModule(){
        return Module.dao.getModule(this.getInt("moduleID"));
    }

    /* private */
    private void removeAllCache() {
        CacheKit.removeAll(TOPIC_CACHE);
        CacheKit.removeAll(TOPIC_PAGE_FOR_INDEX_CACHE);
        CacheKit.removeAll(TOPIC_PAGE_FOR_MODULE_CACHE);
        CacheKit.removeAll(HOT_TOPIC_PAGE_CACHE);
        CacheKit.removeAll(NICE_TOPIC_PAGE_CACHE);
    }
}