

package ws.billy.CookieGadgets.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;

public class VersionManager
{
    public VersionManager() {
        if (getBukkitVersion() != null) {
            ServerVersion.setServerVersion(ServerVersion.valueOf(getBukkitVersion()));
        }
        else {
            ServerVersion.setServerVersion(ServerVersion.valueOfSpigotRelease(getMinecraftVersion()));
        }
    }
    
    public static boolean isPaperServer() {
        return Bukkit.getName().equals("Paper");
    }
    
    public static boolean is1_8OrAbove() {
        return is1_8Version() || is1_9OrAbove();
    }
    
    public static boolean is1_9OrAbove() {
        return is1_9Version() || is1_10OrAbove();
    }
    
    public static boolean is1_10OrAbove() {
        return is1_10Version() || is1_11OrAbove();
    }
    
    public static boolean is1_11OrAbove() {
        return is1_11Version() || is1_12OrAbove();
    }
    
    public static boolean is1_12OrAbove() {
        return is1_12Version() || is1_13OrAbove();
    }
    
    public static boolean is1_13OrAbove() {
        return is1_13Version() || is1_14OrAbove();
    }
    
    public static boolean is1_14OrAbove() {
        return is1_14Version() || is1_15OrAbove();
    }
    
    public static boolean is1_15OrAbove() {
        return is1_15Version() || is1_16OrAbove();
    }
    
    public static boolean is1_16OrAbove() {
        return is1_16Version();
    }
    
    public static boolean is1_8Version() {
        return is1_8_R1Version() || is1_8_R2Version() || is1_8_R3Version();
    }
    
    public static boolean is1_9Version() {
        return is1_9_R1Version() || is1_9_R2Version();
    }
    
    public static boolean is1_10Version() {
        return is1_10_R1Version();
    }
    
    public static boolean is1_11Version() {
        return is1_11_R1Version();
    }
    
    public static boolean is1_12Version() {
        return is1_12_R1Version();
    }
    
    public static boolean is1_13Version() {
        return is1_13_R1Version() || is1_13_R2Version();
    }
    
    public static boolean is1_14Version() {
        return is1_14_R1Version();
    }
    
    public static boolean is1_15Version() {
        return is1_15_R1Version();
    }
    
    public static boolean is1_16Version() {
        return is1_16_R1Version() || is1_16_R2Version() || is1_16_R3Version();
    }
    
    public static boolean is1_8_R1Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_8_R1;
    }
    
    public static boolean is1_8_R2Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_8_R2;
    }
    
    public static boolean is1_8_R3Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_8_R3;
    }
    
    public static boolean is1_9_R1Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_9_R1;
    }
    
    public static boolean is1_9_R2Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_9_R2;
    }
    
    public static boolean is1_10_R1Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_10_R1;
    }
    
    public static boolean is1_11_R1Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_11_R1;
    }
    
    public static boolean is1_12_R1Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_12_R1;
    }
    
    public static boolean is1_13_R1Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_13_R1;
    }
    
    public static boolean is1_13_R2Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_13_R2;
    }
    
    public static boolean is1_14_R1Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_14_R1;
    }
    
    public static boolean is1_15_R1Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_15_R1;
    }
    
    public static boolean is1_16_R1Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_16_R1;
    }
    
    public static boolean is1_16_R2Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_16_R2;
    }
    
    public static boolean is1_16_R3Version() {
        return ServerVersion.getServerVersion() == ServerVersion.v1_16_R3;
    }
    
    public static String getBukkitVersion() {
        final Matcher matcher = Pattern.compile("v\\d+_\\d+_R\\d+").matcher(Bukkit.getServer().getClass().getPackage().getName());
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
    
    public static String getMinecraftVersion() {
        final Matcher matcher = Pattern.compile("(\\(MC: )([\\d\\.]+)(\\))").matcher(Bukkit.getVersion());
        if (matcher.find()) {
            return matcher.group(2);
        }
        return null;
    }
    
    private static int compare(final String s, final String s2) {
        final String[] split = s.split("\\.");
        final String[] split2 = s2.split("\\.");
        final int max = Math.max(split.length, split2.length);
        final int[] array = new int[max];
        final int[] array2 = new int[max];
        for (int i = 0; i < split.length; ++i) {
            array[i] = Integer.parseInt(split[i]);
        }
        for (int j = 0; j < split2.length; ++j) {
            array2[j] = Integer.parseInt(split2[j]);
        }
        for (int k = 0; k < max; ++k) {
            final int n = array[k] - array2[k];
            if (n > 0) {
                return 1;
            }
            if (n < 0) {
                return -1;
            }
        }
        return 0;
    }
    
    private static int compare(final String s, final String s2, final int n) {
        final String[] split = s.split("\\.");
        final String[] split2 = s2.split("\\.");
        final int max = Math.max(split.length, split2.length);
        final int[] array = new int[max];
        final int[] array2 = new int[max];
        for (int i = 0; i < n; ++i) {
            array[i] = Integer.parseInt(split[i]);
        }
        for (int j = 0; j < n; ++j) {
            array2[j] = Integer.parseInt(split2[j]);
        }
        for (int k = 0; k < max; ++k) {
            final int n2 = array[k] - array2[k];
            if (n2 > 0) {
                return 1;
            }
            if (n2 < 0) {
                return -1;
            }
        }
        return 0;
    }
    
    public static boolean isVersionGreater(final String s, final String s2) {
        return compare(s, s2) > 0;
    }
    
    public static boolean isVersionGreaterEqual(final String s, final String s2) {
        return compare(s, s2) >= 0;
    }
    
    public static boolean isVersionLessEqual(final String s, final String s2) {
        return compare(s, s2) <= 0;
    }
    
    public static boolean isVersionBetweenEqual(final String s, final String s2, final String s3) {
        return isVersionGreaterEqual(s, s2) && isVersionLessEqual(s, s3);
    }
    
    public static boolean isSupported(final String s, final String s2, final String s3) {
        return compare(s, s2, 2) >= 0 && compare(s, s3, 2) <= 0;
    }
    
    public static boolean isClassExists(final String className) {
        try {
            Class.forName(className);
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
}
