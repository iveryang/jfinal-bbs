package cn.iver.controller.admin;

import cn.iver.model.User;
import com.jfinal.core.Controller;
import com.jfinal.kit.StringKit;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-4-4
 */
public class AdminController extends Controller {
    public void index(){
        render("/admin/login.html");
    }
    public void login(){
        String email = getPara("email");
        setCookie("email", email, 3600*24*30);
        String password = getPara("password");
        if (StringKit.notBlank(email) && StringKit.notBlank(password)){
            User user = User.dao.getUserByEmailAndPassword(email, password);
            if (user != null){
                setCookie("password", password, 3600*24*10);
                setSessionAttr("isAdminLogin", "true");
                setSessionAttr("user", user);
                redirect("/");
            }
        }else{
            redirect("/admin/welcome");
        }
    }
}
