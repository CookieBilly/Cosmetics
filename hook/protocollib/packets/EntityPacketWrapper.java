// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.hook.protocollib.packets;

import org.bukkit.entity.Entity;
import com.comphenix.protocol.events.PacketEvent;

public interface EntityPacketWrapper
{
    int getEntityId();
    
    Entity getEntity(final PacketEvent p0);
}
