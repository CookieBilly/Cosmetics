

package ws.billy.CookieGadgets.utils.cosmetics.morphs;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;

public enum GDisguiseType
{
    PIG("PIG", 0, "Pig"), 
    COW("COW", 1, "Cow"), 
    ENDERMAN("ENDERMAN", 2, "Enderman"), 
    CHICKEN("CHICKEN", 3, "Chicken"), 
    SPIDER("SPIDER", 4, "Spider"), 
    SHEEP("SHEEP", 5, "Sheep"), 
    SKELETON("SKELETON", 6, "Skeleton"), 
    CREEPER("CREEPER", 7, "Creeper"), 
    BLAZE("BLAZE", 8, "Blaze"), 
    ZOMBIE("ZOMBIE", 9, "Zombie"), 
    IRON_GOLEM("IRON_GOLEM", 10, "Iron_Golem"), 
    WITCH("WITCH", 11, "Witch"), 
    SNOWMAN("SNOWMAN", 12, "Snowman"), 
    GUARDIAN("GUARDIAN", 13, "Guardian"), 
    GRINCH("GRINCH", 14, "Zombie"), 
    RABBIT("RABBIT", 15, "Rabbit"), 
    WITHER_SLEKETON("WITHER_SLEKETON", 16, "Wither_Skeleton"), 
    CAVE_SPIDER("CAVE_SPIDER", 17, "Cave_Spider"), 
    SQUID("SQUID", 18, "Squid"), 
    BAT("BAT", 19, "Bat");
    
    private String mobName;
    
    private GDisguiseType(final String name, final int ordinal, final String mobName) {
        this.mobName = mobName;
    }
    
    public String getMobName() {
        return this.mobName;
    }
    
    @Override
    public String toString() {
        return this.mobName;
    }
    
    public DisguiseType getLibDisguiseType() {
        return DisguiseType.valueOf(this.toString().toUpperCase());
    }
    
    public de.robingrether.idisguise.disguise.DisguiseType getIDisguiseType() {
        return de.robingrether.idisguise.disguise.DisguiseType.valueOf(this.toString().toUpperCase());
    }
    
    public static GDisguiseType valueOfLibDisguiseType(final DisguiseType disguiseType) {
        GDisguiseType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GDisguiseType gDisguiseType = values[i];
            if (gDisguiseType.getLibDisguiseType().equals((Object)disguiseType)) {
                return gDisguiseType;
            }
        }
        return null;
    }
    
    public static GDisguiseType valueOfIDisguiseType(final de.robingrether.idisguise.disguise.DisguiseType disguiseType) {
        GDisguiseType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GDisguiseType gDisguiseType = values[i];
            if (gDisguiseType.getIDisguiseType().equals((Object)disguiseType)) {
                return gDisguiseType;
            }
        }
        return null;
    }
}
