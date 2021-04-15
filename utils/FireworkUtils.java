

package ws.billy.CookieGadgets.utils;

import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Firework;
import org.bukkit.Color;
import java.util.List;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;

public class FireworkUtils
{
    public static void displayFirework(final Location location, final FireworkEffect.Type type, final boolean b, final boolean b2, final List<Color> list, final List<Color> list2) {
        final Firework firework = (Firework)location.getWorld().spawn(location, (Class)Firework.class);
        final FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder().with(type).flicker(b).trail(b2).withColor((Iterable)list).withFade((Iterable)list2).build());
        firework.setFireworkMeta(fireworkMeta);
        firework.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        new BukkitRunnable() {
            public void run() {
                firework.detonate();
            }
        }.runTaskLater((Plugin)CookieGadgets.getInstance(), 2L);
    }
}
