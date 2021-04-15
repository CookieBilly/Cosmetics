

package ws.billy.CookieGadgets.nms.v1_10_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GProfession;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_10_R1.World;
import net.minecraft.server.v1_10_R1.DataWatcher;
import net.minecraft.server.v1_10_R1.DataWatcherRegistry;
import net.minecraft.server.v1_10_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityVillagerPet;
import ws.billy.CookieGadgets.nms.v1_10_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.6f, height = 1.8f)
public class EntityVillagerPet extends EntityAgeablePet implements IEntityVillagerPet
{
    private static final DataWatcherObject<Integer> PROFESSION;
    
    static {
        PROFESSION = DataWatcher.a((Class)EntityVillagerPet.class, DataWatcherRegistry.b);
    }
    
    public EntityVillagerPet(final World world) {
        super(world);
    }
    
    public EntityVillagerPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityVillagerPet.PROFESSION, (Object)GProfession.FARMER.getId());
    }
    
    @Override
    public GProfession getProfession() {
        return GProfession.getById((int)this.datawatcher.get((DataWatcherObject)EntityVillagerPet.PROFESSION));
    }
    
    @Override
    public void setProfession(final GProfession gProfession) {
        this.datawatcher.set((DataWatcherObject)EntityVillagerPet.PROFESSION, (Object)gProfession.getId());
    }
}
