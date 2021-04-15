

package ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_12_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityIllusionerPet;

@EntitySize(width = 0.6f, height = 1.95f)
public class EntityIllusionerPet extends EntityEvokerPet implements IEntityIllusionerPet
{
    public EntityIllusionerPet(final World world) {
        super(world);
    }
    
    public EntityIllusionerPet(final World world, final IPet pet) {
        super(world, pet);
    }
}
