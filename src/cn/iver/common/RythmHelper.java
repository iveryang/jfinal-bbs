package cn.iver.common;

import cn.iver.model.*;
import org.rythmengine.extension.IPropertyAccessor;
import org.rythmengine.jfinal.ActiveRecordPropAccessor;

/**
 * Created with IntelliJ IDEA.
 * User: luog
 * Date: 30/05/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class RythmHelper {
    
    public static final IPropertyAccessor postPropAccessor = new ActiveRecordPropAccessor() {
        @Override
        public Class getTargetType() {
            return Post.class;
        }

        @Override
        public Object getProperty(String name, Object contextObj) {
            Post p = (Post)contextObj;
            if ("user".equalsIgnoreCase(name)) {
                return p.getUser();
            } else if ("ReplyPage".equalsIgnoreCase(name)) {
                return p.getReplyPage();
            } else if ("topic".equalsIgnoreCase(name)) {
                return p.getTopic();
            }
            return super.getProperty(name, contextObj);
        }
    };
    
    public static final IPropertyAccessor replyPropAccessor = new ActiveRecordPropAccessor() {
        @Override
        public Class getTargetType() {
            return Reply.class;
        }

        @Override
        public Object getProperty(String name, Object contextObj) {
            Reply p = (Reply)contextObj;            
            if ("user".equalsIgnoreCase(name)) {
                return p.getUser();
            } else if ("topic".equalsIgnoreCase(name)) {
                return p.getTopic();
            }
            return super.getProperty(name, contextObj);
        }
    };
    
    public static final IPropertyAccessor topicAccessor = new ActiveRecordPropAccessor() {
        @Override
        public Class getTargetType() {
            return Topic.class;
        }

        @Override
        public Object getProperty(String name, Object contextObj) {
            Topic p = (Topic)contextObj;            
            if ("user".equalsIgnoreCase(name)) {
                return p.getUser();
            } else if ("Module".equalsIgnoreCase(name)) {
                return p.getModule();
            }
            return super.getProperty(name, contextObj);
        }
    };
}
