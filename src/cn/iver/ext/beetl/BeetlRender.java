package cn.iver.ext.beetl;

import com.jfinal.render.Render;
import com.jfinal.render.RenderException;
import org.bee.tl.core.GroupTemplate;
import org.bee.tl.core.Template;
import org.bee.tl.ext.spring.WebVariable;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Enumeration;

public class BeetlRender extends Render {
    private GroupTemplate gt = null;
    private transient static final String encoding = getEncoding();
    private transient static final String contentType = "text/html; charset=" + encoding;

    public BeetlRender(GroupTemplate gt, String view) {
        this.gt = gt;
        this.view = view;
    }

    @Override
    public void render() {
        Writer writer = null;
        OutputStream os = null;
        try {
            response.setContentType(contentType);
            Template template = gt.getFileTemplate(view);
            Enumeration<String> attrs = request.getAttributeNames();
            while (attrs.hasMoreElements()) {
                String attrName = attrs.nextElement();
                template.set(attrName, request.getAttribute(attrName));

            }
            WebVariable webVariable = new WebVariable();
            webVariable.setRequest(request);
            webVariable.setResponse(response);
            webVariable.setSession(request.getSession());
            template.set("servlet", webVariable);
            template.set("request", request);
            template.set("ctxPath", request.getContextPath());
            if (gt.isDirectByteOutput()) {
                os = response.getOutputStream();
                template.getText(os);
            } else {
                writer = response.getWriter();
                template.getText(writer);
            }

        } catch (Exception e) {
            throw new RenderException(e);
        } finally {
            try {
                if (writer != null)
                    writer.close();
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
