

package ws.billy.CookieGadgets.cosmetics.suits.types;

import org.bukkit.event.EventPriority;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.CuboID;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;
import org.bukkit.entity.FallingBlock;
import org.bukkit.Location;

public class SuitThor extends Suit
{
    private boolean activated;
    private boolean clicked;
    private Location target;
    private FallingBlock fallingBlock;
    
    public SuitThor(final UUID uuid) {
        super(uuid, SuitType.THOR);
        this.activated = false;
        this.clicked = false;
        this.target = this.getPlayer().getLocation();
        this.fallingBlock = null;
    }
    
    public void onUpdate() {
        if (this.clicked && !this.isBeingCooldown()) {
            final Location location = BlockUtil.getTargetBlock(this.getPlayer(), 9).getLocation();
            if (location.getBlock().isEmpty()) {
                this.getPlayer().sendMessage(MessageType.TARGET_A_BLOCK.getFormatMessage());
                this.clicked = false;
                return;
            }
            if (!new CuboID(location.clone().add(0.0, 1.0, 0.0), location.clone().add(0.0, 16.0, 0.0)).isEmpty() || this.getPlayer().getLocation().add(0.0, 16.0, 0.0).getBlockY() >= 256) {
                this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
                this.clicked = false;
                return;
            }
            location.add(0.5, 0.2, 0.5);
            this.target = location;
            this.activated = true;
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    SuitThor.this.spawnAnvil(SuitThor.this.getPlayer(), SuitThor.this.target);
                }
            }, 20L);
            this.clicked = false;
            this.addCooldownTimer();
        }
        if (this.fallingBlock != null) {
            ParticleEffect.FLAME.display(this.fallingBlock.getLocation(), 0.2f, 0.2f, 0.2f, 2.0f, 2);
            ParticleEffect.CLOUD.display(this.fallingBlock.getLocation(), 0.5f, 0.3f, 0.5f, 3);
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        if (this.fallingBlock != null) {
            this.fallingBlock.getWorld().strikeLightningEffect(this.fallingBlock.getLocation());
            ParticleEffect.PORTAL.display(this.fallingBlock.getLocation(), 0.7f, 0.7f, 0.7f, 25);
            SoundEffect.BLOCK_ANVIL_LAND.playSound(this.fallingBlock.getLocation());
            this.fallingBlock.remove();
        }
        this.fallingBlock = null;
        this.clicked = false;
        this.activated = false;
    }
    
    private void spawnAnvil(final Player player, final Location location) {
        final FallingBlock spawnFallingBlock = location.getWorld().spawnFallingBlock(location.clone().add(0.0, 15.0, 0.0), EnumMaterial.ANVIL.getType(), (byte)0);
        spawnFallingBlock.setDropItem(false);
        spawnFallingBlock.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.fallingBlock = spawnFallingBlock;
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                SuitThor.this.clearAll();
            }
        }, 28L);
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
    
    @EventHandler(priority = EventPriority.LOW)
    public void onBlockChange(final EntityChangeBlockEvent entityChangeBlockEvent) {
        if (entityChangeBlockEvent.getEntityType() == EntityType.FALLING_BLOCK && ((FallingBlock)entityChangeBlockEvent.getEntity()).hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            entityChangeBlockEvent.setCancelled(true);
        }
    }
}
