

package ws.billy.CookieGadgets.utils.cosmetics.pets;

import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.entity.EntityType;

public enum GEntity
{
    BAT("BAT", 0, "Bat", "Bat", 65, 8), 
    BLAZE("BLAZE", 1, "Blaze", "Blaze", 61, 8), 
    CAVE_SPIDER("CAVE_SPIDER", 2, "Cave_Spider", "Cave_Spider", 59, 8), 
    CAT("CAT", 3, "Cat", 14), 
    CHICKEN("CHICKEN", 4, "Chicken", "Chicken", 93, 8), 
    COW("COW", 5, "Cow", "Cow", 92, 8), 
    CREEPER("CREEPER", 6, "Creeper", "Creeper", 50, 8), 
    DONKEY("DONKEY", 7, "Horse", "Donkey", 31, 8), 
    ENDERMAN("ENDERMAN", 8, "Enderman", "Enderman", 58, 8), 
    ENDERMITE("ENDERMITE", 9, "Endermite", "Endermite", 67, 8), 
    EVOKER("EVOKER", 10, "Evoker", 34, 11), 
    GUARDIAN("GUARDIAN", 11, "Guardian", "Guardian", 68, 8), 
    HORSE("HORSE", 12, "Horse", "Horse", 100, 8), 
    HUSK("HUSK", 13, "Zombie", "Husk", 23, 10), 
    ILLUSIONER("ILLUSIONER", 14, "Illusioner", 37, 12), 
    IRON_GOLEM("IRON_GOLEM", 15, "Iron Golem", "Iron Golem", 99, 8), 
    LLAMA("LLAMA", 16, "Llama", 103, 11), 
    MAGMA_CUBE("MAGMA_CUBE", 17, "Magma Cube", "Magma Cube", 62, 8), 
    MULE("MULE", 18, "Horse", "Mule", 32, 8), 
    MUSHROOM_COW("MUSHROOM_COW", 19, "Mushroom Cow", "Mushroom Cow", 96, 8), 
    OCELOT("OCELOT", 20, "Ocelot", "Ocelot", 98, 8), 
    PANDA("PANDA", 21, "Panda", 14), 
    PIG("PIG", 22, "Pig", "Pig", 90, 8), 
    PIG_ZOMBIE("PIG_ZOMBIE", 23, "Pig Zombie", "Pig Zombie", 57, 8), 
    POLAR_BEAR("POLAR_BEAR", 24, "Polar Bear", "Polar Bear", 102, 10), 
    RABBIT("RABBIT", 25, "Rabbit", "Rabbit", 101, 8), 
    SHEEP("SHEEP", 26, "Sheep", "Sheep", 91, 8), 
    SILVERFISH("SILVERFISH", 27, "Silverfish", "Silverfish", 60, 8), 
    SKELETON("SKELETON", 28, "Skeleton", "Skeleton", 51, 8), 
    SKELETON_HORSE("SKELETON_HORSE", 29, "Horse", "Skeleton Horse", 28, 8), 
    SLIME("SLIME", 30, "Slime", "Slime", 55, 8), 
    SNOWMAN("SNOWMAN", 31, "Snowman", "Snowman", 97, 8), 
    SPIDER("SPIDER", 32, "Spider", "Spider", 52, 8), 
    SQUID("SQUID", 33, "Squid", "Squid", 94, 8), 
    STRAY("STRAY", 34, "Skeleton", "Stray", 6, 10), 
    TURTLE("TURTLE", 35, "Turtle", 13), 
    VEX("VEX", 36, "Vex", 35, 11), 
    VILLAGER("VILLAGER", 37, "Villager", "Villager", 120, 8), 
    VINDICATOR("VINDICATOR", 38, "Vindicator", 36, 11), 
    WITCH("WITCH", 39, "Witch", "Witch", 66, 8), 
    WITHER("WITHER", 40, "Wither", "Wither", 64, 8), 
    WITHER_SKELETON("WITHER_SKELETON", 41, "Skeleton", "Wither Skeleton", 5, 8), 
    WOLF("WOLF", 42, "Wolf", "Wolf", 95, 8), 
    ZOMBIE("ZOMBIE", 43, "Zombie", "Zombie", 54, 8), 
    ZOMBIE_HORSE("ZOMBIE_HORSE", 44, "Horse", "Zombie Horse", 29, 8), 
    ZOMBIE_VILLAGER("ZOMBIE_VILLAGER", 45, "Zombie", "Zombie Villager", 27, 8);
    
    private String oldName;
    private String newName;
    private int id;
    private int requiredVersion;
    
    private GEntity(final String s, final int n, final String s2, final int n2) {
        this(s, n, s2, 0, n2);
    }
    
    private GEntity(final String name, final int ordinal, final String newName, final int id, final int requiredVersion) {
        this.id = 0;
        this.oldName = null;
        this.newName = newName;
        this.id = id;
        this.requiredVersion = requiredVersion;
    }
    
    private GEntity(final String name, final int ordinal, final String oldName, final String newName, final int id, final int requiredVersion) {
        this.id = 0;
        this.oldName = oldName;
        this.newName = newName;
        this.id = id;
        this.requiredVersion = requiredVersion;
    }
    
    public String getOldName() {
        return this.oldName;
    }
    
    public String getNewName() {
        return this.newName;
    }
    
    public EntityType getEntityType() {
        if (VersionManager.is1_11OrAbove()) {
            return EntityType.valueOf(this.newName.replace(" ", "_").toUpperCase());
        }
        return EntityType.valueOf(this.oldName.replace(" ", "_").toUpperCase());
    }
    
    @Override
    public String toString() {
        if (VersionManager.is1_11OrAbove()) {
            return this.newName;
        }
        return this.oldName;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getRequiredVersion() {
        return this.requiredVersion;
    }
    
    public boolean isVersionSupported() {
        return Integer.parseInt(VersionManager.getBukkitVersion().split("_")[1]) >= this.getRequiredVersion();
    }
}
