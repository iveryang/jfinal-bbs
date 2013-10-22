package cn.iver.kit;

import com.jfinal.kit.StringKit;
import com.jfinal.plugin.activerecord.Model;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-4-4
 */
public class HtmlTagKit {
//    private static final Pattern SIMPLE_IMG_PATTERN = Pattern.compile("\\[\\d{2,3}]");
//    private static final String IMG_SRC_FRONT = "<img src='/img/emoticon/";
//    private static final String IMG_SRC_BHIND = ".gif'";

//    public static String restoreImgSrc(String content){
//        Matcher matcher = SIMPLE_IMG_PATTERN.matcher(content);
//        while(matcher.find()){
//            String result = matcher.group();
//            String faceNumber = result.replaceAll("\\[", "").replaceAll("]", "");
//            content = matcher.replaceAll(IMG_SRC_FRONT + faceNumber + IMG_SRC_BHIND);
//        }
//        return content;
//    }

    public static void processHtmlSpecialTag(Model model, String... attrNames){
        for (String attrName : attrNames) {
            String content = model.getStr(attrName);
            model.set(attrName, processHtmlSpecialTag(content));
        }
    }

    public static String processHtmlSpecialTag(String content){
        if(StringKit.notBlank(content)){
            return content.trim().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        }
        return null;
    }

    public static void processHtmlXSSTag(Model model, String... attrNames){
        for (String attrName : attrNames) {
            String content = model.getStr(attrName);
            if(StringKit.notBlank(content)){
                String temp = Jsoup.clean(content, Whitelist.basicWithImages().addTags("embed"));
                model.set(attrName, temp);
            }
        }
    }
}
