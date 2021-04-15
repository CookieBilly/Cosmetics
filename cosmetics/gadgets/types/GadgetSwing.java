

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import ws.billy.CookieGadgets.utils.ReflectionUtils;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ArmorStand;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.block.Block;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.ChatUtil;
import ws.billy.CookieGadgets.utils.SoundEffect;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import ws.billy.CookieGadgets.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.scheduler.BukkitRunnable;
import ws.billy.CookieGadgets.utils.CuboID;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.List;
import java.util.HashMap;
import org.bukkit.Location;

public class GadgetSwing extends Gadget
{
    private boolean activated;
    private Location location;
    private HashMap<Location, String> blocks;
    private static List<String> messages;
    
    static {
        GadgetSwing.messages = FileManager.getGadgetsFile().getStringList("Gadgets.Fun And Games.Types.Swing.Messages");
    }
    
    public GadgetSwing(final UUID uuid) {
        super(uuid, GadgetType.BAT_LAUNCHER);
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
        if (!new CuboID(this.getPlayer().getLocation().clone().add(0.0, 0.0, -1.0), this.getPlayer().getLocation().clone().add(0.0, 4.0, 1.0)).isEmpty()) {
            this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.location = this.getPlayer().getLocation().clone();
        final Location add = this.getPlayer().getLocation().getBlock().getLocation().clone().add(0.5, 5.0, 0.5);
        add.setYaw(-90.0f);
        this.getPlayer().teleport(add);
        this.genetateSwing();
        new BukkitRunnable() {
            public void run() {
                if (!GadgetSwing.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetSwing.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetSwing.this.getPlayer()).getCurrentGadget().getType() != GadgetSwing.this.getType() || !GadgetSwing.this.activated) {
                    return;
                }
                GadgetSwing.this.clearAll();
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
                    for (final Player player : PlayerUtils.getNearbyPlayers(GadgetSwing.this.getLocation(2, 4, 0).getLocation(), 2.0)) {
                        final Block relative = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
                        if (relative.getType() == EnumMaterial.OAK_SLAB.getType() && relative.getData() == 0) {
                            MathUtil.applyVelocity((Entity)player, new Vector(0.3, 1.8, 0.0));
                            SoundEffect.BLOCK_WOODEN_TRAPDOOR_OPEN.playSound(relative.getLocation());
                            if (!CookieGadgets.random().nextBoolean()) {
                                continue;
                            }
                            player.sendMessage(ChatUtil.format(GadgetSwing.messages.get(CookieGadgets.random().nextInt(GadgetSwing.messages.size()))));
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
    
    private void genetateSwing() {
        this.setBlock(this.getLocation(0, 0, -1), EnumMaterial.OAK_FENCE, (byte)0);
        this.setBlock(this.getLocation(0, 1, -1), EnumMaterial.OAK_FENCE, (byte)0);
        this.setBlock(this.getLocation(0, 2, -1), EnumMaterial.OAK_FENCE, (byte)0);
        this.setBlock(this.getLocation(0, 0, 1), EnumMaterial.OAK_FENCE, (byte)0);
        this.setBlock(this.getLocation(0, 1, 1), EnumMaterial.OAK_FENCE, (byte)0);
        this.setBlock(this.getLocation(0, 2, 1), EnumMaterial.OAK_FENCE, (byte)0);
        final ArmorStand armorStand = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.location.clone().add(0.5, 0.0, 0.5), EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setSmall(true);
        armorStand.setArms(false);
        armorStand.setBasePlate(false);
        if (VersionManager.is1_9OrAbove()) {
            armorStand.setSilent(true);
        }
        armorStand.setHelmet(new ItemStack(EnumMaterial.OAK_SLAB.getType()));
        armorStand.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
    }
    
    private void setBlock(final Block block, final EnumMaterial enumMaterial, final byte b) {
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
        block.setType(enumMaterial.getType());
        ReflectionUtils.setData(block, b);
        block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
    }
    
    private Block getLocation(final int n, final int n2, final int n3) {
        return this.location.getBlock().getRelative(n, n2, n3);
    }
}
