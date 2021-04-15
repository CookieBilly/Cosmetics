

package ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R1.EntityTypes;
import net.minecraft.server.v1_13_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityIronGolemPet;
import ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.EntityPet;

@EntitySize(width = 1.4f, height = 2.9f)
public class EntityIronGolemPet extends EntityPet implements IEntityIronGolemPet
{
    public EntityIronGolemPet(final World world) {
        super((EntityTypes<?>)EntityTypes.IRON_GOLEM, world);
    }
    
    public EntityIronGolemPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.IRON_GOLEM, world, pet);
    }
}
