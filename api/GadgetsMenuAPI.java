

package ws.billy.CookieGadgets.api;

import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import java.util.UUID;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.entity.Player;


public class CookieGadgetsAPI
{
    public static PlayerManager getPlayerManager(final Player player) {
        return CookieGadgets.getPlayerManager(player);
    }
    
    public static OfflinePlayerManager getOfflinePlayerManager(final UUID uuid) {
        return new OfflinePlayerManager(uuid);
    }
    
    public static void goBackToMainMenu(final Player player) {
        CookieGadgets.getPlayerManager(player).goBackToMainMenu();
    }
}
