package cn.iver.model;

import cn.iver.kit.HtmlTagKit;
import com.jfinal.kit.StringKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

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

    public User getUser(Integer id) {
        final int ID = id;
        return CacheKit.get(USER_CACHE, ID, new IDataLoader() {
            @Override
            public Object load() {
                return dao.findById(ID);
            }
        });
    }
    public void mySave(){
        String password = getMD5(this.getStr("password").getBytes());
        this.set("password", password).set("registDate", new Date());
        this.processHtmlSpecilTag();
        this.save();
        removeCache(this.getInt("id"));
    }
    public void myUpdate() {
        this.processHtmlSpecilTag();
        this.update();
        removeCache(this.getInt("id"));
    }
    public User getUserByEmailAndPassword (String email, String password){
        return dao.findFirst("select id, username, email, password from user where email=? and password=?", email, getMD5(password.getBytes()));
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
    private void processHtmlSpecilTag(){
        this.set("feeling", HtmlTagKit.processHtmlSpecialTag(this.getStr("feeling")));
    }
}
