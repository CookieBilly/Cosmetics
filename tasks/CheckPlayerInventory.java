

package ws.billy.CookieGadgets.tasks;

import java.util.Iterator;
import ws.billy.CookieGadgets.cosmetics.CategoryManager;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.CookieGadgets;

public class CheckPlayerInventory implements Runnable
{
    @Override
    public void run() {
        for (final PlayerManager playerManager : CookieGadgets.getGPlayer().getGPlayers()) {
            if (playerManager.getPlayer() == null) {
                continue;
            }
            CategoryManager.removeItemFromImproperSlot(playerManager);
        }
    }
}
