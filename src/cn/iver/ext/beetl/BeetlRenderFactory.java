package cn.iver.ext.beetl;

import cn.iver.common.Const;
import com.jfinal.kit.PathKit;
import com.jfinal.render.IMainRenderFactory;
import com.jfinal.render.Render;
import org.bee.tl.core.GroupTemplate;

import java.io.File;

public class BeetlRenderFactory implements IMainRenderFactory {
    public static String viewExtension = ".html";
    public static GroupTemplate gt = null;

    public BeetlRenderFactory(boolean isLocal) {
        File file = new File(PathKit.getWebRootPath() + Const.BEETL_ROOT_DIR);
        gt = new GroupTemplate(file);
        if (isLocal){
            gt.enableChecker(2);
        } else {
            gt.enableChecker(0);
        }
        gt.setCharset("UTF-8");
        gt.registerFunction("isSame", new IsSameFunction());
        gt.registerFunction("printTime", new PrintTimeFunction());
        gt.setStatementStart("@");
        gt.setStatementEnd(null);
    }

    public Render getRender(String view) {
        return new BeetlRender(gt, view);
    }

    public String getViewExtension() {
        return viewExtension;
    }

}
