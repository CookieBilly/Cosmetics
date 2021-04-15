

package ws.billy.CookieGadgets.cosmetics.suits.types;

import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.PlayerUtils;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;
import org.bukkit.entity.Player;

public class SuitDeathAngel extends Suit
{
    private boolean activated;
    private boolean clicked;
    private Player targetPlayer;
    
    public SuitDeathAngel(final UUID uuid) {
        super(uuid, SuitType.BAKER);
        this.activated = false;
        this.clicked = false;
        this.targetPlayer = this.getPlayer();
    }
    
    public void onUpdate() {
        if (this.clicked && !this.isBeingCooldown()) {
            final Entity entityPlayerLooking = PlayerUtils.getEntityPlayerLookingAt(this.getPlayer(), 10);
            if (entityPlayerLooking == null) {
                this.getPlayer().sendMessage(MessageType.TARGET_A_PLAYER.getFormatMessage());
                this.clicked = false;
                return;
            }
            if (!(entityPlayerLooking instanceof Player)) {
                this.getPlayer().sendMessage(MessageType.TARGET_A_PLAYER.getFormatMessage());
                this.clicked = false;
                return;
            }
            this.targetPlayer = (Player)entityPlayerLooking;
            this.activated = true;
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    SuitDeathAngel.this.targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
                    SuitDeathAngel.this.targetPlayer.setPlayerTime(15000L, false);
                    SuitDeathAngel.this.getPlayer().setPlayerTime(15000L, false);
                    SuitDeathAngel.this.getPlayer().teleport(new Location(SuitDeathAngel.this.getPlayer().getWorld(), SuitDeathAngel.this.getPlayer().getLocation().getX() - SuitDeathAngel.this.getPlayer().getLocation().distance(SuitDeathAngel.this.targetPlayer.getLocation()), SuitDeathAngel.this.getPlayer().getLocation().getY(), SuitDeathAngel.this.getPlayer().getLocation().getZ() - SuitDeathAngel.this.getPlayer().getLocation().distance(SuitDeathAngel.this.targetPlayer.getLocation())));
                    Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            SuitDeathAngel.this.targetPlayer.resetPlayerTime();
                            SuitDeathAngel.this.getPlayer().resetPlayerTime();
                            SuitDeathAngel.this.clearAll();
                        }
                    }, 60L);
                }
            }, 10L);
            this.clicked = false;
            this.addCooldownTimer();
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        this.clicked = false;
        this.activated = false;
    }
    
    @EventHandler
    public void onPlayerClick(final PlayerInteractEvent playerInteractEvent) {
        if ((playerInteractEvent.getAction() == Action.LEFT_CLICK_AIR || playerInteractEvent.getAction() == Action.LEFT_CLICK_BLOCK) && playerInteractEvent.getPlayer() == this.getPlayer() && !this.clicked) {
            if (playerInteractEvent.getPlayer().getItemInHand().getType() != Material.AIR) {
                return;
            }
            if (this.isBeingCooldown()) {
                playerInteractEvent.setCancelled(true);
                return;
            }
            if (this.activated) {
                this.getPlayer().sendMessage(MessageType.SUIT_ABILITY_IS_ACTIVATED.getFormatMessage().replace("{SUIT}", this.getType().getDisplayNameStripColor()));
                playerInteractEvent.setCancelled(true);
                return;
            }
            playerInteractEvent.setCancelled(this.clicked = true);
        }
    }
}
