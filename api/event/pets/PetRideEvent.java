

package ws.billy.CookieGadgets.api.event.pets;

import ws.billy.CookieGadgets.cosmetics.pets.Pet;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class PetRideEvent extends Event implements Cancellable
{
    private static final HandlerList handlers;
    private Pet pet;
    private Type type;
    private boolean cancelled;
    
    static {
        handlers = new HandlerList();
    }
    
    public PetRideEvent(final Pet pet, final Type type) {
        this.cancelled = false;
        this.pet = pet;
        this.type = type;
    }
    
    public Pet getPet() {
        return this.pet;
    }
    
    public Type getEventType() {
        return this.type;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return PetRideEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PetRideEvent.handlers;
    }
    
    public enum Type
    {
        MOUNT("MOUNT", 0), 
        DISMOUNT("DISMOUNT", 1);
        
        private Type(final String name, final int ordinal) {
        }
    }
}
