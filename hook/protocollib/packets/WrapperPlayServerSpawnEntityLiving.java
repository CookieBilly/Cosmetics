

package ws.billy.CookieGadgets.hook.protocollib.packets;

import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.entity.EntityType;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.World;
import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.entity.Entity;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.injector.PacketConstructor;
import com.comphenix.protocol.PacketType;

public class WrapperPlayServerSpawnEntityLiving extends AbstractPacket implements EntityPacketWrapper
{
    public static final PacketType TYPE;
    private static PacketConstructor entityConstructor;
    
    static {
        TYPE = PacketType.Play.Server.SPAWN_ENTITY_LIVING;
    }
    
    public WrapperPlayServerSpawnEntityLiving() {
        super(new PacketContainer(WrapperPlayServerSpawnEntityLiving.TYPE), WrapperPlayServerSpawnEntityLiving.TYPE);
        this.handle.getModifier().writeDefaults();
    }
    
    public WrapperPlayServerSpawnEntityLiving(final PacketContainer packetContainer) {
        super(packetContainer, WrapperPlayServerSpawnEntityLiving.TYPE);
    }
    
    public WrapperPlayServerSpawnEntityLiving(final Entity entity) {
        super(fromEntity(entity), WrapperPlayServerSpawnEntityLiving.TYPE);
    }
    
    private static PacketContainer fromEntity(final Entity entity) {
        if (WrapperPlayServerSpawnEntityLiving.entityConstructor == null) {
            WrapperPlayServerSpawnEntityLiving.entityConstructor = ProtocolLibrary.getProtocolManager().createPacketConstructor(WrapperPlayServerSpawnEntityLiving.TYPE, new Object[] { entity });
        }
        return WrapperPlayServerSpawnEntityLiving.entityConstructor.createPacket(new Object[] { entity });
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
    
    public EntityType getType() {
        return EntityType.fromId((int)this.handle.getIntegers().read(1));
    }
    
    public void setType(final EntityType entityType) {
        this.handle.getIntegers().write(1, (Object)(int)entityType.getTypeId());
    }
    
    public WrappedDataWatcher getMetadata() {
        return (WrappedDataWatcher)this.handle.getDataWatcherModifier().read(0);
    }
    
    public void setMetadata(final WrappedDataWatcher wrappedDataWatcher) {
        this.handle.getDataWatcherModifier().write(0, (Object)wrappedDataWatcher);
    }
}
