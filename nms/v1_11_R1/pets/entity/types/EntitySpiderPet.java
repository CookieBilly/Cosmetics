

package ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_11_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySpiderPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.EntityPet;

@EntitySize(width = 1.4f, height = 0.9f)
public class EntitySpiderPet extends EntityPet implements IEntitySpiderPet
{
    public EntitySpiderPet(final World world) {
        super(world);
    }
    
    public EntitySpiderPet(final World world, final IPet pet) {
        super(world, pet);
    }
}
