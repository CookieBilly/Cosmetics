

package ws.billy.CookieGadgets.hook.protocollib.packets;

import java.lang.reflect.InvocationTargetException;
import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.entity.Player;
import com.google.common.base.Objects;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

public abstract class AbstractPacket
{
    protected PacketContainer handle;
    
    protected AbstractPacket(final PacketContainer handle, final PacketType obj) {
        if (handle == null) {
            throw new IllegalArgumentException("Packet handle cannot be NULL.");
        }
        if (!Objects.equal((Object)handle.getType(), (Object)obj)) {
            throw new IllegalArgumentException(handle.getHandle() + " is not a packet of type " + obj);
        }
        this.handle = handle;
    }
    
    public PacketContainer getHandle() {
        return this.handle;
    }
    
    public void sendPacket(final Player player) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, this.getHandle());
        }
        catch (InvocationTargetException cause) {
            throw new RuntimeException("Cannot send packet.", cause);
        }
    }
    
    public void recievePacket(final Player player) {
        try {
            ProtocolLibrary.getProtocolManager().recieveClientPacket(player, this.getHandle());
        }
        catch (Exception cause) {
            throw new RuntimeException("Cannot recieve packet.", cause);
        }
    }
}
