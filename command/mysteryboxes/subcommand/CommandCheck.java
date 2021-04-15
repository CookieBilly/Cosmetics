

package ws.billy.CookieGadgets.command.mysteryboxes.subcommand;

import org.bukkit.OfflinePlayer;
import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.mysteryboxes.CommandManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.mysteryboxes.SubCommand;

public class CommandCheck extends SubCommand
{
    public CommandCheck() {
        super("/gmysteryboxes check [player]", "Check player's mystery boxes amount.", null, "CookieGadgets.mysteryboxes.check", new String[] { "check", "lookup" }, true);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length > 2) {
            CommandManager.printMessage((CommandSender)player, this);
            return;
        }
        if (array.length != 1) {
            final Player player2 = player.getServer().getPlayer(array[1]);
            if (player2 == null) {
                Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    player.sendMessage(MessageType.COUNTING_MYSTERY_BOXES.getFormatMessage());
                    this.offlinePlayerCommand((CommandSender)player, array);
                });
            }
            else {
                if (!player2.isOnline()) {
                    player.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                    return;
                }
                final PlayerManager playerManager = CookieGadgets.getPlayerManager(player2);
                if (playerManager == null) {
                    player.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                    return;
                }
                player.sendMessage(MessageType.PLAYER_CHECK_MYSTERY_BOXES.getFormatMessage().replace("{PLAYER}", playerManager.getPlayer().getName()).replace("{MYSTERY_BOXES}", String.valueOf(playerManager.getMysteryBoxes())));
            }
            return;
        }
        final PlayerManager playerManager2 = CookieGadgets.getPlayerManager(player);
        if (playerManager2 == null) {
            player.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
            return;
        }
        player.sendMessage(MessageType.PLAYER_CHECK_MYSTERY_BOXES.getFormatMessage().replace("{PLAYER}", player.getName()).replace("{MYSTERY_BOXES}", String.valueOf(playerManager2.getMysteryBoxes())));
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        if (array.length != 2) {
            CommandManager.printMessage(commandSender, new CommandCheck());
            return;
        }
        final Player player = commandSender.getServer().getPlayer(array[1]);
        if (player == null) {
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                commandSender.sendMessage(MessageType.COUNTING_MYSTERY_BOXES.getFormatMessage());
                this.offlinePlayerCommand(commandSender, array);
            });
        }
        else {
            if (!player.isOnline()) {
                commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
            final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
            if (playerManager == null) {
                commandSender.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
            commandSender.sendMessage(MessageType.PLAYER_CHECK_MYSTERY_BOXES.getFormatMessage().replace("{PLAYER}", playerManager.getPlayer().getName()).replace("{MYSTERY_BOXES}", String.valueOf(playerManager.getMysteryBoxes())));
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
        commandSender.sendMessage(MessageType.PLAYER_CHECK_MYSTERY_BOXES.getFormatMessage().replace("{PLAYER}", offlinePlayerManager.getName()).replace("{MYSTERY_BOXES}", String.valueOf(offlinePlayerManager.getMysteryBoxes())));
        return true;
    }
}
