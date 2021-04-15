

package ws.billy.CookieGadgets.nms.v1_14_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.World;
import net.minecraft.server.v1_14_R1.DataWatcher;
import net.minecraft.server.v1_14_R1.DataWatcherRegistry;
import net.minecraft.server.v1_14_R1.IBlockData;
import java.util.Optional;
import net.minecraft.server.v1_14_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityEndermanPet;
import ws.billy.CookieGadgets.nms.v1_14_R1.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 2.9f)
public class EntityEndermanPet extends EntityPet implements IEntityEndermanPet
{
    private static final DataWatcherObject<Optional<IBlockData>> BLOCK;
    private static final DataWatcherObject<Boolean> SCREAMING;
    
    static {
        BLOCK = DataWatcher.a((Class)EntityEndermanPet.class, DataWatcherRegistry.h);
        SCREAMING = DataWatcher.a((Class)EntityEndermanPet.class, DataWatcherRegistry.i);
    }
    
    public EntityEndermanPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.ENDERMAN, world);
    }
    
    public EntityEndermanPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.ENDERMAN, world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityEndermanPet.BLOCK, (Object)Optional.empty());
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
        return ((Optional)this.datawatcher.get((DataWatcherObject)EntityEndermanPet.BLOCK)).orElse(null);
    }
    
    public void setCarried(final IBlockData value) {
        this.datawatcher.set((DataWatcherObject)EntityEndermanPet.BLOCK, (Object)Optional.ofNullable(value));
    }
}
