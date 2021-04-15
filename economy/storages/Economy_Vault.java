

package ws.billy.CookieGadgets.economy.storages;

import net.milkbowl.vault.economy.EconomyResponse;
import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.economy.GStorage;
import net.milkbowl.vault.economy.Economy;
import ws.billy.CookieGadgets.economy.GEconomyProvider;

public class Economy_Vault extends GEconomyProvider
{
    private static Economy economy;
    
    static {
        Economy_Vault.economy = null;
    }
    
    public Economy_Vault() {
        super(GStorage.VAULT);
    }
    
    @Override
    public boolean hook() {
        final RegisteredServiceProvider registration = Bukkit.getServer().getServicesManager().getRegistration((Class)Economy.class);
        if (registration != null) {
            Economy_Vault.economy = (Economy)registration.getProvider();
        }
        return Economy_Vault.economy != null;
    }
    
    @Override
    public int getMysteryDust(final OfflinePlayerManager offlinePlayerManager) {
        return (int)Economy_Vault.economy.getBalance(offlinePlayerManager.getPlayer());
    }
    
    @Override
    public boolean addMysteryDust(final OfflinePlayerManager offlinePlayerManager, final int n) {
        return Economy_Vault.economy.depositPlayer(offlinePlayerManager.getPlayer(), (double)n).transactionSuccess();
    }
    
    @Override
    public boolean setMysteryDust(final OfflinePlayerManager offlinePlayerManager, final int n) {
        final EconomyResponse withdrawPlayer = Economy_Vault.economy.withdrawPlayer(offlinePlayerManager.getPlayer(), (double)this.getMysteryDust(offlinePlayerManager));
        final EconomyResponse depositPlayer = Economy_Vault.economy.depositPlayer(offlinePlayerManager.getPlayer(), (double)n);
        return withdrawPlayer.transactionSuccess() && depositPlayer.transactionSuccess();
    }
    
    @Override
    public boolean removeMysteryDust(final OfflinePlayerManager offlinePlayerManager, final int n) {
        return Economy_Vault.economy.withdrawPlayer(offlinePlayerManager.getPlayer(), (double)n).transactionSuccess();
    }
}
