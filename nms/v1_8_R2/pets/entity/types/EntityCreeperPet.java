

package ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityCreeperPet;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 1.9f)
public class EntityCreeperPet extends EntityPet implements IEntityCreeperPet
{
    public EntityCreeperPet(final World world) {
        super(world);
    }
    
    public EntityCreeperPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(16, (Object)(-1));
        this.datawatcher.a(17, (Object)0);
        this.datawatcher.a(18, (Object)0);
    }
    
    @Override
    public boolean isPowered() {
        return this.datawatcher.getByte(17) == 1;
    }
    
    @Override
    public void setPowered(final boolean b) {
        this.datawatcher.watch(17, (Object)(byte)(b ? 1 : 0));
    }
    
    @Override
    public boolean isIgnited() {
        return this.datawatcher.getByte(18) == 1;
    }
    
    @Override
    public void setIgnited(final boolean b) {
        this.datawatcher.watch(18, (Object)(byte)(b ? 1 : 0));
    }
}
