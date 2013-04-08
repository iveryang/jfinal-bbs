package cn.iver.model;

import cn.iver.common.MyConstants;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-31
 */
public class Topic extends Model<Topic>{
    public static final Topic dao = new Topic();
    private static final String TOPIC_CACHE = "topic";
    private static final String INDEX_TOPIC_PAGE_CACHE = "indexTopicPage";
    private static final String MODULE_TOPIC_PAGE_CACHE = "moduleTopicPage";
    private static final String SUB_MODULE_TOPIC_PAGE_CACHE = "subModuleTopicPage";
    private static final String UP_TOPIC_LIST_CACHE = "upTopicList";
    private static final String HOT_TOPIC_LIST_CACHE = "hotTopicList";
    private static final String CACHE_KEY_SEPARATE = "-";

    public Topic getTopicByID(int ID){
        return dao.findByCache(TOPIC_CACHE, ID, "select * from topic where id=?", ID).get(0);
    }
    public Page<Topic> getTopicPage(int pageNumber){
        return dao.paginateByCache(INDEX_TOPIC_PAGE_CACHE, pageNumber,
                pageNumber, MyConstants.PAGE_SIZE, "select *", "from topic where isPublished=true order by createdTime desc");
    }
    public Page<Topic> getTopicPageForModule(int moduleID, int pageNumber){
        return dao.paginateByCache(MODULE_TOPIC_PAGE_CACHE, moduleID + CACHE_KEY_SEPARATE + pageNumber,
                pageNumber, MyConstants.PAGE_SIZE, "select *", "from topic where moduleID=? and isPublished=true order by createdTime desc", moduleID);
    }
    public Page<Topic> getTopicPageForSubModule(int subModuleID, int pageNumber){
        return dao.paginateByCache(SUB_MODULE_TOPIC_PAGE_CACHE, subModuleID + CACHE_KEY_SEPARATE + pageNumber,
                pageNumber, MyConstants.PAGE_SIZE, "select *", "from topic where subModuleID=? and isPublished=true order by createdTime desc", subModuleID);
    }
    public List<Topic> getUpTopic(){
        return dao.findByCache(UP_TOPIC_LIST_CACHE, 1,
                "select * from topic where isUp=true order by createdTime limit ?", MyConstants.SIDEBAR_TOPIC_SIZE);
    }
    public List<Topic> getHotTopic(){
        return dao.findByCache(HOT_TOPIC_LIST_CACHE, 1,
                "select * from topic order by pv desc limit ?", MyConstants.SIDEBAR_TOPIC_SIZE);
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
    public List<Topic> getAllUnPublishedTopic(){
        return Topic.dao.find("select * from topic where isPublished=false");
    }
    public Page<Topic> getTopicPageForAdmin(int pageNumber){
        return Topic.dao.paginate(pageNumber, MyConstants.PAGE_SIZE_FOR_ADMIN, "select *", "from topic order by createdTime desc");
    }
    public int saveTopicAndPost(Topic topic, Post post) {
        topic = createFisrtPostPreview(topic, post);
        topic.save();
        int topicID = topic.getInt("id");
        post.set("topicID", topicID);
        post.save();
        removeAllCache();
        return topicID;
    }
    public int updateTopic(Topic topic) {
        int topicID = topic.getInt("id");
        topic.update();
        removeAllCache();
        return topicID;
    }

    /* private */
    private void removeAllCache() {
        CacheKit.removeAll(TOPIC_CACHE);
        CacheKit.removeAll(INDEX_TOPIC_PAGE_CACHE);
        CacheKit.removeAll(MODULE_TOPIC_PAGE_CACHE);
        CacheKit.removeAll(SUB_MODULE_TOPIC_PAGE_CACHE);
        CacheKit.removeAll(UP_TOPIC_LIST_CACHE);
        CacheKit.removeAll(HOT_TOPIC_LIST_CACHE);
    }
    private Topic createFisrtPostPreview(Topic topic, Post post){
        String postContent = post.getStr("content");
        int endIndex = postContent.length() > MyConstants.TOPIC_CONTENT_PREVIEW_SIZE ?
                MyConstants.TOPIC_CONTENT_PREVIEW_SIZE : postContent.length();
        topic.set("firstPostPreview", postContent.substring(0, endIndex));
        return topic;
    }
}