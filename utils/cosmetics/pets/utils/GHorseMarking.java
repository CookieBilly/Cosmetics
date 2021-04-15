

package ws.billy.CookieGadgets.utils.cosmetics.pets.utils;

import org.bukkit.entity.Horse;

public enum GHorseMarking
{
    NONE("NONE", 0, 0, Horse.Style.NONE, new Integer[] { 0, 1, 2, 3, 4, 5, 6 }), 
    WHITE("WHITE", 1, 1, Horse.Style.WHITE, new Integer[] { 256, 257, 258, 259, 260, 261, 262 }), 
    WHITEFIELD("WHITEFIELD", 2, 2, Horse.Style.WHITEFIELD, new Integer[] { 512, 513, 514, 515, 516, 517, 518 }), 
    WHITE_DOTS("WHITE_DOTS", 3, 3, Horse.Style.WHITE_DOTS, new Integer[] { 768, 769, 770, 771, 772, 773, 774 }), 
    BLACK_DOTS("BLACK_DOTS", 4, 4, Horse.Style.BLACK_DOTS, new Integer[] { 1024, 1025, 1026, 1027, 1028, 1029, 1030 });
    
    private int id;
    private Horse.Style style;
    private Integer[] type;
    private GHorseColor[] color;
    
    private GHorseMarking(final String name, final int ordinal, final int id, final Horse.Style style, final Integer[] type) {
        this.id = id;
        this.style = style;
        this.type = type;
        this.color = new GHorseColor[] { GHorseColor.WHITE, GHorseColor.CREAMY, GHorseColor.CHESTNUT, GHorseColor.BROWN, GHorseColor.BLACK, GHorseColor.GRAY, GHorseColor.DARK_BROWN };
    }
    
    public int getId(final GHorseColor gHorseColor) {
        for (int i = 0; i < GHorseColor.values().length; ++i) {
            if (this.color[i] == gHorseColor) {
                return this.type[i];
            }
        }
        return -1;
    }
    
    public Horse.Style getStyle() {
        return this.style;
    }
    
    public int getId() {
        return this.id;
    }
    
    public static GHorseMarking getByName(final String anotherString) {
        GHorseMarking[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GHorseMarking gHorseMarking = values[i];
            if (gHorseMarking.name().equalsIgnoreCase(anotherString)) {
                return gHorseMarking;
            }
        }
        return null;
    }
    
    public static GHorseMarking getByStyle(final Horse.Style style) {
        GHorseMarking[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GHorseMarking gHorseMarking = values[i];
            if (gHorseMarking.getStyle().equals((Object)style)) {
                return gHorseMarking;
            }
        }
        return null;
    }
    
    public static GHorseMarking getById(final int n) {
        GHorseMarking[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GHorseMarking gHorseMarking = values[i];
            if (gHorseMarking.getId() == n) {
                return gHorseMarking;
            }
        }
        return null;
    }
}
