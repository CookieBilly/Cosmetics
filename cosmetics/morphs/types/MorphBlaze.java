

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.hanging.HangingBreakEvent;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;
import org.bukkit.entity.Fireball;

public class MorphBlaze extends Morph
{
    private Fireball fireball;
    private boolean activated;
    
    public MorphBlaze(final UUID uuid) {
        super(uuid, MorphType.BLAZE);
        this.activated = false;
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.MORPH_SKILL_IS_ACTIVATED.getFormatMessage().replace("{MORPH}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        return true;
    }
    
    public void onClick() {
        this.activated = true;
        final Fireball fireball = (Fireball)this.getPlayer().launchProjectile((Class)Fireball.class);
        fireball.setIsIncendiary(false);
        fireball.setYield(0.0f);
        fireball.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.fireball = fireball;
        new BukkitRunnable() {
            int time = 0;
            
            public void run() {
                ++this.time;
                if (fireball.isDead() || this.time >= 100) {
                    MorphBlaze.this.clearAll();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 1L);
    }
    
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        if (this.fireball != null && this.fireball.isValid()) {
            SoundEffect.ENTITY_DRAGON_FIREBALL_EXPLODE.playSound(this.fireball.getLocation());
            this.fireball.remove();
        }
        this.fireball = null;
        this.activated = false;
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onItemFrameBreak(final HangingBreakEvent hangingBreakEvent) {
        if (this.fireball == null) {
            return;
        }
        if (hangingBreakEvent.getCause() == HangingBreakEvent.RemoveCause.ENTITY && this.fireball.getWorld() == hangingBreakEvent.getEntity().getWorld()) {
            if (this.fireball.getLocation().distance(hangingBreakEvent.getEntity().getLocation()) > 15.0) {
                return;
            }
            hangingBreakEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onVehicleDestroy(final VehicleDestroyEvent vehicleDestroyEvent) {
        if (this.fireball == null) {
            return;
        }
        if (this.fireball.getWorld() == vehicleDestroyEvent.getVehicle().getWorld() && this.fireball.getLocation().distance(vehicleDestroyEvent.getVehicle().getLocation()) > 10.0) {
            return;
        }
        vehicleDestroyEvent.setCancelled(true);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFireballHit(final ProjectileHitEvent projectileHitEvent) {
        if (this.fireball == null) {
            return;
        }
        if (!(projectileHitEvent.getEntity() instanceof Fireball)) {
            return;
        }
        if (this.fireball.getWorld() != projectileHitEvent.getEntity().getWorld()) {
            return;
        }
        if (projectileHitEvent.getEntity().hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            SoundEffect.ENTITY_DRAGON_FIREBALL_EXPLODE.playSound(this.fireball.getLocation());
        }
    }
}
