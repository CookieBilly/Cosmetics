

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.HandlerList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;
import org.bukkit.event.Listener;

public abstract class Cloak implements Listener
{
    private boolean moving;
    private UUID uuid;
    private CloakType type;
    private Listener listener;
    protected boolean showCloakEffectToEveryone;
    protected boolean hideCloakEffectForVanishedPlayer;
    
    public Cloak(final UUID uuid, final CloakType type) {
        this.moving = false;
        this.showCloakEffectToEveryone = CookieGadgets.getCookieGadgetsData().showCloakEffectToEveryone();
        this.hideCloakEffectForVanishedPlayer = CookieGadgets.getCookieGadgetsData().hideCloakEffectForVanishedPlayer();
        this.type = type;
        if (!type.isEnabled()) {
            return;
        }
        if (uuid != null) {
            this.uuid = uuid;
            if (PermissionUtils.noPermission(this.getPlayer(), type.getPermission(), EnumPermission.CLOAKS.getPermission(), true)) {
                CookieGadgets.getPlayerManager(this.getPlayer()).unequipCloak();
                return;
            }
            if (CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentCloak() != null) {
                CookieGadgets.getPlayerManager(this.getPlayer()).removeCloak();
            }
            new BukkitRunnable() {
                public void run() {
                    try {
                        if (Cloak.this.getPlayer() != null && CookieGadgets.getPlayerManager(Cloak.this.getPlayer()).getCurrentCloak() != null && CookieGadgets.getPlayerManager(Cloak.this.getPlayer()).getCurrentCloak().getType() == type) {
                            if (Cloak.this.isMoving()) {
                                Cloak.access$1(Cloak.this, false);
                            }
                            else {
                                Cloak.this.onUpdate();
                            }
                        }
                        else {
                            this.cancel();
                        }
                    }
                    catch (NullPointerException ex) {
                        ex.printStackTrace();
                        Cloak.this.clear();
                        this.cancel();
                    }
                }
            }.runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), 0L, type.getRepeatDelay());
            this.listener = (Listener)new CloakListener(this);
            CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentCloak(this);
        }
    }
    
    protected UUID getPlayerUUID() {
        return this.uuid;
    }
    
    public CloakType getType() {
        return this.type;
    }
    
    public abstract void onUpdate();
    
    protected Player getPlayer() {
        return Bukkit.getPlayer(this.getPlayerUUID());
    }
    
    protected boolean isMoving() {
        return this.moving;
    }
    
    public void clear() {
        if (this.getPlayer() != null && CookieGadgets.getPlayerManager(this.getPlayer()) != null) {
            CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentCloak(null);
        }
        try {
            HandlerList.unregisterAll((Listener)this);
            HandlerList.unregisterAll(this.listener);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.moving = false;
        this.uuid = null;
    }
    
    static /* synthetic */ void access$1(final Cloak cloak, final boolean moving) {
        cloak.moving = moving;
    }
    
    public class CloakListener implements Listener
    {
        private Cloak cloak;
        
        public CloakListener(final Cloak cloak) {
            this.cloak = cloak;
            CookieGadgets.getInstance().registerListener((Listener)this);
        }
        
        @EventHandler(priority = EventPriority.LOW)
        public void onPlayerMove(final PlayerMoveEvent playerMoveEvent) {
            if (playerMoveEvent.getPlayer() != this.cloak.getPlayer()) {
                return;
            }
            if (playerMoveEvent.getPlayer() == this.cloak.getPlayer() && !this.cloak.moving && (playerMoveEvent.getFrom().getX() != playerMoveEvent.getTo().getX() || playerMoveEvent.getFrom().getY() != playerMoveEvent.getTo().getY() || playerMoveEvent.getFrom().getZ() != playerMoveEvent.getTo().getZ())) {
                Cloak.access$1(this.cloak, true);
            }
        }
    }
}
