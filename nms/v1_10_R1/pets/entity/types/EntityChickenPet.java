

package ws.billy.CookieGadgets.nms.v1_10_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_10_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityChickenPet;
import ws.billy.CookieGadgets.nms.v1_10_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.3f, height = 0.7f)
public class EntityChickenPet extends EntityAgeablePet implements IEntityChickenPet
{
    public EntityChickenPet(final World world) {
        super(world);
    }
    
    public EntityChickenPet(final World world, final IPet pet) {
        super(world, pet);
    }
}
