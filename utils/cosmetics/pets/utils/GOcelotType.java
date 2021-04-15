

package ws.billy.CookieGadgets.utils.cosmetics.pets.utils;

import org.bukkit.entity.Cat;
import org.bukkit.entity.Ocelot;

public enum GOcelotType
{
    WILD_OCELOT("WILD_OCELOT", 0, "WILD_OCELOT", "WILD_OCELOT", 0), 
    BLACK_CAT("BLACK_CAT", 1, "BLACK_CAT", "BLACK", 1), 
    RED_CAT("RED_CAT", 2, "RED_CAT", "RED", 2), 
    SIAMESE_CAT("SIAMESE_CAT", 3, "SIAMESE_CAT", "SIAMESE", 3);
    
    private String name;
    private String v1_14Name;
    private int id;
    
    private GOcelotType(final String name, final int ordinal, final String name2, final String v1_14Name, final int id) {
        this.name = name2;
        this.v1_14Name = v1_14Name;
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getV1_14Name() {
        return this.v1_14Name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public Ocelot.Type getDepracatedType() {
        return Ocelot.Type.valueOf(this.name);
    }
    
    public Cat.Type getType() {
        return Cat.Type.valueOf(this.v1_14Name);
    }
    
    public static GOcelotType getById(final int n) {
        GOcelotType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GOcelotType gOcelotType = values[i];
            if (gOcelotType.getId() == n) {
                return gOcelotType;
            }
        }
        return null;
    }
    
    public static GOcelotType getByName(final String anotherString) {
        GOcelotType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GOcelotType gOcelotType = values[i];
            if (gOcelotType.name().equalsIgnoreCase(anotherString)) {
                return gOcelotType;
            }
        }
        return null;
    }
}
