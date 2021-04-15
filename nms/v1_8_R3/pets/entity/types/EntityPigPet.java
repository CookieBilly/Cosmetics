

package ws.billy.CookieGadgets.nms.v1_8_R3.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R3.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityPigPet;
import ws.billy.CookieGadgets.nms.v1_8_R3.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.9f, height = 0.9f)
public class EntityPigPet extends EntityAgeablePet implements IEntityPigPet
{
    public EntityPigPet(final World world) {
        super(world);
    }
    
    public EntityPigPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(16, (Object)0);
    }
    
    @Override
    public boolean hasSaddle() {
        return (this.datawatcher.getByte(16) & 0x1) != 0x0;
    }
    
    @Override
    public void setSaddle(final boolean b) {
        this.datawatcher.watch(16, (Object)(byte)(b ? 1 : 0));
    }
}
