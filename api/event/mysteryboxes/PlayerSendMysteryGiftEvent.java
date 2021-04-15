

package ws.billy.CookieGadgets.api.event.mysteryboxes;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class PlayerSendMysteryGiftEvent extends Event
{
    private static final HandlerList handlers;
    private Player sender;
    private Player receiver;
    
    static {
        handlers = new HandlerList();
    }
    
    public PlayerSendMysteryGiftEvent(final Player sender, final Player receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }
    
    public Player getSender() {
        return this.sender;
    }
    
    public Player getReceiver() {
        return this.receiver;
    }
    
    public HandlerList getHandlers() {
        return PlayerSendMysteryGiftEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerSendMysteryGiftEvent.handlers;
    }
}
