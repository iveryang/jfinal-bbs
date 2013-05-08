package cn.iver.controller.admin;

import com.jfinal.core.Controller;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-4-4
 */
public class AdminController extends Controller {
    public void index(){
        render("/admin/login.html");
    }
}
