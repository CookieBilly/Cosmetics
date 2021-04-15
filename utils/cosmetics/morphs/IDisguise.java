

package ws.billy.CookieGadgets.utils.cosmetics.morphs;

import de.robingrether.idisguise.disguise.Disguise;
import org.bukkit.OfflinePlayer;
import de.robingrether.idisguise.disguise.PigDisguise;
import de.robingrether.idisguise.disguise.CreeperDisguise;
import ws.billy.CookieGadgets.CookieGadgets;
import de.robingrether.idisguise.disguise.MobDisguise;
import de.robingrether.idisguise.iDisguise;
import org.bukkit.entity.Player;

public class IDisguise implements GDisguise
{
    @Override
    public boolean isDisguised(final Player player) {
        return iDisguise.getInstance().getAPI().isDisguised(player);
    }
    
    @Override
    public GDisguiseType getDisguise(final Player player) {
        return GDisguiseType.valueOfIDisguiseType(iDisguise.getInstance().getAPI().getDisguise(player).getType());
    }
    
    @Override
    public boolean disguise(final Player player, final GDisguiseType gDisguiseType) {
        if (player == null) {
            return false;
        }
        if (gDisguiseType == null) {
            return false;
        }
        Object o = new MobDisguise(gDisguiseType.getIDisguiseType());
        ((MobDisguise)o).setCustomNameVisible(CookieGadgets.getCookieGadgetsData().showNameForMobDisguise());
        if (CookieGadgets.getCookieGadgetsData().showNameForMobDisguise()) {
            ((MobDisguise)o).setCustomName(player.getName());
        }
        if (gDisguiseType == GDisguiseType.CREEPER) {
            o = new CreeperDisguise(true);
        }
        else if (gDisguiseType == GDisguiseType.PIG) {
            o = new PigDisguise(true, true);
        }
        return iDisguise.getInstance().getAPI().disguise((OfflinePlayer)player, (Disguise)o);
    }
    
    @Override
    public boolean disguiseCreeper(final Player player) {
        return false;
    }
    
    @Override
    public boolean disguiseSheep(final Player player) {
        return player != null;
    }
    
    @Override
    public boolean undisguise(final Player player) {
        return player != null && iDisguise.getInstance().getAPI().undisguise((OfflinePlayer)player);
    }
}
