

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity;

import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import ws.billy.CookieGadgets.nms.interfaces.pets.NMSPet;
import org.bukkit.entity.Entity;

public interface IEntityPet
{
    IPet getPet();
    
    Entity getEntity();
    
    NMSPet getEntityNMS();
    
    GEntity getGEntity();
    
    void setEntitySize(final float p0, final float p1);
}
