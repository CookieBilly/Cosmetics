

package ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityBlazePet;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 1.7f)
public class EntityBlazePet extends EntityPet implements IEntityBlazePet
{
    public EntityBlazePet(final World world) {
        super(world);
    }
    
    public EntityBlazePet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(16, (Object)0);
    }
    
    @Override
    public boolean isBurning() {
        return (this.datawatcher.getByte(16) & 0x1) != 0x0;
    }
    
    @Override
    public void setBurning(final boolean b) {
        this.datawatcher.watch(16, (Object)(byte)(b ? 1 : 0));
    }
}
