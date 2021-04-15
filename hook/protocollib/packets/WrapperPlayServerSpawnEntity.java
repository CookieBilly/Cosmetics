

package ws.billy.CookieGadgets.hook.protocollib.packets;

import com.comphenix.protocol.reflect.IntEnum;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.World;
import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.entity.Entity;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.injector.PacketConstructor;
import com.comphenix.protocol.PacketType;

public class WrapperPlayServerSpawnEntity extends AbstractPacket implements EntityPacketWrapper
{
    public static final PacketType TYPE;
    private static PacketConstructor entityConstructor;
    
    static {
        TYPE = PacketType.Play.Server.SPAWN_ENTITY;
    }
    
    public WrapperPlayServerSpawnEntity() {
        super(new PacketContainer(WrapperPlayServerSpawnEntity.TYPE), WrapperPlayServerSpawnEntity.TYPE);
        this.handle.getModifier().writeDefaults();
    }
    
    public WrapperPlayServerSpawnEntity(final PacketContainer packetContainer) {
        super(packetContainer, WrapperPlayServerSpawnEntity.TYPE);
    }
    
    public WrapperPlayServerSpawnEntity(final Entity entity, final int n, final int n2) {
        super(fromEntity(entity, n, n2), WrapperPlayServerSpawnEntity.TYPE);
    }
    
    private static PacketContainer fromEntity(final Entity entity, final int n, final int n2) {
        if (WrapperPlayServerSpawnEntity.entityConstructor == null) {
            WrapperPlayServerSpawnEntity.entityConstructor = ProtocolLibrary.getProtocolManager().createPacketConstructor(WrapperPlayServerSpawnEntity.TYPE, new Object[] { entity, n, n2 });
        }
        return WrapperPlayServerSpawnEntity.entityConstructor.createPacket(new Object[] { entity, n, n2 });
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
    
    public int getType() {
        return (int)this.handle.getIntegers().read(6);
    }
    
    public void setType(final int i) {
        this.handle.getIntegers().write(6, (Object)i);
    }
    
    public static class ObjectTypes extends IntEnum
    {
        public static final int BOAT = 1;
        public static final int ITEM_STACK = 2;
        public static final int MINECART = 10;
        public static final int MINECART_STORAGE = 11;
        public static final int MINECART_POWERED = 12;
        public static final int ACTIVATED_TNT = 50;
        public static final int ENDER_CRYSTAL = 51;
        public static final int ARROW_PROJECTILE = 60;
        public static final int SNOWBALL_PROJECTILE = 61;
        public static final int EGG_PROJECTILE = 62;
        public static final int FIRE_BALL_GHAST = 63;
        public static final int FIRE_BALL_BLAZE = 64;
        public static final int THROWN_ENDERPEARL = 65;
        public static final int WITHER_SKULL = 66;
        public static final int FALLING_BLOCK = 70;
        public static final int ITEM_FRAME = 71;
        public static final int EYE_OF_ENDER = 72;
        public static final int THROWN_POTION = 73;
        public static final int FALLING_DRAGON_EGG = 74;
        public static final int THROWN_EXP_BOTTLE = 75;
        public static final int FIREWORK = 76;
        public static final int ARMOR_STAND = 78;
        public static final int FISHING_FLOAT = 90;
        private static ObjectTypes INSTANCE;
        
        static {
            ObjectTypes.INSTANCE = new ObjectTypes();
        }
        
        public static ObjectTypes getInstance() {
            return ObjectTypes.INSTANCE;
        }
    }
}
