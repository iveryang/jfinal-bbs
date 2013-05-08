package cn.iver.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-5-7
 */
public class LoginInterceptor implements Interceptor {
    @Override
    public void intercept(ActionInvocation ai) {
        Controller controller = ai.getController();
        if(controller.getSessionAttr("user") != null){
            ai.invoke();
        }else{
            controller.setAttr("msg", "需要登录才可以进行改操作：）");
            controller.render("/user/login.html");
        }
    }
}
