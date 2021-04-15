

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntitySlimePet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntitySlime extends EntityPet
{
    public EntitySlime() {
        super(GEntity.SLIME, SoundEffect.ENTITY_SLIME_SQUISH);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntitySlimePet.class;
    }
}
