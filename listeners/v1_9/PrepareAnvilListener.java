

package ws.billy.CookieGadgets.listeners.v1_9;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.Listener;

public class PrepareAnvilListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPrepareAnvil(final PrepareAnvilEvent prepareAnvilEvent) {
        final ItemStack item = prepareAnvilEvent.getInventory().getContents()[0];
        if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(CookieGadgets.getCookieGadgetsData().getMenuSelector().getItemMeta().getDisplayName())) {
            prepareAnvilEvent.setResult((ItemStack)null);
            prepareAnvilEvent.getInventory().clear(0);
        }
        if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && CookieGadgets.getNMSManager().hasNBTTag(item, "Cosmetics")) {
            prepareAnvilEvent.setResult((ItemStack)null);
            prepareAnvilEvent.getInventory().clear(0);
        }
    }
}
