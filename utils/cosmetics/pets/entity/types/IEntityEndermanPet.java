

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;

public interface IEntityEndermanPet extends IEntityPet
{
    boolean isScreaming();
    
    void setScreaming(final boolean p0);
}
