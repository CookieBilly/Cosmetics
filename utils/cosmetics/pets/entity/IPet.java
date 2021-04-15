

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity;

import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.PetType;
import org.bukkit.entity.Player;

public interface IPet
{
    Player getOwner();
    
    PetType getType();
    
    GEntity getGEntity();
    
    IEntityPet getEntityPet();
    
    void removePet();
    
    boolean isOwnerRiding();
    
    void setOwnerRidePet(final boolean p0);
    
    boolean isHat();
    
    void setAsHat(final boolean p0);
    
    void toggleRidePet();
    
    void toggleHat();
}
