

package ws.billy.CookieGadgets.mysteryboxes.customloot;

import ws.billy.CookieGadgets.utils.ChatUtil;
import java.util.Iterator;
import java.util.Arrays;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;
import java.util.List;

public class CustomMysteryBoxesLoot
{
    private static final List<CustomMysteryBoxesLoot> VALUES;
    private String name;
    private String displayName;
    private Rarity rarity;
    private String categoryName;
    private boolean canBeFound;
    private boolean executeCustomCommand;
    private List<String> customCommands;
    
    static {
        VALUES = new ArrayList<CustomMysteryBoxesLoot>();
    }
    
    public static void loadCustomLoots() {
        final FileManager customLootsFile = FileManager.getCustomLootsFile();
        if (customLootsFile.get("Custom-Loots") != null) {
            for (final String s : customLootsFile.getConfigurationSection("Custom-Loots").getKeys(false)) {
                new CustomMysteryBoxesLoot(s, s.toLowerCase(), Rarity.EPIC, "&cMoney", true, true, Arrays.asList("eco give {PLAYER} 10"));
            }
        }
        else {
            customLootsFile.addDefault("Custom-Loots.10-Money.Name", "&a10$");
            customLootsFile.addDefault("Custom-Loots.10-Money.Rarity", Rarity.COMMON.getName());
            customLootsFile.addDefault("Custom-Loots.10-Money.Category", "&cMoney");
            customLootsFile.addDefault("Custom-Loots.10-Money.CanBeFound", true);
            customLootsFile.addDefault("Custom-Loots.10-Money.Execute-Command.Enabled", true);
            customLootsFile.addDefault("Custom-Loots.10-Money.Execute-Command.Commands", Arrays.asList("eco give {PLAYER} 10"));
            customLootsFile.addDefault("Custom-Loots.12-TNT.Name", "&912x TNT (Block)");
            customLootsFile.addDefault("Custom-Loots.12-TNT.Rarity", Rarity.RARE.getName());
            customLootsFile.addDefault("Custom-Loots.12-TNT.Category", "&cBlock");
            customLootsFile.addDefault("Custom-Loots.12-TNT.CanBeFound", true);
            customLootsFile.addDefault("Custom-Loots.12-TNT.Execute-Command.Enabled", true);
            customLootsFile.addDefault("Custom-Loots.12-TNT.Execute-Command.Commands", Arrays.asList("give {PLAYER} minecraft:tnt 12"));
            customLootsFile.addDefault("Custom-Loots.16-Gold-Ore.Name", "&916x Gold Ore (Block)");
            customLootsFile.addDefault("Custom-Loots.16-Gold-Ore.Rarity", Rarity.RARE.getName());
            customLootsFile.addDefault("Custom-Loots.16-Gold-Ore.Category", "&cBlock");
            customLootsFile.addDefault("Custom-Loots.16-Gold-Ore.CanBeFound", true);
            customLootsFile.addDefault("Custom-Loots.16-Gold-Ore.Execute-Command.Enabled", true);
            customLootsFile.addDefault("Custom-Loots.16-Gold-Ore.Execute-Command.Commands", Arrays.asList("give {PLAYER} minecraft:gold_ore 16"));
            customLootsFile.addDefault("Custom-Loots.16-Diamond-Ore.Name", "&516x Diamond Ore (Block)");
            customLootsFile.addDefault("Custom-Loots.16-Diamond-Ore.Rarity", Rarity.EPIC.getName());
            customLootsFile.addDefault("Custom-Loots.16-Diamond-Ore.Category", "&cBlock");
            customLootsFile.addDefault("Custom-Loots.16-Diamond-Ore.CanBeFound", true);
            customLootsFile.addDefault("Custom-Loots.16-Diamond-Ore.Execute-Command.Enabled", true);
            customLootsFile.addDefault("Custom-Loots.16-Diamond-Ore.Execute-Command.Commands", Arrays.asList("give {PLAYER} minecraft:diamond_ore 16"));
            customLootsFile.addDefault("Custom-Loots.Diamond-Suit.Name", "&6Diamond Armor");
            customLootsFile.addDefault("Custom-Loots.Diamond-Suit.Rarity", Rarity.LEGENDARY.getName());
            customLootsFile.addDefault("Custom-Loots.Diamond-Suit.Category", "&cArmor");
            customLootsFile.addDefault("Custom-Loots.Diamond-Suit.CanBeFound", true);
            customLootsFile.addDefault("Custom-Loots.Diamond-Suit.Execute-Command.Enabled", true);
            customLootsFile.addDefault("Custom-Loots.Diamond-Suit.Execute-Command.Commands", Arrays.asList("give {PLAYER} minecraft:diamond_helmet 1", "give {PLAYER} minecraft:diamond_chestplate 1", "give {PLAYER} minecraft:diamond_leggings 1", "give {PLAYER} minecraft:diamond_boots 1"));
        }
    }
    
    public CustomMysteryBoxesLoot(final String name, final String displayName, final Rarity rarity, final String categoryName, final boolean canBeFound, final boolean executeCustomCommand, final List<String> customCommands) {
        this.displayName = null;
        this.executeCustomCommand = false;
        this.name = name;
        if (FileManager.getCustomLootsFile().get("Custom-Loots." + this.name + ".Name") == null) {
            this.displayName = displayName;
            FileManager.getCustomLootsFile().set("Custom-Loots." + this.name + ".Name", this.displayName);
        }
        else {
            this.displayName = FileManager.getCustomLootsFile().getString("Custom-Loots." + this.name + ".Name");
        }
        if (FileManager.getCustomLootsFile().get("Custom-Loots." + this.name + ".Rarity") == null) {
            this.rarity = rarity;
            FileManager.getCustomLootsFile().set("Custom-Loots." + this.name + ".Rarity", this.rarity.getName());
        }
        else {
            this.rarity = Rarity.getName(FileManager.getCustomLootsFile().getString("Custom-Loots." + this.name + ".Rarity"));
        }
        if (FileManager.getCustomLootsFile().get("Custom-Loots." + this.name + ".Category") == null) {
            this.categoryName = categoryName;
            FileManager.getCustomLootsFile().set("Custom-Loots." + this.name + ".Category", this.categoryName);
        }
        else {
            this.categoryName = FileManager.getCustomLootsFile().getString("Custom-Loots." + this.name + ".Category");
        }
        if (FileManager.getCustomLootsFile().get("Custom-Loots." + this.name + ".CanBeFound") == null) {
            this.canBeFound = canBeFound;
            FileManager.getCustomLootsFile().set("Custom-Loots." + this.name + ".CanBeFound", true);
        }
        else {
            this.canBeFound = FileManager.getCustomLootsFile().getBoolean("Custom-Loots." + this.name + ".CanBeFound");
        }
        if (FileManager.getCustomLootsFile().get("Custom-Loots." + this.name + ".Execute-Command.Enabled") == null) {
            this.executeCustomCommand = executeCustomCommand;
            FileManager.getCustomLootsFile().set("Custom-Loots." + this.name + ".Execute-Command.Enabled", true);
        }
        else {
            this.executeCustomCommand = FileManager.getCustomLootsFile().getBoolean("Custom-Loots." + this.name + ".Execute-Command.Enabled");
        }
        if (FileManager.getCustomLootsFile().get("Custom-Loots." + this.name + ".Execute-Command.Commands") == null) {
            this.customCommands = customCommands;
            FileManager.getCustomLootsFile().set("Custom-Loots." + this.name + ".Execute-Command.Commands", customCommands);
        }
        else {
            this.customCommands = FileManager.getCustomLootsFile().getStringList("Custom-Loots." + this.name + ".Execute-Command.Commands");
        }
        if (!CustomMysteryBoxesLoot.VALUES.contains(this)) {
            CustomMysteryBoxesLoot.VALUES.add(this);
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public String getDisplayNameStripColor() {
        return ChatUtil.stripColor(this.displayName);
    }
    
    public Rarity getRarity() {
        return this.rarity;
    }
    
    public String getCategoryName() {
        return this.categoryName;
    }
    
    public boolean canBeFound() {
        return this.canBeFound;
    }
    
    public boolean executeCustomCommand() {
        return this.executeCustomCommand;
    }
    
    public List<String> getCustomCommands() {
        return this.customCommands;
    }
    
    public static List<CustomMysteryBoxesLoot> values() {
        return CustomMysteryBoxesLoot.VALUES;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static CustomMysteryBoxesLoot valueOf(final String anotherString) {
        for (final CustomMysteryBoxesLoot customMysteryBoxesLoot : values()) {
            if (customMysteryBoxesLoot.getName().equalsIgnoreCase(anotherString)) {
                return customMysteryBoxesLoot;
            }
        }
        return null;
    }
}
