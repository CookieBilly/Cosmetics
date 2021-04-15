

package ws.billy.CookieGadgets.player;

import java.util.Iterator;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.updater.UpdaterManager;
import ws.billy.CookieGadgets.utils.WorldUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import java.util.Collection;
import ws.billy.CookieGadgets.cosmetics.CategoryManager;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.Player;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;
import java.util.Map;

public class GPlayer
{
    private Map<UUID, PlayerManager> playerCache;
    
    public GPlayer() {
        this.playerCache = new ConcurrentHashMap<UUID, PlayerManager>();
    }
    
    public PlayerManager getPlayerManager(final Player player) {
        synchronized (this) {
            try {
                final PlayerManager playerManager = this.playerCache.get(player.getUniqueId());
                if (playerManager == null) {
                    // monitorexit(this)
                    return this.create(player);
                }
                return playerManager;
            }
            catch (NullPointerException ex) {
                // monitorexit(this)
                return null;
            }
        }
    }
    
    public PlayerManager create(final Player player) {
        synchronized (this) {
            final PlayerManager playerManager = new PlayerManager(player.getUniqueId());
            this.playerCache.put(player.getUniqueId(), playerManager);
            return playerManager;
        }
    }
    
    public boolean remove(final Player player) {
        synchronized (this) {
            if (!this.playerCache.containsKey(player.getUniqueId())) {
                return false;
            }
            final PlayerManager playerManager = this.getPlayerManager(player);
            if (playerManager == null) {
                // monitorexit(this)
                return false;
            }
            playerManager.stopAnimation();
            if (CookieGadgets.getCookieGadgetsData().isMysteryBoxesRewardEnabled()) {
                playerManager.stopMysteryBoxReward(true);
            }
            playerManager.savePlayerStats(true);
            CategoryManager.removeItemFromImproperSlot(playerManager);
            this.playerCache.remove(player.getUniqueId());
            // monitorexit(this)
            return true;
        }
    }
    
    public Collection<PlayerManager> getGPlayers() {
        synchronized (this) {
            return this.playerCache.values();
        }
    }
    
    public void initPlayer(final Player player) {
        if (!player.isOnline()) {
            return;
        }
        this.create(player);
        final PlayerManager playerManager = this.getPlayerManager(player);
        if (CookieGadgets.getDatabaseManager().getConnection() != null) {
            CookieGadgets.getDatabaseManager().getDatabaseUtils().initPlayerStats(playerManager);
        }
        playerManager.initData();
        if (CookieGadgets.getCookieGadgetsData().isGiveMenuSelectorOnJoin() && player.hasPermission(EnumPermission.MENU_SELECTOR.getPermission()) && WorldUtils.isWorldEnabled(player.getWorld())) {
            playerManager.giveMenuSelector();
        }
        if (CookieGadgets.getCookieGadgetsData().isMysteryBoxesEnabled() && CookieGadgets.getCookieGadgetsData().isMysteryBoxesRewardEnabled()) {
            playerManager.startMysteryBoxReward();
        }
        if (CookieGadgets.getCookieGadgetsData().syncCosmeticsOnJoin()) {
            playerManager.loadEquippedCosmeticsAfterDataLoaded();
        }
        if (CookieGadgets.getCookieGadgetsData().isCheckUpdateEnabled() && CookieGadgets.getUpdaterChecker() != null && CookieGadgets.getUpdaterChecker().isOutdated() && (player.isOp() || player.hasPermission(EnumPermission.COMMAND_CHECK_UPDATE.getPermission()))) {
            player.sendMessage(String.valueOf(CookieGadgets.getCookieGadgetsData().getPrefix()) + UpdaterManager.getMessage());
        }
    }
    
    public void initPlayers() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (!player.isOnline()) {
                return;
            }
            final PlayerManager playerManager = this.getPlayerManager(player);
            if (CookieGadgets.getDatabaseManager().getConnection() != null) {
                CookieGadgets.getDatabaseManager().getDatabaseUtils().initPlayerStats(playerManager);
            }
            playerManager.initData();
            if (CookieGadgets.getCookieGadgetsData().isGiveMenuSelectorOnJoin() && player.hasPermission(EnumPermission.MENU_SELECTOR.getPermission()) && WorldUtils.isWorldEnabled(player.getWorld())) {
                playerManager.giveMenuSelector();
            }
            if (CookieGadgets.getCookieGadgetsData().isMysteryBoxesEnabled() && CookieGadgets.getCookieGadgetsData().isMysteryBoxesRewardEnabled()) {
                playerManager.startMysteryBoxReward();
            }
            if (CookieGadgets.getCookieGadgetsData().syncCosmeticsOnJoin()) {
                playerManager.loadEquippedCosmeticsAfterDataLoaded();
            }
            if (!CookieGadgets.getCookieGadgetsData().isCheckUpdateEnabled() || CookieGadgets.getUpdaterChecker() == null || !CookieGadgets.getUpdaterChecker().isOutdated() || (!player.isOp() && !player.hasPermission(EnumPermission.COMMAND_CHECK_UPDATE.getPermission()))) {
                continue;
            }
            player.sendMessage(String.valueOf(CookieGadgets.getCookieGadgetsData().getPrefix()) + UpdaterManager.getMessage());
        }
    }
    
    public void dispose() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.stopAnimation();
                playerManager.savePlayerStats(false);
                playerManager.saveEquippedCosmetics(false);
                playerManager.unequipActiveCosmetics();
                playerManager.removeMenuSelector();
                CategoryManager.removeItemFromImproperSlot(playerManager);
                if (!CookieGadgets.getCookieGadgetsData().isMysteryBoxesRewardEnabled()) {
                    continue;
                }
                playerManager.stopMysteryBoxReward(false);
            }
        }
        this.playerCache.clear();
        this.playerCache = null;
    }
    
    public void clearActiveCosmetics() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.unequipActiveCosmetics();
            }
        }
    }
    
    public void unequipHat() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.unequipHat();
            }
        }
    }
    
    public void unequipAnimatedHat() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.unequipAnimatedHat();
            }
        }
    }
    
    public void unequipParticle() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.unequipParticle();
            }
        }
    }
    
    public void unequipSuit() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.unequipSuit();
            }
        }
    }
    
    public void unequipGadget() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.unequipGadget();
            }
        }
    }
    
    public void unequipPet() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.unequipPet();
            }
        }
    }
    
    public void unequipMiniature() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.unequipMiniature();
            }
        }
    }
    
    public void unequipMorph() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.unequipMorph();
            }
        }
    }
    
    public void unequipBanner() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.unequipBanner();
            }
        }
    }
    
    public void unequipEmote() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.unequipEmote();
            }
        }
    }
    
    public void unequipCloak() {
        for (final PlayerManager playerManager : this.playerCache.values()) {
            if (playerManager.getPlayer() != null && playerManager.getPlayer().isOnline()) {
                playerManager.unequipCloak();
            }
        }
    }
}
