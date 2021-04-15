

package ws.billy.CookieGadgets.utils.mysteryvault;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

public class MysteryVaultAPI
{
    public static MysteryVault mysteryVault(final String s, final BlockFace blockFace, final Location location) {
        return new MysteryVault(s, blockFace, location);
    }
}
