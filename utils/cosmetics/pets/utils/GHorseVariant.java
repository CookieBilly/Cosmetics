

package ws.billy.CookieGadgets.utils.cosmetics.pets.utils;

import org.bukkit.entity.Horse;

public enum GHorseVariant
{
    HORSE("HORSE", 0, "Horse", 0), 
    DONKEY("DONKEY", 1, "Donkey", 1), 
    MULE("MULE", 2, "Mule", 2), 
    UNDEAD_HORSE("UNDEAD_HORSE", 3, "Undead Horse", 3), 
    SKELETON_HORSE("SKELETON_HORSE", 4, "Skeleton Horse", 4), 
    LLAMA("LLAMA", 5, "Llama", 5);
    
    private String name;
    private int id;
    
    private GHorseVariant(final String name, final int ordinal, final String name2, final int id) {
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
    
    public Horse.Variant toHorseVariant() {
        return Horse.Variant.valueOf(this.name.replace(" ", "_").toUpperCase());
    }
    
    public static GHorseVariant getById(final int n) {
        GHorseVariant[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GHorseVariant gHorseVariant = values[i];
            if (gHorseVariant.getId() == n) {
                return gHorseVariant;
            }
        }
        return null;
    }
}
