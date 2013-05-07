package cn.iver.kit;

import cn.iver.model.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-5-5
 */
public class ReferUserKit {
    private static Pattern referer_pattern = Pattern.compile("@.+?[\\s:]");

    /**
     * 处理提到某人 @xxxx
     * @param msg  传入的文本内容
     * @param referers 传出被引用到的会员名单
     * @return 返回带有链接的文本内容
     */
    public static String _GenerateRefererLinks(String msg, List<Long> referers) {
//        StringBuilder html = new StringBuilder();
//        int lastIdx = 0;
//        Matcher matchr = referer_pattern.matcher(msg);
//        while (matchr.find()) {
//            String origion_str = matchr.group();
//            String str = origion_str.substring(1, origion_str.length()).trim();
//            html.append(msg.substring(lastIdx, matchr.start()));
//
//            User u = null;
//            @SuppressWarnings("unchecked")
//            List<User> users = User.INSTANCE.LoadList(User.CheckUsername(str));
//            if(users != null && users.size()>0){
//                u = users.get(0);
//                for(User ref : users){
//                    if(ref.getThis_login_time()!=null && u.getThis_login_time()!=null &&
//                            ref.getThis_login_time().after(u.getThis_login_time())){
//                        u = ref;
//                    }
//                }
//            }
//            if(u == null){
//                u = User.GetByIdent(str);
//            }
//
//            if(u != null && !u.IsBlocked()){
//                html.append("<a href='"+LinkTool.user(u)+"' class='referer' target='_blank'>@");
//                html.append(str.trim());
//                html.append("</a> ");
//                if(referers != null && !referers.contains(u.getId()))
//                    referers.add(u.getId());
//            }
//            else{
//                html.append(origion_str);
//            }
//            lastIdx = matchr.end();
//            //if(ch == ':' || ch == ',' || ch == ';')
//            //	html.append(ch);
//        }
//        html.append(msg.substring(lastIdx));
//        return html.toString();
        return null;
    }
}
