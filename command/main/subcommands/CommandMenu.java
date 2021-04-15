

package ws.billy.CookieGadgets.command.main.subcommands;

import java.util.Iterator;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.cosmetics.Category;
import ws.billy.CookieGadgets.menu.menus.MainMenu;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.cosmetics.MainMenuType;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.main.CommandManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandMenu extends SubCommand
{
    public CommandMenu() {
        super("/gmenu menu <cosmetic> [page]", "Bring up specified menu.", null, null, new String[] { "menu", "gui" }, false);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length != 2 && array.length != 3) {
            CommandManager.printMessage((CommandSender)player, new CommandMenu());
            return;
        }
        String replace = "main";
        if (array.length >= 2) {
            for (final MainMenuType mainMenuType : MainMenuType.enabled()) {
                if (mainMenuType.getName().replace(" ", "_").equalsIgnoreCase(array[1])) {
                    replace = mainMenuType.getName().replace(" ", "_");
                }
            }
            if (replace.equalsIgnoreCase("main") && !array[1].equalsIgnoreCase("main")) {
                CommandManager.printMessage((CommandSender)player, new CommandMenu());
                final StringBuilder sb = new StringBuilder();
                sb.append("Main&f, &c&l");
                for (int i = 0; i < MainMenuType.enabled().size(); ++i) {
                    sb.append(String.valueOf(MainMenuType.enabled().get(i).toString().replace(" ", "_")) + ((i != MainMenuType.enabled().size() - 1) ? "&f, &c&l" : ""));
                }
                player.sendMessage(ChatUtil.format("&bAvailable Menus&e: &c&l" + sb.toString()));
                return;
            }
        }
        int int1 = 1;
        if (array.length == 3) {
            try {
                int1 = Integer.parseInt(array[2]);
            }
            catch (NumberFormatException ex) {
                player.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
                return;
            }
        }
        if (replace.equalsIgnoreCase("main")) {
            MainMenu.openMainMenu(player);
        }
        else {
            if (!Category.valueOf(replace.toUpperCase()).isEnabled()) {
                CommandManager.printMessage((CommandSender)player, new CommandMenu());
                final StringBuilder sb2 = new StringBuilder();
                for (int j = 0; j < MainMenuType.enabled().size(); ++j) {
                    sb2.append(String.valueOf(MainMenuType.enabled().get(j).toString().replace(" ", "_")) + ((j != MainMenuType.enabled().size() - 1) ? "&f, &c" : ""));
                }
                player.sendMessage(ChatUtil.format("&bAvailable Menus&e: &c" + sb2.toString()));
                return;
            }
            Category.valueOf(replace.toUpperCase()).openMenu(CookieGadgets.getPlayerManager(player), int1);
        }
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        this.notAllowed(commandSender);
    }
}
