

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityVindicatorPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.ServerVersion;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityVindicator extends EntityPet
{
    public EntityVindicator() {
        super(GEntity.VINDICATOR, SoundEffect.ENTITY_VINDICATOR_AMBIENT);
    }
    
    @Override
    public ServerVersion getRequiredVersion() {
        return ServerVersion.v1_11_R1;
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityVindicatorPet.class;
    }
}
