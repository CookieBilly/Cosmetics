

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GRabbitType;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IAgeablePet;

public interface IEntityRabbitPet extends IAgeablePet
{
    GRabbitType getRabbitType();
    
    void setRabbitType(final GRabbitType p0);
}
