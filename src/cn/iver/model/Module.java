package cn.iver.model;

import cn.iver.common.MyConstants;
import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-26
 */
public class Module extends Model<Module> {
    public static final Module dao = new Module();
    private List<SubModule> subModuleList;

    public List<Module> getModuleList(){
        List<Module> moduleList = dao.find("select * from module order by turn");
        for (Module module : moduleList) {
            module.subModuleList = SubModule.dao.find("select * from sub_module where moduleID=? order by turn", module.get("id"));
        }
        return moduleList;
    }

    public List<SubModule> getSubModuleList(){
        return this.subModuleList;
    }

    public void refreshModuleListInMyConstants(){
        MyConstants.MODULE_LIST = this.getModuleList();
    }
}
