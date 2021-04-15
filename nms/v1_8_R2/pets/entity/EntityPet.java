

package ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity;

import net.minecraft.server.v1_8_R2.NBTTagCompound;
import net.minecraft.server.v1_8_R2.DamageSource;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.CraftNMSCreature;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.block.BlockFace;
import net.minecraft.server.v1_8_R2.EntityPlayer;
import net.minecraft.server.v1_8_R2.MathHelper;
import net.minecraft.server.v1_8_R2.Packet;
import net.minecraft.server.v1_8_R2.PacketPlayOutEntityTeleport;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.Event;
import ws.billy.CookieGadgets.api.event.pets.PetMoveEvent;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityHorsePet;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IFlyablePet;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftEntity;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import org.bukkit.entity.Entity;
import net.minecraft.server.v1_8_R2.GenericAttributes;
import net.minecraft.server.v1_8_R2.EntityLiving;
import net.minecraft.server.v1_8_R2.World;
import java.lang.reflect.Field;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import ws.billy.CookieGadgets.nms.interfaces.pets.NMSPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import net.minecraft.server.v1_8_R2.IAnimal;
import net.minecraft.server.v1_8_R2.EntityCreature;

public abstract class EntityPet extends EntityCreature implements IAnimal, IEntityPet, NMSPet
{
    private IPet pet;
    private double moveSpeed;
    private double rideSpeed;
    private boolean floatDown;
    protected Field JUMP_FIELD;
    
    public EntityPet(final World world) {
        super(world);
        this.JUMP_FIELD = null;
    }
    
    public EntityPet(final World world, final IPet pet) {
        super(world);
        this.JUMP_FIELD = null;
        this.pet = pet;
        this.resetEntitySize();
        if (this.JUMP_FIELD == null) {
            try {
                (this.JUMP_FIELD = EntityLiving.class.getDeclaredField("aY")).setAccessible(true);
            }
            catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            }
        }
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(20.0);
        this.moveSpeed = pet.getType().getEntityType().getMoveSpeed();
        this.rideSpeed = pet.getType().getEntityType().getRideSpeed();
        this.floatDown = pet.getType().getEntityPet().canFloat();
    }
    
    public IPet getPet() {
        return this.pet;
    }
    
    public Entity getEntity() {
        return (Entity)super.getBukkitEntity();
    }
    
    public GEntity getGEntity() {
        return this.pet.getType().getEntityType().getGEntity();
    }
    
    public void resetEntitySize() {
        final EntitySize entitySize = this.getClass().getAnnotation(EntitySize.class);
        if (entitySize != null) {
            this.setSize(entitySize.width(), entitySize.height());
        }
    }
    
    public void resetEntitySize(final boolean b) {
        final EntitySize entitySize = this.getClass().getAnnotation(EntitySize.class);
        if (entitySize != null) {
            this.setSize(b ? (entitySize.width() / 2.0f) : entitySize.width(), b ? (entitySize.height() / 2.0f) : entitySize.height());
        }
    }
    
    public void setEntitySize(final float n, final float n2) {
        this.setSize(n, n2);
    }
    
    public void setCustomNameNMS(final String customName) {
        if (!super.getCustomNameVisible()) {
            super.setCustomNameVisible(true);
        }
        super.setCustomName(customName);
    }
    
    public String getCustomNameNMS() {
        return super.getCustomName();
    }
    
    public void setOwnerRidePetNMS() {
        if (this.pet == null || this.pet.getOwner() == null || !this.pet.getOwner().isOnline()) {
            return;
        }
        if (this.pet.getOwner() instanceof CraftEntity) {
            this.removePassengerNMS();
            ((CraftEntity)this.pet.getOwner()).getHandle().mount(((CraftEntity)this.getBukkitEntity()).getHandle());
        }
    }
    
    public void removePassengerNMS() {
        if (((CraftEntity)this.getBukkitEntity()).getHandle().vehicle == null) {
            return;
        }
        ((CraftEntity)this.pet.getOwner()).getHandle().mount((net.minecraft.server.v1_8_R2.Entity)null);
    }
    
    public void setAsHatNMS() {
        if (this.pet == null || this.pet.getOwner() == null || !this.pet.getOwner().isOnline()) {
            return;
        }
        final CraftCreature bukkitEntity = this.getBukkitEntity();
        final CraftPlayer craftPlayer = (CraftPlayer)this.pet.getOwner();
        this.removeHatNMS();
        craftPlayer.getHandle().passenger = ((CraftEntity)bukkitEntity).getHandle();
    }
    
    public void removeHatNMS() {
        if (this.pet == null || this.pet.getOwner() == null || !this.pet.getOwner().isOnline()) {
            return;
        }
        ((CraftPlayer)this.pet.getOwner()).getHandle().passenger = null;
    }
    
    public boolean isDeadNMS() {
        return super.dead;
    }
    
    public void killEntityNMS() {
        super.dead = true;
    }
    
    public void setSilentNMS(final boolean b) {
    }
    
    public boolean isSilentNMS() {
        return false;
    }
    
    public void setEquipmentNMS(final EnumArmorType enumArmorType, final ItemStack itemStack) {
        final net.minecraft.server.v1_8_R2.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);
        switch (enumArmorType) {
            case HELMET: {
                super.setEquipment(4, nmsCopy);
                break;
            }
            case CHESTPLATE: {
                super.setEquipment(3, nmsCopy);
                break;
            }
            case LEGGINGS: {
                super.setEquipment(2, nmsCopy);
                break;
            }
            case BOOTS: {
                super.setEquipment(1, nmsCopy);
                break;
            }
        }
    }
    
    public void teleportNMS(final Location location) {
        super.setPosition(location.getX(), location.getY(), location.getZ());
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }
    
    public NMSPet getEntityNMS() {
        return this;
    }
    
    protected void h() {
        super.h();
        this.initDatawatchers();
    }
    
    protected void initDatawatchers() {
    }
    
    public void t_() {
        super.t_();
        this.repeatTask();
    }
    
    public void repeatTask() {
        if (this.pet == null) {
            this.killEntityNMS();
            return;
        }
        if (this.pet.getOwner() == null || !this.pet.getOwner().isOnline()) {
            this.killEntityNMS();
            return;
        }
        if (this instanceof IFlyablePet && this.pet.isOwnerRiding() && this.floatDown && !this.onGround && this.motY < 0.0) {
            this.motY *= 0.6;
        }
        final Player owner = this.pet.getOwner();
        if (this.pet.isHat()) {
            final float yaw = owner.getLocation().getYaw();
            this.yaw = yaw;
            this.lastYaw = yaw;
        }
        final double value = this.getAttributeInstance(GenericAttributes.d).getValue();
        if (this.isOwnerRiding()) {
            if (value != this.rideSpeed) {
                this.getAttributeInstance(GenericAttributes.d).setValue(this.rideSpeed);
            }
        }
        else if (value != this.moveSpeed) {
            this.getAttributeInstance(GenericAttributes.d).setValue(this.moveSpeed);
        }
    }
    
    protected boolean isOwnerRiding() {
        return this.passenger != null && this.passenger == ((CraftPlayer)this.pet.getOwner()).getHandle();
    }
    
    public void g(float n, float ba) {
        if (this.pet.getOwner() == null) {
            return;
        }
        if (this.pet == null || !this.isOwnerRiding()) {
            this.S = 0.5f;
            super.g(n, ba);
            return;
        }
        final EntityPlayer handle = ((CraftPlayer)this.pet.getOwner()).getHandle();
        this.yaw = handle.yaw;
        this.lastYaw = this.yaw;
        this.pitch = handle.pitch * 0.5f;
        this.setYawPitch(this.yaw, this.pitch);
        final float yaw = this.yaw;
        this.aI = yaw;
        this.aK = yaw;
        this.S = 1.0f;
        n = handle.aZ * 0.5f;
        ba = handle.ba;
        if (ba <= 0.0f) {
            ba *= (float)(0.25 * this.rideSpeed);
        }
        else {
            ba *= (float)this.rideSpeed;
        }
        if (!(this instanceof IEntityHorsePet)) {
            n *= 0.75f;
        }
        this.k((float)this.rideSpeed);
        if (!this.world.isClientSide) {
            Bukkit.getServer().getPluginManager().callEvent((Event)new PetMoveEvent(this, PetMoveEvent.Cause.RIDE));
            final Location location = new Location((org.bukkit.World)this.world.getWorld(), this.lastX, this.lastY, this.lastZ, this.lastYaw, this.lastPitch);
            super.g(n, ba);
            Bukkit.getServer().getPluginManager().callEvent((Event)new PlayerTeleportEvent((Player)handle.getBukkitEntity(), location, new Location((org.bukkit.World)this.world.getWorld(), this.locX, this.locY, this.locZ, this.yaw, this.pitch)));
            if (this instanceof IEntityHorsePet) {
                final Location location2 = this.getBukkitEntity().getLocation();
                this.setPosition(location2.getX(), location2.getY(), location2.getZ());
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutEntityTeleport((net.minecraft.server.v1_8_R2.Entity)this));
                if (ba > 0.0f) {
                    final float sin = MathHelper.sin(this.yaw * 0.017453292f);
                    final float cos = MathHelper.cos(this.yaw * 0.017453292f);
                    this.motX += -0.4f * sin * 0.0f;
                    this.motZ += 0.4f * cos * 0.0f;
                }
            }
        }
        if (this.JUMP_FIELD != null) {
            if (!this.isOnGround((net.minecraft.server.v1_8_R2.Entity)this) && this.pet.getType().getEntityPet().canFly()) {
                try {
                    if (this.pet.getOwner().isFlying()) {
                        this.pet.getOwner().setFlying(false);
                    }
                    if (this.JUMP_FIELD.getBoolean(handle)) {
                        this.motY = 0.5;
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else if (this.isOnGround((net.minecraft.server.v1_8_R2.Entity)this)) {
                try {
                    if (this.JUMP_FIELD.getBoolean(handle)) {
                        this.motY = 0.3;
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    protected boolean isOnGround(final net.minecraft.server.v1_8_R2.Entity entity) {
        return entity.getBukkitEntity().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid();
    }
    
    protected String z() {
        if (this.pet == null || CookieGadgets.getPetData().isPetSilent()) {
            return null;
        }
        final SoundEffect ambientSound = this.pet.getType().getEntityPet().getAmbientSound();
        if (ambientSound != null) {
            ambientSound.playSound(this.getEntity());
        }
        return null;
    }
    
    public CraftCreature getBukkitEntity() {
        if (super.bukkitEntity == null) {
            super.bukkitEntity = (CraftEntity)new CraftNMSCreature(super.world.getServer(), this);
        }
        return (CraftCreature)super.getBukkitEntity();
    }
    
    public boolean isInvulnerable(final DamageSource damageSource) {
        return true;
    }
    
    public void setCustomName(final String s) {
    }
    
    public void setCustomNameVisible(final boolean b) {
    }
    
    public boolean d(final int n, final net.minecraft.server.v1_8_R2.ItemStack itemStack) {
        return false;
    }
    
    public void setEquipment(final int n, final net.minecraft.server.v1_8_R2.ItemStack itemStack) {
    }
    
    public void die() {
    }
    
    public boolean damageEntity(final DamageSource damageSource, final float n) {
        return false;
    }
    
    public void setInvisible(final boolean b) {
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
