

package ws.billy.CookieGadgets.command.main.subcommands;

import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.main.CommandManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandAdmin extends SubCommand
{
    public CommandAdmin() {
        super("/gmenu admin", "Manage CookieGadgets settings.", null, "CookieGadgets.commands.admin", new String[] { "admin", "administrator" }, false);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length > 1) {
            CommandManager.printMessage((CommandSender)player, new CommandAdmin());
            return;
        }
        player.sendMessage(ChatUtil.format("&cUpgrating!"));
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        this.notAllowed(commandSender);
    }
}
