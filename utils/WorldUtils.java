

package ws.billy.CookieGadgets.utils;

import org.bukkit.World;
import java.util.List;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;

public class WorldUtils
{
    public static boolean isWorldEnabled(final Player player, final boolean b) {
        if (player == null) {
            return false;
        }
        if (player.getWorld() == null) {
            return false;
        }
        final List<String> enabledWorlds = CookieGadgets.getCookieGadgetsData().getEnabledWorlds();
        final String name = player.getWorld().getName();
        if (enabledWorlds.contains("*")) {
            return true;
        }
        if (!enabledWorlds.contains(name)) {
            if (b) {
                player.sendMessage(MessageType.WORLD_DISABLED.getFormatMessage());
            }
            return false;
        }
        return true;
    }
    
    public static boolean isWorldEnabled(final World world) {
        if (world == null) {
            return false;
        }
        final List<String> enabledWorlds = CookieGadgets.getCookieGadgetsData().getEnabledWorlds();
        final String name = world.getName();
        return enabledWorlds.contains("*") || enabledWorlds.contains(name);
    }
    
    public static boolean isWorldEnabledForMysteryBoxReward(final World world) {
        if (world == null) {
            return false;
        }
        final List<String> enabledWorldsForMysteryBoxesReward = CookieGadgets.getCookieGadgetsData().getEnabledWorldsForMysteryBoxesReward();
        final String name = world.getName();
        return enabledWorldsForMysteryBoxesReward.contains("*") || enabledWorldsForMysteryBoxesReward.contains(name);
    }
}
