

package ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityStrayPet;
import ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.sub.EntitySkeletonAbstractPet;

@EntitySize(width = 0.6f, height = 1.99f)
public class EntityStrayPet extends EntitySkeletonAbstractPet implements IEntityStrayPet
{
    public EntityStrayPet(final World world) {
        super((EntityTypes<?>)EntityTypes.STRAY, world);
    }
    
    public EntityStrayPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.STRAY, world, pet);
    }
}
