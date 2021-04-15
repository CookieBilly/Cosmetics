// 
// Decompiled by Procyon v0.5.36
// 

package ws.billy.CookieGadgets.metrics;

import ws.billy.CookieGadgets.license.Md5Convert;
import java.util.Iterator;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import ws.billy.CookieGadgets.economy.GStorage;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.cosmetics.Category;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MetricsStarter
{
    public Metrics metrics;
    
    public MetricsStarter(final JavaPlugin javaPlugin) {
        this.metrics = new Metrics((Plugin)javaPlugin);
    }
    
    public void start() {
        this.metrics.addCustomChart(new Metrics.AdvancedPie("enabled_cosmetics", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() {
                final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
                Category[] values;
                for (int length = (values = Category.values()).length, i = 0; i < length; ++i) {
                    final Category category = values[i];
                    if (category.isEnabled()) {
                        if (category != Category.MORPHS || CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled() || CookieGadgets.getCookieGadgetsData().isIDisguiseEnabled()) {
                            hashMap.put(category.getName(), 1);
                        }
                    }
                }
                return hashMap;
            }
        }));
        this.metrics.addCustomChart(new Metrics.AdvancedPie("storages", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() {
                final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
                final GStorage mysteryDustStorage = CookieGadgets.getCookieGadgetsData().getMysteryDustStorage();
                if (mysteryDustStorage != null) {
                    hashMap.put(mysteryDustStorage.getName(), 1);
                }
                return hashMap;
            }
        }));
        this.metrics.addCustomChart(new Metrics.AdvancedPie("plugin_series", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() {
                final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
                if (CookieGadgets.getInstance().getDescription().getVersion().startsWith("4")) {
                    hashMap.put("v4", 1);
                }
                else {
                    hashMap.put("Unknown", 1);
                }
                return hashMap;
            }
        }));
        this.metrics.addCustomChart(new Metrics.AdvancedPie("update_checking", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() {
                final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
                if (CookieGadgets.getCookieGadgetsData().isCheckUpdateEnabled()) {
                    hashMap.put("Check Update", 1);
                    return hashMap;
                }
                hashMap.put("Disabled", 1);
                return hashMap;
            }
        }));
        this.metrics.addCustomChart(new Metrics.AdvancedPie("disabled_gadgets", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() {
                final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
                if (Category.GADGETS.isEnabled()) {
                    for (final GadgetType gadgetType : GadgetType.values()) {
                        if (!gadgetType.isEnabled()) {
                            hashMap.put(gadgetType.getName(), 1);
                        }
                    }
                    if (GadgetType.values().size() == GadgetType.enabled().size()) {
                        hashMap.put("All Gadgets Enabled", 1);
                    }
                }
                else {
                    hashMap.put("Gadgets Disabled", 1);
                }
                return hashMap;
            }
        }));
        this.metrics.addCustomChart(new Metrics.AdvancedPie("plugin_license", new Callable<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call() {
                final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
                if (CookieGadgets.getCookieGadgetsData().isPremium) {
                    hashMap.put("Premium", 1);
                    return hashMap;
                }
                hashMap.put("Free", 1);
                return hashMap;
            }
        }));
        if (CookieGadgets.getCookieGadgetsData().isPremium) {
            this.metrics.addCustomChart(new Metrics.AdvancedPie("plugin_holders", new Callable<Map<String, Integer>>() {
                @Override
                public Map<String, Integer> call() {
                    final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
                    final String substring = Md5Convert.getMd5("56721").substring(0, 16);
                    if (substring != null && !substring.isEmpty()) {
                        hashMap.put(substring, 1);
                        return hashMap;
                    }
                    hashMap.put("Unknown", 1);
                    return hashMap;
                }
            }));
        }
    }
}
