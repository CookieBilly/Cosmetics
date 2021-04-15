

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityPolarBearPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.ServerVersion;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityPolarBear extends EntityPet
{
    public EntityPolarBear() {
        super(GEntity.POLAR_BEAR, SoundEffect.ENTITY_POLAR_BEAR_AMBIENT);
        super.addExtraFollowDistance(2.0);
    }
    
    @Override
    public ServerVersion getRequiredVersion() {
        return ServerVersion.v1_10_R1;
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityPolarBearPet.class;
    }
}
