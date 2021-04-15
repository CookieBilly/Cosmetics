

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Entity;
import org.bukkit.Material;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.Location;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.Bukkit;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.CuboID;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.FallingBlock;
import org.bukkit.block.Block;
import java.util.ArrayList;

public class GadgetRocket extends Gadget
{
    private boolean activated;
    private ArrayList<Block> blocks;
    private ArrayList<FallingBlock> fallingBlocks;
    private ArmorStand armorStand;
    private boolean launching;
    private boolean enableFlyByDefault;
    
    public GadgetRocket(final UUID uuid) {
        super(uuid, GadgetType.ROCKET);
        this.activated = false;
        this.blocks = new ArrayList<Block>();
        this.fallingBlocks = new ArrayList<FallingBlock>();
        this.launching = false;
        this.enableFlyByDefault = false;
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
        if (!new CuboID(this.getPlayer().getLocation().clone().add(1.0, 0.0, 1.0), this.getPlayer().getLocation().clone().add(-1.0, 150.0, -1.0)).isEmpty() || this.getPlayer().getLocation().clone().add(0.0, 150.0, 0.0).getBlockY() >= 256) {
            this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.activated = true;
        this.getPlayer().setVelocity(new Vector(0, 1, 0));
        final Location clone = this.getPlayer().getLocation().clone();
        clone.setX(clone.getBlockX() + 0.5);
        clone.setY((double)clone.getBlockY());
        clone.setZ(clone.getBlockZ() + 0.5);
        if (!this.getPlayer().getAllowFlight()) {
            this.getPlayer().setAllowFlight(true);
        }
        else {
            this.enableFlyByDefault = true;
        }
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 1; ++i) {
                    final Block block = clone.clone().add(1.0, (double)i, 0.0).getBlock();
                    final Block block2 = clone.clone().add(-1.0, (double)i, 0.0).getBlock();
                    final Block block3 = clone.clone().add(0.0, (double)i, 1.0).getBlock();
                    final Block block4 = clone.clone().add(0.0, (double)i, -1.0).getBlock();
                    block.setType(EnumMaterial.OAK_FENCE.getType());
                    block2.setType(EnumMaterial.OAK_FENCE.getType());
                    block3.setType(EnumMaterial.OAK_FENCE.getType());
                    block4.setType(EnumMaterial.OAK_FENCE.getType());
                    block.setMetadata("CookieGadgets-Rocket", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    block2.setMetadata("CookieGadgets-Rocket", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    block3.setMetadata("CookieGadgets-Rocket", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    block4.setMetadata("CookieGadgets-Rocket", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    block2.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    block3.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    block4.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    GadgetRocket.this.blocks.add(block);
                    GadgetRocket.this.blocks.add(block2);
                    GadgetRocket.this.blocks.add(block3);
                    GadgetRocket.this.blocks.add(block4);
                }
                for (int j = 1; j <= 3; ++j) {
                    final Block block5 = clone.clone().add(0.0, (double)j, 0.0).getBlock();
                    block5.setType(Material.QUARTZ_BLOCK);
                    block5.setMetadata("CookieGadgets-Rocket", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    block5.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    GadgetRocket.this.blocks.add(block5);
                }
                final ArmorStand armorStand = (ArmorStand)clone.getWorld().spawn(clone.add(0.0, 2.5, 0.0), (Class)ArmorStand.class);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                GadgetRocket.access$1(GadgetRocket.this, armorStand);
            }
        }, 10L);
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> {
            this.armorStand.setPassenger((Entity)this.getPlayer());
            new BukkitRunnable() {
                int i;
                
                {
                    this.i = 5;
                }
                
                public void run() {
                    if (!GadgetRocket.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetRocket.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetRocket.this.getPlayer()).getCurrentGadget().getType() != GadgetRocket.this.getType()) {
                        GadgetRocket.this.onClear();
                        this.cancel();
                        return;
                    }
                    if (this.i > 0) {
                        if (this.i > 1) {
                            GadgetRocket.this.getPlayer().sendMessage(ChatUtil.format(MessageType.ROCKET_COUNTDOWN.getFormatMessage().replace("{TIMER}", String.valueOf(this.i)).replace("{SECOND}", MessageType.SECONDS.getFormatMessage())));
                        }
                        else {
                            GadgetRocket.this.getPlayer().sendMessage(ChatUtil.format(MessageType.ROCKET_COUNTDOWN.getFormatMessage().replace("{TIMER}", String.valueOf(this.i)).replace("{SECOND}", MessageType.SECOND.getFormatMessage())));
                        }
                        --this.i;
                    }
                    else {
                        GadgetRocket.this.getPlayer().sendMessage(ChatUtil.format(MessageType.ROCKET_LIFT_OFF.getFormatMessage()));
                        SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(GadgetRocket.this.getPlayer().getLocation());
                        if (GadgetRocket.this.armorStand != null) {
                            GadgetRocket.this.armorStand.remove();
                            GadgetRocket.access$1(GadgetRocket.this, null);
                        }
                        final Iterator<Block> iterator = GadgetRocket.this.blocks.iterator();
                        while (iterator.hasNext()) {
                            iterator.next().setType(Material.AIR);
                        }
                        final FallingBlock spawnFallingBlock = GadgetRocket.this.getPlayer().getWorld().spawnFallingBlock(GadgetRocket.this.getPlayer().getLocation().add(0.0, 4.0, 0.0), Material.QUARTZ_BLOCK, (byte)0);
                        final FallingBlock spawnFallingBlock2 = GadgetRocket.this.getPlayer().getWorld().spawnFallingBlock(GadgetRocket.this.getPlayer().getLocation().add(0.0, 3.0, 0.0), Material.QUARTZ_BLOCK, (byte)0);
                        final FallingBlock spawnFallingBlock3 = GadgetRocket.this.getPlayer().getWorld().spawnFallingBlock(GadgetRocket.this.getPlayer().getLocation().add(0.0, 2.0, 0.0), Material.QUARTZ_BLOCK, (byte)0);
                        spawnFallingBlock.setMetadata("CookieGadgets-Rocket", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                        spawnFallingBlock2.setMetadata("CookieGadgets-Rocket", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                        spawnFallingBlock3.setMetadata("CookieGadgets-Rocket", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                        spawnFallingBlock.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                        spawnFallingBlock2.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                        spawnFallingBlock3.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                        for (int i = 0; i < 2; ++i) {
                            final FallingBlock spawnFallingBlock4 = GadgetRocket.this.getPlayer().getWorld().spawnFallingBlock(GadgetRocket.this.getPlayer().getLocation().add(0.0, (double)(1 + i), 1.0), EnumMaterial.OAK_FENCE.getType(), (byte)0);
                            final FallingBlock spawnFallingBlock5 = GadgetRocket.this.getPlayer().getWorld().spawnFallingBlock(GadgetRocket.this.getPlayer().getLocation().add(0.0, (double)(1 + i), -1.0), EnumMaterial.OAK_FENCE.getType(), (byte)0);
                            final FallingBlock spawnFallingBlock6 = GadgetRocket.this.getPlayer().getWorld().spawnFallingBlock(GadgetRocket.this.getPlayer().getLocation().add(1.0, (double)(1 + i), 0.0), EnumMaterial.OAK_FENCE.getType(), (byte)0);
                            final FallingBlock spawnFallingBlock7 = GadgetRocket.this.getPlayer().getWorld().spawnFallingBlock(GadgetRocket.this.getPlayer().getLocation().add(-1.0, (double)(1 + i), 0.0), EnumMaterial.OAK_FENCE.getType(), (byte)0);
                            GadgetRocket.this.fallingBlocks.add(spawnFallingBlock4);
                            GadgetRocket.this.fallingBlocks.add(spawnFallingBlock5);
                            GadgetRocket.this.fallingBlocks.add(spawnFallingBlock6);
                            GadgetRocket.this.fallingBlocks.add(spawnFallingBlock7);
                            spawnFallingBlock4.setMetadata("CookieGadgets-Rocket", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                            spawnFallingBlock5.setMetadata("CookieGadgets-Rocket", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                            spawnFallingBlock6.setMetadata("CookieGadgets-Rocket", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                            spawnFallingBlock7.setMetadata("CookieGadgets-Rocket", (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                            spawnFallingBlock4.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                            spawnFallingBlock5.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                            spawnFallingBlock6.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                            spawnFallingBlock7.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                        }
                        GadgetRocket.this.fallingBlocks.add(spawnFallingBlock);
                        GadgetRocket.this.fallingBlocks.add(spawnFallingBlock2);
                        GadgetRocket.this.fallingBlocks.add(spawnFallingBlock3);
                        if (GadgetRocket.this.fallingBlocks.get(8).getPassenger() == null) {
                            GadgetRocket.this.fallingBlocks.get(8).setPassenger((Entity)GadgetRocket.this.getPlayer());
                        }
                        spawnFallingBlock.setPassenger((Entity)GadgetRocket.this.getPlayer());
                        GadgetRocket.access$4(GadgetRocket.this, true);
                        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> {
                            if (GadgetRocket.this.activated) {
                                SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(GadgetRocket.this.getPlayer().getLocation());
                                ParticleEffect.EXPLOSION_HUGE.display(GadgetRocket.this.getPlayer().getLocation());
                            }
                            GadgetRocket.this.clearAll();
                            return;
                        }, 160L);
                        this.cancel();
                    }
                }
            }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 20L);
            return;
        }, 12L);
        new BukkitRunnable() {
            public void run() {
                if (!GadgetRocket.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetRocket.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetRocket.this.getPlayer()).getCurrentGadget().getType() != GadgetRocket.this.getType()) {
                    GadgetRocket.this.onClear();
                    this.cancel();
                    return;
                }
                if (!GadgetRocket.this.activated) {
                    this.cancel();
                    return;
                }
                if (GadgetRocket.this.getPlayer().isFlying()) {
                    GadgetRocket.this.getPlayer().setFlying(false);
                }
                if (GadgetRocket.this.armorStand != null) {
                    if (GadgetRocket.this.armorStand.getPassenger() == null) {
                        GadgetRocket.this.armorStand.setPassenger((Entity)GadgetRocket.this.getPlayer());
                    }
                    ParticleEffect.CLOUD.display(GadgetRocket.this.armorStand.getLocation().add(0.0, -3.0, 0.0), 0.3f, 0.2f, 0.3f, 0.2f, 7);
                    ParticleEffect.SMOKE_LARGE.display(GadgetRocket.this.armorStand.getLocation().add(0.0, -3.0, 0.0), 0.3f, 0.2f, 0.3f, 0.2f, 3);
                    SoundEffect.BLOCK_LAVA_EXTINGUISH.playSound(GadgetRocket.this.armorStand.getLocation().add(0.0, -3.0, 0.0), 0.2f, 1.0f);
                }
                final Iterator<FallingBlock> iterator = GadgetRocket.this.fallingBlocks.iterator();
                while (iterator.hasNext()) {
                    iterator.next().setVelocity(new Vector(0.0, 0.8, 0.0));
                }
                if (GadgetRocket.this.activated) {
                    if (GadgetRocket.this.launching) {
                        if (!GadgetRocket.this.fallingBlocks.isEmpty() && GadgetRocket.this.fallingBlocks.get(8).getPassenger() == null) {
                            GadgetRocket.this.fallingBlocks.get(8).setPassenger((Entity)GadgetRocket.this.getPlayer());
                        }
                        ParticleEffect.FLAME.display(GadgetRocket.this.getPlayer().getLocation().add(0.0, -3.0, 0.0), 0.3f, 0.2f, 0.3f, 8);
                        ParticleEffect.LAVA.display(GadgetRocket.this.getPlayer().getLocation().add(0.0, -3.0, 0.0), 0.3f, 0.2f, 0.3f, 8);
                        ParticleEffect.SMOKE_LARGE.display(GadgetRocket.this.getPlayer().getLocation().add(0.0, -3.0, 0.0), 0.3f, 0.2f, 0.3f, 0.2f, 8);
                        SoundEffect.ENTITY_BAT_LOOP.playSound(GadgetRocket.this.getPlayer().getLocation().add(0.0, -3.0, 0.0), 1.0f, 1.0f);
                        SoundEffect.BLOCK_LAVA_EXTINGUISH.playSound(GadgetRocket.this.getPlayer().getLocation().add(0.0, -3.0, 0.0), 0.025f, 1.0f);
                    }
                }
                else {
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
        for (final Block block : this.blocks) {
            block.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
            block.setType(Material.AIR);
        }
        this.blocks.clear();
        for (final FallingBlock fallingBlock : this.fallingBlocks) {
            fallingBlock.removeMetadata(CookieGadgets.getInstance().getPluginName(), (Plugin)CookieGadgets.getInstance());
            fallingBlock.remove();
        }
        this.fallingBlocks.clear();
        if (this.armorStand != null) {
            this.armorStand.remove();
            this.armorStand.setPassenger((Entity)null);
        }
        this.armorStand = null;
        CookieGadgets.getPlayerManager(this.getPlayer()).disableFallDamage();
        if (!this.enableFlyByDefault && this.activated) {
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                this.getPlayer().setAllowFlight(false);
                if (this.getPlayer().isFlying()) {
                    this.getPlayer().setFlying(false);
                }
                return;
            });
        }
        this.activated = false;
        this.launching = false;
        this.enableFlyByDefault = false;
    }
    
    static /* synthetic */ void access$1(final GadgetRocket gadgetRocket, final ArmorStand armorStand) {
        gadgetRocket.armorStand = armorStand;
    }
    
    static /* synthetic */ void access$4(final GadgetRocket gadgetRocket, final boolean launching) {
        gadgetRocket.launching = launching;
    }
}
