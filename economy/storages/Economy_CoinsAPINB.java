

package ws.billy.CookieGadgets.economy.storages;

import de.NeonnBukkit.CoinsAPI.API.CoinsAPI;
import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import ws.billy.CookieGadgets.economy.GStorage;
import ws.billy.CookieGadgets.economy.GEconomyProvider;

public class Economy_CoinsAPINB extends GEconomyProvider
{
    public Economy_CoinsAPINB() {
        super(GStorage.COINSAPI);
    }
    
    @Override
    public int getMysteryDust(final OfflinePlayerManager offlinePlayerManager) {
        return CoinsAPI.getCoins(offlinePlayerManager.getUUID().toString());
    }
    
    @Override
    public boolean addMysteryDust(final OfflinePlayerManager offlinePlayerManager, final int i) {
        CoinsAPI.addCoins(offlinePlayerManager.getUUID().toString(), Integer.valueOf(i));
        return true;
    }
    
    @Override
    public boolean setMysteryDust(final OfflinePlayerManager offlinePlayerManager, final int i) {
        CoinsAPI.setCoins(offlinePlayerManager.getUUID().toString(), Integer.valueOf(i));
        return true;
    }
    
    @Override
    public boolean removeMysteryDust(final OfflinePlayerManager offlinePlayerManager, final int i) {
        CoinsAPI.removeCoins(offlinePlayerManager.getUUID().toString(), Integer.valueOf(i));
        return true;
    }
}
