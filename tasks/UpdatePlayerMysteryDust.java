

package ws.billy.CookieGadgets.tasks;

import java.util.Iterator;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.CookieGadgets;

public class UpdatePlayerMysteryDust implements Runnable
{
    @Override
    public void run() {
        for (final PlayerManager playerManager : CookieGadgets.getGPlayer().getGPlayers()) {
            if (playerManager.getPlayer() == null) {
                continue;
            }
            playerManager.updateMysteryDust();
        }
    }
}
