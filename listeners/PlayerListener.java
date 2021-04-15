

package ws.billy.CookieGadgets.listeners;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.GameMode;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.event.entity.PlayerDeathEvent;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.utils.WorldUtils;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetFlowerGiver;
import ws.billy.CookieGadgets.cosmetics.gadgets.types.GadgetScarecrow;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class PlayerListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(final PlayerJoinEvent playerJoinEvent) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> CookieGadgets.getGPlayer().initPlayer(playerJoinEvent.getPlayer()));
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(final PlayerQuitEvent playerQuitEvent) {
        final Player player = playerQuitEvent.getPlayer();
        final PlayerManager playerManager = CookieGadgets.getPlayerManager(player);
        playerManager.removeMenuSelector();
        if (CookieGadgets.getCookieGadgetsData().syncCosmeticsOnJoin()) {
            playerManager.saveEquippedCosmetics(true);
        }
        playerManager.unequipActiveCosmetics();
        GadgetScarecrow.unequipLitPumpkin(player);
        GadgetFlowerGiver.removeFlower(player);
        CookieGadgets.getGPlayer().remove(player);
    }
    
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerMove(final PlayerMoveEvent playerMoveEvent) {
        if (CookieGadgets.getCookieGadgetsData().isMysteryBoxesRewardEnabled()) {
            final PlayerManager playerManager = CookieGadgets.getPlayerManager(playerMoveEvent.getPlayer());
            if (playerMoveEvent.getFrom().getX() != playerMoveEvent.getTo().getX() || playerMoveEvent.getFrom().getY() != playerMoveEvent.getTo().getY() || playerMoveEvent.getFrom().getZ() != playerMoveEvent.getTo().getZ()) {
                if (!playerManager.isMoving()) {
                    playerManager.setMoving(true);
                }
            }
            else {
                playerManager.setMoving(false);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChangeWorld(final PlayerChangedWorldEvent playerChangedWorldEvent) {
        playerChangedWorldEvent.getPlayer();
        if (WorldUtils.isWorldEnabled(playerChangedWorldEvent.getFrom())) {
            final Player player;
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                CookieGadgets.getPlayerManager(player).cacheEquippedCosmetics();
                CookieGadgets.getPlayerManager(player).unequipActiveCosmetics();
                return;
            });
        }
        final Player player2;
        final PlayerManager playerManager;
        final PlayerManager playerManager2;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            CookieGadgets.getPlayerManager(player2);
            if (WorldUtils.isWorldEnabled(player2.getWorld())) {
                playerManager.loadEquippedCosmeticsFromCache();
                if (CookieGadgets.getCookieGadgetsData().isGiveMenuSelectorOnJoin() && player2.hasPermission(EnumPermission.MENU_SELECTOR.getPermission())) {
                    playerManager.giveMenuSelector();
                }
                else {
                    playerManager.removeMenuSelector();
                }
            }
            else {
                Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                    playerManager2.unequipActiveCosmetics();
                    playerManager2.removeMenuSelector();
                });
            }
        });
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(final PlayerDeathEvent playerDeathEvent) {
        if (playerDeathEvent.getEntity() instanceof Player && !playerDeathEvent.getEntity().hasMetadata("NPC") && WorldUtils.isWorldEnabled(playerDeathEvent.getEntity().getWorld())) {
            final Player entity = playerDeathEvent.getEntity();
            final int menuSelectorSlot = CookieGadgets.getCookieGadgetsData().getMenuSelectorSlot();
            if (CookieGadgets.getCookieGadgetsData().isGiveMenuSelectorOnJoin() && entity.hasPermission(EnumPermission.MENU_SELECTOR.getPermission()) && entity.getInventory().getItem(menuSelectorSlot) != null && entity.getInventory().getItem(menuSelectorSlot).hasItemMeta() && entity.getInventory().getItem(menuSelectorSlot).getItemMeta().hasDisplayName() && entity.getInventory().getItem(menuSelectorSlot).getItemMeta().getDisplayName().equals(ChatUtil.format(CookieGadgets.getCookieGadgetsData().getMenuSelector().getItemMeta().getDisplayName()))) {
                playerDeathEvent.getDrops().remove(entity.getInventory().getItem(menuSelectorSlot));
                entity.getInventory().setItem(menuSelectorSlot, (ItemStack)null);
            }
            CookieGadgets.getPlayerManager(entity).cacheEquippedCosmetics();
            CookieGadgets.getPlayerManager(entity).unequipActiveCosmetics();
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(final PlayerRespawnEvent playerRespawnEvent) {
        if (WorldUtils.isWorldEnabled(playerRespawnEvent.getPlayer().getWorld())) {
            final Player player;
            final PlayerManager playerManager;
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                CookieGadgets.getPlayerManager(player);
                if (playerManager != null) {
                    if (CookieGadgets.getCookieGadgetsData().isGiveMenuSelectorOnJoin() && player.hasPermission(EnumPermission.MENU_SELECTOR.getPermission())) {
                        playerManager.giveMenuSelector();
                    }
                    playerManager.loadEquippedCosmeticsFromCache();
                }
            });
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onToggleSneak(final PlayerToggleSneakEvent playerToggleSneakEvent) {
        if (!WorldUtils.isWorldEnabled(playerToggleSneakEvent.getPlayer().getWorld())) {
            return;
        }
        CookieGadgets.getNMSManager().leaveVehicle(playerToggleSneakEvent.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDismountPlayer(final EntityDismountEvent event) {
        if (!(event.getDismounted() instanceof Player)) {
            return;
        }
        if (!WorldUtils.isWorldEnabled(event.getDismounted().getWorld())) {
            return;
        }
        CookieGadgets.getNMSManager().passengerDismount(event);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onGameModeChange(final PlayerGameModeChangeEvent playerGameModeChangeEvent) {
        if (!WorldUtils.isWorldEnabled(playerGameModeChangeEvent.getPlayer().getWorld())) {
            return;
        }
        if (playerGameModeChangeEvent.getNewGameMode() != GameMode.SPECTATOR) {
            return;
        }
        CookieGadgets.getNMSManager().leaveVehicle(playerGameModeChangeEvent.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onExecuteCommand(final PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        if (WorldUtils.isWorldEnabled(playerCommandPreprocessEvent.getPlayer().getWorld()) && CookieGadgets.getPlayerManager(playerCommandPreprocessEvent.getPlayer()).hasActiveCosmetics()) {
            for (final String s : CookieGadgets.getCookieGadgetsData().getDisabledCommands()) {
                if (playerCommandPreprocessEvent.getMessage().replace("/", "").toLowerCase().startsWith(s) || playerCommandPreprocessEvent.getMessage().replace("/", "").toLowerCase().equalsIgnoreCase(s)) {
                    playerCommandPreprocessEvent.getPlayer().sendMessage(MessageType.DISABLED_COMMAND_WHILE_COSMETICS_ACTIVATED.getFormatMessage());
                    playerCommandPreprocessEvent.setCancelled(true);
                }
            }
        }
    }
}
