

package ws.billy.CookieGadgets.command.main.subcommands;

import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.updater.UpdaterManager;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.command.main.CommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandCheckUpdate extends SubCommand
{
    public CommandCheckUpdate() {
        super("/gmenu checkupdate", "Check latest released version of the plugin.", null, "CookieGadgets.commands.checkupdate", new String[] { "checkupdate", "cupdate", "cu" }, true);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        this.onCommand((CommandSender)player, array);
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        this.onCommand(commandSender, array);
    }
    
    private void onCommand(final CommandSender commandSender, final String[] array) {
        if (array.length > 1) {
            CommandManager.printMessage(commandSender, this);
            return;
        }
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> UpdaterManager.checkUpdate(commandSender, false));
    }
}
