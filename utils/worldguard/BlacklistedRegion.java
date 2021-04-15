

package ws.billy.CookieGadgets.utils.worldguard;

import java.util.Iterator;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.MessageType;
import java.util.List;
import ws.billy.CookieGadgets.cosmetics.Category;
import java.util.ArrayList;

public class BlacklistedRegion
{
    private static final ArrayList<BlacklistedRegion> VALUES;
    public static final BlacklistedRegion ALL_COSMETICS;
    public static final BlacklistedRegion HATS;
    public static final BlacklistedRegion ANIMATED_HATS;
    public static final BlacklistedRegion PARTICLES;
    public static final BlacklistedRegion SUITS;
    public static final BlacklistedRegion GADGETS;
    public static final BlacklistedRegion PETS;
    public static final BlacklistedRegion MINIATURES;
    public static final BlacklistedRegion MORPHS;
    public static final BlacklistedRegion BANNERS;
    public static final BlacklistedRegion EMOTES;
    public static final BlacklistedRegion CLOAKS;
    public static final BlacklistedRegion PET_RIDING;
    private Category category;
    private List<String> blacklistedRegions;
    private MessageType disableMessage;
    
    static {
        VALUES = new ArrayList<BlacklistedRegion>();
        ALL_COSMETICS = new BlacklistedRegion(null, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-All-Cosmetics"), MessageType.NOT_ALLOW_COSMETICS_IN_REGION);
        HATS = new BlacklistedRegion(Category.HATS, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-Hats"), MessageType.NOT_ALLOW_HAT_IN_REGION);
        ANIMATED_HATS = new BlacklistedRegion(Category.ANIMATED_HATS, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-Animated-Hats"), MessageType.NOT_ALLOW_ANIMATED_HAT_IN_REGION);
        PARTICLES = new BlacklistedRegion(Category.PARTICLES, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-Particles"), MessageType.NOT_ALLOW_PARTICLE_IN_REGION);
        SUITS = new BlacklistedRegion(Category.SUITS, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-Suits"), MessageType.NOT_ALLOW_SUIT_IN_REGION);
        GADGETS = new BlacklistedRegion(Category.GADGETS, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-Gadgets"), MessageType.NOT_ALLOW_GADGET_IN_REGION);
        PETS = new BlacklistedRegion(Category.PETS, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-Pets"), MessageType.NOT_ALLOW_PET_IN_REGION);
        MINIATURES = new BlacklistedRegion(Category.MINIATURES, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-Miniatures"), MessageType.NOT_ALLOW_MINIATURES_IN_REGION);
        MORPHS = new BlacklistedRegion(Category.MORPHS, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-Morphs"), MessageType.NOT_ALLOW_MORPH_IN_REGION);
        BANNERS = new BlacklistedRegion(Category.BANNERS, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-Banners"), MessageType.NOT_ALLOW_BANNER_IN_REGION);
        EMOTES = new BlacklistedRegion(Category.EMOTES, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-Emotes"), MessageType.NOT_ALLOW_EMOTES_IN_REGION);
        CLOAKS = new BlacklistedRegion(Category.CLOAKS, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-Cloaks"), MessageType.NOT_ALLOW_CLOAK_IN_REGION);
        PET_RIDING = new BlacklistedRegion(null, FileManager.getConfigFile().getStringList("Blacklisted-Regions.Disable-Pet-Riding"), MessageType.NOT_ALLOW_PET_RIDING_IN_REGION);
    }
    
    private BlacklistedRegion(final Category category, final List<String> blacklistedRegions, final MessageType disableMessage) {
        this.category = category;
        this.blacklistedRegions = blacklistedRegions;
        this.disableMessage = disableMessage;
        BlacklistedRegion.VALUES.add(this);
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public List<String> getBlacklistedRegions() {
        return this.blacklistedRegions;
    }
    
    public MessageType getDisableMessage() {
        return this.disableMessage;
    }
    
    public static ArrayList<BlacklistedRegion> values() {
        return BlacklistedRegion.VALUES;
    }
    
    public static BlacklistedRegion valueOf(final Category category) {
        if (category == null) {
            return BlacklistedRegion.ALL_COSMETICS;
        }
        for (final BlacklistedRegion blacklistedRegion : values()) {
            if (blacklistedRegion.getCategory() == category) {
                return blacklistedRegion;
            }
        }
        return null;
    }
}
