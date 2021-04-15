

package ws.billy.CookieGadgets.tasks;

import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.CookieGadgets;
import java.lang.reflect.InvocationTargetException;
import ws.billy.CookieGadgets.log.LoggerManager;
import ws.billy.CookieGadgets.utils.ReflectionUtils;

public class AntiLagChecker implements Runnable
{
    private static boolean isLowTps;
    private double[] tps;
    private boolean noSuchField;
    
    static {
        AntiLagChecker.isLowTps = false;
    }
    
    public AntiLagChecker() {
        this.tps = null;
        this.noSuchField = false;
    }
    
    @Override
    public void run() {
        if (this.noSuchField) {
            return;
        }
        try {
            final Object invokeMethod = ReflectionUtils.invokeMethod(null, ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("MinecraftServer"), "getServer", new Object[0]);
            this.tps = (double[])ReflectionUtils.getField(invokeMethod.getClass(), false, "recentTps").get(invokeMethod);
        }
        catch (NoSuchFieldException ex) {
            LoggerManager.warn("Unable to retrieve server TPS, Anti Lag Checker will be disabled.");
            this.noSuchField = true;
        }
        catch (SecurityException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex2) {
            final Throwable t;
            t.printStackTrace();
        }
        if (this.tps[0] <= 20.0) {
            if (this.tps[0] < CookieGadgets.getCookieGadgetsData().getMinimumTPS()) {
                if (CookieGadgets.getCookieGadgetsData().clearCosmeticsIfLowTPS()) {
                    for (final PlayerManager playerManager : CookieGadgets.getGPlayer().getGPlayers()) {
                        if (playerManager.hasActiveCosmetics()) {
                            playerManager.getPlayer().sendMessage(MessageType.UNEQUIP_COSMETICS_DUE_TO_LOW_TPS.getFormatMessage());
                            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> playerManager.unequipActiveCosmetics());
                        }
                    }
                }
                AntiLagChecker.isLowTps = true;
            }
            else {
                AntiLagChecker.isLowTps = false;
            }
        }
    }
    
    public static boolean isLowTPS() {
        return AntiLagChecker.isLowTps;
    }
}
