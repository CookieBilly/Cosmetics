

package ws.billy.CookieGadgets.nms.v1_10_R1.balloons;

import org.bukkit.entity.Slime;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftEntity;
import net.minecraft.server.v1_10_R1.SoundEffect;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.Bukkit;
import net.minecraft.server.v1_10_R1.EntityPlayer;
import net.minecraft.server.v1_10_R1.EntityDamageSource;
import net.minecraft.server.v1_10_R1.DamageSource;
import net.minecraft.server.v1_10_R1.NBTTagCompound;
import net.minecraft.server.v1_10_R1.AxisAlignedBB;
import net.minecraft.server.v1_10_R1.Entity;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_10_R1.World;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_10_R1.EntitySlime;

public class NMSBalloons extends EntitySlime
{
    private Player player;
    private ArmorStand contents;
    private Location currentLoc;
    private boolean status;
    private boolean yaw;
    private int i;
    
    public NMSBalloons(final World world, final Player player, final ItemStack helmet) {
        super(world);
        this.status = true;
        this.yaw = true;
        this.i = 0;
        super.persistent = true;
        super.collides = false;
        this.a(new float[] { 0.0f, 0.0f });
        this.setSize(-1);
        this.setInvisible(true);
        this.player = player;
        this.updatePosition();
        this.contents = (ArmorStand)player.getWorld().spawnEntity(this.currentLoc.clone().subtract(0.0, 1.1, 0.0), EntityType.ARMOR_STAND);
        super.setLeashHolder((Entity)((CraftPlayer)this.player).getHandle(), true);
        this.setLocation(this.currentLoc.getX(), this.currentLoc.getY(), this.currentLoc.getZ(), this.currentLoc.getYaw(), this.currentLoc.getPitch());
        this.contents.setVisible(false);
        this.contents.setCustomNameVisible(false);
        this.contents.setMarker(false);
        this.contents.setGravity(false);
        this.contents.setHelmet(helmet);
        this.forceSetBoundingBox(new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
    }
    
    public void a(final AxisAlignedBB axisAlignedBB) {
    }
    
    public void forceSetBoundingBox(final AxisAlignedBB axisAlignedBB) {
        super.a(axisAlignedBB);
    }
    
    public void m() {
        this.updatePosition();
        this.setLocation(this.currentLoc.getX(), this.currentLoc.getY(), this.currentLoc.getZ(), this.currentLoc.getYaw(), this.currentLoc.getPitch());
        this.contents.teleport(this.getBukkitEntity().getLocation().clone().subtract(0.0, 1.1, 0.0));
        super.setLeashHolder((Entity)((CraftPlayer)this.player).getHandle(), true);
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
    
    public void a(final NBTTagCompound nbtTagCompound) {
    }
    
    public boolean damageEntity(final DamageSource damageSource, final float n) {
        if (damageSource instanceof EntityDamageSource) {
            final EntityDamageSource entityDamageSource = (EntityDamageSource)damageSource;
            if (entityDamageSource.getEntity() instanceof EntityPlayer) {
                Bukkit.getPluginManager().callEvent((Event)new PlayerInteractEntityEvent((Player)((EntityPlayer)entityDamageSource.getEntity()).getBukkitEntity(), (org.bukkit.entity.Entity)this.getBukkitEntity()));
            }
        }
        return false;
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
    
    public void a(final SoundEffect soundEffect, final float n, final float n2) {
    }
    
    public void die() {
    }
    
    public CraftEntity getBukkitEntity() {
        if (super.bukkitEntity == null) {
            super.bukkitEntity = (CraftEntity)new CraftNMSSlime(super.world.getServer(), this);
        }
        return super.bukkitEntity;
    }
    
    private void updatePosition() {
        (this.currentLoc = this.player.getLocation()).setPitch(-50.0f - MathUtil.randomFloat(0.0f, 5.0f));
        if (this.i == 90) {
            this.yaw = false;
        }
        else if (this.i == 0) {
            this.yaw = true;
        }
        if (this.yaw) {
            --this.i;
        }
        else {
            ++this.i;
        }
        this.currentLoc.add(this.currentLoc.getDirection().multiply(-1.3).getX(), 2.3 + (this.status ? 0.1 : 0.0), this.currentLoc.getDirection().multiply(-1.8).getZ());
        this.status = !this.status;
    }
    
    public ArmorStand getContents() {
        return this.contents;
    }
    
    public Slime getSlime() {
        return (Slime)this.getBukkitEntity();
    }
}
