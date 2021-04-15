

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityIronGolemPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityIronGolem extends EntityPet
{
    public EntityIronGolem() {
        super(GEntity.IRON_GOLEM, SoundEffect.ENTITY_IRON_GOLEM_STEP);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityIronGolemPet.class;
    }
}
