

package ws.billy.CookieGadgets.listeners.cosmetics;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetTicTacToe;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetFlowerGiver;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.Listener;

public class CommandProcessListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerExecuteCommands(final PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        final Player player = playerCommandPreprocessEvent.getPlayer();
        if (!playerCommandPreprocessEvent.getMessage().startsWith("/flowergiver") && !playerCommandPreprocessEvent.getMessage().startsWith("/tictactoe")) {
            return;
        }
        if (!GadgetFlowerGiver.pendingPlayers.contains(player) && !GadgetFlowerGiver.acceptedFlowerPlayers.containsKey(player) && playerCommandPreprocessEvent.getMessage().startsWith("/flowergiver")) {
            player.sendMessage(ChatUtil.format(GadgetFlowerGiver.noPendingInviteMessage));
            playerCommandPreprocessEvent.setCancelled(true);
            return;
        }
        if (!GadgetTicTacToe.pendingPlayers.contains(player) && !GadgetTicTacToe.acceptedPlayers.contains(player) && playerCommandPreprocessEvent.getMessage().startsWith("/tictactoe")) {
            player.sendMessage(ChatUtil.format(GadgetTicTacToe.noPendingChallengeMessage));
            playerCommandPreprocessEvent.setCancelled(true);
        }
    }
}
