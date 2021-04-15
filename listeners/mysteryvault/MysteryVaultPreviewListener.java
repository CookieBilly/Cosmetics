

package ws.billy.CookieGadgets.listeners.mysteryvault;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.event.Event;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.api.event.mysteryvault.MysteryVaultPreviewEvent;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVaultManager;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class MysteryVaultPreviewListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPreviewMysteryVault(final PlayerInteractEvent playerInteractEvent) {
        final Player player = playerInteractEvent.getPlayer();
        final Block clickedBlock = playerInteractEvent.getClickedBlock();
        if (playerInteractEvent.getAction() != Action.LEFT_CLICK_BLOCK && playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (MysteryVaultManager.vaults().size() == 0) {
            return;
        }
        for (final MysteryVault selectedMysteryVault : MysteryVaultManager.vaults()) {
            if (clickedBlock.getLocation().equals((Object)selectedMysteryVault.getLocation())) {
                final MysteryVaultPreviewEvent mysteryVaultPreviewEvent = new MysteryVaultPreviewEvent(player, selectedMysteryVault);
                Bukkit.getServer().getPluginManager().callEvent((Event)mysteryVaultPreviewEvent);
                if (mysteryVaultPreviewEvent.isCancelled()) {
                    playerInteractEvent.setCancelled(true);
                    return;
                }
                if (CookieGadgets.getPlayerManager(player).isOpeningMysteryBox()) {
                    player.sendMessage(MessageType.CAN_ONLY_OPEN_ONE_MYSTERY_BOX.getFormatMessage());
                    player.closeInventory();
                    playerInteractEvent.setCancelled(true);
                    return;
                }
                CookieGadgets.getPlayerManager(player).openMysteryVaultMenu(1);
                CookieGadgets.getPlayerManager(player).setSelectedMysteryVault(selectedMysteryVault);
                playerInteractEvent.setCancelled(true);
            }
        }
    }
}
