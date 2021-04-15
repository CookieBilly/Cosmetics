

package ws.billy.CookieGadgets.command.main.subcommands;

import ws.billy.CookieGadgets.menu.menus.MainMenu;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.main.CommandManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandMain extends SubCommand
{
    public CommandMain() {
        super("/gmenu main", "Bring up main menu.", null, null, new String[] { "main" }, false);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length > 1) {
            CommandManager.printMessage((CommandSender)player, new CommandMain());
            return;
        }
        MainMenu.openMainMenu(player);
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        this.notAllowed(commandSender);
    }
}
