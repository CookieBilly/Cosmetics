

package ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityDonkeyPet;
import ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.sub.EntityHorseChestedAbstractPet;

@EntitySize(width = 1.4f, height = 1.6f)
public class EntityDonkeyPet extends EntityHorseChestedAbstractPet implements IEntityDonkeyPet
{
    public EntityDonkeyPet(final World world) {
        super((EntityTypes<?>)EntityTypes.DONKEY, world);
    }
    
    public EntityDonkeyPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.DONKEY, world, pet);
    }
}
