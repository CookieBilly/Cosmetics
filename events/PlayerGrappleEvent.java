// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class PlayerGrappleEvent extends Event implements Cancellable
{
    private static final HandlerList handlers;
    private Player player;
    private Location pullLocation;
    private boolean cancelled;
    
    static {
        handlers = new HandlerList();
    }
    
    public PlayerGrappleEvent(final Player player, final Location pullLocation) {
        this.cancelled = false;
        this.player = player;
        this.pullLocation = pullLocation;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Location getPullLocation() {
        return this.pullLocation;
    }
    
    public HandlerList getHandlers() {
        return PlayerGrappleEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerGrappleEvent.handlers;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}
