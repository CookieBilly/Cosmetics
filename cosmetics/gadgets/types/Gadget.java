

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.BlockUtil;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.event.Listener;

public abstract class Gadget implements Listener
{
    public UUID uuid;
    private GadgetType type;
    private Player player;
    private Listener listener;
    protected boolean showCooldownBar;
    protected boolean addCooldownAfterClick;
    protected static ArrayList<Player> hideCooldownBar;
    
    static {
        Gadget.hideCooldownBar = new ArrayList<Player>();
    }
    
    public Gadget(final UUID uuid, final GadgetType type) {
        this.showCooldownBar = true;
        this.addCooldownAfterClick = true;
        this.type = type;
        if (!type.isEnabled()) {
            return;
        }
        if (uuid != null) {
            this.uuid = uuid;
            this.player = Bukkit.getPlayer(uuid);
            if (PermissionUtils.noPermission(this.getPlayer(), type.getPermission(), EnumPermission.GADGETS.getPermission(), false)) {
                CookieGadgets.getPlayerManager(this.getPlayer()).unequipGadget();
                return;
            }
            if (CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() != null) {
                CookieGadgets.getPlayerManager(this.getPlayer()).removeGadget();
            }
            try {
                new BukkitRunnable() {
                    public void run() {
                        if (Bukkit.getPlayer(Gadget.this.getPlayerUUID()) != null && CookieGadgets.getPlayerManager(Gadget.this.player).getCurrentGadget() != null && CookieGadgets.getPlayerManager(Gadget.this.player).getCurrentGadget().getType() == Gadget.this.getType()) {
                            if (CookieGadgets.getCookieGadgetsData().isCooldownInActionBar() && Gadget.this.showCooldownBar) {
                                if (Gadget.hideCooldownBar.contains(Gadget.this.player)) {
                                    return;
                                }
                                if (CookieGadgets.getPlayerManager(Gadget.this.player).gadgetCooldown().containsKey(Gadget.this.getType())) {
                                    if (CookieGadgets.getPlayerManager(Gadget.this.player).gadgetCooldown().get(Gadget.this.getType()) - System.currentTimeMillis() > 0L && !CookieGadgets.getPlayerManager(Gadget.this.player).isBypassCooldown()) {
                                        CookieGadgets.getPlayerManager(Gadget.this.player).sendCooldownBar(type.getDisplayName(), CookieGadgets.getPlayerManager(Gadget.this.player).gadgetCooldown().get(Gadget.this.getType()), type.getCooldown());
                                    }
                                    else {
                                        CookieGadgets.getPlayerManager(Gadget.this.player).resetCooldownBar();
                                        CookieGadgets.getPlayerManager(Gadget.this.player).gadgetCooldown().remove(Gadget.this.getType());
                                    }
                                }
                            }
                            Gadget.this.onUpdate();
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
            this.listener = (Listener)new GadgetListener(this);
            CookieGadgets.getInstance().registerListener(this.listener);
            CookieGadgets.getInstance().registerListener((Listener)this);
            CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentGadget(this);
        }
    }
    
    protected UUID getPlayerUUID() {
        return this.uuid;
    }
    
    public GadgetType getType() {
        return this.type;
    }
    
    protected Player getPlayer() {
        return this.player;
    }
    
    protected boolean leftClickToActivate() {
        return false;
    }
    
    protected void onLeftClick() {
    }
    
    public abstract void onClick();
    
    public abstract void onUpdate();
    
    public abstract void onClear();
    
    protected boolean checkRequirements() {
        if (BlockUtil.isOutsideOfBorder(this.getPlayer().getLocation())) {
            this.getPlayer().sendMessage(MessageType.NOT_ALLOWED_COSMETICS_IN_THIS_LOCATION.getFormatMessage());
            return false;
        }
        return true;
    }
    
    protected boolean cancelEvent() {
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
        if (CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() != null) {
            CookieGadgets.getPlayerManager(this.getPlayer()).setCurrentGadget(null);
        }
        this.unregisterListeners();
        this.uuid = null;
    }
    
    protected void addCooldownTimer() {
        if (!CookieGadgets.getPlayerManager(this.getPlayer()).isBypassCooldown()) {
            CookieGadgets.getPlayerManager(this.getPlayer()).gadgetCooldown().put(this.getType(), System.currentTimeMillis() + this.getType().getCooldown() * 1000L);
        }
    }
    
    public class GadgetListener implements Listener
    {
        private Gadget gadget;
        
        public GadgetListener(final Gadget gadget) {
            this.gadget = gadget;
        }
        
        @EventHandler
        protected void onPlayerActivateGadget(final PlayerInteractEvent playerInteractEvent) {
            final Player player = playerInteractEvent.getPlayer();
            final UUID uniqueId = player.getUniqueId();
            if (playerInteractEvent.getAction() != Action.LEFT_CLICK_AIR && playerInteractEvent.getAction() != Action.LEFT_CLICK_BLOCK && playerInteractEvent.getAction() != Action.RIGHT_CLICK_AIR && playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK) {
                return;
            }
            if (!this.gadget.leftClickToActivate() && (playerInteractEvent.getAction() == Action.LEFT_CLICK_AIR || playerInteractEvent.getAction() == Action.LEFT_CLICK_BLOCK)) {
                return;
            }
            if (!player.getItemInHand().hasItemMeta() || !player.getItemInHand().getItemMeta().hasDisplayName()) {
                return;
            }
            if (!uniqueId.equals(this.gadget.uuid)) {
                return;
            }
            if (!player.getItemInHand().getItemMeta().getDisplayName().startsWith(this.gadget.getType().getDisplayName()) || player.getItemInHand().getType() != this.gadget.getType().getMaterial().getEnumMaterial().getType()) {
                return;
            }
            if (CookieGadgets.getPlayerManager(player).gadgetCooldown().containsKey(Gadget.this.getType()) && !CookieGadgets.getPlayerManager(player).isBypassCooldown()) {
                if (CookieGadgets.getPlayerManager(player).gadgetCooldown().get(Gadget.this.getType()) - System.currentTimeMillis() > 0L) {
                    player.sendMessage(MessageType.COOLDOWN.getFormatMessage().replace("{COOLDOWN}", String.valueOf((CookieGadgets.getPlayerManager(player).gadgetCooldown().get(Gadget.this.getType()) - System.currentTimeMillis()) / 1000L + 1L)));
                    player.updateInventory();
                    playerInteractEvent.setCancelled(true);
                    return;
                }
                CookieGadgets.getPlayerManager(player).gadgetCooldown().remove(Gadget.this.getType());
            }
            if (PermissionUtils.noPermission(player, Gadget.this.type.getPermission(), EnumPermission.GADGETS.getPermission(), true)) {
                CookieGadgets.getPlayerManager(player).removeGadget();
                playerInteractEvent.setCancelled(true);
                return;
            }
            if (this.gadget.checkRequirements()) {
                if (playerInteractEvent.getAction() == Action.LEFT_CLICK_AIR || playerInteractEvent.getAction() == Action.LEFT_CLICK_BLOCK) {
                    if (!this.gadget.leftClickToActivate()) {
                        return;
                    }
                    this.gadget.onLeftClick();
                }
                else {
                    this.gadget.onClick();
                }
                if (this.gadget.addCooldownAfterClick) {
                    this.gadget.addCooldownTimer();
                }
                player.updateInventory();
                if (this.gadget.cancelEvent()) {
                    playerInteractEvent.setCancelled(true);
                }
                return;
            }
            player.updateInventory();
            playerInteractEvent.setCancelled(true);
        }
    }
}
