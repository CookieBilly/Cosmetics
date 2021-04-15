

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IFlyablePet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;

public interface IEntityBlazePet extends IEntityPet, IFlyablePet
{
    boolean isBurning();
    
    void setBurning(final boolean p0);
}
