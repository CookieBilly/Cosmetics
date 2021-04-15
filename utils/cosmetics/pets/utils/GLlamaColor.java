

package ws.billy.CookieGadgets.utils.cosmetics.pets.utils;

import org.bukkit.entity.Llama;

public enum GLlamaColor
{
    CREAMY("CREAMY", 0, "Creamy", 0), 
    WHITE("WHITE", 1, "White", 1), 
    BROWN("BROWN", 2, "Brown", 2), 
    GRAY("GRAY", 3, "Gray", 3);
    
    private String name;
    private int id;
    
    private GLlamaColor(final String name, final int ordinal, final String name2, final int id) {
        this.name = name2;
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getId() {
        return this.id;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public Llama.Color toLlamaColor() {
        return Llama.Color.valueOf(this.name.toUpperCase());
    }
    
    public static GLlamaColor getById(final int n) {
        GLlamaColor[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GLlamaColor gLlamaColor = values[i];
            if (gLlamaColor.getId() == n) {
                return gLlamaColor;
            }
        }
        return null;
    }
}
