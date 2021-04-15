

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityStrayPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.ServerVersion;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityStray extends EntityPet
{
    public EntityStray() {
        super(GEntity.STRAY, SoundEffect.ENTITY_SKELETON_AMBIENT);
    }
    
    @Override
    public ServerVersion getRequiredVersion() {
        return ServerVersion.v1_10_R1;
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityStrayPet.class;
    }
}
