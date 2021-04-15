

package ws.billy.CookieGadgets.nms.v1_11_R1.pets.entity;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import net.minecraft.server.v1_11_R1.World;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityNoClipPet;

public abstract class EntityNoClipPet extends EntityPet implements IEntityNoClipPet
{
    public EntityNoClipPet(final World world, final IPet pet) {
        super(world, pet);
    }
    
    public EntityNoClipPet(final World world) {
        super(world);
    }
    
    @Override
    public void noClip(final boolean noclip) {
        this.noclip = noclip;
    }
}
