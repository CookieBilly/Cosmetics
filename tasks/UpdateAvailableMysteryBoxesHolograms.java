

package ws.billy.CookieGadgets.tasks;

import org.bukkit.Location;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.MathUtil;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVaultManager;

public class UpdateAvailableMysteryBoxesHolograms implements Runnable
{
    private int num;
    
    public UpdateAvailableMysteryBoxesHolograms() {
        this.num = 0;
    }
    
    @Override
    public void run() {
        final int size = MysteryVaultManager.getAvailableMysteryBoxesHolograms().size();
        synchronized (MysteryVaultManager.vaults()) {
            for (final MysteryVault mysteryVault : MysteryVaultManager.vaults()) {
                final Location location = mysteryVault.getLocation();
                if (location.getWorld() == null) {
                    continue;
                }
                if (!mysteryVault.displayIndividualHolograms()) {
                    continue;
                }
                if (!location.getWorld().isChunkLoaded(MathUtil.floor(location.getX()) >> 4, MathUtil.floor(location.getZ()) >> 4)) {
                    mysteryVault.removeAvailableMysteryBoxesHologram();
                }
                else {
                    try {
                        mysteryVault.refreshAvailableMysteryBoxesHologram(this.num);
                    }
                    catch (Exception ex) {}
                }
            }
        }
        // monitorexit(MysteryVaultManager.vaults())
        if (size - 1 <= this.num) {
            this.num = 0;
        }
        else {
            ++this.num;
        }
    }
}
