

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.Listener;

public class MorphBat implements Listener
{
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerToggleFlight(final PlayerToggleFlightEvent playerToggleFlightEvent) {
    }
}
