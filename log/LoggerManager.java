

package ws.billy.CookieGadgets.log;

import java.util.logging.Level;
import org.bukkit.Bukkit;

public class LoggerManager
{
    public static void printLog(final Object... array) {
        logFormat(LogLevel.INFO, null, array);
    }
    
    public static void printLog(final LogLevel logLevel, final Object... array) {
        logFormat(logLevel, null, array);
    }
    
    public static void printLogWithHeader(final LogLevel logLevel, final String s, final Object... array) {
        logFormat(logLevel, s, array);
    }
    
    public static void consoleMessage(final Object... array) {
        if (array.length == 0) {
            Bukkit.getServer().getLogger().log(Level.INFO, new StringBuilder(String.valueOf(prefix())).toString(), new Throwable().getStackTrace());
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            Bukkit.getServer().getConsoleSender().sendMessage(String.valueOf(prefix()) + array[i].toString());
        }
    }
    
    public static void info(final Object... array) {
        if (array.length == 0) {
            Bukkit.getServer().getLogger().log(Level.INFO, new StringBuilder(String.valueOf(prefix())).toString(), new Throwable().getStackTrace());
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            Bukkit.getServer().getLogger().log(Level.INFO, String.valueOf(prefix()) + array[i].toString(), new Throwable().getStackTrace());
        }
    }
    
    public static void severe(final Object... array) {
        if (array.length == 0) {
            Bukkit.getServer().getLogger().log(Level.SEVERE, new StringBuilder(String.valueOf(prefix())).toString(), new Throwable().getStackTrace());
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            Bukkit.getServer().getLogger().log(Level.SEVERE, String.valueOf(prefix()) + array[i].toString(), new Throwable().getStackTrace());
        }
    }
    
    public static void warn(final Object... array) {
        if (array.length == 0) {
            Bukkit.getServer().getLogger().log(Level.WARNING, new StringBuilder(String.valueOf(prefix())).toString(), new Throwable().getStackTrace());
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            Bukkit.getServer().getLogger().log(Level.WARNING, String.valueOf(prefix()) + array[i].toString(), new Throwable().getStackTrace());
        }
    }
    
    private static void logFormat(final LogLevel logLevel, final String s, final Object... array) {
        if (s != null) {
            Bukkit.getServer().getLogger().log(Level.parse(logLevel.toString()), String.valueOf(prefix()) + s);
        }
        if (array.length == 0) {
            Bukkit.getServer().getLogger().log(Level.parse(logLevel.toString()), prefix(), new Throwable().getStackTrace());
        }
        for (final Object o : array) {
            String str = o.toString();
            final int length2 = o.toString().length();
            if (s != null && s.length() > length2) {
                final int n = (s.length() - length2) / 2;
                String string = "";
                for (int j = 1; j <= n; ++j) {
                    string = String.valueOf(string) + " ";
                }
                str = String.valueOf(string) + o.toString();
            }
            Bukkit.getServer().getLogger().log(Level.parse(logLevel.toString()), String.valueOf(prefix()) + str, new Throwable().getStackTrace());
        }
        if (s != null) {
            Bukkit.getServer().getLogger().log(Level.parse(logLevel.toString()), String.valueOf(prefix()) + s);
        }
    }
    
    private static String prefix() {
        return "[CookieGadgets] ";
    }
    
    public enum LogLevel
    {
        INFO("INFO", 0), 
        WARNING("WARNING", 1), 
        SEVERE("SEVERE", 2);
        
        private LogLevel(final String name, final int ordinal) {
        }
    }
}
