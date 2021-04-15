

package ws.billy.CookieGadgets.utils;

import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.cosmetics.type.CosmeticType;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.tasks.AntiLagChecker;
import ws.billy.CookieGadgets.CookieGadgets;
import org.apache.commons.lang.Validate;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.discount.ItemCostDiscount;
import ws.billy.CookieGadgets.cosmetics.Category;

public class PurchaseData
{
    private Category category;
    private String name;
    private String displayName;
    private ItemCostDiscount discount;
    private int price;
    private String permission;
    private ItemStack showCaseItem;
    
    public void setData(final Category category, final String name, final String s, final ItemCostDiscount discount, final int price, final String permission) {
        this.category = category;
        this.name = name;
        this.displayName = ChatUtil.format(s);
        this.discount = discount;
        this.price = price;
        this.permission = permission;
        this.showCaseItem = null;
    }
    
    public void setData(final Category category, final String name, final String s, final ItemCostDiscount discount, final int price, final String permission, final ItemStack showCaseItem) {
        this.category = category;
        this.name = name;
        this.displayName = ChatUtil.format(s);
        this.discount = discount;
        this.price = price;
        this.permission = permission;
        this.showCaseItem = showCaseItem;
    }
    
    public void setCategory(final Category category) {
        this.category = category;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setDisplayName(final String s) {
        this.displayName = ChatUtil.format(s);
    }
    
    public void setItemCostDiscount(final ItemCostDiscount discount) {
        this.discount = discount;
    }
    
    public void setPrice(final int price) {
        this.price = price;
    }
    
    public void setPermission(final String permission) {
        this.permission = permission;
    }
    
    public void setShowCaseItem(final ItemStack showCaseItem) {
        this.showCaseItem = showCaseItem;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public ItemCostDiscount getItemCostDiscount() {
        return this.discount;
    }
    
    public int getPrice() {
        if (this.discount != null) {
            return this.discount.getPriceAfterDiscount(this.price);
        }
        return this.price;
    }
    
    public String getPermission() {
        return this.permission;
    }
    
    public ItemStack getShowCaseItem() {
        return this.showCaseItem;
    }
    
    public void equip(final PlayerManager playerManager) {
        Validate.notNull((Object)playerManager);
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
    
    public void reset() {
        this.category = null;
        this.name = null;
        this.displayName = null;
        this.price = 0;
        this.permission = null;
        this.showCaseItem = null;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.category.getName()) + ": " + this.name + ", " + ChatUtil.format(this.displayName) + ", " + this.price + ", " + this.permission;
    }
}
