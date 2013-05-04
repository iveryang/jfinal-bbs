package cn.iver.interceptor;

import cn.iver.model.User;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-5-3
 */
public class UserValidator extends Validator {
    @Override
    protected void validate(Controller c) {
        validateEmail("user.email", "emailMsg", "错误的邮箱地址");
        validateRegex("user.username", "[a-zA-Z0-9_\\u4e00-\\u9fa5]{2,8}", "usernameMsg", "用户名的长度介于2-8之间，只能包含中文，数字，字母，下划线");
        validateRegex("user.password", "[a-zA-Z0-9_]{6,12}", "passwordMsg", "密码的长度介于2-8之间，只能包含数字，字母，下划线");
        validateEqualField("user.password", "repassword", "repasswordMsg", "2次输入的密码不一致");
    }

    @Override
    protected void handleError(Controller c) {
        c.keepModel(User.class);
        c.render("/user/regist.html");
    }
}
