package cn.iver.kit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

import com.jfinal.kit.StringKit;

public class HtmlKit {
	
	// 只有纯文本可以通过
	public static String getText(String html) {
		if (html == null)
			return null;
		return Jsoup.clean(html, Whitelist.none());
	}
	
	// 以下标签可以通过
	// b, em, i, strong, u. 纯文本 
	public static String getSimpleHtml(String html) {
		if (html == null)
			return null;
		return Jsoup.clean(html, Whitelist.simpleText());
	}
	
	// 以下标签可以通过
	//a, b, blockquote, br, cite, code, dd, dl, dt, em, i, li, ol, p, pre, q, small, strike, strong, sub, sup, u, ul
	public static String getBasicHtml(String html) {
		if (html == null)
			return null;
		return Jsoup.clean(html, Whitelist.basic());
	}
	
	//在basic基础上  增加图片通过
	public static String getBasicHtmlAndImage(String html) {
		if (html == null)
			return null;
		return Jsoup.clean(html, Whitelist.basicWithImages());
	}
	// 以下标签可以通过	
	//a, b, blockquote, br, caption, cite, code, col, colgroup, dd, dl, dt, em, h1, h2, h3, h4, h5, h6, i, img, li, ol, p, pre, q, small, strike, strong, sub, sup, table, tbody, td, tfoot, th, thead, tr, u, ul
	public static String getFullHtml(String html) {
		if (html == null)
			return null;
		return Jsoup.clean(html, Whitelist.relaxed());
	}
	
	//只允许指定的html标签
	public static String clearTags(String html, String ...tags) {
		Whitelist wl = new Whitelist();
		return Jsoup.clean(html, wl.addTags(tags));
	}
	
	// 对关键字加上颜色
	public static String markKeywods (String keywords, String target) {
		if (StringKit.notBlank(keywords)) {
			String[] arr = keywords.split(" ");
			for (String s : arr) {
				if (StringKit.notBlank(s)) {
					String temp = "<span class=\"highlight\">" + s + "</span>";
					if(temp!=null)
						target = target.replaceAll(s, temp);
				}
			}
		}
		return target;
	}

	// 获取文章中的img url
	public static String getImgSrc(String html) {
		if (html == null)
			return null;
	    Document doc = Jsoup.parseBodyFragment(html);
	    Element image = doc.select("img").first();
	    return image == null ? null : image.attr("src");
	}
}
