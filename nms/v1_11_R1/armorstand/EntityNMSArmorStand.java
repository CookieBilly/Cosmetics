

package ws.billy.CookieGadgets.nms.v1_11_R1.armorstand;

import net.minecraft.server.v1_11_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftEntity;
import java.util.Iterator;
import net.minecraft.server.v1_11_R1.Packet;
import ws.billy.CookieGadgets.utils.MathUtil;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.Entity;
import net.minecraft.server.v1_11_R1.PacketPlayOutEntityTeleport;
import org.bukkit.Location;
import net.minecraft.server.v1_11_R1.SoundEffect;
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
import ws.billy.CookieGadgets.holograms.CraftHologram;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSArmorStand;
import net.minecraft.server.v1_11_R1.EntityArmorStand;

public class EntityNMSArmorStand extends EntityArmorStand implements NMSArmorStand
{
    private boolean lockTick;
    private CraftHologram hologram;
    
    public EntityNMSArmorStand(final World world, final CraftHologram hologram) {
        super(world);
        super.setInvisible(true);
        super.setSmall(true);
        super.setArms(false);
        super.setNoGravity(true);
        super.setBasePlate(true);
        super.setMarker(true);
        super.collides = false;
        this.hologram = hologram;
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
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length > 2 && stackTrace[2] != null && stackTrace[2].getFileName().equals("EntityTrackerEntry.java") && 158 < stackTrace[2].getLineNumber() && stackTrace[2].getLineNumber() < 168) {
            return -1;
        }
        return super.getId();
    }
    
    public void A_() {
        if (!this.lockTick) {
            super.A_();
        }
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
    
    public void setLockTick(final boolean lockTick) {
        this.lockTick = lockTick;
    }
    
    public void die() {
    }
    
    public void killEntityNMS() {
        super.dead = true;
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
    
    public boolean isDeadNMS() {
        return super.dead;
    }
    
    public int getIdNMS() {
        return super.getId();
    }
    
    public CraftHologram getHologram() {
        return this.hologram;
    }
    
    public org.bukkit.entity.Entity getBukkitEntityNMS() {
        return (org.bukkit.entity.Entity)this.getBukkitEntity();
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
