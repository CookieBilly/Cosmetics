

package ws.billy.CookieGadgets.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;

public class GCommandHandler
{
    public static boolean executeCommand(final String s) {
        Bukkit.dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), s);
        return true;
    }
}
