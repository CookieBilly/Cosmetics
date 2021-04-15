

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.sub;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.World;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.DataWatcherSerializer;
import net.minecraft.server.v1_16_R3.DataWatcher;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.utils.DataWatcherWrapper;
import net.minecraft.server.v1_16_R3.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IChestedAbstractPet;

public abstract class EntityHorseChestedAbstractPet extends EntityHorseAbstractPet implements IChestedAbstractPet
{
    private static final DataWatcherObject<Boolean> CHEST;
    
    static {
        CHEST = DataWatcher.a((Class)EntityHorseChestedAbstractPet.class, (DataWatcherSerializer)DataWatcherWrapper.BOOLEAN);
    }
    
    public EntityHorseChestedAbstractPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntityHorseChestedAbstractPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityHorseChestedAbstractPet.CHEST, (Object)false);
    }
    
    @Override
    public boolean hasChest() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityHorseChestedAbstractPet.CHEST);
    }
    
    @Override
    public void setHasChest(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityHorseChestedAbstractPet.CHEST, (Object)b);
    }
}
