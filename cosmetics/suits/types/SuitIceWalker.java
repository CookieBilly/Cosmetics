

package ws.billy.CookieGadgets.cosmetics.suits.types;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.block.Block;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.cosmetics.suits.SuitType;
import java.util.UUID;
import org.bukkit.Location;
import java.util.HashMap;

public class SuitIceWalker extends Suit
{
    private boolean activated;
    private HashMap<Location, String> snowBlocks;
    
    public SuitIceWalker(final UUID uuid) {
        super(uuid, SuitType.ICE_WALKER);
        this.activated = false;
        this.snowBlocks = new HashMap<Location, String>();
    }
    
    public void onUpdate() {
        ParticleEffect.SNOWBALL.display(this.getPlayer().getLocation().add(0.0, 1.0, 0.0), 1.0f, 0.7f, 1.0f, 1.0f, 15);
        this.activated = true;
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        this.forceRestore();
        this.activated = false;
    }
    
    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent playerMoveEvent) {
        if (playerMoveEvent.getPlayer() == this.getPlayer() && this.activated) {
            final Block block = this.getPlayer().getLocation().getBlock();
            final Block block2 = this.getPlayer().getLocation().add(0.0, -1.0, 0.0).getBlock();
            if (block.isEmpty() && !block2.isEmpty() && !block2.isLiquid() && this.getPlayer().isOnGround() && block2.getType() != EnumMaterial.ATTACHED_MELON_STEM.getType() && block2.getType() != EnumMaterial.ATTACHED_PUMPKIN_STEM.getType() && !block2.getType().toString().toLowerCase().contains("banner") && !block2.getType().toString().toLowerCase().contains("bed") && block2.getType() != EnumMaterial.BEETROOTS.getType() && block2.getType() != EnumMaterial.BREWING_STAND.getType() && block2.getType() != EnumMaterial.BROWN_MUSHROOM.getType() && block2.getType() != EnumMaterial.CACTUS.getType() && block2.getType() != EnumMaterial.CAKE.getType() && !block2.getType().toString().toLowerCase().contains("cake_block") && block2.getType() != EnumMaterial.CARROTS.getType() && !block2.getType().toString().toLowerCase().contains("carpet") && block2.getType() != EnumMaterial.COBWEB.getType() && block2.getType() != EnumMaterial.COCOA.getType() && !block2.getType().toString().toLowerCase().contains("crops") && block2.getType() != EnumMaterial.DANDELION.getType() && block2.getType() != EnumMaterial.DEAD_BUSH.getType() && !block2.getType().toString().toLowerCase().contains("double_plant") && block2.getType() != EnumMaterial.DRAGON_EGG.getType() && block2.getType() != EnumMaterial.END_PORTAL.getType() && block2.getType() != EnumMaterial.END_PORTAL_FRAME.getType() && block2.getType() != EnumMaterial.FARMLAND.getType() && block2.getType() != EnumMaterial.FERN.getType() && block2.getType() != EnumMaterial.FIRE.getType() && block2.getType() != EnumMaterial.FLOWER_POT.getType() && !block2.getType().toString().toLowerCase().contains("flower_pot") && block2.getType() != EnumMaterial.GRASS.getType() && !block2.getType().toString().toLowerCase().contains("head") && block2.getType() != EnumMaterial.LADDER.getType() && block2.getType() != EnumMaterial.LARGE_FERN.getType() && !block2.getType().toString().toLowerCase().endsWith("lava") && block2.getType() != EnumMaterial.LEVER.getType() && block2.getType() != EnumMaterial.LILAC.getType() && block2.getType() != EnumMaterial.LILY_PAD.getType() && !block2.getType().toString().toLowerCase().contains("long_grass") && block2.getType() != EnumMaterial.MELON_STEM.getType() && block2.getType() != EnumMaterial.MUSHROOM_STEM.getType() && block2.getType() != EnumMaterial.NETHER_PORTAL.getType() && block2.getType() != EnumMaterial.NETHER_WART.getType() && block2.getType() != EnumMaterial.PEONY.getType() && !block2.getType().toString().toLowerCase().contains("piston_") && block2.getType() != EnumMaterial.POTATOES.getType() && !block2.getType().toString().toLowerCase().contains("potted_") && block2.getType() != EnumMaterial.PUMPKIN_STEM.getType() && !block2.getType().toString().toLowerCase().contains("redstone_lamp") && block2.getType() != EnumMaterial.RED_MUSHROOM.getType() && !block2.getType().toString().toLowerCase().contains("red_rose") && block2.getType() != EnumMaterial.REDSTONE_WIRE.getType() && block2.getType() != EnumMaterial.ROSE_BUSH.getType() && !block2.getType().toString().toLowerCase().contains("sapling") && !block2.getType().toString().toLowerCase().contains("seeds") && !block2.getType().toString().toLowerCase().contains("sign") && !block2.getType().toString().toLowerCase().contains("skull") && block2.getType() != EnumMaterial.SNOW.getType() && block2.getType() != EnumMaterial.SUGAR_CANE.getType() && !block2.getType().toString().toLowerCase().contains("sugar_cane_block") && block2.getType() != EnumMaterial.SUNFLOWER.getType() && block2.getType() != EnumMaterial.SWEET_BERRY_BUSH.getType() && block2.getType() != EnumMaterial.TALL_GRASS.getType() && !block2.getType().toString().toLowerCase().contains("touch") && block2.getType() != EnumMaterial.TRIPWIRE.getType() && block2.getType() != EnumMaterial.TRIPWIRE_HOOK.getType() && !block2.getType().toString().toLowerCase().contains("tulip") && block2.getType() != EnumMaterial.VINE.getType() && !block2.getType().toString().toLowerCase().endsWith("water") && block2.getType() != EnumMaterial.WHEAT.getType() && !block2.getType().toString().toLowerCase().contains("_button") && !BlockUtil.isPortalBlock(block2) && !BlockUtil.isCocoaBlock(block2) && !BlockUtil.isChorusPlant(block2) && !block2.hasMetadata(CookieGadgets.getInstance().getPluginName())) {
                this.setToRestore(this.getPlayer(), block, EnumMaterial.SNOW, 60);
            }
        }
    }
    
    private void setToRestore(final Player player, final Block block, final EnumMaterial enumMaterial, final int n) {
        if (this.snowBlocks.containsKey(block.getLocation())) {
            return;
        }
        if (!block.hasMetadata(CookieGadgets.getInstance().getPluginName()) && !this.snowBlocks.containsKey(block.getLocation())) {
            if (WorldGuardUtils.isInBlacklistedRegion(block.getLocation(), BlacklistedRegion.SUITS)) {
                return;
            }
            if (VersionManager.is1_13OrAbove()) {
                try {
                    this.snowBlocks.put(block.getLocation(), block.getBlockData().getAsString());
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            else {
                try {
                    this.snowBlocks.put(block.getLocation(), String.valueOf(block.getType().toString()) + "," + block.getData());
                }
                catch (NoSuchMethodError noSuchMethodError2) {}
            }
            block.setType(enumMaterial.getType());
            block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    SuitIceWalker.this.restoreBlock(player, block.getLocation());
                }
            }, (long)n);
        }
    }
    
    private void forceRestore() {
        if (this.snowBlocks.isEmpty()) {
            return;
        }
        for (final Location location : this.snowBlocks.keySet()) {
            final Block block = location.getBlock();
            if (VersionManager.is1_13OrAbove()) {
                try {
                    block.setBlockData(Bukkit.getServer().createBlockData((String)this.snowBlocks.get(location)));
                }
                catch (Exception ex) {}
            }
            else {
                final String s = this.snowBlocks.get(location);
                final Material value = Material.valueOf(s.split(",")[0]);
                final byte byteValue = Byte.valueOf(s.split(",")[1]);
                block.setType(value);
                ReflectionUtils.setData(block, byteValue);
            }
            block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
        }
        this.snowBlocks.clear();
    }
    
    private void restoreBlock(final Player player, final Location location) {
        if (!this.snowBlocks.containsKey(location)) {
            return;
        }
        final Block block = location.getBlock();
        if (VersionManager.is1_13OrAbove()) {
            try {
                block.setBlockData(Bukkit.getServer().createBlockData((String)this.snowBlocks.get(location)));
            }
            catch (Exception ex) {}
        }
        else {
            final String s = this.snowBlocks.get(location);
            final Material value = Material.valueOf(s.split(",")[0]);
            final byte byteValue = Byte.valueOf(s.split(",")[1]);
            block.setType(value);
            ReflectionUtils.setData(block, byteValue);
        }
        block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
        this.snowBlocks.remove(location);
    }
}
