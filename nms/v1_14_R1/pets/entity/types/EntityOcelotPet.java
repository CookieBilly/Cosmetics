

package ws.billy.CookieGadgets.nms.v1_14_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityOcelotPet;
import ws.billy.CookieGadgets.nms.v1_14_R1.pets.entity.EntityTameablePet;

@EntitySize(width = 0.6f, height = 0.8f)
public class EntityOcelotPet extends EntityTameablePet implements IEntityOcelotPet
{
    public EntityOcelotPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.OCELOT, world);
    }
    
    public EntityOcelotPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.OCELOT, world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
    }
}
