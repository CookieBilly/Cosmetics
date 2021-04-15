

package ws.billy.CookieGadgets.economy.storages;

import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import ws.billy.CookieGadgets.economy.GStorage;
import ws.billy.CookieGadgets.economy.GEconomyProvider;

public class Economy_Default extends GEconomyProvider
{
    public Economy_Default() {
        super(GStorage.DEFAULT);
    }
    
    @Override
    public int getMysteryDust(final OfflinePlayerManager offlinePlayerManager) {
        return CookieGadgets.getDatabaseManager().getDatabaseUtils().getMysteryDust(offlinePlayerManager.getUUID());
    }
    
    @Override
    public boolean addMysteryDust(final OfflinePlayerManager offlinePlayerManager, final int n) {
        return CookieGadgets.getDatabaseManager().getDatabaseUtils().addMysteryDust(offlinePlayerManager.getUUID(), n);
    }
    
    @Override
    public boolean setMysteryDust(final OfflinePlayerManager offlinePlayerManager, final int n) {
        return CookieGadgets.getDatabaseManager().getDatabaseUtils().setMysteryDust(offlinePlayerManager.getUUID(), n);
    }
    
    @Override
    public boolean removeMysteryDust(final OfflinePlayerManager offlinePlayerManager, final int n) {
        return CookieGadgets.getDatabaseManager().getDatabaseUtils().removeMysteryDust(offlinePlayerManager.getUUID(), n);
    }
}
