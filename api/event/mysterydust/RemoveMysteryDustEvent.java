

package ws.billy.CookieGadgets.api.event.mysterydust;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class RemoveMysteryDustEvent extends Event implements Cancellable
{
    private static final HandlerList handlers;
    private Player player;
    private int amount;
    private boolean cancelled;
    
    static {
        handlers = new HandlerList();
    }
    
    public RemoveMysteryDustEvent(final Player player, final int amount) {
        this.cancelled = false;
        this.player = player;
        this.amount = amount;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return RemoveMysteryDustEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return RemoveMysteryDustEvent.handlers;
    }
}
