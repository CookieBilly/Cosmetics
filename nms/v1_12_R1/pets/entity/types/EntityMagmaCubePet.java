

package ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_12_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityMagmaCubePet;

@EntitySize(width = 0.6f, height = 0.6f)
public class EntityMagmaCubePet extends EntitySlimePet implements IEntityMagmaCubePet
{
    public EntityMagmaCubePet(final World world) {
        super(world);
    }
    
    public EntityMagmaCubePet(final World world, final IPet pet) {
        super(world, pet);
    }
}
