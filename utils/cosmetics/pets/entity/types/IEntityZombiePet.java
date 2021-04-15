

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IAgeablePet;

public interface IEntityZombiePet extends IAgeablePet, IEntityVillagerPet
{
    default void setVillager(final boolean flag) {
    }
    
    default boolean isArmsRaised() {
        return false;
    }
    
    default void setArmsRaised(final boolean flag) {
    }
}
