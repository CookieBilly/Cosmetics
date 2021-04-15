

package ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_11_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityHuskPet;

@EntitySize(width = 0.6f, height = 1.95f)
public class EntityHuskPet extends EntityZombiePet implements IEntityHuskPet
{
    public EntityHuskPet(final World world) {
        super(world);
    }
    
    public EntityHuskPet(final World world, final IPet pet) {
        super(world, pet);
    }
}
