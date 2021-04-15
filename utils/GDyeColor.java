

package ws.billy.CookieGadgets.utils;

import org.bukkit.DyeColor;

public enum GDyeColor
{
    WHITE("WHITE", 0, 0, 15), 
    ORANGE("ORANGE", 1, 1, 14), 
    MAGENTA("MAGENTA", 2, 2, 13), 
    LIGHT_BLUE("LIGHT_BLUE", 3, 3, 12), 
    YELLOW("YELLOW", 4, 4, 11), 
    LIME("LIME", 5, 5, 10), 
    PINK("PINK", 6, 6, 9), 
    GRAY("GRAY", 7, 7, 8), 
    LIGHT_GRAY("LIGHT_GRAY", 8, 8, 7), 
    SILVER("SILVER", 9, 8, 7), 
    CYAN("CYAN", 10, 9, 6), 
    PURPLE("PURPLE", 11, 10, 5), 
    BLUE("BLUE", 12, 11, 4), 
    BROWN("BROWN", 13, 12, 3), 
    GREEN("GREEN", 14, 13, 2), 
    RED("RED", 15, 14, 1), 
    BLACK("BLACK", 16, 15, 0);
    
    private byte woolData;
    private byte dyeData;
    
    private GDyeColor(final String name, final int ordinal, final int n, final int n2) {
        this.woolData = (byte)n;
        this.dyeData = (byte)n2;
    }
    
    public byte getWoolData() {
        return this.woolData;
    }
    
    public byte getDyeData() {
        return this.dyeData;
    }
    
    public DyeColor getColor() {
        if (!VersionManager.is1_13OrAbove() && this == GDyeColor.LIGHT_GRAY) {
            return DyeColor.valueOf("SILVER");
        }
        if (VersionManager.is1_13OrAbove() && this == GDyeColor.SILVER) {
            return DyeColor.valueOf("LIGHT_GRAY");
        }
        return DyeColor.valueOf(this.toString());
    }
    
    public static GDyeColor getNextColor(final GDyeColor gDyeColor) {
        if (gDyeColor == GDyeColor.BLACK) {
            return GDyeColor.WHITE;
        }
        return getByWoolData((byte)(gDyeColor.getWoolData() + 1));
    }
    
    public static GDyeColor getByWoolData(final byte b) {
        GDyeColor[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GDyeColor gDyeColor = values[i];
            if (gDyeColor.woolData == b) {
                return gDyeColor;
            }
        }
        return null;
    }
    
    public static GDyeColor getByDyeData(final byte b) {
        GDyeColor[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GDyeColor gDyeColor = values[i];
            if (gDyeColor.dyeData == b) {
                return gDyeColor;
            }
        }
        return null;
    }
}
