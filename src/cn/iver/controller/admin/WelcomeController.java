package cn.iver.controller.admin;

import cn.iver.model.User;
import com.jfinal.core.Controller;
import com.jfinal.kit.StringKit;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-4-4
 */
public class WelcomeController extends Controller {
    public void index(){
        render("/admin/login.html");
    }
    public void login(){
        String username = getPara("username");
        String password = getPara("password");
        if (StringKit.notBlank(username) && StringKit.notBlank(password) && User.dao.login(username, password)){
            setCookie("username", username, 3600*24*3);
            setCookie("password", password, 3600*24*3);
            setSessionAttr("isAdminLogin", "true");
            redirect("/");
        }else{
            redirect("/admin/welcome");
        }
    }
}
