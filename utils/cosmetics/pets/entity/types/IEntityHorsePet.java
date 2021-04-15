

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseVariant;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseMarking;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseColor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GHorseArmor;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IHorseAbstractPet;

public interface IEntityHorsePet extends IHorseAbstractPet
{
    GHorseArmor getArmor();
    
    void setArmor(final GHorseArmor p0);
    
    GHorseColor getColor();
    
    void setColor(final GHorseColor p0);
    
    GHorseMarking getMarking();
    
    void setMarking(final GHorseMarking p0);
    
    default GHorseVariant getType() {
        return GHorseVariant.HORSE;
    }
    
    default void setType(final GHorseVariant variant) {
    }
}
