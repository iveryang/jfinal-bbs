package cn.iver.model;

import cn.iver.common.MyConstants;
import cn.iver.kit.HtmlTagKit;
import cn.iver.kit.ModelKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-31
 */
public class Topic extends Model<Topic>{
    public static final Topic dao = new Topic();
    private static final String TOPIC_CACHE = "topic";
    private static final ModelKit mk = new ModelKit(dao, TOPIC_CACHE);
    private static final String TOPIC_PAGE_FOR_INDEX_CACHE = "topicPageForIndex";
    private static final String TOPIC_PAGE_FOR_MODULE_CACHE = "topicPageForModule";
    private static final String HOT_TOPIC_PAGE_CACHE = "hotTopicPage";
    private static final String NICE_TOPIC_PAGE_CACHE = "niceTopicPage";

    public Topic getTopic(int id){
        return mk.getModel(id);
    }
    
    public Page<Topic> getTopicPage(int pageNumber){
        Page<Topic> topicPage = dao.paginateByCache(TOPIC_PAGE_FOR_INDEX_CACHE, pageNumber,
                pageNumber, MyConstants.TOPIC_PAGE_SIZE,
                "select id", "from topic order by createTime desc");
        return mk.loadModelPage(topicPage);
    }
    public Page<Topic> getTopicPageForModule(int moduleID, int pageNumber){
        Page<Topic> topicPage = dao.paginateByCache(TOPIC_PAGE_FOR_MODULE_CACHE, moduleID + "-" + pageNumber,
                pageNumber, MyConstants.TOPIC_PAGE_SIZE,
                "select id", "from topic where moduleID=? order by createTime desc", moduleID);
        return mk.loadModelPage(topicPage);
    }
    public Page<Topic> getHotTopicPage(int pageNumber){
        String cacheName = HOT_TOPIC_PAGE_CACHE;
        Page<Topic> topicPage = dao.paginateByCache(cacheName, pageNumber,
                pageNumber, MyConstants.TOPIC_PAGE_SIZE,
                "select id", "from topic order by pv desc");
        return mk.loadModelPage(topicPage);
    }
    public Page<Topic> getNiceTopicPage(int pageNumber){
        String cacheName = NICE_TOPIC_PAGE_CACHE;
        Page<Topic> topicPage = dao.paginateByCache(cacheName, pageNumber,
                pageNumber, MyConstants.TOPIC_PAGE_SIZE,
                "select id", "from topic where isNice=true order by createTime desc");
        return mk.loadModelPage(topicPage);
    }
    public void increaseTopicPV(int topicID){
        Topic topic = dao.findFirst("select pv from topic where id=?", topicID);
        if (topic != null){
            int pv = topic.getInt("pv");
            new Topic().set("id", topicID).set("pv", pv + 1).update();
        }
        increaseTopicAttrInCache(topicID, "pv");
    }
    public void increaseTopicPostCount(int topicID){
        int postCount = dao.findFirst("select postCount from topic where id=?", topicID).getInt("postCount");
        new Topic().set("id", topicID).set("postCount", postCount + 1).update();
        increaseTopicAttrInCache(topicID, "postCount");
    }
    public void myUpdate(){
        HtmlTagKit.processHtmlSpecialTag(this, "content");
        this.update();
        this.removeThisTopicCache();
        removeAllTopicPageCache();
    }
    public void mySave(Post post){
        HtmlTagKit.processHtmlSpecialTag(this, "content");
        this.set("createTime", new Date());
        this.save();
        post.set("topicID", this.getInt("id")).set("createTime", new Date());
        post.save();
        removeAllTopicPageCache();
    }

    /* getter */
    public User getUser(){
        return User.dao.getUser(this.getInt("userID"));
    }
    public Module getModule(){
        return Module.dao.getModule(this.getInt("moduleID"));
    }

    /* private */
    private void removeThisTopicCache() {
        CacheKit.remove(TOPIC_CACHE, this.getInt("id"));
    }
    private void removeAllTopicPageCache() {
        CacheKit.removeAll(TOPIC_PAGE_FOR_INDEX_CACHE);
        CacheKit.removeAll(TOPIC_PAGE_FOR_MODULE_CACHE);
        CacheKit.removeAll(HOT_TOPIC_PAGE_CACHE);
        CacheKit.removeAll(NICE_TOPIC_PAGE_CACHE);
    }
    private void increaseTopicAttrInCache(int topicID, String attr) {
        CacheKit.put(TOPIC_CACHE, topicID, getTopic(topicID).set(attr, getTopic(topicID).getInt(attr) + 1));
    }
}