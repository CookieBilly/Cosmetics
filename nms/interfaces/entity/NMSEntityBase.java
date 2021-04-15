

package ws.billy.CookieGadgets.nms.interfaces.entity;

import ws.billy.CookieGadgets.holograms.CraftHologram;
import org.bukkit.entity.Entity;
import org.bukkit.Location;

public interface NMSEntityBase
{
    void setLockTick(final boolean p0);
    
    Location getLocationNMS();
    
    void setLocationNMS(final double p0, final double p1, final double p2);
    
    boolean isDeadNMS();
    
    void killEntityNMS();
    
    int getIdNMS();
    
    Entity getBukkitEntityNMS();
    
    CraftHologram getHologram();
}
