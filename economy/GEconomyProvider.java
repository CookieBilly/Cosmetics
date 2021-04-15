

package ws.billy.CookieGadgets.economy;

import ws.billy.CookieGadgets.CookieGadgets;
import org.apache.commons.lang.Validate;
import org.bukkit.plugin.Plugin;

public abstract class GEconomyProvider implements GEconomy
{
    private GStorage storage;
    
    public GEconomyProvider(final Plugin plugin, final String s) {
        Validate.notNull((Object)plugin, "Plugin can not be null!");
        Validate.isTrue(s != null && !s.isEmpty(), "Storage name can not be null or empty!");
        final GStorage value = GStorage.valueOf(s);
        if (value == null) {
            this.storage = new GStorage(s, plugin.getName());
        }
        else {
            this.storage = value;
        }
    }
    
    public GEconomyProvider(final GStorage storage) {
        Validate.isTrue(storage != null, "Storage cannot be null!");
        this.storage = storage;
    }
    
    public GStorage getStorage() {
        return this.storage;
    }
    
    public boolean hook() {
        return true;
    }
    
    public static void setMysteryDustStorage(final GEconomyProvider gEconomyProvider) {
        CookieGadgets.setGEconomyProvider(gEconomyProvider);
        CookieGadgets.getCookieGadgetsData().setMysteryDustStorage(gEconomyProvider.getStorage());
    }
}
