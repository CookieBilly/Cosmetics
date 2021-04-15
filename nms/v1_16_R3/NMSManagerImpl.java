

package ws.billy.CookieGadgets.nms.v1_16_R3;

import ws.billy.CookieGadgets.utils.ReflectionUtils;
import net.minecraft.server.v1_16_R3.MathHelper;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSEntityBase;
import ws.billy.CookieGadgets.nms.v1_16_R3.armorstand.NMSMiniature;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSEntityMiniature;
import ws.billy.CookieGadgets.utils.cosmetics.miniatures.MiniatureEquipments;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import ws.billy.CookieGadgets.nms.v1_16_R3.armorstand.ArmorStandFollower;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.nms.interfaces.ArmorStandSlot;
import net.minecraft.server.v1_16_R3.WorldServer;
import ws.billy.CookieGadgets.log.LoggerManager;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSNameable;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.nms.v1_16_R3.armorstand.EntityNMSArmorStand;
import ws.billy.CookieGadgets.holograms.CraftHologram;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSArmorStand;
import org.bukkit.World;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.utils.SpawnUtil;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.pathfinders.PathfinderGoalPanic;
import net.minecraft.server.v1_16_R3.EntityCreature;
import java.lang.reflect.Field;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.pathfinders.PathfinderGoalFollowOwner;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R3.PathfinderGoal;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R3.EntityHuman;
import java.util.Set;
import java.util.Map;
import net.minecraft.server.v1_16_R3.PathfinderGoalSelector;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity.EntityPet;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.nms.interfaces.pets.ISpawnUtil;
import ws.billy.CookieGadgets.nms.NMSManager;

public class NMSManagerImpl implements NMSManager
{
    private ISpawnUtil spawnUtil;
    
    @Override
    public void handlePathfinders(final Player player, final Entity entity, final double n) {
        final net.minecraft.server.v1_16_R3.Entity handle = ((CraftEntity)entity).getHandle();
        try {
            if (handle instanceof EntityPet) {
                final EntityPet entityPet = (EntityPet)handle;
                final Field declaredField = PathfinderGoalSelector.class.getDeclaredField("c");
                declaredField.setAccessible(true);
                final Field declaredField2 = PathfinderGoalSelector.class.getDeclaredField("d");
                declaredField2.setAccessible(true);
                ((Map)declaredField.get(entityPet.goalSelector)).clear();
                ((Map)declaredField.get(entityPet.targetSelector)).clear();
                ((Set)declaredField2.get(entityPet.goalSelector)).clear();
                ((Set)declaredField2.get(entityPet.targetSelector)).clear();
                entityPet.goalSelector.a(0, (PathfinderGoal)new PathfinderGoalLookAtPlayer((EntityInsentient)entityPet, (Class)EntityHuman.class, 6.0f));
                entityPet.goalSelector.a(1, (PathfinderGoal)new PathfinderGoalFloat((EntityInsentient)entityPet));
                entityPet.goalSelector.a(2, (PathfinderGoal)new PathfinderGoalFollowOwner(player, entityPet, n));
            }
        }
        catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException ex) {
            final Throwable t;
            t.printStackTrace();
        }
    }
    
    @Override
    public void clearPathfinders(final Entity entity) {
        final net.minecraft.server.v1_16_R3.Entity handle = ((CraftEntity)entity).getHandle();
        try {
            if (handle instanceof EntityInsentient) {
                final EntityInsentient entityInsentient = (EntityInsentient)handle;
                final Field declaredField = PathfinderGoalSelector.class.getDeclaredField("c");
                declaredField.setAccessible(true);
                final Field declaredField2 = PathfinderGoalSelector.class.getDeclaredField("d");
                declaredField2.setAccessible(true);
                ((Map)declaredField.get(entityInsentient.goalSelector)).clear();
                ((Map)declaredField.get(entityInsentient.targetSelector)).clear();
                ((Set)declaredField2.get(entityInsentient.goalSelector)).clear();
                ((Set)declaredField2.get(entityInsentient.targetSelector)).clear();
                entityInsentient.goalSelector.a(0, (PathfinderGoal)new PathfinderGoalFloat(entityInsentient));
            }
        }
        catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException ex) {
            final Throwable t;
            t.printStackTrace();
        }
    }
    
    @Override
    public void makeEntityPanic(final Entity entity) {
        final EntityInsentient entityInsentient = (EntityInsentient)((CraftEntity)entity).getHandle();
        entityInsentient.goalSelector.a(3, (PathfinderGoal)new PathfinderGoalPanic((EntityCreature)entityInsentient, 1.5));
    }
    
    @Override
    public ISpawnUtil getSpawnUtils() {
        if (this.spawnUtil == null) {
            return this.spawnUtil = new SpawnUtil();
        }
        return this.spawnUtil;
    }
    
    @Override
    public NMSArmorStand spawnNMSArmorStand(final World world, final double n, final double n2, final double n3, final String s) {
        final WorldServer handle = ((CraftWorld)world).getHandle();
        final CraftHologram craftHologram = new CraftHologram(new Location(world, n, n2, n3));
        final EntityNMSArmorStand nmsNameable = new EntityNMSArmorStand((net.minecraft.server.v1_16_R3.World)handle, craftHologram);
        nmsNameable.setLocationNMS(n, n2, n3);
        if (s != null && !s.isEmpty()) {
            nmsNameable.setCustomNameNMS(ChatUtil.format(s));
        }
        nmsNameable.getBukkitEntity().setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        craftHologram.setNMSNameable(nmsNameable);
        if (!addEntityToWorld(handle, (net.minecraft.server.v1_16_R3.Entity)nmsNameable)) {
            LoggerManager.printLog(LoggerManager.LogLevel.WARNING, "Could not spawn mystery vault hologram!");
        }
        return nmsNameable;
    }
    
    @Override
    public NMSArmorStand spawnNMSArmorStandFollower(final World world, final double n, final double n2, final double n3, final Player player, final double additionY, final String s, final ArmorStandSlot armorStandSlot, final ItemStack itemStack) {
        final WorldServer handle = ((CraftWorld)world).getHandle();
        final ArmorStandFollower armorStandFollower = new ArmorStandFollower((net.minecraft.server.v1_16_R3.World)handle, player);
        armorStandFollower.setLocationNMS(n, n2, n3);
        armorStandFollower.setAdditionY(additionY);
        if (armorStandSlot != null && itemStack != null) {
            armorStandFollower.setSlotNMS(armorStandSlot.to_1_16_R3_Format(), CraftItemStack.asNMSCopy(itemStack));
        }
        if (s != null && !s.isEmpty()) {
            armorStandFollower.setCustomNameNMS(ChatUtil.format(s));
        }
        armorStandFollower.getBukkitEntity().setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        if (!addEntityToWorld(handle, (net.minecraft.server.v1_16_R3.Entity)armorStandFollower)) {
            LoggerManager.printLog(LoggerManager.LogLevel.WARNING, "Could not spawn CookieGadgets hologram!");
        }
        return armorStandFollower;
    }
    
    public NMSArmorStand spawnCustomNMSArmorStand(final World world, final double n, final double n2, final double n3, final boolean b, final String s, final ArmorStandSlot armorStandSlot, final ItemStack itemStack) {
        final WorldServer handle = ((CraftWorld)world).getHandle();
        final EntityNMSArmorStand entityNMSArmorStand = new EntityNMSArmorStand((net.minecraft.server.v1_16_R3.World)handle, b);
        entityNMSArmorStand.setLocationNMS(n, n2, n3);
        if (armorStandSlot != null && itemStack != null) {
            entityNMSArmorStand.setSlotNMS(armorStandSlot.to_1_16_R3_Format(), CraftItemStack.asNMSCopy(itemStack));
        }
        if (s != null && !s.isEmpty()) {
            entityNMSArmorStand.setCustomNameNMS(ChatUtil.format(s));
        }
        entityNMSArmorStand.getBukkitEntity().setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        if (!addEntityToWorld(handle, (net.minecraft.server.v1_16_R3.Entity)entityNMSArmorStand)) {
            LoggerManager.printLog(LoggerManager.LogLevel.WARNING, "Could not spawn CookieGadgets armorstand!");
        }
        return entityNMSArmorStand;
    }
    
    @Override
    public NMSEntityMiniature spawnNMSMiniature(final World world, final double n, final double n2, final double n3, final boolean b, final Player player, final String s, final MiniatureEquipments miniatureEquipments) {
        final WorldServer handle = ((CraftWorld)world).getHandle();
        final NMSMiniature nmsMiniature = new NMSMiniature((net.minecraft.server.v1_16_R3.World)handle, player, b);
        nmsMiniature.setLocationNMS(n, n2, n3);
        if (miniatureEquipments != null) {
            if (miniatureEquipments.getHand() != null) {
                nmsMiniature.setSlotNMS(miniatureEquipments.getHand().getMiniatureEquipments().getArmorStandSlot().to_1_16_R3_Format(), CraftItemStack.asNMSCopy(miniatureEquipments.getHand().getItemStack()));
            }
            if (miniatureEquipments.getHelmet() != null) {
                nmsMiniature.setSlotNMS(miniatureEquipments.getHelmet().getMiniatureEquipments().getArmorStandSlot().to_1_16_R3_Format(), CraftItemStack.asNMSCopy(miniatureEquipments.getHelmet().getItemStack()));
            }
            if (miniatureEquipments.getChestplate() != null) {
                nmsMiniature.setSlotNMS(miniatureEquipments.getChestplate().getMiniatureEquipments().getArmorStandSlot().to_1_16_R3_Format(), CraftItemStack.asNMSCopy(miniatureEquipments.getChestplate().getItemStack()));
            }
            if (miniatureEquipments.getLeggings() != null) {
                nmsMiniature.setSlotNMS(miniatureEquipments.getLeggings().getMiniatureEquipments().getArmorStandSlot().to_1_16_R3_Format(), CraftItemStack.asNMSCopy(miniatureEquipments.getLeggings().getItemStack()));
            }
            if (miniatureEquipments.getBoots() != null) {
                nmsMiniature.setSlotNMS(miniatureEquipments.getBoots().getMiniatureEquipments().getArmorStandSlot().to_1_16_R3_Format(), CraftItemStack.asNMSCopy(miniatureEquipments.getBoots().getItemStack()));
            }
        }
        if (s != null && !s.isEmpty()) {
            nmsMiniature.setCustomNameNMS(ChatUtil.format(s));
        }
        nmsMiniature.getBukkitEntity().setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        if (!addEntityToWorld(handle, (net.minecraft.server.v1_16_R3.Entity)nmsMiniature)) {
            LoggerManager.printLog(LoggerManager.LogLevel.WARNING, "Could not spawn CookieGadgets miniature!");
        }
        return nmsMiniature;
    }
    
    @Override
    public boolean isNMSEntityBase(final Entity entity) {
        return ((CraftEntity)entity).getHandle() instanceof NMSEntityBase;
    }
    
    @Override
    public NMSEntityBase getNMSEntityBase(final Entity entity) {
        final net.minecraft.server.v1_16_R3.Entity handle = ((CraftEntity)entity).getHandle();
        if (handle instanceof NMSEntityBase) {
            return (NMSEntityBase)handle;
        }
        return null;
    }
    
    public static boolean addEntityToWorld(final WorldServer worldServer, final net.minecraft.server.v1_16_R3.Entity entity) {
        Validate.isTrue(Bukkit.isPrimaryThread(), "Async entity add!");
        final int floor = MathHelper.floor(entity.locX() / 16.0);
        final int floor2 = MathHelper.floor(entity.locZ() / 16.0);
        if (!worldServer.isChunkLoaded(floor, floor2)) {
            entity.dead = true;
            return false;
        }
        worldServer.getChunkAt(floor, floor2).a(entity);
        try {
            ReflectionUtils.invokeMethod(worldServer, WorldServer.class, "registerEntity", entity);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
