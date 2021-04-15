

package ws.billy.CookieGadgets.cosmetics.suits.types;

import org.bukkit.event.EventPriority;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.EventHandler;
import ws.billy.CookieGadgets.utils.MessageType;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;
import org.bukkit.block.BlockFace;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.Material;
import org.bukkit.block.Block;
import ws.billy.CookieGadgets.utils.BlockUtil;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;
import java.util.Iterator;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;
import org.bukkit.entity.FallingBlock;
import java.util.ArrayList;

public class SuitWarrior extends Suit
{
    private boolean activated;
    private boolean clicked;
    private ArrayList<FallingBlock> fallingBlocks;
    
    public SuitWarrior(final UUID uuid) {
        super(uuid, SuitType.WARRIOR);
        this.activated = false;
        this.clicked = false;
        this.fallingBlocks = new ArrayList<FallingBlock>();
    }
    
    public void onUpdate() {
        if (this.clicked && !this.isBeingCooldown()) {
            this.activated = true;
            this.spawnFallingBlock(this.getPlayer());
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        SuitWarrior.this.clearAll();
                    }
                    catch (Exception ex) {
                        SuitWarrior.this.onClear();
                        ex.printStackTrace();
                    }
                    SoundEffect.BLOCK_GRASS_STEP.playSound(SuitWarrior.this.getPlayer().getLocation());
                    SuitWarrior.this.fallingBlocks.remove(SuitWarrior.this.getPlayer().getUniqueId());
                }
            }, 22L);
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
        for (final FallingBlock fallingBlock : this.fallingBlocks) {
            if (fallingBlock != null) {
                fallingBlock.remove();
            }
        }
        this.fallingBlocks.clear();
        this.clicked = false;
        this.activated = false;
    }
    
    private void spawnFallingBlock(final Player player) {
        final Location location = player.getLocation();
        SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(player.getLocation(), 2.0f, 1.0f);
        new BukkitRunnable() {
            int step = 1;
            
            public void run() {
                if (!player.isOnline() || CookieGadgets.getPlayerManager(SuitWarrior.this.getPlayer()).getCurrentSuit() == null || CookieGadgets.getPlayerManager(SuitWarrior.this.getPlayer()).getCurrentSuit().getType() != SuitWarrior.this.getType() || !SuitWarrior.this.activated) {
                    this.step = 7;
                    SuitWarrior.this.clearAll();
                    this.cancel();
                }
                if (this.step >= 7) {
                    this.cancel();
                }
                for (final FallingBlock fallingBlock : SuitWarrior.this.fallingBlocks) {
                    if (fallingBlock.getTicksLived() > 2) {
                        fallingBlock.remove();
                    }
                }
                for (final Block block : BlockUtil.getBlocksInRadius(location.clone().add(1.5, -1.5, 1.5), this.step, true)) {
                    if (block.getType() != Material.AIR && !block.getType().toString().toLowerCase().contains("banner") && block.getType() != EnumMaterial.BARRIER.getType() && !block.getType().toString().toLowerCase().contains("bed") && block.getType() != EnumMaterial.BREWING_STAND.getType() && block.getType() != EnumMaterial.BROWN_MUSHROOM.getType() && block.getType() != EnumMaterial.CACTUS.getType() && block.getType() != EnumMaterial.CAKE.getType() && !block.getType().toString().toLowerCase().contains("cake_block") && block.getType() != EnumMaterial.CARROT.getType() && block.getType() != EnumMaterial.CARROTS.getType() && !block.getType().toString().toLowerCase().contains("carpet") && block.getType() != EnumMaterial.CHEST.getType() && block.getType() != EnumMaterial.COCOA.getType() && !block.getType().toString().toLowerCase().contains("crops") && block.getType() != EnumMaterial.DANDELION.getType() && block.getType() != EnumMaterial.DEAD_BUSH.getType() && !block.getType().toString().toLowerCase().contains("double_plant") && block.getType() != EnumMaterial.DRAGON_EGG.getType() && block.getType() != EnumMaterial.END_PORTAL.getType() && block.getType() != EnumMaterial.END_PORTAL_FRAME.getType() && block.getType() != EnumMaterial.FARMLAND.getType() && block.getType() != EnumMaterial.FERN.getType() && block.getType() != EnumMaterial.FIRE.getType() && block.getType() != EnumMaterial.FLOWER_POT.getType() && !block.getType().toString().toLowerCase().contains("flower_pot") && block.getType() != EnumMaterial.GRASS.getType() && !block.getType().toString().toLowerCase().contains("head") && block.getType() != EnumMaterial.LADDER.getType() && block.getType() != EnumMaterial.LARGE_FERN.getType() && !block.getType().toString().toLowerCase().endsWith("lava") && block.getType() != EnumMaterial.LILY_PAD.getType() && block.getType() != EnumMaterial.LILAC.getType() && !block.getType().toString().toLowerCase().contains("long_grass") && block.getType() != EnumMaterial.MELON_STEM.getType() && block.getType() != EnumMaterial.MUSHROOM_STEM.getType() && block.getType() != EnumMaterial.NETHER_PORTAL.getType() && block.getType() != EnumMaterial.NETHER_WART.getType() && block.getType() != EnumMaterial.PEONY.getType() && !block.getType().toString().toLowerCase().contains("piston_") && block.getType() != EnumMaterial.POTATO.getType() && block.getType() != EnumMaterial.POTATOES.getType() && !block.getType().toString().toLowerCase().contains("potted_") && block.getType() != EnumMaterial.PUMPKIN_STEM.getType() && block.getType() != EnumMaterial.RED_MUSHROOM.getType() && !block.getType().toString().toLowerCase().contains("redstone_lamp") && !block.getType().toString().toLowerCase().contains("redstone_torch") && !block.getType().toString().toLowerCase().contains("red_rose") && block.getType() != EnumMaterial.REDSTONE_WIRE.getType() && block.getType() != EnumMaterial.ROSE_BUSH.getType() && !block.getType().toString().toLowerCase().contains("sapling") && !block.getType().toString().toLowerCase().contains("seeds") && !block.getType().toString().toLowerCase().contains("sign") && !block.getType().toString().toLowerCase().contains("skull") && block.getType() != EnumMaterial.SNOW.getType() && block.getType() != EnumMaterial.SPAWNER.getType() && block.getType() != EnumMaterial.SUGAR_CANE.getType() && !block.getType().toString().toLowerCase().contains("sugar_cane_block") && block.getType() != EnumMaterial.SUNFLOWER.getType() && block.getType() != EnumMaterial.TALL_GRASS.getType() && block.getType() != EnumMaterial.TORCH.getType() && block.getType() != EnumMaterial.TRIPWIRE.getType() && block.getType() != EnumMaterial.TRIPWIRE_HOOK.getType() && !block.getType().toString().toLowerCase().contains("tulip") && block.getType() != EnumMaterial.VINE.getType() && !block.getType().toString().toLowerCase().endsWith("water") && block.getType() != EnumMaterial.WHEAT.getType() && !block.getType().toString().toLowerCase().contains("_button") && !block.getType().toString().toLowerCase().contains("_plate") && !block.hasMetadata(CookieGadgets.getInstance().getPluginName()) && block.getType().isSolid() && block.getRelative(BlockFace.UP).getType() == Material.AIR) {
                        final FallingBlock spawnFallingBlock = location.getWorld().spawnFallingBlock(block.getLocation().clone().add(0.0, 1.100000023841858, 0.0), block.getType(), block.getData());
                        spawnFallingBlock.setVelocity(new Vector(0.0f, 0.3f, 0.0f));
                        spawnFallingBlock.setDropItem(false);
                        spawnFallingBlock.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                        SuitWarrior.this.fallingBlocks.add(spawnFallingBlock);
                    }
                }
                ++this.step;
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 3L);
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
    
    @EventHandler(priority = EventPriority.LOW)
    public void onBlockChange(final EntityChangeBlockEvent entityChangeBlockEvent) {
        if (entityChangeBlockEvent.getEntityType() == EntityType.FALLING_BLOCK && ((FallingBlock)entityChangeBlockEvent.getEntity()).hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            entityChangeBlockEvent.setCancelled(true);
        }
    }
}
