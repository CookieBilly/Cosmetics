

package ws.billy.CookieGadgets.nms.interfaces.pets;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.EnumArmorType;

public interface NMSPet
{
    void setCustomNameNMS(final String p0);
    
    String getCustomNameNMS();
    
    void setOwnerRidePetNMS();
    
    void removePassengerNMS();
    
    void setAsHatNMS();
    
    void removeHatNMS();
    
    boolean isDeadNMS();
    
    void killEntityNMS();
    
    void setSilentNMS(final boolean p0);
    
    boolean isSilentNMS();
    
    void setEquipmentNMS(final EnumArmorType p0, final ItemStack p1);
    
    void teleportNMS(final Location p0);
}
