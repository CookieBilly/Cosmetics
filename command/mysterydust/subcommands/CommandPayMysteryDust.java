

package ws.billy.CookieGadgets.command.mysterydust.subcommands;

import org.bukkit.OfflinePlayer;
import ws.billy.CookieGadgets.player.OfflinePlayerManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.command.CommandSender;
import ws.billy.CookieGadgets.command.mysterydust.CommandManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.command.mysterydust.SubCommand;

public class CommandPayMysteryDust extends SubCommand
{
    public CommandPayMysteryDust() {
        super("/mysterydust pay <player> <amount>", "Transfer mystery dust to a player.", "Transfer mystery dust to a player.\n\n&7End with the command 'msg=false', won't send\n&7message to the player when they received\n&7mystery dust.", "CookieGadgets.mysterydust.pay", new String[] { "pay", "transfer" }, false);
    }
    
    @Override
    protected void onCommandPlayer(final Player player, final String[] array) {
        if (array.length != 3 && array.length != 4) {
            CommandManager.printMessage((CommandSender)player, this);
            return;
        }
        final int mysteryDust = CookieGadgets.getPlayerManager(player).getMysteryDust();
        if (mysteryDust <= 0) {
            player.sendMessage(MessageType.PLAYER_NOT_ENOUGH_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(mysteryDust)));
            return;
        }
        final Player player2 = player.getServer().getPlayer(array[1]);
        if (player2 == null) {
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> this.offlinePlayerCommand(player, array));
        }
        else {
            if (!player2.isOnline()) {
                player.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return;
            }
            if (player2 == player) {
                player.sendMessage(MessageType.PAY_MYSTERY_DUST_TO_SELF.getFormatMessage());
                return;
            }
            try {
                Integer.parseInt(array[2]);
            }
            catch (NumberFormatException ex) {
                player.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
                return;
            }
            final int int1 = Integer.parseInt(array[2]);
            if (int1 <= 0) {
                player.sendMessage(MessageType.REQUIRED_POSITIVE_NUMBER.getFormatMessage());
                return;
            }
            if (int1 > mysteryDust) {
                player.sendMessage(MessageType.PLAYER_NOT_ENOUGH_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(mysteryDust)));
                return;
            }
            CookieGadgets.getPlayerManager(player).removeMysteryDust(int1);
            CookieGadgets.getPlayerManager(player2).addMysteryDust(int1);
            player.sendMessage(MessageType.PLAYER_SENT_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)).replace("{PLAYER}", player2.getName()));
            player.sendMessage(MessageType.REMOVED_MYSTERY_DUST_FROM_PLAYER.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)));
            if (array.length == 4 && array[3].equalsIgnoreCase("msg=false")) {
                return;
            }
            player2.sendMessage(MessageType.PLAYER_RECEIVED_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)).replace("{PLAYER}", player.getName()));
        }
    }
    
    @Override
    protected void onOtherCommandSender(final CommandSender commandSender, final String[] array) {
        this.notAllowed(commandSender);
    }
    
    private boolean offlinePlayerCommand(final Player player, final String[] array) {
        OfflinePlayerManager offlinePlayerManager = null;
        if (player.getServer().getPlayer(array[1]) == null) {
            final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(array[1]);
            if (!CookieGadgets.getDatabaseManager().getDatabaseUtils().isExist(offlinePlayer)) {
                player.sendMessage(MessageType.PLAYER_NOT_FOUND.getFormatMessage());
                return false;
            }
            offlinePlayerManager = new OfflinePlayerManager(offlinePlayer.getUniqueId());
        }
        final int mysteryDust = CookieGadgets.getPlayerManager(player).getMysteryDust();
        if (mysteryDust <= 0) {
            player.sendMessage(MessageType.PLAYER_NOT_ENOUGH_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(mysteryDust)));
            return false;
        }
        try {
            Integer.parseInt(array[2]);
        }
        catch (NumberFormatException ex) {
            player.sendMessage(MessageType.REQUIRED_NUMBER_FORMAT.getFormatMessage());
            return false;
        }
        final int int1 = Integer.parseInt(array[2]);
        if (int1 <= 0) {
            player.sendMessage(MessageType.REQUIRED_POSITIVE_NUMBER.getFormatMessage());
            return false;
        }
        if (int1 > mysteryDust) {
            player.sendMessage(MessageType.PLAYER_NOT_ENOUGH_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(mysteryDust)));
            return false;
        }
        if (offlinePlayerManager.getPlayer().getName() == player.getName()) {
            player.sendMessage(MessageType.PAY_MYSTERY_DUST_TO_SELF.getFormatMessage());
            return false;
        }
        CookieGadgets.getPlayerManager(player).removeMysteryDust(int1);
        offlinePlayerManager.addMysteryDust(int1);
        player.sendMessage(MessageType.PLAYER_SENT_MYSTERY_DUST.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)).replace("{PLAYER}", offlinePlayerManager.getName()));
        player.sendMessage(MessageType.REMOVED_MYSTERY_DUST_FROM_PLAYER.getFormatMessage().replace("{MYSTERY_DUST}", String.valueOf(int1)));
        return true;
    }
}
