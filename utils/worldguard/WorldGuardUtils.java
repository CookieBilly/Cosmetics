

package ws.billy.CookieGadgets.utils.worldguard;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.util.Iterator;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.WorldGuard;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.entity.Player;
import org.apache.commons.lang.Validate;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Location;

public class WorldGuardUtils
{
    public static boolean isInBlacklistedRegion(final Location location, final BlacklistedRegion blacklistedRegion) {
        if (location == null || CookieGadgets.getWorldGuardPlugin() == null) {
            return false;
        }
        Validate.notNull((Object)blacklistedRegion);
        if (blacklistedRegion == BlacklistedRegion.ALL_COSMETICS) {
            return inBlacklistedRegion(location, BlacklistedRegion.ALL_COSMETICS);
        }
        return inBlacklistedRegion(location, BlacklistedRegion.ALL_COSMETICS) | inBlacklistedRegion(location, blacklistedRegion);
    }
    
    public static boolean isInBlacklistedRegion(final Player player, final BlacklistedRegion blacklistedRegion) {
        Validate.notNull((Object)player);
        Validate.notNull((Object)blacklistedRegion);
        if (CookieGadgets.getWorldGuardPlugin() == null) {
            return false;
        }
        if (!PermissionUtils.noPermission(player, EnumPermission.BYPASS_COSMETICS_IN_REGION.getPermission(), false)) {
            return false;
        }
        if (blacklistedRegion == BlacklistedRegion.ALL_COSMETICS) {
            return inBlacklistedRegion(player.getLocation(), BlacklistedRegion.ALL_COSMETICS);
        }
        return inBlacklistedRegion(player.getLocation(), BlacklistedRegion.ALL_COSMETICS) | inBlacklistedRegion(player.getLocation(), blacklistedRegion);
    }
    
    private static boolean inBlacklistedRegion(final Location location, final BlacklistedRegion blacklistedRegion) {
        if (location == null || CookieGadgets.getWorldGuardPlugin() == null) {
            return false;
        }
        Validate.notNull((Object)blacklistedRegion);
        if (VersionManager.isClassExists("com.sk89q.worldguard.protection.regions.RegionContainer")) {
            for (final RegionManager regionManager : WorldGuard.getInstance().getPlatform().getRegionContainer().getLoaded()) {
                final Iterator<String> iterator2 = blacklistedRegion.getBlacklistedRegions().iterator();
                while (iterator2.hasNext()) {
                    final ProtectedRegion region = regionManager.getRegion((String)iterator2.next());
                    if (region != null && region.contains(location.getBlockX(), location.getBlockY(), location.getBlockZ())) {
                        return true;
                    }
                }
            }
            return false;
        }
        final RegionManager regionManager2 = CookieGadgets.getWorldGuardPlugin().getRegionManager(location.getWorld());
        if (regionManager2 == null) {
            return false;
        }
        final Iterator<String> iterator3 = blacklistedRegion.getBlacklistedRegions().iterator();
        while (iterator3.hasNext()) {
            final ProtectedRegion region2 = regionManager2.getRegion((String)iterator3.next());
            if (region2 != null && region2.contains(location.getBlockX(), location.getBlockY(), location.getBlockZ())) {
                return true;
            }
        }
        return false;
    }
}
