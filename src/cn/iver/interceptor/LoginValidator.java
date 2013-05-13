package cn.iver.interceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-5-8
 */
public class LoginValidator extends Validator{
    @Override
    protected void validate(Controller c) {
        validateEmail("email", "msg", "错误的邮箱地址");
        validateString("password", 6, 12, "msg", "密码不能为空(6-12)");
    }

    @Override
    protected void handleError(Controller c) {
        c.keepPara("email");
        c.render("/user/login.html");
    }
}
