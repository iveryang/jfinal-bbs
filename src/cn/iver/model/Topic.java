package cn.iver.model;

import cn.iver.common.MyConstants;
import com.jfinal.kit.StringKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

import java.util.List;

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
    private static final String TOPIC_PAGE_FOR_TAG_CACHE = "topicPageForTag";
    private static final String UP_TOPIC_LIST_CACHE = "upTopicList";
    private static final String HOT_TOPIC_LIST_CACHE = "hotTopicList";
    private static final String CACHE_KEY_SEPARATE = "-";

    public Topic getTopicByID(int id){
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
                "select *", "from topic where isPublished=true order by createTime desc");
    }
    public Page<Topic> getTopicPageForModule(int moduleID, int pageNumber){
        return dao.paginateByCache(TOPIC_PAGE_FOR_MODULE_CACHE, moduleID + CACHE_KEY_SEPARATE + pageNumber,
                pageNumber, MyConstants.PAGE_SIZE,
                "select *", "from topic where moduleID=? and isPublished=true order by createTime desc", moduleID);
    }
    public List<Topic> getUpTopic(){
        return dao.findByCache(UP_TOPIC_LIST_CACHE, 1,
                "select * from topic where isUp=true order by createTime desc limit ?", MyConstants.SIDEBAR_TOPIC_SIZE);
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
        return Topic.dao.paginate(pageNumber, MyConstants.PAGE_SIZE_FOR_ADMIN, "select *", "from topic order by createTime desc");
    }
    public void updateTopic() {
        this.update();
        removeAllCache();
    }
    public void createNewTopic(Post post, String[] tagsArray){
        this.save();
        int topicID = this.getInt("id");
        post.set("topicID", topicID);
        post.save();
        for (String tagName : tagsArray) {
            if (StringKit.notBlank(tagName.trim())){
                Tag tag = Tag.dao.getTagByTagName(tagName);
                if (tag == null){
                    Tag tagTemp = new Tag();
                    tagTemp.set("tagName", tagName).save();
                    new TopicTag().set("tagID", tagTemp.getInt("id")).set("topicID", topicID).save();
                } else {
                    tag.set("topicCount", tag.getInt("topicCount") + 1).update();
                    new TopicTag().set("tagID", tag.getInt("id")).set("topicID", topicID).save();
                }
            }
        }
        Tag.dao.removeAllCache();
    }
    public Page<Topic> getTopicPageByTagID(int tagID, int pageNumber){
        return dao.paginateByCache(TOPIC_PAGE_FOR_TAG_CACHE, tagID + CACHE_KEY_SEPARATE + pageNumber,
                pageNumber, MyConstants.PAGE_SIZE,
                "select topic.*", "from topic, topic_tag where topic_tag.tagID = ? and topic.id = topic_tag.topicID", tagID);
    }

    /* getter */
    public List<Tag> getTagList(){
        int topicID = this.getInt("id");
        return Tag.dao.getTagListByTopicID(topicID);
    }

    /* private */
    private void removeAllCache() {
        CacheKit.removeAll(TOPIC_CACHE);
        CacheKit.removeAll(TOPIC_PAGE_FOR_INDEX_CACHE);
        CacheKit.removeAll(TOPIC_PAGE_FOR_MODULE_CACHE);
        CacheKit.removeAll(UP_TOPIC_LIST_CACHE);
        CacheKit.removeAll(HOT_TOPIC_LIST_CACHE);
    }
}