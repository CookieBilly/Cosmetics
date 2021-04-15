

package ws.billy.CookieGadgets.menu.menus;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.discount.ItemCostDiscount;
import java.util.Iterator;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.api.CookieGadgetsAPI;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.ClickType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.discount.DiscountManager;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.StringUtils;
import java.util.Arrays;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.cosmetics.hats.animated.AnimatedHatType;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.Listener;

public class AnimatedHatsMenu implements Listener
{
    public static void openAnimatedHatsMenu(final PlayerManager playerManager, int n) {
        final int size = AnimatedHatType.enabled().size();
        final int maxPagesAmount = GInventory.getMaxPagesAmount(27, size);
        if (n <= 0) {
            n = 1;
        }
        if (n > 1 && maxPagesAmount < n) {
            n = 1;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.format(String.valueOf(Category.ANIMATED_HATS.getGUIName()) + StringUtils.addPlaceholders(EnumItem.PAGES.getDisplayName(), Arrays.asList("{CURRENT_PAGE}", "{MAX_PAGES}"), Arrays.asList(String.valueOf(n), String.valueOf(maxPagesAmount)))));
        int n2 = 0;
        int n3 = 1;
        if (n > 1) {
            n3 = 27 * (n - 1) + 1;
        }
        int n4 = 27;
        if (size < 27) {
            n4 = size;
        }
        if (n > 1) {
            if (size >= 27 * n) {
                n4 = 27 * n;
            }
            else {
                n4 = size;
            }
        }
        final Player player = playerManager.getPlayer();
        for (int i = n3; i <= n4; ++i) {
            try {
                if (i > size) {
                    break;
                }
                final AnimatedHatType animatedHatType = AnimatedHatType.enabled().get(i - 1);
                if (PermissionUtils.noPermission(player, animatedHatType.getPermission(), EnumPermission.ANIMATED_HATS.getPermission(), false)) {
                    final DiscountManager discountManager = new DiscountManager(playerManager, Category.ANIMATED_HATS.isPurchasable() && animatedHatType.isPurchasable(), animatedHatType.getMysteryDust());
                    if (EnumItem.NO_PERMISSION.showCustomItem()) {
                        InventoryUtils.inventory(inventory, animatedHatType.getDisplayName(), EnumItem.NO_PERMISSION.getCustomItem(), animatedHatType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), GInventory.LAY_OUT_27.getLayOut()[n2++]);
                    }
                    else {
                        InventoryUtils.inventory(inventory, animatedHatType.getDisplayName(), animatedHatType.getMaterialHead().getMaterial(), animatedHatType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), GInventory.LAY_OUT_27.getLayOut()[n2++]);
                    }
                }
                else {
                    InventoryUtils.inventoryAddGlow(inventory, animatedHatType.getDisplayName(), animatedHatType.getMaterialHead().getMaterial(), animatedHatType.getUnlockedLore(), EnumItem.HAS_PERMISSION.isShowInLore() ? EnumItem.HAS_PERMISSION.getLore() : null, GInventory.LAY_OUT_27.getLayOut()[n2++], (playerManager.getEquippedAnimatedHat() == null) ? null : playerManager.getEquippedAnimatedHat().getDisplayName());
                }
            }
            catch (Exception ex) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                ex.printStackTrace();
                return;
            }
        }
        if (EnumItem.RESET_ANIMATED_HAT.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RESET_ANIMATED_HAT.getItemStack(), EnumItem.RESET_ANIMATED_HAT.getSlot());
        }
        if (n == 1) {
            if (EnumItem.GO_BACK.show()) {
                InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot());
            }
        }
        else {
            InventoryUtils.inventory(inventory, EnumItem.PREVIOUS_PAGE.getItemStack(), "{PAGE}", String.valueOf(n - 1), EnumItem.PREVIOUS_PAGE.getSlot());
        }
        if (n < maxPagesAmount) {
            InventoryUtils.inventory(inventory, EnumItem.NEXT_PAGE.getItemStack(), "{PAGE}", String.valueOf(n + 1), EnumItem.NEXT_PAGE.getSlot());
        }
        if (EnumItem.MAIN_MENU_ITEM.show()) {
            InventoryUtils.mainMenuButton(player, inventory, EnumItem.MAIN_MENU_ITEM.getSlot());
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
        playerManager.setCurrentMenuPage(n);
    }
    
    @EventHandler
    public void onInvClickAnimatedHats(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().startsWith(Category.ANIMATED_HATS.getGUIName())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (!Category.ANIMATED_HATS.isEnabled() || !WorldUtils.isWorldEnabled(player, true)) {
            inventoryClickEvent.setCancelled(true);
            player.updateInventory();
            return;
        }
        if (inventoryClickEvent.getCurrentItem() == null || inventoryClickEvent.getCurrentItem().getItemMeta() == null || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName() == null) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (inventoryClickEvent.getClick() == ClickType.SHIFT_LEFT || inventoryClickEvent.getClick() == ClickType.SHIFT_RIGHT || inventoryClickEvent.getClick() == ClickType.NUMBER_KEY || inventoryClickEvent.getClick() == ClickType.UNKNOWN) {
            inventoryClickEvent.setCancelled(true);
            player.updateInventory();
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot())) {
            CookieGadgetsAPI.goBackToMainMenu(player);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.RESET_ANIMATED_HAT.getItemStack(), EnumItem.RESET_ANIMATED_HAT.getSlot())) {
            playerManager.unequipAnimatedHat();
            player.sendMessage(MessageType.RESET_ANIMATED_HAT.getFormatMessage());
            if (EnumItem.RESET_ANIMATED_HAT.isPlaySoundEnabled()) {
                EnumItem.RESET_ANIMATED_HAT.getSound().playSound(player, 1.0f, 2.0f);
            }
            player.closeInventory();
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.PREVIOUS_PAGE.getDisplayName(), EnumItem.PREVIOUS_PAGE.getSlot())) {
            openAnimatedHatsMenu(playerManager, playerManager.getCurrentMenuPage() - 1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.NEXT_PAGE.getDisplayName(), EnumItem.NEXT_PAGE.getSlot())) {
            openAnimatedHatsMenu(playerManager, playerManager.getCurrentMenuPage() + 1);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.MAIN_MENU_ITEM.getDisplayName(), EnumItem.MAIN_MENU_ITEM.getSlot())) {
            CookieGadgetsAPI.goBackToMainMenu(player);
            inventoryClickEvent.setCancelled(true);
            return;
        }
        for (final AnimatedHatType animatedHatType : AnimatedHatType.values()) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(animatedHatType.getDisplayName()))) {
                if (PermissionUtils.noPermission(player, animatedHatType.getPermission(), EnumPermission.ANIMATED_HATS.getPermission(), false)) {
                    if (Category.ANIMATED_HATS.isPurchasable()) {
                        if (!animatedHatType.isPurchasable()) {
                            player.sendMessage(MessageType.ITEM_UNPURCHASABLE.getFormatMessage());
                            if (EnumItem.ITEM_UNPURCHASABLE.isPlaySoundEnabled()) {
                                EnumItem.ITEM_UNPURCHASABLE.getSound().playSound(player, 1.0f, 0.5f);
                            }
                            player.closeInventory();
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        final ItemCostDiscount itemCostDiscount = playerManager.getItemCostDiscount();
                        int n = animatedHatType.getMysteryDust();
                        if (itemCostDiscount != null) {
                            n = itemCostDiscount.getPriceAfterDiscount(animatedHatType.getMysteryDust());
                        }
                        if (playerManager.getMysteryDust() >= n) {
                            playerManager.purchaseData().setData(Category.ANIMATED_HATS, animatedHatType.getName(), animatedHatType.getDisplayName(), itemCostDiscount, animatedHatType.getMysteryDust(), animatedHatType.getPermission());
                            playerManager.openItemPurchaseMenu();
                        }
                        else {
                            player.sendMessage(MessageType.NOT_ENOUGH_MYSTERY_DUST_TO_PURCHASE.getFormatMessage());
                            if (EnumItem.NOT_ENOUGH_MYSTERY_DUST.isPlaySoundEnabled()) {
                                EnumItem.NOT_ENOUGH_MYSTERY_DUST.getSound().playSound(player, 1.0f, 0.5f);
                            }
                            player.closeInventory();
                        }
                        inventoryClickEvent.setCancelled(true);
                        return;
                    }
                    else {
                        if (PermissionUtils.noPermission(player, animatedHatType.getPermission(), EnumPermission.ANIMATED_HATS.getPermission(), true)) {
                            if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                            }
                            if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                                player.closeInventory();
                            }
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        continue;
                    }
                }
                else {
                    try {
                        if (playerManager.getEquippedAnimatedHat() != null && ChatUtil.format(animatedHatType.getDisplayName()).equals(playerManager.getEquippedAnimatedHat().getDisplayName())) {
                            playerManager.unequipAnimatedHat();
                            player.sendMessage(MessageType.RESET_ANIMATED_HAT.getFormatMessage());
                            if (EnumItem.RESET_ANIMATED_HAT.isPlaySoundEnabled()) {
                                EnumItem.RESET_ANIMATED_HAT.getSound().playSound(player, 1.0f, 2.0f);
                            }
                            player.closeInventory();
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        if (!animatedHatType.checkRequirement(playerManager)) {
                            if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                            }
                            player.closeInventory();
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        playerManager.equipAnimatedHat(animatedHatType);
                        player.sendMessage(MessageType.SELECT_ANIMATED_HAT.getFormatMessage().replace("{ANIMATED_HAT}", animatedHatType.getDisplayNameStripColor()));
                        if (EnumItem.HAS_PERMISSION.isPlaySoundEnabled()) {
                            EnumItem.HAS_PERMISSION.getSound().playSound(player, 1.0f, 2.0f);
                        }
                        if (EnumItem.HAS_PERMISSION.isCloseGUIMenuAfterSelect()) {
                            player.closeInventory();
                        }
                        else {
                            openAnimatedHatsMenu(playerManager, 1);
                        }
                        inventoryClickEvent.setCancelled(true);
                    }
                    catch (Exception ex) {
                        player.sendMessage(MessageType.ERROR.getFormatMessage());
                        ex.printStackTrace();
                    }
                }
            }
        }
        inventoryClickEvent.setCancelled(true);
    }
}
