

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import org.bukkit.Bukkit;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.CookieGadgets;
import java.util.Iterator;
import org.bukkit.entity.Painting;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.CuboID;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.Location;
import java.util.HashMap;
import org.bukkit.block.Block;
import java.util.ArrayList;

public class GadgetKawarimiNoJutsu extends Gadget
{
    private static int maxRadius;
    private boolean activated;
    private ArrayList<Block> blocks;
    private HashMap<Location, String> groundBlock;
    
    static {
        GadgetKawarimiNoJutsu.maxRadius = 8;
    }
    
    public GadgetKawarimiNoJutsu(final UUID uuid) {
        super(uuid, GadgetType.KAWARIMI_NO_JUTSU);
        this.activated = false;
        this.blocks = new ArrayList<Block>();
        this.groundBlock = new HashMap<Location, String>();
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        final Location clone = this.getPlayer().getLocation().clone();
        final CuboID cuboID = new CuboID(clone, this.getPlayer().getLocation().clone().add(0.0, 2.0, 0.0));
        if (!cuboID.isEmpty()) {
            this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
            return false;
        }
        for (final Entity entity : this.getPlayer().getWorld().getNearbyEntities(clone, 1.0, 3.0, 1.0)) {
            if ((entity instanceof ItemFrame || entity instanceof Painting) && cuboID.contains(entity.getLocation())) {
                this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
                return false;
            }
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.activated = true;
        final Location clone = this.getPlayer().getLocation().clone();
        final Location clone2 = this.getPlayer().getLocation().clone();
        clone2.setX(this.getPlayer().getLocation().getX() + Math.random() * GadgetKawarimiNoJutsu.maxRadius * 2.0 - GadgetKawarimiNoJutsu.maxRadius);
        clone2.setZ(this.getPlayer().getLocation().getZ() + Math.random() * GadgetKawarimiNoJutsu.maxRadius * 2.0 - GadgetKawarimiNoJutsu.maxRadius);
        clone2.setY((double)this.getPlayer().getLocation().getWorld().getHighestBlockAt(clone2.getBlockX(), clone2.getBlockZ()).getY());
        this.getPlayer().teleport(clone2);
        CookieGadgets.getPlayerManager(this.getPlayer()).disableFallDamage();
        SoundEffect.ENTITY_ENDERMAN_TELEPORT.playSound(this.getPlayer().getLocation());
        this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 120, 0));
        this.setToRestore(this.getPlayer(), clone.clone().add(0.0, -1.0, 0.0).getBlock(), 200);
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                if (!GadgetKawarimiNoJutsu.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetKawarimiNoJutsu.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetKawarimiNoJutsu.this.getPlayer()).getCurrentGadget().getType() != GadgetKawarimiNoJutsu.this.getType()) {
                    this.step = 40;
                    GadgetKawarimiNoJutsu.this.onClear();
                    this.cancel();
                    return;
                }
                ++this.step;
                if (this.step <= 40) {
                    for (int i = 0; i <= 2; ++i) {
                        final Block block = clone.clone().add(0.0, (double)i, 0.0).getBlock();
                        if (block.getType() != EnumMaterial.OAK_LOG.getType()) {
                            block.setType(EnumMaterial.OAK_LOG.getType());
                            block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                            GadgetKawarimiNoJutsu.this.blocks.add(block);
                            ParticleEffect.SMOKE_LARGE.display(block.getLocation());
                        }
                    }
                }
                else {
                    GadgetKawarimiNoJutsu.this.clearAll();
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
            block.setType(Material.AIR);
            block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
        }
        this.blocks.clear();
        this.forceRestore();
        if (this.getPlayer().isOnGround()) {
            CookieGadgets.getPlayerManager(this.getPlayer()).enableFallDamage();
        }
        this.activated = false;
    }
    
    private void forceRestore() {
        if (this.groundBlock.isEmpty()) {
            return;
        }
        for (final Location location : this.groundBlock.keySet()) {
            final Block block = location.getBlock();
            if (VersionManager.is1_13OrAbove()) {
                try {
                    block.setBlockData(Bukkit.getServer().createBlockData((String)this.groundBlock.get(location)));
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            else {
                final String s = this.groundBlock.get(location);
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
        if (!this.groundBlock.containsKey(location)) {
            return;
        }
        final Block block = location.getBlock();
        if (VersionManager.is1_13OrAbove()) {
            try {
                block.setBlockData(Bukkit.getServer().createBlockData((String)this.groundBlock.get(location)));
            }
            catch (NoSuchMethodError noSuchMethodError) {}
        }
        else {
            final String s = this.groundBlock.get(location);
            final Material value = Material.valueOf(s.split(",")[0]);
            final byte byteValue = Byte.valueOf(s.split(",")[1]);
            block.setType(value);
            ReflectionUtils.setData(block, byteValue);
        }
        block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
        this.groundBlock.remove(location);
    }
    
    private void setToRestore(final Player player, final Block block, final int n) {
        if (this.groundBlock.containsKey(block.getLocation())) {
            return;
        }
        if (!this.groundBlock.containsKey(block.getLocation())) {
            if (VersionManager.is1_13OrAbove()) {
                try {
                    this.groundBlock.put(block.getLocation(), block.getBlockData().getAsString());
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            else {
                try {
                    this.groundBlock.put(block.getLocation(), String.valueOf(block.getType().toString()) + "," + block.getData());
                }
                catch (NoSuchMethodError noSuchMethodError2) {}
            }
            block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
            Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    GadgetKawarimiNoJutsu.this.restoreBlock(player, block.getLocation());
                }
            }, (long)n);
        }
    }
}
