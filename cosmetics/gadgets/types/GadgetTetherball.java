

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import java.util.Set;
import ws.billy.CookieGadgets.utils.CuboID;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.LeashHitch;
import org.bukkit.Location;
import org.bukkit.entity.Chicken;
import org.bukkit.block.Block;
import java.util.ArrayList;

public class GadgetTetherball extends Gadget
{
    private boolean activated;
    private ArrayList<Block> blocks;
    private Chicken chicken;
    private Location targetBlockLocation;
    private LeashHitch chickenLeash;
    
    public GadgetTetherball(final UUID uuid) {
        super(uuid, GadgetType.TETHERBALL);
        this.activated = false;
        this.blocks = new ArrayList<Block>();
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        final Location location = BlockUtil.getTargetBlock(this.getPlayer(), 7).getLocation();
        if (location.getBlock().isEmpty()) {
            this.getPlayer().sendMessage(MessageType.TARGET_A_BLOCK.getFormatMessage());
            return false;
        }
        final CuboID cuboID = new CuboID(location.clone().add(0.0, 1.0, 0.0), location.clone().add(0.0, 7.0, 0.0));
        if (cuboID.isEmpty() && this.getPlayer().getLocation().add(0.0, 7.0, 0.0).getBlockY() < 256 && !cuboID.hasHangingEntities()) {
            this.targetBlockLocation = location.clone().add(0.0, 1.0, 0.0);
            return super.checkRequirements();
        }
        final Location location2 = this.getPlayer().getLastTwoTargetBlocks((Set)null, 7).get(0).getLocation();
        final CuboID cuboID2 = new CuboID(location2.clone(), location2.clone().add(0.0, 7.0, 0.0));
        if (cuboID2.isEmpty() && this.getPlayer().getLocation().add(0.0, 7.0, 0.0).getBlockY() < 256 && !cuboID2.hasHangingEntities()) {
            this.targetBlockLocation = location2;
            return true;
        }
        this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
        return false;
    }
    
    @Override
    public void onClick() {
        for (int i = 0; i <= 7; ++i) {
            final Block block = this.targetBlockLocation.clone().add(0.0, (double)i, 0.0).getBlock();
            block.setType(EnumMaterial.OAK_FENCE.getType());
            block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
            this.blocks.add(block);
        }
        CookieGadgets.setBypassCreatureSpawn(true);
        final Chicken chicken = (Chicken)this.getPlayer().getWorld().spawnEntity(this.getPlayer().getLocation().add(MathUtil.randomDouble(0.0, 0.5), 3.0, MathUtil.randomDouble(0.0, 0.5)), EntityType.CHICKEN);
        final LeashHitch leashHitch = (LeashHitch)this.getPlayer().getWorld().spawnEntity(this.blocks.get(this.blocks.size() - 1).getLocation(), EntityType.LEASH_HITCH);
        leashHitch.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        chicken.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        chicken.setMetadata("Tetherball-Chicken", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        chicken.setMaxHealth(28.0);
        chicken.setHealth(28.0);
        chicken.setLeashHolder((Entity)leashHitch);
        this.chickenLeash = leashHitch;
        this.chicken = chicken;
        CookieGadgets.setBypassCreatureSpawn(false);
        this.activated = true;
        new BukkitRunnable() {
            public void run() {
                if (!GadgetTetherball.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetTetherball.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetTetherball.this.getPlayer()).getCurrentGadget().getType() != GadgetTetherball.this.getType()) {
                    GadgetTetherball.this.onClear();
                    this.cancel();
                    return;
                }
                GadgetTetherball.this.clearAll();
            }
        }.runTaskLater((Plugin)CookieGadgets.getInstance(), 300L);
    }
    
    @Override
    public void onUpdate() {
        if (this.activated && this.chicken != null && this.chicken.getHealth() < 20.0) {
            this.chicken.setHealth(28.0);
            MathUtil.applyVelocity((Entity)this.chicken, new Vector(0.0, 0.1, 0.0));
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        for (final Block block : this.blocks) {
            block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
            if (block.getType() == EnumMaterial.OAK_FENCE.getType()) {
                block.setType(Material.AIR);
            }
        }
        if (this.chickenLeash != null && this.chickenLeash.isValid()) {
            this.chickenLeash.remove();
        }
        if (this.chicken != null) {
            this.chicken.remove();
            this.chicken.setLeashHolder((Entity)null);
        }
        this.chicken = null;
        this.chickenLeash = null;
        this.activated = false;
    }
}
