package cn.iver.validator;

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
        validateString("reply.content", 1, 200, "msg", "回复内容不能为空，且不超过200字");
    }

    @Override
    protected void handleError(Controller c) {
        c.renderText("error");
    }
}
