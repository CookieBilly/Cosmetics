

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityCreeperPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityCreeper extends EntityPet
{
    public EntityCreeper() {
        super(GEntity.CREEPER, SoundEffect.ENTITY_CREEPER_HURT);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityCreeperPet.class;
    }
}
