package cn.iver.controller;

import cn.iver.common.Const;
import cn.iver.interceptor.UserCheckInterceptor;
import cn.iver.model.User;
import cn.iver.validator.LoginValidator;
import cn.iver.validator.RegistValidator;
import cn.iver.validator.UpdateUserValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-5-6
 */
public class UserController extends Controller {
    public void index(){
        setAttr("user", User.dao.get(getParaToInt(0, 0)));
        render("/user/user.html");
    }

    @Before(LoginValidator.class)
    public void login(){
        String email = getPara("email");
        String password = getPara("password");
        User user = User.dao.getByEmailAndPassword(email, password);
        if (user != null){
            String bbsID = email + Const.BBS_ID_SEPARATOR + password;
            setCookie("bbsID", bbsID, 3600*24*30);
            setSessionAttr("user", user);
            setSessionAttr("userID", user.get("id"));
            redirect("/");
        }else{
            setAttr("msg", "用户名或密码错误");
            render("/user/login.html");
        }
    }

    public void logout(){
        removeSessionAttr("user");
        removeSessionAttr("userID");
        removeCookie("bbsID");
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
        setAttr("user", User.dao.get(getParaToInt(0, 0)));
        render("/user/edit.html");
    }

    @Before(UpdateUserValidator.class)
    public void update(){
        User user = getModel(User.class);
        user.myUpdate();
        setAttr("user", user);
        render("/user/user.html");
    }
}
