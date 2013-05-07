package cn.iver.model;

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
        this.save();
        removeCache(this.getInt("id"));
    }
    public void myUpdate() {
        this.update();
        removeCache(this.getInt("id"));
    }
    public User getUserByEmailAndPassword (String email, String password){
        return dao.findFirst("select * from user where email=? and password=?", email, getMD5(password.getBytes()));
    }
    public boolean containEmail(String email) {
        return dao.findFirst("select email from user where email=? limit 1", email) != null;
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
