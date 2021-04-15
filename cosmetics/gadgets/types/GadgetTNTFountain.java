

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.hanging.HangingBreakEvent;
import java.util.Iterator;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.entity.TNTPrimed;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.block.Block;
import org.bukkit.Material;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.Entity;
import java.util.ArrayList;

public class GadgetTNTFountain extends Gadget
{
    private boolean activated;
    private ArrayList<Entity> tnt;
    
    public GadgetTNTFountain(final UUID uuid) {
        super(uuid, GadgetType.TNT_FOUNTAIN);
        this.activated = false;
        this.tnt = new ArrayList<Entity>();
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        final Block targetBlock = BlockUtil.getTargetBlock(this.getPlayer(), 6);
        if (targetBlock.isEmpty() || targetBlock.getType().equals((Object)Material.AIR)) {
            this.getPlayer().sendMessage(MessageType.NOT_ON_GROUND.getFormatMessage());
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.activated = true;
        new BukkitRunnable() {
            int step = 0;
            private final /* synthetic */ Block val$b = BlockUtil.getTargetBlock(this.getPlayer(), 6);
            
            public void run() {
                ++this.step;
                if (!GadgetTNTFountain.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetTNTFountain.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetTNTFountain.this.getPlayer()).getCurrentGadget().getType() != GadgetTNTFountain.this.getType()) {
                    this.step = 26;
                    GadgetTNTFountain.this.onClear();
                    this.cancel();
                    return;
                }
                if (this.step <= 25) {
                    final TNTPrimed e = (TNTPrimed)this.val$b.getLocation().getWorld().spawn(this.val$b.getLocation().add(0.0, 0.25, 0.0), (Class)TNTPrimed.class);
                    e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    e.setVelocity(new Vector((CookieGadgets.random().nextDouble() - 0.5) * 0.7, 1.25, (CookieGadgets.random().nextDouble() - 0.5) * 0.7));
                    GadgetTNTFountain.this.tnt.add(e);
                }
                else {
                    GadgetTNTFountain.this.clearAll();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 8L);
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
        final Iterator<Entity> iterator = this.tnt.iterator();
        while (iterator.hasNext()) {
            iterator.next().remove();
        }
        this.tnt.clear();
        this.activated = false;
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onItemFrameBreak(final HangingBreakEvent hangingBreakEvent) {
        if (hangingBreakEvent.getCause() == HangingBreakEvent.RemoveCause.EXPLOSION) {
            for (final Entity entity : this.tnt) {
                if (entity.getWorld() == hangingBreakEvent.getEntity().getWorld()) {
                    if (entity.getLocation().distance(hangingBreakEvent.getEntity().getLocation()) > 15.0) {
                        return;
                    }
                    hangingBreakEvent.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onVehicleDestroy(final VehicleDestroyEvent vehicleDestroyEvent) {
        for (final Entity entity : this.tnt) {
            if (entity.getWorld() == vehicleDestroyEvent.getVehicle().getWorld()) {
                if (entity.getLocation().distance(vehicleDestroyEvent.getVehicle().getLocation()) > 10.0) {
                    return;
                }
                vehicleDestroyEvent.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityExplode(final EntityExplodeEvent entityExplodeEvent) {
        if (this.tnt.contains(entityExplodeEvent.getEntity())) {
            entityExplodeEvent.setCancelled(true);
            ParticleEffect.EXPLOSION_LARGE.display(entityExplodeEvent.getEntity().getLocation());
            ParticleEffect.SMOKE_LARGE.display(entityExplodeEvent.getEntity().getLocation(), 2.0f, 2.0f, 2.0f, 0.0f, 5);
            SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(entityExplodeEvent.getEntity());
            this.tnt.remove(entityExplodeEvent.getEntity());
        }
    }
}
