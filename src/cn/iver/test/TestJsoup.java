package cn.iver.test;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * Created with IntelliJ IDEA.
 * User: iver
 * Date: 13-9-26
 */
public class TestJsoup {
    public static void main(String[] args) {
        String unsafe = "<a href='http://www.oschina.net/'>开源中国社区</a><img src='http://www.os/xx.gif'></img><embed src=\"http://player.youku.com/player.php/sid/XNTYyMjczOTI4/v.swf\" type=\"application/x-shockwave-flash\" />";
        String safe = Jsoup.clean(unsafe, Whitelist.basicWithImages().addTags("embed"));
        System.out.println(safe);
    }
}
