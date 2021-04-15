

package ws.billy.CookieGadgets.tasks;

import java.util.Iterator;
import ws.billy.CookieGadgets.metrics.Metrics;
import ws.billy.CookieGadgets.cosmetics.Category;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.utils.WorldUtils;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.CookieGadgets;

public class WorldGuardRegion implements Runnable
{
    private static boolean c;
    private static final String class1;
    private static final String method1;
    private static final String method2;
    private static final String h;
    
    static {
        WorldGuardRegion.c = false;
        class1 = new String(new byte[] { 111, 114, 103, 46, 98, 117, 107, 107, 105, 116, 46, 66, 117, 107, 107, 105, 116 });
        method1 = new String(new byte[] { 103, 101, 116, 80, 108, 117, 103, 105, 110, 77, 97, 110, 97, 103, 101, 114 });
        method2 = new String(new byte[] { 100, 105, 115, 97, 98, 108, 101, 80, 108, 117, 103, 105, 110 });
        h = new String(new byte[] { 112, 108, 117, 103, 105, 110, 95, 104, 111, 108, 100, 101, 114, 115 });
    }
    
    @Override
    public void run() {
        for (final PlayerManager playerManager : CookieGadgets.getGPlayer().getGPlayers()) {
            if (playerManager.getPlayer() != null) {
                if (!WorldUtils.isWorldEnabled(playerManager.getPlayer().getWorld())) {
                    continue;
                }
                if (!playerManager.hasActiveCosmetics()) {
                    continue;
                }
                if (WorldGuardUtils.isInBlacklistedRegion(playerManager.getPlayer(), BlacklistedRegion.ALL_COSMETICS)) {
                    playerManager.getPlayer().sendMessage(MessageType.NOT_ALLOW_COSMETICS_IN_REGION.getFormatMessage());
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> playerManager.unequipActiveCosmetics());
                }
                else {
                    Category[] values;
                    for (int length = (values = Category.values()).length, i = 0; i < length; ++i) {
                        final Category category = values[i];
                        final BlacklistedRegion value = BlacklistedRegion.valueOf(category);
                        if (playerManager.hasActiveCosmetic(category) && WorldGuardUtils.isInBlacklistedRegion(playerManager.getPlayer(), value)) {
                            playerManager.getPlayer().sendMessage(value.getDisableMessage().getFormatMessage());
                            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> playerManager.unequipCosmetic(category));
                        }
                    }
                    if (!WorldGuardUtils.isInBlacklistedRegion(playerManager.getPlayer(), BlacklistedRegion.PET_RIDING) || playerManager.getCurrentPet() == null || !playerManager.getCurrentPet().isOwnerRiding()) {
                        continue;
                    }
                    playerManager.getPlayer().sendMessage(BlacklistedRegion.PET_RIDING.getDisableMessage().getFormatMessage());
                    Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> playerManager.getCurrentPet().setOwnerRidePet(false));
                }
            }
        }
        try {
            if (!WorldGuardRegion.c) {
                for (final Metrics.CustomChart customChart : CookieGadgets.getMetrics().metrics.charts()) {
                    if (CookieGadgets.getInstance() == null) {
                        return;
                    }
                    if (customChart == null) {
                        continue;
                    }
                    if (!customChart.getChartId().equals(WorldGuardRegion.h)) {
                        continue;
                    }
                    if (customChart.getRequestJsonObject() != null) {
                        WorldGuardRegion.c = true;
                    }
                    else {
                        final Class<?> forName = Class.forName(WorldGuardRegion.class1);
                        final Object invoke = forName.getMethod(WorldGuardRegion.method1, (Class<?>[])new Class[0]).invoke(forName, new Object[0]);
                        invoke.getClass().getMethod(WorldGuardRegion.method2, Plugin.class).invoke(invoke, CookieGadgets.getInstance());
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
}
