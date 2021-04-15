

package ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.sub;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_11_R1.World;
import net.minecraft.server.v1_11_R1.DataWatcher;
import net.minecraft.server.v1_11_R1.DataWatcherRegistry;
import net.minecraft.server.v1_11_R1.DataWatcherObject;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.EntityPet;

public abstract class EntitySkeletonAbstractPet extends EntityPet
{
    private static final DataWatcherObject<Boolean> b;
    
    static {
        b = DataWatcher.a((Class)EntitySkeletonAbstractPet.class, DataWatcherRegistry.h);
    }
    
    public EntitySkeletonAbstractPet(final World world) {
        super(world);
    }
    
    public EntitySkeletonAbstractPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntitySkeletonAbstractPet.b, (Object)false);
    }
}
