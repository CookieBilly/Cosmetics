// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GProfession;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_11_R1.World;
import net.minecraft.server.v1_11_R1.DataWatcher;
import net.minecraft.server.v1_11_R1.DataWatcherRegistry;
import net.minecraft.server.v1_11_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityZombieVillagerPet;
import ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.6f, height = 1.95f)
public class EntityZombieVillagerPet extends EntityAgeablePet implements IEntityZombieVillagerPet
{
    private static final DataWatcherObject<Integer> VILLAGER_TYPE;
    private static final DataWatcherObject<Boolean> ARMS_RAISED;
    private static final DataWatcherObject<Boolean> CONVERTING;
    private static final DataWatcherObject<Integer> PROFESSION;
    
    static {
        VILLAGER_TYPE = DataWatcher.a((Class)EntityZombieVillagerPet.class, DataWatcherRegistry.b);
        ARMS_RAISED = DataWatcher.a((Class)EntityZombieVillagerPet.class, DataWatcherRegistry.h);
        CONVERTING = DataWatcher.a((Class)EntityZombieVillagerPet.class, DataWatcherRegistry.h);
        PROFESSION = DataWatcher.a((Class)EntityZombieVillagerPet.class, DataWatcherRegistry.b);
    }
    
    public EntityZombieVillagerPet(final World world) {
        super(world);
    }
    
    public EntityZombieVillagerPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityZombieVillagerPet.VILLAGER_TYPE, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityZombieVillagerPet.ARMS_RAISED, (Object)false);
        this.datawatcher.register((DataWatcherObject)EntityZombieVillagerPet.CONVERTING, (Object)false);
        this.datawatcher.register((DataWatcherObject)EntityZombieVillagerPet.PROFESSION, (Object)0);
    }
    
    @Override
    public boolean isShaking() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityZombieVillagerPet.CONVERTING);
    }
    
    @Override
    public void setShaking(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityZombieVillagerPet.CONVERTING, (Object)b);
    }
    
    public GProfession getProfession() {
        return GProfession.getById((int)this.datawatcher.get((DataWatcherObject)EntityZombieVillagerPet.PROFESSION));
    }
    
    public void setProfession(final GProfession gProfession) {
        this.datawatcher.set((DataWatcherObject)EntityZombieVillagerPet.PROFESSION, (Object)gProfession.getId());
    }
    
    @Override
    public boolean isArmsRaised() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityZombieVillagerPet.ARMS_RAISED);
    }
    
    @Override
    public void setArmsRaised(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityZombieVillagerPet.ARMS_RAISED, (Object)b);
    }
}
