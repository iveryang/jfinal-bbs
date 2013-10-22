package cn.iver.kit;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: iver
 * Date: 13-10-13
 */
public class ToolKit {
    public static List<String> getDifference(String[] a, String[] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0)
            return null;
        List<String> diff = new ArrayList<String>();
        Set<String> setA = toSet(a);
        Set<String> setB = toSet(b);
        for (String elementOfA : setA) {
            if (!setB.contains(elementOfA))
                diff.add(elementOfA);
        }
        return diff;
    }

    public static Set<String> toSet(String[] strArray) {
        Set<String> strSet = new HashSet<String>();
        for (String str : strArray) {
            strSet.add(str);
        }
        return strSet;
    }

    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }

}
