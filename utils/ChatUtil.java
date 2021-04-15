

package ws.billy.CookieGadgets.utils;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import org.bukkit.ChatColor;
import ws.billy.CookieGadgets.CookieGadgets;
import java.util.Random;
import java.util.regex.Pattern;

public class ChatUtil
{
    private static final String[] COLORS;
    private static final Pattern STRIP_COLOR_PATTERN;
    private static final Pattern HEX_COLOR_PATTERN;
    private static Random random;
    
    static {
        COLORS = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e" };
        STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('&') + "[0-9A-FK-ORX]");
        HEX_COLOR_PATTERN = Pattern.compile("[{](#[0-9A-Fa-f]{6}+)[}]");
        ChatUtil.random = new Random();
    }
    
    public static String format(String s) {
        if (s == null) {
            return null;
        }
        if (CookieGadgets.getCookieGadgetsData() != null && CookieGadgets.getCookieGadgetsData().getPrefix() != null) {
            s = s.replace("{PREFIX}", CookieGadgets.getCookieGadgetsData().getPrefix());
        }
        if (VersionManager.is1_16OrAbove() && ChatUtil.HEX_COLOR_PATTERN.matcher(s).find()) {
            final Matcher matcher = ChatUtil.HEX_COLOR_PATTERN.matcher(s);
            while (matcher.find()) {
                final String group = matcher.group();
                final StringBuilder sb = new StringBuilder("§x");
                char[] charArray;
                for (int length = (charArray = group.substring(2, matcher.group().length() - 1).toUpperCase().toCharArray()).length, i = 0; i < length; ++i) {
                    sb.append('§').append(charArray[i]);
                }
                s = s.replace(group, sb.toString());
            }
        }
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    public static List<String> format(final List<String> list) {
        if (list == null) {
            return null;
        }
        final ArrayList<String> list2 = new ArrayList<String>();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(format(iterator.next()));
        }
        return list2;
    }
    
    public static String correction(String str) {
        if (str == null) {
            return null;
        }
        if (VersionManager.is1_13OrAbove()) {
            if (!str.contains("&") && !str.contains("§")) {
                str = "&f" + str;
            }
            str = ChatColor.translateAlternateColorCodes('&', str);
        }
        return str;
    }
    
    public static String randomColor(final String str) {
        return ChatColor.translateAlternateColorCodes('&', "&" + ChatUtil.COLORS[ChatUtil.random.nextInt(ChatUtil.COLORS.length)] + str);
    }
    
    public static String getRandomColor() {
        return ChatColor.translateAlternateColorCodes('&', "&" + ChatUtil.COLORS[ChatUtil.random.nextInt(ChatUtil.COLORS.length)]);
    }
    
    public static String stripColor(final String s) {
        String input = stripColorFormat(s);
        if (VersionManager.is1_16OrAbove()) {
            input = ChatUtil.HEX_COLOR_PATTERN.matcher(input).replaceAll("");
        }
        if (CookieGadgets.getCookieGadgetsData() != null && CookieGadgets.getCookieGadgetsData().getPrefix() != null) {
            input = input.replace("{PREFIX}", CookieGadgets.getCookieGadgetsData().getPrefix());
        }
        return ChatColor.stripColor(input);
    }
    
    private static String stripColorFormat(final String input) {
        return ChatUtil.STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }
}
