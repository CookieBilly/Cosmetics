

package ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.sub;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_11_R1.World;
import net.minecraft.server.v1_11_R1.DataWatcher;
import net.minecraft.server.v1_11_R1.DataWatcherRegistry;
import net.minecraft.server.v1_11_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IChestedAbstractPet;

public abstract class EntityHorseChestedAbstractPet extends EntityHorseAbstractPet implements IChestedAbstractPet
{
    private static final DataWatcherObject<Boolean> CHEST;
    
    static {
        CHEST = DataWatcher.a((Class)EntityHorseChestedAbstractPet.class, DataWatcherRegistry.h);
    }
    
    public EntityHorseChestedAbstractPet(final World world) {
        super(world);
    }
    
    public EntityHorseChestedAbstractPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
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
