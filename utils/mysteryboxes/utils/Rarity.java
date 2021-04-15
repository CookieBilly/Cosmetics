

package ws.billy.CookieGadgets.utils.mysteryboxes.utils;

import ws.billy.CookieGadgets.utils.MathUtil;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.SoundEffect;
import java.util.List;

public enum Rarity
{
    COMMON("COMMON", 0, "Common", "&aCommon", 70, 65, 58, 52, 0), 
    RARE("RARE", 1, "Rare", "&9Rare", 15, 18, 20, 22, 40), 
    EPIC("EPIC", 2, "Epic", "&5Epic", 10, 12, 15, 18, 50), 
    LEGENDARY("LEGENDARY", 3, "Legendary", "&6Legendary", 5, 6, 7, 8, 10);
    
    private String name;
    private String displayName;
    private int oneStarChance;
    private int twoStarChance;
    private int threeStarChance;
    private int fourStarChance;
    private int fiveStarChance;
    private boolean givePetItems;
    private int minPetItems;
    private int maxPetItems;
    private boolean isSendMessageEnabled;
    private List<String> foundLootMessages;
    private boolean isPlaySoundEnabled;
    private SoundEffect sound;
    private boolean isBroadcastMessagesEnabled;
    private String broadcastMessage;
    private String foundLootHologram;
    private boolean isGiveMysteryDustEnabled;
    private int minMysteryDust;
    private int maxMysteryDust;
    private boolean isSendAlreadyHadLootMessagesEnabled;
    private List<String> alreadyHadLootMessages;
    private boolean executeCustomCommand;
    private String customCommand;
    
    private Rarity(final String name, final int ordinal, final String name2, final String displayName, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.givePetItems = true;
        this.minPetItems = 25;
        this.maxPetItems = 40;
        this.isSendMessageEnabled = true;
        this.isPlaySoundEnabled = true;
        this.sound = SoundEffect.ENTITY_CHICKEN_EGG;
        this.isBroadcastMessagesEnabled = true;
        this.isGiveMysteryDustEnabled = true;
        this.minMysteryDust = 1;
        this.maxMysteryDust = 3;
        this.isSendAlreadyHadLootMessagesEnabled = true;
        this.executeCustomCommand = false;
        this.name = name2;
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Rarity." + this.name) == null) {
            this.displayName = displayName;
            FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Rarity." + this.name, displayName);
        }
        else {
            this.displayName = FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Rarity." + this.name);
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Chances.One-Star." + this.name) == null) {
            this.oneStarChance = n;
            FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Chances.One-Star." + this.name, n);
        }
        else {
            this.oneStarChance = FileManager.getMysteryBoxesFile().getInt("Mystery-Boxes.Chances.One-Star." + this.name);
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Chances.Two-Star." + this.name) == null) {
            this.twoStarChance = n2;
            FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Chances.Two-Star." + this.name, n2);
        }
        else {
            this.twoStarChance = FileManager.getMysteryBoxesFile().getInt("Mystery-Boxes.Chances.Two-Star." + this.name);
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Chances.Three-Star." + this.name) == null) {
            this.threeStarChance = n3;
            FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Chances.Three-Star." + this.name, n3);
        }
        else {
            this.threeStarChance = FileManager.getMysteryBoxesFile().getInt("Mystery-Boxes.Chances.Three-Star." + this.name);
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Chances.Four-Star." + this.name) == null) {
            this.fourStarChance = n4;
            FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Chances.Four-Star." + this.name, n4);
        }
        else {
            this.fourStarChance = FileManager.getMysteryBoxesFile().getInt("Mystery-Boxes.Chances.Four-Star." + this.name);
        }
        if (n5 != 0) {
            if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Chances.Five-Star." + this.name) == null) {
                this.fiveStarChance = n5;
                FileManager.getMysteryBoxesFile().set("Mystery-Boxes.Chances.Five-Star." + this.name, n5);
            }
            else {
                this.fiveStarChance = FileManager.getMysteryBoxesFile().getInt("Mystery-Boxes.Chances.Five-Star." + this.name);
            }
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Found-Loot." + this.name + ".Give-Pet-Items.Enabled") != null) {
            this.givePetItems = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Found-Loot." + this.name + ".Give-Pet-Items.Enabled");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Found-Loot." + this.name + ".Give-Pet-Items.Min") != null) {
            this.minPetItems = FileManager.getMysteryBoxesFile().getInt("Mystery-Boxes.Found-Loot." + this.name + ".Give-Pet-Items.Min");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Found-Loot." + this.name + ".Give-Pet-Items.Max") != null) {
            this.maxPetItems = FileManager.getMysteryBoxesFile().getInt("Mystery-Boxes.Found-Loot." + this.name + ".Give-Pet-Items.Max");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Found-Loot." + this.name + ".Give-Pet-Items.Enabled") != null) {
            this.givePetItems = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Found-Loot." + this.name + ".Give-Pet-Items.Enabled");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Found-Loot." + this.name + ".Send-Message.Enabled") != null) {
            this.isSendMessageEnabled = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Found-Loot." + this.name + ".Send-Message.Enabled");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Found-Loot." + this.name + ".Send-Message.Message") != null) {
            this.foundLootMessages = FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Found-Loot." + this.name + ".Send-Message.Message");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Found-Loot." + this.name + ".Play-Sound.Enabled") != null) {
            this.isPlaySoundEnabled = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Found-Loot." + this.name + ".Play-Sound.Enabled");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Found-Loot." + this.name + ".Play-Sound.Sound") != null) {
            this.sound = SoundEffect.valueOf(FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Found-Loot." + this.name + ".Play-Sound.Sound"));
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Broadcast.Found-Loot." + this.name + ".Enabled") != null) {
            this.isBroadcastMessagesEnabled = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Broadcast.Found-Loot." + this.name + ".Enabled");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Broadcast.Found-Loot." + this.name + ".Message") != null) {
            this.broadcastMessage = FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Broadcast.Found-Loot." + this.name + ".Message");
        }
        else {
            this.broadcastMessage = ChatUtil.format("&b[Mystery Box] {PLAYER} &bfound a " + this.displayName + " {LOOT}&b!");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Holograms.Found-Loot." + this.name) != null) {
            this.foundLootHologram = FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Holograms.Found-Loot." + this.name);
        }
        else {
            this.foundLootHologram = ChatUtil.format(String.valueOf(this.displayName) + " {LOOT}");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Already-Had-Loot." + this.name + ".Give-Mystery-Dust.Enabled") != null) {
            this.isGiveMysteryDustEnabled = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Already-Had-Loot." + this.name + ".Give-Mystery-Dust.Enabled");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Already-Had-Loot." + this.name + ".Give-Mystery-Dust.Min") != null) {
            this.minMysteryDust = FileManager.getMysteryBoxesFile().getInt("Mystery-Boxes.Already-Had-Loot." + this.name + ".Give-Mystery-Dust.Min");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Already-Had-Loot." + this.name + ".Give-Mystery-Dust.Max") != null) {
            this.maxMysteryDust = FileManager.getMysteryBoxesFile().getInt("Mystery-Boxes.Already-Had-Loot." + this.name + ".Give-Mystery-Dust.Max");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Already-Had-Loot." + this.name + ".Send-Message.Enabled") != null) {
            this.isSendAlreadyHadLootMessagesEnabled = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Already-Had-Loot." + this.name + ".Send-Message.Enabled");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Already-Had-Loot." + this.name + ".Send-Message.Message") != null) {
            this.alreadyHadLootMessages = FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Already-Had-Loot." + this.name + ".Send-Message.Message");
        }
        else {
            this.alreadyHadLootMessages = Arrays.asList("&7You already had " + this.displayName + " {LOOT} &7so you", "&7received &a{MYSTERY_DUST} Mystery Dust &7instead.");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Already-Had-Loot." + this.name + ".Execute-Command.Enabled") != null) {
            this.executeCustomCommand = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Already-Had-Loot." + this.name + ".Execute-Command.Enabled");
        }
        if (FileManager.getMysteryBoxesFile().get("Mystery-Boxes.Already-Had-Loot." + this.name + ".Execute-Command.Command") != null) {
            this.customCommand = FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Already-Had-Loot." + this.name + ".Execute-Command.Command");
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDisplayName() {
        return ChatUtil.format(this.displayName);
    }
    
    public int getOneStarChance() {
        return this.oneStarChance;
    }
    
    public int getTwoStarChance() {
        return this.twoStarChance;
    }
    
    public int getThreeStarChance() {
        return this.threeStarChance;
    }
    
    public int getFourStarChance() {
        return this.fourStarChance;
    }
    
    public int getFiveStarChance() {
        return this.fiveStarChance;
    }
    
    public int getChance(final Quality quality) {
        if (quality == Quality.ONE_STAR) {
            return this.oneStarChance;
        }
        if (quality == Quality.TWO_STAR) {
            return this.twoStarChance;
        }
        if (quality == Quality.THREE_STAR) {
            return this.threeStarChance;
        }
        if (quality == Quality.FOUR_STAR) {
            return this.fourStarChance;
        }
        if (quality == Quality.FIVE_STAR) {
            return this.fiveStarChance;
        }
        return this.oneStarChance;
    }
    
    public boolean isGivePetItemsEnabled() {
        return this.givePetItems;
    }
    
    public int getMinPetItems() {
        return this.minPetItems;
    }
    
    public int getMaxPetItems() {
        return this.maxPetItems;
    }
    
    public int getRandomPetItemsInRange() {
        if (this.maxPetItems > this.minPetItems) {
            return MathUtil.randomInt(this.minPetItems, this.maxPetItems);
        }
        return MathUtil.randomInt(this.maxPetItems, this.minPetItems);
    }
    
    public boolean isSendMessageEnabled() {
        return this.isSendMessageEnabled;
    }
    
    public List<String> getFoundLootMessages() {
        return this.foundLootMessages;
    }
    
    public boolean isPlaySoundEnabled() {
        return this.isPlaySoundEnabled;
    }
    
    public SoundEffect getSound() {
        return this.sound;
    }
    
    public boolean isBroadcastMessagesEnabled() {
        return this.isBroadcastMessagesEnabled;
    }
    
    public String getBroadcastMessage() {
        return this.broadcastMessage;
    }
    
    public String getFoundLootHologram() {
        return this.foundLootHologram;
    }
    
    public boolean isGiveMysteryDustEnabled() {
        return this.isGiveMysteryDustEnabled;
    }
    
    public int getMinMysteryDust() {
        return this.minMysteryDust;
    }
    
    public int getMaxMysteryDust() {
        return this.maxMysteryDust;
    }
    
    public int getRandomMysteryDustInRange() {
        if (this.maxMysteryDust > this.minMysteryDust) {
            return MathUtil.randomInt(this.minMysteryDust, this.maxMysteryDust);
        }
        return MathUtil.randomInt(this.maxMysteryDust, this.minMysteryDust);
    }
    
    public boolean isSendAlreadyHadLootMessagesEnabled() {
        return this.isSendAlreadyHadLootMessagesEnabled;
    }
    
    public List<String> getAlreadyHadLootMessages() {
        return this.alreadyHadLootMessages;
    }
    
    public boolean executeCustomCommand() {
        return this.executeCustomCommand;
    }
    
    public String getCustomCommand() {
        return this.customCommand;
    }
    
    public static Rarity getName(final String anotherString) {
        Rarity[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final Rarity rarity = values[i];
            if (rarity.getName().equalsIgnoreCase(anotherString)) {
                return rarity;
            }
        }
        return null;
    }
}
