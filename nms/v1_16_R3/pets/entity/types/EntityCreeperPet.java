

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
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityCreeperPet;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 1.9f)
public class EntityCreeperPet extends EntityPet implements IEntityCreeperPet
{
    protected static final DataWatcherObject<Integer> STATE;
    protected static final DataWatcherObject<Boolean> POWERED;
    protected static final DataWatcherObject<Boolean> IGNITED;
    
    static {
        STATE = DataWatcher.a((Class)EntityCreeperPet.class, (DataWatcherSerializer)DataWatcherWrapper.INTEGER);
        POWERED = DataWatcher.a((Class)EntityCreeperPet.class, (DataWatcherSerializer)DataWatcherWrapper.BOOLEAN);
        IGNITED = DataWatcher.a((Class)EntityCreeperPet.class, (DataWatcherSerializer)DataWatcherWrapper.BOOLEAN);
    }
    
    public EntityCreeperPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.CREEPER, world);
    }
    
    public EntityCreeperPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.CREEPER, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityCreeperPet.STATE, (Object)(-1));
        this.datawatcher.register((DataWatcherObject)EntityCreeperPet.POWERED, (Object)false);
        this.datawatcher.register((DataWatcherObject)EntityCreeperPet.IGNITED, (Object)false);
    }
    
    @Override
    public boolean isPowered() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityCreeperPet.POWERED);
    }
    
    @Override
    public void setPowered(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityCreeperPet.POWERED, (Object)b);
    }
    
    @Override
    public boolean isIgnited() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityCreeperPet.IGNITED);
    }
    
    @Override
    public void setIgnited(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityCreeperPet.IGNITED, (Object)b);
    }
}
