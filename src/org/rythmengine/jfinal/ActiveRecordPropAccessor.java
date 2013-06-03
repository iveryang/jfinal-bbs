package org.rythmengine.jfinal;

import com.jfinal.plugin.activerecord.Model;
import org.rythmengine.extension.IPropertyAccessor;

/**
 * Created with IntelliJ IDEA.
 * User: luog
 * Date: 30/05/13
 * Time: 11:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActiveRecordPropAccessor implements IPropertyAccessor {
    @Override
    public Class getTargetType() {
        return com.jfinal.plugin.activerecord.Model.class;
    }

    @Override
    public Object getProperty(String name, Object contextObj) {
        Model model = (Model)contextObj;
        if (!name.contains("attr")) {
            return model.get(name);
        }
        if ("attrNames".equalsIgnoreCase(name)) {
            return model.getAttrNames();
        } else if ("attrsEntrySet".equalsIgnoreCase(name)) {
            return model.getAttrsEntrySet();
        } else if ("attrValues".equalsIgnoreCase(name)) {
            return model.getAttrValues();
        }
        return model.get(name);
    }

    @Override
    public Object setProperty(String name, Object contextObj, Object value) {
        Model model = (Model)contextObj;
        if (!name.contains("attr")) {
            model.put(name, value);
        }
        if ("attrNames".equalsIgnoreCase(name) || "attrsEntrySet".equalsIgnoreCase(name) || "attrValues".equalsIgnoreCase(name)) {
            throw new UnsupportedOperationException();
        }
        model.put(name, value);
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
