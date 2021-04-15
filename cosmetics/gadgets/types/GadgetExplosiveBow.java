

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Arrow;

public class GadgetExplosiveBow extends Gadget
{
    private Arrow arrow;
    private TNTPrimed TNT;
    private boolean activated;
    
    public GadgetExplosiveBow(final UUID uuid) {
        super(uuid, GadgetType.EXPLOSIVE_BOW);
        this.activated = false;
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        final Arrow arrow = (Arrow)this.getPlayer().launchProjectile((Class)Arrow.class, this.getPlayer().getLocation().getDirection().multiply(1.25).add(new Vector(0.0, 0.1, 0.0)));
        arrow.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.arrow = arrow;
        this.activated = true;
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!GadgetExplosiveBow.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetExplosiveBow.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetExplosiveBow.this.getPlayer()).getCurrentGadget().getType() != GadgetExplosiveBow.this.getType()) {
                    this.step = 7;
                    GadgetExplosiveBow.this.onClear();
                    this.cancel();
                    return;
                }
                if (this.step <= 6) {
                    ParticleEffect.FLAME.display(arrow.getLocation(), 0.3f, 10);
                    ParticleEffect.LAVA.display(arrow.getLocation(), 1.0f, 3);
                    SoundEffect.ENTITY_ARROW_SHOOT.playSound((Entity)arrow);
                }
                else {
                    final TNTPrimed tntPrimed = (TNTPrimed)GadgetExplosiveBow.this.getPlayer().getWorld().spawn(arrow.getLocation(), (Class)TNTPrimed.class);
                    tntPrimed.setFuseTicks(20);
                    tntPrimed.setVelocity(arrow.getLocation().getDirection().multiply(0.4));
                    tntPrimed.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    GadgetExplosiveBow.access$0(GadgetExplosiveBow.this, tntPrimed);
                    if (GadgetExplosiveBow.this.arrow != null) {
                        GadgetExplosiveBow.this.arrow.remove();
                    }
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 6L, 5L);
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        if (this.TNT != null) {
            this.TNT.remove();
        }
        this.TNT = null;
        if (this.arrow != null) {
            this.arrow.remove();
        }
        this.arrow = null;
        this.activated = false;
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onItemFrameBreak(final HangingBreakEvent hangingBreakEvent) {
        if (hangingBreakEvent.getCause() == HangingBreakEvent.RemoveCause.EXPLOSION) {
            if (this.TNT == null) {
                return;
            }
            if (this.TNT.getWorld() == hangingBreakEvent.getEntity().getWorld()) {
                if (this.TNT.getLocation().distance(hangingBreakEvent.getEntity().getLocation()) > 15.0) {
                    return;
                }
                hangingBreakEvent.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onVehicleDestroy(final VehicleDestroyEvent vehicleDestroyEvent) {
        if (this.TNT == null) {
            return;
        }
        if (this.TNT.getWorld() == vehicleDestroyEvent.getVehicle().getWorld()) {
            if (this.TNT.getLocation().distance(vehicleDestroyEvent.getVehicle().getLocation()) > 10.0) {
                return;
            }
            vehicleDestroyEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityExplode(final EntityExplodeEvent entityExplodeEvent) {
        if (this.TNT != null && this.TNT.equals(entityExplodeEvent.getEntity())) {
            ParticleEffect.EXPLOSION_LARGE.display(entityExplodeEvent.getEntity().getLocation(), 0.1f);
            ParticleEffect.SMOKE_LARGE.display(entityExplodeEvent.getEntity().getLocation());
            SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(entityExplodeEvent.getEntity());
            entityExplodeEvent.setCancelled(true);
            this.clearAll();
        }
    }
    
    static /* synthetic */ void access$0(final GadgetExplosiveBow gadgetExplosiveBow, final TNTPrimed tnt) {
        gadgetExplosiveBow.TNT = tnt;
    }
}
