package cn.iver.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.kit.StringKit;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-4-4
 */
public class AdminInterceptor implements Interceptor {
    @Override
    public void intercept(ActionInvocation ai) {
        String isAdminLogin = ai.getController().getSessionAttr("isAdminLogin");
        if (StringKit.notBlank(isAdminLogin) && isAdminLogin.equals("true")){
            ai.invoke();
        }else{
            ai.getController().setAttr("msg", "权限不够啊");
            ai.getController().renderError500();
        }
    }
}
