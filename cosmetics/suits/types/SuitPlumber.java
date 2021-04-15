

package ws.billy.CookieGadgets.cosmetics.suits.types;

import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.util.Vector;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;
import org.bukkit.entity.Item;
import org.bukkit.entity.Entity;
import java.util.ArrayList;

public class SuitPlumber extends Suit
{
    public static ArrayList<Entity> disableFireDamage;
    private boolean clicked;
    private ArrayList<Item> items;
    private ArrayList<Entity> entitys;
    
    static {
        SuitPlumber.disableFireDamage = new ArrayList<Entity>();
    }
    
    public SuitPlumber(final UUID uuid) {
        super(uuid, SuitType.PLUMBER);
        this.clicked = false;
        this.items = new ArrayList<Item>();
        this.entitys = new ArrayList<Entity>();
    }
    
    public void onUpdate() {
        if (this.clicked && !this.isBeingCooldown()) {
            new BukkitRunnable() {
                public void run() {
                    final Item dropItem = SuitPlumber.this.getPlayer().getWorld().dropItem(SuitPlumber.this.getPlayer().getEyeLocation(), ItemUtils.item(UUID.randomUUID().toString(), EnumMaterial.BLAZE_POWDER, 0));
                    dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    dropItem.setPickupDelay(Integer.MAX_VALUE);
                    dropItem.setVelocity(new Vector(dropItem.getVelocity().getX(), 0.6, dropItem.getVelocity().getZ()));
                    dropItem.setVelocity(SuitPlumber.this.getPlayer().getLocation().getDirection().multiply(1.5));
                    SuitPlumber.this.items.add(dropItem);
                    SoundEffect.ENTITY_BLAZE_SHOOT.playSound(SuitPlumber.this.getPlayer().getLocation());
                }
            }.runTask((Plugin)CookieGadgets.getInstance());
            this.clicked = false;
            this.addCooldownTimer();
        }
        for (final Item item : this.items) {
            if (item != null && item.isOnGround()) {
                if (!item.isDead()) {
                    for (final Entity entity : item.getNearbyEntities(0.6, 0.4, 0.6)) {
                        if (!SuitPlumber.disableFireDamage.contains(entity)) {
                            entity.setFireTicks(20);
                            if (!SuitPlumber.disableFireDamage.contains(entity)) {
                                SuitPlumber.disableFireDamage.add(entity);
                            }
                            this.entitys.add(entity);
                            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    if (!SuitPlumber.this.entitys.contains(entity) || SuitPlumber.this.entitys == null) {
                                        return;
                                    }
                                    if (SuitPlumber.disableFireDamage.contains(entity)) {
                                        SuitPlumber.disableFireDamage.remove(entity);
                                    }
                                    SuitPlumber.this.entitys.remove(entity);
                                }
                            }, 21L);
                        }
                    }
                }
                item.remove();
            }
        }
    }
    
    @Override
    public void onClear() {
        this.clicked = false;
        final Iterator<Item> iterator = this.items.iterator();
        while (iterator.hasNext()) {
            iterator.next().remove();
        }
        this.items.clear();
        for (final Entity entity : this.entitys) {
            entity.setFireTicks(0);
            if (SuitPlumber.disableFireDamage.contains(entity)) {
                SuitPlumber.disableFireDamage.remove(entity);
            }
        }
        this.entitys.clear();
        HandlerList.unregisterAll((Listener)this);
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
            playerInteractEvent.setCancelled(this.clicked = true);
        }
    }
}
