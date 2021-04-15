

package ws.billy.CookieGadgets.utils.discount;

import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.entity.Player;
import java.util.Collection;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.CookieGadgets;
import java.util.ArrayList;
import java.util.List;
import ws.billy.CookieGadgets.player.PlayerManager;

public class DiscountManager
{
    private PlayerManager pManager;
    private boolean purchasable;
    private int price;
    private int discountedPrice;
    private List<String> lore;
    
    public DiscountManager(final PlayerManager pManager, final boolean purchasable, final int i) {
        this.pManager = pManager;
        this.purchasable = purchasable;
        this.price = i;
        this.lore = new ArrayList<String>();
        if (purchasable) {
            final ItemCostDiscount highestDiscountGroup = getHighestDiscountGroup(pManager.getPlayer());
            if (highestDiscountGroup == null && pManager.getItemCostDiscount() != null) {
                pManager.setItemCostDiscount(null);
            }
            if (CookieGadgets.getCookieGadgetsData().isItemCostDiscountEnabled() && CookieGadgets.getCookieGadgetsData().isCosmeticItemDiscountEnabled() && highestDiscountGroup != null) {
                pManager.setItemCostDiscount(highestDiscountGroup);
                this.discountedPrice = highestDiscountGroup.getPriceAfterDiscount(this.price);
                if (pManager.getMysteryDust() < this.discountedPrice) {
                    final Iterator<String> iterator = highestDiscountGroup.getNotEnoughMysteryDustLore().iterator();
                    while (iterator.hasNext()) {
                        this.lore.add(this.replacePlaceHolders(iterator.next(), this.price).replace("{COST_LEFT}", String.valueOf(this.discountedPrice - pManager.getMysteryDust())));
                    }
                }
                else {
                    final Iterator<String> iterator2 = highestDiscountGroup.getEnoughMysteryDustLore().iterator();
                    while (iterator2.hasNext()) {
                        this.lore.add(this.replacePlaceHolders(iterator2.next(), this.price));
                    }
                }
            }
            else if (pManager.getMysteryDust() < i) {
                final Iterator<String> iterator3 = EnumItem.NOT_ENOUGH_MYSTERY_DUST.getLore().iterator();
                while (iterator3.hasNext()) {
                    this.lore.add(iterator3.next().replace("{COST}", String.valueOf(i)).replace("{COST_LEFT}", String.valueOf(i - pManager.getMysteryDust())));
                }
            }
            else {
                final Iterator<String> iterator4 = EnumItem.ENOUGH_MYSTERY_DUST.getLore().iterator();
                while (iterator4.hasNext()) {
                    this.lore.add(iterator4.next().replace("{COST}", String.valueOf(i)));
                }
            }
        }
        else {
            final Iterator<String> iterator5 = EnumItem.ITEM_UNPURCHASABLE.getLore().iterator();
            while (iterator5.hasNext()) {
                this.lore.add(iterator5.next());
            }
        }
    }
    
    public DiscountManager(final PlayerManager pManager, final List<String> list, final int price) {
        this.pManager = pManager;
        this.purchasable = true;
        this.price = price;
        (this.lore = new ArrayList<String>()).addAll(list);
        final ItemCostDiscount highestDiscountGroup = getHighestDiscountGroup(pManager.getPlayer());
        if (highestDiscountGroup == null && pManager.getItemCostDiscount() != null) {
            pManager.setItemCostDiscount(null);
        }
        if (CookieGadgets.getCookieGadgetsData().isItemCostDiscountEnabled() && CookieGadgets.getCookieGadgetsData().isCraftingMysteryBoxDiscountEnabled() && highestDiscountGroup != null) {
            pManager.setItemCostDiscount(highestDiscountGroup);
            this.discountedPrice = highestDiscountGroup.getPriceAfterDiscount(this.price);
            if (pManager.getMysteryDust() < this.discountedPrice) {
                final Iterator<String> iterator = highestDiscountGroup.getNotEnoughMysteryDustLore().iterator();
                while (iterator.hasNext()) {
                    this.lore.add(this.replacePlaceHolders(iterator.next(), this.price).replace("{COST_LEFT}", String.valueOf(this.discountedPrice - pManager.getMysteryDust())));
                }
            }
            else {
                final Iterator<String> iterator2 = highestDiscountGroup.getEnoughMysteryDustLore().iterator();
                while (iterator2.hasNext()) {
                    this.lore.add(this.replacePlaceHolders(iterator2.next(), this.price));
                }
            }
        }
        else if (pManager.getMysteryDust() < price) {
            final Iterator<String> iterator3 = EnumItem.NOT_ENOUGH_MYSTERY_DUST_TO_CRAFT_MYSTERY_BOX.getLore().iterator();
            while (iterator3.hasNext()) {
                this.lore.add(iterator3.next().replace("{COST}", String.valueOf(this.price)).replace("{COST_LEFT}", String.valueOf(price - pManager.getMysteryDust())));
            }
        }
        else {
            final Iterator<String> iterator4 = EnumItem.ENOUGH_MYSTERY_DUST_TO_CRAFT_MYSTERY_BOX.getLore().iterator();
            while (iterator4.hasNext()) {
                this.lore.add(iterator4.next().replace("{COST}", String.valueOf(this.price)));
            }
        }
    }
    
    public PlayerManager getPlayerManager() {
        return this.pManager;
    }
    
    public boolean isPurchasable() {
        return this.purchasable;
    }
    
    public int getOldPrice() {
        return this.price;
    }
    
    public int getDiscountedPrice() {
        return this.discountedPrice;
    }
    
    public List<String> getLore() {
        return this.lore;
    }
    
    public static ItemCostDiscount getHighestDiscountGroup(final Player player) {
        if (player == null || !player.isOnline()) {
            return null;
        }
        ItemCostDiscount itemCostDiscount = null;
        for (final ItemCostDiscount itemCostDiscount2 : ItemCostDiscount.getArrangedGroups()) {
            if (!PermissionUtils.noPermission(player, itemCostDiscount2.getPermission(), EnumPermission.ITEM_COST_DISCOUNT.getPermission(), false)) {
                itemCostDiscount = itemCostDiscount2;
            }
        }
        return itemCostDiscount;
    }
    
    private String replacePlaceHolders(final String s, final int i) {
        String s2 = s.replace("{COST}", String.valueOf(i));
        for (final ItemCostDiscount itemCostDiscount : ItemCostDiscount.getArrangedGroups()) {
            if (s2.contains("COST}")) {
                s2 = s2.replace("{" + itemCostDiscount.getName().toUpperCase() + "_COST}", String.valueOf(itemCostDiscount.getPriceAfterDiscount(i)));
            }
        }
        return s2;
    }
}
