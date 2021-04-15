

package ws.billy.CookieGadgets.custom;

import ws.billy.CookieGadgets.cosmetics.hats.animated.AnimatedHatType;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.CookieGadgets;
import java.util.Iterator;
import java.util.Arrays;
import ws.billy.CookieGadgets.configuration.FileManager;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.List;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;

public class CustomAnimatedHatType
{
    private String name;
    private String displayName;
    private String permission;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private GMaterial.GMaterialHead headTexture;
    private Long ticksPerFrame;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    private ArrayList<ItemStack> frames;
    
    public static void initCustomAnimatedHats() {
        final FileManager customAnimatedHatsFile = FileManager.getCustomAnimatedHatsFile();
        if (customAnimatedHatsFile.get("Custom-Animated-Hats") != null) {
            for (final String s : customAnimatedHatsFile.getConfigurationSection("Custom-Animated-Hats").getKeys(false)) {
                new CustomAnimatedHatType(s, "&a" + s.toLowerCase() + " Animated Hat", "CookieGadgets.animatedhats." + s.toLowerCase().replace(" ", ""), 12, Rarity.COMMON, null, new GMaterial.GMaterialHead("41ac21d93ce17f2b7ee2e0e07a983eeb4a539e341ce5c77c36c722f77a2235"), 2L, new ArrayList<ItemStack>());
            }
        }
        else {
            customAnimatedHatsFile.addDefault("Custom-Animated-Hats.Smiles.Name", "&aSmiles Animated Hat");
            customAnimatedHatsFile.addDefault("Custom-Animated-Hats.Smiles.Mystery Dust", 15);
            customAnimatedHatsFile.addDefault("Custom-Animated-Hats.Smiles.Rarity", Rarity.COMMON.getName());
            customAnimatedHatsFile.addDefault("Custom-Animated-Hats.Smiles.Texture", "41ac21d93ce17f2b7ee2e0e07a983eeb4a539e341ce5c77c36c722f77a2235");
            customAnimatedHatsFile.addDefault("Custom-Animated-Hats.Smiles.Enabled", false);
            customAnimatedHatsFile.addDefault("Custom-Animated-Hats.Smiles.CanBeFound", true);
            customAnimatedHatsFile.addDefault("Custom-Animated-Hats.Smiles.Purchasable", true);
            customAnimatedHatsFile.addDefault("Custom-Animated-Hats.Smiles.TicksPerFrame", 5);
            customAnimatedHatsFile.addDefault("Custom-Animated-Hats.Smiles.Frames", Arrays.asList("11:41ac21d93ce17f2b7ee2e0e07a983eeb4a539e341ce5c77c36c722f77a2235", "1:4168b716281635ceafc3268dfa7d5f46466c8032e11c1cfb7db711a9f647d", "11:41ac21d93ce17f2b7ee2e0e07a983eeb4a539e341ce5c77c36c722f77a2235", "1:4168b716281635ceafc3268dfa7d5f46466c8032e11c1cfb7db711a9f647d", "3:41ac21d93ce17f2b7ee2e0e07a983eeb4a539e341ce5c77c36c722f77a2235"));
            customAnimatedHatsFile.addDefault("Custom-Animated-Hats.Smiles.Lore.Locked", "");
            customAnimatedHatsFile.addDefault("Custom-Animated-Hats.Smiles.Lore.Unlocked", "");
        }
    }
    
    public CustomAnimatedHatType(final String name, final String displayName, final String permission, final int mysteryDust, final Rarity rarity, final List<String> list, final GMaterial.GMaterialHead headTexture, final Long ticksPerFrame, final ArrayList<ItemStack> frames) {
        this.name = name;
        if (FileManager.getCustomAnimatedHatsFile().get("Custom-Animated-Hats." + this.name + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getCustomAnimatedHatsFile().getString("Custom-Animated-Hats." + this.name + ".Name");
        }
        this.permission = permission;
        if (FileManager.getCustomAnimatedHatsFile().get("Custom-Animated-Hats." + this.name + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getCustomAnimatedHatsFile().getInt("Custom-Animated-Hats." + this.name + ".Mystery Dust");
        }
        if (FileManager.getCustomAnimatedHatsFile().get("Custom-Animated-Hats." + this.name + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getCustomAnimatedHatsFile().getString("Custom-Animated-Hats." + this.name + ".Rarity"));
        }
        if (FileManager.getCustomAnimatedHatsFile().get("Custom-Animated-Hats." + this.name + ".Texture") == null) {
            FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Texture", "");
            this.headTexture = headTexture;
        }
        else {
            this.headTexture = new GMaterial.GMaterialHead(FileManager.getCustomAnimatedHatsFile().getString("Custom-Animated-Hats." + this.name + ".Texture"));
        }
        if (FileManager.getCustomAnimatedHatsFile().get("Custom-Animated-Hats." + this.name + ".Enabled") == null) {
            FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Enabled", true);
            this.isEnable = true;
        }
        else {
            this.isEnable = FileManager.getCustomAnimatedHatsFile().getBoolean("Custom-Animated-Hats." + this.name + ".Enabled");
        }
        if (FileManager.getCustomAnimatedHatsFile().get("Custom-Animated-Hats." + this.name + ".CanBeFound") == null) {
            FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".CanBeFound", true);
            this.canBeFound = true;
        }
        else {
            this.canBeFound = FileManager.getCustomAnimatedHatsFile().getBoolean("Custom-Animated-Hats." + this.name + ".CanBeFound");
        }
        if (FileManager.getCustomAnimatedHatsFile().get("Custom-Animated-Hats." + this.name + ".Purchasable") == null) {
            FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Purchasable", true);
            this.purchasable = true;
        }
        else {
            this.purchasable = FileManager.getCustomAnimatedHatsFile().getBoolean("Custom-Animated-Hats." + this.name + ".Purchasable");
        }
        if (FileManager.getCustomAnimatedHatsFile().get("Custom-Animated-Hats." + this.name + ".TicksPerFrame") == null) {
            this.ticksPerFrame = ticksPerFrame;
            FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".TicksPerFrame", this.ticksPerFrame);
        }
        else {
            this.ticksPerFrame = (long)FileManager.getCustomAnimatedHatsFile().getInt("Custom-Animated-Hats." + this.name + ".TicksPerFrame");
        }
        if (FileManager.getCustomAnimatedHatsFile().get("Custom-Animated-Hats." + this.name + ".Frames") == null) {
            if ((this.frames = frames) == null) {
                FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Frames", "");
            }
            else {
                FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Frames", this.frames);
            }
        }
        else {
            final ArrayList<ItemStack> frames2 = new ArrayList<ItemStack>();
            for (final String s : FileManager.getCustomAnimatedHatsFile().getStringList("Custom-Animated-Hats." + this.name + ".Frames")) {
                if (!s.contains(":")) {
                    frames2.add(CookieGadgets.getNMSManager().setNBTTag(CookieGadgets.getNMSManager().setNBTTag(ItemUtils.itemSkull(this.displayName, null, s), "Cosmetics", "true"), "Category", Category.ANIMATED_HATS.getNBTTag()));
                }
                else {
                    for (int i = 1; i <= Integer.valueOf(s.split("\\:")[0]); ++i) {
                        frames2.add(CookieGadgets.getNMSManager().setNBTTag(CookieGadgets.getNMSManager().setNBTTag(ItemUtils.itemSkull(this.displayName, null, s.split("\\:")[1]), "Cosmetics", "true"), "Category", Category.ANIMATED_HATS.getNBTTag()));
                    }
                }
            }
            this.frames = frames2;
        }
        if (FileManager.getCustomAnimatedHatsFile().get("Custom-Animated-Hats." + this.name + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Lore.Locked", "");
                FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Lore.Locked", this.lockedLore);
                FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getCustomAnimatedHatsFile().get("Custom-Animated-Hats." + this.name + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Lore.Locked", "");
                }
                else {
                    FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getCustomAnimatedHatsFile().getStringList("Custom-Animated-Hats." + this.name + ".Lore.Locked");
            }
            if (FileManager.getCustomAnimatedHatsFile().get("Custom-Animated-Hats." + this.name + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getCustomAnimatedHatsFile().set("Custom-Animated-Hats." + this.name + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getCustomAnimatedHatsFile().getStringList("Custom-Animated-Hats." + this.name + ".Lore.Unlocked");
            }
        }
        new AnimatedHatType(this.name, this.displayName, this.permission, this.mysteryDust, this.rarity, this.lockedLore, this.unlockedLore, this.headTexture, this.ticksPerFrame, this.isEnable, this.canBeFound, this.purchasable, this.frames);
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
