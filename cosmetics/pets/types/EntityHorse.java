

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityHorsePet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityHorse extends EntityPet
{
    public EntityHorse() {
        super(GEntity.HORSE, SoundEffect.ENTITY_HORSE_AMBIENT);
        super.addExtraFollowDistance(1.0);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityHorsePet.class;
    }
}
