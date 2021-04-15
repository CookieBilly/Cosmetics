

package ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySkeletonPet;
import ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.sub.EntitySkeletonAbstractPet;

@EntitySize(width = 0.6f, height = 1.9f)
public class EntitySkeletonPet extends EntitySkeletonAbstractPet implements IEntitySkeletonPet
{
    public EntitySkeletonPet(final World world) {
        super((EntityTypes<?>)EntityTypes.SKELETON, world);
    }
    
    public EntitySkeletonPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.SKELETON, world, pet);
    }
    
    public EntitySkeletonPet(final EntityTypes<?> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntitySkeletonPet(final EntityTypes<?> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
    }
}
