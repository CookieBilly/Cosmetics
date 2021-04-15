

package ws.billy.CookieGadgets.cosmetics.suits.types;

import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Entity;
import org.bukkit.Location;
import java.util.ArrayList;

public class SuitNecromancer extends Suit
{
    private boolean activated;
    private boolean clicked;
    private ArrayList<Location> locations;
    private ArrayList<Entity> entitys;
    private static EntityType[] entityTypes;
    
    static {
        SuitNecromancer.entityTypes = new EntityType[] { EntityType.SKELETON, EntityType.ZOMBIE };
    }
    
    public SuitNecromancer(final UUID uuid) {
        super(uuid, SuitType.NECROMANCER);
        this.activated = false;
        this.clicked = false;
        this.locations = new ArrayList<Location>();
        this.entitys = new ArrayList<Entity>();
    }
    
    public void onUpdate() {
        if (this.clicked && !this.isBeingCooldown()) {
            this.activated = true;
            final Iterator<Location> iterator = BlockUtil.getLocationsInRadius(this.getPlayer().getLocation(), 4, false).iterator();
            while (iterator.hasNext()) {
                this.locations.add(iterator.next());
            }
            for (int i = 1; i <= 8; ++i) {
                final Location location = this.locations.get(CookieGadgets.random().nextInt(this.locations.size()));
                final Location clone = location.clone();
                clone.setY((double)(this.getPlayer().getWorld().getHighestBlockYAt(location) + 1));
                CookieGadgets.setBypassCreatureSpawn(true);
                final Entity spawnEntity = this.getPlayer().getWorld().spawnEntity(clone, SuitNecromancer.entityTypes[CookieGadgets.random().nextInt(SuitNecromancer.entityTypes.length)]);
                spawnEntity.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                CookieGadgets.getNMSManager().clearPathfinders(spawnEntity);
                CookieGadgets.getNMSManager().makeEntityPanic(spawnEntity);
                this.entitys.add(spawnEntity);
                CookieGadgets.setBypassCreatureSpawn(false);
            }
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    SuitNecromancer.this.clearAll();
                }
            }, 160L);
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
        for (final Entity entity : this.entitys) {
            if (entity != null) {
                entity.remove();
            }
        }
        this.entitys.clear();
        this.locations.clear();
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
