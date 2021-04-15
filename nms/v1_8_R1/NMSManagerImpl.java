

package ws.billy.CookieGadgets.nms.v1_8_R1;

import java.lang.reflect.Method;
import net.minecraft.server.v1_8_R1.MathHelper;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSEntityBase;
import ws.billy.CookieGadgets.nms.v1_8_R1.armorstand.NMSMiniature;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSEntityMiniature;
import ws.billy.CookieGadgets.utils.cosmetics.miniatures.MiniatureEquipments;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import ws.billy.CookieGadgets.nms.v1_8_R1.armorstand.ArmorStandFollower;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.nms.interfaces.ArmorStandSlot;
import net.minecraft.server.v1_8_R1.WorldServer;
import ws.billy.CookieGadgets.log.LoggerManager;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSNameable;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.nms.v1_8_R1.armorstand.EntityNMSArmorStand;
import ws.billy.CookieGadgets.holograms.CraftHologram;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSArmorStand;
import org.bukkit.World;
import ws.billy.CookieGadgets.nms.interfaces.IJSONMessage;
import net.minecraft.server.v1_8_R1.PathfinderGoal;
import ws.billy.CookieGadgets.nms.v1_8_R1.pathfinders.PathFinderGoalPanic;
import net.minecraft.server.v1_8_R1.EntityCreature;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import ws.billy.CookieGadgets.nms.v1_8_R1.pets.entity.SpawnUtil;
import ws.billy.CookieGadgets.nms.v1_8_R1.pets.registry.PetRegistry;
import ws.billy.CookieGadgets.nms.v1_8_R1.pets.pathfinders.Pathfinders;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.nms.interfaces.pets.ISpawnUtil;
import ws.billy.CookieGadgets.nms.NMSManager;

public class NMSManagerImpl implements NMSManager
{
    private ISpawnUtil spawnUtil;
    
    @Override
    public void handlePathfinders(final Player player, final Entity entity, final double n) {
        Pathfinders.handlePathfinders(player, entity, n);
    }
    
    @Override
    public void clearPathfinders(final Entity entity) {
        Pathfinders.clearPathfinders(entity);
    }
    
    @Override
    public void registerPets() {
        PetRegistry.registerPets();
    }
    
    @Override
    public void unregisterPets() {
        PetRegistry.unregisterPets();
    }
    
    @Override
    public ISpawnUtil getSpawnUtils() {
        if (this.spawnUtil == null) {
            return this.spawnUtil = new SpawnUtil();
        }
        return this.spawnUtil;
    }
    
    @Override
    public void makeEntityPanic(final Entity entity) {
        final EntityInsentient entityInsentient = (EntityInsentient)((CraftEntity)entity).getHandle();
        entityInsentient.goalSelector.a(3, (PathfinderGoal)new PathFinderGoalPanic((EntityCreature)entityInsentient, 0.4));
    }
    
    @Override
    public IJSONMessage newJSONMessage(final String s) {
        return new JSONMessageImpl(s);
    }
    
    @Override
    public NMSArmorStand spawnNMSArmorStand(final World world, final double n, final double n2, final double n3, final String s) {
        final WorldServer handle = ((CraftWorld)world).getHandle();
        final CraftHologram craftHologram = new CraftHologram(new Location(world, n, n2, n3));
        final EntityNMSArmorStand nmsNameable = new EntityNMSArmorStand((net.minecraft.server.v1_8_R1.World)handle, craftHologram);
        nmsNameable.setLocationNMS(n, n2, n3);
        if (s != null && !s.isEmpty()) {
            nmsNameable.setCustomNameNMS(ChatUtil.format(s));
        }
        craftHologram.setNMSNameable(nmsNameable);
        nmsNameable.getBukkitEntity().setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        if (!addEntityToWorld(handle, (net.minecraft.server.v1_8_R1.Entity)nmsNameable)) {
            LoggerManager.printLog(LoggerManager.LogLevel.WARNING, "Could not spawn mystery vault hologram!");
        }
        return nmsNameable;
    }
    
    @Override
    public NMSArmorStand spawnNMSArmorStandFollower(final World world, final double n, final double n2, final double n3, final Player player, final double additionY, final String s, final ArmorStandSlot armorStandSlot, final ItemStack itemStack) {
        final WorldServer handle = ((CraftWorld)world).getHandle();
        final ArmorStandFollower armorStandFollower = new ArmorStandFollower((net.minecraft.server.v1_8_R1.World)handle, player);
        armorStandFollower.setLocationNMS(n, n2, n3);
        armorStandFollower.setAdditionY(additionY);
        if (armorStandSlot != null && itemStack != null) {
            armorStandFollower.setSlotNMS(armorStandSlot.to_1_8_Format(), CraftItemStack.asNMSCopy(itemStack));
        }
        if (s != null && !s.isEmpty()) {
            armorStandFollower.setCustomNameNMS(ChatUtil.format(s));
        }
        armorStandFollower.getBukkitEntity().setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        if (!addEntityToWorld(handle, (net.minecraft.server.v1_8_R1.Entity)armorStandFollower)) {
            LoggerManager.printLog(LoggerManager.LogLevel.WARNING, "Could not spawn CookieGadgets hologram!");
        }
        return armorStandFollower;
    }
    
    @Override
    public NMSEntityMiniature spawnNMSMiniature(final World world, final double n, final double n2, final double n3, final boolean b, final Player player, final String s, final MiniatureEquipments miniatureEquipments) {
        final WorldServer handle = ((CraftWorld)world).getHandle();
        final NMSMiniature nmsMiniature = new NMSMiniature((net.minecraft.server.v1_8_R1.World)handle, player, b);
        nmsMiniature.setLocationNMS(n, n2, n3);
        if (miniatureEquipments != null) {
            if (miniatureEquipments.getHand() != null) {
                nmsMiniature.setSlotNMS(miniatureEquipments.getHand().getMiniatureEquipments().getArmorStandSlot().to_1_8_Format(), CraftItemStack.asNMSCopy(miniatureEquipments.getHand().getItemStack()));
            }
            if (miniatureEquipments.getHelmet() != null) {
                nmsMiniature.setSlotNMS(miniatureEquipments.getHelmet().getMiniatureEquipments().getArmorStandSlot().to_1_8_Format(), CraftItemStack.asNMSCopy(miniatureEquipments.getHelmet().getItemStack()));
            }
            if (miniatureEquipments.getChestplate() != null) {
                nmsMiniature.setSlotNMS(miniatureEquipments.getChestplate().getMiniatureEquipments().getArmorStandSlot().to_1_8_Format(), CraftItemStack.asNMSCopy(miniatureEquipments.getChestplate().getItemStack()));
            }
            if (miniatureEquipments.getLeggings() != null) {
                nmsMiniature.setSlotNMS(miniatureEquipments.getLeggings().getMiniatureEquipments().getArmorStandSlot().to_1_8_Format(), CraftItemStack.asNMSCopy(miniatureEquipments.getLeggings().getItemStack()));
            }
            if (miniatureEquipments.getBoots() != null) {
                nmsMiniature.setSlotNMS(miniatureEquipments.getBoots().getMiniatureEquipments().getArmorStandSlot().to_1_8_Format(), CraftItemStack.asNMSCopy(miniatureEquipments.getBoots().getItemStack()));
            }
        }
        if (s != null && !s.isEmpty()) {
            nmsMiniature.setCustomNameNMS(ChatUtil.format(s));
        }
        nmsMiniature.getBukkitEntity().setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        if (!addEntityToWorld(handle, (net.minecraft.server.v1_8_R1.Entity)nmsMiniature)) {
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
        final net.minecraft.server.v1_8_R1.Entity handle = ((CraftEntity)entity).getHandle();
        if (handle instanceof NMSEntityBase) {
            return (NMSEntityBase)handle;
        }
        return null;
    }
    
    public static boolean addEntityToWorld(final WorldServer obj, final net.minecraft.server.v1_8_R1.Entity entity) {
        if (!Bukkit.isPrimaryThread()) {
            throw new IllegalArgumentException("Async entity add");
        }
        Method declaredMethod = null;
        try {
            declaredMethod = net.minecraft.server.v1_8_R1.World.class.getDeclaredMethod("a", net.minecraft.server.v1_8_R1.Entity.class);
            declaredMethod.setAccessible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final int floor = MathHelper.floor(entity.locX / 16.0);
        final int floor2 = MathHelper.floor(entity.locZ / 16.0);
        if (!obj.chunkProviderServer.isChunkLoaded(floor, floor2)) {
            entity.dead = true;
            return false;
        }
        obj.getChunkAt(floor, floor2).a(entity);
        obj.entityList.add(entity);
        try {
            declaredMethod.invoke(obj, entity);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            return false;
        }
        return true;
    }
}
