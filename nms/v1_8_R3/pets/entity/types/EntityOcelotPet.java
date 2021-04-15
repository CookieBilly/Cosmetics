

package ws.billy.CookieGadgets.nms.v1_8_R3.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GOcelotType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R3.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityOcelotPet;
import ws.billy.CookieGadgets.nms.v1_8_R3.pets.entity.EntityTameablePet;

@EntitySize(width = 0.6f, height = 0.8f)
public class EntityOcelotPet extends EntityTameablePet implements IEntityOcelotPet
{
    public EntityOcelotPet(final World world) {
        super(world);
    }
    
    public EntityOcelotPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(18, (Object)0);
    }
    
    @Override
    public GOcelotType getOcelotType() {
        return GOcelotType.getById(this.datawatcher.getByte(18));
    }
    
    @Override
    public void setOcelotType(final GOcelotType gOcelotType) {
        this.datawatcher.watch(18, (Object)(byte)gOcelotType.getId());
    }
}
