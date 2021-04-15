

package ws.billy.CookieGadgets.listeners;

import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.Listener;

public class GadgetListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockDamage(final EntityDamageEvent entityDamageEvent) {
        if (!(entityDamageEvent.getEntity() instanceof Player)) {
            return;
        }
        if (entityDamageEvent.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION && CookieGadgets.getPlayerManager((Player)entityDamageEvent.getEntity()).isBlockDamageDisabled()) {
            entityDamageEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(final EntityDamageEvent entityDamageEvent) {
        if (!(entityDamageEvent.getEntity() instanceof Player)) {
            return;
        }
        if (entityDamageEvent.getCause() == EntityDamageEvent.DamageCause.FALL) {
            final PlayerManager playerManager = CookieGadgets.getPlayerManager((Player)entityDamageEvent.getEntity());
            if (playerManager.isFallDamageDisabled()) {
                playerManager.enableFallDamage();
                entityDamageEvent.setCancelled(true);
            }
            if (playerManager.getCurrentPet() != null && playerManager.getCurrentPet().isOwnerRiding()) {
                entityDamageEvent.setCancelled(true);
            }
        }
    }
}
