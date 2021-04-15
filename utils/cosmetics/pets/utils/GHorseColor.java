

package ws.billy.CookieGadgets.utils.cosmetics.pets.utils;

import org.bukkit.entity.Horse;

public enum GHorseColor
{
    WHITE("WHITE", 0, Horse.Color.WHITE, 0), 
    CREAMY("CREAMY", 1, Horse.Color.CREAMY, 1), 
    CHESTNUT("CHESTNUT", 2, Horse.Color.CHESTNUT, 2), 
    BROWN("BROWN", 3, Horse.Color.BROWN, 3), 
    BLACK("BLACK", 4, Horse.Color.BLACK, 4), 
    GRAY("GRAY", 5, Horse.Color.GRAY, 5), 
    DARK_BROWN("DARK_BROWN", 6, Horse.Color.DARK_BROWN, 6);
    
    private Horse.Color color;
    private int id;
    
    private GHorseColor(final String name, final int ordinal, final Horse.Color color, final int id) {
        this.color = color;
        this.id = id;
    }
    
    public Horse.Color getColor() {
        return this.color;
    }
    
    public int getId() {
        return this.id;
    }
    
    public static GHorseColor getById(final int n) {
        GHorseColor[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GHorseColor gHorseColor = values[i];
            if (gHorseColor.getId() == n) {
                return gHorseColor;
            }
        }
        return null;
    }
    
    public static GHorseColor getByColor(final Horse.Color color) {
        GHorseColor[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GHorseColor gHorseColor = values[i];
            if (gHorseColor.getColor().equals((Object)color)) {
                return gHorseColor;
            }
        }
        return null;
    }
}
