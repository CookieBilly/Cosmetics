

package ws.billy.CookieGadgets.cosmetics.suits.types;

import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.util.Vector;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.items.ItemUtils;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.entity.Item;
import java.util.ArrayList;

public class SuitBaker extends Suit
{
    private boolean clicked;
    private ArrayList<Item> items;
    private static EnumMaterial[] materials;
    
    static {
        SuitBaker.materials = new EnumMaterial[] { EnumMaterial.BREAD, EnumMaterial.CAKE, EnumMaterial.COOKIE };
    }
    
    public SuitBaker(final UUID uuid) {
        super(uuid, SuitType.BAKER);
        this.clicked = false;
        this.items = new ArrayList<Item>();
    }
    
    public void onUpdate() {
        if (this.clicked && !this.isBeingCooldown()) {
            new BukkitRunnable() {
                public void run() {
                    final Item dropItem = SuitBaker.this.getPlayer().getWorld().dropItem(SuitBaker.this.getPlayer().getEyeLocation(), ItemUtils.item(UUID.randomUUID().toString(), SuitBaker.materials[CookieGadgets.random().nextInt(SuitBaker.materials.length)], 0));
                    dropItem.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    dropItem.setPickupDelay(Integer.MAX_VALUE);
                    dropItem.setVelocity(new Vector(dropItem.getVelocity().getX(), 0.6, dropItem.getVelocity().getZ()));
                    dropItem.setVelocity(SuitBaker.this.getPlayer().getLocation().getDirection().multiply(1.5));
                    SoundEffect.ENTITY_ARROW_SHOOT.playSound(SuitBaker.this.getPlayer().getLocation());
                    SuitBaker.this.items.add(dropItem);
                }
            }.runTask((Plugin)CookieGadgets.getInstance());
            this.clicked = false;
            this.addCooldownTimer();
        }
        for (final Item item : this.items) {
            if (item != null && (item.isOnGround() || item.getTicksLived() > 160)) {
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
