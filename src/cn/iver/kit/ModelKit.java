package cn.iver.kit;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-5-18
 */
public class ModelKit {
    public static <M> Page<M> loadModelPage(Page<M> page, String cacheName, Model dao) {
        List<M> modelList = page.getList();
        for(int i = 0; i < modelList.size(); i++){
            Model model = (Model)modelList.get(i);
            M topic = getModel(model.getInt("id"), cacheName, dao);
            modelList.set(i, topic);
        }
        return page;
    }
    public static <M> M getModel(int id, String cacheName, Model dao) {
        final int ID = id;
        final Model DAO = dao;
        return CacheKit.get(cacheName, ID, new IDataLoader() {
            @Override
            public Object load() {
                return DAO.findById(ID);
            }
        });
    }
}
