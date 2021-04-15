

package ws.billy.CookieGadgets.listeners.mysteryvault;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVaultManager;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.Listener;

public class MysteryVaultBreakListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBreakMysteryVault(final BlockBreakEvent blockBreakEvent) {
        if (MysteryVaultManager.vaults().size() == 0) {
            return;
        }
        final Iterator<MysteryVault> iterator = MysteryVaultManager.vaults().iterator();
        while (iterator.hasNext()) {
            if (blockBreakEvent.getBlock().getLocation().equals((Object)iterator.next().getLocation())) {
                blockBreakEvent.setCancelled(true);
            }
        }
    }
}
