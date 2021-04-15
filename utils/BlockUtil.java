

package ws.billy.CookieGadgets.utils;

import org.bukkit.entity.Painting;
import org.bukkit.entity.ItemFrame;
import java.util.Iterator;
import java.util.Arrays;
import org.bukkit.WorldBorder;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import java.util.ArrayList;
import org.bukkit.block.Block;
import java.util.List;
import org.bukkit.Location;

public class BlockUtil
{
    public static List<Block> getBlocksInRadius(final Location location, final int n, final boolean b) {
        final ArrayList<Block> list = new ArrayList<Block>();
        final int blockX = location.getBlockX();
        final int blockY = location.getBlockY();
        final int blockZ = location.getBlockZ();
        for (int i = blockX - n; i <= blockX + n; ++i) {
            for (int j = blockY - n; j <= blockY + n; ++j) {
                for (int k = blockZ - n; k <= blockZ + n; ++k) {
                    final double n2 = (blockX - i) * (blockX - i) + (blockY - j) * (blockY - j) + (blockZ - k) * (blockZ - k);
                    if (n2 < n * n && (!b || n2 >= (n - 1) * (n - 1))) {
                        final Location location2 = new Location(location.getWorld(), (double)i, (double)j, (double)k);
                        if (location2.getBlock().getType() != Material.BARRIER) {
                            list.add(location2.getBlock());
                        }
                    }
                }
            }
        }
        return list;
    }
    
    public static List<Block> getBlocksInRadiusXZ(final Location location, final int n, final boolean b) {
        final ArrayList<Block> list = new ArrayList<Block>();
        final int blockX = location.getBlockX();
        final int blockY = location.getBlockY();
        final int blockZ = location.getBlockZ();
        for (int i = blockX - n; i <= blockX + n; ++i) {
            for (int j = blockY - 1; j <= blockY; ++j) {
                for (int k = blockZ - n; k <= blockZ + n; ++k) {
                    final double n2 = (blockX - i) * (blockX - i) + (blockY - j) * (blockY - j) + (blockZ - k) * (blockZ - k);
                    if (n2 < n * n && (!b || n2 >= (n - 1) * (n - 1))) {
                        final Location location2 = new Location(location.getWorld(), (double)i, (double)j, (double)k);
                        if (location2.getBlock().getType() != Material.BARRIER) {
                            list.add(location2.getBlock());
                        }
                    }
                }
            }
        }
        return list;
    }
    
    public static List<Block> getSquareBlocks(final Location location, final int n) {
        final ArrayList<Block> list = new ArrayList<Block>();
        final int n2 = location.getBlockX() - n / 2;
        final int n3 = location.getBlockY() - n / 2;
        final int n4 = location.getBlockZ() - n / 2;
        for (int i = n2; i < n2 + n; ++i) {
            for (int j = n3; j < n3 + n; ++j) {
                for (int k = n4; k < n4 + n; ++k) {
                    list.add(location.getWorld().getBlockAt(i, j, k));
                }
            }
        }
        return list;
    }
    
    public static List<Location> getLocationsInRadius(final Location location, final int n, final boolean b) {
        final ArrayList<Location> list = new ArrayList<Location>();
        final int blockX = location.getBlockX();
        final int blockY = location.getBlockY();
        final int blockZ = location.getBlockZ();
        for (int i = blockX - n; i <= blockX + n; ++i) {
            for (int j = blockY - n; j <= blockY + n; ++j) {
                for (int k = blockZ - n; k <= blockZ + n; ++k) {
                    final double n2 = (blockX - i) * (blockX - i) + (blockY - j) * (blockY - j) + (blockZ - k) * (blockZ - k);
                    if (n2 < n * n && (!b || n2 >= (n - 1) * (n - 1))) {
                        list.add(new Location(location.getWorld(), (double)i, (double)j, (double)k).getBlock().getLocation());
                    }
                }
            }
        }
        return list;
    }
    
    public static Block getTargetBlock(final Player player, final int n) {
        try {
            return player.getTargetBlock((Set)null, n);
        }
        catch (IllegalStateException ex) {
            return null;
        }
    }
    
    public static boolean isOnGround(final Entity entity) {
        return entity.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid();
    }
    
    public static double getDistance(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n3;
        final int n6 = n2 - n4;
        return Math.sqrt(n5 * n5 + n6 * n6);
    }
    
    public static boolean hasBlockObject(final Block block) {
        return block.getType() == EnumMaterial.ALLIUM.getType() || block.getType() == EnumMaterial.AZURE_BLUET.getType() || block.getType() == EnumMaterial.ATTACHED_MELON_STEM.getType() || block.getType() == EnumMaterial.ATTACHED_PUMPKIN_STEM.getType() || block.getType().toString().toLowerCase().contains("bed") || block.getType() == EnumMaterial.BAMBOO.getType() || block.getType() == EnumMaterial.BEETROOTS.getType() || block.getType() == EnumMaterial.BLUE_ORCHID.getType() || block.getType() == EnumMaterial.BROWN_MUSHROOM.getType() || block.getType() == EnumMaterial.CACTUS.getType() || block.getType() == EnumMaterial.CAKE.getType() || block.getType().toString().toLowerCase().contains("cake_block") || block.getType() == EnumMaterial.CARROTS.getType() || block.getType() == EnumMaterial.COCOA.getType() || block.getType() == EnumMaterial.CORNFLOWER.getType() || block.getType().toString().toLowerCase().contains("crops") || block.getType() == EnumMaterial.DANDELION.getType() || block.getType() == EnumMaterial.DEAD_BUSH.getType() || block.getType().toString().toLowerCase().contains("double_plant") || block.getType() == EnumMaterial.FERN.getType() || block.getType() == EnumMaterial.FIRE.getType() || block.getType() == EnumMaterial.FLOWER_POT.getType() || block.getType() == EnumMaterial.GRASS.getType() || block.getType() == EnumMaterial.LARGE_FERN.getType() || block.getType() == EnumMaterial.LILAC.getType() || block.getType() == EnumMaterial.LILY_PAD.getType() || block.getType() == EnumMaterial.LILY_OF_THE_VALLEY.getType() || block.getType().toString().toLowerCase().contains("long_grass") || block.getType() == EnumMaterial.MELON_STEM.getType() || block.getType() == EnumMaterial.MUSHROOM_STEM.getType() || block.getType() == EnumMaterial.NETHER_WART.getType() || block.getType() == EnumMaterial.OXEYE_DAISY.getType() || block.getType() == EnumMaterial.PEONY.getType() || block.getType() == EnumMaterial.POPPY.getType() || block.getType() == EnumMaterial.POTATOES.getType() || block.getType() == EnumMaterial.PUMPKIN_STEM.getType() || block.getType() == EnumMaterial.REDSTONE_WIRE.getType() || block.getType().toString().toLowerCase().contains("redstone_torch") || block.getType() == EnumMaterial.RED_BED.getType() || block.getType() == EnumMaterial.RED_MUSHROOM.getType() || block.getType().toString().toLowerCase().contains("red_rose") || block.getType() == EnumMaterial.ROSE_BUSH.getType() || block.getType().toString().toLowerCase().contains("sapling") || block.getType() == EnumMaterial.SEAGRASS.getType() || block.getType().toString().toLowerCase().contains("seeds") || block.getType() == EnumMaterial.SNOW.getType() || block.getType() == EnumMaterial.SUGAR_CANE.getType() || block.getType().toString().toLowerCase().contains("sugar_cane_block") || block.getType() == EnumMaterial.SUNFLOWER.getType() || block.getType() == EnumMaterial.SWEET_BERRY_BUSH.getType() || block.getType() == EnumMaterial.TALL_GRASS.getType() || block.getType() == EnumMaterial.TALL_SEAGRASS.getType() || block.getType() == EnumMaterial.TORCH.getType() || block.getType().toString().toLowerCase().contains("tulip") || block.getType() == EnumMaterial.WHEAT.getType() || block.getType() == EnumMaterial.WITHER_ROSE.getType();
    }
    
    public static BlockFace getBlockFace(final int n) {
        if (n == 0) {
            return BlockFace.EAST;
        }
        if (n == 1 || n == 4) {
            return BlockFace.WEST;
        }
        if (n == 2 || n == 5) {
            return BlockFace.SOUTH;
        }
        if (n == 3) {
            return BlockFace.NORTH;
        }
        return BlockFace.EAST;
    }
    
    public static boolean isOutsideOfBorder(final Location location) {
        if (location == null) {
            return false;
        }
        if (VersionManager.is1_11OrAbove()) {
            final WorldBorder worldBorder = location.getWorld().getWorldBorder();
            return !worldBorder.isInside(location) || !worldBorder.isInside(location.add(0.5, 0.0, 0.0)) || !worldBorder.isInside(location.add(-0.5, 0.0, 0.0)) || !worldBorder.isInside(location.add(0.0, 0.0, 0.5)) || !worldBorder.isInside(location.add(0.0, 0.0, 0.5));
        }
        final WorldBorder worldBorder2 = location.getWorld().getWorldBorder();
        final Location center = worldBorder2.getCenter();
        final double x = location.getX();
        final double z = location.getZ();
        final double size = worldBorder2.getSize();
        return Math.abs(x - center.getX()) > size / 2.0 || Math.abs(z - center.getZ()) > size / 2.0;
    }
    
    public static boolean isPortalBlock(final Block block) {
        final Iterator<BlockFace> iterator = Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN, BlockFace.SELF).iterator();
        while (iterator.hasNext()) {
            if (block.getRelative((BlockFace)iterator.next()).getType() == EnumMaterial.NETHER_PORTAL.getType()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isCocoaBlock(final Block block) {
        final Iterator<BlockFace> iterator = Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN, BlockFace.SELF).iterator();
        while (iterator.hasNext()) {
            if (block.getRelative((BlockFace)iterator.next()).getType() == EnumMaterial.COCOA.getType()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isStem(final Block block) {
        for (final BlockFace blockFace : Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN, BlockFace.SELF)) {
            if (block.getRelative(blockFace).getType() == EnumMaterial.ATTACHED_MELON_STEM.getType() || block.getRelative(blockFace).getType() == EnumMaterial.ATTACHED_PUMPKIN_STEM.getType()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isMushroomBlock(final Block block) {
        BlockFace[] array;
        for (int length = (array = new BlockFace[] { BlockFace.EAST, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST }).length, i = 0; i < length; ++i) {
            final BlockFace blockFace = array[i];
            if (block.getRelative(blockFace).getType() == EnumMaterial.BROWN_MUSHROOM.getType() || block.getRelative(blockFace).getType() == EnumMaterial.RED_MUSHROOM.getType()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isChorusPlant(final Block block) {
        BlockFace[] values;
        for (int length = (values = BlockFace.values()).length, i = 0; i < length; ++i) {
            if (block.getRelative(values[i]).getType() == EnumMaterial.CHORUS_PLANT.getType()) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean hasHangingEntities(final Location location) {
        for (final Entity entity : location.getWorld().getNearbyEntities(location, 1.0, 1.0, 1.0)) {
            if ((entity instanceof ItemFrame || entity instanceof Painting) && location.equals((Object)entity.getLocation().getBlock().getLocation())) {
                return true;
            }
        }
        return false;
    }
}
