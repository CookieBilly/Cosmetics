

package ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_12_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWitherSkeletonPet;

@EntitySize(width = 0.7f, height = 2.4f)
public class EntityWitherSkeletonPet extends EntitySkeletonPet implements IEntityWitherSkeletonPet
{
    public EntityWitherSkeletonPet(final World world) {
        super(world);
    }
    
    public EntityWitherSkeletonPet(final World world, final IPet pet) {
        super(world, pet);
    }
}
