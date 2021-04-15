

package ws.billy.CookieGadgets.command.main.subcommands;

import ws.billy.CookieGadgets.command.main.CommandManager;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandHelp extends SubCommand
{
    public CommandHelp() {
        super("/gmenu help [page]", "Prints CookieGadgets help message.", null, "CookieGadgets.commands.help", new String[] { "help", "h" }, true);
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
        int int1 = 1;
        Label_0051: {
            if (array.length == 2) {
                try {
                    int1 = Integer.parseInt(array[1]);
                    break Label_0051;
                }
                catch (NumberFormatException ex) {
                    commandSender.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
                    return;
                }
            }
            if (array.length > 2) {
                CommandManager.printMessage(commandSender, new CommandHelp());
                return;
            }
        }
        CommandManager.printHelpMessage(commandSender, int1);
    }
}
