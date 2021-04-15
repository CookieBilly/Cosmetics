

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import java.util.Iterator;
import org.bukkit.Material;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.block.Block;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.Location;
import java.util.HashMap;

public class GadgetLetItSnow extends Gadget
{
    private boolean activated;
    private HashMap<Location, String> blocks;
    
    public GadgetLetItSnow(final UUID uuid) {
        super(uuid, GadgetType.LET_IT_SNOW);
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
        SoundEffect.ENTITY_ZOMBIE_VILLAGER_CURE.playSound(this.getPlayer());
        this.activated = true;
        new BukkitRunnable() {
            int step = 0;
            int step2 = 25;
            
            public void run() {
                ++this.step;
                ++this.step2;
                if (!GadgetLetItSnow.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetLetItSnow.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetLetItSnow.this.getPlayer()).getCurrentGadget().getType() != GadgetLetItSnow.this.getType()) {
                    this.step = 200;
                    GadgetLetItSnow.this.onClear();
                    this.cancel();
                    return;
                }
                if (this.step <= 200) {
                    ParticleEffect.SNOW_SHOVEL.display(GadgetLetItSnow.this.getPlayer().getLocation().clone().add(0.0, 3.0, 0.0), 2.5f, 2.5f, 2.5f, 10);
                    if (this.step2 == 30) {
                        SoundEffect.WEATHER_RAIN_ABOVE.playSound(GadgetLetItSnow.this.getPlayer());
                        this.step2 = 0;
                    }
                    for (final Block block : BlockUtil.getBlocksInRadiusXZ(GadgetLetItSnow.this.getPlayer().getLocation(), 3, false)) {
                        if (block.isLiquid()) {
                            if (VersionManager.is1_13OrAbove() && !block.getBlockData().getAsString().contains("level=0")) {
                                continue;
                            }
                            EnumMaterial enumMaterial = EnumMaterial.ICE;
                            if (block.getType() == Material.LAVA || block.getType().toString().toLowerCase().contains("_LAVA")) {
                                enumMaterial = EnumMaterial.OBSIDIAN;
                            }
                            GadgetLetItSnow.this.setToRestore(GadgetLetItSnow.this.getPlayer(), block, enumMaterial, 80);
                        }
                    }
                }
                else {
                    GadgetLetItSnow.this.clearAll();
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
    
    private void setToRestore(final Player player, final Block block, final EnumMaterial enumMaterial, final int n) {
        if (this.blocks.containsKey(block.getLocation())) {
            return;
        }
        if (block.isLiquid() && !this.blocks.containsKey(block.getLocation()) && !block.hasMetadata(CookieGadgets.getInstance().getPluginName())) {
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
            ParticleEffect.BLOCK_CRACK.display(new ParticleEffect.BlockData(enumMaterial, (byte)0), block.getLocation().clone().add(0.0, 0.8, 0.0), 0.15f, 0.15f, 0.15f, 1.0f, 3);
            ParticleEffect.SNOWBALL.display(block.getLocation(), 0.7f, 0.7f, 0.7f, 1.0f, 10);
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    GadgetLetItSnow.this.restoreBlock(player, block.getLocation());
                }
            }, (long)n);
        }
    }
}
