// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.menu.menus;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.PurchaseData;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.EnumPermission;
import java.sql.Timestamp;
import ws.billy.CookieGadgets.utils.GCommandHandler;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.cosmetics.MainMenuType;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.ClickType;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import ws.billy.CookieGadgets.utils.items.InventoryUtils;
import ws.billy.CookieGadgets.utils.EnumItem;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ItemPurchaseMenu implements Listener
{
    public static void openItemPurchaseMenu(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 27, CookieGadgets.getCookieGadgetsData().getItemPurchaseGUIName());
        InventoryUtils.inventory(inventory, EnumItem.CONFIRM_PURCHASE_ITEM.getItemStack(), EnumItem.CONFIRM_PURCHASE_ITEM.getSlot());
        InventoryUtils.inventory(inventory, EnumItem.CANCEL_PURCHASE_ITEM.getItemStack(), EnumItem.CANCEL_PURCHASE_ITEM.getSlot());
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void InvClickPurchaseMenu(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (!inventoryClickEvent.getView().getTitle().equals(CookieGadgets.getCookieGadgetsData().getItemPurchaseGUIName())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 27 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
            inventoryClickEvent.setCancelled(true);
            return;
        }
        if (!CookieGadgets.getCookieGadgetsData().isCosmeticItemPurchasable() || !WorldUtils.isWorldEnabled(player, true)) {
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
        if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.CONFIRM_PURCHASE_ITEM.getItemStack(), EnumItem.CONFIRM_PURCHASE_ITEM.getSlot())) {
            final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
            final PurchaseData purchaseData = playerManager.purchaseData();
            if (purchaseData == null || purchaseData.getPrice() == -1 || purchaseData.getPermission() == null) {
                player.sendMessage(MessageType.FAILED_TO_PURCHASE.getFormatMessage());
                if (EnumItem.FAILED_TO_PURCHASE.isPlaySoundEnabled()) {
                    EnumItem.FAILED_TO_PURCHASE.getSound().playSound(player, 1.0f, 0.5f);
                }
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (!PermissionUtils.noPermission(player, purchaseData.getPermission(), false)) {
                player.sendMessage(MessageType.FAILED_TO_PURCHASE.getFormatMessage());
                if (CookieGadgets.getCookieGadgetsData().isReopenGUIMenuAfterPurchase()) {
                    if (MainMenuType.valueOf(purchaseData.getCategory().getName()).isEnabled()) {
                        purchaseData.getCategory().openMenu(playerManager, playerManager.getCurrentMenuPage());
                    }
                }
                else {
                    player.closeInventory();
                }
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (playerManager.getMysteryDust() < purchaseData.getPrice()) {
                player.sendMessage(MessageType.NOT_ENOUGH_MYSTERY_DUST_TO_PURCHASE.getFormatMessage());
                if (EnumItem.NOT_ENOUGH_MYSTERY_DUST.isPlaySoundEnabled()) {
                    EnumItem.NOT_ENOUGH_MYSTERY_DUST.getSound().playSound(player, 1.0f, 0.5f);
                }
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (purchaseData.getCategory() == null) {
                MessageType.FAILED_TO_PURCHASE.getFormatMessage();
                player.closeInventory();
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (!playerManager.removeMysteryDust(purchaseData.getPrice())) {
                player.sendMessage(MessageType.FAILED_TO_DEDUCT_MYSTERY_DUST.getFormatMessage());
                if (EnumItem.FAILED_TO_DEDUCT_MYSTERY_DUST.isPlaySoundEnabled()) {
                    EnumItem.FAILED_TO_DEDUCT_MYSTERY_DUST.getSound().playSound(player, 1.0f, 0.5f);
                }
                player.closeInventory();
                return;
            }
            player.sendMessage(ChatUtil.format(purchaseData.getCategory().getPurchaseItemMessage().getFormatMessage().replace("{ITEM}", purchaseData.getDisplayName())));
            if (CookieGadgets.getCookieGadgetsData().isCosmeticItemPurchaseCommandEnabled()) {
                GCommandHandler.executeCommand(CookieGadgets.getCookieGadgetsData().getCosmeticItemPurchaseCommand().replace("{PLAYER}", player.getName()).replace("{PERMISSION}", purchaseData.getPermission()));
            }
            else {
                playerManager.addUnlockedCosmeticItem(purchaseData.getCategory(), purchaseData.getName(), null);
            }
            if (CookieGadgets.getCookieGadgetsData().isAutoEquipAfterPurchaseEnabled() || !PermissionUtils.noPermission(player, EnumPermission.AUTO_EQUIP_AFTER_PURCHASE.getPermission(), false)) {
                purchaseData.equip(playerManager);
            }
            if (EnumItem.ENOUGH_MYSTERY_DUST.isPlaySoundEnabled()) {
                EnumItem.ENOUGH_MYSTERY_DUST.getSound().playSound(player, 1.0f, 2.0f);
            }
            if (CookieGadgets.getCookieGadgetsData().isReopenGUIMenuAfterPurchase()) {
                if (MainMenuType.valueOf(purchaseData.getCategory().getName()).isEnabled()) {
                    purchaseData.getCategory().openMenu(playerManager, playerManager.getCurrentMenuPage());
                }
            }
            else {
                player.closeInventory();
            }
            playerManager.resetPurchaseData();
            inventoryClickEvent.setCancelled(true);
        }
        else {
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.CANCEL_PURCHASE_ITEM.getItemStack(), EnumItem.CANCEL_PURCHASE_ITEM.getSlot())) {
                CookieGadgets.getPlayerManager(player).resetPurchaseData();
                SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(player, 1.0f, 0.5f);
                player.sendMessage(MessageType.PURCHASE_CANCELLED.getFormatMessage());
                inventoryClickEvent.setCancelled(true);
                player.closeInventory();
                return;
            }
            inventoryClickEvent.setCancelled(true);
        }
    }
}
