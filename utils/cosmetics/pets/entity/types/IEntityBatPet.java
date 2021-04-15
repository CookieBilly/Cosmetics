

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;

public interface IEntityBatPet extends IEntityPet
{
    boolean isHanging();
    
    void setHanging(final boolean p0);
}
