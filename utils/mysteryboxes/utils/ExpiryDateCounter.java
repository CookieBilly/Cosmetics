

package ws.billy.CookieGadgets.utils.mysteryboxes.utils;

import ws.billy.CookieGadgets.utils.MessageType;

public class ExpiryDateCounter
{
    private long distance;
    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    private String secondMsg;
    private String secondsMsg;
    private String minuteMsg;
    private String minutesMsg;
    private String hourMsg;
    private String hoursMsg;
    private String dayMsg;
    private String daysMsg;
    
    public ExpiryDateCounter(final long distance) {
        this.secondMsg = MessageType.SECOND.getFormatMessage().toLowerCase();
        this.secondsMsg = MessageType.SECONDS.getFormatMessage().toLowerCase();
        this.minuteMsg = MessageType.MINUTE.getFormatMessage().toLowerCase();
        this.minutesMsg = MessageType.MINUTES.getFormatMessage().toLowerCase();
        this.hourMsg = MessageType.HOUR.getFormatMessage().toLowerCase();
        this.hoursMsg = MessageType.HOURS.getFormatMessage().toLowerCase();
        this.dayMsg = MessageType.DAY.getFormatMessage().toLowerCase();
        this.daysMsg = MessageType.DAYS.getFormatMessage().toLowerCase();
        this.distance = distance;
        this.days = 0;
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }
    
    public String getFormat() {
        if (this.distance <= 0L) {
            return "0 " + this.secondMsg;
        }
        if (this.distance >= 86400L) {
            this.days += (int)(this.distance / 86400L);
            this.distance -= this.days * 86400;
        }
        if (this.distance >= 3600L) {
            this.hours += (int)(this.distance / 3600L);
            this.distance -= this.hours * 3600;
        }
        if (this.distance >= 60L) {
            this.minutes += (int)(this.distance / 60L);
            this.distance -= this.minutes * 60;
        }
        if (this.distance < 60L) {
            this.seconds += (int)this.distance;
            this.distance -= this.seconds;
            if (this.distance > 0L) {
                this.getFormat();
            }
        }
        new StringBuilder("0 ").append(this.secondMsg).toString();
        String s;
        if (this.days > 0) {
            if (this.days == 1) {
                s = String.valueOf(this.days) + " " + this.dayMsg;
            }
            else {
                s = String.valueOf(this.days) + " " + this.daysMsg;
            }
            if (this.hours > 0) {
                if (this.hours == 1) {
                    s = String.valueOf(s) + ", " + this.hours + " " + this.hourMsg;
                }
                else {
                    s = String.valueOf(s) + ", " + this.hours + " " + this.hoursMsg;
                }
            }
        }
        else if (this.days == 0 && this.hours > 0) {
            if (this.hours == 1) {
                s = String.valueOf(this.hours) + " " + this.hourMsg;
            }
            else {
                s = String.valueOf(this.hours) + " " + this.hoursMsg;
            }
            if (this.minutes > 0) {
                if (this.minutes == 1) {
                    s = String.valueOf(s) + ", " + this.minutes + " " + this.minuteMsg;
                }
                else {
                    s = String.valueOf(s) + ", " + this.minutes + " " + this.minutesMsg;
                }
            }
        }
        else if (this.days == 0 && this.hours == 0 && this.minutes > 0) {
            if (this.minutes == 1) {
                s = String.valueOf(this.minutes) + " " + this.minuteMsg;
            }
            else {
                s = String.valueOf(this.minutes) + " " + this.minutesMsg;
            }
            if (this.seconds > 0) {
                if (this.seconds == 1) {
                    s = String.valueOf(s) + ", " + this.seconds + " " + this.secondMsg;
                }
                else {
                    s = String.valueOf(s) + ", " + this.seconds + " " + this.secondsMsg;
                }
            }
        }
        else if (this.days == 0 && this.hours == 0 && this.minutes == 0 && this.seconds > 0) {
            if (this.seconds == 1) {
                s = String.valueOf(this.seconds) + " " + this.secondMsg;
            }
            else {
                s = String.valueOf(this.seconds) + " " + this.secondsMsg;
            }
        }
        else {
            s = "0 " + this.secondMsg;
        }
        return s;
    }
    
    public int getSeconds() {
        if (this.distance < 0L) {
            return 0;
        }
        if (this.distance >= 60L) {
            this.seconds += (int)this.distance;
            this.distance -= this.seconds;
            if (this.seconds > 0) {
                this.getSeconds();
            }
        }
        return this.seconds;
    }
    
    public int getMinutes() {
        if (this.distance < 0L) {
            return 0;
        }
        if (this.distance >= 60L) {
            this.minutes += (int)(this.distance / 60L);
            this.distance -= this.minutes * 60;
            if (this.distance >= 60L) {
                this.getMinutes();
            }
        }
        return this.minutes;
    }
    
    public int getHours() {
        if (this.distance < 0L) {
            return 0;
        }
        if (this.distance >= 3600L) {
            this.hours += (int)(this.distance / 3600L);
            this.distance -= this.hours * 3600;
            if (this.distance >= 3600L) {
                this.getHours();
            }
        }
        return this.hours;
    }
    
    public int getDays() {
        if (this.distance < 0L) {
            return 0;
        }
        if (this.distance >= 86400L) {
            this.days += (int)(this.distance / 86400L);
            this.distance -= this.days * 86400;
            if (this.distance >= 86400L) {
                this.getDays();
            }
        }
        return this.days;
    }
}
