

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityDonkeyPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityDonkey extends EntityPet
{
    public EntityDonkey() {
        super(GEntity.DONKEY, SoundEffect.ENTITY_DONKEY_AMBIENT);
        super.addExtraFollowDistance(1.0);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityDonkeyPet.class;
    }
}
