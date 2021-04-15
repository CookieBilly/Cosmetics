

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import java.lang.reflect.Field;
import org.bukkit.inventory.meta.ItemMeta;
import com.mojang.authlib.properties.Property;
import java.util.Base64;
import com.mojang.authlib.GameProfile;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.ItemStack;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.EulerAngle;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.entity.EntityType;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.block.Block;
import ws.billy.CookieGadgets.utils.CuboID;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.Location;
import java.util.HashMap;

public class GadgetPocketBeach extends Gadget
{
    private boolean activated;
    private HashMap<Location, String> blocks;
    private Location centerLocation;
    private ArmorStand armorstand;
    private Entity arrow;
    
    public GadgetPocketBeach(final UUID uuid) {
        super(uuid, GadgetType.POCKET_BEACH);
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
        final Location add = this.getPlayer().getLocation().clone().add(-1.0, 0.0, -1.0);
        final Location add2 = this.getPlayer().getLocation().clone().add(2.0, 4.0, 2.0);
        final Location add3 = this.getPlayer().getLocation().clone().add(-1.0, -1.0, -1.0);
        final Location add4 = this.getPlayer().getLocation().clone().add(2.0, -1.0, 2.0);
        final Location add5 = this.getPlayer().getLocation().clone().add(-1.0, -2.0, -1.0);
        final Location add6 = this.getPlayer().getLocation().clone().add(2.0, -2.0, 2.0);
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
            if (block.isLiquid() || block.getType() == EnumMaterial.ATTACHED_MELON_STEM.getType() || block.getType() == EnumMaterial.ATTACHED_PUMPKIN_STEM.getType() || block.getType().toString().toLowerCase().contains("banner") || block.getType() == EnumMaterial.BREWING_STAND.getType() || block.getType() == EnumMaterial.BROWN_MUSHROOM.getType() || block.getType() == EnumMaterial.CACTUS.getType() || block.getType() == EnumMaterial.CAKE.getType() || block.getType().toString().toLowerCase().contains("cake_block") || block.getType().toString().toLowerCase().contains("carpet") || block.getType() == EnumMaterial.CARROTS.getType() || block.getType() == EnumMaterial.CHEST.getType() || block.getType() == EnumMaterial.COBWEB.getType() || block.getType().toString().toLowerCase().contains("crops") || block.getType() == EnumMaterial.DANDELION.getType() || block.getType() == EnumMaterial.DEAD_BUSH.getType() || block.getType().toString().toLowerCase().contains("double_plant") || block.getType() == EnumMaterial.FARMLAND.getType() || block.getType() == EnumMaterial.FERN.getType() || block.getType() == EnumMaterial.FIRE.getType() || block.getType() == EnumMaterial.FLOWER_POT.getType() || block.getType().toString().toLowerCase().contains("flower_pot") || block.getType() == EnumMaterial.GRASS.getType() || block.getType().toString().toLowerCase().contains("head") || block.getType() == EnumMaterial.LADDER.getType() || block.getType() == EnumMaterial.LARGE_FERN.getType() || block.getType() == EnumMaterial.LEVER.getType() || block.getType() == EnumMaterial.LILAC.getType() || block.getType() == EnumMaterial.LILY_PAD.getType() || block.getType().toString().toLowerCase().contains("long_grass") || block.getType() == EnumMaterial.MELON_STEM.getType() || block.getType() == EnumMaterial.MUSHROOM_STEM.getType() || block.getType() == EnumMaterial.NETHER_WART.getType() || block.getType() == EnumMaterial.PEONY.getType() || block.getType().toString().toLowerCase().contains("plate") || block.getType() == EnumMaterial.POTATOES.getType() || block.getType().toString().toLowerCase().contains("potted_") || block.getType() == EnumMaterial.PUMPKIN_STEM.getType() || block.getType().toString().toLowerCase().contains("redstone_torch") || block.getType() == EnumMaterial.REDSTONE_WIRE.getType() || block.getType() == EnumMaterial.RED_MUSHROOM.getType() || block.getType().toString().toLowerCase().contains("red_rose") || block.getType() == EnumMaterial.ROSE_BUSH.getType() || block.getType().toString().toLowerCase().contains("sapling") || block.getType().toString().toLowerCase().contains("seeds") || block.getType().toString().toLowerCase().contains("sign") || block.getType().toString().toLowerCase().contains("shulker_box") || block.getType().toString().toLowerCase().contains("skull") || block.getType() == EnumMaterial.SNOW.getType() || block.getType() == EnumMaterial.SPAWNER.getType() || block.getType() == EnumMaterial.SUGAR_CANE.getType() || block.getType().toString().toLowerCase().contains("sugar_cane_block") || block.getType() == EnumMaterial.SUNFLOWER.getType() || block.getType() == EnumMaterial.TALL_GRASS.getType() || block.getType() == EnumMaterial.TORCH.getType() || block.getType() == EnumMaterial.TRIPWIRE.getType() || block.getType() == EnumMaterial.TRIPWIRE_HOOK.getType() || block.getType().toString().toLowerCase().contains("tulip") || block.getType() == EnumMaterial.VINE.getType() || block.getType() == EnumMaterial.WHEAT.getType() || block.getType().toString().toLowerCase().contains("_button") || BlockUtil.hasHangingEntities(block.getLocation()) || BlockUtil.isCocoaBlock(block)) {
                this.getPlayer().sendMessage(MessageType.NOT_ON_FLAT_GROUND.getFormatMessage());
                return false;
            }
        }
        for (final Block block2 : cuboID3.getBlocks()) {
            if (block2.isLiquid() || block2.isEmpty() || block2.getType() == EnumMaterial.ATTACHED_MELON_STEM.getType() || block2.getType() == EnumMaterial.ATTACHED_PUMPKIN_STEM.getType() || block2.getType() == EnumMaterial.BROWN_MUSHROOM.getType() || block2.getType() == EnumMaterial.CACTUS.getType() || block2.getType() == EnumMaterial.CAKE.getType() || block2.getType().toString().toLowerCase().contains("cake_block") || block2.getType().toString().toLowerCase().contains("carpet") || block2.getType() == EnumMaterial.CARROTS.getType() || block2.getType() == EnumMaterial.COBWEB.getType() || block2.getType().toString().toLowerCase().contains("crops") || block2.getType() == EnumMaterial.DANDELION.getType() || block2.getType() == EnumMaterial.DEAD_BUSH.getType() || block2.getType().toString().toLowerCase().contains("double_plant") || block2.getType() == EnumMaterial.FARMLAND.getType() || block2.getType() == EnumMaterial.FERN.getType() || block2.getType() == EnumMaterial.FIRE.getType() || block2.getType() == EnumMaterial.FLOWER_POT.getType() || block2.getType().toString().toLowerCase().contains("flower_pot") || block2.getType() == EnumMaterial.GRASS.getType() || block2.getType().toString().toLowerCase().contains("head") || block2.getType() == EnumMaterial.LADDER.getType() || block2.getType() == EnumMaterial.LARGE_FERN.getType() || block2.getType() == EnumMaterial.LEVER.getType() || block2.getType() == EnumMaterial.LILAC.getType() || block2.getType() == EnumMaterial.LILY_PAD.getType() || block2.getType().toString().toLowerCase().contains("long_grass") || block2.getType() == EnumMaterial.MELON_STEM.getType() || block2.getType() == EnumMaterial.MUSHROOM_STEM.getType() || block2.getType() == EnumMaterial.NETHER_WART.getType() || block2.getType() == EnumMaterial.PEONY.getType() || block2.getType().toString().toLowerCase().contains("plate") || block2.getType() == EnumMaterial.POTATOES.getType() || block2.getType().toString().toLowerCase().contains("potted_") || block2.getType() == EnumMaterial.PUMPKIN_STEM.getType() || block2.getType().toString().toLowerCase().contains("redstone_torch") || block2.getType() == EnumMaterial.REDSTONE_WIRE.getType() || block2.getType() == EnumMaterial.RED_MUSHROOM.getType() || block2.getType().toString().toLowerCase().contains("red_rose") || block2.getType() == EnumMaterial.ROSE_BUSH.getType() || block2.getType().toString().toLowerCase().contains("sapling") || block2.getType().toString().toLowerCase().contains("seeds") || block2.getType().toString().toLowerCase().contains("sign") || block2.getType().toString().toLowerCase().contains("skull") || block2.getType() == EnumMaterial.SNOW.getType() || block2.getType() == EnumMaterial.SPAWNER.getType() || block2.getType() == EnumMaterial.SUGAR_CANE.getType() || block2.getType().toString().toLowerCase().contains("sugar_cane_block") || block2.getType() == EnumMaterial.SUNFLOWER.getType() || block2.getType() == EnumMaterial.TALL_GRASS.getType() || block2.getType() == EnumMaterial.TRIPWIRE.getType() || block2.getType() == EnumMaterial.TRIPWIRE_HOOK.getType() || block2.getType().toString().toLowerCase().contains("tulip") || block2.getType() == EnumMaterial.VINE.getType() || block2.getType() == EnumMaterial.TORCH.getType() || block2.getType() == EnumMaterial.WHEAT.getType() || block2.getType().toString().toLowerCase().contains("_button") || BlockUtil.hasHangingEntities(block2.getLocation()) || BlockUtil.isCocoaBlock(block2)) {
                this.getPlayer().sendMessage(MessageType.STAND_ON_TWO_BLOCKS_HIGH.getFormatMessage());
                return false;
            }
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.centerLocation = this.getPlayer().getLocation().getBlock().getLocation().clone();
        (this.arrow = this.getPlayer().getWorld().spawnEntity(this.centerLocation.clone().add(0.5, 0.0, 0.5), EntityType.ARROW)).setPassenger((Entity)this.getPlayer());
        for (int i = -1; i <= 2; ++i) {
            this.setBlock(this.getLocation(i, -1, -1), EnumMaterial.SAND, false, (byte)0);
            this.setBlock(this.getLocation(i, -1, 0), EnumMaterial.SAND, false, (byte)0);
            this.setBlock(this.getLocation(i, -1, 1), EnumMaterial.SAND, false, (byte)0);
            this.setBlock(this.getLocation(i, -1, 2), EnumMaterial.SAND, false, (byte)0);
        }
        this.setBlock(this.getLocation(0, 0, 1), EnumMaterial.OAK_FENCE, false, (byte)0);
        this.setBlock(this.getLocation(0, 1, 1), EnumMaterial.OAK_FENCE, false, (byte)0);
        this.setBlock(this.getLocation(0, 0, 0), EnumMaterial.RED_SANDSTONE_STAIRS, true, (byte)1);
        this.setBlock(this.getLocation(1, 0, 0), EnumMaterial.RED_SANDSTONE_SLAB, false, (byte)0);
        try {
            this.arrow.setGravity(false);
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        this.setBlock(this.getLocation(0, 2, 1), EnumMaterial.RED_WOOL, false, (byte)14);
        this.setBlock(this.getLocation(0, 3, 1), EnumMaterial.RED_WOOL, false, (byte)14);
        this.setBlock(this.getLocation(1, 2, 1), EnumMaterial.RED_WOOL, false, (byte)14);
        this.setBlock(this.getLocation(-1, 2, 1), EnumMaterial.RED_WOOL, false, (byte)14);
        this.setBlock(this.getLocation(0, 2, 2), EnumMaterial.RED_WOOL, false, (byte)14);
        this.setBlock(this.getLocation(0, 2, 0), EnumMaterial.RED_WOOL, false, (byte)14);
        final ArmorStand armorstand = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.centerLocation.clone().add(2.5, -0.7, 1.5), EntityType.ARMOR_STAND);
        armorstand.setVisible(false);
        armorstand.setGravity(false);
        armorstand.setSmall(true);
        armorstand.setArms(false);
        armorstand.setBasePlate(false);
        if (VersionManager.is1_9OrAbove()) {
            armorstand.setSilent(true);
        }
        try {
            armorstand.setMarker(false);
            armorstand.setCollidable(false);
        }
        catch (NoSuchMethodError noSuchMethodError2) {}
        armorstand.setHeadPose(new EulerAngle(0.0, 0.34906585, 0.0));
        armorstand.setHelmet(this.getBeachball());
        armorstand.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.armorstand = armorstand;
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), () -> {
            if (!this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() != this.getType() || !this.activated) {
                return;
            }
            else {
                this.clearAll();
                return;
            }
        }, 400L);
        this.activated = true;
    }
    
    @Override
    public void onUpdate() {
        if (this.activated) {
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                if (this.getPlayer().isOnline() && CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() != null && CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() == this.getType() && this.activated) {
                    ParticleEffect.DRIP_WATER.display(this.centerLocation.clone().add(1.0, 0.0, 1.0), 0.6f, 0.12f, 0.6f, 0.25f, 2);
                    if (this.arrow != null && this.arrow.isValid() && this.arrow.getPassenger() == null) {
                        this.arrow.setPassenger((Entity)this.getPlayer());
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
        this.activated = false;
        for (final Location location : this.blocks.keySet()) {
            final Block block = location.clone().getBlock();
            if (VersionManager.is1_13OrAbove()) {
                try {
                    block.setBlockData(Bukkit.getServer().createBlockData((String)this.blocks.get(location)));
                }
                catch (NoSuchMethodError noSuchMethodError) {}
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
        this.centerLocation = null;
        if (this.armorstand != null) {
            this.armorstand.remove();
        }
        this.armorstand = null;
        if (this.arrow != null) {
            this.arrow.setPassenger((Entity)null);
            this.arrow.remove();
        }
        this.arrow = null;
    }
    
    private ItemStack getBeachball() {
        final ItemStack itemStack = new ItemStack(EnumMaterial.PLAYER_HEAD.getType(), 1, (short)EnumMaterial.PLAYER_HEAD.getData());
        final SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
        final GameProfile value = new GameProfile(UUID.randomUUID(), (String)null);
        value.getProperties().put((Object)"textures", (Object)new Property("textures", new String(Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/5a5ab05ea254c32e3c48f3fdcf9fd9d77d3cba04e6b5ec2e68b3cbdcfac3fd").getBytes()))));
        try {
            final Field declaredField = skullMeta.getClass().getDeclaredField("profile");
            declaredField.setAccessible(true);
            declaredField.set(skullMeta, value);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            final Throwable t;
            t.printStackTrace();
        }
        itemStack.setItemMeta((ItemMeta)skullMeta);
        return itemStack;
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
        return this.centerLocation.getBlock().getRelative(n, n2, n3);
    }
}
