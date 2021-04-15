

package ws.billy.CookieGadgets.nms.v1_9_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_9_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityEndermitePet;
import ws.billy.CookieGadgets.nms.v1_9_R2.pets.entity.EntityPet;

@EntitySize(width = 0.4f, height = 0.3f)
public class EntityEndermitePet extends EntityPet implements IEntityEndermitePet
{
    public EntityEndermitePet(final World world) {
        super(world);
    }
    
    public EntityEndermitePet(final World world, final IPet pet) {
        super(world, pet);
    }
}
