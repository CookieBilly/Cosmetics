

package ws.billy.CookieGadgets.nms.v1_16_R1.pets.entity.sub;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R1.World;
import net.minecraft.server.v1_16_R1.EntityInsentient;
import net.minecraft.server.v1_16_R1.EntityTypes;
import net.minecraft.server.v1_16_R1.DataWatcher;
import net.minecraft.server.v1_16_R1.DataWatcherRegistry;
import net.minecraft.server.v1_16_R1.DataWatcherObject;
import ws.billy.CookieGadgets.nms.v1_16_R1.pets.entity.EntityPet;

public abstract class EntitySkeletonAbstractPet extends EntityPet
{
    private static final DataWatcherObject<Boolean> RAISE_ARMS;
    
    static {
        RAISE_ARMS = DataWatcher.a((Class)EntitySkeletonAbstractPet.class, DataWatcherRegistry.i);
    }
    
    public EntitySkeletonAbstractPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntitySkeletonAbstractPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntitySkeletonAbstractPet.RAISE_ARMS, (Object)false);
    }
    
    public boolean isArmsRaised() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntitySkeletonAbstractPet.RAISE_ARMS);
    }
    
    public void setArmsRaised(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntitySkeletonAbstractPet.RAISE_ARMS, (Object)b);
    }
}
