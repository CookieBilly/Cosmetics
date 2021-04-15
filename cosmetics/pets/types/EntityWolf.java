

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWolfPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityWolf extends EntityPet
{
    public EntityWolf() {
        super(GEntity.WOLF, SoundEffect.ENTITY_WOLF_AMBIENT);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityWolfPet.class;
    }
}
