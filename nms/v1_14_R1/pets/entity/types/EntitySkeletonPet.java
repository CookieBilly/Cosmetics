

package ws.billy.CookieGadgets.nms.v1_14_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_14_R1.World;
import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.EntityTypes;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySkeletonPet;
import ws.billy.CookieGadgets.nms.v1_14_R1.pets.entity.sub.EntitySkeletonAbstractPet;

@EntitySize(width = 0.6f, height = 1.9f)
public class EntitySkeletonPet extends EntitySkeletonAbstractPet implements IEntitySkeletonPet
{
    public EntitySkeletonPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntitySkeletonPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
    }
    
    public EntitySkeletonPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.SKELETON, world);
    }
    
    public EntitySkeletonPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.SKELETON, world, pet);
    }
}
