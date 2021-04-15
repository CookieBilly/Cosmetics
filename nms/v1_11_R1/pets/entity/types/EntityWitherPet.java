

package ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_11_R1.World;
import net.minecraft.server.v1_11_R1.DataWatcher;
import net.minecraft.server.v1_11_R1.DataWatcherRegistry;
import net.minecraft.server.v1_11_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWitherPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.EntityPet;

public class EntityWitherPet extends EntityPet implements IEntityWitherPet
{
    private static final DataWatcherObject<Integer> a;
    private static final DataWatcherObject<Integer> b;
    private static final DataWatcherObject<Integer> c;
    private static final DataWatcherObject<Integer> SHIELDED;
    
    static {
        a = DataWatcher.a((Class)EntityWitherPet.class, DataWatcherRegistry.b);
        b = DataWatcher.a((Class)EntityWitherPet.class, DataWatcherRegistry.b);
        c = DataWatcher.a((Class)EntityWitherPet.class, DataWatcherRegistry.b);
        SHIELDED = DataWatcher.a((Class)EntityWitherPet.class, DataWatcherRegistry.b);
    }
    
    public EntityWitherPet(final World world) {
        super(world);
    }
    
    public EntityWitherPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityWitherPet.a, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityWitherPet.b, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityWitherPet.c, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityWitherPet.SHIELDED, (Object)0);
    }
    
    @Override
    public boolean isShielded() {
        return (int)this.datawatcher.get((DataWatcherObject)EntityWitherPet.SHIELDED) == 1;
    }
    
    @Override
    public void setShielded(final boolean i) {
        this.datawatcher.set((DataWatcherObject)EntityWitherPet.SHIELDED, (Object)(int)(i ? 1 : 0));
        this.setHealth((float)(i ? 150 : 300));
    }
    
    @Override
    public boolean isSmall() {
        return (int)this.datawatcher.get((DataWatcherObject)EntityWitherPet.SHIELDED) == 600;
    }
    
    @Override
    public void setSmall(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityWitherPet.SHIELDED, (Object)(b ? 600 : 0));
    }
}
