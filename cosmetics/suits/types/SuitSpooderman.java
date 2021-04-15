

package ws.billy.CookieGadgets.cosmetics.suits.types;

import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.block.Block;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.BlockUtil;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import java.util.ArrayList;

public class SuitSpooderman extends Suit
{
    private boolean activated;
    private boolean clicked;
    private ArrayList<Item> items;
    private Location location;
    int step;
    
    public SuitSpooderman(final UUID uuid) {
        super(uuid, SuitType.SPOODERMAN);
        this.activated = false;
        this.clicked = false;
        this.items = new ArrayList<Item>();
        this.step = 0;
    }
    
    public void onUpdate() {
        if (this.clicked && !this.isBeingCooldown()) {
            this.activated = true;
            ++this.step;
            if (this.location == null) {
                return;
            }
            if (this.step <= 6) {
                if (this.step == 1) {
                    SoundEffect.ENTITY_SPIDER_AMBIENT.playSound(this.location);
                }
                final Item dropItem = this.getPlayer().getWorld().dropItem(this.location, ItemUtils.item(UUID.randomUUID().toString(), EnumMaterial.COBWEB, 0));
                dropItem.setVelocity(new Vector(dropItem.getVelocity().getX(), 0.7, dropItem.getVelocity().getZ()));
                dropItem.setVelocity(this.location.getDirection().multiply(1.5));
                dropItem.setPickupDelay(Integer.MAX_VALUE);
                dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                SoundEffect.ENTITY_ARROW_SHOOT.playSound(this.getPlayer());
                this.items.add(dropItem);
            }
            else {
                this.clicked = false;
                this.addCooldownTimer();
                Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)CookieGadgets.getInstance(), () -> {
                    if (this.clicked) {
                        return;
                    }
                    else {
                        this.clearAll();
                        return;
                    }
                }, 160L);
            }
        }
        if (this.activated && this.step > 6) {
            for (final Item item : this.items) {
                if (!this.getPlayer().isOnline() || this.getPlayerManager().getCurrentSuit() == null) {
                    break;
                }
                if (this.getPlayerManager().getCurrentSuit().getType() != this.getType()) {
                    break;
                }
                if (item == null) {
                    this.clearAll();
                    break;
                }
                final Block block = item.getLocation().clone().add(1.0, 0.0, 0.0).getBlock();
                final Block block2 = item.getLocation().clone().add(0.0, 0.0, 1.0).getBlock();
                final Block block3 = item.getLocation().clone().add(-1.0, 0.0, 0.0).getBlock();
                final Block block4 = item.getLocation().clone().add(0.0, 0.0, -1.0).getBlock();
                final Block block5 = item.getLocation().clone().add(0.0, 1.0, 0.0).getBlock();
                final Block block6 = item.getLocation().clone().add(0.0, -1.0, 0.0).getBlock();
                if (block.getType().isSolid() || block2.getType().isSolid() || block3.getType().isSolid() || block4.getType().isSolid() || block5.getType().isSolid() || block6.getType().isSolid()) {
                    if (BlockUtil.isOutsideOfBorder(block.getLocation()) || BlockUtil.isOutsideOfBorder(block2.getLocation()) || BlockUtil.isOutsideOfBorder(block3.getLocation()) || BlockUtil.isOutsideOfBorder(block4.getLocation()) || BlockUtil.isOutsideOfBorder(block5.getLocation()) || BlockUtil.isOutsideOfBorder(block6.getLocation())) {
                        this.getPlayer().sendMessage(MessageType.NOT_ALLOWED_TELEPORT_TO_THAT_LOCATION.getFormatMessage());
                        this.clearAll();
                        break;
                    }
                    if (WorldGuardUtils.isInBlacklistedRegion(block.getLocation(), BlacklistedRegion.SUITS) || WorldGuardUtils.isInBlacklistedRegion(block2.getLocation(), BlacklistedRegion.SUITS) || WorldGuardUtils.isInBlacklistedRegion(block3.getLocation(), BlacklistedRegion.SUITS) || WorldGuardUtils.isInBlacklistedRegion(block4.getLocation(), BlacklistedRegion.SUITS) || WorldGuardUtils.isInBlacklistedRegion(block5.getLocation(), BlacklistedRegion.SUITS) || WorldGuardUtils.isInBlacklistedRegion(block6.getLocation(), BlacklistedRegion.SUITS)) {
                        this.getPlayer().sendMessage(MessageType.NOT_ALLOWED_TELEPORT_TO_THAT_LOCATION.getFormatMessage());
                        this.clearAll();
                        break;
                    }
                    this.pullToLocation(this.getPlayer(), item.getLocation());
                    this.clearAll();
                    break;
                }
                else {
                    if (item.getTicksLived() > 160) {
                        this.pullToLocation(this.getPlayer(), item.getLocation());
                        this.clearAll();
                        break;
                    }
                    continue;
                }
            }
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        for (final Item item : this.items) {
            if (item != null) {
                item.remove();
            }
        }
        this.items.clear();
        this.clicked = false;
        this.activated = false;
        this.step = 0;
        this.location = null;
    }
    
    private void pullToLocation(final Player player, final Location location) {
        Validate.notNull((Object)player);
        Validate.notNull((Object)location);
        if (player.getWorld() != location.getWorld()) {
            return;
        }
        final Location location2 = player.getLocation();
        location2.setY(location2.getY() + 0.5);
        player.teleport(location2);
        final double n = -0.09;
        final double distance = location.distance(location2);
        final double x = (1.2 + 0.07 * distance) * (location.getX() - location2.getX()) / distance;
        final double y = (1.2 + 0.03 * distance) * (location.getY() - location2.getY()) / distance - 0.5 * n * distance;
        final double z = (1.2 + 0.07 * distance) * (location.getZ() - location2.getZ()) / distance;
        final Vector velocity = player.getVelocity();
        velocity.setX(x);
        velocity.setY(y);
        velocity.setZ(z);
        MathUtil.applyVelocity((Entity)player, velocity);
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
            this.location = this.getPlayer().getEyeLocation();
            playerInteractEvent.setCancelled(this.clicked = true);
        }
    }
}
