package cn.iver.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-29
 */
public class SubModule extends Model<SubModule> {
    public static final SubModule dao = new SubModule();

    public List<SubModule> getSubModuleList(int moduleID){
        return SubModule.dao.find("select * from sub_module where moduleID=?",moduleID);
    }
}
