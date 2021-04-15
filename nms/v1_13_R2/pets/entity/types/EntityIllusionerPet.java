// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityIllusionerPet;

@EntitySize(width = 0.6f, height = 1.95f)
public class EntityIllusionerPet extends EntityEvokerPet implements IEntityIllusionerPet
{
    public EntityIllusionerPet(final World world) {
        super((EntityTypes<?>)EntityTypes.ILLUSIONER, world);
    }
    
    public EntityIllusionerPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.ILLUSIONER, world, pet);
    }
}
