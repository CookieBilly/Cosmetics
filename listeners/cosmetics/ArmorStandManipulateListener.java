

package ws.billy.CookieGadgets.listeners.cosmetics;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.Listener;

public class ArmorStandManipulateListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractWithArmorStand(final PlayerArmorStandManipulateEvent playerArmorStandManipulateEvent) {
        if (playerArmorStandManipulateEvent.getPlayerItem() == null) {
            return;
        }
        if (!playerArmorStandManipulateEvent.getPlayerItem().hasItemMeta()) {
            return;
        }
        if (!playerArmorStandManipulateEvent.getPlayerItem().getItemMeta().hasDisplayName()) {
            return;
        }
        if (!WorldUtils.isWorldEnabled(playerArmorStandManipulateEvent.getPlayer().getWorld())) {
            return;
        }
        final ItemStack playerItem = playerArmorStandManipulateEvent.getPlayerItem();
        if (CookieGadgets.getNMSManager().hasNBTTag(playerItem, "Cosmetics")) {
            playerArmorStandManipulateEvent.setCancelled(true);
            playerArmorStandManipulateEvent.getPlayer().updateInventory();
        }
        if (playerItem != null && playerItem.hasItemMeta() && playerItem.getItemMeta().hasDisplayName() && CookieGadgets.getNMSManager().hasNBTTag(playerItem, "Menu_Selector")) {
            playerArmorStandManipulateEvent.setCancelled(true);
            playerArmorStandManipulateEvent.getPlayer().updateInventory();
        }
    }
}
