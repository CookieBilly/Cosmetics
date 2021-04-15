

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GProfession;
import net.minecraft.server.v1_16_R3.VillagerProfession;
import net.minecraft.server.v1_16_R3.VillagerType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.World;
import net.minecraft.server.v1_16_R3.DataWatcherSerializer;
import net.minecraft.server.v1_16_R3.DataWatcher;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.utils.DataWatcherWrapper;
import net.minecraft.server.v1_16_R3.VillagerData;
import net.minecraft.server.v1_16_R3.DataWatcherObject;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityZombieVillagerPet;

@EntitySize(width = 0.6f, height = 1.95f)
public class EntityZombieVillagerPet extends EntityZombiePet implements IEntityZombieVillagerPet
{
    private static final DataWatcherObject<Boolean> CONVERTING;
    private static final DataWatcherObject<VillagerData> VILLAGER_DATA;
    
    static {
        CONVERTING = DataWatcher.a((Class)EntityZombieVillagerPet.class, (DataWatcherSerializer)DataWatcherWrapper.BOOLEAN);
        VILLAGER_DATA = DataWatcher.a((Class)EntityZombieVillagerPet.class, (DataWatcherSerializer)DataWatcherWrapper.VILLAGER_DATA);
    }
    
    public EntityZombieVillagerPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.ZOMBIE_VILLAGER, world);
    }
    
    public EntityZombieVillagerPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.ZOMBIE_VILLAGER, world, pet);
    }
    
    @Override
    protected void initDataWatcher() {
        super.initDataWatcher();
        this.datawatcher.register((DataWatcherObject)EntityZombieVillagerPet.CONVERTING, (Object)false);
        this.datawatcher.register((DataWatcherObject)EntityZombieVillagerPet.VILLAGER_DATA, (Object)new VillagerData(VillagerType.PLAINS, VillagerProfession.NONE, 0));
    }
    
    @Override
    public boolean isShaking() {
        return (boolean)this.datawatcher.get((DataWatcherObject)EntityZombieVillagerPet.CONVERTING);
    }
    
    @Override
    public void setShaking(final boolean b) {
        this.datawatcher.set((DataWatcherObject)EntityZombieVillagerPet.CONVERTING, (Object)b);
    }
    
    @Override
    public GProfession getProfession() {
        return GProfession.getByName(((VillagerData)this.datawatcher.get((DataWatcherObject)EntityZombieVillagerPet.VILLAGER_DATA)).getProfession().toString());
    }
    
    @Override
    public void setProfession(final GProfession gProfession) {
        this.datawatcher.set((DataWatcherObject)EntityZombieVillagerPet.VILLAGER_DATA, (Object)new VillagerData(VillagerType.PLAINS, this.getProfesstion(gProfession), 0));
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
