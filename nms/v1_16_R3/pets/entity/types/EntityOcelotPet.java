

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
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityOcelotPet;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.6f, height = 0.8f)
public class EntityOcelotPet extends EntityAgeablePet implements IEntityOcelotPet
{
    private static final DataWatcherObject<Boolean> TRUSTING;
    
    static {
        TRUSTING = DataWatcher.a((Class)EntityOcelotPet.class, (DataWatcherSerializer)DataWatcherWrapper.BOOLEAN);
    }
    
    public EntityOcelotPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.OCELOT, world);
    }
    
    public EntityOcelotPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.OCELOT, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityOcelotPet.TRUSTING, (Object)false);
    }
    
    @Override
    public boolean isTamed() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityOcelotPet.TRUSTING);
    }
    
    @Override
    public void setTamed(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityOcelotPet.TRUSTING, (Object)b);
    }
    
    @Override
    public boolean isSitting() {
        return false;
    }
    
    @Override
    public void setSitting(final boolean b) {
    }
}
