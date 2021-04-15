

package ws.billy.CookieGadgets.nms.v1_15_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GProfession;
import net.minecraft.server.v1_15_R1.VillagerProfession;
import net.minecraft.server.v1_15_R1.VillagerType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.EntityTypes;
import net.minecraft.server.v1_15_R1.World;
import net.minecraft.server.v1_15_R1.DataWatcher;
import net.minecraft.server.v1_15_R1.DataWatcherRegistry;
import net.minecraft.server.v1_15_R1.VillagerData;
import net.minecraft.server.v1_15_R1.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityVillagerPet;
import ws.billy.CookieGadgets.nms.v1_15_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.6f, height = 1.8f)
public class EntityVillagerPet extends EntityAgeablePet implements IEntityVillagerPet
{
    private static final DataWatcherObject<Integer> PROFESSION;
    private static final DataWatcherObject<VillagerData> VILLAGER_DATA;
    
    static {
        PROFESSION = DataWatcher.a((Class)EntityVillagerPet.class, DataWatcherRegistry.b);
        VILLAGER_DATA = DataWatcher.a((Class)EntityVillagerPet.class, DataWatcherRegistry.q);
    }
    
    public EntityVillagerPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.VILLAGER, world);
    }
    
    public EntityVillagerPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.VILLAGER, world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.register((DataWatcherObject)EntityVillagerPet.PROFESSION, (Object)0);
        this.datawatcher.register((DataWatcherObject)EntityVillagerPet.VILLAGER_DATA, (Object)new VillagerData(VillagerType.PLAINS, VillagerProfession.NONE, 0));
    }
    
    @Override
    public GProfession getProfession() {
        return GProfession.getByName(((VillagerData)this.datawatcher.get((DataWatcherObject)EntityVillagerPet.VILLAGER_DATA)).getProfession().toString());
    }
    
    @Override
    public void setProfession(final GProfession gProfession) {
        this.datawatcher.set((DataWatcherObject)EntityVillagerPet.PROFESSION, (Object)gProfession.getId());
        this.datawatcher.set((DataWatcherObject)EntityVillagerPet.VILLAGER_DATA, (Object)new VillagerData(VillagerType.PLAINS, this.getProfesstion(gProfession), 0));
    }
    
    public VillagerData getVillagerData() {
        return (VillagerData)this.datawatcher.get((DataWatcherObject)EntityVillagerPet.VILLAGER_DATA);
    }
    
    private VillagerProfession getProfesstion(final GProfession gProfession) {
        final VillagerProfession none = VillagerProfession.NONE;
        VillagerProfession villagerProfession = null;
        switch (gProfession) {
            case NORMAL: {
                villagerProfession = VillagerProfession.NONE;
                break;
            }
            case FARMER: {
                villagerProfession = VillagerProfession.FARMER;
                break;
            }
            case LIBRARIAN: {
                villagerProfession = VillagerProfession.LIBRARIAN;
                break;
            }
            case PRIEST: {
                villagerProfession = VillagerProfession.SHEPHERD;
                break;
            }
            case BLACKSMITH: {
                villagerProfession = VillagerProfession.ARMORER;
                break;
            }
            case BUTCHER: {
                villagerProfession = VillagerProfession.BUTCHER;
                break;
            }
            case NITWIT: {
                villagerProfession = VillagerProfession.NITWIT;
                break;
            }
            default: {
                villagerProfession = VillagerProfession.NONE;
                break;
            }
        }
        return villagerProfession;
    }
}
