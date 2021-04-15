// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.World;
import net.minecraft.server.v1_13_R2.DataWatcher;
import net.minecraft.server.v1_13_R2.DataWatcherRegistry;
import net.minecraft.server.v1_13_R2.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityPolarBearPet;
import ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.EntityAgeablePet;

@EntitySize(width = 1.3f, height = 1.4f)
public class EntityPolarBearPet extends EntityAgeablePet implements IEntityPolarBearPet
{
    private static final DataWatcherObject<Boolean> STANDING;
    
    static {
        STANDING = DataWatcher.a((Class)EntityPolarBearPet.class, DataWatcherRegistry.i);
    }
    
    public EntityPolarBearPet(final World world) {
        super((EntityTypes<?>)EntityTypes.POLAR_BEAR, world);
    }
    
    public EntityPolarBearPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.POLAR_BEAR, world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityPolarBearPet.STANDING, (Object)false);
    }
    
    @Override
    public boolean isStanding() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityPolarBearPet.STANDING);
    }
    
    @Override
    public void setStanding(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityPolarBearPet.STANDING, (Object)b);
    }
}
