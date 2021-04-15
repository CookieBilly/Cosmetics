

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import ws.billy.CookieGadgets.utils.ReflectionUtils;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerTeleportEvent;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.block.Block;
import ws.billy.CookieGadgets.utils.CuboID;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.Location;
import java.util.HashMap;
import org.bukkit.entity.ArmorStand;
import java.util.ArrayList;

public class GadgetSandCastle extends Gadget
{
    private boolean activated;
    private ArrayList<ArmorStand> armorstands;
    private HashMap<Location, String> blocks;
    private Location centerLocation;
    private double lY;
    private static double distance;
    private static double locY;
    
    static {
        GadgetSandCastle.distance = 0.435;
        GadgetSandCastle.locY = -2.0;
    }
    
    public GadgetSandCastle(final UUID uuid) {
        super(uuid, GadgetType.SAND_CASTLE);
        this.activated = false;
        this.armorstands = new ArrayList<ArmorStand>();
        this.blocks = new HashMap<Location, String>();
        this.lY = 0.0;
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
        final Location add = this.getPlayer().getLocation().clone().add(1.0, 0.0, 0.0);
        final Location add2 = this.getPlayer().getLocation().clone().add(3.0, 2.0, -3.0);
        final Location add3 = this.getPlayer().getLocation().clone().add(1.0, -1.0, 0.0);
        final Location add4 = this.getPlayer().getLocation().clone().add(3.0, -1.0, -3.0);
        final Location add5 = this.getPlayer().getLocation().clone().add(1.0, -2.0, 0.0);
        final Location add6 = this.getPlayer().getLocation().clone().add(3.0, -2.0, -3.0);
        final CuboID cuboID = new CuboID(add, add2);
        final CuboID cuboID2 = new CuboID(add3, add4);
        final CuboID cuboID3 = new CuboID(add5, add6);
        if (!cuboID.isEmpty()) {
            this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
            return false;
        }
        for (final Block block : cuboID2.getBlocks()) {
            if (block.hasMetadata(CookieGadgets.getInstance().getPluginName())) {
                this.getPlayer().sendMessage(MessageType.GADGET_ACTIVATED_IN_SAME_AREA.getFormatMessage());
                return false;
            }
            if (block.isLiquid() || block.getType() == EnumMaterial.ATTACHED_MELON_STEM.getType() || block.getType() == EnumMaterial.ATTACHED_PUMPKIN_STEM.getType() || block.getType().toString().toLowerCase().contains("banner") || block.getType() == EnumMaterial.BREWING_STAND.getType() || block.getType() == EnumMaterial.BROWN_MUSHROOM.getType() || block.getType() == EnumMaterial.CACTUS.getType() || block.getType() == EnumMaterial.CAKE.getType() || block.getType().toString().toLowerCase().contains("cake_block") || block.getType() == EnumMaterial.CARROTS.getType() || block.getType().toString().toLowerCase().contains("carpet") || block.getType() == EnumMaterial.CHEST.getType() || block.getType() == EnumMaterial.COBWEB.getType() || block.getType().toString().toLowerCase().contains("command") || block.getType().toString().toLowerCase().contains("crops") || block.getType() == EnumMaterial.DANDELION.getType() || block.getType() == EnumMaterial.DEAD_BUSH.getType() || block.getType().toString().toLowerCase().contains("double_plant") || block.getType() == EnumMaterial.FARMLAND.getType() || block.getType() == EnumMaterial.FERN.getType() || block.getType() == EnumMaterial.FIRE.getType() || block.getType() == EnumMaterial.FLOWER_POT.getType() || block.getType().toString().toLowerCase().contains("flower_pot") || block.getType() == EnumMaterial.GRASS.getType() || block.getType().toString().toLowerCase().contains("head") || block.getType() == EnumMaterial.LADDER.getType() || block.getType() == EnumMaterial.LARGE_FERN.getType() || block.getType() == EnumMaterial.LEVER.getType() || block.getType() == EnumMaterial.LILAC.getType() || block.getType() == EnumMaterial.LILY_PAD.getType() || block.getType().toString().toLowerCase().contains("long_grass") || block.getType() == EnumMaterial.MELON_STEM.getType() || block.getType() == EnumMaterial.MUSHROOM_STEM.getType() || block.getType() == EnumMaterial.NETHER_WART.getType() || block.getType() == EnumMaterial.PEONY.getType() || block.getType().toString().toLowerCase().contains("plate") || block.getType() == EnumMaterial.POTATOES.getType() || block.getType().toString().toLowerCase().contains("potted_") || block.getType() == EnumMaterial.PUMPKIN_STEM.getType() || block.getType().toString().toLowerCase().contains("redstone_comparator") || block.getType().toString().toLowerCase().contains("redstone_lamp") || block.getType().toString().toLowerCase().contains("redstone_torch") || block.getType() == EnumMaterial.REDSTONE_WIRE.getType() || block.getType() == EnumMaterial.REPEATER.getType() || block.getType() == EnumMaterial.RED_MUSHROOM.getType() || block.getType().toString().toLowerCase().contains("red_rose") || block.getType() == EnumMaterial.ROSE_BUSH.getType() || block.getType().toString().toLowerCase().contains("sapling") || block.getType().toString().toLowerCase().contains("seeds") || block.getType().toString().toLowerCase().contains("sign") || block.getType().toString().toLowerCase().contains("shulker_box") || block.getType().toString().toLowerCase().contains("skull") || block.getType() == EnumMaterial.SNOW.getType() || block.getType() == EnumMaterial.SUGAR_CANE.getType() || block.getType().toString().toLowerCase().contains("sugar_cane_block") || block.getType() == EnumMaterial.SUNFLOWER.getType() || block.getType() == EnumMaterial.TALL_GRASS.getType() || block.getType() == EnumMaterial.TORCH.getType() || block.getType() == EnumMaterial.TRIPWIRE.getType() || block.getType() == EnumMaterial.TRIPWIRE_HOOK.getType() || block.getType().toString().toLowerCase().contains("tulip") || block.getType() == EnumMaterial.VINE.getType() || block.getType() == EnumMaterial.WHEAT.getType() || block.getType().toString().toLowerCase().contains("_button") || BlockUtil.hasHangingEntities(block.getLocation()) || BlockUtil.isCocoaBlock(block)) {
                this.getPlayer().sendMessage(MessageType.NOT_ON_FLAT_GROUND.getFormatMessage());
                return false;
            }
        }
        for (final Block block2 : cuboID3.getBlocks()) {
            if (block2.isLiquid() || block2.isEmpty() || block2.getType() == EnumMaterial.ATTACHED_MELON_STEM.getType() || block2.getType() == EnumMaterial.ATTACHED_PUMPKIN_STEM.getType() || block2.getType().toString().toLowerCase().contains("banner") || block2.getType() == EnumMaterial.BREWING_STAND.getType() || block2.getType() == EnumMaterial.BROWN_MUSHROOM.getType() || block2.getType() == EnumMaterial.CACTUS.getType() || block2.getType() == EnumMaterial.CAKE.getType() || block2.getType().toString().toLowerCase().contains("cake_block") || block2.getType() == EnumMaterial.CARROTS.getType() || block2.getType().toString().toLowerCase().contains("carpet") || block2.getType() == EnumMaterial.COBWEB.getType() || block2.getType().toString().toLowerCase().contains("crops") || block2.getType() == EnumMaterial.DANDELION.getType() || block2.getType() == EnumMaterial.DEAD_BUSH.getType() || block2.getType().toString().toLowerCase().contains("double_plant") || block2.getType() == EnumMaterial.FARMLAND.getType() || block2.getType() == EnumMaterial.FERN.getType() || block2.getType() == EnumMaterial.FIRE.getType() || block2.getType() == EnumMaterial.FLOWER_POT.getType() || block2.getType().toString().toLowerCase().contains("flower_pot") || block2.getType() == EnumMaterial.GRASS.getType() || block2.getType().toString().toLowerCase().contains("head") || block2.getType() == EnumMaterial.LADDER.getType() || block2.getType() == EnumMaterial.LARGE_FERN.getType() || block2.getType() == EnumMaterial.LEVER.getType() || block2.getType() == EnumMaterial.LILAC.getType() || block2.getType() == EnumMaterial.LILY_PAD.getType() || block2.getType().toString().toLowerCase().contains("long_grass") || block2.getType() == EnumMaterial.MELON_STEM.getType() || block2.getType() == EnumMaterial.MUSHROOM_STEM.getType() || block2.getType() == EnumMaterial.NETHER_WART.getType() || block2.getType() == EnumMaterial.PEONY.getType() || block2.getType().toString().toLowerCase().contains("plate") || block2.getType() == EnumMaterial.POTATOES.getType() || block2.getType().toString().toLowerCase().contains("potted_") || block2.getType() == EnumMaterial.PUMPKIN_STEM.getType() || block2.getType().toString().toLowerCase().contains("redstone_torch") || block2.getType() == EnumMaterial.REDSTONE_WIRE.getType() || block2.getType() == EnumMaterial.RED_MUSHROOM.getType() || block2.getType().toString().toLowerCase().contains("red_rose") || block2.getType() == EnumMaterial.ROSE_BUSH.getType() || block2.getType().toString().toLowerCase().contains("sapling") || block2.getType().toString().toLowerCase().contains("sapling") || block2.getType().toString().toLowerCase().contains("sign") || block2.getType().toString().toLowerCase().contains("skull") || block2.getType() == EnumMaterial.SNOW.getType() || block2.getType() == EnumMaterial.SUGAR_CANE.getType() || block2.getType().toString().toLowerCase().contains("sugar_cane_block") || block2.getType() == EnumMaterial.SUNFLOWER.getType() || block2.getType() == EnumMaterial.TALL_GRASS.getType() || block2.getType() == EnumMaterial.TORCH.getType() || block2.getType() == EnumMaterial.TRIPWIRE.getType() || block2.getType() == EnumMaterial.TRIPWIRE_HOOK.getType() || block2.getType().toString().toLowerCase().contains("tulip") || block2.getType() == EnumMaterial.VINE.getType() || block2.getType() == EnumMaterial.WHEAT.getType() || block2.getType().toString().toLowerCase().contains("_button") || BlockUtil.hasHangingEntities(block2.getLocation()) || BlockUtil.isCocoaBlock(block2)) {
                this.getPlayer().sendMessage(MessageType.STAND_ON_TWO_BLOCKS_HIGH.getFormatMessage());
                return false;
            }
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        (this.centerLocation = this.getPlayer().getLocation().getBlock().getLocation().add(1.2, 0.0, -1.0).clone()).setYaw(-90.0f);
        final Location add = this.getPlayer().getLocation().getBlock().getLocation().clone().add(0.5, 0.0, -1.0);
        add.setYaw(-90.0f);
        add.setPitch(this.getPlayer().getLocation().getPitch());
        this.getPlayer().teleport(add, PlayerTeleportEvent.TeleportCause.PLUGIN);
        for (int i = 0; i <= 2; ++i) {
            this.setBlock(this.getLocation(i, -1, 1), EnumMaterial.SAND, (byte)0);
            this.setBlock(this.getLocation(i, -1, 0), EnumMaterial.SAND, (byte)0);
            this.setBlock(this.getLocation(i, -1, -1), EnumMaterial.SAND, (byte)0);
            this.setBlock(this.getLocation(i, -1, -2), EnumMaterial.SAND, (byte)0);
        }
        for (int j = 1; j <= 5; j += 4) {
            for (int k = -3; k <= 3; k += 2) {
                final ArmorStand e = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.centerLocation.clone().add(GadgetSandCastle.distance * j, GadgetSandCastle.locY, GadgetSandCastle.distance * k), EntityType.ARMOR_STAND);
                e.setVisible(false);
                e.setGravity(false);
                e.setSmall(true);
                e.setArms(false);
                e.setBasePlate(false);
                if (VersionManager.is1_9OrAbove()) {
                    e.setSilent(true);
                }
                try {
                    e.setMarker(false);
                    e.setCollidable(false);
                }
                catch (NoSuchMethodError noSuchMethodError) {}
                e.setHelmet(new ItemStack(Material.SAND));
                e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                this.armorstands.add(e);
            }
        }
        for (int l = -3; l <= 3; l += 6) {
            final ArmorStand e2 = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.centerLocation.clone().add(GadgetSandCastle.distance * 3.0, GadgetSandCastle.locY, GadgetSandCastle.distance * l), EntityType.ARMOR_STAND);
            e2.setVisible(false);
            e2.setGravity(false);
            e2.setSmall(true);
            e2.setArms(false);
            e2.setBasePlate(false);
            if (VersionManager.is1_9OrAbove()) {
                e2.setSilent(true);
            }
            try {
                e2.setMarker(false);
                e2.setCollidable(false);
            }
            catch (NoSuchMethodError noSuchMethodError2) {}
            e2.setHelmet(new ItemStack(Material.SAND));
            e2.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
            this.armorstands.add(e2);
        }
        for (int n = 1; n <= 5; n += 4) {
            for (int n2 = 1; n2 <= 2; ++n2) {
                for (int n3 = -3; n3 <= 3; ++n3) {
                    final ArmorStand e3 = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.centerLocation.clone().add(GadgetSandCastle.distance * n, GadgetSandCastle.locY + -n2 * GadgetSandCastle.distance, GadgetSandCastle.distance * n3), EntityType.ARMOR_STAND);
                    e3.setVisible(false);
                    e3.setGravity(false);
                    e3.setSmall(true);
                    e3.setArms(false);
                    e3.setBasePlate(false);
                    if (VersionManager.is1_9OrAbove()) {
                        e3.setSilent(true);
                    }
                    try {
                        e3.setMarker(false);
                        e3.setCollidable(false);
                    }
                    catch (NoSuchMethodError noSuchMethodError3) {}
                    e3.setHelmet(new ItemStack(Material.SAND));
                    e3.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    this.armorstands.add(e3);
                }
            }
        }
        for (int n4 = 2; n4 <= 4; ++n4) {
            for (int n5 = 1; n5 <= 2; ++n5) {
                for (int n6 = -3; n6 <= 3; n6 += 6) {
                    final ArmorStand e4 = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.centerLocation.clone().add(n4 * GadgetSandCastle.distance, GadgetSandCastle.locY + -n5 * GadgetSandCastle.distance, GadgetSandCastle.distance * n6), EntityType.ARMOR_STAND);
                    e4.setVisible(false);
                    e4.setGravity(false);
                    e4.setSmall(true);
                    e4.setArms(false);
                    e4.setBasePlate(false);
                    if (VersionManager.is1_9OrAbove()) {
                        e4.setSilent(true);
                    }
                    try {
                        e4.setMarker(false);
                        e4.setCollidable(false);
                    }
                    catch (NoSuchMethodError noSuchMethodError4) {}
                    e4.setHelmet(new ItemStack(Material.SAND));
                    e4.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                    this.armorstands.add(e4);
                }
            }
        }
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> {
            if (!this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() != this.getType() || !this.activated) {
                return;
            }
            else {
                this.clearAll();
                return;
            }
        }, 300L);
        this.activated = true;
    }
    
    @Override
    public void onUpdate() {
        if (this.activated) {
            new BukkitRunnable() {
                public void run() {
                    if (!GadgetSandCastle.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetSandCastle.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetSandCastle.this.getPlayer()).getCurrentGadget().getType() != GadgetSandCastle.this.getType() || !GadgetSandCastle.this.activated) {
                        this.cancel();
                        return;
                    }
                    if (GadgetSandCastle.this.lY < 2.1) {
                        for (final ArmorStand armorStand : GadgetSandCastle.this.armorstands) {
                            final Location clone = armorStand.getLocation().clone();
                            if (GadgetSandCastle.this.lY == 0.0) {
                                clone.setY(clone.getY() + 0.6);
                            }
                            else {
                                clone.setY(clone.getY() + 0.0375);
                            }
                            clone.setYaw(0.0f);
                            armorStand.teleport(clone);
                        }
                        if (GadgetSandCastle.this.lY == 0.0) {
                            final GadgetSandCastle this$0 = GadgetSandCastle.this;
                            GadgetSandCastle.access$3(this$0, this$0.lY + 0.6);
                        }
                        else {
                            final GadgetSandCastle this$2 = GadgetSandCastle.this;
                            GadgetSandCastle.access$3(this$2, this$2.lY + 0.0375);
                        }
                    }
                }
            }.runTask((Plugin)CookieGadgets.getInstance());
        }
    }
    
    @Override
    public void onClear() {
        this.clearAll();
        HandlerList.unregisterAll((Listener)this);
    }
    
    private void clearAll() {
        for (final Location location : this.blocks.keySet()) {
            final Block block = location.clone().getBlock();
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
        for (final ArmorStand armorStand : this.armorstands) {
            if (armorStand.isValid()) {
                armorStand.remove();
            }
        }
        this.armorstands.clear();
        this.centerLocation = null;
        this.lY = 0.0;
        this.activated = false;
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
            block.setType(enumMaterial.getType());
            ReflectionUtils.setData(block, b);
            block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        }
    }
    
    private Block getLocation(final int n, final int n2, final int n3) {
        return this.centerLocation.getBlock().getRelative(n, n2, n3);
    }
    
    static /* synthetic */ void access$3(final GadgetSandCastle gadgetSandCastle, final double ly) {
        gadgetSandCastle.lY = ly;
    }
}
