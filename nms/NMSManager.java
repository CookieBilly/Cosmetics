

package ws.billy.CookieGadgets.nms;

import ws.billy.CookieGadgets.log.LoggerManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bukkit.Material;
import java.util.List;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSEntityMiniature;
import ws.billy.CookieGadgets.utils.cosmetics.miniatures.MiniatureEquipments;
import ws.billy.CookieGadgets.nms.interfaces.ArmorStandSlot;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSEntityBase;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSArmorStand;
import org.bukkit.World;
import ws.billy.CookieGadgets.utils.EnumPotion;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.GMaterial;
import java.util.UUID;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.nms.interfaces.IJSONMessage;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import org.spigotmc.event.entity.EntityDismountEvent;
import java.util.Iterator;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.nms.interfaces.pets.ISpawnUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import java.lang.reflect.InvocationTargetException;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.inventory.ItemStack;

public interface NMSManager
{
    default ItemStack spawnEgg(final ItemStack item, final String name) {
        if (!VersionManager.is1_9OrAbove()) {
            return item;
        }
        String id = name;
        if (VersionManager.is1_11OrAbove()) {
            id = "minecraft:" + name.toLowerCase();
        }
        try {
            final Object itemStack = ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asNMSCopy", item);
            Object tag = ReflectionUtils.invokeMethod(itemStack, itemStack.getClass(), "getTag", new Object[0]);
            if (tag == null) {
                tag = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagCompound"), (Class<?>[])new Class[0]).newInstance(new Object[0]);
            }
            final Object nbtTagCompound = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagCompound"), (Class<?>[])new Class[0]).newInstance(new Object[0]);
            ReflectionUtils.invokeMethod(nbtTagCompound, "setString", "id", id);
            ReflectionUtils.invokeMethod(tag, "set", "EntityTag", nbtTagCompound);
            ReflectionUtils.invokeMethod(itemStack, "setTag", tag);
            return (ItemStack)ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asBukkitCopy", itemStack);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | InstantiationException | ClassNotFoundException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
            return item;
        }
    }
    
    void handlePathfinders(final Player p0, final Entity p1, final double p2);
    
    void clearPathfinders(final Entity p0);
    
    default void registerPets() {
    }
    
    default void unregisterPets() {
    }
    
    ISpawnUtil getSpawnUtils();
    
    default void setPassenger(final Player player, final Player passenger) {
        player.setPassenger((Entity)passenger);
        if (!VersionManager.is1_9OrAbove()) {
            return;
        }
        try {
            final Method getHandle = ReflectionUtils.getMethod("CraftPlayer", ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY, "getHandle", (Class<?>[])new Class[0]);
            final Object packet = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutMount"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Entity")).newInstance(getHandle.invoke(player, new Object[0]));
            for (final Player p : Bukkit.getOnlinePlayers()) {
                ReflectionUtils.sendPacket(p, packet);
            }
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | SecurityException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
        }
    }
    
    default void leaveVehicle(final Player player) {
        if (!VersionManager.is1_9OrAbove()) {
            return;
        }
        if (player.getPassenger() == null) {
            return;
        }
        try {
            if (ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY.getClass("CraftPlayer").isInstance(player.getPassenger())) {
                player.getPassenger().leaveVehicle();
                final Method getHandle = ReflectionUtils.getMethod("CraftPlayer", ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY, "getHandle", (Class<?>[])new Class[0]);
                final Object packet = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutMount"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Entity")).newInstance(getHandle.invoke(player, new Object[0]));
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    ReflectionUtils.sendPacket(p, packet);
                }
            }
        }
        catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | InstantiationException | NoSuchMethodException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
        }
    }
    
    default void passengerDismount(final EntityDismountEvent event) {
        if (!VersionManager.is1_9OrAbove()) {
            return;
        }
        try {
            if (!ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY.getClass("CraftPlayer").isInstance(event.getDismounted())) {
                return;
            }
            event.getDismounted().setPassenger((Entity)null);
            Method getHandle;
            Object packet;
            final Iterator<Player> iterator;
            Player p;
            final Exception ex;
            Exception e;
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> {
                try {
                    getHandle = ReflectionUtils.getMethod("CraftPlayer", ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY, "getHandle", (Class<?>[])new Class[0]);
                    packet = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutMount"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Entity")).newInstance(getHandle.invoke(event.getDismounted(), new Object[0]));
                    Bukkit.getOnlinePlayers().iterator();
                    while (iterator.hasNext()) {
                        p = iterator.next();
                        ReflectionUtils.sendPacket(p, packet);
                    }
                }
                catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | SecurityException | InstantiationException ex2) {
                    e = ex;
                    e.printStackTrace();
                }
            }, 2L);
        }
        catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }
    
    void makeEntityPanic(final Entity p0);
    
    default IJSONMessage newJSONMessage(final String text) {
        return new JSONMessage(text);
    }
    
    default void sendActionbarMessage(final Player player, final String message) {
        Object packet = null;
        try {
            final String JSONString = this.newJSONMessage(ChatUtil.format(message)).toJSONString();
            final Object component = ReflectionUtils.invokeMethod(null, VersionManager.is1_8_R1Version() ? ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ChatSerializer") : ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("IChatBaseComponent$ChatSerializer"), "a", JSONString);
            if (VersionManager.is1_16OrAbove()) {
                packet = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutChat"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("IChatBaseComponent"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ChatMessageType"), UUID.class).newInstance(component, ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ChatMessageType"), "valueOf", "GAME_INFO"), UUID.randomUUID());
            }
            else if (VersionManager.is1_12OrAbove()) {
                packet = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutChat"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("IChatBaseComponent"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ChatMessageType")).newInstance(component, ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ChatMessageType"), "valueOf", "GAME_INFO"));
            }
            else {
                packet = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutChat"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("IChatBaseComponent"), Byte.TYPE).newInstance(component, 2);
            }
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | InstantiationException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
        }
        ReflectionUtils.sendPacket(player, packet);
    }
    
    default ItemStack getPotionFromId(final ItemStack itemStack, final GMaterial material) {
        if (VersionManager.is1_8Version()) {
            return itemStack;
        }
        if (material.getEnumMaterial() != EnumMaterial.POTION && material.getEnumMaterial() != EnumMaterial.SPLASH_POTION && material.getEnumMaterial() != EnumMaterial.LINGERING_POTION) {
            return itemStack;
        }
        final EnumPotion ePotion = EnumPotion.valueOfMaterialData(material.getData());
        return new Potion(Potion.PotionType.valueOf(ePotion.getPotionType().toString()), ePotion.isStrong(), ePotion.isExtend(), material.getEnumMaterial() == EnumMaterial.LINGERING_POTION, material.getEnumMaterial() == EnumMaterial.SPLASH_POTION).toItemStack(itemStack);
    }
    
    default ItemStack getPotionFromId(final ItemStack itemStack, final EnumMaterial material, final int data) {
        return this.getPotionFromId(itemStack, new GMaterial(material, data));
    }
    
    NMSArmorStand spawnNMSArmorStand(final World p0, final double p1, final double p2, final double p3, final String p4);
    
    boolean isNMSEntityBase(final Entity p0);
    
    NMSEntityBase getNMSEntityBase(final Entity p0);
    
    NMSArmorStand spawnNMSArmorStandFollower(final World p0, final double p1, final double p2, final double p3, final Player p4, final double p5, final String p6, final ArmorStandSlot p7, final ItemStack p8);
    
    NMSEntityMiniature spawnNMSMiniature(final World p0, final double p1, final double p2, final double p3, final boolean p4, final Player p5, final String p6, final MiniatureEquipments p7);
    
    default ItemStack createBook(final String title, final String page1, final List<String> contents) {
        final ItemStack item = new ItemStack(Material.WRITTEN_BOOK, 1);
        try {
            final Object itemStackObj = ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asNMSCopy", item);
            final Object tagCompound = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagCompound"), (Class<?>[])new Class[0]).newInstance(new Object[0]);
            ReflectionUtils.invokeMethod(tagCompound, "setString", "title", title);
            ReflectionUtils.invokeMethod(tagCompound, "setString", "author", "CookieGadgets");
            final Object tagList = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagList"), (Class<?>[])new Class[0]).newInstance(new Object[0]);
            if (VersionManager.is1_15OrAbove()) {
                ReflectionUtils.invokeMethod(tagList, "add", ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagString"), "a", page1));
                for (final String content : contents) {
                    ReflectionUtils.invokeMethod(tagList, "add", ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagString"), "a", content));
                }
            }
            else {
                ReflectionUtils.invokeMethod(tagList, "add", ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagString"), String.class).newInstance(page1));
                for (final String content : contents) {
                    ReflectionUtils.invokeMethod(tagList, "add", ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagString"), String.class).newInstance(content));
                }
            }
            ReflectionUtils.invokeMethod(tagCompound, "set", "pages", tagList);
            ReflectionUtils.invokeMethod(itemStackObj, "setTag", tagCompound);
            return (ItemStack)ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asBukkitCopy", itemStackObj);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | InstantiationException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
            return item;
        }
    }
    
    default void openBook(final ItemStack book, final Player player) {
        final int slot = player.getInventory().getHeldItemSlot();
        final ItemStack itemHolding = player.getInventory().getItem(slot);
        player.getInventory().setItem(slot, book);
        Object packet = null;
        try {
            if (VersionManager.is1_14OrAbove()) {
                packet = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutOpenBook"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EnumHand")).newInstance(ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EnumHand"), "valueOf", "MAIN_HAND"));
            }
            else if (VersionManager.is1_13OrAbove()) {
                final ByteBuf buf = Unpooled.buffer(256);
                buf.setByte(0, 0);
                buf.writerIndex(1);
                packet = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutCustomPayload"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("MinecraftKey"), ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketDataSerializer")).newInstance(ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("MinecraftKey"), "a", "minecraft:book_open"), ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketDataSerializer"), ByteBuf.class).newInstance(buf));
            }
            else {
                final ByteBuf buf = Unpooled.buffer(256);
                buf.setByte(0, 0);
                buf.writerIndex(1);
                packet = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutCustomPayload"), String.class, ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketDataSerializer")).newInstance("MC|BOpen", ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketDataSerializer"), ByteBuf.class).newInstance(buf));
            }
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
        }
        ReflectionUtils.sendPacket(player, packet);
        player.getInventory().setItem(slot, itemHolding);
    }
    
    default String getNBTTag(final ItemStack item, final String name) {
        if (item == null || name == null) {
            return null;
        }
        try {
            final Object itemStack = ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asNMSCopy", item);
            if (itemStack == null || !(boolean)ReflectionUtils.invokeMethod(itemStack, "hasTag", new Object[0])) {
                return null;
            }
            final Object tag = ReflectionUtils.invokeMethod(itemStack, itemStack.getClass(), "getTag", new Object[0]);
            final String nbtTagPrefix = CookieGadgets.getInstance().getPluginName();
            if (tag == null || !(boolean)ReflectionUtils.invokeMethod(tag, "hasKey", nbtTagPrefix)) {
                return null;
            }
            final Object compoundObj = ReflectionUtils.invokeMethod(tag, "getCompound", nbtTagPrefix);
            return (String)ReflectionUtils.invokeMethod(compoundObj, "getString", name);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
            return null;
        }
    }
    
    default ItemStack setNBTTag(final ItemStack item, final String name, final String value) {
        if (item == null) {
            LoggerManager.warn("Failed to set NBT data.");
            return item;
        }
        try {
            final Object itemStack = ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asNMSCopy", item);
            Object tag = ReflectionUtils.invokeMethod(itemStack, itemStack.getClass(), "getTag", new Object[0]);
            if (tag == null) {
                tag = ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagCompound"), (Class<?>[])new Class[0]).newInstance(new Object[0]);
                ReflectionUtils.invokeMethod(itemStack, "setTag", tag);
            }
            final String nbtTagPrefix = CookieGadgets.getInstance().getPluginName();
            if (!(boolean)ReflectionUtils.invokeMethod(tag, "hasKey", nbtTagPrefix)) {
                ReflectionUtils.invokeMethod(tag, "set", nbtTagPrefix, ReflectionUtils.getConstructor(ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("NBTTagCompound"), (Class<?>[])new Class[0]).newInstance(new Object[0]));
            }
            final Object compoundObj = ReflectionUtils.invokeMethod(tag, "getCompound", nbtTagPrefix);
            ReflectionUtils.invokeMethod(compoundObj, "setString", name, value);
            return (ItemStack)ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asCraftMirror", itemStack);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | SecurityException | InstantiationException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
            return null;
        }
    }
    
    default boolean hasNBTTag(final ItemStack item, final String name) {
        if (item == null || name == null) {
            return false;
        }
        try {
            final Object itemStack = ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asNMSCopy", item);
            if (itemStack == null || !(boolean)ReflectionUtils.invokeMethod(itemStack, "hasTag", new Object[0])) {
                return false;
            }
            final Object tag = ReflectionUtils.invokeMethod(itemStack, itemStack.getClass(), "getTag", new Object[0]);
            final String nbtTagPrefix = CookieGadgets.getInstance().getPluginName();
            if (tag == null || !(boolean)ReflectionUtils.invokeMethod(tag, "hasKey", nbtTagPrefix)) {
                return false;
            }
            final Object compoundObj = ReflectionUtils.invokeMethod(tag, "getCompound", nbtTagPrefix);
            final String result = (String)ReflectionUtils.invokeMethod(compoundObj, "getString", name);
            return result != null && !result.isEmpty();
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
            return false;
        }
    }
    
    default boolean isNBTTagEqual(final ItemStack item, final String name, final String value) {
        if (item == null || name == null) {
            return false;
        }
        try {
            final Object itemStack = ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack"), "asNMSCopy", item);
            if (itemStack == null || !(boolean)ReflectionUtils.invokeMethod(itemStack, "hasTag", new Object[0])) {
                return false;
            }
            final Object tag = ReflectionUtils.invokeMethod(itemStack, itemStack.getClass(), "getTag", new Object[0]);
            final String nbtTagPrefix = CookieGadgets.getInstance().getPluginName();
            if (tag == null || !(boolean)ReflectionUtils.invokeMethod(tag, "hasKey", nbtTagPrefix)) {
                return false;
            }
            final Object compoundObj = ReflectionUtils.invokeMethod(tag, "getCompound", nbtTagPrefix);
            final String result = (String)ReflectionUtils.invokeMethod(compoundObj, "getString", name);
            return result != null && !result.isEmpty() && result.equals(value);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
            return false;
        }
    }
}
