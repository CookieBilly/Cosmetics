

package ws.billy.CookieGadgets.api.event.pets;

import org.bukkit.Location;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class PetMoveEvent extends Event
{
    private static final HandlerList handlers;
    private IEntityPet entity;
    private Location targetLocation;
    private Cause cause;
    
    static {
        handlers = new HandlerList();
    }
    
    public PetMoveEvent(final IEntityPet entity, final Cause cause) {
        this.entity = entity;
        if (cause == Cause.RIDE) {
            this.targetLocation = entity.getEntity().getLocation();
        }
        else {
            this.targetLocation = entity.getPet().getOwner().getLocation();
        }
    }
    
    public PetMoveEvent(final IEntityPet entity, final Cause cause, final boolean b) {
        super(b);
        this.entity = entity;
        this.cause = cause;
        if (cause == Cause.RIDE) {
            this.targetLocation = entity.getEntity().getLocation();
        }
        else {
            this.targetLocation = entity.getPet().getOwner().getLocation();
        }
    }
    
    public IEntityPet getEntity() {
        return this.entity;
    }
    
    public Location getTargetLocation() {
        return this.targetLocation;
    }
    
    public Cause getCause() {
        return this.cause;
    }
    
    public HandlerList getHandlers() {
        return PetMoveEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PetMoveEvent.handlers;
    }
    
    public enum Cause
    {
        RIDE("RIDE", 0), 
        WALK("WALK", 1);
        
        private Cause(final String name, final int ordinal) {
        }
    }
}
