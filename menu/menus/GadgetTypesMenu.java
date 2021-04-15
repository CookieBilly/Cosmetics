

package ws.billy.CookieGadgets.menu.menus;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.discount.ItemCostDiscount;
import java.util.Iterator;
import ws.billy.CookieGadgets.api.CookieGadgetsAPI;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.ClickType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.discount.DiscountManager;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetCategoryType;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.Listener;

public class GadgetTypesMenu implements Listener
{
    public static void openGadgetTypesMenu(final PlayerManager playerManager, final GadgetCategoryType gadgetCategoryType, int n) {
        final int size = GadgetType.getGadgetCategory(gadgetCategoryType).size();
        final int maxPagesAmount = GInventory.getMaxPagesAmount(27, size);
        if (n <= 0) {
            n = 1;
        }
        if (n > 1 && maxPagesAmount < n) {
            n = 1;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.format(gadgetCategoryType.getGUIName()));
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
                final GadgetType gadgetType = GadgetType.getGadgetCategory(gadgetCategoryType).get(i - 1);
                if (PermissionUtils.noPermission(player, gadgetType.getPermission(), EnumPermission.GADGETS.getPermission(), false)) {
                    final DiscountManager discountManager = new DiscountManager(playerManager, Category.GADGETS.isPurchasable() && gadgetType.isPurchasable(), gadgetType.getMysteryDust());
                    if (EnumItem.NO_PERMISSION.showCustomItem()) {
                        InventoryUtils.inventory(inventory, gadgetType.getDisplayName(), EnumItem.NO_PERMISSION.getCustomItem(), gadgetType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), GInventory.LAY_OUT_27.getLayOut()[n2++]);
                    }
                    else {
                        InventoryUtils.inventory(inventory, gadgetType.getDisplayName(), gadgetType.getMaterial(), gadgetType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), GInventory.LAY_OUT_27.getLayOut()[n2++]);
                    }
                }
                else {
                    InventoryUtils.inventoryAddGlow(inventory, gadgetType.getDisplayName(), gadgetType.getMaterial(), gadgetType.getUnlockedLore(), EnumItem.HAS_PERMISSION.isShowInLore() ? EnumItem.HAS_PERMISSION.getLore() : null, GInventory.LAY_OUT_27.getLayOut()[n2++], (playerManager.getEquippedGadget() == null) ? null : playerManager.getEquippedGadget().getDisplayName());
                }
            }
            catch (Exception ex) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                ex.printStackTrace();
                return;
            }
        }
        if (EnumItem.RESET_GADGET.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RESET_GADGET.getItemStack(), EnumItem.RESET_GADGET.getSlot());
        }
        if (EnumItem.GO_BACK.show()) {
            InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), EnumItem.GO_BACK.getSlot());
        }
        if (EnumItem.MAIN_MENU_ITEM.show()) {
            InventoryUtils.mainMenuButton(player, inventory, EnumItem.MAIN_MENU_ITEM.getSlot());
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler
    public void onInvClickGadgets(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        for (final GadgetCategoryType gadgetCategoryType : GadgetCategoryType.values()) {
            if (inventoryClickEvent.getView().getTitle().equals(ChatUtil.format(gadgetCategoryType.getGUIName()))) {
                if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (!Category.GADGETS.isEnabled() || !WorldUtils.isWorldEnabled(player, true)) {
                    player.closeInventory();
                    inventoryClickEvent.setCancelled(true);
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
                    CookieGadgets.getPlayerManager(player).openCategoryCookieGadgets();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
                if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.RESET_GADGET.getItemStack(), EnumItem.RESET_GADGET.getSlot())) {
                    playerManager.unequipGadget();
                    player.sendMessage(MessageType.RESET_GADGET.getFormatMessage());
                    if (EnumItem.RESET_GADGET.isPlaySoundEnabled()) {
                        EnumItem.RESET_GADGET.getSound().playSound(player, 1.0f, 2.0f);
                    }
                    player.closeInventory();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.MAIN_MENU_ITEM.getDisplayName(), EnumItem.MAIN_MENU_ITEM.getSlot())) {
                    CookieGadgetsAPI.goBackToMainMenu(player);
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                for (final GadgetType gadgetType : GadgetType.getGadgetCategory(gadgetCategoryType)) {
                    if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(gadgetType.getDisplayName()))) {
                        if (PermissionUtils.noPermission(player, gadgetType.getPermission(), EnumPermission.GADGETS.getPermission(), false)) {
                            if (Category.GADGETS.isPurchasable()) {
                                if (!gadgetType.isPurchasable()) {
                                    player.sendMessage(MessageType.ITEM_UNPURCHASABLE.getFormatMessage());
                                    if (EnumItem.ITEM_UNPURCHASABLE.isPlaySoundEnabled()) {
                                        EnumItem.ITEM_UNPURCHASABLE.getSound().playSound(player, 1.0f, 0.5f);
                                    }
                                    player.closeInventory();
                                    inventoryClickEvent.setCancelled(true);
                                    return;
                                }
                                final ItemCostDiscount itemCostDiscount = playerManager.getItemCostDiscount();
                                int n = gadgetType.getMysteryDust();
                                if (itemCostDiscount != null) {
                                    n = itemCostDiscount.getPriceAfterDiscount(gadgetType.getMysteryDust());
                                }
                                if (playerManager.getMysteryDust() >= n) {
                                    playerManager.purchaseData().setData(Category.GADGETS, gadgetType.getName(), gadgetType.getDisplayName(), itemCostDiscount, gadgetType.getMysteryDust(), gadgetType.getPermission());
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
                            else if (PermissionUtils.noPermission(player, gadgetType.getPermission(), EnumPermission.GADGETS.getPermission(), true)) {
                                if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                                }
                                if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                                    player.closeInventory();
                                }
                                inventoryClickEvent.setCancelled(true);
                                return;
                            }
                        }
                        if (playerManager.getEquippedGadget() != null && ChatUtil.format(gadgetType.getDisplayName()).equals(playerManager.getEquippedGadget().getDisplayName())) {
                            playerManager.unequipGadget();
                            player.sendMessage(MessageType.RESET_GADGET.getFormatMessage());
                            if (EnumItem.RESET_GADGET.isPlaySoundEnabled()) {
                                EnumItem.RESET_GADGET.getSound().playSound(player, 1.0f, 2.0f);
                            }
                            player.closeInventory();
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        if (!gadgetType.checkRequirement(playerManager)) {
                            if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                            }
                            player.closeInventory();
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        player.sendMessage(MessageType.SELECT_GADGET.getFormatMessage().replace("{GADGET}", gadgetType.getDisplayNameStripColor()));
                        playerManager.equipGadget(gadgetType);
                        if (EnumItem.HAS_PERMISSION.isPlaySoundEnabled()) {
                            EnumItem.HAS_PERMISSION.getSound().playSound(player, 1.0f, 2.0f);
                        }
                        if (EnumItem.HAS_PERMISSION.isCloseGUIMenuAfterSelect()) {
                            player.closeInventory();
                        }
                        else {
                            openGadgetTypesMenu(playerManager, gadgetCategoryType, 1);
                        }
                        inventoryClickEvent.setCancelled(true);
                        return;
                    }
                }
                inventoryClickEvent.setCancelled(true);
            }
        }
    }
}
