

package ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWitchPet;
import ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 1.9f)
public class EntityWitchPet extends EntityPet implements IEntityWitchPet
{
    public EntityWitchPet(final World world) {
        super((EntityTypes<?>)EntityTypes.WITCH, world);
    }
    
    public EntityWitchPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.WITCH, world, pet);
    }
}
