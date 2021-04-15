

package ws.billy.CookieGadgets.nms.v1_12_R1.pets.entity;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.DamageSource;
import ws.billy.CookieGadgets.nms.v1_12_R1.pets.CraftNMSCreature;
import ws.billy.CookieGadgets.CookieGadgets;
import net.minecraft.server.v1_12_R1.SoundEffect;
import org.bukkit.block.BlockFace;
import net.minecraft.server.v1_12_R1.MathHelper;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityTeleport;
import org.bukkit.event.player.PlayerTeleportEvent;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityHorsePet;
import java.util.Iterator;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IFlyablePet;
import org.bukkit.event.Event;
import ws.billy.CookieGadgets.api.event.pets.PetMoveEvent;
import org.bukkit.Bukkit;
import net.minecraft.server.v1_12_R1.EnumMoveType;
import org.bukkit.Location;
import net.minecraft.server.v1_12_R1.EnumItemSlot;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PacketPlayOutMount;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import org.bukkit.entity.Entity;
import net.minecraft.server.v1_12_R1.GenericAttributes;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.World;
import java.lang.reflect.Field;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import ws.billy.CookieGadgets.nms.interfaces.pets.NMSPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import net.minecraft.server.v1_12_R1.IAnimal;
import net.minecraft.server.v1_12_R1.EntityCreature;

public abstract class EntityPet extends EntityCreature implements IAnimal, IEntityPet, NMSPet
{
    private IPet pet;
    private double moveSpeed;
    private double rideSpeed;
    private boolean floatDown;
    protected Field FIELD_JUMP;
    
    public EntityPet(final World world) {
        super(world);
        this.FIELD_JUMP = null;
    }
    
    public EntityPet(final World world, final IPet pet) {
        super(world);
        this.FIELD_JUMP = null;
        this.pet = pet;
        this.collides = false;
        this.noclip = false;
        this.resetEntitySize();
        if (this.FIELD_JUMP == null) {
            try {
                (this.FIELD_JUMP = EntityLiving.class.getDeclaredField("bd")).setAccessible(true);
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
            ((CraftEntity)this.pet.getOwner()).getHandle().startRiding(((CraftEntity)this.getBukkitEntity()).getHandle());
        }
    }
    
    public void removePassengerNMS() {
        final CraftCreature bukkitEntity = this.getBukkitEntity();
        if (!((CraftEntity)bukkitEntity).getHandle().isVehicle()) {
            return;
        }
        ((CraftEntity)bukkitEntity).getPassenger().leaveVehicle();
    }
    
    public void setAsHatNMS() {
        if (this.pet == null || this.pet.getOwner() == null || !this.pet.getOwner().isOnline()) {
            return;
        }
        final CraftCreature bukkitEntity = this.getBukkitEntity();
        final CraftPlayer craftPlayer = (CraftPlayer)this.pet.getOwner();
        this.removeHatNMS();
        craftPlayer.getHandle().passengers.add(0, ((CraftEntity)bukkitEntity).getHandle());
        craftPlayer.getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutMount((net.minecraft.server.v1_12_R1.Entity)craftPlayer.getHandle()));
    }
    
    public void removeHatNMS() {
        if (this.pet == null || this.pet.getOwner() == null || !this.pet.getOwner().isOnline()) {
            return;
        }
        final CraftPlayer craftPlayer = (CraftPlayer)this.pet.getOwner();
        craftPlayer.getHandle().passengers.clear();
        craftPlayer.getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutMount((net.minecraft.server.v1_12_R1.Entity)craftPlayer.getHandle()));
    }
    
    public boolean isDeadNMS() {
        return super.dead;
    }
    
    public void killEntityNMS() {
        super.dead = true;
    }
    
    public void setSilentNMS(final boolean silent) {
        super.setSilent(silent);
    }
    
    public boolean isSilentNMS() {
        return super.isSilent();
    }
    
    public void setEquipmentNMS(final EnumArmorType enumArmorType, final ItemStack itemStack) {
        final net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);
        switch (enumArmorType) {
            case HELMET: {
                super.setSlot(EnumItemSlot.HEAD, nmsCopy);
                break;
            }
            case CHESTPLATE: {
                super.setSlot(EnumItemSlot.CHEST, nmsCopy);
                break;
            }
            case LEGGINGS: {
                super.setSlot(EnumItemSlot.LEGS, nmsCopy);
                break;
            }
            case BOOTS: {
                super.setSlot(EnumItemSlot.FEET, nmsCopy);
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
    
    protected void i() {
        super.i();
        this.initDatawatchers();
    }
    
    protected void initDatawatchers() {
    }
    
    public void f(final double n, final double n2, final double n3) {
    }
    
    public void move(final EnumMoveType enumMoveType, final double n, final double n2, final double n3) {
        if (this.pet == null) {
            return;
        }
        if (this.pet.getOwner() == null) {
            return;
        }
        Bukkit.getServer().getPluginManager().callEvent((Event)new PetMoveEvent(this, PetMoveEvent.Cause.RIDE));
        super.move(enumMoveType, n, n2, n3);
    }
    
    public void B_() {
        super.B_();
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
            this.motY *= 0.4;
        }
        final Player owner = this.pet.getOwner();
        if (this.pet.isHat()) {
            final float yaw = owner.getLocation().getYaw();
            this.yaw = yaw;
            this.lastYaw = yaw;
        }
        final double value = this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue();
        if (this.isOwnerRiding()) {
            if (value != this.rideSpeed) {
                this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(this.rideSpeed);
            }
        }
        else if (value != this.moveSpeed) {
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(this.moveSpeed);
        }
    }
    
    protected boolean isOwnerRiding() {
        if (this.passengers.size() == 0) {
            return false;
        }
        final EntityPlayer handle = ((CraftPlayer)this.pet.getOwner()).getHandle();
        final Iterator<net.minecraft.server.v1_12_R1.Entity> iterator = this.passengers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getUniqueID().equals(handle.getUniqueID())) {
                return true;
            }
        }
        return false;
    }
    
    public void a(float n, final float n2, float bg) {
        if (this.pet == null) {
            return;
        }
        if (this.pet.getOwner() == null) {
            return;
        }
        if (this.pet == null || this.passengers.isEmpty() || !this.isOwnerRiding()) {
            this.P = 0.5f;
            this.aR = 0.02f;
            super.a(n, n2, bg);
            return;
        }
        final EntityPlayer handle = ((CraftPlayer)this.pet.getOwner()).getHandle();
        this.yaw = handle.yaw;
        this.lastYaw = this.yaw;
        this.pitch = handle.pitch * 0.5f;
        this.setYawPitch(this.yaw, this.pitch);
        final float yaw = this.yaw;
        this.aM = yaw;
        this.aO = yaw;
        this.P = 1.0f;
        n = handle.be * 0.5f;
        bg = handle.bg;
        if (bg <= 0.0f) {
            bg *= (float)(this.rideSpeed * 0.25);
        }
        else {
            bg *= (float)this.rideSpeed;
        }
        if (!(this instanceof IEntityHorsePet)) {
            n *= 0.75f;
        }
        this.k((float)this.rideSpeed);
        if (!this.world.isClientSide) {
            Bukkit.getServer().getPluginManager().callEvent((Event)new PetMoveEvent(this, PetMoveEvent.Cause.RIDE));
            final Location location = new Location((org.bukkit.World)this.world.getWorld(), this.lastX, this.lastY, this.lastZ, this.lastYaw, this.lastPitch);
            super.a(n, n2, bg);
            Bukkit.getServer().getPluginManager().callEvent((Event)new PlayerTeleportEvent((Player)handle.getBukkitEntity(), location, new Location((org.bukkit.World)this.world.getWorld(), this.locX, this.locY, this.locZ, this.yaw, this.pitch)));
            if (this instanceof IEntityHorsePet) {
                final Location location2 = this.getBukkitEntity().getLocation();
                this.setPosition(location2.getX(), location2.getY(), location2.getZ());
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutEntityTeleport((net.minecraft.server.v1_12_R1.Entity)this));
                if (bg > 0.0f) {
                    final float sin = MathHelper.sin((float)(this.yaw * 0.017453292));
                    final float cos = MathHelper.cos((float)(this.yaw * 0.017453292));
                    this.motX += -0.4 * sin * 0.0;
                    this.motZ += 0.4 * cos * 0.0;
                }
                this.aF = this.aG;
                final double n3 = this.locX - this.lastX;
                final double n4 = this.locZ - this.lastZ;
                float n5 = MathHelper.sqrt(n3 * n3 + n4 * n4) * 4.0f;
                if (n5 > 1.0) {
                    n5 = 1.0f;
                }
                this.aG += (n5 - this.aG) * 0.4f;
                this.aH += this.aG;
            }
        }
        if (this.FIELD_JUMP != null) {
            if (!this.isOnGround((net.minecraft.server.v1_12_R1.Entity)this) && this.pet.getType().getEntityPet().canFly()) {
                try {
                    if (this.pet.getOwner().isFlying()) {
                        this.pet.getOwner().setFlying(false);
                    }
                    if (this.FIELD_JUMP.getBoolean(handle)) {
                        this.motY = 0.5;
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else if (this.isOnGround((net.minecraft.server.v1_12_R1.Entity)this)) {
                try {
                    if (this.FIELD_JUMP.getBoolean(handle)) {
                        this.motY = 0.3;
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    protected boolean isOnGround(final net.minecraft.server.v1_12_R1.Entity entity) {
        return entity.getBukkitEntity().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid();
    }
    
    protected SoundEffect F() {
        if (this.pet == null || super.isSilent() || CookieGadgets.getPetData().isPetSilent()) {
            return null;
        }
        final ws.billy.CookieGadgets.utils.SoundEffect ambientSound = this.pet.getType().getEntityPet().getAmbientSound();
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
    
    public boolean isCollidable() {
        return false;
    }
    
    public void setCustomName(final String s) {
    }
    
    public void setCustomNameVisible(final boolean b) {
    }
    
    public boolean c(final int n, final net.minecraft.server.v1_12_R1.ItemStack itemStack) {
        return false;
    }
    
    public void setSlot(final EnumItemSlot enumItemSlot, final net.minecraft.server.v1_12_R1.ItemStack itemStack) {
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
    
    public NBTTagCompound save(final NBTTagCompound nbtTagCompound) {
        return nbtTagCompound;
    }
    
    public void f(final NBTTagCompound nbtTagCompound) {
    }
}
