

package ws.billy.CookieGadgets.nms.v1_8_R3.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R3.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityPigmanPet;

@EntitySize(width = 0.6f, height = 1.8f)
public class EntityPigmanPet extends EntityZombiePet implements IEntityPigmanPet
{
    public EntityPigmanPet(final World world) {
        super(world);
    }
    
    public EntityPigmanPet(final World world, final IPet pet) {
        super(world, pet);
    }
}
