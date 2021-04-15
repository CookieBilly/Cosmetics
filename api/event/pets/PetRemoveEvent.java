

package ws.billy.CookieGadgets.api.event.pets;

import ws.billy.CookieGadgets.cosmetics.pets.Pet;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class PetRemoveEvent extends Event implements Cancellable
{
    private static final HandlerList handlers;
    private Pet pet;
    private boolean cancelled;
    
    static {
        handlers = new HandlerList();
    }
    
    public PetRemoveEvent(final Pet pet) {
        this.cancelled = false;
        this.pet = pet;
    }
    
    public Pet getPet() {
        return this.pet;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return PetRemoveEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PetRemoveEvent.handlers;
    }
}
