

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityPandaPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.ServerVersion;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityPanda extends EntityPet
{
    public EntityPanda() {
        super(GEntity.PANDA, SoundEffect.ENTITY_PANDA_AMBIENT);
        super.addExtraFollowDistance(2.0);
    }
    
    @Override
    public ServerVersion getRequiredVersion() {
        return ServerVersion.v1_14_R1;
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityPandaPet.class;
    }
}
