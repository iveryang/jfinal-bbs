package cn.iver.interceptor;

import cn.iver.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-5-7
 */
public class UserCheckInterceptor implements Interceptor {
    @Override
    public void intercept(ActionInvocation ai) {
        Controller controller = ai.getController();
        User user = controller.getSessionAttr("user");
        if(user.getInt("id") == controller.getAttrForInt("user.id")){
            ai.invoke();
        }else{
            controller.setAttr("msg", "只有该登录用户本人才有权操作");
            controller.renderError(500);
        }
    }
}
