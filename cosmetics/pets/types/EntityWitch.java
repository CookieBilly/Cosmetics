

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityWitchPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityWitch extends EntityPet
{
    public EntityWitch() {
        super(GEntity.WITCH, SoundEffect.ENTITY_WITCH_AMBIENT);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityWitchPet.class;
    }
}
