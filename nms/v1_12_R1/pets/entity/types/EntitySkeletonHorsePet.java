// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_12_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySkeletonHorsePet;
import ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity.sub.EntityHorseChestedAbstractPet;

@EntitySize(width = 1.4f, height = 1.6f)
public class EntitySkeletonHorsePet extends EntityHorseChestedAbstractPet implements IEntitySkeletonHorsePet
{
    public EntitySkeletonHorsePet(final World world) {
        super(world);
    }
    
    public EntitySkeletonHorsePet(final World world, final IPet pet) {
        super(world, pet);
    }
}
