

package ws.billy.CookieGadgets.cosmetics;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.List;

public class MainMenuType
{
    private static final List<MainMenuType> ENABLED;
    private static final List<MainMenuType> VALUES;
    public static final MainMenuType HATS;
    public static final MainMenuType ANIMATED_HATS;
    public static final MainMenuType PARTICLES;
    public static final MainMenuType SUITS;
    public static final MainMenuType GADGETS;
    public static final MainMenuType PETS;
    public static final MainMenuType MINIATURES;
    public static final MainMenuType MORPHS;
    public static final MainMenuType BANNERS;
    public static final MainMenuType EMOTES;
    public static final MainMenuType CLOAKS;
    private String name;
    private String displayName;
    private GMaterial material;
    private List<String> lore;
    private static String layout;
    private static int[] layoutList;
    private static int inventorySize;
    
    static {
        ENABLED = new ArrayList<MainMenuType>();
        VALUES = new ArrayList<MainMenuType>();
        HATS = new MainMenuType("Hats", "&eHats", new GMaterial(EnumMaterial.DIAMOND_HELMET), Arrays.asList("&7Collect all the hats and", "&7wear them while in a lobby."));
        ANIMATED_HATS = new MainMenuType("Animated Hats", "&eAnimated Hats", new GMaterial("head:5ebfd2396cbabdb42c348bcf41599c87a506a71ef60948c496f95c6cb63141"), Arrays.asList("&7These hats take it a step", "&7further and move while on", "&7your head."));
        PARTICLES = new MainMenuType("Particles", "&eParticles", new GMaterial(EnumMaterial.BLAZE_POWDER), Arrays.asList("&7Make particles around you", "&7while standing in lobbies", "&7using these cool particle", "&7effects!"));
        SUITS = new MainMenuType("Suits", "&eSuits", new GMaterial(EnumMaterial.GOLDEN_LEGGINGS), Arrays.asList("&7Collect and wear all the", "&7pieces from a specific suit", "&7to gain unique effects!"));
        GADGETS = new MainMenuType("Gadgets", "&eGadgets", new GMaterial(EnumMaterial.PISTON), Arrays.asList("&7These fun little toys", "&7can be used while", "&7in a lobby."));
        PETS = new MainMenuType("Pets", "&ePets", new GMaterial(EnumMaterial.BONE), Arrays.asList("&7Unlock these pets to have", "&7them follow you around in", "&7lobbies!"));
        MINIATURES = new MainMenuType("Miniatures", "&eMiniatures", new GMaterial("head:9da39269ef45f825ec61bb4f8aa09bd3cf07996fb6fac338a6e91d6699ae425"), Arrays.asList("&7Unlock these miniatures to", "&7have a little friend follow", "&7you around in lobbies!"));
        MORPHS = new MainMenuType("Morphs", "&eMorphs", new GMaterial(EnumMaterial.ZOMBIE_HEAD), Arrays.asList("&7The ultimate transformation!", "&7Using these in lobbies", "&7will make you transform", "&7into a monster and unlock", "&7unique powers for everyone", "&7else to see!"));
        BANNERS = new MainMenuType("Banners", "&eBanners", new GMaterial("CUSTOM_MATERIAL_1"), Arrays.asList("&7Unlock all the Banners and", "&7wear them as hats while in", "&7in a lobby!"));
        EMOTES = new MainMenuType("Emotes", "&eEmotes", new GMaterial("head:60c432cbc490a8af6e9dfeb28095c0a0ec79fff705fb184674d1e743bd05baa"), Arrays.asList("&7Express yourself! Show the", "&7world how you're feeling", "&7with these unique animated", "&7Emotes."));
        CLOAKS = new MainMenuType("Cloaks", "&eCloaks", new GMaterial(EnumMaterial.ENCHANTING_TABLE), Arrays.asList("&7Made of particles, these", "&7cloaks provide ultimate", "&7swag. To activate them,", "&7stand still for 1 second", "&7while in a lobby."));
    }
    
    private MainMenuType(final String name, final String displayName, final GMaterial material, final List<String> lore) {
        this.name = name;
        if (FileManager.getMainMenuFile().get("MainMenu." + this.name + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getMainMenuFile().set("MainMenu." + this.name + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getMainMenuFile().getString("MainMenu." + this.name + ".Name");
        }
        if (FileManager.getMainMenuFile().get("MainMenu." + this.name + ".Material") == null) {
            this.material = material;
            FileManager.getMainMenuFile().set("MainMenu." + this.name + ".Material", this.material.getCombinedMaterial());
        }
        else {
            this.material = new GMaterial(FileManager.getMainMenuFile().getString("MainMenu." + this.name + ".Material"));
        }
        if (FileManager.getMainMenuFile().get("MainMenu." + this.name + ".Lore") == null) {
            if ((this.lore = lore) == null) {
                FileManager.getMainMenuFile().set("MainMenu." + this.name + ".Lore", "");
            }
            else {
                FileManager.getMainMenuFile().set("MainMenu." + this.name + ".Lore", this.lore);
            }
        }
        else {
            this.lore = FileManager.getMainMenuFile().getStringList("MainMenu." + this.name + ".Lore");
        }
        if (!MainMenuType.VALUES.contains(this)) {
            MainMenuType.VALUES.add(this);
        }
        if (this.name.equals("Morphs") && !CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() && !CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled() && MainMenuType.VALUES.contains(this)) {
            MainMenuType.VALUES.remove(MainMenuType.MORPHS);
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
    
    public List<String> getLore() {
        return this.lore;
    }
    
    public boolean isEnabled() {
        return Category.valueOfByName(this.name).isEnabled();
    }
    
    public Category getCategory() {
        return Category.valueOfByName(this.name);
    }
    
    public static List<MainMenuType> enabled() {
        return MainMenuType.ENABLED;
    }
    
    public static List<MainMenuType> values() {
        return MainMenuType.VALUES;
    }
    
    public static void checkEnabled() {
        for (final MainMenuType mainMenuType : values()) {
            if (mainMenuType.isEnabled() && !MainMenuType.ENABLED.contains(mainMenuType)) {
                MainMenuType.ENABLED.add(mainMenuType);
            }
        }
        getLayout();
        getInventorySize();
    }
    
    public static int[] getLayout() {
        if (MainMenuType.layout == null) {
            if (FileManager.getMainMenuFile().get("Slot-Layout") == null) {
                MainMenuType.layout = getDefaultLayout();
                FileManager.getMainMenuFile().set("Slot-Layout", MainMenuType.layout);
            }
            else {
                MainMenuType.layout = FileManager.getMainMenuFile().getString("Slot-Layout");
            }
        }
        if (MainMenuType.layoutList != null) {
            return MainMenuType.layoutList;
        }
        int n = 0;
        int[] copy = { 0 };
        final String[] split = MainMenuType.layout.replace(" ", "").split("\\,");
        String[] array;
        for (int length = (array = split).length, i = 0; i < length; ++i) {
            final String s = array[i];
            try {
                copy[n++] = Integer.parseInt(s);
                if (copy.length < split.length) {
                    copy = Arrays.copyOf(copy, copy.length + 1);
                }
            }
            catch (NumberFormatException ex) {}
        }
        return MainMenuType.layoutList = copy;
    }
    
    public static int getTheHighestItemSlot() {
        if (MainMenuType.layout == null) {
            getLayout();
        }
        if (MainMenuType.inventorySize == 0) {
            getInventorySize();
        }
        int inventorySize = MainMenuType.inventorySize;
        int[] layoutList;
        for (int length = (layoutList = MainMenuType.layoutList).length, i = 0; i < length; ++i) {
            final int n = layoutList[i];
            if (n > inventorySize) {
                inventorySize = n;
            }
        }
        return inventorySize;
    }
    
    public static String getDefaultLayout() {
        switch (enabled().size()) {
            case 11: {
                return "11, 12, 13, 14, 15, 19, 20, 31, 22, 24, 25";
            }
            case 10: {
                return "9, 11, 13, 15, 17, 27, 29, 31, 33, 35";
            }
            case 9: {
                return "10, 12, 14, 16, 27, 29, 31, 33, 35";
            }
            case 8: {
                return "10, 12, 14, 16, 28, 30, 32, 34";
            }
            case 7: {
                return "11, 13, 15, 28, 30, 32, 34";
            }
            case 6: {
                return "11, 15, 28, 30, 32, 34";
            }
            case 5: {
                return "11, 15, 28, 31, 34";
            }
            case 4: {
                return "19, 21, 23, 25";
            }
            case 3: {
                return "20, 22, 24";
            }
            case 2: {
                return "20, 24";
            }
            case 1: {
                return "22";
            }
            default: {
                return null;
            }
        }
    }
    
    public static int getInventorySize() {
        if (MainMenuType.inventorySize == 0) {
            if (FileManager.getMainMenuFile().get("Inventory-Size") == null) {
                MainMenuType.inventorySize = 54;
                FileManager.getMainMenuFile().set("Inventory-Size", MainMenuType.inventorySize);
            }
            else {
                MainMenuType.inventorySize = FileManager.getMainMenuFile().getInt("Inventory-Size");
            }
        }
        if (MainMenuType.inventorySize != 9 && MainMenuType.inventorySize != 18 && MainMenuType.inventorySize != 27 && MainMenuType.inventorySize != 36 && MainMenuType.inventorySize != 45 && MainMenuType.inventorySize != 54) {
            return MainMenuType.inventorySize = 54;
        }
        return MainMenuType.inventorySize;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static MainMenuType valueOf(final String anotherString) {
        for (final MainMenuType mainMenuType : values()) {
            if (mainMenuType.getName().equalsIgnoreCase(anotherString)) {
                return mainMenuType;
            }
        }
        return null;
    }
}
