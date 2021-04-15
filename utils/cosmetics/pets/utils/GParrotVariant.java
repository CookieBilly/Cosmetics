

package ws.billy.CookieGadgets.utils.cosmetics.pets.utils;

import org.bukkit.entity.Parrot;

public enum GParrotVariant
{
    BLUE("BLUE", 0, "Blue"), 
    CYAN("CYAN", 1, "Cyan"), 
    GRAY("GRAY", 2, "Gray"), 
    GREEN("GREEN", 3, "Green"), 
    RED("RED", 4, "Red");
    
    private String name;
    
    private GParrotVariant(final String name, final int ordinal, final String name2) {
        this.name = name2;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public Parrot.Variant toParrotVariant() {
        return Parrot.Variant.valueOf(this.name.toUpperCase());
    }
}
