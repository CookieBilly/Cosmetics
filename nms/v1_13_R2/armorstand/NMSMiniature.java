

package ws.billy.CookieGadgets.nms.v1_13_R2.armorstand;

import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import ws.billy.CookieGadgets.holograms.CraftHologram;
import java.util.Iterator;
import net.minecraft.server.v1_13_R2.Packet;
import ws.billy.CookieGadgets.utils.MathUtil;
import net.minecraft.server.v1_13_R2.Entity;
import net.minecraft.server.v1_13_R2.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_13_R2.ChatComponentText;
import net.minecraft.server.v1_13_R2.SoundEffect;
import org.bukkit.Location;
import net.minecraft.server.v1_13_R2.EntityPlayer;
import net.minecraft.server.v1_13_R2.Vector3f;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import net.minecraft.server.v1_13_R2.EnumItemSlot;
import net.minecraft.server.v1_13_R2.ItemStack;
import net.minecraft.server.v1_13_R2.EnumInteractionResult;
import net.minecraft.server.v1_13_R2.EnumHand;
import net.minecraft.server.v1_13_R2.Vec3D;
import net.minecraft.server.v1_13_R2.EntityHuman;
import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.DamageSource;
import net.minecraft.server.v1_13_R2.AxisAlignedBB;
import ws.billy.CookieGadgets.nms.v1_13_R2.NullBoundingBox;
import net.minecraft.server.v1_13_R2.World;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.nms.interfaces.entity.NMSEntityMiniature;
import net.minecraft.server.v1_13_R2.EntityArmorStand;

public class NMSMiniature extends EntityArmorStand implements NMSEntityMiniature
{
    private Player player;
    private double additionY;
    private int count;
    private float poseX;
    private double locationY;
    private boolean reverse;
    private float headPoseX;
    private float headPoseY;
    private float headPoseZ;
    private boolean lockTick;
    
    public NMSMiniature(final World world, final Player player, final boolean invisible) {
        super(world);
        this.additionY = 0.55;
        this.count = 0;
        this.poseX = 0.0f;
        this.locationY = 0.0;
        this.reverse = false;
        this.player = player;
        super.setInvisible(invisible);
        super.setSmall(true);
        super.setArms(true);
        super.setNoGravity(true);
        super.setBasePlate(true);
        super.setMarker(false);
        super.collides = false;
        this.forceSetBoundingBox(new NullBoundingBox());
    }
    
    public boolean isInvulnerable(final DamageSource damageSource) {
        return true;
    }
    
    public boolean isCollidable() {
        return false;
    }
    
    public void setCustomName(final IChatBaseComponent chatBaseComponent) {
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
    
    public void tick() {
        if (this.player == null || (this.player != null && !this.player.isOnline())) {
            this.killEntityNMS();
            return;
        }
        final EntityPlayer handle = ((CraftPlayer)this.player).getHandle();
        final Location location = ((CraftPlayer)this.player).getLocation();
        final Location locationNMS = this.getLocationNMS();
        if (handle.getWorld() != this.getWorld()) {
            this.teleportNMS(location.clone().add(1.5, this.additionY, 1.5));
        }
        else {
            final float yaw = (float)Math.toDegrees(Math.atan2(handle.locZ - this.getLocationNMS().getZ(), handle.locX - this.getLocationNMS().getX())) - 90.0f + CookieGadgets.random().nextFloat();
            final float pitch = (float)Math.toDegrees(Math.atan2(handle.locZ - this.getLocationNMS().getZ(), handle.locX - this.getLocationNMS().getX())) - 90.0f;
            final double y = handle.locY + this.additionY;
            locationNMS.setY(y);
            locationNMS.setYaw(yaw);
            locationNMS.setPitch(pitch);
            this.glideTeleport(locationNMS);
            final double distanceSquared = location.distanceSquared(this.getLocationNMS());
            if (distanceSquared >= 3.2) {
                final Location add = this.getLocationNMS().add(location.toVector().subtract(this.getLocationNMS().toVector()).setY(0).normalize().multiply(0.6));
                add.setYaw(yaw);
                add.setPitch(pitch);
                add.setY(y);
                this.glideTeleport(add);
            }
            if (distanceSquared >= 32.0) {
                final Location add2 = this.getLocationNMS().add(location.toVector().subtract(this.getLocationNMS().toVector()).setY(0).normalize().multiply(1.6));
                add2.setYaw(yaw);
                add2.setPitch(pitch);
                add2.setY(y);
                this.glideTeleport(add2);
            }
            if (distanceSquared >= 1000.0) {
                this.teleportNMS(location.clone().add(1.5, this.additionY, 1.5));
            }
            ++this.count;
            if (this.count >= 24) {
                this.count = 0;
            }
            final Location locationNMS2 = this.getLocationNMS();
            if (this.count >= 12.0f && !this.reverse) {
                this.reverse = true;
            }
            if (this.count <= 0.0f && this.reverse) {
                this.reverse = false;
            }
            if (this.reverse) {
                this.poseX -= 1.5f;
            }
            else {
                this.poseX += 1.5f;
            }
            super.setHeadPose(new Vector3f(this.poseX + this.headPoseX, this.headPoseY, this.headPoseZ));
            locationNMS2.add(0.0, this.locationY += (this.reverse ? -0.04 : 0.04), 0.0);
            this.glideTeleport(locationNMS2);
        }
    }
    
    private void glideTeleport(final Location location) {
        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }
    
    public void teleportNMS(final Location location) {
        this.setLocationNMS(location.getX(), location.getY(), location.getZ());
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }
    
    public double getOriginalLocationYNMS() {
        return super.locY - this.locationY;
    }
    
    public void setHeadPoseXNMS(final float headPoseX) {
        this.headPoseX = headPoseX;
    }
    
    public void setHeadPoseYNMS(final float headPoseY) {
        this.headPoseY = headPoseY;
    }
    
    public void setHeadPoseZNMS(final float headPoseZ) {
        this.headPoseZ = headPoseZ;
    }
    
    public void setHeadPoseNMS(final float n, final float n2, final float n3) {
        super.setHeadPose(new Vector3f(n, n2, n3));
    }
    
    public void setBodyPoseNMS(final float n, final float n2, final float n3) {
        super.setBodyPose(new Vector3f(n, n2, n3));
    }
    
    public void setLeftArmPoseNMS(final float n, final float n2, final float n3) {
        super.setLeftArmPose(new Vector3f(n, n2, n3));
    }
    
    public void setLeftLegPoseNMS(final float n, final float n2, final float n3) {
        super.setLeftLegPose(new Vector3f(n, n2, n3));
    }
    
    public void setRightArmPoseNMS(final float n, final float n2, final float n3) {
        super.setRightArmPose(new Vector3f(n, n2, n3));
    }
    
    public void setRightLegPoseNMS(final float n, final float n2, final float n3) {
        super.setRightLegPose(new Vector3f(n, n2, n3));
    }
    
    public void a(final SoundEffect soundEffect, final float n, final float n2) {
    }
    
    public void setCustomNameNMS(String substring) {
        if (substring != null && substring.length() > 300) {
            substring = substring.substring(0, 300);
        }
        super.setCustomName((IChatBaseComponent)new ChatComponentText(substring));
        super.setCustomNameVisible(false);
    }
    
    public String getCustomNameNMS() {
        return super.getCustomName().toString();
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
    
    public NBTTagCompound save(final NBTTagCompound nbtTagCompound) {
        return nbtTagCompound;
    }
    
    public void f(final NBTTagCompound nbtTagCompound) {
    }
}
