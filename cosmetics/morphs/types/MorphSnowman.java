

package ws.billy.CookieGadgets.cosmetics.morphs.types;

import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.worldguard.WorldGuardUtils;
import ws.billy.CookieGadgets.utils.worldguard.BlacklistedRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.util.Vector;
import org.bukkit.entity.Snowball;
import ws.billy.CookieGadgets.cosmetics.morphs.MorphType;
import java.util.UUID;
import org.bukkit.Location;
import java.util.HashMap;

public class MorphSnowman extends Morph
{
    private HashMap<Location, String> snowBlocks;
    
    public MorphSnowman(final UUID uuid) {
        super(uuid, MorphType.SNOWMAN);
        this.snowBlocks = new HashMap<Location, String>();
    }
    
    public void onClick() {
        final Snowball snowball = (Snowball)this.getPlayer().launchProjectile((Class)Snowball.class, this.getPlayer().getEyeLocation().getDirection().multiply(1.25).add(new Vector(0.0, 0.1, 0.0)));
        snowball.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        SoundEffect.ENTITY_SNOWBALL_THROW.playSound(snowball.getLocation());
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (this.step >= 100) {
                    this.cancel();
                }
                if (snowball.isDead()) {
                    for (int i = 1; i <= 15; ++i) {
                        final EnumMaterial value = EnumMaterial.valueOf(351, CookieGadgets.random().nextInt(15));
                        ParticleEffect.ITEM_CRACK.display(new ParticleEffect.ItemData(value, value.getData()), snowball.getLocation().add(0.0, 0.7, 0.0), 0.5f, 0.5f, 0.5f);
                    }
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 1L, 5L);
    }
    
    public void onUpdate() {
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        this.forceRestore();
    }
    
    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent playerMoveEvent) {
        if (playerMoveEvent.getPlayer() == this.getPlayer()) {
            final Block block = this.getPlayer().getLocation().getBlock();
            final Block block2 = this.getPlayer().getLocation().add(0.0, -1.0, 0.0).getBlock();
            if (block.isEmpty() && !block2.isEmpty() && !block2.isLiquid() && this.getPlayer().isOnGround()) {
                this.setToRestore(this.getPlayer(), block, EnumMaterial.SNOW, 60);
            }
        }
    }
    
    private void setToRestore(final Player player, final Block block, final EnumMaterial enumMaterial, final int n) {
        if (this.snowBlocks.containsKey(block.getLocation())) {
            return;
        }
        if (!block.hasMetadata(CookieGadgets.getInstance().getPluginName()) && !this.snowBlocks.containsKey(block.getLocation())) {
            if (WorldGuardUtils.isInBlacklistedRegion(block.getLocation(), BlacklistedRegion.MORPHS)) {
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
                    MorphSnowman.this.restoreBlock(player, block.getLocation());
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
