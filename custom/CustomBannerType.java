

package ws.billy.CookieGadgets.custom;

import ws.billy.CookieGadgets.cosmetics.banners.BannerType;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.cosmetics.banners.GPatternType;
import ws.billy.CookieGadgets.utils.cosmetics.banners.GPattern;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import ws.billy.CookieGadgets.configuration.FileManager;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.cosmetics.banners.GPatterns;
import org.bukkit.block.banner.Pattern;
import ws.billy.CookieGadgets.utils.GDyeColor;
import java.util.List;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;

public class CustomBannerType
{
    private String name;
    private String displayName;
    private String permission;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private GDyeColor baseColor;
    private List<Pattern> patterns;
    private GPatterns gPatterns;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    private ItemStack itemStack;
    
    public static void initCustomBanners() {
        final FileManager customBannersFile = FileManager.getCustomBannersFile();
        if (customBannersFile.get("Custom-Banners") != null) {
            for (final String s : customBannersFile.getConfigurationSection("Custom-Banners").getKeys(false)) {
                new CustomBannerType(s, "&5" + s.toLowerCase() + " Banner", "CookieGadgets.banners." + s.toLowerCase().replace(" ", ""), 12, Rarity.EPIC, null, GDyeColor.WHITE, null);
            }
        }
        else {
            customBannersFile.addDefault("Custom-Banners.Wolf.Name", "&5Wolf Banner");
            customBannersFile.addDefault("Custom-Banners.Wolf.Mystery Dust", 15);
            customBannersFile.addDefault("Custom-Banners.Wolf.Rarity", Rarity.EPIC.getName());
            customBannersFile.addDefault("Custom-Banners.Wolf.Enabled", false);
            customBannersFile.addDefault("Custom-Banners.Wolf.CanBeFound", true);
            customBannersFile.addDefault("Custom-Banners.Wolf.Purchasable", true);
            customBannersFile.addDefault("Custom-Banners.Wolf.Base-Color", "WHITE");
            customBannersFile.addDefault("Custom-Banners.Wolf.Patterns", Arrays.asList("BLACK:RHOMBUS_MIDDLE", "BLACK:RHOMBUS_MIDDLE", "LIGHT_BLUE:CURLY_BORDER", "LIGHT_BLUE:CIRCLE_MIDDLE", "LIGHT_BLUE:CREEPER", "LIGHT_BLUE:TRIANGLE_TOP"));
            customBannersFile.addDefault("Custom-Banners.Wolf.Lore.Locked", "");
            customBannersFile.addDefault("Custom-Banners.Wolf.Lore.Unlocked", "");
        }
    }
    
    public CustomBannerType(final String name, final String displayName, final String permission, final int mysteryDust, final Rarity rarity, final List<String> list, final GDyeColor gDyeColor, final List<Pattern> list2) {
        this.name = name;
        if (FileManager.getCustomBannersFile().get("Custom-Banners." + this.name + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getCustomBannersFile().getString("Custom-Banners." + this.name + ".Name");
        }
        this.permission = permission;
        if (FileManager.getCustomBannersFile().get("Custom-Banners." + this.name + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getCustomBannersFile().getInt("Custom-Banners." + this.name + ".Mystery Dust");
        }
        if (FileManager.getCustomBannersFile().get("Custom-Banners." + this.name + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getCustomBannersFile().getString("Custom-Banners." + this.name + ".Rarity"));
        }
        if (FileManager.getCustomBannersFile().get("Custom-Banners." + this.name + ".Enabled") == null) {
            FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Enabled", true);
            this.isEnable = true;
        }
        else {
            this.isEnable = FileManager.getCustomBannersFile().getBoolean("Custom-Banners." + this.name + ".Enabled");
        }
        if (FileManager.getCustomBannersFile().get("Custom-Banners." + this.name + ".CanBeFound") == null) {
            FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".CanBeFound", true);
            this.canBeFound = true;
        }
        else {
            this.canBeFound = FileManager.getCustomBannersFile().getBoolean("Custom-Banners." + this.name + ".CanBeFound");
        }
        if (FileManager.getCustomBannersFile().get("Custom-Banners." + this.name + ".Purchasable") == null) {
            FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Purchasable", true);
            this.purchasable = true;
        }
        else {
            this.purchasable = FileManager.getCustomBannersFile().getBoolean("Custom-Banners." + this.name + ".Purchasable");
        }
        if (FileManager.getCustomBannersFile().get("Custom-Banners." + this.name + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Lore.Locked", "");
                FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Lore.Locked", this.lockedLore);
                FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getCustomBannersFile().get("Custom-Banners." + this.name + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Lore.Locked", "");
                }
                else {
                    FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getCustomBannersFile().getStringList("Custom-Banners." + this.name + ".Lore.Locked");
            }
            if (FileManager.getCustomBannersFile().get("Custom-Banners." + this.name + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getCustomBannersFile().getStringList("Custom-Banners." + this.name + ".Lore.Unlocked");
            }
        }
        if (FileManager.getCustomBannersFile().get("Custom-Banners." + this.name + ".Base-Color") == null) {
            this.baseColor = GDyeColor.WHITE;
            FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Base-Color", "");
        }
        else {
            this.baseColor = GDyeColor.valueOf(FileManager.getCustomBannersFile().getString("Custom-Banners." + this.name + ".Base-Color"));
        }
        if (FileManager.getCustomBannersFile().get("Custom-Banners." + this.name + ".Patterns") == null) {
            this.patterns = null;
            FileManager.getCustomBannersFile().set("Custom-Banners." + this.name + ".Patterns", "");
        }
        else {
            final ArrayList<GPattern> list3 = new ArrayList<GPattern>();
            for (final String s : FileManager.getCustomBannersFile().getStringList("Custom-Banners." + this.name + ".Patterns")) {
                list3.add(new GPattern(GDyeColor.valueOf(s.split("\\:")[0].toUpperCase()), GPatternType.valueOf(s.split("\\:")[1].toUpperCase())));
            }
            this.gPatterns = new GPatterns(this.baseColor, list3);
            this.patterns = this.gPatterns.getPatterns();
        }
        this.itemStack = ItemUtils.itemBanner(this.displayName, this.baseColor, this.patterns, null);
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Cosmetics", "true");
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Category", Category.BANNERS.getNBTTag());
        new BannerType(this.name, this.displayName, this.permission, this.mysteryDust, this.rarity, this.lockedLore, this.unlockedLore, this.gPatterns, this.isEnable, this.canBeFound, this.purchasable, this.itemStack);
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
