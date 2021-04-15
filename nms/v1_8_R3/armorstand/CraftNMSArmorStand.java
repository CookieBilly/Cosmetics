// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.nms.v1_8_R3.armorstand;

import org.bukkit.EntityEffect;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.bukkit.entity.Entity;
import java.util.Collection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.EulerAngle;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;

public class CraftNMSArmorStand extends CraftArmorStand
{
    public CraftNMSArmorStand(final CraftServer craftServer, final EntityNMSArmorStand entityNMSArmorStand) {
        super(craftServer, (EntityArmorStand)entityNMSArmorStand);
    }
    
    public CraftNMSArmorStand(final CraftServer craftServer, final ArmorStandFollower armorStandFollower) {
        super(craftServer, (EntityArmorStand)armorStandFollower);
    }
    
    public CraftNMSArmorStand(final CraftServer craftServer, final NMSMiniature nmsMiniature) {
        super(craftServer, (EntityArmorStand)nmsMiniature);
    }
    
    public void setItemInHand(final ItemStack itemStack) {
    }
    
    public void setBoots(final ItemStack itemStack) {
    }
    
    public void setLeggings(final ItemStack itemStack) {
    }
    
    public void setChestplate(final ItemStack itemStack) {
    }
    
    public void setHelmet(final ItemStack itemStack) {
    }
    
    public void setBodyPose(final EulerAngle eulerAngle) {
    }
    
    public void setLeftArmPose(final EulerAngle eulerAngle) {
    }
    
    public void setRightArmPose(final EulerAngle eulerAngle) {
    }
    
    public void setLeftLegPose(final EulerAngle eulerAngle) {
    }
    
    public void setRightLegPose(final EulerAngle eulerAngle) {
    }
    
    public void setHeadPose(final EulerAngle eulerAngle) {
    }
    
    public void setBasePlate(final boolean b) {
    }
    
    public void setGravity(final boolean b) {
    }
    
    public void setVisible(final boolean b) {
    }
    
    public void setArms(final boolean b) {
    }
    
    public void setSmall(final boolean b) {
    }
    
    public void setMarker(final boolean b) {
    }
    
    public boolean addPotionEffect(final PotionEffect potionEffect) {
        return false;
    }
    
    public boolean addPotionEffect(final PotionEffect potionEffect, final boolean b) {
        return false;
    }
    
    public boolean addPotionEffects(final Collection<PotionEffect> collection) {
        return false;
    }
    
    public void setRemoveWhenFarAway(final boolean b) {
    }
    
    public void setCanPickupItems(final boolean b) {
    }
    
    public boolean setLeashHolder(final Entity entity) {
        return false;
    }
    
    public void setGliding(final boolean b) {
    }
    
    public void setAI(final boolean b) {
    }
    
    public void setCollidable(final boolean b) {
    }
    
    public void setVelocity(final Vector vector) {
    }
    
    public boolean teleport(final Location location) {
        return false;
    }
    
    public boolean teleport(final Location location, final PlayerTeleportEvent.TeleportCause teleportCause) {
        return false;
    }
    
    public boolean teleport(final Entity entity) {
        return false;
    }
    
    public boolean teleport(final Entity entity, final PlayerTeleportEvent.TeleportCause teleportCause) {
        return false;
    }
    
    public void setFireTicks(final int n) {
    }
    
    public void remove() {
    }
    
    public void setMomentum(final Vector vector) {
    }
    
    public boolean setPassenger(final Entity entity) {
        return false;
    }
    
    public boolean eject() {
        return false;
    }
    
    public void setTicksLived(final int n) {
    }
    
    public void playEffect(final EntityEffect entityEffect) {
    }
    
    public boolean leaveVehicle() {
        return false;
    }
    
    public void setCustomName(final String s) {
    }
    
    public void setCustomNameVisible(final boolean b) {
    }
    
    public void setGlowing(final boolean b) {
    }
    
    public void setInvulnerable(final boolean b) {
    }
    
    public void setSilent(final boolean b) {
    }
}
