

package ws.billy.CookieGadgets.listeners.cosmetics;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.Listener;

public class EntityPickupItemListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPickUpItem(final EntityPickupItemEvent entityPickupItemEvent) {
        if (!(entityPickupItemEvent.getEntity() instanceof Player)) {
            return;
        }
        final ItemStack itemStack = entityPickupItemEvent.getItem().getItemStack();
        if (itemStack == null) {
            return;
        }
        if (!itemStack.hasItemMeta()) {
            return;
        }
        if (!itemStack.getItemMeta().hasDisplayName()) {
            return;
        }
        if (!WorldUtils.isWorldEnabled(entityPickupItemEvent.getItem().getWorld())) {
            return;
        }
        if (CookieGadgets.getNMSManager().hasNBTTag(itemStack, "Cosmetics") || CookieGadgets.getNMSManager().hasNBTTag(itemStack, "Menu_Selector")) {
            entityPickupItemEvent.setCancelled(true);
            entityPickupItemEvent.getItem().remove();
        }
        if (entityPickupItemEvent.getItem().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            entityPickupItemEvent.setCancelled(true);
            entityPickupItemEvent.getItem().remove();
        }
    }
}
