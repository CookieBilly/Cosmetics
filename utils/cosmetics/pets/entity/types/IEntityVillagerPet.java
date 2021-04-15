

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types;

import ws.billy.CookieGadgets.utils.cosmetics.pets.utils.GProfession;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IAgeablePet;

public interface IEntityVillagerPet extends IAgeablePet
{
    default GProfession getProfession() {
        return GProfession.NORMAL;
    }
    
    default void setProfession(final GProfession profession) {
    }
}
