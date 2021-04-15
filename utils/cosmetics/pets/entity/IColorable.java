

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity;

import ws.billy.CookieGadgets.utils.GDyeColor;

public interface IColorable extends IEntityPet
{
    GDyeColor getColor();
    
    void setColor(final GDyeColor p0);
}
