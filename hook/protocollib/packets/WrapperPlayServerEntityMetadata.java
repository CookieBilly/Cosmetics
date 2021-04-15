

package ws.billy.CookieGadgets.hook.protocollib.packets;

import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import java.util.List;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Entity;
import org.bukkit.World;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.PacketType;

public class WrapperPlayServerEntityMetadata extends AbstractPacket implements EntityPacketWrapper
{
    public static final PacketType TYPE;
    
    static {
        TYPE = PacketType.Play.Server.ENTITY_METADATA;
    }
    
    public WrapperPlayServerEntityMetadata() {
        super(new PacketContainer(WrapperPlayServerEntityMetadata.TYPE), WrapperPlayServerEntityMetadata.TYPE);
        this.handle.getModifier().writeDefaults();
    }
    
    public WrapperPlayServerEntityMetadata(final PacketContainer packetContainer) {
        super(packetContainer, WrapperPlayServerEntityMetadata.TYPE);
    }
    
    @Override
    public int getEntityId() {
        return (int)this.handle.getIntegers().read(0);
    }
    
    public void setEntityId(final int i) {
        this.handle.getIntegers().write(0, (Object)i);
    }
    
    public Entity getEntity(final World world) {
        return (Entity)this.handle.getEntityModifier(world).read(0);
    }
    
    @Override
    public Entity getEntity(final PacketEvent packetEvent) {
        return this.getEntity(packetEvent.getPlayer().getWorld());
    }
    
    public List<WrappedWatchableObject> getEntityMetadata() {
        return (List<WrappedWatchableObject>)this.handle.getWatchableCollectionModifier().read(0);
    }
    
    public void setEntityMetadata(final List<WrappedWatchableObject> list) {
        this.handle.getWatchableCollectionModifier().write(0, (Object)list);
    }
}
