package cn.iver.ext.beetlfunction;

import org.bee.tl.core.Context;
import org.bee.tl.core.Function;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-4-20
 */
public class IsSame implements Function {
    @Override
    public String call(Object[] params, Context ctx) {
        if (params.length != 3){
            throw new RuntimeException("length of params must be 3 !");
        }
        if (params[0] == null || params[1] == null){
            return null;
        }
        String one = (String) ctx.getVar(params[0].toString());
        String two = (String) ctx.getVar(params[1].toString());
        if (one == null || two == null){
            return null;
        }else if(one.equals(two)){
            return params[2].toString();
        }
        return null;
    }
}
