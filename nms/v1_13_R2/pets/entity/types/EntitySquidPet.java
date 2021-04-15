// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySquidPet;
import ws.billy.CookieGadgets.nms.v1_13_R2.pets.entity.EntityPet;

@EntitySize(width = 0.95f, height = 0.95f)
public class EntitySquidPet extends EntityPet implements IEntitySquidPet
{
    public EntitySquidPet(final World world) {
        super((EntityTypes<?>)EntityTypes.SQUID, world);
    }
    
    public EntitySquidPet(final World world, final IPet pet) {
        super((EntityTypes<?>)EntityTypes.SQUID, world, pet);
    }
}
