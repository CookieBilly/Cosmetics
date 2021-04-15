

package ws.billy.CookieGadgets.utils;

import java.util.List;

public class BooleanUtil
{
    public static boolean isTrue(final String s) {
        return match(s, "on", "true", "enable", "enabled");
    }
    
    public static boolean isFalse(final String s) {
        return match(s, "off", "false", "disable", "disabled");
    }
    
    public static boolean getBooleanValue(final String s) {
        return match(s, "on", "true", "enable", "enabled") || (match(s, "off", "false", "disable", "disabled") && false);
    }
    
    public static boolean match(final String anotherString, final String... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (array[i].equalsIgnoreCase(anotherString)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean match(final String s, final List<String> list) {
        final String[] array = new String[list.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = list.get(i);
        }
        return match(s, array);
    }
}
