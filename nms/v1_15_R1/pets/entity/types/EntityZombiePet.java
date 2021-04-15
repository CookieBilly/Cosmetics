

package ws.billy.CookieGadgets.nms.v1_15_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GProfession;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_15_R1.World;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.DataWatcher;
import net.minecraft.server.v1_15_R1.DataWatcherRegistry;
import net.minecraft.server.v1_15_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityZombiePet;
import ws.billy.CookieGadgets.nms.v1_15_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.6f, height = 1.8f)
public class EntityZombiePet extends EntityAgeablePet implements IEntityZombiePet
{
    private static final DataWatcherObject<Integer> VILLAGER_TYPE;
    private static final DataWatcherObject<Boolean> ARMS_RAISED;
    
    static {
        VILLAGER_TYPE = DataWatcher.a((Class)EntityZombiePet.class, DataWatcherRegistry.b);
        ARMS_RAISED = DataWatcher.a((Class)EntityZombiePet.class, DataWatcherRegistry.i);
    }
    
    public EntityZombiePet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntityZombiePet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
    }
    
    public EntityZombiePet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.ZOMBIE, world);
    }
    
    public EntityZombiePet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.ZOMBIE, world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.getDataWatcher().register((DataWatcherObject)EntityZombiePet.VILLAGER_TYPE, (Object)GProfession.NORMAL.getId());
        this.getDataWatcher().register((DataWatcherObject)EntityZombiePet.ARMS_RAISED, (Object)false);
    }
    
    @Override
    public boolean isArmsRaised() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityZombiePet.ARMS_RAISED);
    }
    
    @Override
    public void setArmsRaised(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityZombiePet.ARMS_RAISED, (Object)b);
    }
}
