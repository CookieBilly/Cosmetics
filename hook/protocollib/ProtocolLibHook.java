

package ws.billy.CookieGadgets.hook.protocollib;

import ws.billy.CookieGadgets.holograms.CraftHologram;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.nms.NMSManager;
import org.bukkit.plugin.Plugin;

public interface ProtocolLibHook
{
    boolean hook(final Plugin p0, final NMSManager p1);
    
    void sendDestroyEntitiesPacket(final Player p0, final CraftHologram p1);
    
    void sendCreateEntitiesPacket(final Player p0, final CraftHologram p1);
}
