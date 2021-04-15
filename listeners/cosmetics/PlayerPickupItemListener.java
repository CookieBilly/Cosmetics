// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.listeners.cosmetics;

import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.Listener;

public class PlayerPickupItemListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPickUpItem(final PlayerPickupItemEvent playerPickupItemEvent) {
        final ItemStack itemStack = playerPickupItemEvent.getItem().getItemStack();
        if (itemStack == null) {
            return;
        }
        if (!itemStack.hasItemMeta()) {
            return;
        }
        if (!itemStack.getItemMeta().hasDisplayName()) {
            return;
        }
        if (!WorldUtils.isWorldEnabled(playerPickupItemEvent.getPlayer().getWorld())) {
            return;
        }
        if (CookieGadgets.getNMSManager().hasNBTTag(itemStack, "Cosmetics") || CookieGadgets.getNMSManager().hasNBTTag(itemStack, "Menu_Selector")) {
            playerPickupItemEvent.setCancelled(true);
            playerPickupItemEvent.getItem().remove();
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPickUp(final PlayerPickupItemEvent playerPickupItemEvent) {
        if (!WorldUtils.isWorldEnabled(playerPickupItemEvent.getPlayer().getWorld())) {
            return;
        }
        if (playerPickupItemEvent.getItem().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            playerPickupItemEvent.setCancelled(true);
            playerPickupItemEvent.getItem().remove();
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHopperPickUpItem(final InventoryPickupItemEvent inventoryPickupItemEvent) {
        final ItemStack itemStack = inventoryPickupItemEvent.getItem().getItemStack();
        if (itemStack == null) {
            return;
        }
        if (!itemStack.hasItemMeta()) {
            return;
        }
        if (!itemStack.getItemMeta().hasDisplayName()) {
            return;
        }
        if (!WorldUtils.isWorldEnabled(inventoryPickupItemEvent.getItem().getWorld())) {
            return;
        }
        if (CookieGadgets.getNMSManager().hasNBTTag(itemStack, "Cosmetics") || CookieGadgets.getNMSManager().hasNBTTag(itemStack, "Menu_Selector")) {
            inventoryPickupItemEvent.setCancelled(true);
            inventoryPickupItemEvent.getItem().remove();
        }
        if (itemStack != null && inventoryPickupItemEvent.getItem().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            inventoryPickupItemEvent.setCancelled(true);
            inventoryPickupItemEvent.getItem().remove();
        }
    }
}
