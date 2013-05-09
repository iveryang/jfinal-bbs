package cn.iver.kit;

import com.jfinal.kit.StringKit;

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

    public static String processHtmlSpecialTag(String content){
        if(StringKit.notBlank(content)){
            return content.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        }
        return null;
    }
}
