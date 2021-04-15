

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IAgeablePet;

public interface IEntityPigPet extends IAgeablePet
{
    boolean hasSaddle();
    
    void setSaddle(final boolean p0);
}
