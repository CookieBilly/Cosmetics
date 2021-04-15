

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IColorable;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.ITameable;

public interface IEntityWolfPet extends ITameable, IColorable
{
    default boolean isHeadTilted() {
        return false;
    }
    
    default void setHeadTilted(final boolean flag) {
    }
    
    default boolean isAngry() {
        return false;
    }
    
    default void setAngry(final boolean flag) {
    }
}
