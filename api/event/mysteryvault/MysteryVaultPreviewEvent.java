

package ws.billy.CookieGadgets.api.event.mysteryvault;

import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class MysteryVaultPreviewEvent extends Event implements Cancellable
{
    private static final HandlerList handlers;
    private Player player;
    private MysteryVault mysteryVault;
    private boolean cancelled;
    
    static {
        handlers = new HandlerList();
    }
    
    public MysteryVaultPreviewEvent(final Player player, final MysteryVault mysteryVault) {
        this.cancelled = false;
        this.player = player;
        this.mysteryVault = mysteryVault;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public MysteryVault getClickedMysteryVault() {
        return this.mysteryVault;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return MysteryVaultPreviewEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return MysteryVaultPreviewEvent.handlers;
    }
}
