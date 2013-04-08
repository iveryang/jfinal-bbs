package cn.iver.interceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-4-4
 */
public class ReplyValidator extends Validator {
    @Override
    protected void validate(Controller c) {
        validateString("reply.userName", 1, 10, "msg", "不合理的输入啊");
        validateString("reply.content", 1, 200, "msg", "不合理的输入啊");
    }

    @Override
    protected void handleError(Controller c) {
        c.renderText("error");
    }
}
