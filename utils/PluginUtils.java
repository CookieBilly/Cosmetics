

package ws.billy.CookieGadgets.utils;

import java.util.Iterator;
import java.lang.reflect.Field;
import org.bukkit.plugin.PluginManager;
import java.io.IOException;
import java.net.URLClassLoader;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.RegisteredListener;
import java.util.SortedSet;
import org.bukkit.command.SimpleCommandMap;
import java.util.Map;
import java.util.List;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import java.net.URISyntaxException;
import ws.billy.CookieGadgets.CookieGadgets;
import java.io.File;

public class PluginUtils
{
    private static File jarFile;
    
    static {
        PluginUtils.jarFile = null;
        try {
            PluginUtils.jarFile = new File(CookieGadgets.getInstance().getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
        }
        catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void reload(final Plugin plugin) {
        if (plugin != null) {
            unload(plugin);
            load();
        }
    }
    
    private static boolean load() {
        return PluginUtils.jarFile != null && load(PluginUtils.jarFile.getName());
    }
    
    public static boolean load(final String child) {
        Plugin loadPlugin = null;
        final File parent = new File("plugins");
        if (!parent.isDirectory()) {
            return false;
        }
        final File file = new File(parent, child);
        if (!file.isFile()) {
            return false;
        }
        try {
            loadPlugin = Bukkit.getPluginManager().loadPlugin(file);
        }
        catch (InvalidDescriptionException ex) {
            ex.printStackTrace();
        }
        catch (InvalidPluginException ex2) {
            ex2.printStackTrace();
        }
        loadPlugin.onLoad();
        Bukkit.getPluginManager().enablePlugin(loadPlugin);
        return true;
    }
    
    public static void unload(final Plugin plugin) {
        final String name = plugin.getName();
        final PluginManager pluginManager = Bukkit.getPluginManager();
        Object obj = null;
        List list = null;
        Map map = null;
        Map<K, PluginCommand> map2 = null;
        Map<K, SortedSet<RegisteredListener>> map3 = null;
        boolean b = true;
        if (pluginManager != null) {
            pluginManager.disablePlugin(plugin);
            try {
                final Field declaredField = Bukkit.getPluginManager().getClass().getDeclaredField("plugins");
                declaredField.setAccessible(true);
                list = (List)declaredField.get(pluginManager);
                final Field declaredField2 = Bukkit.getPluginManager().getClass().getDeclaredField("lookupNames");
                declaredField2.setAccessible(true);
                map = (Map)declaredField2.get(pluginManager);
                try {
                    final Field declaredField3 = Bukkit.getPluginManager().getClass().getDeclaredField("listeners");
                    declaredField3.setAccessible(true);
                    map3 = (Map<K, SortedSet<RegisteredListener>>)declaredField3.get(pluginManager);
                }
                catch (Exception ex2) {
                    b = false;
                }
                final Field declaredField4 = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
                declaredField4.setAccessible(true);
                obj = declaredField4.get(pluginManager);
                final Field declaredField5 = SimpleCommandMap.class.getDeclaredField("knownCommands");
                declaredField5.setAccessible(true);
                map2 = (Map<K, PluginCommand>)declaredField5.get(obj);
            }
            catch (NoSuchFieldException | IllegalAccessException ex3) {
                final Throwable t;
                t.printStackTrace();
            }
        }
        pluginManager.disablePlugin(plugin);
        if (list != null && list.contains(plugin)) {
            list.remove(plugin);
        }
        if (map != null && map.containsKey(name)) {
            map.remove(name);
        }
        if (map3 != null && b) {
            final Iterator<SortedSet<RegisteredListener>> iterator = map3.values().iterator();
            while (iterator.hasNext()) {
                final Iterator<Object> iterator2 = iterator.next().iterator();
                while (iterator2.hasNext()) {
                    if (iterator2.next().getPlugin() == plugin) {
                        iterator2.remove();
                    }
                }
            }
        }
        if (obj != null) {
            final Iterator<Map.Entry<K, PluginCommand>> iterator3 = map2.entrySet().iterator();
            while (iterator3.hasNext()) {
                final Map.Entry<K, PluginCommand> entry = iterator3.next();
                if (entry.getValue() instanceof PluginCommand) {
                    final PluginCommand pluginCommand = entry.getValue();
                    if (pluginCommand.getPlugin() != plugin) {
                        continue;
                    }
                    pluginCommand.unregister((CommandMap)obj);
                    iterator3.remove();
                }
            }
        }
        final ClassLoader classLoader = plugin.getClass().getClassLoader();
        if (classLoader instanceof URLClassLoader) {
            try {
                ((URLClassLoader)classLoader).close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.gc();
    }
}
