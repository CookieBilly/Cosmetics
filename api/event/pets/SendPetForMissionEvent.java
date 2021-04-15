

package ws.billy.CookieGadgets.api.event.pets;

import ws.billy.CookieGadgets.utils.cosmetics.pets.petdata.GPet;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class SendPetForMissionEvent extends Event
{
    private static final HandlerList handlers;
    private Player owner;
    private GPet pet;
    private int expObtained;
    
    static {
        handlers = new HandlerList();
    }
    
    public SendPetForMissionEvent(final Player owner, final GPet pet, final int expObtained) {
        this.owner = owner;
        this.pet = pet;
        this.expObtained = expObtained;
    }
    
    public Player getOwner() {
        return this.owner;
    }
    
    public GPet getGPet() {
        return this.pet;
    }
    
    public int getEXPObtained() {
        return this.expObtained;
    }
    
    public HandlerList getHandlers() {
        return SendPetForMissionEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return SendPetForMissionEvent.handlers;
    }
}
