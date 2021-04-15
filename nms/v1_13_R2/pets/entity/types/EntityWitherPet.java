

package ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.World;
import net.minecraft.server.v1_13_R2.DataWatcher;
import net.minecraft.server.v1_13_R2.DataWatcherRegistry;
import net.minecraft.server.v1_13_R2.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWitherPet;
import ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.EntityPet;

public class EntityWitherPet extends EntityPet implements IEntityWitherPet
{
    private static final DataWatcherObject<Integer> FIRST_HEAD_TARGET;
    private static final DataWatcherObject<Integer> SECOND_HEAD_TARGET;
    private static final DataWatcherObject<Integer> THIRD_HEAD_TARGET;
    private static final DataWatcherObject<Integer> SHIELDED;
    
    static {
        FIRST_HEAD_TARGET = DataWatcher.a((Class)EntityWitherPet.class, DataWatcherRegistry.b);
        SECOND_HEAD_TARGET = DataWatcher.a((Class)EntityWitherPet.class, DataWatcherRegistry.b);
        THIRD_HEAD_TARGET = DataWatcher.a((Class)EntityWitherPet.class, DataWatcherRegistry.b);
        SHIELDED = DataWatcher.a((Class)EntityWitherPet.class, DataWatcherRegistry.b);
    }
    
    public EntityWitherPet(final World world) {
        super((EntityTypes<?>)EntityTypes.WITHER, world);
    }
    
    public EntityWitherPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.WITHER, world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityWitherPet.FIRST_HEAD_TARGET, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityWitherPet.SECOND_HEAD_TARGET, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityWitherPet.THIRD_HEAD_TARGET, (Object)0);
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
