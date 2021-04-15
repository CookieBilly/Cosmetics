

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityMagmaCubePet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityMagmaCube extends EntityPet
{
    public EntityMagmaCube() {
        super(GEntity.MAGMA_CUBE, SoundEffect.ENTITY_MAGMA_CUBE_SQUISH);
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityMagmaCubePet.class;
    }
}
