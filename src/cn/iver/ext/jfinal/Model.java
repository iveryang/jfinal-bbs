package cn.iver.ext.jfinal;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-5-18
 */
public class Model<M extends com.jfinal.plugin.activerecord.Model> extends com.jfinal.plugin.activerecord.Model<M> {
    private String cacheNameForModel;

    public Model(String cacheNameForModel){
        this.cacheNameForModel = cacheNameForModel;
    }

    public Page<M> loadModelPage(Page<M> page) {
        List<M> modelList = page.getList();
        for(int i = 0; i < modelList.size(); i++){
            com.jfinal.plugin.activerecord.Model model = modelList.get(i);
            M topic = loadModel(model.getInt("id"));
            modelList.set(i, topic);
        }
        return page;
    }

    public M loadModel(int id) {
        final int ID = id;
        return (M)CacheKit.get(cacheNameForModel, ID, new IDataLoader() {
            @Override
            public Object load() {
                return findById(ID);
            }
        });
    }

}
