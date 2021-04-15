

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityOcelotPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityOcelot extends EntityPet
{
    public EntityOcelot() {
        super(GEntity.OCELOT, VersionManager.is1_14OrAbove() ? SoundEffect.ENTITY_OCELOT_AMBIENT : SoundEffect.ENTITY_CAT_AMBIENT);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityOcelotPet.class;
    }
}
