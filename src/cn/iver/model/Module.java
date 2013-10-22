package cn.iver.model;

import cn.iver.ext.jfinal.Model;
import com.jfinal.plugin.ehcache.CacheKit;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-26
 */
public class Module extends Model<Module> {
    public static final Module dao = new Module();
    private static final String MODULE_CACHE = "module";
    private static final String MODULE_LIST_CACHE = "moduleList";

    public Module() {
        super(MODULE_CACHE);
    }

    /* get */
    public Module get(int id){
        return loadModel(id);
    }
    public List<Module> getList(){
        return dao.findByCache(MODULE_LIST_CACHE, 1, "select * from module order by turn");
    }

    /* cache */
    public void removeAllCache(){
        CacheKit.removeAll(MODULE_CACHE);
        CacheKit.removeAll(MODULE_LIST_CACHE);
    }
}
