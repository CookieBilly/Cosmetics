

package ws.billy.CookieGadgets.cosmetics.gadgets;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import java.util.Arrays;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.List;

public class GadgetCategoryType
{
    private static final List<GadgetCategoryType> ENABLED;
    private static final List<GadgetCategoryType> VALUES;
    public static final GadgetCategoryType Fun_AND_GAMES;
    public static final GadgetCategoryType MOBS_AND_NPCS;
    public static final GadgetCategoryType MOVEMENT;
    public static final GadgetCategoryType MUSICAL;
    public static final GadgetCategoryType PROJECTILE;
    public static final GadgetCategoryType VISUAL;
    private String name;
    private String displayName;
    private GMaterial material;
    private String GUIName;
    private List<String> permission;
    private List<String> lore;
    private boolean isEnable;
    
    static {
        ENABLED = new ArrayList<GadgetCategoryType>();
        VALUES = new ArrayList<GadgetCategoryType>();
        Fun_AND_GAMES = new GadgetCategoryType("Fun And Games", "&eFun & Games", new GMaterial("head:b89042082bb7a7618b784ee7605a134c58834e21e374c888937161057f6c7"), Arrays.asList("CookieGadgets.gadgets.magic9ball", "CookieGadgets.gadgets.fortunecookie", "CookieGadgets.gadgets.tetherball", "CookieGadgets.gadgets.divingboard", "CookieGadgets.gadgets.trampoline", "CookieGadgets.gadgets.flowergiver", "CookieGadgets.gadgets.sandcastle", "CookieGadgets.gadgets.bbqgrill", "CookieGadgets.gadgets.pocketbeach", "CookieGadgets.gadgets.icecreamstand", "CookieGadgets.gadgets.tictactoe"), Arrays.asList("&7Play games or just", "&7generally mess around in", "&7lobbies with these fun", "&7gadgets!"));
        MOBS_AND_NPCS = new GadgetCategoryType("Mobs And NPCs", "&eMobs & NPCs", new GMaterial(EnumMaterial.CREEPER_HEAD), Arrays.asList("CookieGadgets.gadgets.piggybank", "CookieGadgets.gadgets.catapult", "CookieGadgets.gadgets.whenpigsfly", "CookieGadgets.gadgets.creeperastronaut", "CookieGadgets.gadgets.explodingsheep", "CookieGadgets.gadgets.batlauncher", "CookieGadgets.gadgets.scarecrow"), Arrays.asList("&7Spawn and play with wacky", "&7creatures using these Mobs", "&7& NPC gadgets!"));
        MOVEMENT = new GadgetCategoryType("Movement", "&eMovement", new GMaterial(EnumMaterial.GOLDEN_BOOTS), Arrays.asList("CookieGadgets.gadgets.cowboy", "CookieGadgets.gadgets.teleportstick", "CookieGadgets.gadgets.firetrail", "CookieGadgets.gadgets.painttrail", "CookieGadgets.gadgets.parachute", "CookieGadgets.gadgets.teleporter", "CookieGadgets.gadgets.rocket", "CookieGadgets.gadgets.letitsnow"), Arrays.asList("&7Change the way you walk,", "&7run or fly around lobbies", "&7with these movement-altering", "&7gadgets!"));
        MUSICAL = new GadgetCategoryType("Musical", "&eMusical", new GMaterial(EnumMaterial.JUKEBOX), Arrays.asList("CookieGadgets.gadgets.jukebox", "CookieGadgets.gadgets.radio", "CookieGadgets.gadgets.discoball", "CookieGadgets.gadgets.djbooth"), Arrays.asList("&7Make some noise with these", "&7sound-emitting musical", "&7gadgets!"));
        PROJECTILE = new GadgetCategoryType("Projectile", "&eProjectile", new GMaterial(EnumMaterial.BOW), Arrays.asList("CookieGadgets.gadgets.mobgun", "CookieGadgets.gadgets.railgun", "CookieGadgets.gadgets.paintballgun", "CookieGadgets.gadgets.explosivebow", "CookieGadgets.gadgets.melonlauncher"), Arrays.asList("&7Fire at will! Shoot your", "&7way around the lobbies with", "&7these fun projectile", "&7gadgets!"));
        VISUAL = new GadgetCategoryType("Visual", "&eVisual", new GMaterial("head:fec5963e1f78f2f05943f4dd32224661374c220ecfde1e54754f5ee1e555dd"), Arrays.asList("CookieGadgets.gadgets.kookiefountain", "CookieGadgets.gadgets.pyromaniac", "CookieGadgets.gadgets.diamondshower", "CookieGadgets.gadgets.goldfountain", "CookieGadgets.gadgets.kawariminojutsu", "CookieGadgets.gadgets.cryotube", "CookieGadgets.gadgets.ghosts", "CookieGadgets.gadgets.partypopper", "CookieGadgets.gadgets.poopbomb", "CookieGadgets.gadgets.tntfountain", "CookieGadgets.gadgets.dracula"), Arrays.asList("&7Change the way you or the", "&7lobby looks with these", "&7visual gadgets!"));
    }
    
    private GadgetCategoryType(final String name, final String displayName, final GMaterial material, final List<String> permission, final List<String> lore) {
        this.name = name;
        if (FileManager.getGadgetsFile().get("Gadgets." + this.name + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getGadgetsFile().set("Gadgets." + this.name + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getGadgetsFile().getString("Gadgets." + this.name + ".Name");
        }
        if (FileManager.getGadgetsFile().get("Gadgets." + this.name + ".Material") == null) {
            this.material = material;
            FileManager.getGadgetsFile().set("Gadgets." + this.name + ".Material", material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getGadgetsFile().getString("Gadgets." + this.name + ".Material"));
        }
        if (FileManager.getGadgetsFile().get("Gadgets." + this.name + ".GUI-Name") == null) {
            this.GUIName = ChatUtil.stripColor(displayName);
            FileManager.getGadgetsFile().set("Gadgets." + this.name + ".GUI-Name", this.GUIName);
        }
        else {
            this.GUIName = FileManager.getGadgetsFile().getString("Gadgets." + this.name + ".GUI-Name");
        }
        this.permission = permission;
        if (FileManager.getGadgetsFile().get("Gadgets." + this.name + ".Enabled") == null) {
            this.isEnable = true;
            FileManager.getGadgetsFile().set("Gadgets." + this.name + ".Enabled", true);
        }
        else {
            this.isEnable = FileManager.getGadgetsFile().getBoolean("Gadgets." + this.name + ".Enabled");
        }
        if (FileManager.getGadgetsFile().get("Gadgets." + this.name + ".Lore") == null) {
            if ((this.lore = lore) == null) {
                FileManager.getGadgetsFile().set("Gadgets." + this.name + ".Lore", "");
            }
            else {
                FileManager.getGadgetsFile().set("Gadgets." + this.name + ".Lore", this.lore);
            }
        }
        else {
            this.lore = FileManager.getGadgetsFile().getStringList("Gadgets." + this.name + ".Lore");
        }
        if (!GadgetCategoryType.VALUES.contains(this)) {
            GadgetCategoryType.VALUES.add(this);
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDisplayName() {
        return ChatUtil.format(this.displayName);
    }
    
    public String getDisplayNameStripColor() {
        return ChatUtil.stripColor(this.displayName);
    }
    
    public GMaterial getMaterial() {
        return this.material;
    }
    
    public String getGUIName() {
        return ChatUtil.format(this.GUIName);
    }
    
    public List<String> getPermission() {
        return this.permission;
    }
    
    public List<String> getLore() {
        return this.lore;
    }
    
    public boolean isEnabled() {
        return this.isEnable;
    }
    
    public static List<GadgetCategoryType> enabled() {
        return GadgetCategoryType.ENABLED;
    }
    
    public static List<GadgetCategoryType> values() {
        return GadgetCategoryType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final GadgetCategoryType gadgetCategoryType : values()) {
            if (gadgetCategoryType.isEnabled() && !GadgetCategoryType.ENABLED.contains(gadgetCategoryType)) {
                GadgetCategoryType.ENABLED.add(gadgetCategoryType);
            }
        }
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static GadgetCategoryType valueOf(final String anotherString) {
        for (final GadgetCategoryType gadgetCategoryType : values()) {
            if (gadgetCategoryType.getName().equalsIgnoreCase(anotherString)) {
                return gadgetCategoryType;
            }
        }
        return null;
    }
}
