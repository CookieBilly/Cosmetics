

package ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityMulePet;
import ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.sub.EntityHorseChestedAbstractPet;

@EntitySize(width = 1.4f, height = 1.6f)
public class EntityMulePet extends EntityHorseChestedAbstractPet implements IEntityMulePet
{
    public EntityMulePet(final World world) {
        super((EntityTypes<?>)EntityTypes.MULE, world);
    }
    
    public EntityMulePet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.MULE, world, pet);
    }
}
