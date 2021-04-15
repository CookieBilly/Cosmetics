

package ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R1.EntityTypes;
import net.minecraft.server.v1_13_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWitherSkeletonPet;

@EntitySize(width = 0.7f, height = 2.4f)
public class EntityWitherSkeletonPet extends EntitySkeletonPet implements IEntityWitherSkeletonPet
{
    public EntityWitherSkeletonPet(final World world) {
        super((EntityTypes<?>)EntityTypes.WITHER_SKELETON, world);
    }
    
    public EntityWitherSkeletonPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.WITHER_SKELETON, world, pet);
    }
}
