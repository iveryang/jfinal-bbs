package cn.iver.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-26
 */
public class Module extends Model<Module> {
    public static final Module dao = new Module();
    private final String MODULE_CACHE = "module";
    private final String MODULE_LIST_CACHE = "moduleList";

    public Module getModule(int moduleID){
        final int MODULE_ID = moduleID;
        return CacheKit.get(MODULE_CACHE, MODULE_ID, new IDataLoader() {
            @Override
            public Object load() {
                return dao.findById(MODULE_ID);
            }
        });
    }
    public List<Module> getModuleList(){
        return dao.findByCache(MODULE_LIST_CACHE, 1, "select * from module order by turn");
    }
    public void removeCache(){
        CacheKit.removeAll(MODULE_CACHE);
        CacheKit.removeAll(MODULE_LIST_CACHE);
    }
}
