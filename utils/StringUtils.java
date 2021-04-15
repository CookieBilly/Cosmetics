

package ws.billy.CookieGadgets.utils;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class StringUtils
{
    public static String addPlaceholder(String format, final String target, final String replacement) {
        if (format != null) {
            format = ChatUtil.format(format.replace(target, replacement));
        }
        return format;
    }
    
    public static String addPlaceholders(String format, final List<String> list, final List<String> list2) {
        if (format != null) {
            if (list.size() != list.size()) {
                return format;
            }
            for (int i = 0; i < list.size(); ++i) {
                format = ChatUtil.format(format.replace(list.get(i), list2.get(i)));
            }
        }
        return format;
    }
    
    public static List<String> addPlaceholder(final List<String> list, final String s, final String s2) {
        return addPlaceholder(list, null, s, s2);
    }
    
    public static List<String> addPlaceholder(final List<String> list, final List<String> list2, final String s, final String s2) {
        final ArrayList<String> list3 = new ArrayList<String>();
        if (list != null) {
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                list3.add(ChatUtil.format(iterator.next().replace(s, s2)));
            }
        }
        if (list2 != null) {
            final Iterator<String> iterator2 = list2.iterator();
            while (iterator2.hasNext()) {
                list3.add(ChatUtil.format(iterator2.next().replace(s, s2)));
            }
        }
        return list3;
    }
    
    public static List<String> addPlaceholders(final List<String> list, final List<String> list2, final List<String> list3) {
        final ArrayList<String> list4 = new ArrayList<String>();
        if (list != null) {
            for (final String s : list) {
                if (list2.size() != list2.size()) {
                    return list;
                }
                String format = s;
                for (int i = 0; i < list2.size(); ++i) {
                    format = ChatUtil.format(format.replace(list2.get(i), list3.get(i)));
                }
                list4.add(format);
            }
        }
        return list4;
    }
}
