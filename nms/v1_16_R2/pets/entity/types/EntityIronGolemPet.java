

package ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityIronGolemPet;
import ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.EntityPet;

@EntitySize(width = 1.4f, height = 2.9f)
public class EntityIronGolemPet extends EntityPet implements IEntityIronGolemPet
{
    public EntityIronGolemPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.IRON_GOLEM, world);
    }
    
    public EntityIronGolemPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.IRON_GOLEM, world, pet);
    }
}
