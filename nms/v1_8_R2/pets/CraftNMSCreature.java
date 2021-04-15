

package ws.billy.CookieGadgets.nms.v1_8_R2.pets;

import org.bukkit.EntityEffect;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import java.util.Collection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.entity.Entity;
import net.minecraft.server.v1_8_R2.EntityCreature;
import ws.billy.CookieGadgets.nms.v1_8_R2.pets.entity.EntityPet;
import org.bukkit.craftbukkit.v1_8_R2.CraftServer;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftCreature;

public class CraftNMSCreature extends CraftCreature
{
    public CraftNMSCreature(final CraftServer craftServer, final EntityPet entityPet) {
        super(craftServer, (EntityCreature)entityPet);
    }
    
    public void setHealth(final double n) {
    }
    
    public void setMaxHealth(final double n) {
    }
    
    public void damage(final double n) {
    }
    
    public void damage(final double n, final Entity entity) {
    }
    
    public void setLastDamage(final double n) {
    }
    
    public void setRemoveWhenFarAway(final boolean b) {
    }
    
    public void setCanPickupItems(final boolean b) {
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
    
    public void setPersistent(final boolean b) {
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
    
    public void setGravity(final boolean b) {
    }
    
    public void setPortalCooldown(final int n) {
    }
}
