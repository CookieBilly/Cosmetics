

package ws.billy.CookieGadgets.tasks;

import org.bukkit.Location;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.MathUtil;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVaultUtils;
import ws.billy.CookieGadgets.configuration.FileManager;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVaultManager;

public class UpdateHolograms implements Runnable
{
    @Override
    public void run() {
        synchronized (MysteryVaultManager.vaults()) {
            for (final MysteryVault mysteryVault : MysteryVaultManager.vaults()) {
                final Location location = mysteryVault.getLocation();
                if (location.getWorld() == null) {
                    mysteryVault.removeHolograms();
                    mysteryVault.updateLocationWithoutSaving(MysteryVaultUtils.getLocation(FileManager.getMysteryVaultFile(), "Mystery-Vaults." + mysteryVault.getName() + ".Location"));
                }
                else if (!location.getWorld().isChunkLoaded(MathUtil.floor(location.getX()) >> 4, MathUtil.floor(location.getZ()) >> 4)) {
                    mysteryVault.removeHolograms();
                }
                else {
                    try {
                        mysteryVault.createHolograms();
                    }
                    catch (Exception ex) {}
                }
            }
        }
        // monitorexit(MysteryVaultManager.vaults())
    }
}
