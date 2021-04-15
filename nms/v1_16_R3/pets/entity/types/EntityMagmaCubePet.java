

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityMagmaCubePet;

@EntitySize(width = 0.6f, height = 0.6f)
public class EntityMagmaCubePet extends EntitySlimePet implements IEntityMagmaCubePet
{
    public EntityMagmaCubePet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.MAGMA_CUBE, world);
    }
    
    public EntityMagmaCubePet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.MAGMA_CUBE, world, pet);
    }
}
