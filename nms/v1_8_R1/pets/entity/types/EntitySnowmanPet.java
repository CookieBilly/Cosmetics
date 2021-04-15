

package ws.billy.CookieGadgets.nms.v1_8_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySnowmanPet;
import ws.billy.CookieGadgets.nms.v1_8_R1.pets.entity.EntityPet;

@EntitySize(width = 0.4f, height = 1.8f)
public class EntitySnowmanPet extends EntityPet implements IEntitySnowmanPet
{
    public EntitySnowmanPet(final World world) {
        super(world);
    }
    
    public EntitySnowmanPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
    }
}
