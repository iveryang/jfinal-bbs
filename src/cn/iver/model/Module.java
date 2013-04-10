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
    private final String MODULE_LIST_CACHE = "moduleList";

    private List<SubModule> subModuleList;

    public List<Module> getModuleList(){
        return CacheKit.get(MODULE_LIST_CACHE, MODULE_LIST_CACHE, new IDataLoader() {
            @Override
            public Object load() {
                List<Module> moduleList = dao.find("select * from module order by turn");
                for (Module module : moduleList) {
                    module.subModuleList = SubModule.dao.find("select * from sub_module where moduleID=? order by turn", module.get("id"));
                }
                return moduleList;
            }
        });
    }
    public List<SubModule> getSubModuleList(){
        return this.subModuleList;
    }
    public void removeCache(){
        CacheKit.removeAll(MODULE_LIST_CACHE);
    }
}
