// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_12_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySilverfishPet;
import ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.EntityPet;

@EntitySize(width = 0.3f, height = 0.7f)
public class EntitySilverfishPet extends EntityPet implements IEntitySilverfishPet
{
    public EntitySilverfishPet(final World world) {
        super(world);
    }
    
    public EntitySilverfishPet(final World world, final IPet pet) {
        super(world, pet);
    }
}
