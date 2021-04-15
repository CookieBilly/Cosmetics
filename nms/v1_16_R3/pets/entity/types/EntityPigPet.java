

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
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityPigPet;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.9f, height = 0.9f)
public class EntityPigPet extends EntityAgeablePet implements IEntityPigPet
{
    private static final DataWatcherObject<Boolean> SADDLE;
    
    static {
        SADDLE = DataWatcher.a((Class)EntityPigPet.class, (DataWatcherSerializer)DataWatcherWrapper.BOOLEAN);
    }
    
    public EntityPigPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.PIG, world);
    }
    
    public EntityPigPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.PIG, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityPigPet.SADDLE, (Object)false);
    }
    
    @Override
    public boolean hasSaddle() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityPigPet.SADDLE);
    }
    
    @Override
    public void setSaddle(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityPigPet.SADDLE, (Object)b);
    }
}
