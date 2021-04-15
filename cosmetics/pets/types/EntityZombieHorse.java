

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityZombieHorsePet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityZombieHorse extends EntityPet
{
    public EntityZombieHorse() {
        super(GEntity.ZOMBIE_HORSE, SoundEffect.ENTITY_ZOMBIE_HORSE_AMBIENT);
        super.addExtraFollowDistance(1.0);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityZombieHorsePet.class;
    }
}
