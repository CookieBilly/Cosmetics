

package ws.billy.CookieGadgets.utils.mysteryvault;

import java.util.Set;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import ws.billy.CookieGadgets.configuration.FileManager;

public class MysteryVaultUtils
{
    public static Location getLocation(final FileManager fileManager, final String s) {
        return new Location(Bukkit.getServer().getWorld(fileManager.getString(String.valueOf(s) + ".world")), fileManager.getDouble(String.valueOf(s) + ".x"), fileManager.getDouble(String.valueOf(s) + ".y"), fileManager.getDouble(String.valueOf(s) + ".z"));
    }
    
    public static void saveLocation(final Location location, final ConfigurationSection configurationSection) {
        configurationSection.set("world", (Object)location.getWorld().getName());
        configurationSection.set("x", (Object)location.getX());
        configurationSection.set("y", (Object)location.getY());
        configurationSection.set("z", (Object)location.getZ());
    }
    
    public static Set<String> getKeys(final FileManager fileManager, final String s) {
        return (Set<String>)fileManager.getConfigurationSection(s).getKeys(false);
    }
}
