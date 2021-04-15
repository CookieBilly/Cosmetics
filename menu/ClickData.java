

package ws.billy.CookieGadgets.menu;

import org.bukkit.inventory.ItemStack;
import org.bukkit.event.inventory.InventoryAction;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.inventory.Inventory;

public class ClickData
{
    private Inventory inventory;
    private PlayerManager pManager;
    private InventoryAction action;
    private ItemStack itemClicked;
    private int slot;
    
    public ClickData(final Inventory inventory, final PlayerManager pManager, final InventoryAction action, final ItemStack itemClicked, final int slot) {
        this.inventory = inventory;
        this.pManager = pManager;
        this.action = action;
        this.itemClicked = itemClicked;
        this.slot = slot;
    }
    
    public Inventory getInventory() {
        return this.inventory;
    }
    
    public PlayerManager getGPlayer() {
        return this.pManager;
    }
    
    public ItemStack getItemClicked() {
        return this.itemClicked;
    }
    
    public InventoryAction getAction() {
        return this.action;
    }
    
    public int getSlot() {
        return this.slot;
    }
}
