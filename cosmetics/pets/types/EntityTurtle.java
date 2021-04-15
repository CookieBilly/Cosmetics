

package ws.billy.CookieGadgets.cosmetics.pets.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityTurtlePet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.ServerVersion;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.cosmetics.pets.EntityPet;

public class EntityTurtle extends EntityPet
{
    public EntityTurtle() {
        super(GEntity.TURTLE, SoundEffect.ENTITY_TURTLE_AMBIENT_LAND);
    }
    
    @Override
    public ServerVersion getRequiredVersion() {
        return ServerVersion.v1_13_R1;
    }
    
    @Override
    public Class<? extends IEntityPet> getEntityClass() {
        return IEntityTurtlePet.class;
    }
}
