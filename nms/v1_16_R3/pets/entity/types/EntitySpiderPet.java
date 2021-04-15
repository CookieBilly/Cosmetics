

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_16_R3.World;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.EntityTypes;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySpiderPet;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.EntityPet;

@EntitySize(width = 1.4f, height = 0.9f)
public class EntitySpiderPet extends EntityPet implements IEntitySpiderPet
{
    public EntitySpiderPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    public EntitySpiderPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
    }
    
    public EntitySpiderPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.SPIDER, world);
    }
    
    public EntitySpiderPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.SPIDER, world, pet);
    }
}
