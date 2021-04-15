

package ws.billy.CookieGadgets.command.mysterydust.subcommands;

import org.bukkit.OfflinePlayer;
import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import ws.billy.CookieGadgets.player.PlayerManager;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.command.mysterydust.CommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.mysterydust.SubCommand;

public class CommandRemoveMysteryDust extends SubCommand
{
    public CommandRemoveMysteryDust() {
        super("/mysterydust remove <player> <amount>", "Remove mystery dust from a player.", "Remove mystery dust from a player.\n\n&7End with the command 'msg=false', won't send\n&7message to the player when they mystery dust\n&7have been removed.", "CookieGadgets.mysterydust.remove", new String[] { "remove" }, true);
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
            CommandManager.printMessage(commandSender, new CommandRemoveMysteryDust());
            return;
        }
        final Player player = commandSender.getServer().getPlayer(array[1]);
        if (player == null) {
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.offlinePlayerCommand(commandSender, array));
        }
        else {
            if (!player.isOnline()) {
                commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
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
            final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
            if (playerManager.getMysteryDust() - int1 < 0) {
                commandSender.sendMessage(MessageType.PLAYER_ONLY_HAVE_MYSTERY_DUST.getFormatMessage().replace("{PLAYER}", player.getName()).replace("{MYSTERY_DUST}", String.valueOf(playerManager.getMysteryDust())));
                return;
            }
            playerManager.removeMysteryDust(int1);
            commandSender.sendMessage(MessageType.PLAYER_REMOVED_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)).replace("{PLAYER}", player.getName()));
            if (array.length == 4 && array[4].equalsIgnoreCase("msg=false")) {
                return;
            }
            player.sendMessage(MessageType.REMOVED_MYSTERY_DUST_FROM_PLAYER.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)));
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
        if (offlinePlayerManager.getMysteryDust() - int1 < 0) {
            commandSender.sendMessage(MessageType.PLAYER_ONLY_HAVE_MYSTERY_DUST.getFormatMessage().replace("{PLAYER}", offlinePlayerManager.getName()).replace("{MYSTERY_DUST}", String.valueOf(offlinePlayerManager.getMysteryDust())));
            return false;
        }
        offlinePlayerManager.removeMysteryDust(int1);
        commandSender.sendMessage(MessageType.PLAYER_REMOVED_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)).replace("{PLAYER}", offlinePlayerManager.getName()));
        return true;
    }
}
