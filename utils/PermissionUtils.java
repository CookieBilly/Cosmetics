

package ws.billy.CookieGadgets.utils;

import java.util.Iterator;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.CookieGadgets;
import java.util.List;
import org.bukkit.entity.Player;

public class PermissionUtils
{
    public static boolean containsPermission(final Player player, final List<String> list) {
        if (player == null) {
            return true;
        }
        if (player.hasPermission(EnumPermission.ALL.getPermission())) {
            return true;
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        for (final String s : list) {
            if (player.hasPermission(s) || playerManager.hasPermission(s)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean noPermission(final Player player, final String replacement, final boolean b) {
        if (player == null) {
            return true;
        }
        if (player.hasPermission(EnumPermission.ALL.getPermission())) {
            return false;
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (!player.hasPermission(replacement) && !playerManager.hasPermission(replacement)) {
            if (b) {
                player.sendMessage(MessageType.NO_PERMISSION.getFormatMessage().replace("{PERMISSION}", replacement));
            }
            return true;
        }
        return false;
    }
    
    public static boolean noPermission(final Player player, final String replacement, final String s) {
        if (player == null) {
            return true;
        }
        if (player.hasPermission(EnumPermission.ALL.getPermission())) {
            return false;
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (!player.hasPermission(replacement) && !playerManager.hasPermission(replacement)) {
            player.sendMessage(ChatUtil.format(s.replace("{PERMISSION}", replacement)));
            return true;
        }
        return false;
    }
    
    public static boolean noPermission(final Player player, final String replacement, final String s, final boolean b) {
        if (player == null) {
            return true;
        }
        if (player.hasPermission(s) || player.hasPermission(EnumPermission.ALL.getPermission())) {
            return false;
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (!player.hasPermission(replacement) && !playerManager.hasPermission(replacement)) {
            if (b) {
                player.sendMessage(MessageType.NO_PERMISSION.getFormatMessage().replace("{PERMISSION}", replacement));
            }
            return true;
        }
        return false;
    }
}
