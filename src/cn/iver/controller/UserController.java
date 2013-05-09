package cn.iver.controller;

import cn.iver.interceptor.LoginValidator;
import cn.iver.interceptor.RegistValidator;
import cn.iver.interceptor.UpdateUserValidator;
import cn.iver.interceptor.UserCheckInterceptor;
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

    @Before(LoginValidator.class)
    public void login(){
        String email = getPara("email");
        String password = getPara("password");
        if (StringKit.notBlank(email, password)){
            User user = User.dao.getUserByEmailAndPassword(email, password);
            if (user != null){
                setCookie("email", email, 3600*24*30);
                if (getParaToBoolean("rememberPassword")){
                    setCookie("password", password, 3600*24*30);
                }
                setSessionAttr("user", user);
                redirect("/");
            }
        }else{
            setAttr("msg", "用户名或密码错误");
            render("/user/login.html");
        }
    }
    public void logout(){
        removeSessionAttr("user");
        removeCookie("email");
        removeCookie("password");
        redirect("/");
    }

    @Before(RegistValidator.class)
    public void save(){
        User user = getModel(User.class);
        user.mySave();
        setAttr("msg", "恭喜你，注册成功，请登录：");
        render("/user/login.html");
    }

    @Before(UserCheckInterceptor.class)
    public void edit(){
        setAttr("user", User.dao.getUser(getParaToInt(0, 0)));
        render("/user/editUser.html");
    }

    @Before(UpdateUserValidator.class)
    public void update(){
        User user = getModel(User.class);
        user.myUpdate();
        setAttr("user", user);
        render("/user/user.html");
    }
}
