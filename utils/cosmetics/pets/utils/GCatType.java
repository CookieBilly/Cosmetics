

package ws.billy.CookieGadgets.utils.cosmetics.pets.utils;

import org.bukkit.entity.Cat;

public enum GCatType
{
    TABBY("TABBY", 0, "TABBY", 0), 
    BLACK("BLACK", 1, "BLACK", 1), 
    RED("RED", 2, "RED", 2), 
    SIAMESE("SIAMESE", 3, "SIAMESE", 3), 
    BRITISH_SHORTHAIR("BRITISH_SHORTHAIR", 4, "BRITISH_SHORTHAIR", 4), 
    CALICO("CALICO", 5, "CALICO", 5), 
    PERSIAN("PERSIAN", 6, "PERSIAN", 6), 
    RAGDOLL("RAGDOLL", 7, "RAGDOLL", 7), 
    WHITE("WHITE", 8, "WHITE", 8), 
    JELLIE("JELLIE", 9, "JELLIE", 9), 
    ALL_BLACK("ALL_BLACK", 10, "ALL_BLACK", 10);
    
    private String name;
    private int id;
    
    private GCatType(final String name, final int ordinal, final String name2, final int id) {
        this.name = name2;
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public Cat.Type getType() {
        return Cat.Type.valueOf(this.name);
    }
    
    public static GCatType getById(final int n) {
        GCatType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GCatType gCatType = values[i];
            if (gCatType.getId() == n) {
                return gCatType;
            }
        }
        return null;
    }
    
    public static GCatType getByName(final String anotherString) {
        GCatType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GCatType gCatType = values[i];
            if (gCatType.name().equalsIgnoreCase(anotherString)) {
                return gCatType;
            }
        }
        return null;
    }
}
