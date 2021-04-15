

package ws.billy.CookieGadgets.economy;

import ws.billy.CookieGadgets.player.OfflinePlayerManager;

public interface GEconomy
{
    int getMysteryDust(final OfflinePlayerManager p0);
    
    boolean addMysteryDust(final OfflinePlayerManager p0, final int p1);
    
    boolean setMysteryDust(final OfflinePlayerManager p0, final int p1);
    
    boolean removeMysteryDust(final OfflinePlayerManager p0, final int p1);
}
