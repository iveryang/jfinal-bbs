package cn.iver.common;

import org.mvel2.MVEL;
import org.mvel2.compiler.ExecutableAccessor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-3-28
 */
public class MyConstants {
    // page size
    public static int TOPIC_PAGE_SIZE;
    public static int POST_PAGE_SIZE;
    public static int REPLY_PAGE_SIZE;
    public static int SIDEBAR_TOPIC_SIZE;
    public static int PAGE_SIZE_FOR_ADMIN;
    // others
    public static String ADMIN_EMAIL;

    public static void main(String[] args) {
        Map<String, Object> m = new HashMap<String, Object>();
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("foo", "bar");
        m.put("props", props);
        ExecutableAccessor compiled = (ExecutableAccessor)MVEL.compileExpression("props.foo");
        //Object result = MVEL.eval("props.foo", m);
        Object result = MVEL.executeExpression(compiled, m);
        System.out.println(result);
    }
}
