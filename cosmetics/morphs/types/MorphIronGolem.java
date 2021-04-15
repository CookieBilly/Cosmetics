

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.block.BlockFace;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.Material;
import org.bukkit.block.Block;
import ws.billy.CookieGadgets.utils.BlockUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.Iterator;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;
import org.bukkit.entity.FallingBlock;
import java.util.ArrayList;

public class MorphIronGolem extends Morph
{
    private boolean activated;
    private ArrayList<FallingBlock> fallingBlocks;
    
    public MorphIronGolem(final UUID uuid) {
        super(uuid, MorphType.IRON_GOLEM);
        this.activated = false;
        this.fallingBlocks = new ArrayList<FallingBlock>();
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.MORPH_SKILL_IS_ACTIVATED.getFormatMessage().replace("{MORPH}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        return true;
    }
    
    @Override
    void onClick() {
        this.activated = true;
        SoundEffect.ENTITY_FIREWORK_ROCKET_LAUNCH.playSound(this.getPlayer().getLocation(), 2.0f, 1.0f);
        this.getPlayer().setVelocity(new Vector(0, 2, 0));
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!MorphIronGolem.this.getPlayer().isOnline() || MorphIronGolem.this.getPlayerManager().getCurrentMorph() == null || MorphIronGolem.this.getPlayerManager().getCurrentMorph().getType() != MorphIronGolem.this.getType()) {
                    MorphIronGolem.this.onClear();
                    this.step = 16;
                    this.cancel();
                    return;
                }
                if (this.step > 15) {
                    this.cancel();
                }
                ParticleEffect.CLOUD.display(MorphIronGolem.this.getPlayer().getLocation());
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 1L);
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                if (!MorphIronGolem.this.getPlayer().isOnline() || MorphIronGolem.this.getPlayerManager().getCurrentMorph() == null || MorphIronGolem.this.getPlayerManager().getCurrentMorph().getType() != MorphIronGolem.this.getType()) {
                    MorphIronGolem.this.onClear();
                    return;
                }
                MorphIronGolem.this.getPlayer().setVelocity(new Vector(0, -2, 0));
            }
        }, 25L);
        new BukkitRunnable() {
            public void run() {
                if (!MorphIronGolem.this.getPlayer().isOnline() || MorphIronGolem.this.getPlayerManager().getCurrentMorph() == null || MorphIronGolem.this.getPlayerManager().getCurrentMorph().getType() != MorphIronGolem.this.getType()) {
                    MorphIronGolem.this.onClear();
                    this.cancel();
                    return;
                }
                if (MorphIronGolem.this.getPlayer().isOnGround()) {
                    MorphIronGolem.this.spawnFallingBlock(MorphIronGolem.this.getPlayer());
                    Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            try {
                                MorphIronGolem.this.clearAll();
                            }
                            catch (Exception ex) {
                                MorphIronGolem.this.onClear();
                            }
                            SoundEffect.BLOCK_GRASS_STEP.playSound(MorphIronGolem.this.getPlayer().getLocation());
                            MorphIronGolem.this.fallingBlocks.remove(MorphIronGolem.this.getPlayer().getUniqueId());
                        }
                    }, 15L);
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 10L, 1L);
    }
    
    @Override
    void onUpdate() {
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
        this.activated = false;
    }
    
    private void spawnFallingBlock(final Player player) {
        final Location location = player.getLocation();
        SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(player.getLocation(), 2.0f, 1.0f);
        new BukkitRunnable() {
            int step = 1;
            
            public void run() {
                if (!player.isOnline() || MorphIronGolem.this.getPlayerManager().getCurrentMorph() == null || MorphIronGolem.this.getPlayerManager().getCurrentMorph().getType() != MorphIronGolem.this.getType() || !MorphIronGolem.this.activated) {
                    this.step = 5;
                    MorphIronGolem.this.clearAll();
                    this.cancel();
                }
                if (this.step >= 5) {
                    this.cancel();
                }
                for (final FallingBlock fallingBlock : MorphIronGolem.this.fallingBlocks) {
                    if (fallingBlock.getTicksLived() > 3) {
                        fallingBlock.remove();
                    }
                }
                for (final Block block : BlockUtil.getBlocksInRadius(location.clone().add(1.0, -1.0, 1.0), this.step, true)) {
                    if (block.getType() != Material.AIR && !block.getType().toString().toLowerCase().contains("banner") && block.getType() != EnumMaterial.BARRIER.getType() && !block.getType().toString().toLowerCase().contains("bed") && block.getType() != EnumMaterial.BREWING_STAND.getType() && block.getType() != EnumMaterial.BROWN_MUSHROOM.getType() && block.getType() != EnumMaterial.CACTUS.getType() && block.getType() != EnumMaterial.CARROT.getType() && block.getType() != EnumMaterial.CARROTS.getType() && !block.getType().toString().toLowerCase().contains("carpet") && block.getType() != EnumMaterial.CHEST.getType() && block.getType() != EnumMaterial.COCOA.getType() && !block.getType().toString().toLowerCase().contains("crops") && block.getType() != EnumMaterial.DANDELION.getType() && block.getType() != EnumMaterial.DEAD_BUSH.getType() && !block.getType().toString().toLowerCase().contains("double_plant") && block.getType() != EnumMaterial.DRAGON_EGG.getType() && block.getType() != EnumMaterial.END_PORTAL.getType() && block.getType() != EnumMaterial.END_PORTAL_FRAME.getType() && block.getType() != EnumMaterial.FARMLAND.getType() && block.getType() != EnumMaterial.FERN.getType() && block.getType() != EnumMaterial.FIRE.getType() && block.getType() != EnumMaterial.FLOWER_POT.getType() && !block.getType().toString().toLowerCase().contains("flower_pot") && block.getType() != EnumMaterial.GRASS.getType() && !block.getType().toString().toLowerCase().contains("head") && block.getType() != EnumMaterial.LADDER.getType() && block.getType() != EnumMaterial.LARGE_FERN.getType() && !block.getType().toString().toLowerCase().endsWith("lava") && block.getType() != EnumMaterial.LILY_PAD.getType() && block.getType() != EnumMaterial.LILAC.getType() && !block.getType().toString().toLowerCase().contains("long_grass") && block.getType() != EnumMaterial.MELON_STEM.getType() && block.getType() != EnumMaterial.MUSHROOM_STEM.getType() && block.getType() != EnumMaterial.NETHER_PORTAL.getType() && block.getType() != EnumMaterial.NETHER_WART.getType() && block.getType() != EnumMaterial.PEONY.getType() && !block.getType().toString().toLowerCase().contains("piston_") && block.getType() != EnumMaterial.POTATO.getType() && block.getType() != EnumMaterial.POTATOES.getType() && !block.getType().toString().toLowerCase().contains("potted_") && block.getType() != EnumMaterial.PUMPKIN_STEM.getType() && block.getType() != EnumMaterial.RED_MUSHROOM.getType() && !block.getType().toString().toLowerCase().contains("redstone_lamp") && !block.getType().toString().toLowerCase().contains("redstone_torch") && !block.getType().toString().toLowerCase().contains("red_rose") && block.getType() != EnumMaterial.REDSTONE_WIRE.getType() && block.getType() != EnumMaterial.ROSE_BUSH.getType() && !block.getType().toString().toLowerCase().contains("sapling") && !block.getType().toString().toLowerCase().contains("seeds") && !block.getType().toString().toLowerCase().contains("sign") && !block.getType().toString().toLowerCase().contains("skull") && block.getType() != EnumMaterial.SNOW.getType() && block.getType() != EnumMaterial.SPAWNER.getType() && block.getType() != EnumMaterial.SUGAR_CANE.getType() && !block.getType().toString().toLowerCase().contains("sugar_cane_block") && block.getType() != EnumMaterial.SUNFLOWER.getType() && block.getType() != EnumMaterial.TALL_GRASS.getType() && block.getType() != EnumMaterial.TORCH.getType() && block.getType() != EnumMaterial.TRIPWIRE.getType() && !block.getType().toString().toLowerCase().contains("tulip") && block.getType() != EnumMaterial.VINE.getType() && !block.getType().toString().toLowerCase().endsWith("water") && block.getType() != EnumMaterial.WHEAT.getType() && !block.getType().toString().toLowerCase().contains("_button") && !block.getType().toString().toLowerCase().contains("_plate") && !block.hasMetadata(CookieGadgets.getInstance().getPluginName()) && block.getType().isSolid() && block.getRelative(BlockFace.UP).getType() == Material.AIR) {
                        final FallingBlock spawnFallingBlock = location.getWorld().spawnFallingBlock(block.getLocation().clone().add(0.0, 1.100000023841858, 0.0), block.getType(), block.getData());
                        spawnFallingBlock.setVelocity(new Vector(0.0f, 0.3f, 0.0f));
                        spawnFallingBlock.setDropItem(false);
                        spawnFallingBlock.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                        MorphIronGolem.this.fallingBlocks.add(spawnFallingBlock);
                        for (final Entity entity : spawnFallingBlock.getNearbyEntities(1.5, 1.0, 1.5)) {
                            if (entity != player && entity instanceof Player) {
                                MathUtil.applyVelocity(entity, new Vector(entity.getVelocity().getX(), 0.7, entity.getVelocity().getZ()).add(MathUtil.getRandomCircleVector().multiply(0.15)));
                            }
                        }
                    }
                }
                ++this.step;
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 3L);
    }
    
    @EventHandler(priority = EventPriority.LOW)
    public void onBlockChange(final EntityChangeBlockEvent entityChangeBlockEvent) {
        if (entityChangeBlockEvent.getEntityType() == EntityType.FALLING_BLOCK && ((FallingBlock)entityChangeBlockEvent.getEntity()).hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            entityChangeBlockEvent.setCancelled(true);
        }
    }
}
