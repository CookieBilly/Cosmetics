

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityNoClipPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IFlyablePet;

public interface IEntityVexPet extends IFlyablePet, IEntityNoClipPet
{
    boolean isPowered();
    
    void setPowered(final boolean p0);
}
