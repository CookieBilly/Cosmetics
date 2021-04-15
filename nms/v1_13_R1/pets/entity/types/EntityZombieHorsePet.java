

package ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R1.EntityTypes;
import net.minecraft.server.v1_13_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityZombieHorsePet;
import ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.sub.EntityHorseChestedAbstractPet;

@EntitySize(width = 1.4f, height = 1.6f)
public class EntityZombieHorsePet extends EntityHorseChestedAbstractPet implements IEntityZombieHorsePet
{
    public EntityZombieHorsePet(final World world) {
        super((EntityTypes<?>)EntityTypes.ZOMBIE_HORSE, world);
    }
    
    public EntityZombieHorsePet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.ZOMBIE_HORSE, world, pet);
    }
}
