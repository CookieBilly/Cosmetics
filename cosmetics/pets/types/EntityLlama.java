

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityLlamaPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.ServerVersion;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityLlama extends EntityPet
{
    public EntityLlama() {
        super(GEntity.LLAMA, SoundEffect.ENTITY_LLAMA_AMBIENT);
    }
    
    @Override
    public ServerVersion getRequiredVersion() {
        return ServerVersion.v1_11_R1;
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityLlamaPet.class;
    }
}
