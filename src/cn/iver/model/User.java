package cn.iver.model;

import cn.iver.kit.HtmlTagKit;
import cn.iver.kit.ModelKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.ehcache.CacheKit;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: I have A dream
 * Date: 13-3-26
 */
public class User extends Model<User> {
    public static final User dao = new User();
    private static final String USER_CACHE = "user";
    private static final ModelKit mk = new ModelKit(dao, USER_CACHE);

    /* get */
    public User get(int id) {
        return mk.getModel(id);
    }
    public User getByEmailAndPassword(String email, String password){
        return dao.findFirst("select id, username, email, password from user where email=? and password=?", email, getMD5(password.getBytes()));
    }

    /* other */
    public void mySave(){
        HtmlTagKit.processHtmlSpecialTag(this, "username", "headImg", "blogUrl", "feeling");
        String password = getMD5(this.getStr("password").getBytes());
        this.set("password", password).set("registDate", new Date());
        this.save();
    }
    public void myUpdate() {
        HtmlTagKit.processHtmlSpecialTag(this, "username", "headImg", "blogUrl", "feeling");
        this.update();
        removeCache(this.getInt("id"));
    }
    public boolean containEmail(String email) {
        return dao.findFirst("select email from user where email=? limit 1", email) != null;
    }
    public boolean containUsername(String username) {
        return dao.findFirst("select username from user where username=? limit 1", username) != null;
    }
    public boolean containEmailExceptThis(int userID, String email) {
        return dao.findFirst("select email from user where email=? and id!=? limit 1", email, userID) != null;
    }
    public boolean containUsernameExceptThis(int userID, String username) {
        return dao.findFirst("select username from user where username=? and id!=? limit 1", username, userID) != null;
    }

    /* private */
    private String getMD5(byte[] src){
        StringBuffer sb=new StringBuffer();
        try {
            java.security.MessageDigest md=java.security.MessageDigest.getInstance("MD5");
            md.update(src);
            for(byte b : md.digest())
                sb.append(Integer.toString(b>>>4&0xF,16)).append(Integer.toString(b&0xF,16));
        } catch (NoSuchAlgorithmException e) {}
        return sb.toString();
    }
    private void removeCache(int id){
        CacheKit.remove(USER_CACHE, id);
    }
}
