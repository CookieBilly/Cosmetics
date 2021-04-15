

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityHuskPet;

@EntitySize(width = 0.6f, height = 1.95f)
public class EntityHuskPet extends EntityZombiePet implements IEntityHuskPet
{
    public EntityHuskPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.HUSK, world);
    }
    
    public EntityHuskPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.HUSK, world, pet);
    }
}
