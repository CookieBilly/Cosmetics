

package ws.billy.CookieGadgets.nms.interfaces.entity;

import org.bukkit.Location;

public interface NMSEntityMiniature extends NMSArmorStand
{
    void teleportNMS(final Location p0);
    
    double getOriginalLocationYNMS();
    
    void setHeadPoseXNMS(final float p0);
    
    void setHeadPoseYNMS(final float p0);
    
    void setHeadPoseZNMS(final float p0);
    
    void setHeadPoseNMS(final float p0, final float p1, final float p2);
    
    void setBodyPoseNMS(final float p0, final float p1, final float p2);
    
    void setLeftArmPoseNMS(final float p0, final float p1, final float p2);
    
    void setLeftLegPoseNMS(final float p0, final float p1, final float p2);
    
    void setRightArmPoseNMS(final float p0, final float p1, final float p2);
    
    void setRightLegPoseNMS(final float p0, final float p1, final float p2);
}
