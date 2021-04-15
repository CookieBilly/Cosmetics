

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.block.data.BlockData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import ws.billy.CookieGadgets.utils.BlockUtil;
import org.bukkit.block.data.Directional;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.block.BlockFace;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.EntityUtils;
import org.bukkit.block.Block;
import org.bukkit.Material;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import java.util.HashMap;
import ws.billy.CookieGadgets.utils.CuboID;
import org.bukkit.Location;

public class GadgetTrampoline extends Gadget
{
    private boolean activated;
    private Location location;
    private CuboID cuboid;
    private HashMap<Location, String> blocks;
    
    public GadgetTrampoline(final UUID uuid) {
        super(uuid, GadgetType.TRAMPOLINE);
        this.activated = false;
        this.blocks = new HashMap<Location, String>();
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
        final Location add = this.getPlayer().getLocation().clone().add(-3.0, 0.0, -3.0);
        final Location add2 = this.getPlayer().getLocation().clone().add(3.0, 20.0, 3.0);
        final Block relative = this.getPlayer().getLocation().clone().getBlock().getRelative(-4, 1, 0);
        final Block relative2 = this.getPlayer().getLocation().clone().getBlock().getRelative(-5, 0, 0);
        this.cuboid = new CuboID(add, add2);
        if (!this.cuboid.isEmpty() || relative.getType() != Material.AIR || relative2.getType() != Material.AIR) {
            this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.location = this.getPlayer().getLocation().clone();
        this.getPlayer().teleport(this.getPlayer().getLocation().clone().add(0.0, 5.0, 0.0));
        this.genetateTrampoline();
        for (final Entity entity : EntityUtils.getNearbyLivingEntities(this.location, 3.0)) {
            entity.teleport(entity.getLocation().clone().add(0.0, 2.0, 0.0));
        }
        new BukkitRunnable() {
            public void run() {
                if (!GadgetTrampoline.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetTrampoline.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetTrampoline.this.getPlayer()).getCurrentGadget().getType() != GadgetTrampoline.this.getType() || !GadgetTrampoline.this.activated) {
                    return;
                }
                GadgetTrampoline.this.clearAll();
            }
        }.runTaskLater((Plugin)CookieGadgets.getInstance(), 300L);
        this.activated = true;
    }
    
    @Override
    public void onUpdate() {
        if (this.activated) {
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    for (final Entity entity : EntityUtils.getNearbyLivingEntities(GadgetTrampoline.this.location, 4.0)) {
                        final Block relative = entity.getLocation().getBlock().getRelative(BlockFace.DOWN);
                        if (relative.getType() == EnumMaterial.BLACK_WOOL.getType() && relative.getData() == 15 && GadgetTrampoline.this.cuboid.contains(relative)) {
                            MathUtil.applyVelocity(entity, new Vector(0, 3, 0));
                        }
                    }
                }
            });
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        final Iterator<Location> iterator = this.blocks.keySet().iterator();
        while (iterator.hasNext()) {
            final Block block = iterator.next().clone().getBlock();
            block.setType(Material.AIR);
            block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
        }
        this.blocks.clear();
        this.activated = false;
    }
    
    private void genetateTrampoline() {
        this.setBlock(this.getLocation(3, 0, 3), EnumMaterial.OAK_FENCE, false, (byte)0);
        this.setBlock(this.getLocation(-3, 0, 3), EnumMaterial.OAK_FENCE, false, (byte)0);
        this.setBlock(this.getLocation(3, 0, -3), EnumMaterial.OAK_FENCE, false, (byte)0);
        this.setBlock(this.getLocation(-3, 0, -3), EnumMaterial.OAK_FENCE, false, (byte)0);
        this.setBlock(this.getLocation(3, 1, 3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(2, 1, 3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(1, 1, 3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(0, 1, 3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(-1, 1, 3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(-2, 1, 3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(-3, 1, 3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(3, 1, 2), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(3, 1, 1), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(3, 1, 0), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(3, 1, -1), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(3, 1, -2), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(-3, 1, 2), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(-3, 1, 1), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(-3, 1, 0), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(-3, 1, -1), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(-3, 1, -2), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(3, 1, -3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(2, 1, -3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(1, 1, -3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(0, 1, -3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(-1, 1, -3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(-2, 1, -3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(-3, 1, -3), EnumMaterial.BLUE_WOOL, false, (byte)11);
        this.setBlock(this.getLocation(2, 1, 2), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(1, 1, 2), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(0, 1, 2), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(-1, 1, 2), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(-2, 1, 2), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(2, 1, 1), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(1, 1, 1), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(0, 1, 1), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(-1, 1, 1), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(-2, 1, 1), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(2, 1, 0), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(1, 1, 0), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(0, 1, 0), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(-1, 1, 0), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(-2, 1, 0), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(2, 1, -1), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(1, 1, -1), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(0, 1, -1), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(-1, 1, -1), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(-2, 1, -1), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(2, 1, -2), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(1, 1, -2), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(0, 1, -2), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(-1, 1, -2), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(-2, 1, -2), EnumMaterial.BLACK_WOOL, false, (byte)15);
        this.setBlock(this.getLocation(-4, 1, 0), EnumMaterial.OAK_STAIRS, true, (byte)0);
        this.setBlock(this.getLocation(-5, 0, 0), EnumMaterial.OAK_STAIRS, true, (byte)0);
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
            block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        }
    }
    
    private Block getLocation(final int n, final int n2, final int n3) {
        return this.location.getBlock().getRelative(n, n2, n3);
    }
}
