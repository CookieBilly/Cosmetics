

package ws.billy.CookieGadgets.economy;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class GStorage
{
    private static final List<GStorage> VALUES;
    public static final GStorage COINSAPI;
    public static final GStorage DEFAULT;
    public static final GStorage PLAYERPOINTS;
    public static final GStorage VAULT;
    private String name;
    private String pluginName;
    
    static {
        VALUES = new ArrayList<GStorage>();
        COINSAPI = new GStorage("CoinsAPI", "CoinsAPINB");
        DEFAULT = new GStorage("Default", null);
        PLAYERPOINTS = new GStorage("PlayerPoints", "PlayerPoints");
        VAULT = new GStorage("Vault", "Vault");
    }
    
    public GStorage(final String name, final String pluginName) {
        this.name = name;
        this.pluginName = pluginName;
        if (!GStorage.VALUES.contains(this)) {
            GStorage.VALUES.add(this);
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getPluginName() {
        return this.pluginName;
    }
    
    public static List<GStorage> values() {
        return GStorage.VALUES;
    }
    
    public static boolean isCustomStorage(final GStorage gStorage) {
        return gStorage != GStorage.COINSAPI && gStorage != GStorage.DEFAULT && gStorage != GStorage.PLAYERPOINTS && gStorage != GStorage.VAULT;
    }
    
    public static GStorage valueOf(final String anotherString) {
        for (final GStorage gStorage : values()) {
            if (gStorage.getName().equalsIgnoreCase(anotherString)) {
                return gStorage;
            }
        }
        return null;
    }
}
