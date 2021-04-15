

package ws.billy.CookieGadgets.nms.v1_15_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityCowPet;
import ws.billy.CookieGadgets.nms.v1_15_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.9f, height = 1.3f)
public class EntityCowPet extends EntityAgeablePet implements IEntityCowPet
{
    public EntityCowPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.COW, world);
    }
    
    public EntityCowPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.COW, world, pet);
    }
}
