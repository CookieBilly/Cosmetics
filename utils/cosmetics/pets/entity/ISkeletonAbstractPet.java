

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity;

public interface ISkeletonAbstractPet extends IEntityPet
{
    default boolean isArmsRaised() {
        return false;
    }
    
    default void setArmsRaised(final boolean flag) {
    }
}
