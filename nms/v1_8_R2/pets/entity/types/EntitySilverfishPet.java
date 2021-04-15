

package ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySilverfishPet;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.EntityPet;

@EntitySize(width = 0.3f, height = 0.7f)
public class EntitySilverfishPet extends EntityPet implements IEntitySilverfishPet
{
    public EntitySilverfishPet(final World world) {
        super(world);
    }
    
    public EntitySilverfishPet(final World world, final IPet pet) {
        super(world, pet);
    }
}
