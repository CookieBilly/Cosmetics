

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;

public interface IEntityWitherPet extends IEntityPet
{
    boolean isShielded();
    
    void setShielded(final boolean p0);
    
    default boolean isSmall() {
        return false;
    }
    
    default void setSmall(final boolean flag) {
    }
}
