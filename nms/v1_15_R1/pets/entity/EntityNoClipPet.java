// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.nms.v1_15_R1.pets.entity;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_15_R1.World;
import net.minecraft.server.v1_15_R1.EntityInsentient;
import net.minecraft.server.v1_15_R1.EntityTypes;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityNoClipPet;

public abstract class EntityNoClipPet extends EntityPet implements IEntityNoClipPet
{
    public EntityNoClipPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world, final IPet pet) {
        super(entityTypes, world, pet);
    }
    
    public EntityNoClipPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world) {
        super(entityTypes, world);
    }
    
    @Override
    public void noClip(final boolean noclip) {
        this.noclip = noclip;
    }
}
