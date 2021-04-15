

package ws.billy.CookieGadgets.nms.v1_16_R3.pets.entity;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.DamageSource;
import ws.billy.CookieGadgets.nms.v1_16_R3.pets.CraftNMSCreature;
import ws.billy.CookieGadgets.CookieGadgets;
import net.minecraft.server.v1_16_R3.SoundEffect;
import org.bukkit.block.BlockFace;
import net.minecraft.server.v1_16_R3.MathHelper;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityTeleport;
import org.bukkit.event.player.PlayerTeleportEvent;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.types.IEntityHorsePet;
import java.util.Iterator;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IFlyablePet;
import org.bukkit.event.Event;
import ws.billy.CookieGadgets.api.event.pets.PetMoveEvent;
import org.bukkit.Bukkit;
import net.minecraft.server.v1_16_R3.Vec3D;
import net.minecraft.server.v1_16_R3.EnumMoveType;
import org.bukkit.Location;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.EnumArmorType;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutMount;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftLivingEntity;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.EntitySize;
import ws.billy.CookieGadgets.utils.cosmetics.pets.GEntity;
import org.bukkit.entity.Entity;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.EntityLiving;
import net.minecraft.server.v1_16_R3.World;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import java.lang.reflect.Field;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IPet;
import ws.billy.CookieGadgets.nms.interfaces.pets.NMSPet;
import ws.billy.CookieGadgets.utils.cosmetics.pets.entity.IEntityPet;
import net.minecraft.server.v1_16_R3.EntityInsentient;

public abstract class EntityPet extends EntityInsentient implements IEntityPet, NMSPet
{
    private IPet pet;
    private double moveSpeed;
    private double rideSpeed;
    private boolean floatDown;
    protected Field FIELD_JUMP;
    private CraftEntity bukkitEntity;
    
    public EntityPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world) {
        super((EntityTypes)entityTypes, world);
        this.FIELD_JUMP = null;
    }
    
    public EntityPet(final EntityTypes<? extends EntityInsentient> entityTypes, final World world, final IPet pet) {
        super((EntityTypes)entityTypes, world);
        this.FIELD_JUMP = null;
        this.pet = pet;
        this.collides = false;
        this.setInvisible(this.noclip = false);
        this.resetEntitySize();
        if (this.FIELD_JUMP == null) {
            try {
                (this.FIELD_JUMP = EntityLiving.class.getDeclaredField("jumping")).setAccessible(true);
            }
            catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            }
        }
        this.getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(20.0);
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
    
    public NMSPet getEntityNMS() {
        return this;
    }
    
    protected void resetEntitySize() {
        final EntitySize entitySize = this.getClass().getAnnotation(EntitySize.class);
    }
    
    protected void resetEntitySize(final boolean b) {
        final EntitySize entitySize = this.getClass().getAnnotation(EntitySize.class);
    }
    
    public void setEntitySize(final float n, final float n2) {
    }
    
    public void setCustomNameNMS(final String s) {
        if (!super.getCustomNameVisible()) {
            super.setCustomNameVisible(true);
        }
        super.setCustomName((IChatBaseComponent)new ChatComponentText(s));
    }
    
    public String getCustomNameNMS() {
        return super.getCustomName().toString();
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
        final CraftLivingEntity bukkitEntity = this.getBukkitEntity();
        if (!((CraftEntity)bukkitEntity).getHandle().isVehicle()) {
            return;
        }
        ((CraftEntity)bukkitEntity).getPassenger().leaveVehicle();
    }
    
    public void setAsHatNMS() {
        if (this.pet == null || this.pet.getOwner() == null || !this.pet.getOwner().isOnline()) {
            return;
        }
        final CraftLivingEntity bukkitEntity = this.getBukkitEntity();
        final CraftPlayer craftPlayer = (CraftPlayer)this.pet.getOwner();
        this.removeHatNMS();
        craftPlayer.getHandle().passengers.add(0, ((CraftEntity)bukkitEntity).getHandle());
        craftPlayer.getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutMount((net.minecraft.server.v1_16_R3.Entity)craftPlayer.getHandle()));
    }
    
    public void removeHatNMS() {
        if (this.pet == null || this.pet.getOwner() == null || !this.pet.getOwner().isOnline()) {
            return;
        }
        final CraftPlayer craftPlayer = (CraftPlayer)this.pet.getOwner();
        craftPlayer.getHandle().passengers.clear();
        craftPlayer.getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutMount((net.minecraft.server.v1_16_R3.Entity)craftPlayer.getHandle()));
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
        final net.minecraft.server.v1_16_R3.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);
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
    
    protected void initDatawatcher() {
        super.initDatawatcher();
        this.initDataWatcher();
    }
    
    protected void initDataWatcher() {
    }
    
    public void f(final double n, final double n2, final double n3) {
    }
    
    public void move(final EnumMoveType enumMoveType, final Vec3D vec3D) {
        if (this.pet == null) {
            return;
        }
        if (this.pet.getOwner() == null) {
            return;
        }
        Bukkit.getServer().getPluginManager().callEvent((Event)new PetMoveEvent(this, PetMoveEvent.Cause.RIDE));
        super.move(enumMoveType, vec3D);
    }
    
    public void tick() {
        super.tick();
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
        if (this instanceof IFlyablePet && this.pet.isOwnerRiding() && this.floatDown && !this.onGround) {
            this.setMot(this.getMot().x, this.getMot().y * 0.4, this.getMot().z);
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
        final Iterator<net.minecraft.server.v1_16_R3.Entity> iterator = this.passengers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getUniqueID().equals(handle.getUniqueID())) {
                return true;
            }
        }
        return false;
    }
    
    public void g(final Vec3D vec3D) {
        if (this.pet == null) {
            return;
        }
        if (this.pet.getOwner() == null) {
            return;
        }
        if (this.pet == null || this.passengers.isEmpty() || !this.isOwnerRiding()) {
            this.G = 0.5f;
            this.aL = 0.02f;
            super.g(vec3D);
            return;
        }
        final EntityPlayer handle = ((CraftPlayer)this.pet.getOwner()).getHandle();
        this.yaw = handle.yaw;
        this.lastYaw = this.yaw;
        this.pitch = handle.pitch * 0.5f;
        this.setYawPitch(this.yaw, this.pitch);
        final float yaw = this.yaw;
        this.aA = yaw;
        this.aC = yaw;
        this.G = 1.0f;
        double n = handle.aR * 0.5f;
        final double y = vec3D.y;
        final double n2 = handle.aT;
        double n3;
        if (n2 <= 0.0) {
            n3 = n2 * (this.rideSpeed * 0.25);
        }
        else {
            n3 = n2 * this.rideSpeed;
        }
        if (!(this instanceof IEntityHorsePet)) {
            n *= 0.75;
        }
        this.q((float)this.rideSpeed);
        if (!this.world.isClientSide) {
            Bukkit.getServer().getPluginManager().callEvent((Event)new PetMoveEvent(this, PetMoveEvent.Cause.RIDE));
            final Location location = new Location((org.bukkit.World)this.world.getWorld(), this.lastX, this.lastY, this.lastZ, this.lastYaw, this.lastPitch);
            super.g(new Vec3D(n, y, n3));
            Bukkit.getServer().getPluginManager().callEvent((Event)new PlayerTeleportEvent((Player)handle.getBukkitEntity(), location, new Location((org.bukkit.World)this.world.getWorld(), this.locX(), this.locY(), this.locZ(), this.yaw, this.pitch)));
            if (this instanceof IEntityHorsePet) {
                final Location location2 = this.getBukkitEntity().getLocation();
                this.setPosition(location2.getX(), location2.getY(), location2.getZ());
                handle.playerConnection.sendPacket((Packet)new PacketPlayOutEntityTeleport((net.minecraft.server.v1_16_R3.Entity)this));
                if (n3 > 0.0) {
                    this.setMot(this.getMot().add(-0.4f * MathHelper.sin(this.yaw * 0.017453292f) * 0.0, 0.0, 0.4f * MathHelper.cos(this.yaw * 0.017453292f) * 0.0));
                }
            }
            this.au = this.av;
            final double n4 = this.locX() - this.lastX;
            final double n5 = this.locZ() - this.lastZ;
            float n6 = MathHelper.sqrt(n4 * n4 + n5 * n5) * 4.0f;
            if (n6 > 1.0f) {
                n6 = 1.0f;
            }
            this.av += (n6 - this.av) * 0.4f;
            this.aw += this.av;
        }
        if (this.FIELD_JUMP != null) {
            if (!this.isOnGround((net.minecraft.server.v1_16_R3.Entity)this) && this.pet.getType().getEntityPet().canFly()) {
                try {
                    if (this.pet.getOwner().isFlying()) {
                        this.pet.getOwner().setFlying(false);
                    }
                    if (this.FIELD_JUMP.getBoolean(handle)) {
                        this.setMot(vec3D.x, 0.5, vec3D.z);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else if (this.isOnGround((net.minecraft.server.v1_16_R3.Entity)this)) {
                try {
                    if (this.FIELD_JUMP.getBoolean(handle)) {
                        this.setMot(vec3D.x, 0.3, vec3D.z);
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    protected boolean isOnGround(final net.minecraft.server.v1_16_R3.Entity entity) {
        return entity.getBukkitEntity().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid();
    }
    
    protected SoundEffect getSoundAmbient() {
        if (this.pet == null || super.isSilent() || CookieGadgets.getPetData().isPetSilent()) {
            return null;
        }
        final ws.billy.CookieGadgets.utils.SoundEffect ambientSound = this.pet.getType().getEntityPet().getAmbientSound();
        if (ambientSound != null) {
            ambientSound.playSound(this.getEntity());
        }
        return null;
    }
    
    public CraftLivingEntity getBukkitEntity() {
        if (this.bukkitEntity == null) {
            this.bukkitEntity = (CraftEntity)new CraftNMSCreature(super.world.getServer(), this);
        }
        return (CraftLivingEntity)this.bukkitEntity;
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
    
    public boolean a_(final int n, final net.minecraft.server.v1_16_R3.ItemStack itemStack) {
        return false;
    }
    
    public void setSlot(final EnumItemSlot enumItemSlot, final net.minecraft.server.v1_16_R3.ItemStack itemStack) {
    }
    
    public void die() {
    }
    
    public boolean damageEntity(final DamageSource damageSource, final float n) {
        return false;
    }
    
    public void setInvisible(final boolean b) {
    }
    
    public boolean a_(final NBTTagCompound nbtTagCompound) {
        return false;
    }
    
    public boolean d(final NBTTagCompound nbtTagCompound) {
        return false;
    }
    
    public NBTTagCompound save(final NBTTagCompound nbtTagCompound) {
        return nbtTagCompound;
    }
    
    public void load(final NBTTagCompound nbtTagCompound) {
    }
}
