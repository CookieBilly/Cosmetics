

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GSkeletonType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.ISkeletonAbstractPet;

public interface IEntitySkeletonPet extends ISkeletonAbstractPet
{
    default GSkeletonType getSkeletonType() {
        return GSkeletonType.NORMAL;
    }
    
    default void setSkeletonType(final GSkeletonType type) {
    }
}
