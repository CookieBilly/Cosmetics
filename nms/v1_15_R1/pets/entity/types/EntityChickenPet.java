

package ws.billy.CookieGadgets.nms.v1_15_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityChickenPet;
import ws.billy.CookieGadgets.nms.v1_15_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.3f, height = 0.7f)
public class EntityChickenPet extends EntityAgeablePet implements IEntityChickenPet
{
    public EntityChickenPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.CHICKEN, world);
    }
    
    public EntityChickenPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.CHICKEN, world, pet);
    }
}
