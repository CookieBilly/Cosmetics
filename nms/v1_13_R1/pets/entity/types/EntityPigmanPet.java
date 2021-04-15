

package ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R1.EntityTypes;
import net.minecraft.server.v1_13_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityPigmanPet;

@EntitySize(width = 0.6f, height = 1.8f)
public class EntityPigmanPet extends EntityZombiePet implements IEntityPigmanPet
{
    public EntityPigmanPet(final World world) {
        super((EntityTypes<?>)EntityTypes.ZOMBIE_PIGMAN, world);
    }
    
    public EntityPigmanPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.ZOMBIE_PIGMAN, world, pet);
    }
}
