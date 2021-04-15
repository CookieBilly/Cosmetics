

package ws.billy.CookieGadgets.hook.protocollib.packets;

import java.util.Collection;
import com.google.common.primitives.Ints;
import java.util.List;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.PacketType;

public class WrapperPlayServerEntityDestroy extends AbstractPacket
{
    public static final PacketType TYPE;
    
    static {
        TYPE = PacketType.Play.Server.ENTITY_DESTROY;
    }
    
    public WrapperPlayServerEntityDestroy() {
        super(new PacketContainer(WrapperPlayServerEntityDestroy.TYPE), WrapperPlayServerEntityDestroy.TYPE);
        this.handle.getModifier().writeDefaults();
    }
    
    public WrapperPlayServerEntityDestroy(final PacketContainer packetContainer) {
        super(packetContainer, WrapperPlayServerEntityDestroy.TYPE);
    }
    
    public List<Integer> getEntities() {
        return (List<Integer>)Ints.asList((int[])this.handle.getIntegerArrays().read(0));
    }
    
    public void setEntities(final int[] array) {
        this.handle.getIntegerArrays().write(0, (Object)array);
    }
    
    public void setEntities(final List<Integer> list) {
        this.setEntities(Ints.toArray((Collection)list));
    }
}
