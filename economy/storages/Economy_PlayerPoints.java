

package ws.billy.CookieGadgets.economy.storages;

import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.economy.GStorage;
import org.black_ixx.playerpoints.PlayerPoints;
import ws.billy.CookieGadgets.economy.GEconomyProvider;

public class Economy_PlayerPoints extends GEconomyProvider
{
    public static PlayerPoints playerPoints;
    
    public Economy_PlayerPoints() {
        super(GStorage.PLAYERPOINTS);
    }
    
    @Override
    public boolean hook() {
        Economy_PlayerPoints.playerPoints = PlayerPoints.class.cast(Bukkit.getServer().getPluginManager().getPlugin("PlayerPoints"));
        return Economy_PlayerPoints.playerPoints != null;
    }
    
    @Override
    public int getMysteryDust(final OfflinePlayerManager offlinePlayerManager) {
        return Economy_PlayerPoints.playerPoints.getAPI().look(offlinePlayerManager.getUUID());
    }
    
    @Override
    public boolean addMysteryDust(final OfflinePlayerManager offlinePlayerManager, final int n) {
        return Economy_PlayerPoints.playerPoints.getAPI().give(offlinePlayerManager.getUUID(), n);
    }
    
    @Override
    public boolean setMysteryDust(final OfflinePlayerManager offlinePlayerManager, final int n) {
        return Economy_PlayerPoints.playerPoints.getAPI().set(offlinePlayerManager.getUUID(), n);
    }
    
    @Override
    public boolean removeMysteryDust(final OfflinePlayerManager offlinePlayerManager, final int n) {
        return Economy_PlayerPoints.playerPoints.getAPI().take(offlinePlayerManager.getUUID(), n);
    }
}
