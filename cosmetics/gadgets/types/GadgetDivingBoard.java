

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.block.data.BlockData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.block.data.Directional;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.Material;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.util.Vector;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.block.Block;
import ws.billy.CookieGadgets.utils.CuboID;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.List;
import java.util.HashMap;
import org.bukkit.Location;

public class GadgetDivingBoard extends Gadget
{
    private boolean activated;
    private Location location;
    private HashMap<Location, String> blocks;
    private HashMap<Location, String> waterGroundBlocks;
    private static List<String> messages;
    
    static {
        GadgetDivingBoard.messages = FileManager.getGadgetsFile().getStringList("Gadgets.Fun And Games.Types.Diving Board.Messages");
    }
    
    public GadgetDivingBoard(final UUID uuid) {
        super(uuid, GadgetType.DIVING_BOARD);
        this.activated = false;
        this.blocks = new HashMap<Location, String>();
        this.waterGroundBlocks = new HashMap<Location, String>();
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        if (!this.getPlayer().isOnGround()) {
            this.getPlayer().sendMessage(MessageType.NOT_ON_GROUND.getFormatMessage());
            return false;
        }
        final Location add = this.getPlayer().getLocation().clone().add(-1.0, 0.0, -2.0);
        final Location add2 = this.getPlayer().getLocation().clone().add(7.0, 8.0, 2.0);
        final Location add3 = this.getPlayer().getLocation().clone().add(4.0, -1.0, -1.0);
        final Location add4 = this.getPlayer().getLocation().clone().add(6.0, -1.0, 1.0);
        final CuboID cuboID = new CuboID(add, add2);
        final CuboID cuboID2 = new CuboID(add3, add4);
        if (!cuboID.isEmpty()) {
            this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
            return false;
        }
        for (final Block block : cuboID2.getBlocks()) {
            if (block.hasMetadata(CookieGadgets.getInstance().getPluginName())) {
                this.getPlayer().sendMessage(MessageType.GADGET_ACTIVATED_IN_SAME_AREA.getFormatMessage());
                return false;
            }
            if (block.isLiquid() || block.isEmpty() || block.getType() == EnumMaterial.ATTACHED_MELON_STEM.getType() || block.getType() == EnumMaterial.ATTACHED_PUMPKIN_STEM.getType() || block.getType().toString().toLowerCase().contains("banner") || block.getType() == EnumMaterial.BREWING_STAND.getType() || block.getType() == EnumMaterial.BROWN_MUSHROOM.getType() || block.getType() == EnumMaterial.CACTUS.getType() || block.getType() == EnumMaterial.CAKE.getType() || block.getType().toString().toLowerCase().contains("cake_block") || block.getType() == EnumMaterial.CARROTS.getType() || block.getType().toString().toLowerCase().contains("carpet") || block.getType() == EnumMaterial.COBWEB.getType() || block.getType().toString().toLowerCase().contains("crops") || block.getType() == EnumMaterial.DANDELION.getType() || block.getType() == EnumMaterial.DEAD_BUSH.getType() || block.getType().toString().toLowerCase().contains("double_plant") || block.getType() == EnumMaterial.FARMLAND.getType() || block.getType() == EnumMaterial.FERN.getType() || block.getType() == EnumMaterial.FIRE.getType() || block.getType() == EnumMaterial.FLOWER_POT.getType() || block.getType().toString().toLowerCase().contains("flower_pot") || block.getType() == EnumMaterial.GRASS.getType() || block.getType().toString().toLowerCase().contains("head") || block.getType() == EnumMaterial.LADDER.getType() || block.getType() == EnumMaterial.LARGE_FERN.getType() || block.getType() == EnumMaterial.LEVER.getType() || block.getType() == EnumMaterial.LILAC.getType() || block.getType() == EnumMaterial.LILY_PAD.getType() || block.getType().toString().toLowerCase().contains("long_grass") || block.getType() == EnumMaterial.MELON_STEM.getType() || block.getType() == EnumMaterial.MUSHROOM_STEM.getType() || block.getType() == EnumMaterial.NETHER_WART.getType() || block.getType() == EnumMaterial.PEONY.getType() || block.getType().toString().toLowerCase().contains("plate") || block.getType() == EnumMaterial.POTATOES.getType() || block.getType().toString().toLowerCase().contains("potted_") || block.getType() == EnumMaterial.PUMPKIN_STEM.getType() || block.getType().toString().toLowerCase().contains("redstone_torch") || block.getType() == EnumMaterial.REDSTONE_WIRE.getType() || block.getType().toString().toLowerCase().contains("red_rose") || block.getType() == EnumMaterial.RED_MUSHROOM.getType() || block.getType() == EnumMaterial.ROSE_BUSH.getType() || block.getType().toString().toLowerCase().contains("sapling") || block.getType().toString().toLowerCase().contains("seeds") || block.getType().toString().toLowerCase().contains("sign") || block.getType().toString().toLowerCase().contains("skull") || block.getType() == EnumMaterial.SNOW.getType() || block.getType() == EnumMaterial.SPAWNER.getType() || block.getType() == EnumMaterial.SUGAR_CANE.getType() || block.getType().toString().toLowerCase().contains("sugar_cane_block") || block.getType() == EnumMaterial.SUNFLOWER.getType() || block.getType() == EnumMaterial.TALL_GRASS.getType() || block.getType() == EnumMaterial.TRIPWIRE.getType() || block.getType() == EnumMaterial.TRIPWIRE_HOOK.getType() || block.getType() == EnumMaterial.TORCH.getType() || block.getType().toString().toLowerCase().contains("tulip") || block.getType() == EnumMaterial.VINE.getType() || block.getType() == EnumMaterial.WHEAT.getType() || block.getType().toString().toLowerCase().contains("_button") || BlockUtil.hasHangingEntities(block.getLocation()) || BlockUtil.isCocoaBlock(block)) {
                this.getPlayer().sendMessage(MessageType.NOT_ON_FLAT_GROUND.getFormatMessage());
                return false;
            }
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.location = this.getPlayer().getLocation().clone();
        final Location add = this.getPlayer().getLocation().getBlock().getLocation().clone().add(0.5, 5.0, 0.5);
        add.setYaw(-90.0f);
        this.getPlayer().teleport(add);
        this.generateDivingBoard();
        new BukkitRunnable() {
            public void run() {
                if (!GadgetDivingBoard.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetDivingBoard.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetDivingBoard.this.getPlayer()).getCurrentGadget().getType() != GadgetDivingBoard.this.getType() || !GadgetDivingBoard.this.activated) {
                    return;
                }
                GadgetDivingBoard.this.clearAll();
            }
        }.runTaskLater((Plugin)CookieGadgets.getInstance(), 800L);
        this.activated = true;
    }
    
    @Override
    public void onUpdate() {
        if (this.activated) {
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    for (final Player player : PlayerUtils.getNearbyPlayers(GadgetDivingBoard.this.getLocation(2, 4, 0).getLocation(), 2.0)) {
                        final Block relative = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
                        if (relative.getType() == EnumMaterial.SMOOTH_STONE_SLAB.getType() && relative.getData() == 0) {
                            MathUtil.applyVelocity((Entity)player, new Vector(0.3, 1.8, 0.0));
                            SoundEffect.BLOCK_WOODEN_TRAPDOOR_OPEN.playSound(relative.getLocation());
                            if (!CookieGadgets.random().nextBoolean() || GadgetDivingBoard.messages.size() == 0) {
                                continue;
                            }
                            player.sendMessage(ChatUtil.format(GadgetDivingBoard.messages.get(CookieGadgets.random().nextInt(GadgetDivingBoard.messages.size()))));
                        }
                    }
                }
            });
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
    }
    
    private void clearAll() {
        if (this.location != null) {
            this.getLocation(-1, 3, 0).setType(Material.AIR);
            this.getLocation(-1, 2, 0).setType(Material.AIR);
            this.getLocation(-1, 1, 0).setType(Material.AIR);
            this.getLocation(-1, 0, 0).setType(Material.AIR);
        }
        final Iterator<Location> iterator = this.blocks.keySet().iterator();
        while (iterator.hasNext()) {
            final Block block = iterator.next().clone().getBlock();
            block.setType(Material.AIR);
            block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
        }
        this.blocks.clear();
        for (final Location location : this.waterGroundBlocks.keySet()) {
            final Block block2 = location.clone().getBlock();
            if (VersionManager.is1_13OrAbove()) {
                try {
                    block2.setBlockData(Bukkit.getServer().createBlockData((String)this.waterGroundBlocks.get(location)));
                }
                catch (Exception ex) {}
            }
            else {
                final String s = this.waterGroundBlocks.get(location);
                final Material value = Material.valueOf(s.split(",")[0]);
                final byte byteValue = Byte.valueOf(s.split(",")[1]);
                block2.setType(value);
                ReflectionUtils.setData(block2, byteValue);
            }
            block2.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
        }
        this.waterGroundBlocks.clear();
        this.activated = false;
    }
    
    private void generateDivingBoard() {
        this.saveWaterGroundBlock(this.getLocation(4, -1, -1));
        this.saveWaterGroundBlock(this.getLocation(4, -1, 0));
        this.saveWaterGroundBlock(this.getLocation(4, -1, 1));
        this.saveWaterGroundBlock(this.getLocation(5, -1, -1));
        this.saveWaterGroundBlock(this.getLocation(5, -1, 0));
        this.saveWaterGroundBlock(this.getLocation(5, -1, 1));
        this.saveWaterGroundBlock(this.getLocation(6, -1, -1));
        this.saveWaterGroundBlock(this.getLocation(6, -1, 0));
        this.saveWaterGroundBlock(this.getLocation(6, -1, 1));
        this.setBlock(this.getLocation(3, 0, 0), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(3, 0, -1), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(3, 0, -2), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(4, 0, -2), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(5, 0, -2), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(6, 0, -2), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(7, 0, -2), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(7, 0, -1), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(7, 0, 0), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(7, 0, 1), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(7, 0, 2), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(3, 0, 1), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(3, 0, 2), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(4, 0, 2), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(5, 0, 2), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(6, 0, 2), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(4, 0, 0), EnumMaterial.WATER, false, (byte)0);
        this.setBlock(this.getLocation(4, 0, -1), EnumMaterial.WATER, false, (byte)0);
        this.setBlock(this.getLocation(4, 0, 1), EnumMaterial.WATER, false, (byte)0);
        this.setBlock(this.getLocation(5, 0, 0), EnumMaterial.WATER, false, (byte)0);
        this.setBlock(this.getLocation(5, 0, -1), EnumMaterial.WATER, false, (byte)0);
        this.setBlock(this.getLocation(5, 0, 1), EnumMaterial.WATER, false, (byte)0);
        this.setBlock(this.getLocation(6, 0, 0), EnumMaterial.WATER, false, (byte)0);
        this.setBlock(this.getLocation(6, 0, -1), EnumMaterial.WATER, false, (byte)0);
        this.setBlock(this.getLocation(6, 0, 1), EnumMaterial.WATER, false, (byte)0);
        this.setBlock(this.getLocation(1, 3, 0), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(2, 3, 0), EnumMaterial.SMOOTH_STONE_SLAB, false, (byte)0);
        this.setBlock(this.getLocation(0, 3, 0), EnumMaterial.IRON_BLOCK, false, (byte)0);
        this.setBlock(this.getLocation(0, 2, 0), EnumMaterial.IRON_BLOCK, false, (byte)0);
        this.setBlock(this.getLocation(0, 1, 0), EnumMaterial.IRON_BLOCK, false, (byte)0);
        this.setBlock(this.getLocation(0, 0, 0), EnumMaterial.IRON_BLOCK, false, (byte)0);
        this.setBlock(this.getLocation(-1, 3, 0), EnumMaterial.LADDER, true, (byte)4);
        this.setBlock(this.getLocation(-1, 2, 0), EnumMaterial.LADDER, true, (byte)4);
        this.setBlock(this.getLocation(-1, 1, 0), EnumMaterial.LADDER, true, (byte)4);
        this.setBlock(this.getLocation(-1, 0, 0), EnumMaterial.LADDER, true, (byte)4);
    }
    
    private void setBlock(final Block block, final EnumMaterial enumMaterial, final boolean b, final byte b2) {
        if (!this.blocks.containsKey(block.getLocation())) {
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
        }
        if (enumMaterial != null) {
            block.setType(enumMaterial.getType());
            if (VersionManager.is1_13OrAbove()) {
                if (b) {
                    final BlockData blockData = block.getBlockData();
                    ((Directional)blockData).setFacing(BlockUtil.getBlockFace(b2));
                    block.setBlockData(blockData);
                }
            }
            else {
                ReflectionUtils.setData(block, b ? b2 : enumMaterial.getData());
            }
        }
        block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
    }
    
    private void saveWaterGroundBlock(final Block block) {
        if (!this.waterGroundBlocks.containsKey(block.getLocation())) {
            if (VersionManager.is1_13OrAbove()) {
                try {
                    this.waterGroundBlocks.put(block.getLocation(), block.getBlockData().getAsString());
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            else {
                try {
                    this.waterGroundBlocks.put(block.getLocation(), String.valueOf(block.getType().toString()) + "," + block.getData());
                }
                catch (NoSuchMethodError noSuchMethodError2) {}
            }
        }
        block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
    }
    
    private Block getLocation(final int n, final int n2, final int n3) {
        return this.location.getBlock().getRelative(n, n2, n3);
    }
}
