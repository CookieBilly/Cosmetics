

package ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_11_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySkeletonPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.sub.EntitySkeletonAbstractPet;

@EntitySize(width = 0.6f, height = 1.9f)
public class EntitySkeletonPet extends EntitySkeletonAbstractPet implements IEntitySkeletonPet
{
    public EntitySkeletonPet(final World world) {
        super(world);
    }
    
    public EntitySkeletonPet(final World world, final IPet pet) {
        super(world, pet);
    }
}
