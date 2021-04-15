

package ws.billy.CookieGadgets.menu.menus;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.api.CookieGadgetsAPI;
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

public class SettingsMenu implements Listener
{
    public static void openSettingsMenu(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, CookieGadgets.getCookieGadgetsData().getSettingGUIName());
        if (EnumItem.SETTINGS_IGNORE_COOLDOWN.show()) {
            InventoryUtils.inventory(inventory, EnumItem.SETTINGS_IGNORE_COOLDOWN.getItemStack(), EnumItem.SETTINGS_IGNORE_COOLDOWN.getSlot());
            InventoryUtils.inventory(inventory, CookieGadgets.getPlayerManager(player).isBypassCooldown() ? EnumItem.SETTINGS_ENABLED.getItemStack() : EnumItem.SETTINGS_DISABLED.getItemStack(), InventoryUtils.findSlotBelowItem(EnumItem.SETTINGS_IGNORE_COOLDOWN.getSlot(), 54));
        }
        if (EnumItem.SETTINGS_SELF_MORPH_VIEW.show()) {
            InventoryUtils.inventory(inventory, EnumItem.SETTINGS_SELF_MORPH_VIEW.getItemStack(), EnumItem.SETTINGS_SELF_MORPH_VIEW.getSlot());
            InventoryUtils.inventory(inventory, CookieGadgets.getPlayerManager(player).canSeeSelfMorph() ? EnumItem.SETTINGS_ENABLED.getItemStack() : EnumItem.SETTINGS_DISABLED.getItemStack(), InventoryUtils.findSlotBelowItem(EnumItem.SETTINGS_SELF_MORPH_VIEW.getSlot(), 54));
        }
        if (EnumItem.GO_BACK.show()) {
            InventoryUtils.inventory(inventory, EnumItem.GO_BACK.getItemStack(), 45);
        }
        if (EnumItem.MAIN_MENU_ITEM.show()) {
            InventoryUtils.mainMenuButton(player, inventory, EnumItem.MAIN_MENU_ITEM.getSlot());
        }
        InventoryUtils.fillItems(inventory);
        player.openInventory(inventory);
    }
    
    @EventHandler
    public void onInvClickSettingsMenu(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (inventoryClickEvent.getView().getTitle().equals(CookieGadgets.getCookieGadgetsData().getSettingGUIName())) {
            if (inventoryClickEvent.getClickedInventory() == null || inventoryClickEvent.getClickedInventory().getSize() != 54 || inventoryClickEvent.getClickedInventory().getType() != InventoryType.CHEST) {
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (!WorldUtils.isWorldEnabled(player, true)) {
                inventoryClickEvent.setCancelled(true);
                player.closeInventory();
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
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.GO_BACK.getItemStack(), 45)) {
                CookieGadgetsAPI.goBackToMainMenu(player);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.MAIN_MENU_ITEM.getDisplayName(), EnumItem.MAIN_MENU_ITEM.getSlot())) {
                CookieGadgetsAPI.goBackToMainMenu(player);
                inventoryClickEvent.setCancelled(true);
                return;
            }
            final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
            if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.SETTINGS_ENABLED.getItemStack(), InventoryUtils.findSlotBelowItem(EnumItem.SETTINGS_IGNORE_COOLDOWN.getSlot(), 54))) {
                if (!PermissionUtils.noPermission(player, EnumPermission.BYPASS_COOLDOWN.getPermission(), true)) {
                    playerManager.setBypassCooldown(false);
                    openSettingsMenu(player);
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                }
                if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                    player.closeInventory();
                }
                inventoryClickEvent.setCancelled(true);
            }
            else if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.SETTINGS_DISABLED.getItemStack(), InventoryUtils.findSlotBelowItem(EnumItem.SETTINGS_IGNORE_COOLDOWN.getSlot(), 54))) {
                if (!PermissionUtils.noPermission(player, EnumPermission.BYPASS_COOLDOWN.getPermission(), true)) {
                    playerManager.setBypassCooldown(true);
                    openSettingsMenu(player);
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                if (EnumItem.NO_PERMISSION.isPlaySoundEnabled()) {
                    EnumItem.NO_PERMISSION.getSound().playSound(player, 1.0f, 0.5f);
                }
                if (EnumItem.NO_PERMISSION.isCloseGUIMenuAfterSelect()) {
                    player.closeInventory();
                }
                inventoryClickEvent.setCancelled(true);
            }
            else if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.SETTINGS_ENABLED.getItemStack(), InventoryUtils.findSlotBelowItem(EnumItem.SETTINGS_SELF_MORPH_VIEW.getSlot(), 54))) {
                if (!CookieGadgets.getCookieGadgetsData().isSelfMorphViewEnabled()) {
                    player.sendMessage(MessageType.DISABLED_SELF_MORPH_VIEW.getFormatMessage());
                    player.closeInventory();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                playerManager.setSeeSelfMorph(false);
                openSettingsMenu(player);
                inventoryClickEvent.setCancelled(true);
            }
            else if (ItemUtils.getCurrentItem(inventoryClickEvent, EnumItem.SETTINGS_DISABLED.getItemStack(), InventoryUtils.findSlotBelowItem(EnumItem.SETTINGS_SELF_MORPH_VIEW.getSlot(), 54))) {
                if (!CookieGadgets.getCookieGadgetsData().isSelfMorphViewEnabled()) {
                    player.sendMessage(MessageType.DISABLED_SELF_MORPH_VIEW.getFormatMessage());
                    player.closeInventory();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                playerManager.setSeeSelfMorph(true);
                openSettingsMenu(player);
                inventoryClickEvent.setCancelled(true);
            }
            else {
                inventoryClickEvent.setCancelled(true);
            }
        }
    }
}
