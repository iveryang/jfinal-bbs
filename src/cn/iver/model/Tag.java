package cn.iver.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-4-18
 */
public class Tag extends Model<Tag> {
    public static final Tag dao = new Tag();
    private static final String TAG_LIST_CACHE = "tagList";

    public List<Tag> getTagList(){
        return dao.findByCache(TAG_LIST_CACHE, 1, "select * from tag order by topicCount desc");
    }

}
