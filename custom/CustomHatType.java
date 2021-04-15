

package ws.billy.CookieGadgets.custom;

import ws.billy.CookieGadgets.cosmetics.hats.standard.HatType;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.configuration.FileManager;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.List;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;

public class CustomHatType
{
    private String name;
    private String displayName;
    private String permission;
    private int mysteryDust;
    private Rarity rarity;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private GMaterial material;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    private ItemStack itemStack;
    
    public static void initCustomHats() {
        final FileManager customHatsFile = FileManager.getCustomHatsFile();
        if (customHatsFile.get("Custom-Hats") != null) {
            for (final String s : customHatsFile.getConfigurationSection("Custom-Hats").getKeys(false)) {
                new CustomHatType(s, "&a" + s.toLowerCase() + " Hat", "CookieGadgets.hats." + s.toLowerCase().replace(" ", ""), 12, Rarity.COMMON, null, new GMaterial(EnumMaterial.STONE), "eb7af9e4411217c7de9c60acbd3c3fd6519783332a1b3bc56fbfce90721ef35");
            }
        }
        else {
            customHatsFile.addDefault("Custom-Hats.Steve.Name", "&aSteve Hat");
            customHatsFile.addDefault("Custom-Hats.Steve.Mystery Dust", 12);
            customHatsFile.addDefault("Custom-Hats.Steve.Rarity", Rarity.COMMON.getName());
            customHatsFile.addDefault("Custom-Hats.Steve.Texture", "eb7af9e4411217c7de9c60acbd3c3fd6519783332a1b3bc56fbfce90721ef35");
            customHatsFile.addDefault("Custom-Hats.Steve.Enabled", false);
            customHatsFile.addDefault("Custom-Hats.Steve.CanBeFound", true);
            customHatsFile.addDefault("Custom-Hats.Steve.Purchasable", true);
            customHatsFile.addDefault("Custom-Hats.Steve.Lore.Locked", "");
            customHatsFile.addDefault("Custom-Hats.Steve.Lore.Unlocked", "");
            customHatsFile.addDefault("Custom-Hats.Sponge.Name", "&aSponge Hat");
            customHatsFile.addDefault("Custom-Hats.Sponge.Mystery Dust", 12);
            customHatsFile.addDefault("Custom-Hats.Sponge.Rarity", Rarity.COMMON.getName());
            customHatsFile.addDefault("Custom-Hats.Sponge.Material", "SPONGE");
            customHatsFile.addDefault("Custom-Hats.Sponge.Enabled", false);
            customHatsFile.addDefault("Custom-Hats.Sponge.CanBeFound", true);
            customHatsFile.addDefault("Custom-Hats.Sponge.Purchasable", true);
            customHatsFile.addDefault("Custom-Hats.Sponge.Lore.Locked", "");
            customHatsFile.addDefault("Custom-Hats.Sponge.Lore.Unlocked", "");
        }
    }
    
    public CustomHatType(final String name, final String displayName, final String permission, final int mysteryDust, final Rarity rarity, final List<String> list, final GMaterial material, final String s) {
        this.name = name;
        if (FileManager.getCustomHatsFile().get("Custom-Hats." + this.name + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getCustomHatsFile().getString("Custom-Hats." + this.name + ".Name");
        }
        this.permission = permission;
        if (FileManager.getCustomHatsFile().get("Custom-Hats." + this.name + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getCustomHatsFile().getInt("Custom-Hats." + this.name + ".Mystery Dust");
        }
        if (FileManager.getCustomHatsFile().get("Custom-Hats." + this.name + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getCustomHatsFile().getString("Custom-Hats." + this.name + ".Rarity"));
        }
        if (FileManager.getCustomHatsFile().get("Custom-Hats." + this.name + ".Enabled") == null) {
            FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Enabled", true);
            this.isEnable = true;
        }
        else {
            this.isEnable = FileManager.getCustomHatsFile().getBoolean("Custom-Hats." + this.name + ".Enabled");
        }
        if (FileManager.getCustomHatsFile().get("Custom-Hats." + this.name + ".CanBeFound") == null) {
            FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".CanBeFound", true);
            this.canBeFound = true;
        }
        else {
            this.canBeFound = FileManager.getCustomHatsFile().getBoolean("Custom-Hats." + this.name + ".CanBeFound");
        }
        if (FileManager.getCustomHatsFile().get("Custom-Hats." + this.name + ".Purchasable") == null) {
            FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Purchasable", true);
            this.purchasable = true;
        }
        else {
            this.purchasable = FileManager.getCustomHatsFile().getBoolean("Custom-Hats." + this.name + ".Purchasable");
        }
        if (FileManager.getCustomHatsFile().get("Custom-Hats." + this.name + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Lore.Locked", "");
                FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Lore.Locked", this.lockedLore);
                FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getCustomHatsFile().get("Custom-Hats." + this.name + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Lore.Locked", "");
                }
                else {
                    FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getCustomHatsFile().getStringList("Custom-Hats." + this.name + ".Lore.Locked");
            }
            if (FileManager.getCustomHatsFile().get("Custom-Hats." + this.name + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getCustomHatsFile().getStringList("Custom-Hats." + this.name + ".Lore.Unlocked");
            }
        }
        if (FileManager.getCustomHatsFile().get("Custom-Hats." + this.name + ".Material") == null) {
            this.material = material;
        }
        else {
            this.material = new GMaterial(FileManager.getCustomHatsFile().getString("Custom-Hats." + this.name + ".Material"));
        }
        if (FileManager.getCustomHatsFile().get("Custom-Hats." + this.name + ".Texture") == null) {
            if (FileManager.getCustomHatsFile().get("Custom-Hats." + this.name + ".Material") == null) {
                FileManager.getCustomHatsFile().set("Custom-Hats." + this.name + ".Texture", "");
            }
        }
        else {
            this.material = new GMaterial("head:" + FileManager.getCustomHatsFile().getString("Custom-Hats." + this.name + ".Texture"));
        }
        this.itemStack = ItemUtils.item(this.displayName, this.material);
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Cosmetics", "true");
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Category", Category.HATS.getNBTTag());
        new HatType(this.name, this.displayName, this.permission, this.mysteryDust, this.rarity, this.lockedLore, this.unlockedLore, this.material, this.isEnable, this.canBeFound, this.purchasable, this.itemStack);
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
