package cn.iver.interceptor;

import cn.iver.common.Const;
import cn.iver.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StringKit;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-4-4
 */
public class AdminInterceptor implements Interceptor {
    @Override
    public void intercept(ActionInvocation ai) {
        Controller controller = ai.getController();
        User user = controller.getSessionAttr("user");
        if (user != null && Const.ADMIN_EMAIL.equals(user.getStr("email"))){
            ai.invoke();
        }else{
            controller.setAttr("msg", "需要管理员权限");
            controller.renderError(500);
        }
    }
}
