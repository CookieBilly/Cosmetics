

package ws.billy.CookieGadgets.holograms;

import ws.billy.CookieGadgets.CookieGadgets;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.Validate;
import java.util.Iterator;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.Map;

public class CraftVisibilityManager
{
    private final CraftHologram hologram;
    private boolean visibleByDefault;
    private Map<String, Boolean> playersVisibilityMap;
    
    public CraftVisibilityManager(final CraftHologram hologram) {
        this.hologram = hologram;
        this.visibleByDefault = true;
    }
    
    public void setVisibleByDefault(final boolean visibleByDefault) {
        if (this.visibleByDefault != visibleByDefault) {
            final boolean visibleByDefault2 = this.visibleByDefault;
            this.visibleByDefault = visibleByDefault;
            for (final Player player : Bukkit.getOnlinePlayers()) {
                if (this.playersVisibilityMap != null && this.playersVisibilityMap.containsKey(player.getName().toLowerCase())) {
                    continue;
                }
                if (visibleByDefault2) {
                    sendDestroyPacketIfNear(player, this.hologram);
                }
                else {
                    sendCreatePacketIfNear(player, this.hologram);
                }
            }
        }
    }
    
    public void showTo(final Player player) {
        Validate.notNull((Object)player);
        final boolean visibleTo = this.isVisibleTo(player);
        if (this.playersVisibilityMap == null) {
            this.playersVisibilityMap = new ConcurrentHashMap<String, Boolean>();
        }
        this.playersVisibilityMap.put(player.getName().toLowerCase(), true);
        if (!visibleTo) {
            sendCreatePacketIfNear(player, this.hologram);
        }
    }
    
    public void hideTo(final Player player) {
        Validate.notNull((Object)player);
        final boolean visibleTo = this.isVisibleTo(player);
        if (this.playersVisibilityMap == null) {
            this.playersVisibilityMap = new ConcurrentHashMap<String, Boolean>();
        }
        this.playersVisibilityMap.put(player.getName().toLowerCase(), false);
        if (visibleTo) {
            sendDestroyPacketIfNear(player, this.hologram);
        }
    }
    
    public boolean isVisibleTo(final Player player) {
        Validate.notNull((Object)player);
        if (this.playersVisibilityMap != null) {
            final Boolean b = this.playersVisibilityMap.get(player.getName().toLowerCase());
            if (b != null) {
                return b;
            }
        }
        return this.visibleByDefault;
    }
    
    private static void sendCreatePacketIfNear(final Player player, final CraftHologram craftHologram) {
        if (CookieGadgets.hasProtocolLibHook() && isNear(player, craftHologram)) {
            CookieGadgets.getProtocolLib().sendCreateEntitiesPacket(player, craftHologram);
        }
    }
    
    private static void sendDestroyPacketIfNear(final Player player, final CraftHologram craftHologram) {
        if (CookieGadgets.hasProtocolLibHook() && isNear(player, craftHologram)) {
            CookieGadgets.getProtocolLib().sendDestroyEntitiesPacket(player, craftHologram);
        }
    }
    
    private static boolean isNear(final Player player, final CraftHologram craftHologram) {
        return player.isOnline() && player.getWorld().equals(craftHologram.getWorld()) && player.getLocation().distanceSquared(craftHologram.getLocation()) < 4096.0;
    }
}
