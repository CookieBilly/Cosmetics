

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IColorable;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IAgeablePet;

public interface IEntitySheepPet extends IAgeablePet, IColorable
{
    boolean isSheared();
    
    void setSheared(final boolean p0);
    
    boolean isRainbow();
    
    void setRainbow(final boolean p0);
}
