package cn.iver.model;

import cn.iver.common.MyConstants;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-4-18
 */
public class TopicTag extends Model<TopicTag> {
    public static final TopicTag dao = new TopicTag();
    private static final String TAG_TOPIC_PAGE_CACHE = "tagTopicPage";
    private static final String CACHE_KEY_SEPARATE = "-";

    public Page<Topic> getTopicPageByTagID(int tagID, int pageNumber){
        return Topic.dao.paginateByCache( TAG_TOPIC_PAGE_CACHE, tagID + CACHE_KEY_SEPARATE + pageNumber,
                pageNumber, MyConstants.PAGE_SIZE,
                "select topic.* ", "from topic, topic_tag where topic_tag.tagID = ? and topic.id = topic_tag.topicID", tagID);
    }
}
