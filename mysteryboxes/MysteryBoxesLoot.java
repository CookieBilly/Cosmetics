

package ws.billy.CookieGadgets.mysteryboxes;

import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.tasks.AntiLagChecker;
import ws.billy.CookieGadgets.CookieGadgets;
import org.apache.commons.lang.Validate;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.log.LoggerManager;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticType;
import ws.billy.CookieGadgets.mysteryboxes.customloot.CustomMysteryBoxesLoot;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.LootCategory;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Rarity;

public class MysteryBoxesLoot
{
    private Rarity rarity;
    private Category category;
    private LootCategory lootCategory;
    private CustomMysteryBoxesLoot customMysteryBoxesLoot;
    private String name;
    private String displayName;
    private String permission;
    
    public MysteryBoxesLoot(final CustomMysteryBoxesLoot customMysteryBoxesLoot) {
        this.displayName = null;
        this.permission = null;
        this.rarity = customMysteryBoxesLoot.getRarity();
        this.category = null;
        this.lootCategory = LootCategory.CUSTOM;
        this.customMysteryBoxesLoot = customMysteryBoxesLoot;
        this.name = customMysteryBoxesLoot.getName();
        this.displayName = customMysteryBoxesLoot.getDisplayName();
    }
    
    public MysteryBoxesLoot(final Rarity rarity, final Category category, final String name) {
        this.displayName = null;
        this.permission = null;
        this.rarity = rarity;
        this.category = category;
        this.lootCategory = LootCategory.BUILT_IN;
        this.name = name;
        final CosmeticType value = CosmeticType.valueOf(name, category);
        if (value == null) {
            this.displayName = null;
        }
        else if (value.isEnabled() && value.canBeFound()) {
            this.displayName = value.getDisplayName();
            this.permission = value.getPermission();
        }
    }
    
    public Rarity getRarity() {
        return this.rarity;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public LootCategory getLootCategory() {
        return this.lootCategory;
    }
    
    public CustomMysteryBoxesLoot customMysteryBoxesLoot() {
        return this.customMysteryBoxesLoot;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public String getDisplayNameStripColor() {
        if (this.displayName == null) {
            return "Error";
        }
        return ChatUtil.stripColor(this.displayName);
    }
    
    public String getPermission() {
        if (this.lootCategory != LootCategory.BUILT_IN) {
            return null;
        }
        if (this.permission == null) {
            LoggerManager.warn("This item($s) has been disabled, so player won't get the permission.".replace("$s", this.getRarity() + " " + this.getCategory() + " " + this.getName()));
            return null;
        }
        return this.permission;
    }
    
    public void equip(final PlayerManager playerManager) {
        Validate.notNull((Object)playerManager);
        if (this.lootCategory != LootCategory.BUILT_IN) {
            return;
        }
        final Player player = playerManager.getPlayer();
        if (CookieGadgets.getCookieGadgetsData().isAntiLagEnabled() && CookieGadgets.getCookieGadgetsData().disableUsageIfLowTPS() && AntiLagChecker.isLowTPS()) {
            player.sendMessage(MessageType.DISABLE_USAGE_OF_COSMETICS.getFormatMessage());
            return;
        }
        if (WorldGuardUtils.isInBlacklistedRegion(player, BlacklistedRegion.valueOf(this.category))) {
            player.sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
            return;
        }
        final CosmeticType value = CosmeticType.valueOf(this.name, this.category);
        if (value != null && value.isEnabled() && value.canBeFound()) {
            value.equip(playerManager);
        }
    }
    
    @Override
    public String toString() {
        if (this.lootCategory != LootCategory.BUILT_IN) {
            return String.valueOf(this.rarity.getName()) + " %% " + this.customMysteryBoxesLoot.getCategoryName() + " %% " + this.name;
        }
        return String.valueOf(this.rarity.getName()) + " %% " + this.category.getName() + " %% " + this.name;
    }
}
