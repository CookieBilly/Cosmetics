

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.Material;
import ws.billy.CookieGadgets.utils.EnumItem;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;
import org.bukkit.event.Listener;

public abstract class Morph implements Listener
{
    public UUID uuid;
    private MorphType type;
    private Player player;
    private PlayerManager pManager;
    private Listener listener;
    
    public Morph(final UUID uuid, final MorphType type) {
        this.type = type;
        if (!type.isEnabled()) {
            return;
        }
        if (uuid != null) {
            this.uuid = uuid;
            this.player = Bukkit.getPlayer(uuid);
            this.pManager = CookieGadgets.getPlayerManager(this.getPlayer());
            if (PermissionUtils.noPermission(this.getPlayer(), type.getPermission(), EnumPermission.MORPHS.getPermission(), false)) {
                this.pManager.unequipMorph();
                return;
            }
            if (this.pManager.getCurrentMorph() != null) {
                this.pManager.removeMorph();
            }
            try {
                new BukkitRunnable() {
                    public void run() {
                        if (Bukkit.getPlayer(Morph.this.getPlayerUUID()) != null && Morph.this.getPlayerManager().getCurrentMorph() != null && Morph.this.getPlayerManager().getCurrentMorph().getType() == Morph.this.getType()) {
                            if (CookieGadgets.getCookieGadgetsData().isCooldownInActionBar() && Morph.this.getPlayerManager().morphCooldown().containsKey(Morph.this.getType())) {
                                if (Morph.this.getPlayerManager().morphCooldown().get(Morph.this.getType()) - System.currentTimeMillis() > 0L && !Morph.this.getPlayerManager().isBypassCooldown()) {
                                    Morph.this.getPlayerManager().sendCooldownBar(type.getDisplayName(), Morph.this.getPlayerManager().morphCooldown().get(Morph.this.getType()), type.getCooldown());
                                }
                                else {
                                    Morph.this.getPlayerManager().resetCooldownBar();
                                    Morph.this.getPlayerManager().morphCooldown().remove(Morph.this.getType());
                                }
                            }
                            Morph.this.onUpdate();
                        }
                        else {
                            this.cancel();
                        }
                    }
                }.runTaskTimerAsynchronously((Plugin)CookieGadgets.getInstance(), 0L, 1L);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.listener = (Listener)new MorphListener(this);
            CookieGadgets.getInstance().registerListener(this.listener);
            CookieGadgets.getInstance().registerListener((Listener)this);
            this.getPlayerManager().setCurrentMorph(this);
        }
    }
    
    protected UUID getPlayerUUID() {
        return this.uuid;
    }
    
    public MorphType getType() {
        return this.type;
    }
    
    protected Player getPlayer() {
        return this.player;
    }
    
    protected PlayerManager getPlayerManager() {
        return this.pManager;
    }
    
    abstract void onClick();
    
    abstract void onUpdate();
    
    public abstract void onClear();
    
    protected boolean checkRequirements() {
        return true;
    }
    
    public void removeListener() {
        HandlerList.unregisterAll((Listener)this);
    }
    
    public void unregisterListeners() {
        try {
            HandlerList.unregisterAll((Listener)this);
            HandlerList.unregisterAll(this.listener);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void clear() {
        if (this.uuid == null) {
            return;
        }
        if (CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentMorph() != null) {
            CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentMorph(null);
        }
        this.unregisterListeners();
        this.uuid = null;
    }
    
    public class MorphListener implements Listener
    {
        private Morph morph;
        
        public MorphListener(final Morph morph) {
            this.morph = morph;
        }
        
        @EventHandler
        protected void onPlayerActivateMorphSkill(final PlayerInteractEvent playerInteractEvent) {
            final Player player = playerInteractEvent.getPlayer();
            final UUID uniqueId = player.getUniqueId();
            if (playerInteractEvent.getAction() != Action.RIGHT_CLICK_AIR && playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK) {
                return;
            }
            if (!player.getItemInHand().hasItemMeta() || !player.getItemInHand().getItemMeta().hasDisplayName()) {
                return;
            }
            if (!uniqueId.equals(this.morph.uuid)) {
                return;
            }
            if (!CookieGadgets.getGDisguise().isDisguised(player) || CookieGadgets.getPlayerManager(player).getEquippedMorph() == null) {
                playerInteractEvent.setCancelled(true);
                return;
            }
            if (!player.getItemInHand().getItemMeta().getDisplayName().equals(EnumItem.MORPH_SLIMEBALL.getDisplayName()) || player.getItemInHand().getType() != Material.SLIME_BALL) {
                return;
            }
            if (CookieGadgets.getPlayerManager(player).morphCooldown().containsKey(Morph.this.getType()) && !CookieGadgets.getPlayerManager(player).isBypassCooldown()) {
                if (CookieGadgets.getPlayerManager(player).morphCooldown().get(Morph.this.getType()) - System.currentTimeMillis() > 0L) {
                    player.sendMessage(MessageType.COOLDOWN.getFormatMessage().replace("{COOLDOWN}", String.valueOf((CookieGadgets.getPlayerManager(player).morphCooldown().get(Morph.this.getType()) - System.currentTimeMillis()) / 1000L + 1L)));
                    player.updateInventory();
                    playerInteractEvent.setCancelled(true);
                    return;
                }
                CookieGadgets.getPlayerManager(player).morphCooldown().remove(Morph.this.getType());
            }
            if (PermissionUtils.noPermission(Morph.this.getPlayer(), Morph.this.type.getPermission(), EnumPermission.MORPHS.getPermission(), true)) {
                CookieGadgets.getPlayerManager(player).unequipMorph();
                player.updateInventory();
                playerInteractEvent.setCancelled(true);
                return;
            }
            if (!Morph.this.type.isAbilityEnabled()) {
                player.sendMessage(MessageType.MORPH_ABILITY_IS_DISABLED.getFormatMessage().replace("{MORPH}", Morph.this.type.getDisplayNameStripColor()));
                player.updateInventory();
                playerInteractEvent.setCancelled(true);
                return;
            }
            if (!CookieGadgets.getCookieGadgetsData().isLibsDisguiseEnabled()) {
                player.updateInventory();
                playerInteractEvent.setCancelled(true);
                return;
            }
            if (Morph.this.checkRequirements()) {
                this.morph.onClick();
                if (!CookieGadgets.getPlayerManager(player).isBypassCooldown()) {
                    CookieGadgets.getPlayerManager(player).morphCooldown().put(Morph.this.getType(), System.currentTimeMillis() + Morph.this.getType().getCooldown() * 1000L);
                }
                player.updateInventory();
                playerInteractEvent.setCancelled(true);
                return;
            }
            player.updateInventory();
            playerInteractEvent.setCancelled(true);
        }
    }
}
