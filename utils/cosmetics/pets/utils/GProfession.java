// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.utils.cosmetics.pets.utils;

import ws.billy.CookieGadgets.utils.ServerVersion;
import org.bukkit.entity.Villager;
import ws.billy.CookieGadgets.utils.VersionManager;

public enum GProfession
{
    NORMAL("NORMAL", 0, "Normal", "None", 0, 8), 
    FARMER("FARMER", 1, "Farmer", "Farmer", 0, 8), 
    LIBRARIAN("LIBRARIAN", 2, "Librarian", "Librarian", 1, 8), 
    PRIEST("PRIEST", 3, "Priest", "Shepherd", 2, 8), 
    BLACKSMITH("BLACKSMITH", 4, "Blacksmith", "Armorer", 3, 8), 
    BUTCHER("BUTCHER", 5, "Butcher", "Butcher", 4, 8), 
    NITWIT("NITWIT", 6, "Nitwit", "Nitwit", 5, 11), 
    HUSK("HUSK", 7, "Husk", "", 6, 8);
    
    private String name;
    private String v1_14Name;
    private int id;
    private int requiredVersion;
    
    private GProfession(final String name, final int ordinal, final String name2, final String v1_14Name, final int id, final int requiredVersion) {
        this.name = name2;
        this.v1_14Name = v1_14Name;
        this.id = id;
        this.requiredVersion = requiredVersion;
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
    
    public int getRequiredVersion() {
        return this.requiredVersion;
    }
    
    @Override
    public String toString() {
        return VersionManager.is1_14OrAbove() ? this.v1_14Name : this.name;
    }
    
    public Villager.Profession toProfession() {
        return Villager.Profession.valueOf(VersionManager.is1_14OrAbove() ? this.v1_14Name.toUpperCase() : this.name.toUpperCase());
    }
    
    public boolean isVersionSupported() {
        return ServerVersion.getServerVersion().getCurrentVersionNumber() >= this.getRequiredVersion();
    }
    
    public static GProfession getById(final int n) {
        GProfession[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GProfession gProfession = values[i];
            if (gProfession.getId() == n) {
                return gProfession;
            }
        }
        return null;
    }
    
    public static GProfession getByName(final String s) {
        GProfession[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GProfession gProfession = values[i];
            if (gProfession.getName().equalsIgnoreCase(s)) {
                return gProfession;
            }
            if (gProfession.getV1_14Name().equalsIgnoreCase(s)) {
                return gProfession;
            }
        }
        return null;
    }
}
