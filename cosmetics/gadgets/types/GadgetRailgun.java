

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.Location;
import java.util.Iterator;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.Firework;
import java.util.ArrayList;

public class GadgetRailgun extends Gadget
{
    private boolean activated;
    private ArrayList<Firework> fireworks;
    
    public GadgetRailgun(final UUID uuid) {
        super(uuid, GadgetType.RAIL_GUN);
        this.activated = false;
        this.fireworks = new ArrayList<Firework>();
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
        this.activated = true;
        this.spawnStraightLine(this.getPlayer().getLocation(), this.getPlayer().getLocation().clone().add(this.getPlayer().getLocation().clone().getDirection().multiply(100)), 25);
        new BukkitRunnable() {
            public void run() {
                GadgetRailgun.this.clearAll();
            }
        }.runTaskLaterAsynchronously((Plugin)CookieGadgets.getInstance(), 5L);
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
        this.activated = false;
        final Iterator<Firework> iterator = this.fireworks.iterator();
        while (iterator.hasNext()) {
            iterator.next().remove();
        }
    }
    
    private void spawnStraightLine(final Location location, final Location location2, final int n) {
        final double abs = Math.abs(location2.distance(location));
        for (int i = -1; i < n; ++i) {
            final double n2 = i / abs;
            final Location location3 = new Location(location.getWorld(), (1.0 - n2) * location.getX() + n2 * (location2.getX() + 0.5), (1.0 - n2) * location.getY() + n2 * (location2.getY() + 0.5), (1.0 - n2) * location.getZ() + n2 * (location2.getZ() + 0.5));
            final Firework e = (Firework)location3.getWorld().spawn(location3, (Class)Firework.class);
            ParticleEffect.FIREWORKS_SPARK.display(location3, (float)CookieGadgets.random().nextGaussian() * 0.05f, (float)(-(location3.getZ() * 1.149999976158142)) * 0.5f, (float)CookieGadgets.random().nextGaussian() * 0.05f);
            this.fireworks.add(e);
        }
    }
}
