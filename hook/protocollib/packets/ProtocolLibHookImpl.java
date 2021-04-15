

package ws.billy.CookieGadgets.hook.protocollib.packets;

import ws.billy.CookieGadgets.nms.interfaces.entity.NMSEntityBase;
import org.bukkit.entity.EntityType;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSArmorStand;
import java.util.ArrayList;
import com.comphenix.protocol.events.PacketListener;
import java.util.List;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.holograms.CraftHologram;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVaultManager;
import ws.billy.CookieGadgets.CookieGadgets;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import java.util.Optional;
import com.comphenix.net.sf.cglib.proxy.Factory;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.utility.MinecraftReflection;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.plugin.Plugin;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import ws.billy.CookieGadgets.nms.NMSManager;
import ws.billy.CookieGadgets.hook.protocollib.ProtocolLibHook;

public class ProtocolLibHookImpl implements ProtocolLibHook
{
    private NMSManager nmsManager;
    private WrappedDataWatcher.Serializer byteSerializer;
    private WrappedDataWatcher.Serializer stringSerializer;
    private WrappedDataWatcher.Serializer booleanSerializer;
    private WrappedDataWatcher.Serializer chatComponentSerializer;
    private int customNameWatcherIndex;
    
    @Override
    public boolean hook(final Plugin plugin, final NMSManager nmsManager) {
        this.nmsManager = nmsManager;
        this.customNameWatcherIndex = 2;
        if (VersionManager.is1_9OrAbove()) {
            this.byteSerializer = WrappedDataWatcher.Registry.get((Class)Byte.class);
            this.stringSerializer = WrappedDataWatcher.Registry.get((Class)String.class);
            this.booleanSerializer = WrappedDataWatcher.Registry.get((Class)Boolean.class);
        }
        if (VersionManager.is1_13OrAbove()) {
            this.chatComponentSerializer = WrappedDataWatcher.Registry.get(MinecraftReflection.getIChatBaseComponentClass(), true);
        }
        ProtocolLibrary.getProtocolManager().addPacketListener((PacketListener)new PacketAdapter(PacketAdapter.params().plugin(plugin).types(new PacketType[] { PacketType.Play.Server.SPAWN_ENTITY_LIVING, PacketType.Play.Server.SPAWN_ENTITY, PacketType.Play.Server.ENTITY_METADATA }).serverSide().listenerPriority(ListenerPriority.NORMAL)) {
            public void onPacketSending(final PacketEvent packetEvent) {
                final PacketContainer packet = packetEvent.getPacket();
                final Player player = packetEvent.getPlayer();
                if (player instanceof Factory) {
                    return;
                }
                if (packet.getType() == PacketType.Play.Server.SPAWN_ENTITY_LIVING) {
                    final WrapperPlayServerSpawnEntityLiving wrapperPlayServerSpawnEntityLiving = new WrapperPlayServerSpawnEntityLiving(packet);
                    final Entity access$0 = ProtocolLibHookImpl.this.getEntity(packetEvent, wrapperPlayServerSpawnEntityLiving);
                    if (access$0 == null || !ProtocolLibHookImpl.this.isHologram(access$0.getType())) {
                        return;
                    }
                    final CraftHologram access$2 = ProtocolLibHookImpl.this.getHologram(access$0);
                    if (access$2 == null) {
                        return;
                    }
                    if (!access$2.getVisibilityManager().isVisibleTo(player)) {
                        packetEvent.setCancelled(true);
                        return;
                    }
                    if (VersionManager.is1_15OrAbove()) {
                        return;
                    }
                    final WrappedWatchableObject watchableObject = wrapperPlayServerSpawnEntityLiving.getMetadata().getWatchableObject(ProtocolLibHookImpl.this.customNameWatcherIndex);
                    if (watchableObject == null || !(watchableObject.getValue() instanceof String)) {
                        return;
                    }
                    final Object value = watchableObject.getValue();
                    String json;
                    if (VersionManager.is1_13OrAbove()) {
                        if (!(value instanceof Optional)) {
                            return;
                        }
                        final Optional<Object> optional = (Optional<Object>)value;
                        if (!optional.isPresent()) {
                            return;
                        }
                        json = WrappedChatComponent.fromHandle(optional.get()).getJson();
                    }
                    else {
                        if (!(value instanceof String)) {
                            return;
                        }
                        json = (String)value;
                    }
                    if (json.contains("{MYSTERY_BOXES}") || json.contains("{MYSTERY_DUST}")) {
                        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
                        if (playerManager == null) {
                            return;
                        }
                        final String replace = json.replace("{MYSTERY_BOXES}", String.valueOf(playerManager.getMysteryBoxes())).replace("{MYSTERY_DUST}", String.valueOf(playerManager.getMysteryDust()));
                        if (playerManager.getMysteryBoxes() == 0) {
                            if (VersionManager.is1_13OrAbove()) {
                                new StringBuilder("{\"text\":\"").append(MysteryVaultManager.getZeroMysteryBoxAvailableHologram()).append("\"}").toString();
                            }
                            else {
                                MysteryVaultManager.getZeroMysteryBoxAvailableHologram();
                            }
                        }
                        if (!playerManager.isLoaded() || playerManager.isOpeningMysteryBox()) {
                            ChatUtil.format("&c");
                        }
                        WrappedWatchableObject wrappedWatchableObject;
                        if (VersionManager.is1_13OrAbove()) {
                            wrappedWatchableObject = new WrappedWatchableObject(watchableObject.getWatcherObject(), (Object)Optional.of(WrappedChatComponent.fromJson(replace).getHandle()));
                        }
                        else if (VersionManager.is1_9OrAbove()) {
                            wrappedWatchableObject = new WrappedWatchableObject(watchableObject.getWatcherObject(), (Object)replace);
                        }
                        else {
                            wrappedWatchableObject = new WrappedWatchableObject(watchableObject.getIndex(), (Object)replace);
                        }
                        watchableObject.setValue(wrappedWatchableObject.getValue());
                    }
                }
                else if (packet.getType() == PacketType.Play.Server.SPAWN_ENTITY) {
                    final Entity access$3 = ProtocolLibHookImpl.this.getEntity(packetEvent, new WrapperPlayServerSpawnEntity(packet));
                    if (access$3 == null || !ProtocolLibHookImpl.this.isHologram(access$3.getType())) {
                        return;
                    }
                    final CraftHologram access$4 = ProtocolLibHookImpl.this.getHologram(access$3);
                    if (access$4 == null) {
                        return;
                    }
                    if (!access$4.getVisibilityManager().isVisibleTo(player)) {
                        packetEvent.setCancelled(true);
                    }
                }
                else if (packet.getType() == PacketType.Play.Server.ENTITY_METADATA) {
                    final WrapperPlayServerEntityMetadata wrapperPlayServerEntityMetadata = new WrapperPlayServerEntityMetadata(packet);
                    final Entity access$5 = ProtocolLibHookImpl.this.getEntity(packetEvent, wrapperPlayServerEntityMetadata);
                    if (access$5 == null || !ProtocolLibHookImpl.this.isHologram(access$5.getType())) {
                        return;
                    }
                    final CraftHologram access$6 = ProtocolLibHookImpl.this.getHologram(access$5);
                    if (access$6 == null) {
                        return;
                    }
                    if (!access$6.getVisibilityManager().isVisibleTo(player)) {
                        packetEvent.setCancelled(true);
                        return;
                    }
                    final List<WrappedWatchableObject> entityMetadata = wrapperPlayServerEntityMetadata.getEntityMetadata();
                    for (int i = 0; i < entityMetadata.size(); ++i) {
                        final WrappedWatchableObject wrappedWatchableObject2 = entityMetadata.get(i);
                        if (wrappedWatchableObject2.getIndex() == ProtocolLibHookImpl.this.customNameWatcherIndex) {
                            final Object value2 = wrappedWatchableObject2.getValue();
                            String json2;
                            if (VersionManager.is1_13OrAbove()) {
                                if (!(value2 instanceof Optional)) {
                                    return;
                                }
                                final Optional<Object> optional2 = (Optional<Object>)value2;
                                if (!optional2.isPresent()) {
                                    return;
                                }
                                json2 = WrappedChatComponent.fromHandle(optional2.get()).getJson();
                            }
                            else {
                                if (!(value2 instanceof String)) {
                                    return;
                                }
                                json2 = (String)value2;
                            }
                            if (json2.contains("{MYSTERY_BOXES}") || json2.contains("{MYSTERY_DUST}")) {
                                final PlayerManager playerManager2 = CookieGadgets.getPlayerManager(player);
                                if (playerManager2 == null) {
                                    return;
                                }
                                String s = json2.replace("{MYSTERY_BOXES}", String.valueOf(playerManager2.getMysteryBoxes())).replace("{MYSTERY_DUST}", String.valueOf(playerManager2.getMysteryDust()));
                                if (playerManager2.getMysteryBoxes() == 0) {
                                    if (VersionManager.is1_13OrAbove()) {
                                        s = "{\"text\":\"" + MysteryVaultManager.getZeroMysteryBoxAvailableHologram() + "\"}";
                                    }
                                    else {
                                        s = MysteryVaultManager.getZeroMysteryBoxAvailableHologram();
                                    }
                                }
                                if (!playerManager2.isLoaded() || playerManager2.isOpeningMysteryBox()) {
                                    s = ChatUtil.format("&c");
                                }
                                WrappedWatchableObject wrappedWatchableObject3;
                                if (VersionManager.is1_13OrAbove()) {
                                    wrappedWatchableObject3 = new WrappedWatchableObject(wrappedWatchableObject2.getWatcherObject(), (Object)Optional.of(WrappedChatComponent.fromJson(s).getHandle()));
                                }
                                else if (VersionManager.is1_9OrAbove()) {
                                    wrappedWatchableObject3 = new WrappedWatchableObject(wrappedWatchableObject2.getWatcherObject(), (Object)s);
                                }
                                else {
                                    wrappedWatchableObject3 = new WrappedWatchableObject(wrappedWatchableObject2.getIndex(), (Object)s);
                                }
                                entityMetadata.set(i, wrappedWatchableObject3);
                                final PacketContainer shallowClone = packet.shallowClone();
                                shallowClone.getWatchableCollectionModifier().write(0, (Object)entityMetadata);
                                packetEvent.setPacket(shallowClone);
                                return;
                            }
                        }
                    }
                }
            }
        });
        return true;
    }
    
    @Override
    public void sendDestroyEntitiesPacket(final Player player, final CraftHologram craftHologram) {
        final ArrayList<Integer> entities = new ArrayList<Integer>();
        entities.add(craftHologram.getNMSNameable().getIdNMS());
        if (!entities.isEmpty()) {
            final WrapperPlayServerEntityDestroy wrapperPlayServerEntityDestroy = new WrapperPlayServerEntityDestroy();
            wrapperPlayServerEntityDestroy.setEntities(entities);
            wrapperPlayServerEntityDestroy.sendPacket(player);
        }
    }
    
    @Override
    public void sendCreateEntitiesPacket(final Player player, final CraftHologram craftHologram) {
        if (!craftHologram.getNMSNameable().isDeadNMS() || !craftHologram.getVisibilityManager().isVisibleTo(player)) {
            this.sendSpawnArmorStandPacket(player, (NMSArmorStand)craftHologram.getNMSNameable());
        }
    }
    
    private void sendSpawnArmorStandPacket(final Player player, final NMSArmorStand nmsArmorStand) {
        new WrapperPlayServerSpawnEntity(nmsArmorStand.getBukkitEntityNMS(), 78, 1).sendPacket(player);
        final WrapperPlayServerEntityMetadata wrapperPlayServerEntityMetadata = new WrapperPlayServerEntityMetadata();
        final WrappedDataWatcher wrappedDataWatcher = new WrappedDataWatcher();
        wrappedDataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(0, this.byteSerializer), (Object)32);
        final String customNameNMS = nmsArmorStand.getCustomNameNMS();
        if (customNameNMS != null && !customNameNMS.isEmpty()) {
            if (VersionManager.is1_13OrAbove()) {
                wrappedDataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(this.customNameWatcherIndex, this.chatComponentSerializer), (Object)Optional.of(WrappedChatComponent.fromText(customNameNMS).getHandle()));
            }
            else {
                wrappedDataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(this.customNameWatcherIndex, this.stringSerializer), (Object)customNameNMS);
            }
            wrappedDataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(3, this.booleanSerializer), (Object)true);
        }
        wrappedDataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(5, this.booleanSerializer), (Object)true);
        wrappedDataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(11, this.byteSerializer), (Object)25);
        wrapperPlayServerEntityMetadata.setEntityMetadata(wrappedDataWatcher.getWatchableObjects());
        wrapperPlayServerEntityMetadata.setEntityId(nmsArmorStand.getIdNMS());
        wrapperPlayServerEntityMetadata.sendPacket(player);
    }
    
    private boolean isHologram(final EntityType entityType) {
        return entityType == EntityType.ARMOR_STAND;
    }
    
    private CraftHologram getHologram(final Entity entity) {
        final NMSEntityBase nmsEntityBase = this.nmsManager.getNMSEntityBase(entity);
        if (nmsEntityBase != null) {
            return nmsEntityBase.getHologram();
        }
        return null;
    }
    
    private Entity getEntity(final PacketEvent packetEvent, final EntityPacketWrapper entityPacketWrapper) {
        try {
            return entityPacketWrapper.getEntity(packetEvent);
        }
        catch (RuntimeException ex) {
            return null;
        }
    }
}
