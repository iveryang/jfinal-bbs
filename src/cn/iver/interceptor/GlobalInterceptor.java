package cn.iver.interceptor;

import cn.iver.common.MyConstants;
import cn.iver.common.Myconfig;
import cn.iver.model.Module;
import cn.iver.model.Topic;
import cn.iver.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StringKit;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-3-30
 */
public class GlobalInterceptor implements Interceptor {
    @Override
    public void intercept(ActionInvocation ai) {
        Controller controller = ai.getController();
        controller.setAttr("moduleList", Module.dao.getModuleList());
        String email = controller.getCookie("email");
        String password = controller.getCookie("password");
        if(controller.getSessionAttr("user") == null && StringKit.notBlank(email, password)){
            User user = User.dao.getUserByEmailAndPassword(email, password);
            if(user != null){
                controller.getSession().setMaxInactiveInterval(3600);
                controller.setSessionAttr("user", user);
                if(email.equals(MyConstants.ADMIN_EMAIL)){
                    controller.setSessionAttr("isAdminLogin", "true");
                }
            }
        }
        ai.invoke();
    }
}
