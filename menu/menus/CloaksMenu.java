

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
import ws.billy.CookieGadgets.utils.GInventory;
import ws.billy.CookieGadgets.utils.EnumItem;
import ws.billy.CookieGadgets.utils.discount.DiscountManager;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.Listener;

public class CloaksMenu implements Listener
{
    public static void openCloaksMenu(final PlayerManager playerManager) {
        final int size = CloakType.enabled().size();
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.format(Category.CLOAKS.getGUIName()));
        int n = 0;
        final int n2 = 1;
        int n3 = 21;
        if (size < 21) {
            n3 = size;
        }
        final Player player = playerManager.getPlayer();
        for (int i = n2; i <= n3; ++i) {
            try {
                if (i > size) {
                    break;
                }
                final CloakType cloakType = CloakType.enabled().get(i - 1);
                if (PermissionUtils.noPermission(player, cloakType.getPermission(), EnumPermission.CLOAKS.getPermission(), false)) {
                    final DiscountManager discountManager = new DiscountManager(playerManager, Category.CLOAKS.isPurchasable() && cloakType.isPurchasable(), cloakType.getMysteryDust());
                    if (EnumItem.NO_PERMISSION.showCustomItem()) {
                        InventoryUtils.inventory(inventory, cloakType.getDisplayName(), EnumItem.NO_PERMISSION.getCustomItem(), cloakType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), GInventory.LAY_OUT_21.getLayOut()[n++]);
                    }
                    else {
                        InventoryUtils.inventory(inventory, cloakType.getDisplayName(), cloakType.getMaterial(), cloakType.getLockedLore(), EnumItem.NO_PERMISSION.isShowInLore() ? EnumItem.NO_PERMISSION.getLore() : null, discountManager.getLore(), GInventory.LAY_OUT_21.getLayOut()[n++]);
                    }
                }
                else {
                    InventoryUtils.inventoryAddGlow(inventory, cloakType.getDisplayName(), cloakType.getMaterial(), cloakType.getUnlockedLore(), EnumItem.HAS_PERMISSION.isShowInLore() ? EnumItem.HAS_PERMISSION.getLore() : null, GInventory.LAY_OUT_21.getLayOut()[n++], (playerManager.getEquippedCloak() == null) ? null : playerManager.getEquippedCloak().getDisplayName());
                }
            }
            catch (Exception ex) {
                player.sendMessage(MessageType.ERROR.getFormatMessage());
                ex.printStackTrace();
                return;
            }
        }
        if (EnumItem.RESET_CLOAK.show()) {
            InventoryUtils.inventory(inventory, EnumItem.RESET_CLOAK.getItemStack(), EnumItem.RESET_CLOAK.getSlot());
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
    public void onInvClickCloaks(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(Category.CLOAKS.getGUIName())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (!Category.CLOAKS.isEnabled() || !WorldUtils.isWorldEnabled(player, true)) {
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
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.RESET_CLOAK.getItemStack(), EnumItem.RESET_CLOAK.getSlot())) {
            playerManager.unequipCloak();
            player.sendMessage(MessageType.RESET_CLOAK.getFormatMessage());
            if (EnumItem.RESET_CLOAK.isPlaySoundEnabled()) {
                EnumItem.RESET_CLOAK.getSound().playSound(player, 1.0f, 2.0f);
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
        for (final CloakType cloakType : CloakType.values()) {
            if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.format(cloakType.getDisplayName()))) {
                if (PermissionUtils.noPermission(player, cloakType.getPermission(), EnumPermission.CLOAKS.getPermission(), false)) {
                    if (Category.CLOAKS.isPurchasable()) {
                        if (!cloakType.isPurchasable()) {
                            player.sendMessage(MessageType.ITEM_UNPURCHASABLE.getFormatMessage());
                            if (EnumItem.ITEM_UNPURCHASABLE.isPlaySoundEnabled()) {
                                EnumItem.ITEM_UNPURCHASABLE.getSound().playSound(player, 1.0f, 0.5f);
                            }
                            player.closeInventory();
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        final ItemCostDiscount itemCostDiscount = playerManager.getItemCostDiscount();
                        int n = cloakType.getMysteryDust();
                        if (itemCostDiscount != null) {
                            n = itemCostDiscount.getPriceAfterDiscount(cloakType.getMysteryDust());
                        }
                        if (playerManager.getMysteryDust() >= n) {
                            playerManager.purchaseData().setData(Category.CLOAKS, cloakType.getName(), cloakType.getDisplayName(), itemCostDiscount, cloakType.getMysteryDust(), cloakType.getPermission());
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
                        if (PermissionUtils.noPermission(player, cloakType.getPermission(), EnumPermission.CLOAKS.getPermission(), true)) {
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
                        if (playerManager.getEquippedCloak() != null && ChatUtil.format(cloakType.getDisplayName()).equals(playerManager.getEquippedCloak().getDisplayName())) {
                            playerManager.unequipCloak();
                            player.sendMessage(MessageType.RESET_CLOAK.getFormatMessage());
                            if (EnumItem.RESET_CLOAK.isPlaySoundEnabled()) {
                                EnumItem.RESET_CLOAK.getSound().playSound(player, 1.0f, 2.0f);
                            }
                            player.closeInventory();
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        if (!cloakType.checkRequirement(playerManager)) {
                            if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                                EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                            }
                            player.closeInventory();
                            inventoryClickEvent.setCancelled(true);
                            return;
                        }
                        playerManager.equipCloak(cloakType);
                        player.sendMessage(MessageType.SELECT_CLOAK.getFormatMessage().replace("{CLOAK}", cloakType.getDisplayNameStripColor()));
                        if (EnumItem.HAS_PERMISSION.isPlaySoundEnabled()) {
                            EnumItem.HAS_PERMISSION.getSound().playSound(player, 1.0f, 2.0f);
                        }
                        if (EnumItem.HAS_PERMISSION.isCloseGUIMenuAfterSelect()) {
                            player.closeInventory();
                        }
                        else {
                            openCloaksMenu(playerManager);
                        }
                        inventoryClickEvent.setCancelled(true);
                        return;
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
