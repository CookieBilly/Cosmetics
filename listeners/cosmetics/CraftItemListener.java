

package ws.billy.CookieGadgets.listeners.cosmetics;

import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.Listener;

public class CraftItemListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPrepareItemCraft(final PrepareItemCraftEvent prepareItemCraftEvent) {
        final ItemStack[] matrix = prepareItemCraftEvent.getInventory().getMatrix();
        for (int i = 0; i <= matrix.length - 1; ++i) {
            if (CookieGadgets.getNMSManager().hasNBTTag(matrix[i], "Cosmetics")) {
                prepareItemCraftEvent.getInventory().setResult((ItemStack)null);
            }
            if (CookieGadgets.getNMSManager().hasNBTTag(matrix[i], "Menu_Selector")) {
                prepareItemCraftEvent.getInventory().setResult((ItemStack)null);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCraftItem(final CraftItemEvent craftItemEvent) {
        final ItemStack[] matrix = craftItemEvent.getInventory().getMatrix();
        for (int i = 0; i <= matrix.length - 1; ++i) {
            if (CookieGadgets.getNMSManager().hasNBTTag(matrix[i], "Cosmetics")) {
                craftItemEvent.getInventory().setResult((ItemStack)null);
            }
            if (CookieGadgets.getNMSManager().hasNBTTag(matrix[i], "Menu_Selector")) {
                craftItemEvent.getInventory().setResult((ItemStack)null);
            }
        }
    }
}
