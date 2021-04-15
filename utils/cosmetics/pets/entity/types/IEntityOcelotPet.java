

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GOcelotType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.ITameable;

public interface IEntityOcelotPet extends ITameable
{
    default GOcelotType getOcelotType() {
        return null;
    }
    
    default void setOcelotType(final GOcelotType type) {
    }
}
