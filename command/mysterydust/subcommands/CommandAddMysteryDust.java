

package ws.billy.CookieGadgets.command.mysterydust.subcommands;

import org.bukkit.OfflinePlayer;
import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.command.mysterydust.CommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.mysterydust.SubCommand;

public class CommandAddMysteryDust extends SubCommand
{
    public CommandAddMysteryDust() {
        super("/mysterydust add <all|player> <amount>", "Add mystery dust to a player.", "Add mystery dust to a player.\n\n&7End with the command 'msg=false', won't send\n&7message to the player when they received\n&7mystery dust.", "CookieGadgets.mysterydust.add", new String[] { "add", "give" }, true);
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
        if (array.length != 3 && array.length != 4) {
            CommandManager.printMessage(commandSender, this);
            return;
        }
        Player player = commandSender.getServer().getPlayer(array[1]);
        if (array[1].equalsIgnoreCase("all")) {
            player = null;
        }
        if (player == null && !array[1].equalsIgnoreCase("all")) {
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.offlinePlayerCommand(commandSender, array));
        }
        else {
            try {
                Integer.parseInt(array[2]);
            }
            catch (NumberFormatException ex) {
                commandSender.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
                return;
            }
            final int int1 = Integer.parseInt(array[2]);
            if (int1 <= 0) {
                commandSender.sendMessage(MessageType.REQUIRED_POSITIVE_NUMBER.getFormatMessage());
                return;
            }
            if (player == null) {
                int i = 0;
                for (final Player player2 : Bukkit.getOnlinePlayers()) {
                    if (!player2.isOnline()) {
                        continue;
                    }
                    CookieGadgets.getPlayerManager(player2).addMysteryDust(int1);
                    if (array.length == 4) {
                        if (!array[3].equalsIgnoreCase("msg=false")) {
                            player2.sendMessage(MessageType.PLAYER_RECEIVED_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)).replace("{PLAYER}", commandSender.getName()));
                        }
                    }
                    else {
                        player2.sendMessage(MessageType.PLAYER_RECEIVED_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)).replace("{PLAYER}", commandSender.getName()));
                    }
                    ++i;
                }
                commandSender.sendMessage(MessageType.PLAYER_ADDED_MYSTERY_DUST_TO_ONLINE_PLAYERS.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)).replace("{ONLINE}", String.valueOf(i)));
            }
            else {
                CookieGadgets.getPlayerManager(player).addMysteryDust(int1);
                if (array.length == 4) {
                    if (!array[3].equalsIgnoreCase("msg=false")) {
                        player.sendMessage(MessageType.PLAYER_RECEIVED_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)).replace("{PLAYER}", commandSender.getName()));
                    }
                }
                else {
                    player.sendMessage(MessageType.PLAYER_RECEIVED_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)).replace("{PLAYER}", commandSender.getName()));
                }
                commandSender.sendMessage(MessageType.PLAYER_ADDED_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)).replace("{PLAYER}", player.getName()));
            }
        }
    }
    
    private boolean offlinePlayerCommand(final CommandSender commandSender, final String[] array) {
        OfflinePlayerManager offlinePlayerManager = null;
        if (commandSender.getServer().getPlayer(array[1]) == null) {
            final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(array[1]);
            if (!CookieGadgets.getDatabaseManager().getDatabaseUtils().isExist(offlinePlayer)) {
                commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return false;
            }
            offlinePlayerManager = new OfflinePlayerManager(offlinePlayer.getUniqueId());
        }
        try {
            Integer.parseInt(array[2]);
        }
        catch (NumberFormatException ex) {
            commandSender.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
            return false;
        }
        final int int1 = Integer.parseInt(array[2]);
        if (int1 <= 0) {
            commandSender.sendMessage(MessageType.REQUIRED_POSITIVE_NUMBER.getFormatMessage());
            return false;
        }
        offlinePlayerManager.addMysteryDust(int1);
        commandSender.sendMessage(MessageType.PLAYER_ADDED_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)).replace("{PLAYER}", offlinePlayerManager.getName()));
        return true;
    }
}
