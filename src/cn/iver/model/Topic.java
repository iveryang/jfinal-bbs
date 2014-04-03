package cn.iver.model;

import cn.iver.common.Const;
import cn.iver.ext.jfinal.Model;
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
    private static final String TOPIC_PAGE_FOR_INDEX_CACHE = "topicPageForIndex";
    private static final String TOPIC_PAGE_FOR_MODULE_CACHE = "topicPageForModule";
    private static final String HOT_TOPIC_PAGE_CACHE = "hotTopicPage";
    private static final String NICE_TOPIC_PAGE_CACHE = "niceTopicPage";
    private static final String TOPIC_PAGE_FOR_ADMIN_CACHE = "topicPageForAdmin";

    public Topic(){
        super(TOPIC_CACHE);
    }
    
    /* get */
    public Topic get(int id){
        return loadModel(id);
    }
    public Page<Topic> getPage(int pageNumber){
        Page<Topic> topicPage = dao.paginateByCache(TOPIC_PAGE_FOR_INDEX_CACHE, pageNumber,
                pageNumber, Const.TOPIC_PAGE_SIZE,
                "select id", "from topic order by createTime desc");
        return loadModelPage(topicPage);
    }
    public Page<Topic> getPageForModule(int moduleID, int pageNumber){
        Page<Topic> topicPage = dao.paginateByCache(TOPIC_PAGE_FOR_MODULE_CACHE, moduleID + "-" + pageNumber,
                pageNumber, Const.TOPIC_PAGE_SIZE,
                "select id", "from topic where moduleID=? order by createTime desc", moduleID);
        return loadModelPage(topicPage);
    }
    public Page<Topic> getHotPage(int pageNumber){
        String cacheName = HOT_TOPIC_PAGE_CACHE;
        Page<Topic> topicPage = dao.paginateByCache(cacheName, pageNumber,
                pageNumber, Const.TOPIC_PAGE_SIZE,
                "select id", "from topic order by pv desc");
        return loadModelPage(topicPage);
    }
    public Page<Topic> getNicePage(int pageNumber){
        String cacheName = NICE_TOPIC_PAGE_CACHE;
        Page<Topic> topicPage = dao.paginateByCache(cacheName, pageNumber,
                pageNumber, Const.TOPIC_PAGE_SIZE,
                "select id", "from topic where isNice=true order by createTime desc");
        return loadModelPage(topicPage);
    }
    public Page<Topic> getPageForAdmin(Integer pageNumber) {
        String cacheName = TOPIC_PAGE_FOR_ADMIN_CACHE;
        Page<Topic> topicPage = dao.paginateByCache(cacheName, pageNumber,
                pageNumber, Const.PAGE_SIZE_FOR_ADMIN,
                "select id", "from topic order by createTime desc");
        return loadModelPage(topicPage);
    }

    /* other */
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
        this.filterText("content").update();
        removeThisCache();
        removeAllPageCache();
    }
    public void save(Post post){
        this.set("createTime", new Date()).filterText("content").save();
        post.set("topicID", this.getInt("id")).set("createTime", new Date());
        post.save();
        removeAllPageCache();
    }
    public void deleteByID(int topicID){
        dao.deleteById(topicID);
        dao.removeAllPageCache();
        Post.dao.removeAllPageCache();
        Reply.dao.removeAllPageCache();
    }

    /* getter */
    public User getUser(){
        return User.dao.get(this.getInt("userID"));
    }
    public Module getModule(){
        return Module.dao.get(this.getInt("moduleID"));
    }

    /* cache */
    public void removeThisCache() {
        CacheKit.remove(TOPIC_CACHE, this.getInt("id"));
    }
    public void removeAllPageCache() {
        CacheKit.removeAll(TOPIC_PAGE_FOR_INDEX_CACHE);
        CacheKit.removeAll(TOPIC_PAGE_FOR_MODULE_CACHE);
        CacheKit.removeAll(HOT_TOPIC_PAGE_CACHE);
        CacheKit.removeAll(NICE_TOPIC_PAGE_CACHE);
        CacheKit.removeAll(TOPIC_PAGE_FOR_ADMIN_CACHE);
    }

    /* private */
    private void increaseTopicAttrInCache(int topicID, String attr) {
        CacheKit.put(TOPIC_CACHE, topicID, get(topicID).set(attr, get(topicID).getInt(attr) + 1));
    }

}