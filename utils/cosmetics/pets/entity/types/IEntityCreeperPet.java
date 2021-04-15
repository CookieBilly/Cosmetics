

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;

public interface IEntityCreeperPet extends IEntityPet
{
    boolean isPowered();
    
    void setPowered(final boolean p0);
    
    boolean isIgnited();
    
    void setIgnited(final boolean p0);
}
