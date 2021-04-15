

package ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GOcelotType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_11_R1.World;
import net.minecraft.server.v1_11_R1.DataWatcher;
import net.minecraft.server.v1_11_R1.DataWatcherRegistry;
import net.minecraft.server.v1_11_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityOcelotPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.EntityTameablePet;

@EntitySize(width = 0.6f, height = 0.8f)
public class EntityOcelotPet extends EntityTameablePet implements IEntityOcelotPet
{
    private static final DataWatcherObject<Integer> TYPE;
    
    static {
        TYPE = DataWatcher.a((Class)EntityOcelotPet.class, DataWatcherRegistry.b);
    }
    
    public EntityOcelotPet(final World world) {
        super(world);
    }
    
    public EntityOcelotPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityOcelotPet.TYPE, (Object)0);
    }
    
    @Override
    public GOcelotType getOcelotType() {
        return GOcelotType.getById((int)this.datawatcher.get((DataWatcherObject)EntityOcelotPet.TYPE));
    }
    
    @Override
    public void setOcelotType(final GOcelotType gOcelotType) {
        this.datawatcher.set((DataWatcherObject)EntityOcelotPet.TYPE, (Object)gOcelotType.getId());
    }
}
