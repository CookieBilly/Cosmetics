

package ws.billy.CookieGadgets.nms.v1_8_R2.armorstand;

import net.minecraft.server.v1_8_R2.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftEntity;
import ws.billy.CookieGadgets.holograms.CraftHologram;
import org.bukkit.entity.Entity;
import java.util.Iterator;
import net.minecraft.server.v1_8_R2.Packet;
import ws.billy.CookieGadgets.utils.MathUtil;
import net.minecraft.server.v1_8_R2.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R2.MathHelper;
import org.bukkit.Location;
import net.minecraft.server.v1_8_R2.EntityPlayer;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import net.minecraft.server.v1_8_R2.ItemStack;
import net.minecraft.server.v1_8_R2.Vec3D;
import net.minecraft.server.v1_8_R2.EntityHuman;
import net.minecraft.server.v1_8_R2.DamageSource;
import net.minecraft.server.v1_8_R2.AxisAlignedBB;
import ws.billy.CookieGadgets.nms.v1_8_R2.NullBoundingBox;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import net.minecraft.server.v1_8_R2.World;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSArmorStand;
import net.minecraft.server.v1_8_R2.EntityArmorStand;

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
        super.setGravity(true);
        super.setBasePlate(true);
        try {
            ReflectionUtils.setPrivateField(EntityArmorStand.class, this, "bi", Integer.MAX_VALUE);
        }
        catch (Exception ex) {}
        this.forceSetBoundingBox(new NullBoundingBox());
    }
    
    public boolean isInvulnerable(final DamageSource damageSource) {
        return true;
    }
    
    public void setCustomName(final String s) {
    }
    
    public void setCustomNameVisible(final boolean b) {
    }
    
    public boolean a(final EntityHuman entityHuman, final Vec3D vec3D) {
        return true;
    }
    
    public boolean d(final int n, final ItemStack itemStack) {
        return false;
    }
    
    public void setEquipment(final int n, final ItemStack itemStack) {
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
    
    public void t_() {
        if (this.player == null || (this.player != null && !this.player.isOnline())) {
            this.killEntityNMS();
            return;
        }
        final EntityPlayer handle = ((CraftPlayer)this.player).getHandle();
        this.setPosition(handle.locX, handle.locY + this.additionY, handle.locZ);
        this.yaw = handle.yaw;
        this.pitch = handle.pitch;
    }
    
    public void makeSound(final String s, final float n, final float n2) {
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
        final PacketPlayOutEntityTeleport packetPlayOutEntityTeleport = new PacketPlayOutEntityTeleport(this.getIdNMS(), MathHelper.floor(this.locX * 32.0), MathHelper.floor(this.locY * 32.0), MathHelper.floor(this.locZ * 32.0), (byte)(this.yaw * 256.0f / 360.0f), (byte)(this.pitch * 256.0f / 360.0f), this.onGround);
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
    
    public void setSlotNMS(final int n, final ItemStack itemStack) {
        super.setEquipment(n, itemStack);
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
    
    public Entity getBukkitEntityNMS() {
        return (Entity)this.getBukkitEntity();
    }
    
    public void setLockTick(final boolean lockTick) {
        this.lockTick = lockTick;
    }
    
    public void die() {
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
    
    public void e(final NBTTagCompound nbtTagCompound) {
    }
    
    public void f(final NBTTagCompound nbtTagCompound) {
    }
}
