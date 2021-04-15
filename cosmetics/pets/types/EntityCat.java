

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityCatPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.ServerVersion;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityCat extends EntityPet
{
    public EntityCat() {
        super(GEntity.CAT, SoundEffect.ENTITY_CAT_AMBIENT);
    }
    
    @Override
    public ServerVersion getRequiredVersion() {
        return ServerVersion.v1_14_R1;
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityCatPet.class;
    }
}
