

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import ws.billy.CookieGadgets.utils.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.utils.BlockUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.Material;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.block.Block;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.Location;
import java.util.HashMap;

public class GadgetFireTrail extends Gadget
{
    private boolean activated;
    private HashMap<Location, String> blocks;
    
    public GadgetFireTrail(final UUID uuid) {
        super(uuid, GadgetType.FIRE_TRAIL);
        this.activated = false;
        this.blocks = new HashMap<Location, String>();
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 160, 1));
        this.activated = true;
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!GadgetFireTrail.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetFireTrail.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetFireTrail.this.getPlayer()).getCurrentGadget().getType() != GadgetFireTrail.this.getType()) {
                    this.step = 161;
                    GadgetFireTrail.this.onClear();
                    this.cancel();
                    return;
                }
                if (this.step <= 160) {
                    final Block block = GadgetFireTrail.this.getPlayer().getLocation().getBlock();
                    final Block block2 = GadgetFireTrail.this.getPlayer().getLocation().add(0.0, -1.0, 0.0).getBlock();
                    if (block.isEmpty() && !block2.isEmpty() && !block2.isLiquid() && GadgetFireTrail.this.getPlayer().isOnGround()) {
                        GadgetFireTrail.this.setToRestore(GadgetFireTrail.this.getPlayer(), block, EnumMaterial.FIRE, 60);
                    }
                }
                else {
                    GadgetFireTrail.this.clearAll();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 1L);
    }
    
    @Override
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        this.forceRestore();
        this.getPlayer().setFireTicks(0);
        this.activated = false;
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockSpread(final BlockSpreadEvent blockSpreadEvent) {
        for (final Location location : this.blocks.keySet()) {
            if (location.getWorld() != blockSpreadEvent.getBlock().getWorld()) {
                return;
            }
            if (blockSpreadEvent.getBlock().getLocation().distance(location) > 5.0) {
                return;
            }
            blockSpreadEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockFade(final BlockFadeEvent blockFadeEvent) {
        for (final Location location : this.blocks.keySet()) {
            if (location.getWorld() != blockFadeEvent.getBlock().getWorld()) {
                return;
            }
            if (blockFadeEvent.getBlock().getLocation().distance(location) > 10.0) {
                return;
            }
            if (blockFadeEvent.getBlock().getType() != Material.ICE && blockFadeEvent.getBlock().getType() != Material.SNOW && blockFadeEvent.getBlock().getType() != Material.SNOW_BLOCK) {
                continue;
            }
            blockFadeEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBurn(final BlockBurnEvent blockBurnEvent) {
        for (final Location location : this.blocks.keySet()) {
            if (location.getWorld() != blockBurnEvent.getBlock().getWorld()) {
                return;
            }
            if (blockBurnEvent.getBlock().getLocation().distance(location) > 10.0) {
                return;
            }
            blockBurnEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(final EntityDamageEvent entityDamageEvent) {
        if (entityDamageEvent.getCause() == EntityDamageEvent.DamageCause.FIRE || entityDamageEvent.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
            for (final Location location : this.blocks.keySet()) {
                if (location.getWorld() != entityDamageEvent.getEntity().getWorld()) {
                    return;
                }
                if (entityDamageEvent.getEntity().getLocation().distance(location) >= 3.0) {
                    continue;
                }
                entityDamageEvent.getEntity().setFireTicks(0);
                entityDamageEvent.setCancelled(true);
            }
        }
    }
    
    private void setToRestore(final Player player, final Block block, final EnumMaterial enumMaterial, final int n) {
        if (this.blocks.containsKey(block.getLocation())) {
            return;
        }
        if (((block.getType() != EnumMaterial.ATTACHED_MELON_STEM.getType() && block.getType() != EnumMaterial.ATTACHED_PUMPKIN_STEM.getType()) || (!block.getType().toString().toLowerCase().contains("banner") && block.getType() != EnumMaterial.BARRIER.getType() && !block.getType().toString().toLowerCase().contains("bed") && block.getType() != EnumMaterial.BREWING_STAND.getType() && block.getType() != EnumMaterial.BROWN_MUSHROOM.getType() && block.getType() != EnumMaterial.CACTUS.getType() && block.getType() != EnumMaterial.CAKE.getType() && !block.getType().toString().toLowerCase().contains("cake_block") && block.getType() != EnumMaterial.CARROTS.getType() && !block.getType().toString().toLowerCase().contains("carpet") && block.getType() != EnumMaterial.COBWEB.getType() && block.getType() != EnumMaterial.COCOA.getType() && !block.getType().toString().toLowerCase().contains("crops") && block.getType() != EnumMaterial.DANDELION.getType() && block.getType() != EnumMaterial.DEAD_BUSH.getType() && !block.getType().toString().toLowerCase().contains("double_plant") && block.getType() != EnumMaterial.DRAGON_EGG.getType() && block.getType() != EnumMaterial.END_PORTAL.getType() && block.getType() != EnumMaterial.END_PORTAL_FRAME.getType() && block.getType() != EnumMaterial.FARMLAND.getType() && block.getType() != EnumMaterial.FERN.getType() && block.getType() != EnumMaterial.FIRE.getType() && block.getType() != EnumMaterial.FLOWER_POT.getType() && !block.getType().toString().toLowerCase().contains("flower_pot") && block.getType() != EnumMaterial.GRASS.getType() && !block.getType().toString().toLowerCase().contains("head") && block.getType() != EnumMaterial.LADDER.getType() && block.getType() != EnumMaterial.LARGE_FERN.getType() && !block.getType().toString().toLowerCase().endsWith("lava") && block.getType() != EnumMaterial.LEVER.getType() && block.getType() != EnumMaterial.LILY_PAD.getType() && block.getType() != EnumMaterial.LILAC.getType() && !block.getType().toString().toLowerCase().contains("long_grass") && block.getType() != EnumMaterial.MELON_STEM.getType() && block.getType() != EnumMaterial.MUSHROOM_STEM.getType() && block.getType() != EnumMaterial.NETHER_PORTAL.getType() && block.getType() != EnumMaterial.NETHER_WART.getType() && block.getType() != EnumMaterial.PEONY.getType() && !block.getType().toString().toLowerCase().contains("piston_") && block.getType() != EnumMaterial.POTATOES.getType() && !block.getType().toString().toLowerCase().contains("potted_") && block.getType() != EnumMaterial.PUMPKIN_STEM.getType() && block.getType() != EnumMaterial.RED_MUSHROOM.getType() && !block.getType().toString().toLowerCase().contains("redstone_lamp") && !block.getType().toString().toLowerCase().contains("redstone_torch") && !block.getType().toString().toLowerCase().contains("red_rose") && block.getType() != EnumMaterial.REDSTONE_WIRE.getType() && block.getType() != EnumMaterial.ROSE_BUSH.getType() && !block.getType().toString().toLowerCase().contains("sapling") && !block.getType().toString().toLowerCase().contains("seeds") && !block.getType().toString().toLowerCase().contains("sign") && !block.getType().toString().toLowerCase().contains("skull") && block.getType() != EnumMaterial.SNOW.getType() && block.getType() != EnumMaterial.SPAWNER.getType() && block.getType() != EnumMaterial.SUGAR_CANE.getType() && !block.getType().toString().toLowerCase().contains("sugar_cane_block") && block.getType() != EnumMaterial.SUNFLOWER.getType() && block.getType() != EnumMaterial.TALL_GRASS.getType() && block.getType() != EnumMaterial.TORCH.getType() && block.getType() != EnumMaterial.TRIPWIRE.getType() && block.getType() != EnumMaterial.TRIPWIRE_HOOK.getType() && !block.getType().toString().toLowerCase().contains("tulip") && block.getType() != EnumMaterial.VINE.getType() && !block.getType().toString().toLowerCase().endsWith("water") && block.getType() != EnumMaterial.WHEAT.getType() && !block.getType().toString().toLowerCase().contains("_button") && !block.getType().toString().toLowerCase().contains("_plate") && !BlockUtil.isPortalBlock(block) && !BlockUtil.isCocoaBlock(block) && !BlockUtil.isChorusPlant(block) && !block.hasMetadata(CookieGadgets.getInstance().getPluginName()))) && !this.blocks.containsKey(block.getLocation())) {
            if (WorldGuardUtils.isInBlacklistedRegion(block.getLocation(), BlacklistedRegion.GADGETS)) {
                return;
            }
            if (VersionManager.is1_13OrAbove()) {
                try {
                    this.blocks.put(block.getLocation(), block.getBlockData().getAsString());
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            else {
                try {
                    this.blocks.put(block.getLocation(), String.valueOf(block.getType().toString()) + "," + block.getData());
                }
                catch (NoSuchMethodError noSuchMethodError2) {}
            }
            block.setType(enumMaterial.getType());
            block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    GadgetFireTrail.this.restoreBlock(player, block.getLocation());
                }
            }, (long)n);
        }
    }
    
    private void forceRestore() {
        if (this.blocks.isEmpty()) {
            return;
        }
        for (final Location location : this.blocks.keySet()) {
            final Block block = location.getBlock();
            if (VersionManager.is1_13OrAbove()) {
                try {
                    block.setBlockData(Bukkit.getServer().createBlockData((String)this.blocks.get(location)));
                }
                catch (Exception ex) {}
            }
            else {
                final String s = this.blocks.get(location);
                final Material value = Material.valueOf(s.split(",")[0]);
                final byte byteValue = Byte.valueOf(s.split(",")[1]);
                block.setType(value);
                ReflectionUtils.setData(block, byteValue);
            }
            block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
        }
        this.blocks.clear();
    }
    
    private void restoreBlock(final Player player, final Location location) {
        if (!this.blocks.containsKey(location)) {
            return;
        }
        final Block block = location.getBlock();
        if (VersionManager.is1_13OrAbove()) {
            try {
                block.setBlockData(Bukkit.getServer().createBlockData((String)this.blocks.get(location)));
            }
            catch (Exception ex) {}
        }
        else {
            final String s = this.blocks.get(location);
            final Material value = Material.valueOf(s.split(",")[0]);
            final byte byteValue = Byte.valueOf(s.split(",")[1]);
            block.setType(value);
            ReflectionUtils.setData(block, byteValue);
        }
        block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
        this.blocks.remove(location);
    }
}
