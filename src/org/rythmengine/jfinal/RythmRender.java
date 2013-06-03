package org.rythmengine.jfinal;

import cn.iver.common.RythmHelper;
import cn.iver.ext.beetl.PrintTime;
import com.jfinal.render.Render;
import com.jfinal.render.RenderException;
import org.rythmengine.Rythm;
import org.rythmengine.RythmEngine;
import org.rythmengine.template.ITemplate;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.rythmengine.conf.RythmConfigurationKey.*;

/**
 * Created with IntelliJ IDEA.
 * User: luog
 * Date: 28/05/13
 * Time: 9:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class RythmRender extends Render {

    private static RythmEngine engine;
    
    public static void init() {
        try {
            String s = RythmRender.class.getResource("/").getFile();
            File f = new File(new File(s).getParentFile().getCanonicalFile(), "rythm");
            Map<String, Object> conf = new HashMap<String, Object>();
            conf.put(HOME_TEMPLATE.getKey(), f);
            conf.put(HOME_TMP.getKey(), "c:/tmp");
            conf.put(ENGINE_MODE.getKey(), Rythm.Mode.dev);
            engine = new RythmEngine(conf);
            engine.registerPropertyAccessor(RythmHelper.postPropAccessor, RythmHelper.topicAccessor, RythmHelper.replyPropAccessor, new ActiveRecordPropAccessor());
            engine.registerTransformer(PrintTime.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void init(ServletContext servletContext) {
        Map<String, Object> conf = new HashMap<String, Object>();
        for (Enumeration<String> e = servletContext.getInitParameterNames(); e.hasMoreElements();){
            String k = e.nextElement();
            conf.put(k, servletContext.getInitParameter(k));
        }
        engine = new RythmEngine(conf);
    }
    
    public RythmRender(String view) {
        this.view = view;
    } 
    
    @Override
    public void render() {
        ITemplate template = engine.getTemplate(view);
        for (Enumeration<String> e = request.getAttributeNames();e.hasMoreElements();) {
            String k = e.nextElement();
            template.__setRenderArg(k, request.getAttribute(k));
        }
        template.__setRenderArg("request", request);
        template.__setRenderArg("response", response);
        PrintWriter w = null;
        try {
            response.setContentType("text/html; charset=utf-8"); 
            w = response.getWriter();
            w.write(template.render());
        } catch (Exception e) {
            throw new RenderException(e);
        } finally {
            if (null != w) {
                try {w.close();} catch (Exception e) {}
            }
        }
    }
}
