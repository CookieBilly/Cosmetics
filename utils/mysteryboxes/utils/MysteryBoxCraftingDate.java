

package ws.billy.CookieGadgets.utils.mysteryboxes.utils;

import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MysteryBoxCraftingDate
{
    private String date;
    
    public MysteryBoxCraftingDate() {
        final Date date = new Date();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss zzz");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
        this.date = simpleDateFormat.format(date);
    }
    
    public String getDate() {
        return this.date;
    }
}
