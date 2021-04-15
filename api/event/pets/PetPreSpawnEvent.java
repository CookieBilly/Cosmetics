

package ws.billy.CookieGadgets.api.event.pets;

import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class PetPreSpawnEvent extends Event
{
    private static final HandlerList handlers;
    private Player player;
    private Location spawnLocation;
    private PetType petType;
    
    static {
        handlers = new HandlerList();
    }
    
    public PetPreSpawnEvent(final Player player, final Location spawnLocation, final PetType petType) {
        this.player = player;
        this.spawnLocation = spawnLocation;
        this.petType = petType;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Location getSpawnLocation() {
        return this.spawnLocation;
    }
    
    public PetType getPetType() {
        return this.petType;
    }
    
    public HandlerList getHandlers() {
        return PetPreSpawnEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PetPreSpawnEvent.handlers;
    }
}
