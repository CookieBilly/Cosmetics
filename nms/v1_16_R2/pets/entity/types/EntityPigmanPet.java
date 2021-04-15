

package ws.billy.CookieGadgets.nms.v1_16_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityPigmanPet;

@EntitySize(width = 0.6f, height = 1.8f)
public class EntityPigmanPet extends EntityZombiePet implements IEntityPigmanPet
{
    public EntityPigmanPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.ZOMBIFIED_PIGLIN, world);
    }
    
    public EntityPigmanPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.ZOMBIFIED_PIGLIN, world, pet);
    }
}
