

package ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R1.EntityTypes;
import net.minecraft.server.v1_13_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityHuskPet;

@EntitySize(width = 0.6f, height = 1.95f)
public class EntityHuskPet extends EntityZombiePet implements IEntityHuskPet
{
    public EntityHuskPet(final World world) {
        super((EntityTypes<?>)EntityTypes.HUSK, world);
    }
    
    public EntityHuskPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.HUSK, world, pet);
    }
}
