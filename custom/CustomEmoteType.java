

package ws.billy.CookieGadgets.custom;

import ws.billy.CookieGadgets.cosmetics.emotes.EmoteType;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.CookieGadgets;
import java.util.Iterator;
import java.util.Arrays;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.List;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;

public class CustomEmoteType
{
    private String name;
    private String displayName;
    private String permission;
    private int cooldown;
    private int mysteryDust;
    private Rarity rarity;
    private boolean isHologramEnable;
    private String hologram;
    private List<String> lockedLore;
    private List<String> unlockedLore;
    private GMaterial.GMaterialHead headTexture;
    private Long ticksPerFrame;
    private boolean isEnable;
    private boolean canBeFound;
    private boolean purchasable;
    private ItemStack itemStack;
    private ArrayList<ItemStack> frames;
    
    public static void initCustomEmotes() {
        final FileManager customEmotesFile = FileManager.getCustomEmotesFile();
        if (customEmotesFile.get("Custom-Emotes") != null) {
            for (final String s : customEmotesFile.getConfigurationSection("Custom-Emotes").getKeys(false)) {
                new CustomEmoteType(s, "&a" + s.toLowerCase() + " Emote", "CookieGadgets.emotes." + s.toLowerCase().replace(" ", ""), 10, 10, Rarity.COMMON, false, "", null, new GMaterial.GMaterialHead("60c432cbc490a8af6e9dfeb28095c0a0ec79fff705fb184674d1e743bd05baa"), 2L, new ArrayList<ItemStack>());
            }
        }
        else {
            customEmotesFile.addDefault("Custom-Emotes.Smiles.Name", "&aSmiles Emote");
            customEmotesFile.addDefault("Custom-Emotes.Smiles.Mystery Dust", 10);
            customEmotesFile.addDefault("Custom-Emotes.Smiles.Cooldown", 10);
            customEmotesFile.addDefault("Custom-Emotes.Smiles.Rarity", Rarity.COMMON.getName());
            customEmotesFile.addDefault("Custom-Emotes.Smiles.Hologram.Enabled", false);
            customEmotesFile.addDefault("Custom-Emotes.Smiles.Hologram.Text", "");
            customEmotesFile.addDefault("Custom-Emotes.Smiles.Texture", "60c432cbc490a8af6e9dfeb28095c0a0ec79fff705fb184674d1e743bd05baa");
            customEmotesFile.addDefault("Custom-Emotes.Smiles.Enabled", false);
            customEmotesFile.addDefault("Custom-Emotes.Smiles.CanBeFound", true);
            customEmotesFile.addDefault("Custom-Emotes.Smiles.Purchasable", true);
            customEmotesFile.addDefault("Custom-Emotes.Smiles.TicksPerFrame", 5);
            customEmotesFile.addDefault("Custom-Emotes.Smiles.Frames", Arrays.asList("5:264614ad4bb2eb61b06b1a8b5d57f02448a975a8217ec16571f87c49227cbd", "1:60c432cbc490a8af6e9dfeb28095c0a0ec79fff705fb184674d1e743bd05baa", "11:41ac21d93ce17f2b7ee2e0e07a983eeb4a539e341ce5c77c36c722f77a2235", "1:4168b716281635ceafc3268dfa7d5f46466c8032e11c1cfb7db711a9f647d", "11:41ac21d93ce17f2b7ee2e0e07a983eeb4a539e341ce5c77c36c722f77a2235", "1:4168b716281635ceafc3268dfa7d5f46466c8032e11c1cfb7db711a9f647d", "3:41ac21d93ce17f2b7ee2e0e07a983eeb4a539e341ce5c77c36c722f77a2235"));
            customEmotesFile.addDefault("Custom-Emotes.Smiles.Lore.Locked", "");
            customEmotesFile.addDefault("Custom-Emotes.Smiles.Lore.Unlocked", "");
        }
    }
    
    public CustomEmoteType(final String name, final String displayName, final String permission, final int cooldown, final int mysteryDust, final Rarity rarity, final boolean b, final String hologram, final List<String> list, final GMaterial.GMaterialHead headTexture, final Long ticksPerFrame, final ArrayList<ItemStack> frames) {
        this.name = name;
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getCustomEmotesFile().getString("Custom-Emotes." + this.name + ".Name");
        }
        this.permission = permission;
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Mystery Dust") == null) {
            this.mysteryDust = mysteryDust;
            FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Mystery Dust", this.mysteryDust);
        }
        else {
            this.mysteryDust = FileManager.getCustomEmotesFile().getInt("Custom-Emotes." + this.name + ".Mystery Dust");
        }
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Cooldown") == null) {
            this.cooldown = cooldown;
            FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Cooldown", this.cooldown);
        }
        else {
            this.cooldown = FileManager.getCustomEmotesFile().getInt("Custom-Emotes." + this.name + ".Cooldown");
        }
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getCustomEmotesFile().getString("Custom-Emotes." + this.name + ".Rarity"));
        }
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Hologram.Enabled") == null) {
            this.isHologramEnable = true;
            FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Hologram.Enabled", true);
        }
        else {
            this.isHologramEnable = FileManager.getCustomEmotesFile().getBoolean("Custom-Emotes." + this.name + ".Hologram.Enabled");
        }
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Hologram.Text") == null) {
            this.hologram = hologram;
            FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Hologram.Text", this.hologram);
        }
        else {
            this.hologram = FileManager.getCustomEmotesFile().getString("Custom-Emotes." + this.name + ".Hologram.Text");
        }
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Texture") == null) {
            FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Texture", "");
            this.headTexture = headTexture;
        }
        else {
            this.headTexture = new GMaterial.GMaterialHead(FileManager.getCustomEmotesFile().getString("Custom-Emotes." + this.name + ".Texture"));
        }
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Enabled") == null) {
            FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Enabled", true);
            this.isEnable = true;
        }
        else {
            this.isEnable = FileManager.getCustomEmotesFile().getBoolean("Custom-Emotes." + this.name + ".Enabled");
        }
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".CanBeFound") == null) {
            FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".CanBeFound", true);
            this.canBeFound = true;
        }
        else {
            this.canBeFound = FileManager.getCustomEmotesFile().getBoolean("Custom-Emotes." + this.name + ".CanBeFound");
        }
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Purchasable") == null) {
            FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Purchasable", true);
            this.purchasable = true;
        }
        else {
            this.purchasable = FileManager.getCustomEmotesFile().getBoolean("Custom-Emotes." + this.name + ".Purchasable");
        }
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".TicksPerFrame") == null) {
            this.ticksPerFrame = ticksPerFrame;
            FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".TicksPerFrame", this.ticksPerFrame);
        }
        else {
            this.ticksPerFrame = (long)FileManager.getCustomEmotesFile().getInt("Custom-Emotes." + this.name + ".TicksPerFrame");
        }
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Frames") == null) {
            if ((this.frames = frames) == null) {
                FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Frames", "");
            }
            else {
                FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Frames", this.frames);
            }
        }
        else {
            final ArrayList<ItemStack> frames2 = new ArrayList<ItemStack>();
            for (final String s : FileManager.getCustomEmotesFile().getStringList("Custom-Emotes." + this.name + ".Frames")) {
                if (!s.contains(":")) {
                    frames2.add(CookieGadgets.getNMSManager().setNBTTag(CookieGadgets.getNMSManager().setNBTTag(ItemUtils.itemSkull(this.displayName, s), "Cosmetics", "true"), "Category", Category.EMOTES.getNBTTag()));
                }
                else {
                    for (int i = 1; i <= Integer.valueOf(s.split("\\:")[0]); ++i) {
                        frames2.add(CookieGadgets.getNMSManager().setNBTTag(CookieGadgets.getNMSManager().setNBTTag(ItemUtils.itemSkull(this.displayName, s.split("\\:")[1]), "Cosmetics", "true"), "Category", Category.EMOTES.getNBTTag()));
                    }
                }
            }
            this.frames = frames2;
        }
        if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Lore") == null) {
            this.lockedLore = list;
            if ((this.unlockedLore = list) == null) {
                FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Lore.Locked", "");
                FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Lore.Unlocked", "");
            }
            else {
                FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Lore.Locked", this.lockedLore);
                FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Lore.Unlocked", this.unlockedLore);
            }
        }
        else {
            if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Lore.Locked") == null) {
                if ((this.lockedLore = list) == null) {
                    FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Lore.Locked", "");
                }
                else {
                    FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Lore.Locked", this.lockedLore);
                }
            }
            else {
                this.lockedLore = FileManager.getCustomEmotesFile().getStringList("Custom-Emotes." + this.name + ".Lore.Locked");
            }
            if (FileManager.getCustomEmotesFile().get("Custom-Emotes." + this.name + ".Lore.Unlocked") == null) {
                if ((this.unlockedLore = list) == null) {
                    FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Lore.Unlocked", "");
                }
                else {
                    FileManager.getCustomEmotesFile().set("Custom-Emotes." + this.name + ".Lore.Unlocked", this.unlockedLore);
                }
            }
            else {
                this.unlockedLore = FileManager.getCustomEmotesFile().getStringList("Custom-Emotes." + this.name + ".Lore.Unlocked");
            }
        }
        this.itemStack = ItemUtils.item(this.displayName, this.headTexture.getMaterial());
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Cosmetics", "true");
        this.itemStack = CookieGadgets.getNMSManager().setNBTTag(this.itemStack, "Category", Category.EMOTES.getNBTTag());
        new EmoteType(this.name, this.displayName, this.permission, this.cooldown, this.mysteryDust, this.rarity, this.isHologramEnable, this.hologram, this.lockedLore, this.unlockedLore, this.headTexture, this.ticksPerFrame, this.isEnable, this.canBeFound, this.purchasable, this.itemStack, this.frames);
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
