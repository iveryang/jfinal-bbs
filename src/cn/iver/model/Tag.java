package cn.iver.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-4-18
 */
public class Tag extends Model<Tag> {
    public static final Tag dao = new Tag();
    private static final String TAG_LIST_CACHE = "tagList";
    private static final String TAG_LIST_FOR_TOPIC_CACHE = "tagListForTopic";
    private static final String TAG_CACHE = "tag";

    public List<Tag> getTagList(){
        return dao.findByCache(TAG_LIST_CACHE, 1, "select * from tag order by topicCount desc");
    }
    public Tag getTagByTagName(String tagName){
        final String TAG_NAME = tagName;
        return CacheKit.get(TAG_CACHE, tagName, new IDataLoader() {
            @Override
            public Object load() {
                return dao.find("select * from tag where tagName = ?", TAG_NAME);
            }
        });
    }
    public void removeAllCache(){
        CacheKit.removeAll(TAG_LIST_CACHE);
        CacheKit.removeAll(TAG_LIST_FOR_TOPIC_CACHE);
        CacheKit.removeAll(TAG_CACHE);
    }
    public List<Tag> getTagListByTopicID(int topicID) {
        return dao.findByCache(TAG_LIST_FOR_TOPIC_CACHE, topicID,
                "select tag.* from tag, topic_tag where topic_tag.topicID = ? and tag.id = topic_tag.tagID", topicID);
    }
}
