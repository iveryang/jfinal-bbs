package cn.iver.interceptor;

import cn.iver.model.Module;
import cn.iver.model.Topic;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-3-30
 */
public class GlobalInterceptor implements Interceptor {
    @Override
    public void intercept(ActionInvocation ai) {
        // 导航标题
        ai.getController().setAttr("moduleList", Module.dao.getModuleList());
        // 侧边栏列表
        ai.getController().setAttr("upTopicList", Topic.dao.getUpTopic());
        ai.getController().setAttr("hotTopicList", Topic.dao.getHotTopic());
        ai.invoke();
    }
}
