package cn.iver.validator;

import cn.iver.model.User;
import com.jfinal.core.Controller;
import com.jfinal.kit.StringKit;
import com.jfinal.validate.Validator;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-5-3
 */
public class RegistValidator extends Validator {
    @Override
    protected void validate(Controller c) {
        validateEmail("user.email", "emailMsg", "错误的邮箱地址");
        validateRegex("user.username", "[a-zA-Z0-9_\\u4e00-\\u9fa5]{2,8}", "usernameMsg", "用户名的长度介于2-8之间，只能包含中文，数字，字母，下划线");
        validateRegex("user.password", "[a-zA-Z0-9_]{6,12}", "passwordMsg", "密码的长度介于6-12之间，只能包含数字，字母，下划线");
        validateEqualField("user.password", "repassword", "repasswordMsg", "2次输入的密码不一致");
        String email = c.getPara("user.email");
        if(StringKit.notBlank(email) && User.dao.containEmail(email)){
            addError("emailMsg", "该email已经被注册过了：（");
        }
        String username = c.getPara("user.username");
        if(StringKit.notBlank(username) && User.dao.containUsername(username)){
            addError("usernameMsg", "该用户名已经被注册过了：（");
        }
    }

    @Override
    protected void handleError(Controller c) {
        c.keepModel(User.class);
        c.render("/user/regist.html");
    }
}
