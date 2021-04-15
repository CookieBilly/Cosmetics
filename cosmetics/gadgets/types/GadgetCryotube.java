

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import ws.billy.CookieGadgets.utils.ReflectionUtils;
import org.bukkit.Bukkit;
import java.util.Iterator;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.CuboID;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.Location;
import java.util.HashMap;
import org.bukkit.block.Block;
import java.util.ArrayList;

public class GadgetCryotube extends Gadget
{
    private boolean activated;
    private ArrayList<Block> blocks;
    private HashMap<Location, String> floorBlocks;
    
    public GadgetCryotube(final UUID uuid) {
        super(uuid, GadgetType.CRYOTUBE);
        this.activated = false;
        this.blocks = new ArrayList<Block>();
        this.floorBlocks = new HashMap<Location, String>();
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
        final CuboID cuboID = new CuboID(this.getPlayer().getLocation().clone().add(0.0, 0.0, 0.0), this.getPlayer().getLocation().clone().add(0.0, 2.0, 0.0));
        if (!cuboID.isEmpty() || cuboID.hasHangingEntities()) {
            this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.activated = true;
        final Location location = this.getPlayer().getLocation();
        final Location location2 = location.clone().getBlock().getLocation();
        this.getPlayer().teleport(location2.clone().add(0.5, 0.0, 0.5).setDirection(this.getPlayer().getLocation().getDirection()));
        final Block block = location.clone().subtract(0.0, 1.0, 0.0).getBlock();
        if (VersionManager.is1_13OrAbove()) {
            try {
                this.floorBlocks.put(block.getLocation(), block.getBlockData().getAsString());
            }
            catch (NoSuchMethodError noSuchMethodError) {}
        }
        else {
            try {
                this.floorBlocks.put(block.getLocation(), String.valueOf(block.getType().toString()) + "," + block.getData());
            }
            catch (NoSuchMethodError noSuchMethodError2) {}
        }
        block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                if (!GadgetCryotube.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetCryotube.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetCryotube.this.getPlayer()).getCurrentGadget().getType() != GadgetCryotube.this.getType()) {
                    this.step = 40;
                    GadgetCryotube.this.onClear();
                    this.cancel();
                    return;
                }
                ++this.step;
                if (this.step <= 40) {
                    ParticleEffect.SPELL_INSTANT.display(location2.clone().add(0.5, 2.0, 0.5), 1.0f, 10);
                    ParticleEffect.FLAME.display(location2.clone().add(0.5, 2.0, 0.5), 0.0f, 10);
                    ParticleEffect.SNOWBALL.display(location2.clone().add(0.5, 2.0, 0.5), 0.0f, 10);
                    for (int i = 0; i <= 1; ++i) {
                        final Block block = location.clone().add(0.0, (double)i, 0.0).getBlock();
                        if (block.getType() != EnumMaterial.ICE.getType()) {
                            block.setType(EnumMaterial.ICE.getType());
                            block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                            GadgetCryotube.this.blocks.add(block);
                        }
                    }
                    final Block block2 = location.clone().add(0.0, 2.0, 0.0).getBlock();
                    if (block2.getType() != EnumMaterial.STONE_SLAB.getType()) {
                        block2.setType(EnumMaterial.STONE_SLAB.getType());
                        block2.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                        GadgetCryotube.this.blocks.add(block2);
                    }
                    CookieGadgets.getPlayerManager(GadgetCryotube.this.getPlayer()).disableBlockDamage();
                }
                else {
                    GadgetCryotube.this.clearAll();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 5L);
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
        for (final Block block : this.blocks) {
            block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
            block.setType(Material.AIR);
        }
        this.blocks.clear();
        CookieGadgets.getPlayerManager(this.getPlayer()).enableBlockDamage();
        this.forceRestore();
        this.activated = false;
    }
    
    private void forceRestore() {
        if (this.floorBlocks.isEmpty()) {
            return;
        }
        for (final Location location : this.floorBlocks.keySet()) {
            final Block block = location.getBlock();
            if (VersionManager.is1_13OrAbove()) {
                try {
                    block.setBlockData(Bukkit.getServer().createBlockData((String)this.floorBlocks.get(location)));
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            else {
                final String s = this.floorBlocks.get(location);
                final Material value = Material.valueOf(s.split(",")[0]);
                final byte byteValue = Byte.valueOf(s.split(",")[1]);
                block.setType(value);
                ReflectionUtils.setData(block, byteValue);
            }
            block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
        }
        this.floorBlocks.clear();
    }
}
