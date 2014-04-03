package cn.iver.interceptor;

import cn.iver.common.Const;
import cn.iver.model.Module;
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

        controller.setAttr("moduleList", Module.dao.getList());
        // validate user info from bbs_id
        if(controller.getSessionAttr("user") == null && StringKit.notBlank(controller.getCookie("bbsID"))){
            String bbsID = controller.getCookie("bbsID");
            if(StringKit.notBlank(bbsID)){
                String[] userAndEmail = bbsID.split(Const.BBS_ID_SEPARATOR);
                User user = null;
                if(userAndEmail != null && userAndEmail.length == 2){
                    user = User.dao.getByEmailAndPassword(userAndEmail[0], userAndEmail[1]);
                }
                if(user != null){
                    controller.getSession().setMaxInactiveInterval(1800);
                    controller.setSessionAttr("user", user);
                    controller.setSessionAttr("userID", user.get("id"));
                }else{
                    ai.getController().removeCookie("bbsID");
                }
            }
        }
        ai.invoke();
        controller.setAttr("v", Const.TIMESTAMP);
    }
}
