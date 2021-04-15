

package ws.billy.CookieGadgets.nms.v1_14_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityDonkeyPet;
import ws.billy.CookieGadgets.nms.v1_14_R1.pets.entity.sub.EntityHorseChestedAbstractPet;

@EntitySize(width = 1.4f, height = 1.6f)
public class EntityDonkeyPet extends EntityHorseChestedAbstractPet implements IEntityDonkeyPet
{
    public EntityDonkeyPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.DONKEY, world);
    }
    
    public EntityDonkeyPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.DONKEY, world, pet);
    }
}
