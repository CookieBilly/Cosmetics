

package ws.billy.CookieGadgets.nms.v1_8_R3.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R3.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWitherPet;
import ws.billy.CookieGadgets.nms.v1_8_R3.pets.entity.EntityPet;

@EntitySize(width = 0.9f, height = 3.5f)
public class EntityWitherPet extends EntityPet implements IEntityWitherPet
{
    public EntityWitherPet(final World world) {
        super(world);
    }
    
    public EntityWitherPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(17, (Object)0);
        this.datawatcher.a(18, (Object)0);
        this.datawatcher.a(19, (Object)0);
        this.datawatcher.a(20, (Object)0);
    }
    
    @Override
    public boolean isShielded() {
        return this.datawatcher.getInt(20) == 1;
    }
    
    @Override
    public void setShielded(final boolean i) {
        this.datawatcher.watch(20, (Object)(int)(i ? 1 : 0));
        this.setHealth((float)(i ? 150 : 300));
    }
    
    @Override
    public boolean isSmall() {
        return this.datawatcher.getInt(20) == 600;
    }
    
    @Override
    public void setSmall(final boolean b) {
        this.datawatcher.watch(20, (Object)(b ? 600 : 0));
    }
}
