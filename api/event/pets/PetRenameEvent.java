

package ws.billy.CookieGadgets.api.event.pets;

import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.GPet;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class PetRenameEvent extends Event implements Cancellable
{
    private static final HandlerList handlers;
    private Player owner;
    private GPet pet;
    private String petName;
    private boolean cancelled;
    
    static {
        handlers = new HandlerList();
    }
    
    public PetRenameEvent(final Player owner, final GPet gPet, final String petName) {
        this.cancelled = false;
        this.owner = owner;
        this.petName = petName;
    }
    
    public Player getOwner() {
        return this.owner;
    }
    
    public GPet getPet() {
        return this.pet;
    }
    
    public String getPetName() {
        return this.petName;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return PetRenameEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PetRenameEvent.handlers;
    }
}
