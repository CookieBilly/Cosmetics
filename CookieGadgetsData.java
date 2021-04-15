

package ws.billy.CookieGadgets;

import java.util.Iterator;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.GMaterial;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import java.util.List;
import ws.billy.CookieGadgets.utils.EnumClickType;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.EnumEquipType;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.AnimationType;
import ws.billy.CookieGadgets.utils.EnumInventorySort;
import ws.billy.CookieGadgets.economy.GStorage;
import ws.billy.CookieGadgets.database.DatabaseStorage;

public class CookieGadgetsData
{
    public static final String dateModified = "8 November 2020";
    public String configVersion;
    public boolean isPremium;
    private String prefix;
    private DatabaseStorage databaseStorage;
    private GStorage gStorage;
    private boolean checkUpdate;
    private int maxPetNameCharacters;
    private int defaultMysteryDust;
    private int gadgetSlot;
    private EnumInventorySort inventorySorting;
    private AnimationType defaultMysteryVaultAnimation;
    private boolean defaultSelfMorphView;
    private boolean isSelfMorphViewEnabled;
    private boolean showNameForMobDisguise;
    private boolean isMobDisguiseDamageEnabled;
    private boolean isAutoEquipAfterPurchaseEnabled;
    private boolean isAutoEquipOnLootFoundEnabled;
    private boolean showParticleEffectToEveryone;
    private boolean showCloakEffectToEveryone;
    private boolean hideParticleEffectForVanishedPlayer;
    private boolean hideCloakEffectForVanishedPlayer;
    private EnumEquipType equipCosmeticItemToSlot;
    private boolean syncCosmeticsOnJoin;
    private ItemStack menuSelector;
    private int menuSelectorSlot;
    private boolean giveMenuSelectorOnJoin;
    private EnumClickType menuSelectorClickType;
    private boolean ableToMoveMenuSelector;
    private List<String> enabled_worlds;
    private List<String> disabled_commands;
    private boolean isAntiLagEnabled;
    private int minimumTps;
    private boolean clearCosmeticsIfLowTps;
    private boolean disableUsageIfLowTps;
    private String mainMenuGuiName;
    private String settingsGuiName;
    private String itemPurchaseGuiName;
    private String mysteryVaultGuiName;
    private String confirmOpenMysteryBoxGuiName;
    private String mysteryBoxCraftingGuiName;
    private String giftInventoryGuiName;
    private String sendGiftGuiName;
    private String confirmSendGiftGuiName;
    private String mysteryVaultAnimationsGuiName;
    private String openMultipleBoxesGuiName;
    private String confirmOpenMultipleBoxesGuiName;
    private String petItemsGuiName;
    private boolean isCosmeticItemPurchasable;
    private boolean reopenGuiAfterPurchase;
    @Deprecated
    private boolean cosmeticItemPurchaseCommandEnabled;
    @Deprecated
    private String cosmeticItemPurchaseCommand;
    private boolean isMysteryBoxesEnabled;
    private boolean isMysteryBoxesRewardEnabled;
    private boolean isMysteryBoxesRewardAllowAFK;
    private List<String> enabledWorldsForMysteryBoxesReward;
    @Deprecated
    private boolean foundMysteryBoxLootCommandEnabled;
    @Deprecated
    private String foundMysteryBoxLootCommand;
    private boolean isExecuteCommandsWhenBackToMainMenu;
    private List<String> backToMainMenuCustomCommands;
    private boolean isLibsDisguiseEnable;
    private boolean isIDisguiseEnable;
    private boolean isItemCostDiscountEnabled;
    private boolean isCosmeticItemDiscountEnabled;
    private boolean isCraftingMysteryBoxDiscountEnabled;
    private boolean isFillEmptySlotWithItemEnable;
    private ItemStack fillEmptySlotItemStack;
    private boolean isCooldownInActionBar;
    private String remainCooldownBlock;
    private String retainCooldownBlock;
    private int amountOfCooldownBlocks;
    private String cooldownMessage;
    
    public CookieGadgetsData() {
        this.configVersion = "2.0.3";
        this.isPremium = true;
        this.databaseStorage = DatabaseStorage.SQLITE;
        this.gStorage = GStorage.DEFAULT;
        this.checkUpdate = false;
        this.maxPetNameCharacters = 16;
        this.defaultMysteryDust = 0;
        this.gadgetSlot = 5;
        this.inventorySorting = EnumInventorySort.DEFAULT;
        this.defaultMysteryVaultAnimation = AnimationType.NORMAL;
        this.defaultSelfMorphView = true;
        this.isSelfMorphViewEnabled = true;
        this.showNameForMobDisguise = true;
        this.isMobDisguiseDamageEnabled = true;
        this.isAutoEquipAfterPurchaseEnabled = true;
        this.isAutoEquipOnLootFoundEnabled = true;
        this.showParticleEffectToEveryone = true;
        this.showCloakEffectToEveryone = true;
        this.hideParticleEffectForVanishedPlayer = true;
        this.hideCloakEffectForVanishedPlayer = true;
        this.equipCosmeticItemToSlot = EnumEquipType.WARN;
        this.syncCosmeticsOnJoin = true;
        this.menuSelectorSlot = 4;
        this.giveMenuSelectorOnJoin = true;
        this.menuSelectorClickType = EnumClickType.LEFT_AND_RIGHT;
        this.ableToMoveMenuSelector = false;
        this.isAntiLagEnabled = true;
        this.minimumTps = 17;
        this.clearCosmeticsIfLowTps = true;
        this.disableUsageIfLowTps = true;
        this.mainMenuGuiName = "Main";
        this.settingsGuiName = "Settings";
        this.itemPurchaseGuiName = "Confirm Purchase";
        this.mysteryVaultGuiName = "Mystery Vault";
        this.confirmOpenMysteryBoxGuiName = "Confirm";
        this.mysteryBoxCraftingGuiName = "Mystery Box Crafting";
        this.giftInventoryGuiName = "Gift Inventory";
        this.sendGiftGuiName = "Online Players";
        this.confirmSendGiftGuiName = "Confirm";
        this.mysteryVaultAnimationsGuiName = "Mystery Vault Animations";
        this.openMultipleBoxesGuiName = "Open Multiple Boxes";
        this.confirmOpenMultipleBoxesGuiName = "Confirm Open Multiple Boxes";
        this.petItemsGuiName = "Pet Comsumables";
        this.isCosmeticItemPurchasable = true;
        this.reopenGuiAfterPurchase = true;
        this.cosmeticItemPurchaseCommandEnabled = false;
        this.cosmeticItemPurchaseCommand = "pex user {PLAYER} add {PERMISSION}";
        this.isMysteryBoxesEnabled = true;
        this.isMysteryBoxesRewardEnabled = true;
        this.isMysteryBoxesRewardAllowAFK = false;
        this.foundMysteryBoxLootCommandEnabled = false;
        this.foundMysteryBoxLootCommand = "pex user {PLAYER} add {PERMISSION}";
        this.isExecuteCommandsWhenBackToMainMenu = false;
        this.isLibsDisguiseEnable = false;
        this.isIDisguiseEnable = false;
        this.isItemCostDiscountEnabled = true;
        this.isCosmeticItemDiscountEnabled = true;
        this.isCraftingMysteryBoxDiscountEnabled = true;
        this.isFillEmptySlotWithItemEnable = false;
        this.fillEmptySlotItemStack = ItemUtils.item("&8", EnumMaterial.BLACK_STAINED_GLASS_PANE, 15, null);
        this.isCooldownInActionBar = true;
        this.amountOfCooldownBlocks = 100;
    }
    
    public void initData() {
        final String string = FileManager.getConfigFile().getString("Player-Data.Storage");
        if (string != null) {
            if (string.equalsIgnoreCase("file")) {
                FileManager.getConfigFile().set("Player-Data.Storage", (Object)"sqlite");
            }
            if (string.equalsIgnoreCase("sqlite")) {
                this.databaseStorage = DatabaseStorage.SQLITE;
            }
            else if (string.equalsIgnoreCase("mysql")) {
                this.databaseStorage = DatabaseStorage.MYSQL;
            }
        }
        String string2 = FileManager.getConfigFile().getString("Cosmetic-Item-Purchase.Mystery-Dust-Storage");
        if (string2 != null) {
            if (string2.equalsIgnoreCase("sqlite") || string2.equalsIgnoreCase("mysql")) {
                FileManager.getConfigFile().set("Cosmetic-Item-Purchase.Mystery-Dust-Storage", (Object)"default");
                string2 = "default";
            }
            final GStorage value = GStorage.valueOf(string2.toUpperCase());
            if (value == null) {
                FileManager.getConfigFile().set("Cosmetic-Item-Purchase.Mystery-Dust-Storage", (Object)"default");
            }
            else {
                this.gStorage = value;
            }
        }
        this.isCosmeticItemPurchasable = FileManager.getConfigFile().getBoolean("Cosmetic-Item-Purchase.Enabled");
        this.reopenGuiAfterPurchase = FileManager.getConfigFile().getBoolean("Cosmetic-Item-Purchase.Reopen-GUI-Menu-After-Purchase");
        if (FileManager.getConfigFile().getBoolean("Cosmetic-Item-Purchase.Execute-Command.Enabled")) {
            this.cosmeticItemPurchaseCommandEnabled = true;
            this.cosmeticItemPurchaseCommand = FileManager.getConfigFile().getString("Cosmetic-Item-Purchase.Execute-Command.Command");
        }
        this.maxPetNameCharacters = FileManager.getConfigFile().getInt("Settings.Max-Pet-Name-Characters");
        this.defaultMysteryDust = FileManager.getConfigFile().getInt("Settings.Starting-Mystery-Dust");
        this.gadgetSlot = FileManager.getConfigFile().getInt("Settings.Gadget-Slot");
        final String string3 = FileManager.getConfigFile().getString("Settings.Inventory-Sorting");
        if (string3 != null) {
            if (string3.equalsIgnoreCase("RARITY")) {
                this.inventorySorting = EnumInventorySort.RARITY;
            }
            else if (string3.equalsIgnoreCase("NAME")) {
                this.inventorySorting = EnumInventorySort.NAME;
            }
        }
        final AnimationType value2 = AnimationType.valueOf(FileManager.getConfigFile().getString("Settings.Default-Mystery-Vault-Animation"));
        if (value2 != null) {
            this.defaultMysteryVaultAnimation = value2;
        }
        this.defaultSelfMorphView = FileManager.getConfigFile().getBoolean("Settings.Default-Self-Morph-View");
        this.isSelfMorphViewEnabled = FileManager.getConfigFile().getBoolean("Settings.Enabled-Self-Morph-View");
        this.showNameForMobDisguise = FileManager.getConfigFile().getBoolean("Settings.Show-Name-For-Mob-Disguise");
        this.isMobDisguiseDamageEnabled = FileManager.getConfigFile().getBoolean("Settings.Enabled-Mob-Disguise-Damage");
        this.isAutoEquipAfterPurchaseEnabled = FileManager.getConfigFile().getBoolean("Settings.Auto-Equip-After-Purchase");
        this.isAutoEquipOnLootFoundEnabled = FileManager.getConfigFile().getBoolean("Settings.Auto-Equip-On-Loot-Found");
        this.showParticleEffectToEveryone = FileManager.getConfigFile().getBoolean("Settings.Show-Particle-Effect-To-Everyone");
        this.showCloakEffectToEveryone = FileManager.getConfigFile().getBoolean("Settings.Show-Cloak-Effect-To-Everyone");
        this.hideParticleEffectForVanishedPlayer = FileManager.getConfigFile().getBoolean("Settings.Hide-Particle-Effect-For-Vanished-Player");
        this.hideCloakEffectForVanishedPlayer = FileManager.getConfigFile().getBoolean("Settings.Hide-Cloak-Effect-For-Vanished-Player");
        final String string4 = FileManager.getConfigFile().getString("Settings.Equip-Cosmetic-Item-To-Slot");
        if (string4 != null) {
            if (string4.startsWith("REPLACE") || string4.equalsIgnoreCase("REPLACE")) {
                this.equipCosmeticItemToSlot = EnumEquipType.REPLACE;
            }
            else if (string4.startsWith("WARN") || string4.equalsIgnoreCase("WARN")) {
                this.equipCosmeticItemToSlot = EnumEquipType.WARN;
            }
            else if (string4.startsWith("DROP") || string4.equalsIgnoreCase("DROP")) {
                this.equipCosmeticItemToSlot = EnumEquipType.DROP;
            }
        }
        if (FileManager.getConfigFile().get("Settings.Sync-Cosmetics-On-Join") != null) {
            this.syncCosmeticsOnJoin = FileManager.getConfigFile().getBoolean("Settings.Sync-Cosmetics-On-Join");
        }
        this.menuSelector = ItemUtils.item(FileManager.getConfigFile().getString("Menu-Item.Name"), new GMaterial(FileManager.getConfigFile().getString("Menu-Item.Material")), FileManager.getConfigFile().getStringList("Menu-Item.Lore"));
        this.menuSelectorSlot = FileManager.getConfigFile().getInt("Menu-Item.Slot");
        this.giveMenuSelectorOnJoin = FileManager.getConfigFile().getBoolean("Menu-Item.Give-On-Join");
        final String string5 = FileManager.getConfigFile().getString("Menu-Item.Click-Type");
        if (string5 != null) {
            if (string5.equalsIgnoreCase("LEFT")) {
                this.menuSelectorClickType = EnumClickType.LEFT;
            }
            else if (string5.equalsIgnoreCase("RIGHT")) {
                this.menuSelectorClickType = EnumClickType.RIGHT;
            }
            else if (string5.equalsIgnoreCase("LEFT_AND_RIGHT")) {
                this.menuSelectorClickType = EnumClickType.LEFT_AND_RIGHT;
            }
        }
        this.ableToMoveMenuSelector = FileManager.getConfigFile().getBoolean("Menu-Item.Able-To-Move");
        this.enabled_worlds = (List<String>)FileManager.getConfigFile().getStringList("Enabled-Worlds");
        this.isItemCostDiscountEnabled = FileManager.getConfigFile().getBoolean("Item-Cost-Discount.Enabled");
        this.isCosmeticItemDiscountEnabled = FileManager.getConfigFile().getBoolean("Item-Cost-Discount.Discount.Cosmetic-Item");
        this.isCraftingMysteryBoxDiscountEnabled = FileManager.getConfigFile().getBoolean("Item-Cost-Discount.Discount.Crafting-Mystery-Box");
        this.isExecuteCommandsWhenBackToMainMenu = FileManager.getConfigFile().getBoolean("Back-To-Main-Menu.Execute-Commands.Enabled");
        this.backToMainMenuCustomCommands = (List<String>)FileManager.getConfigFile().getStringList("Back-To-Main-Menu.Execute-Commands.Commands");
        this.isFillEmptySlotWithItemEnable = FileManager.getConfigFile().getBoolean("Fill-Empty-Slot-With-Item.Enabled");
        this.fillEmptySlotItemStack = ItemUtils.item("&8", new GMaterial(FileManager.getConfigFile().getString("Fill-Empty-Slot-With-Item.Material")), null);
        final ArrayList<String> disabled_commands = new ArrayList<String>();
        final Iterator<String> iterator = FileManager.getConfigFile().getStringList("Disabled-Commands").iterator();
        while (iterator.hasNext()) {
            disabled_commands.add(iterator.next().replace("/", "").toLowerCase());
        }
        this.disabled_commands = disabled_commands;
        this.isAntiLagEnabled = FileManager.getConfigFile().getBoolean("Anti-Lag.Enabled");
        this.minimumTps = FileManager.getConfigFile().getInt("Anti-Lag.Minimum-TPS");
        this.clearCosmeticsIfLowTps = FileManager.getConfigFile().getBoolean("Anti-Lag.Action.Clear-Cosmetics");
        this.disableUsageIfLowTps = FileManager.getConfigFile().getBoolean("Anti-Lag.Action.Disable-Usage");
        this.checkUpdate = FileManager.getConfigFile().getBoolean("Check-Update");
        this.prefix = ChatUtil.format(FileManager.getMessagesFile().getString("Prefix"));
        this.mainMenuGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Main"));
        this.itemPurchaseGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Purchase-Menu.GUI-Name"));
        this.mysteryVaultGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Menu.GUI-Name"));
        this.confirmOpenMysteryBoxGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Open-Mystery-Box-Menu.GUI-Name"));
        this.settingsGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Settings-Menu.GUI-Name"));
        this.mysteryBoxCraftingGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Box-Crafting-Menu.GUI-Name"));
        this.giftInventoryGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Gift-Inventory-Menu.GUI-Name"));
        this.sendGiftGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Send-Gift-Menu.GUI-Name"));
        this.confirmSendGiftGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Send-Gift-Menu.GUI-Name"));
        this.mysteryVaultAnimationsGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Mystery-Vault-Animations-Menu.GUI-Name"));
        this.openMultipleBoxesGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Open-Multiple-Boxes-Menu.GUI-Name"));
        this.confirmOpenMultipleBoxesGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Confirm-Open-Multiple-Boxes-Menu.GUI-Name"));
        this.petItemsGuiName = ChatUtil.format(FileManager.getMessagesFile().getString("GUI-Menus.Pet-Items-Menu.GUI-Name"));
        this.isCooldownInActionBar = FileManager.getMessagesFile().getBoolean("Cooldown-In-Action-Bar.Enabled");
        this.remainCooldownBlock = FileManager.getMessagesFile().getString("Cooldown-In-Action-Bar.Cooldown-Block.Remain");
        this.retainCooldownBlock = FileManager.getMessagesFile().getString("Cooldown-In-Action-Bar.Cooldown-Block.Retain");
        this.amountOfCooldownBlocks = FileManager.getMessagesFile().getInt("Cooldown-In-Action-Bar.Cooldown-Block.Amount-Of-Blocks");
        this.cooldownMessage = FileManager.getMessagesFile().getString("Cooldown-In-Action-Bar.Cooldown-Message");
        this.isMysteryBoxesEnabled = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Enabled");
        this.isMysteryBoxesRewardEnabled = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Mystery-Boxes-Reward.Enabled");
        this.isMysteryBoxesRewardAllowAFK = FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Mystery-Boxes-Reward.Allow-AFK");
        this.enabledWorldsForMysteryBoxesReward = FileManager.getMysteryBoxesFile().getStringList("Mystery-Boxes.Mystery-Boxes-Reward.Enabled-Worlds");
        if (FileManager.getMysteryBoxesFile().getBoolean("Mystery-Boxes.Execute-Command.Enabled")) {
            this.foundMysteryBoxLootCommandEnabled = true;
            this.foundMysteryBoxLootCommand = FileManager.getMysteryBoxesFile().getString("Mystery-Boxes.Execute-Command.Command");
        }
        if (Bukkit.getPluginManager().isPluginEnabled("LibsDisguises")) {
            this.isLibsDisguiseEnable = true;
        }
        else if (Bukkit.getPluginManager().isPluginEnabled("iDisguise")) {
            this.isIDisguiseEnable = true;
        }
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public String getMainMenuGUIName() {
        return ChatUtil.format(this.mainMenuGuiName);
    }
    
    public String getSettingGUIName() {
        return ChatUtil.format(this.settingsGuiName);
    }
    
    public String getItemPurchaseGUIName() {
        return ChatUtil.format(this.itemPurchaseGuiName);
    }
    
    public String getMysteryVaultGUIName() {
        return ChatUtil.format(this.mysteryVaultGuiName);
    }
    
    public String getConfirmOpenMysteryBoxGUIName() {
        return ChatUtil.format(this.confirmOpenMysteryBoxGuiName);
    }
    
    public String getMysteryBoxCraftingGUIName() {
        return ChatUtil.format(this.mysteryBoxCraftingGuiName);
    }
    
    public String getGiftInventoryGUIName() {
        return ChatUtil.format(this.giftInventoryGuiName);
    }
    
    public String getSendGiftGUIName() {
        return ChatUtil.format(this.sendGiftGuiName);
    }
    
    public String getConfirmSendGiftGUIName() {
        return ChatUtil.format(this.confirmSendGiftGuiName);
    }
    
    public String getMysteryVaultAnimationsGUIName() {
        return ChatUtil.format(this.mysteryVaultAnimationsGuiName);
    }
    
    public String getOpenMultipleBoxesGUIName() {
        return ChatUtil.format(this.openMultipleBoxesGuiName);
    }
    
    public String getConfirmOpenMultipleBoxesGUIName() {
        return ChatUtil.format(this.confirmOpenMultipleBoxesGuiName);
    }
    
    public String getPetItemsGUIName() {
        return ChatUtil.format(this.petItemsGuiName);
    }
    
    public DatabaseStorage getDatabaseStorage() {
        return this.databaseStorage;
    }
    
    public GStorage getMysteryDustStorage() {
        return this.gStorage;
    }
    
    public void setMysteryDustStorage(final GStorage gStorage) {
        this.gStorage = gStorage;
    }
    
    public int getMaximumPetNameCharacters() {
        return this.maxPetNameCharacters;
    }
    
    public int getDefaultMysteryDust() {
        return this.defaultMysteryDust;
    }
    
    public int getGadgetSlot() {
        return this.gadgetSlot;
    }
    
    public EnumInventorySort getInventorySorting() {
        return this.inventorySorting;
    }
    
    public AnimationType getDefaultMysteryVaultAnimation() {
        return this.defaultMysteryVaultAnimation;
    }
    
    public boolean getDefaultSelfMorphView() {
        return this.defaultSelfMorphView;
    }
    
    public boolean isSelfMorphViewEnabled() {
        return this.isSelfMorphViewEnabled;
    }
    
    public boolean showNameForMobDisguise() {
        return this.showNameForMobDisguise;
    }
    
    public boolean isMobDisguiseDamageEnabled() {
        return this.isMobDisguiseDamageEnabled;
    }
    
    public boolean isAutoEquipAfterPurchaseEnabled() {
        return this.isAutoEquipAfterPurchaseEnabled;
    }
    
    public boolean isAutoEquipOnLootFoundEnabled() {
        return this.isAutoEquipOnLootFoundEnabled;
    }
    
    public boolean showParticleEffectToEveryone() {
        return this.showParticleEffectToEveryone;
    }
    
    public boolean showCloakEffectToEveryone() {
        return this.showCloakEffectToEveryone;
    }
    
    public boolean hideParticleEffectForVanishedPlayer() {
        return this.hideParticleEffectForVanishedPlayer;
    }
    
    public boolean hideCloakEffectForVanishedPlayer() {
        return this.hideCloakEffectForVanishedPlayer;
    }
    
    public EnumEquipType getEquipCosmeticItemToSlotAction() {
        return this.equipCosmeticItemToSlot;
    }
    
    public boolean syncCosmeticsOnJoin() {
        return this.syncCosmeticsOnJoin;
    }
    
    public ItemStack getMenuSelector() {
        return this.menuSelector;
    }
    
    public int getMenuSelectorSlot() {
        return this.menuSelectorSlot;
    }
    
    public boolean isGiveMenuSelectorOnJoin() {
        return this.giveMenuSelectorOnJoin;
    }
    
    public EnumClickType getMenuSelectorClickType() {
        return this.menuSelectorClickType;
    }
    
    public boolean isAbleToMoveMenuSelector() {
        return this.ableToMoveMenuSelector;
    }
    
    public List<String> getEnabledWorlds() {
        return this.enabled_worlds;
    }
    
    public List<String> getDisabledCommands() {
        return this.disabled_commands;
    }
    
    public boolean isAntiLagEnabled() {
        return this.isAntiLagEnabled;
    }
    
    public int getMinimumTPS() {
        return this.minimumTps;
    }
    
    public boolean clearCosmeticsIfLowTPS() {
        return this.clearCosmeticsIfLowTps;
    }
    
    public boolean disableUsageIfLowTPS() {
        return this.disableUsageIfLowTps;
    }
    
    public boolean isCosmeticItemPurchasable() {
        return this.isCosmeticItemPurchasable;
    }
    
    public boolean isReopenGUIMenuAfterPurchase() {
        return this.reopenGuiAfterPurchase;
    }
    
    public boolean isMysteryBoxesEnabled() {
        return this.isMysteryBoxesEnabled;
    }
    
    public boolean isMysteryBoxesRewardEnabled() {
        return this.isMysteryBoxesRewardEnabled;
    }
    
    public boolean isMysteryBoxesRewardAllowAFK() {
        return this.isMysteryBoxesRewardAllowAFK;
    }
    
    public List<String> getEnabledWorldsForMysteryBoxesReward() {
        return this.enabledWorldsForMysteryBoxesReward;
    }
    
    public boolean isItemCostDiscountEnabled() {
        return this.isItemCostDiscountEnabled;
    }
    
    public boolean isCosmeticItemDiscountEnabled() {
        return this.isCosmeticItemDiscountEnabled;
    }
    
    public boolean isCraftingMysteryBoxDiscountEnabled() {
        return this.isCraftingMysteryBoxDiscountEnabled;
    }
    
    public boolean isExecuteCommandsWhenBackToMainMenu() {
        return this.isExecuteCommandsWhenBackToMainMenu;
    }
    
    public List<String> getBackToMainMenuCustomCommands() {
        return this.backToMainMenuCustomCommands;
    }
    
    public boolean isFillEmptySlotWithItemEnabled() {
        return this.isFillEmptySlotWithItemEnable;
    }
    
    public ItemStack getFillEmptySlotItemStack() {
        return this.fillEmptySlotItemStack;
    }
    
    public boolean isCheckUpdateEnabled() {
        return this.checkUpdate;
    }
    
    public boolean isLibsDisguiseEnabled() {
        return this.isLibsDisguiseEnable;
    }
    
    public boolean isIDisguiseEnabled() {
        return this.isIDisguiseEnable;
    }
    
    public boolean isCooldownInActionBar() {
        return this.isCooldownInActionBar;
    }
    
    public String getRemainCooldownBlock() {
        return this.remainCooldownBlock;
    }
    
    public String getRetainCooldownBlock() {
        return this.retainCooldownBlock;
    }
    
    public int getTheAmountOfCooldownBlocks() {
        return this.amountOfCooldownBlocks;
    }
    
    public String getCooldownMessage() {
        return this.cooldownMessage;
    }
    
    @Deprecated
    public boolean isCosmeticItemPurchaseCommandEnabled() {
        return this.cosmeticItemPurchaseCommandEnabled;
    }
    
    @Deprecated
    public String getCosmeticItemPurchaseCommand() {
        return this.cosmeticItemPurchaseCommand;
    }
    
    @Deprecated
    public boolean isFoundMysteryBoxLootCommandEnabled() {
        return this.foundMysteryBoxLootCommandEnabled;
    }
    
    @Deprecated
    public String getFoundMysteryBoxLootCommand() {
        return this.foundMysteryBoxLootCommand;
    }

}
