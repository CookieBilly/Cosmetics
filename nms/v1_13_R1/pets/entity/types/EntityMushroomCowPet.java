

package ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R1.EntityTypes;
import net.minecraft.server.v1_13_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityMushroomCowPet;
import ws.billy.CookieGadgets.nms.v1_13_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.9f, height = 1.3f)
public class EntityMushroomCowPet extends EntityAgeablePet implements IEntityMushroomCowPet
{
    public EntityMushroomCowPet(final World world) {
        super((EntityTypes<?>)EntityTypes.MOOSHROOM, world);
    }
    
    public EntityMushroomCowPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.MOOSHROOM, world, pet);
    }
}
