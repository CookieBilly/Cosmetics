

package ws.billy.CookieGadgets.command.main.subcommands;

import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.utils.WorldUtils;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.main.CommandManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.main.SubCommand;

public class CommandMenuItem extends SubCommand
{
    public CommandMenuItem() {
        super("/gmenu menuitem [player]", "Give a player menu selector.", null, "CookieGadgets.commands.menuitem", new String[] { "menuitem", "menuselector", "mi" }, true);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length > 2) {
            CommandManager.printMessage((CommandSender)player, this);
            return;
        }
        Player player2 = player;
        if (array.length == 2) {
            player2 = player.getServer().getPlayer(array[1]);
            if (player2 == null || !player2.isOnline()) {
                player.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
        }
        if (!WorldUtils.isWorldEnabled(player2.getWorld())) {
            player.sendMessage(MessageType.NO_PERMISSION_FOR_MENU_ITEM_IN_THAT_WORLD.getFormatMessage().replace("{PLAYER}", player2.getName()));
            return;
        }
        if (!player2.hasPermission(EnumPermission.MENU_SELECTOR.getPermission())) {
            player.sendMessage(MessageType.NO_PERMISSION_FOR_MENU_ITEM.getFormatMessage().replace("{PLAYER}", player2.getName()));
            return;
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player2);
        if (playerManager == null) {
            player.sendMessage(MessageType.ERROR.getFormatMessage());
            return;
        }
        playerManager.giveMenuSelector();
        player.sendMessage(MessageType.GIVE_MENU_ITEM.getFormatMessage().replace("{PLAYER}", player2.getName()));
        player2.sendMessage(MessageType.PLAYER_RECEIVE_MENU_ITEM.getFormatMessage());
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        if (array.length != 2) {
            CommandManager.printMessage(commandSender, this);
            return;
        }
        final Player player = commandSender.getServer().getPlayer(array[1]);
        if (player == null || !player.isOnline()) {
            commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
            return;
        }
        if (!WorldUtils.isWorldEnabled(player.getWorld())) {
            commandSender.sendMessage(MessageType.NO_PERMISSION_FOR_MENU_ITEM_IN_THAT_WORLD.getFormatMessage().replace("{PLAYER}", player.getName()));
            return;
        }
        if (!player.hasPermission(EnumPermission.MENU_SELECTOR.getPermission())) {
            commandSender.sendMessage(MessageType.NO_PERMISSION_FOR_MENU_ITEM.getFormatMessage().replace("{PLAYER}", player.getName()));
            return;
        }
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        if (playerManager == null) {
            commandSender.sendMessage(MessageType.ERROR.getFormatMessage());
            return;
        }
        playerManager.giveMenuSelector();
        commandSender.sendMessage(MessageType.GIVE_MENU_ITEM.getFormatMessage().replace("{PLAYER}", player.getName()));
        player.sendMessage(MessageType.PLAYER_RECEIVE_MENU_ITEM.getFormatMessage());
    }
}
