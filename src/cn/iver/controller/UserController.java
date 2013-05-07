package cn.iver.controller;

import cn.iver.interceptor.UserValidator;
import cn.iver.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.StringKit;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-5-6
 */
public class UserController extends Controller {
    public void index(){
        setAttr("user", User.dao.getUser(getParaToInt(0, 0)));
        render("/user/user.html");
    }
    public void login(){
        String email = getPara("email");
        setCookie("email", email, 3600*24*30);
        String password = getPara("password");
        if (StringKit.notBlank(email) && StringKit.notBlank(password)){
            User user = User.dao.getUserByEmailAndPassword(email, password);
            if (user != null){
                if (getParaToBoolean("rememberPassword")){
                    setCookie("password", password, 3600*24*10);
                }
                getSession().setMaxInactiveInterval(3600);
                setSessionAttr("user", user);
                redirect("/");
            }
        }else{
            redirect("/login");
        }
    }

    @Before(UserValidator.class)
    public void save(){
        if (User.dao.containEmail(getPara("user.email"))){
            setAttr("emailMsg", "该email已经被注册过了：（");
        }else{
            User user = getModel(User.class);
            user.mySave();
            setSessionAttr("user", user);
            redirect("/");
        }
    }
    public void edit(){
        setAttr("user", User.dao.getUser(getParaToInt(0, 0)));
        render("/user/editUser.html");
    }
    public void update(){
        getModel(User.class).myUpdate();
        render("/user/user.html");
    }
}
