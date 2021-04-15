

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWitherSkeletonPet;

@EntitySize(width = 0.7f, height = 2.4f)
public class EntityWitherSkeletonPet extends EntitySkeletonPet implements IEntityWitherSkeletonPet
{
    public EntityWitherSkeletonPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.WITHER_SKELETON, world);
    }
    
    public EntityWitherSkeletonPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.WITHER_SKELETON, world, pet);
    }
}
