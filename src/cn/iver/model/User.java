package cn.iver.model;

import com.jfinal.plugin.activerecord.Model;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: I have A dream
 * Date: 13-3-26
 */
public class User extends Model<User> {
    public static final User dao = new User();
    private static final String keyMap = "C2E8D9A3B5F14607";

    public boolean login (String username, String password){
        User user = dao.findFirst("select * from user where username=? and password=?", username, getMD5(password.getBytes()));
        if(user == null){
            return false;
        }
        return true;
    }
    public void createNewUser(){
        String password = getMD5(this.getStr("password").getBytes());
        this.set("password", password).set("registDate", new Date());
        this.save();
    }

    /* private */
    private String getMD5(byte[] bytes){
        java.security.MessageDigest md = null;
        try {
            md = java.security.MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(bytes);
        byte bt, tmp[] = md.digest();
        char str[] = new char[32];
        int k = 0;
        for (int i = 0; i < 16; i++) {
            bt = tmp[i];
            str[k++] = keyMap.charAt(bt >>> 4 & 0xf);
            str[k++] = keyMap.charAt(bt & 0xf);
        }
        return new String(str);
    }

}
