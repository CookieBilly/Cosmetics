

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.util.Vector;
import org.bukkit.entity.Arrow;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;

public class MorphSkeleton extends Morph
{
    public MorphSkeleton(final UUID uuid) {
        super(uuid, MorphType.SKELETON);
    }
    
    public void onClick() {
        final Arrow arrow = (Arrow)this.getPlayer().launchProjectile((Class)Arrow.class, this.getPlayer().getLocation().getDirection().multiply(1.25).add(new Vector(0.0, 0.1, 0.0)));
        arrow.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (arrow.isOnGround() || arrow == null || this.step >= 40) {
                    arrow.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 1L, 5L);
    }
    
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
    }
}
