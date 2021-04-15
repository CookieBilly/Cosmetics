

package ws.billy.CookieGadgets.cosmetics.suits.types;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;

public class SuitFirefighter extends Suit
{
    private boolean activated;
    private boolean clicked;
    
    public SuitFirefighter(final UUID uuid) {
        super(uuid, SuitType.FIREFIGHTER);
        this.activated = false;
        this.clicked = false;
    }
    
    public void onUpdate() {
        if (this.clicked && !this.isBeingCooldown()) {
            final Location add = this.getPlayer().getEyeLocation().add(0.5, 0.0, 0.5);
            this.drawArc(add, add.clone().add(add.getDirection().multiply(15).add(new Vector(0, -4, 0))), 2.0f, 150);
            this.activated = true;
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
    
    private void drawArc(final Location location, final Location location2, final float n, final int n2) {
        if (location == null || location2 == null) {
            throw new NullPointerException();
        }
        int i = 0;
        final Vector vector;
        float n3;
        float n4;
        float n5;
        final Vector vector2;
        final double n6;
        Bukkit.getServer().getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
            location.getY();
            while (i <= 400) {
                location2.toVector().subtract(location.toVector());
                n3 = (float)vector.length();
                n4 = (float)(4.0f * n / Math.pow(n3, 2.0));
                vector.clone().normalize().multiply(n3 * i / n2);
                n5 = (float)(-n4 * Math.pow(i / (float)n2 * n3 - n3 / 2.0f, 2.0) + n);
                location.add(vector2).add(0.0, (double)n5, 0.0);
                if (Math.abs(location.getY() - n6) > 15.0) {
                    this.clearAll();
                    break;
                }
                else if (location.getWorld().getBlockAt(location).getType().isSolid()) {
                    this.clearAll();
                    break;
                }
                else {
                    ParticleEffect.DRIP_WATER.display(location, 0.07f, 0.07f, 0.07f, 0.0f, 3);
                    location.subtract(0.0, (double)n5, 0.0).subtract(vector2);
                    try {
                        Thread.sleep(3L);
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if (i == 400) {
                        this.clearAll();
                    }
                    ++i;
                }
            }
        });
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
                playerInteractEvent.getPlayer().sendMessage(MessageType.SUIT_ABILITY_IS_ACTIVATED.getFormatMessage().replace("{SUIT}", this.getType().getDisplayNameStripColor()));
                playerInteractEvent.setCancelled(true);
                return;
            }
            playerInteractEvent.setCancelled(this.clicked = true);
        }
    }
}
