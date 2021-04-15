

package ws.billy.CookieGadgets.nms.interfaces.pets;

import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import org.bukkit.Location;

public interface ISpawnUtil
{
    void initNMSPets();
    
    IEntityPet spawnEntityPet(final Location p0, final IPet p1, final Class<? extends IEntityPet> p2);
}
