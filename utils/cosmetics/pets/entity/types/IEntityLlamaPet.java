

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GLlamaColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IColorable;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IChestedAbstractPet;

public interface IEntityLlamaPet extends IChestedAbstractPet, IColorable
{
    GLlamaColor getLlamaColor();
    
    void setLlamaColor(final GLlamaColor p0);
}
