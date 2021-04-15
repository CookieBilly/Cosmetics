

package ws.billy.CookieGadgets.utils;

import org.bukkit.Color;

public enum EnumColor
{
    AQUA("AQUA", 0, Color.AQUA), 
    BLACK("BLACK", 1, Color.BLACK), 
    BLUE("BLUE", 2, Color.BLUE), 
    FUCHSIA("FUCHSIA", 3, Color.FUCHSIA), 
    GRAY("GRAY", 4, Color.GRAY), 
    GREEN("GREEN", 5, Color.GREEN), 
    LIME("LIME", 6, Color.LIME), 
    MAROON("MAROON", 7, Color.MAROON), 
    NAVY("NAVY", 8, Color.NAVY), 
    OLIVE("OLIVE", 9, Color.OLIVE), 
    ORANGE("ORANGE", 10, Color.ORANGE), 
    PURPLE("PURPLE", 11, Color.PURPLE), 
    RED("RED", 12, Color.RED), 
    SILVER("SILVER", 13, Color.SILVER), 
    TEAL("TEAL", 14, Color.TEAL), 
    WHITE("WHITE", 15, Color.WHITE), 
    YELLOW("YELLOW", 16, Color.YELLOW);
    
    private Color color;
    
    private EnumColor(final String name, final int ordinal, final Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return this.color;
    }
}
