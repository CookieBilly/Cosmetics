

package ws.billy.CookieGadgets.command.mysteryboxes.subcommand;

import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.command.mysteryboxes.CommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.mysteryboxes.SubCommand;

public class CommandGift extends SubCommand
{
    public CommandGift() {
        super("/gmysteryboxes gift <player> <pack>", "Give mystery gifts to a player.", "Give mystery gifts to a player.\n&7Each pack contains 5 mystery boxes.\n\n&7End with the command 'msg=false', won't send\n&7message to the player when they received\n&7mystery gifts.", "CookieGadgets.mysteryboxes.gift", new String[] { "gift" }, true);
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
        if (array.length < 3 || array.length > 4) {
            CommandManager.printMessage(commandSender, this);
            return;
        }
        final Player player = commandSender.getServer().getPlayer(array[1]);
        if (player == null || !player.isOnline()) {
            commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(array[2]);
        }
        catch (NumberFormatException ex) {
            commandSender.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
            return;
        }
        if (int1 <= 0) {
            commandSender.sendMessage(MessageType.REQUIRED_POSITIVE_NUMBER.getFormatMessage());
            return;
        }
        boolean b = true;
        if (array.length == 4 && array[3].startsWith("msg=") && array[3].equalsIgnoreCase("msg=false")) {
            b = false;
        }
        CookieGadgets.getPlayerManager(player).addGiftPacks(int1);
        commandSender.sendMessage(MessageType.PLAYER_GAVE_MYSTERY_GIFTS.getFormatMessage().replace("{GIFTS}", String.valueOf(int1)).replace("{PLAYER}", player.getName()));
        if (b && player != commandSender) {
            player.sendMessage(MessageType.PLAYER_RECEIVED_MYSTERY_GIFTS.getFormatMessage().replace("{GIFTS}", String.valueOf(int1)).replace("{PLAYER}", commandSender.getName()));
        }
    }
}
