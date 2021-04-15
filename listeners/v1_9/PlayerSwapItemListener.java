

package ws.billy.CookieGadgets.listeners.v1_9;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.Listener;

public class PlayerSwapItemListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerSwapoffHand(final PlayerSwapHandItemsEvent playerSwapHandItemsEvent) {
        if (playerSwapHandItemsEvent.getMainHandItem() != null && playerSwapHandItemsEvent.getMainHandItem().hasItemMeta() && playerSwapHandItemsEvent.getMainHandItem().getItemMeta().hasDisplayName() && playerSwapHandItemsEvent.getMainHandItem().getItemMeta().getDisplayName().equals(CookieGadgets.getCookieGadgetsData().getMenuSelector().getItemMeta().getDisplayName())) {
            playerSwapHandItemsEvent.setCancelled(true);
        }
        if (playerSwapHandItemsEvent.getOffHandItem() != null && playerSwapHandItemsEvent.getOffHandItem().hasItemMeta() && playerSwapHandItemsEvent.getOffHandItem().getItemMeta().hasDisplayName() && playerSwapHandItemsEvent.getOffHandItem().getItemMeta().getDisplayName().equals(CookieGadgets.getCookieGadgetsData().getMenuSelector().getItemMeta().getDisplayName())) {
            playerSwapHandItemsEvent.setCancelled(true);
        }
        if (playerSwapHandItemsEvent.getMainHandItem() != null && playerSwapHandItemsEvent.getMainHandItem().hasItemMeta() && playerSwapHandItemsEvent.getMainHandItem().getItemMeta().hasDisplayName() && CookieGadgets.getNMSManager().hasNBTTag(playerSwapHandItemsEvent.getMainHandItem(), "Cosmetics")) {
            playerSwapHandItemsEvent.setCancelled(true);
        }
        if (playerSwapHandItemsEvent.getOffHandItem() != null && playerSwapHandItemsEvent.getOffHandItem().hasItemMeta() && playerSwapHandItemsEvent.getOffHandItem().getItemMeta().hasDisplayName() && CookieGadgets.getNMSManager().hasNBTTag(playerSwapHandItemsEvent.getOffHandItem(), "Cosmetics")) {
            playerSwapHandItemsEvent.setCancelled(true);
        }
    }
}
