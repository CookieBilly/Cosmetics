

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySkeletonHorsePet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntitySkeletonHorse extends EntityPet
{
    public EntitySkeletonHorse() {
        super(GEntity.SKELETON_HORSE, SoundEffect.ENTITY_SKELETON_HORSE_AMBIENT);
        super.addExtraFollowDistance(1.0);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntitySkeletonHorsePet.class;
    }
}
