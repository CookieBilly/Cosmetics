

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySkeletonPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntitySkeleton extends EntityPet
{
    public EntitySkeleton() {
        super(GEntity.SKELETON, SoundEffect.ENTITY_SKELETON_AMBIENT);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntitySkeletonPet.class;
    }
}
