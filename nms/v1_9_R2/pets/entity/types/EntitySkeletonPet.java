

package ws.billy.CookieGadgets.nms.v1_9_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GSkeletonType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_9_R2.World;
import net.minecraft.server.v1_9_R2.DataWatcher;
import net.minecraft.server.v1_9_R2.DataWatcherRegistry;
import net.minecraft.server.v1_9_R2.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySkeletonPet;
import ws.billy.CookieGadgets.nms.v1_9_R2.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 1.9f)
public class EntitySkeletonPet extends EntityPet implements IEntitySkeletonPet
{
    private static final DataWatcherObject<Integer> TYPE;
    
    static {
        TYPE = DataWatcher.a((Class)EntitySkeletonPet.class, DataWatcherRegistry.b);
    }
    
    public EntitySkeletonPet(final World world) {
        super(world);
    }
    
    public EntitySkeletonPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntitySkeletonPet.TYPE, (Object)GSkeletonType.NORMAL.getId());
    }
    
    @Override
    public GSkeletonType getSkeletonType() {
        return GSkeletonType.getById((int)this.datawatcher.get((DataWatcherObject)EntitySkeletonPet.TYPE));
    }
    
    @Override
    public void setSkeletonType(final GSkeletonType gSkeletonType) {
        this.datawatcher.set((DataWatcherObject)EntitySkeletonPet.TYPE, (Object)gSkeletonType.getId());
    }
}
