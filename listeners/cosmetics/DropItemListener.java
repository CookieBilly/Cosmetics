

package ws.billy.CookieGadgets.listeners.cosmetics;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Item;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.Listener;

public class DropItemListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDropItem(final PlayerDropItemEvent playerDropItemEvent) {
        if (playerDropItemEvent.getItemDrop().getItemStack() == null) {
            return;
        }
        if (!playerDropItemEvent.getItemDrop().getItemStack().hasItemMeta()) {
            return;
        }
        if (!playerDropItemEvent.getItemDrop().getItemStack().getItemMeta().hasDisplayName()) {
            return;
        }
        if (!WorldUtils.isWorldEnabled(playerDropItemEvent.getPlayer().getWorld())) {
            return;
        }
        final Item itemDrop = playerDropItemEvent.getItemDrop();
        if (CookieGadgets.getNMSManager().hasNBTTag(itemDrop.getItemStack(), "Cosmetics")) {
            itemDrop.remove();
            playerDropItemEvent.setCancelled(true);
            playerDropItemEvent.getPlayer().updateInventory();
        }
        if (itemDrop.getItemStack() != null && itemDrop.getItemStack().hasItemMeta() && itemDrop.getItemStack().getItemMeta().hasDisplayName() && CookieGadgets.getNMSManager().hasNBTTag(itemDrop.getItemStack(), "Menu_Selector")) {
            itemDrop.remove();
            playerDropItemEvent.setCancelled(!CookieGadgets.getCookieGadgetsData().isAbleToMoveMenuSelector());
            playerDropItemEvent.getPlayer().updateInventory();
        }
    }
}
