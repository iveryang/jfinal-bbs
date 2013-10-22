package cn.iver.validator;

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
        validateRequired("password", "msg", "密码不能为空");
    }

    @Override
    protected void handleError(Controller c) {
        c.keepPara("email");
        c.render("/user/login.html");
    }
}
