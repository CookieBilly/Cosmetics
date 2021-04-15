

package ws.billy.CookieGadgets.nms.v1_8_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GProfession;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityVillagerPet;
import ws.billy.CookieGadgets.nms.v1_8_R1.pets.entity.EntityAgeablePet;

@EntitySize(width = 0.6f, height = 1.8f)
public class EntityVillagerPet extends EntityAgeablePet implements IEntityVillagerPet
{
    public EntityVillagerPet(final World world) {
        super(world);
    }
    
    public EntityVillagerPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(16, (Object)0);
    }
    
    @Override
    public GProfession getProfession() {
        return GProfession.getById(this.datawatcher.getInt(16));
    }
    
    @Override
    public void setProfession(final GProfession gProfession) {
        this.datawatcher.watch(16, (Object)gProfession.getId());
    }
}
