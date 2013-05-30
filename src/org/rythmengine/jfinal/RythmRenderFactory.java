package org.rythmengine.jfinal;

import com.jfinal.render.IMainRenderFactory;
import com.jfinal.render.Render;

/**
 * Created with IntelliJ IDEA.
 * User: luog
 * Date: 28/05/13
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class RythmRenderFactory implements IMainRenderFactory {

    public RythmRenderFactory() {
        RythmRender.init();
    }

    @Override

    public Render getRender(String view) {
        return new RythmRender(view);
    }

    @Override
    public String getViewExtension() {
        return ".html";
    }
}
