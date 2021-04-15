

package ws.billy.CookieGadgets.nms.v1_11_R1.armorstand;

import net.minecraft.server.v1_11_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftEntity;
import ws.billy.CookieGadgets.holograms.CraftHologram;
import java.lang.reflect.Field;
import ws.billy.CookieGadgets.log.LoggerManager;
import java.util.List;
import java.util.Set;
import net.minecraft.server.v1_11_R1.RegistryMaterials;
import net.minecraft.server.v1_11_R1.MinecraftKey;
import net.minecraft.server.v1_11_R1.EntityTypes;
import java.util.Iterator;
import net.minecraft.server.v1_11_R1.Packet;
import ws.billy.CookieGadgets.utils.MathUtil;
import net.minecraft.server.v1_11_R1.Entity;
import net.minecraft.server.v1_11_R1.PacketPlayOutEntityTeleport;
import org.bukkit.Location;
import net.minecraft.server.v1_11_R1.SoundEffect;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import net.minecraft.server.v1_11_R1.EnumItemSlot;
import net.minecraft.server.v1_11_R1.ItemStack;
import net.minecraft.server.v1_11_R1.EnumInteractionResult;
import net.minecraft.server.v1_11_R1.EnumHand;
import net.minecraft.server.v1_11_R1.Vec3D;
import net.minecraft.server.v1_11_R1.EntityHuman;
import net.minecraft.server.v1_11_R1.DamageSource;
import net.minecraft.server.v1_11_R1.AxisAlignedBB;
import ws.billy.CookieGadgets.nms.v1_11_R1.NullBoundingBox;
import net.minecraft.server.v1_11_R1.World;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSArmorStand;
import net.minecraft.server.v1_11_R1.EntityArmorStand;

public class ArmorStandFollower extends EntityArmorStand implements NMSArmorStand
{
    private Player player;
    private double additionY;
    private boolean lockTick;
    
    public ArmorStandFollower(final World world) {
        super(world);
        this.additionY = 2.0;
    }
    
    public ArmorStandFollower(final World world, final Player player) {
        super(world);
        this.additionY = 2.0;
        this.player = player;
        super.setInvisible(true);
        super.setSmall(true);
        super.setArms(false);
        super.setNoGravity(true);
        super.setBasePlate(true);
        super.setMarker(true);
        super.collides = false;
        this.forceSetBoundingBox(new NullBoundingBox());
    }
    
    public boolean isInvulnerable(final DamageSource damageSource) {
        return true;
    }
    
    public boolean isCollidable() {
        return false;
    }
    
    public void setCustomName(final String s) {
    }
    
    public void setCustomNameVisible(final boolean b) {
    }
    
    public EnumInteractionResult a(final EntityHuman entityHuman, final Vec3D vec3D, final EnumHand enumHand) {
        return EnumInteractionResult.PASS;
    }
    
    public boolean c(final int n, final ItemStack itemStack) {
        return false;
    }
    
    public void setSlot(final EnumItemSlot enumItemSlot, final ItemStack itemStack) {
    }
    
    public void a(final AxisAlignedBB axisAlignedBB) {
    }
    
    public void forceSetBoundingBox(final AxisAlignedBB axisAlignedBB) {
        super.a(axisAlignedBB);
    }
    
    public void inactiveTick() {
        if (!this.lockTick) {
            super.inactiveTick();
        }
    }
    
    public int getId() {
        return super.getId();
    }
    
    public void A_() {
        if (this.player == null || (this.player != null && !this.player.isOnline())) {
            this.killEntityNMS();
            return;
        }
        final EntityPlayer handle = ((CraftPlayer)this.player).getHandle();
        this.setPosition(handle.locX, handle.locY + this.additionY, handle.locZ);
        this.yaw = handle.yaw;
        this.pitch = handle.pitch;
    }
    
    public void a(final SoundEffect soundEffect, final float n, final float n2) {
    }
    
    public void setCustomNameNMS(String substring) {
        if (substring != null && substring.length() > 300) {
            substring = substring.substring(0, 300);
        }
        super.setCustomName(substring);
        super.setCustomNameVisible(substring != null && !substring.isEmpty());
    }
    
    public String getCustomNameNMS() {
        return super.getCustomName();
    }
    
    public Location getLocationNMS() {
        return new Location((org.bukkit.World)this.world.getWorld(), this.locX, this.locY, this.locZ, this.yaw, this.pitch);
    }
    
    public void setLocationNMS(final double n, final double n2, final double n3) {
        super.setPosition(n, n2, n3);
        final PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport((Entity)this);
        for (final EntityPlayer next : super.world.players) {
            if (next instanceof EntityPlayer) {
                final EntityPlayer entityPlayer = next;
                if (MathUtil.square(entityPlayer.locX - super.locX) + MathUtil.square(entityPlayer.locZ - super.locZ) >= 8192.0 || entityPlayer.playerConnection == null) {
                    continue;
                }
                entityPlayer.playerConnection.sendPacket((Packet)packetPlayOutEntityTeleport);
            }
        }
    }
    
    public void setAdditionY(final double additionY) {
        this.additionY = additionY;
    }
    
    public void setSlotNMS(final EnumItemSlot enumItemSlot, final ItemStack itemStack) {
        super.setSlot(enumItemSlot, itemStack);
    }
    
    public boolean isDeadNMS() {
        return super.dead;
    }
    
    public void killEntityNMS() {
        super.dead = true;
    }
    
    public int getIdNMS() {
        return super.getId();
    }
    
    public org.bukkit.entity.Entity getBukkitEntityNMS() {
        return (org.bukkit.entity.Entity)this.getBukkitEntity();
    }
    
    public void setLockTick(final boolean lockTick) {
        this.lockTick = lockTick;
    }
    
    public void die() {
    }
    
    public static void registerEntity() {
        try {
            final Class<EntityTypes> clazz = EntityTypes.class;
            final MinecraftKey minecraftKey = new MinecraftKey("CustomArmorStandFollower");
            final Field declaredField = clazz.getDeclaredField("b");
            declaredField.setAccessible(true);
            ((RegistryMaterials)declaredField.get(null)).a((int)30, (Object)minecraftKey, (Object)ArmorStandFollower.class);
            final Field declaredField2 = clazz.getDeclaredField("d");
            declaredField2.setAccessible(true);
            ((Set)declaredField2.get(null)).add(minecraftKey);
            final Field declaredField3 = clazz.getDeclaredField("g");
            declaredField3.setAccessible(true);
            ((List)declaredField3.get(null)).set(30, "CustomArmorStandFollower");
        }
        catch (Exception ex) {
            LoggerManager.warn("Failed to register custom ArmorStand follower.");
            ex.printStackTrace();
        }
    }
    
    public CraftHologram getHologram() {
        return null;
    }
    
    public CraftEntity getBukkitEntity() {
        if (super.bukkitEntity == null) {
            super.bukkitEntity = (CraftEntity)new CraftNMSArmorStand(super.world.getServer(), this);
        }
        return super.bukkitEntity;
    }
    
    public void a(final NBTTagCompound nbtTagCompound) {
    }
    
    public void b(final NBTTagCompound nbtTagCompound) {
    }
    
    public boolean c(final NBTTagCompound nbtTagCompound) {
        return false;
    }
    
    public boolean d(final NBTTagCompound nbtTagCompound) {
        return false;
    }
    
    public NBTTagCompound e(final NBTTagCompound nbtTagCompound) {
        return nbtTagCompound;
    }
    
    public void f(final NBTTagCompound nbtTagCompound) {
    }
}
