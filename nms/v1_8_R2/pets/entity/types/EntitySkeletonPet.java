

package ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GSkeletonType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_8_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySkeletonPet;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.EntityPet;

@EntitySize(width = 0.6f, height = 1.9f)
public class EntitySkeletonPet extends EntityPet implements IEntitySkeletonPet
{
    public EntitySkeletonPet(final World world) {
        super(world);
    }
    
    public EntitySkeletonPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    @Override
    protected void initDatawatchers() {
        super.initDatawatchers();
        this.datawatcher.a(13, (Object)0);
    }
    
    @Override
    public GSkeletonType getSkeletonType() {
        return GSkeletonType.getById(this.datawatcher.getByte(13));
    }
    
    @Override
    public void setSkeletonType(final GSkeletonType gSkeletonType) {
        this.datawatcher.watch(13, (Object)(byte)((gSkeletonType != GSkeletonType.NORMAL) ? 1 : 0));
    }
}
