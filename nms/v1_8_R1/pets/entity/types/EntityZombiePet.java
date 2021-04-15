

package ws.billy.CookieGadgets.nms.v1_8_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityZombiePet;
import ws.billy.CookieGadgets.nms.v1_8_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.6f, height = 1.8f)
public class EntityZombiePet extends EntityAgeablePet implements IEntityZombiePet
{
    public EntityZombiePet(final World world) {
        super(world);
    }
    
    public EntityZombiePet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(13, (Object)0);
    }
    
    @Override
    public void setBaby(final boolean b) {
        this.datawatcher.watch(12, (Object)(byte)(b ? 1 : 0));
    }
    
    @Override
    public void setVillager(final boolean b) {
        this.datawatcher.watch(13, (Object)(byte)(b ? 1 : 0));
    }
}
