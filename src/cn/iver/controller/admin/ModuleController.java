package cn.iver.controller.admin;

import cn.iver.interceptor.AdminInterceptor;
import cn.iver.model.Module;
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
}
