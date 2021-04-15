

package ws.billy.CookieGadgets.listeners.cosmetics;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;

public class SelectItemListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerClickItem(final InventoryClickEvent inventoryClickEvent) {
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        ItemStack item = null;
        ItemStack item2;
        if (inventoryClickEvent.getAction().name().contains("HOTBAR")) {
            try {
                item = inventoryClickEvent.getView().getBottomInventory().getItem(inventoryClickEvent.getHotbarButton());
            }
            catch (ArrayIndexOutOfBoundsException ex) {}
            if (item == null) {
                item = inventoryClickEvent.getCurrentItem();
            }
            item2 = inventoryClickEvent.getCurrentItem();
        }
        else {
            item = inventoryClickEvent.getCurrentItem();
            item2 = inventoryClickEvent.getCurrentItem();
        }
        if (item == null && item2 == null) {
            return;
        }
        if (item != null && !item.hasItemMeta() && item2 != null && !item2.hasItemMeta()) {
            return;
        }
        if (item != null && (!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) && item2 != null && (!item2.hasItemMeta() || !item2.getItemMeta().hasDisplayName())) {
            return;
        }
        if (!WorldUtils.isWorldEnabled(player.getWorld())) {
            return;
        }
        if (inventoryClickEvent.getClickedInventory() == null) {
            return;
        }
        if (inventoryClickEvent.getView().getTitle().startsWith(Category.HATS.getGUIName()) || inventoryClickEvent.getView().getTitle().startsWith(Category.ANIMATED_HATS.getGUIName()) || inventoryClickEvent.getView().getTitle().startsWith(Category.PARTICLES.getGUIName()) || inventoryClickEvent.getView().getTitle().equals(Category.GADGETS.getGUIName()) || inventoryClickEvent.getView().getTitle().equals(Category.PETS.getGUIName()) || inventoryClickEvent.getView().getTitle().startsWith(Category.MINIATURES.getGUIName()) || inventoryClickEvent.getView().getTitle().equals(Category.MORPHS.getGUIName()) || inventoryClickEvent.getView().getTitle().startsWith(Category.BANNERS.getGUIName()) || inventoryClickEvent.getView().getTitle().startsWith(Category.EMOTES.getGUIName()) || inventoryClickEvent.getView().getTitle().equals(Category.CLOAKS.getGUIName())) {
            return;
        }
        if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && CookieGadgets.getNMSManager().hasNBTTag(item, "Cosmetics")) {
            inventoryClickEvent.setCancelled(true);
            player.updateInventory();
        }
        if (item2 != null && item2.hasItemMeta() && item2.getItemMeta().hasDisplayName() && CookieGadgets.getNMSManager().hasNBTTag(item2, "Cosmetics")) {
            inventoryClickEvent.setCancelled(true);
            player.updateInventory();
        }
        if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && !CookieGadgets.getCookieGadgetsData().isAbleToMoveMenuSelector() && CookieGadgets.getNMSManager().hasNBTTag(item, "Menu_Selector")) {
            inventoryClickEvent.setCancelled(true);
            player.updateInventory();
        }
        if (item2 != null && item2.hasItemMeta() && item2.getItemMeta().hasDisplayName() && !CookieGadgets.getCookieGadgetsData().isAbleToMoveMenuSelector() && CookieGadgets.getNMSManager().hasNBTTag(item, "Menu_Selector")) {
            inventoryClickEvent.setCancelled(true);
            player.updateInventory();
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMoveItemInCreativeMode(final InventoryClickEvent inventoryClickEvent) {
        if (inventoryClickEvent.getClick() != ClickType.CREATIVE) {
            return;
        }
        final Player player = (Player)inventoryClickEvent.getWhoClicked();
        final ItemStack cursor = inventoryClickEvent.getCursor();
        if (cursor == null || !cursor.hasItemMeta() || !cursor.getItemMeta().hasDisplayName()) {
            return;
        }
        if (!WorldUtils.isWorldEnabled(player.getWorld())) {
            return;
        }
        if (cursor != null && cursor.hasItemMeta() && cursor.getItemMeta().hasDisplayName() && CookieGadgets.getNMSManager().hasNBTTag(cursor, "Cosmetics")) {
            inventoryClickEvent.setCancelled(true);
            player.updateInventory();
        }
        if (cursor != null && cursor.hasItemMeta() && cursor.getItemMeta().hasDisplayName() && CookieGadgets.getNMSManager().hasNBTTag(cursor, "Menu_Selector")) {
            if (CookieGadgets.getCookieGadgetsData().isAbleToMoveMenuSelector()) {
                inventoryClickEvent.setCancelled(true);
                CookieGadgets.getPlayerManager(player).giveMenuSelector();
                player.updateInventory();
                return;
            }
            inventoryClickEvent.setCancelled(true);
            player.updateInventory();
        }
    }
}
