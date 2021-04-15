

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

public interface IEntityZombieVillagerPet extends IEntityZombiePet
{
    default boolean isShaking() {
        return false;
    }
    
    default void setShaking(final boolean flag) {
    }
}
