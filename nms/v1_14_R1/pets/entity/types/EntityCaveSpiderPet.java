

package ws.billy.CookieGadgets.nms.v1_14_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_14_R1.EntityInsentient;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityCaveSpiderPet;

@EntitySize(width = 0.7f, height = 0.5f)
public class EntityCaveSpiderPet extends EntitySpiderPet implements IEntityCaveSpiderPet
{
    public EntityCaveSpiderPet(final World world) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.CAVE_SPIDER, world);
    }
    
    public EntityCaveSpiderPet(final World world, final IPet pet) {
        super((EntityTypes<? extends EntityInsentient>)EntityTypes.CAVE_SPIDER, world, pet);
    }
}
