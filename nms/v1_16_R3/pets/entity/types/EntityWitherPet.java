// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.World;
import net.minecraft.server.v1_16_R3.DataWatcherSerializer;
import net.minecraft.server.v1_16_R3.DataWatcher;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.utils.DataWatcherWrapper;
import net.minecraft.server.v1_16_R3.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWitherPet;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.EntityPet;

@EntitySize(width = 0.9f, height = 3.5f)
public class EntityWitherPet extends EntityPet implements IEntityWitherPet
{
    private static final DataWatcherObject<Integer> FIRST_HEAD_TARGET;
    private static final DataWatcherObject<Integer> SECOND_HEAD_TARGET;
    private static final DataWatcherObject<Integer> THIRD_HEAD_TARGET;
    private static final DataWatcherObject<Integer> SHIELDED;
    
    static {
        FIRST_HEAD_TARGET = DataWatcher.a((Class)EntityWitherPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
        SECOND_HEAD_TARGET = DataWatcher.a((Class)EntityWitherPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
        THIRD_HEAD_TARGET = DataWatcher.a((Class)EntityWitherPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
        SHIELDED = DataWatcher.a((Class)EntityWitherPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
    }
    
    public EntityWitherPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.WITHER, world);
    }
    
    public EntityWitherPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.WITHER, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
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
        return (int)this.datawatcher.get((DataWatcherObject)EntityWitherPet.SHIELDED) == 400;
    }
    
    @Override
    public void setSmall(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityWitherPet.SHIELDED, (Object)(b ? 400 : 0));
    }
}
