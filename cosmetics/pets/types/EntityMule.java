

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityMulePet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityMule extends EntityPet
{
    public EntityMule() {
        super(GEntity.MULE, SoundEffect.ENTITY_MULE_AMBIENT);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityMulePet.class;
    }
}
