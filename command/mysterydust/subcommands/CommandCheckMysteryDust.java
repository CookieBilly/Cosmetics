

package ws.billy.CookieGadgets.command.mysterydust.subcommands;

import org.bukkit.OfflinePlayer;
import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import ws.billy.CookieGadgets.command.mysterydust.CommandManager;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.plugin.Plugin;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.mysterydust.SubCommand;

public class CommandCheckMysteryDust extends SubCommand
{
    public CommandCheckMysteryDust() {
        super("/mysterydust check [player]", "Check current mystery dust of a player.", null, "CookieGadgets.mysterydust.check", new String[] { "check" }, true);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length == 2) {
            final Player player2 = player.getServer().getPlayer(array[1]);
            if (player2 == null) {
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.offlinePlayerCommand((CommandSender)player, array));
            }
            else {
                if (!player2.isOnline()) {
                    player.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                    return;
                }
                player.sendMessage(MessageType.PLAYER_CHECK_MYSTERY_DUST.getFormatMessage().replace("{PLAYER}", player2.getName()).replace("{MYSTERY_DUST}", String.valueOf(CookieGadgets.getPlayerManager(player2).getMysteryDust())));
            }
        }
        else {
            player.sendMessage(MessageType.PLAYER_CHECK_MYSTERY_DUST.getFormatMessage().replace("{PLAYER}", player.getName()).replace("{MYSTERY_DUST}", String.valueOf(CookieGadgets.getPlayerManager(player).getMysteryDust())));
        }
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        if (array.length != 2) {
            CommandManager.printMessage(commandSender, this);
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
            commandSender.sendMessage(MessageType.PLAYER_CHECK_MYSTERY_DUST.getFormatMessage().replace("{PLAYER}", player.getName()).replace("{MYSTERY_DUST}", String.valueOf(CookieGadgets.getPlayerManager(player).getMysteryDust())));
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
        commandSender.sendMessage(MessageType.PLAYER_CHECK_MYSTERY_DUST.getFormatMessage().replace("{PLAYER}", offlinePlayerManager.getName()).replace("{MYSTERY_DUST}", String.valueOf(offlinePlayerManager.getMysteryDust())));
        return true;
    }
}
