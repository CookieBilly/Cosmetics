

package ws.billy.CookieGadgets.nms.v1_9_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_9_R2.World;
import net.minecraft.server.v1_9_R2.DataWatcher;
import net.minecraft.server.v1_9_R2.DataWatcherRegistry;
import net.minecraft.server.v1_9_R2.IBlockData;
import com.google.common.base.Optional;
import net.minecraft.server.v1_9_R2.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityEndermanPet;
import ws.billy.CookieGadgets.nms.v1_9_R2.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 2.9f)
public class EntityEndermanPet extends EntityPet implements IEntityEndermanPet
{
    private static final DataWatcherObject<Optional<IBlockData>> BLOCK;
    private static final DataWatcherObject<Boolean> SCREAMING;
    
    static {
        BLOCK = DataWatcher.a((Class)EntityEndermanPet.class, DataWatcherRegistry.g);
        SCREAMING = DataWatcher.a((Class)EntityEndermanPet.class, DataWatcherRegistry.h);
    }
    
    public EntityEndermanPet(final World world) {
        super(world);
    }
    
    public EntityEndermanPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityEndermanPet.BLOCK, (Object)Optional.absent());
        this.datawatcher.register((DataWatcherObject)EntityEndermanPet.SCREAMING, (Object)false);
    }
    
    @Override
    public boolean isScreaming() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityEndermanPet.SCREAMING);
    }
    
    @Override
    public void setScreaming(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityEndermanPet.SCREAMING, (Object)b);
    }
    
    public IBlockData getCarried() {
        return (IBlockData)((Optional)this.datawatcher.get((DataWatcherObject)EntityEndermanPet.BLOCK)).orNull();
    }
    
    public void setCarried(final IBlockData blockData) {
        this.datawatcher.set((DataWatcherObject)EntityEndermanPet.BLOCK, (Object)Optional.fromNullable((Object)blockData));
    }
}
