

package ws.billy.CookieGadgets.utils;

import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Calendar;
import java.sql.Timestamp;

public class GTimestamp
{
    public static Timestamp getTime(final String input) {
        if (input.equalsIgnoreCase("forever")) {
            return getForever();
        }
        final Calendar instance = Calendar.getInstance();
        final Matcher matcher = Pattern.compile("\\d+[ymdhMs]").matcher(input);
        int n = 0;
        while (matcher.find()) {
            final String group = matcher.group();
            final String substring = group.substring(group.length() - 1, group.length());
            switch (substring) {
                case "y": {
                    instance.add(1, Integer.parseInt(group.substring(0, group.length() - 1)));
                    ++n;
                    continue;
                }
                case "m": {
                    instance.add(2, Integer.parseInt(group.substring(0, group.length() - 1)));
                    ++n;
                    continue;
                }
                case "d": {
                    instance.add(5, Integer.parseInt(group.substring(0, group.length() - 1)));
                    ++n;
                    continue;
                }
                case "h": {
                    instance.add(10, Integer.parseInt(group.substring(0, group.length() - 1)));
                    ++n;
                    continue;
                }
                case "M": {
                    instance.add(12, Integer.parseInt(group.substring(0, group.length() - 1)));
                    ++n;
                    continue;
                }
                case "s": {
                    instance.add(13, Integer.parseInt(group.substring(0, group.length() - 1)));
                    ++n;
                    continue;
                }
                default: {
                    throw new NumberFormatException();
                }
            }
        }
        if (n == 0) {
            throw new NumberFormatException();
        }
        return new Timestamp(instance.getTimeInMillis());
    }
    
    public static Timestamp getForever() {
        return new Timestamp(8099, 0, 1, 0, 0, 0, 0);
    }
    
    public static String toDate(final Timestamp timestamp) {
        final Date date = new Date(timestamp.getTime());
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss zzz");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
        return simpleDateFormat.format(date);
    }
    
    public static String toDate(final long date) {
        final Date date2 = new Date(date);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss zzz");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
        return simpleDateFormat.format(date2);
    }
}
