

package ws.billy.CookieGadgets.listeners;

import org.bukkit.inventory.ItemStack;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryCloseEvent;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.menu.menus.MainMenu;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.utils.WorldUtils;
import ws.billy.CookieGadgets.utils.EnumClickType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class OpenMenuListener implements Listener
{
    @EventHandler
    public void onPlayerClickMenuSelector(final PlayerInteractEvent playerInteractEvent) {
        final Player player = playerInteractEvent.getPlayer();
        if (playerInteractEvent.getAction() != Action.LEFT_CLICK_AIR && playerInteractEvent.getAction() != Action.LEFT_CLICK_BLOCK && playerInteractEvent.getAction() != Action.RIGHT_CLICK_AIR && playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (player.getOpenInventory() == null || (player.getOpenInventory().getTitle() != null && !player.getOpenInventory().getTitle().toLowerCase().contains("crafting"))) {
            return;
        }
        if (CookieGadgets.getNMSManager().hasNBTTag(player.getItemInHand(), "Menu_Selector")) {
            if (CookieGadgets.getCookieGadgetsData().getMenuSelectorClickType() == EnumClickType.LEFT && playerInteractEvent.getAction() != Action.LEFT_CLICK_AIR && playerInteractEvent.getAction() != Action.LEFT_CLICK_BLOCK) {
                player.updateInventory();
                playerInteractEvent.setCancelled(true);
                return;
            }
            if (CookieGadgets.getCookieGadgetsData().getMenuSelectorClickType() == EnumClickType.RIGHT && playerInteractEvent.getAction() != Action.RIGHT_CLICK_AIR && playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK) {
                player.updateInventory();
                playerInteractEvent.setCancelled(true);
                return;
            }
            if (!WorldUtils.isWorldEnabled(player, true)) {
                CookieGadgets.getPlayerManager(player).removeMenuSelector();
                player.updateInventory();
                playerInteractEvent.setCancelled(true);
                return;
            }
            if (!player.hasPermission(EnumPermission.MENU_SELECTOR.getPermission())) {
                CookieGadgets.getPlayerManager(player).removeMenuSelector();
                playerInteractEvent.setCancelled(true);
                return;
            }
            MainMenu.openMainMenu(player);
            player.updateInventory();
            playerInteractEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onInvClickMenu(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        if (inventoryClickEvent.getInventory() != null) {
            if (inventoryClickEvent.getCurrentItem() == null || inventoryClickEvent.getCurrentItem().getItemMeta() == null || inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName() == null) {
                return;
            }
            if (!WorldUtils.isWorldEnabled(player.getWorld())) {
                return;
            }
            if (ItemUtils.getCurrentItem(inventoryClickEvent, CookieGadgets.getCookieGadgetsData().getMenuSelector()) && !CookieGadgets.getCookieGadgetsData().isAbleToMoveMenuSelector()) {
                if (!player.hasPermission(EnumPermission.MENU_SELECTOR.getPermission())) {
                    CookieGadgets.getPlayerManager(player).removeMenuSelector();
                    inventoryClickEvent.setCancelled(true);
                    return;
                }
                MainMenu.openMainMenu(player);
                inventoryClickEvent.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onPlayerCloseInventory(final InventoryCloseEvent inventoryCloseEvent) {
        if (!CookieGadgets.getCookieGadgetsData().isAbleToMoveMenuSelector()) {
            return;
        }
        if (!WorldUtils.isWorldEnabled(((Player)inventoryCloseEvent.getPlayer()).getWorld())) {
            return;
        }
        if (inventoryCloseEvent.getInventory().getType() == InventoryType.PLAYER || inventoryCloseEvent.getInventory().getType() == InventoryType.CREATIVE) {
            return;
        }
        ItemStack[] contents;
        for (int length = (contents = inventoryCloseEvent.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = contents[i];
            if (itemStack != null && itemStack.hasItemMeta()) {
                if (itemStack.getItemMeta().hasDisplayName()) {
                    if (CookieGadgets.getNMSManager().hasNBTTag(itemStack, "Cosmetics") || CookieGadgets.getNMSManager().hasNBTTag(itemStack, "Menu_Selector")) {
                        inventoryCloseEvent.getInventory().remove(itemStack);
                    }
                }
            }
        }
    }
}
