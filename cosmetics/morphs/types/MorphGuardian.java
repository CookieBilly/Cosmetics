

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import java.util.Iterator;
import org.bukkit.block.Block;
import java.util.List;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;

public class MorphGuardian extends Morph
{
    public MorphGuardian(final UUID uuid) {
        super(uuid, MorphType.GUARDIAN);
    }
    
    public void onClick() {
        final Location add = this.getPlayer().getLocation().clone().add(0.0, 0.6, 0.0);
        this.shootLaser(add, add.clone().add(add.getDirection().multiply(100)), 80, this.getPlayer());
    }
    
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
    }
    
    private void shootLaser(final Location location, final Location location2, final int n, final Player player) {
        final double abs = Math.abs(location2.distance(location));
        final List livingEntities = location.getWorld().getLivingEntities();
        for (int i = -1; i < n; ++i) {
            final double n2 = i / 10.0 / abs;
            final Location location3 = new Location(location.getWorld(), (1.0 - n2) * location.getX() + n2 * (location2.getX() + 0.5), (1.0 - n2) * location.getY() + n2 * (location2.getY() + 0.5), (1.0 - n2) * location.getZ() + n2 * (location2.getZ() + 0.5));
            ParticleEffect.REDSTONE.displayColor(location3, 255, 0, 0);
            if (i > 5) {
                final Block block = location.getWorld().getBlockAt(location3);
                if (block.getType().isSolid()) {
                    if (block.getType().toString().contains("GLASS")) {
                        continue;
                    }
                    break;
                }
            }
            for (final LivingEntity livingEntity : livingEntities) {
                if (livingEntity != player && location3.distance(livingEntity.getEyeLocation()) < 1.0) {
                    livingEntity.damage(0.01, (Entity)player);
                }
            }
        }
    }
}
