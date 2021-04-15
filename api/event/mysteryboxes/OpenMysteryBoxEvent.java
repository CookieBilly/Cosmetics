

package ws.billy.CookieGadgets.api.event.mysteryboxes;

import ws.billy.CookieGadgets.mysteryboxes.MysteryBoxes;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class OpenMysteryBoxEvent extends Event implements Cancellable
{
    private static final HandlerList handlers;
    private Player player;
    private MysteryBoxes mysteryBox;
    private boolean cancelled;
    
    static {
        handlers = new HandlerList();
    }
    
    public OpenMysteryBoxEvent(final Player player, final MysteryBoxes mysteryBox) {
        this.cancelled = false;
        this.player = player;
        this.mysteryBox = mysteryBox;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public MysteryBoxes getSelectedMysteryBox() {
        return this.mysteryBox;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return OpenMysteryBoxEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return OpenMysteryBoxEvent.handlers;
    }
}
