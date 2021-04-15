

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import ws.billy.CookieGadgets.utils.GMaterial;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import java.util.Iterator;
import org.bukkit.block.Block;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.CookieGadgets;
import java.util.Random;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.MessageType;
import java.util.UUID;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.List;
import org.bukkit.Location;
import java.util.HashMap;

public class GadgetPaintTrail extends Gadget
{
    private boolean activated;
    private HashMap<Location, String> blocks;
    private static List<String> blackList;
    
    static {
        GadgetPaintTrail.blackList = FileManager.getGadgetsFile().getStringList(String.valueOf(GadgetType.PAINT_TRAIL.getFilePath()) + ".Blacklist");
    }
    
    public GadgetPaintTrail(final UUID uuid) {
        super(uuid, GadgetType.PAINT_TRAIL);
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
        this.activated = true;
        new BukkitRunnable() {
            int step = 0;
            Random r = new Random();
            
            public void run() {
                if (!GadgetPaintTrail.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetPaintTrail.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetPaintTrail.this.getPlayer()).getCurrentGadget().getType() != GadgetPaintTrail.this.getType()) {
                    this.step = 200;
                    GadgetPaintTrail.this.onClear();
                    this.cancel();
                    return;
                }
                ++this.step;
                final byte b = (byte)this.r.nextInt(15);
                final EnumMaterial value = EnumMaterial.valueOf(159, b);
                if (this.step <= 200) {
                    final Block block = GadgetPaintTrail.this.getPlayer().getLocation().subtract(0.0, 1.0, 0.0).getBlock();
                    if (!block.isEmpty() && GadgetPaintTrail.this.getPlayer().isOnGround()) {
                        final Iterator<Block> iterator = BlockUtil.getBlocksInRadius(block.getLocation(), 1, false).iterator();
                        while (iterator.hasNext()) {
                            GadgetPaintTrail.this.setToRestore(GadgetPaintTrail.this.getPlayer(), iterator.next(), value, b, 60);
                        }
                    }
                }
                else {
                    GadgetPaintTrail.this.clearAll();
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
        this.activated = false;
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
    
    private void setToRestore(final Player player, final Block block, final EnumMaterial enumMaterial, final byte b, final int n) {
        if (this.blocks.containsKey(block.getLocation())) {
            return;
        }
        final Block relative = block.getRelative(BlockFace.UP);
        if (block.getType() != EnumMaterial.ANVIL.getType() && block.getType() != EnumMaterial.ATTACHED_MELON_STEM.getType() && block.getType() != EnumMaterial.ATTACHED_PUMPKIN_STEM.getType() && !block.getType().toString().toLowerCase().contains("banner") && block.getType() != EnumMaterial.BARREL.getType() && block.getType() != EnumMaterial.BARRIER.getType() && block.getType() != EnumMaterial.BEACON.getType() && !block.getType().toString().toLowerCase().contains("bed") && block.getType() != EnumMaterial.BEETROOTS.getType() && block.getType() != EnumMaterial.BREWING_STAND.getType() && block.getType() != EnumMaterial.BROWN_MUSHROOM.getType() && block.getType() != EnumMaterial.CACTUS.getType() && !block.getType().toString().toLowerCase().contains("cake_block") && block.getType() != EnumMaterial.CAKE.getType() && block.getType() != EnumMaterial.CARROTS.getType() && !block.getType().toString().toLowerCase().contains("carpet") && block.getType() != EnumMaterial.CHEST.getType() && block.getType() != EnumMaterial.COBWEB.getType() && block.getType() != EnumMaterial.COCOA.getType() && !block.getType().toString().toLowerCase().contains("command") && block.getType() != EnumMaterial.CRAFTING_TABLE.getType() && !block.getType().toString().toLowerCase().contains("crops") && block.getType() != EnumMaterial.DANDELION.getType() && block.getType() != EnumMaterial.DEAD_BUSH.getType() && block.getType() != EnumMaterial.DISPENSER.getType() && !block.getType().toString().toLowerCase().contains("double_plant") && !block.getType().toString().toLowerCase().contains("door") && block.getType() != EnumMaterial.DRAGON_EGG.getType() && block.getType() != EnumMaterial.DROPPER.getType() && block.getType() != EnumMaterial.ENCHANTING_TABLE.getType() && block.getType() != EnumMaterial.END_PORTAL.getType() && block.getType() != EnumMaterial.END_PORTAL_FRAME.getType() && block.getType() != EnumMaterial.FARMLAND.getType() && block.getType() != EnumMaterial.FERN.getType() && block.getType() != EnumMaterial.FLOWER_POT.getType() && !block.getType().toString().toLowerCase().contains("flower_pot") && block.getType() != EnumMaterial.FIRE.getType() && !block.getType().toString().toLowerCase().contains("furnace") && block.getType() != EnumMaterial.GRASS.getType() && !block.getType().toString().toLowerCase().contains("head") && block.getType() != EnumMaterial.HOPPER.getType() && block.getType() != EnumMaterial.JUKEBOX.getType() && block.getType() != EnumMaterial.LADDER.getType() && block.getType() != EnumMaterial.LARGE_FERN.getType() && block.getType() != EnumMaterial.LEVER.getType() && block.getType() != EnumMaterial.LILY_PAD.getType() && block.getType() != EnumMaterial.LILAC.getType() && !block.getType().toString().toLowerCase().contains("long_grass") && block.getType() != EnumMaterial.MELON_STEM.getType() && block.getType() != EnumMaterial.MUSHROOM_STEM.getType() && block.getType() != EnumMaterial.NETHER_WART.getType() && block.getType() != EnumMaterial.NETHER_PORTAL.getType() && block.getType() != EnumMaterial.NOTE_BLOCK.getType() && block.getType() != EnumMaterial.PEONY.getType() && !block.getType().toString().toLowerCase().contains("piston_") && !block.getType().toString().toLowerCase().contains("plate") && block.getType() != EnumMaterial.POTATOES.getType() && !block.getType().toString().toLowerCase().contains("potted_") && block.getType() != EnumMaterial.PUMPKIN_STEM.getType() && !block.getType().toString().toLowerCase().contains("redstone_comparator") && !block.getType().toString().toLowerCase().contains("redstone_lamp") && !block.getType().toString().toLowerCase().contains("redstone_torch") && block.getType() != EnumMaterial.REDSTONE_WIRE.getType() && block.getType() != EnumMaterial.REPEATER.getType() && block.getType() != EnumMaterial.RED_MUSHROOM.getType() && !block.getType().toString().toLowerCase().contains("red_rose") && block.getType() != EnumMaterial.ROSE_BUSH.getType() && !block.getType().toString().toLowerCase().contains("sapling") && block.getType() != EnumMaterial.SCAFFOLDING.getType() && !block.getType().toString().toLowerCase().contains("seeds") && !block.getType().toString().toLowerCase().contains("sign") && !block.getType().toString().toLowerCase().contains("shulker_box") && !block.getType().toString().toLowerCase().contains("skull") && block.getType() != EnumMaterial.SMOKER.getType() && block.getType() != EnumMaterial.SNOW.getType() && block.getType() != EnumMaterial.SPAWNER.getType() && block.getType() != EnumMaterial.SUGAR_CANE.getType() && !block.getType().toString().toLowerCase().contains("sugar_cane_block") && block.getType() != EnumMaterial.SUNFLOWER.getType() && block.getType() != EnumMaterial.SWEET_BERRY_BUSH.getType() && block.getType() != EnumMaterial.TALL_GRASS.getType() && block.getType() != EnumMaterial.TORCH.getType() && block.getType() != EnumMaterial.TRAPPED_CHEST.getType() && block.getType() != EnumMaterial.TRIPWIRE_HOOK.getType() && block.getType() != EnumMaterial.TRIPWIRE.getType() && !block.getType().toString().toLowerCase().contains("tulip") && block.getType() != EnumMaterial.VINE.getType() && block.getType() != EnumMaterial.WHEAT.getType() && !block.getType().toString().toLowerCase().contains("_button") && !block.isEmpty() && !block.isLiquid() && !BlockUtil.isPortalBlock(block) && !BlockUtil.isCocoaBlock(block) && !BlockUtil.isStem(block) && !BlockUtil.isChorusPlant(block) && !BlockUtil.isMushroomBlock(block) && !BlockUtil.hasBlockObject(block) && !BlockUtil.hasBlockObject(relative) && !block.hasMetadata(CookieGadgets.getInstance().getPluginName())) {
            final Iterator<String> iterator = GadgetPaintTrail.blackList.iterator();
            while (iterator.hasNext()) {
                final GMaterial gMaterial = new GMaterial(iterator.next());
                if (block.getType() == gMaterial.getEnumMaterial().getType() && block.getData() == gMaterial.getData()) {
                    return;
                }
            }
            if (!this.blocks.containsKey(block.getLocation())) {
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
                ReflectionUtils.setData(block, b);
                block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        GadgetPaintTrail.this.restoreBlock(player, block.getLocation());
                    }
                }, (long)n);
            }
        }
    }
}
