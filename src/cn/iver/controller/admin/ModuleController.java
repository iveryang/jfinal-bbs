package cn.iver.controller.admin;

import cn.iver.interceptor.AdminInterceptor;
import cn.iver.model.Module;
import cn.iver.model.SubModule;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-29
 */
@Before(AdminInterceptor.class)
public class ModuleController extends Controller {

    public void index(){
        Module.dao.removeCache();
        setAttr("moduleList", Module.dao.getModuleList());
        render("/admin/module.html");
    }

    public void save(){
        getModel(Module.class).save();
        index();
    }

    public void edit(){
        setAttr("module", Module.dao.findById(getParaToInt()));
        index();
    }

    public void update(){
        getModel(Module.class).update();
        index();
    }

    public void delete(){
        Module.dao.deleteById(getParaToInt());
        index();
    }

    /* Sub Module */
    private int currentModuleID = 0;

    public void indexSub(){
        if (getPara("flag") != null){
            currentModuleID = getParaToInt(0);
        }
        setAttr("subModuleList", SubModule.dao.getSubModuleList(currentModuleID));
        setAttr("currentModule", Module.dao.findById(currentModuleID));
        index();
    }

    public void saveSub(){
        SubModule subModule = getModel(SubModule.class);
        subModule.save();
        currentModuleID = subModule.getInt("moduleID");
        indexSub();
    }

    public void editSub(){
        setAttr("subModule", SubModule.dao.findById(getParaToInt(1)));
        currentModuleID = getParaToInt(0);
        indexSub();
    }

    public void updateSub(){
        SubModule subModule = getModel(SubModule.class);
        subModule.update();
        currentModuleID = subModule.getInt("moduleID");
        indexSub();
    }

    public void deleteSub(){
        SubModule.dao.deleteById(getParaToInt(1));
        currentModuleID = getParaToInt(0);
        indexSub();
    }
}
