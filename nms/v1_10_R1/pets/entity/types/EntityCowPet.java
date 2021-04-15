

package ws.billy.CookieGadgets.nms.v1_10_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_10_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityCowPet;
import ws.billy.CookieGadgets.nms.v1_10_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.9f, height = 1.3f)
public class EntityCowPet extends EntityAgeablePet implements IEntityCowPet
{
    public EntityCowPet(final World world) {
        super(world);
    }
    
    public EntityCowPet(final World world, final IPet pet) {
        super(world, pet);
    }
}
