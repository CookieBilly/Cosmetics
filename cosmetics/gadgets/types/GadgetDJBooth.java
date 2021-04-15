

package ws.billy.CookieGadgets.cosmetics.gadgets.types;
import ws.billy.CookieGadgets.utils.ReflectionUtils;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;
import java.util.Random;
import ws.billy.CookieGadgets.utils.BlockUtil;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.CuboID;

public class GadgetDJBooth extends Gadget
{
    private static int duration;
    public static HashMap<GadgetDJBooth, Location> DJ_BOOTH;
    private boolean activated;
    private Location location;
    private CuboID danceFloorCuboid;
    private PositionSongPlayer positionSongPlayer;
    private HashMap<Location, String> blocks;
    private ArrayList<ArmorStand> armorstands;
    private static File files;
    private static List<File> songs;
    
    static {
        GadgetDJBooth.duration = ((FileManager.getGadgetsFile().get("Gadgets.Musical.Types.DJ Booth.Duration-Seconds") == null) ? 60 : FileManager.getGadgetsFile().getInt("Gadgets.Musical.Types.DJ Booth.Duration-Seconds"));
        GadgetDJBooth.DJ_BOOTH = new HashMap<GadgetDJBooth, Location>();
        GadgetDJBooth.files = new File(String.valueOf(CookieGadgets.getInstance().getDataFolder().getPath()) + "/songs/DjBoothGadget/");
        GadgetDJBooth.songs = new ArrayList<File>();
    }
    
    public GadgetDJBooth(final UUID uuid) {
        super(uuid, GadgetType.DJ_BOOTH);
        this.activated = false;
        this.blocks = new HashMap<Location, String>();
        this.armorstands = new ArrayList<ArmorStand>();
        if (GadgetDJBooth.songs.isEmpty()) {
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    if (GadgetDJBooth.files.exists() && GadgetDJBooth.files.listFiles().length > 0) {
                        File[] listFiles;
                        for (int length = (listFiles = GadgetDJBooth.files.listFiles()).length, i = 0; i < length; ++i) {
                            final File file = listFiles[i];
                            if (file.getName().contains(".nbs") && !GadgetDJBooth.songs.contains(file)) {
                                GadgetDJBooth.songs.add(file);
                            }
                        }
                    }
                }
            });
        }
    }
    
    @Override
    protected boolean checkRequirements() {
        if (this.activated) {
            this.getPlayer().sendMessage(MessageType.GADGET_IS_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
            return false;
        }
        final Iterator<Location> iterator = GadgetDJBooth.DJ_BOOTH.values().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getWorld().equals(this.getPlayer().getWorld())) {
                this.getPlayer().sendMessage(MessageType.SAME_TYPE_GADGET_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
                return false;
            }
        }
        for (final Location location : GadgetDiscoBall.DISCO_BALLS.values()) {
            if (location.getWorld().equals(this.getPlayer().getWorld()) && location.distanceSquared(this.getPlayer().getLocation()) <= 625.0) {
                this.getPlayer().sendMessage(MessageType.SAME_TYPE_GADGET_ACTIVATED.getFormatMessage().replace("{GADGET}", GadgetType.DISCO_BALL.getDisplayNameStripColor()));
                return false;
            }
        }
        if (!this.getPlayer().isOnGround()) {
            this.getPlayer().sendMessage(MessageType.NOT_ON_GROUND.getFormatMessage());
            return false;
        }
        final Location add = this.getPlayer().getLocation().clone().add(0.0, 0.0, -2.0);
        final Location add2 = this.getPlayer().getLocation().clone().add(2.0, 5.0, 2.0);
        final Location add3 = this.getPlayer().getLocation().clone().add(2.0, -1.0, -2.0);
        final Location add4 = this.getPlayer().getLocation().clone().add(6.0, 3.0, 2.0);
        final CuboID cuboID = new CuboID(add, add2);
        this.danceFloorCuboid = new CuboID(add3, add4);
        if (!cuboID.isEmpty()) {
            this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
            return false;
        }
        for (final Block block : this.danceFloorCuboid.getBlocks()) {
            if (block.hasMetadata(CookieGadgets.getInstance().getPluginName())) {
                this.getPlayer().sendMessage(MessageType.GADGET_ACTIVATED_IN_SAME_AREA.getFormatMessage());
                return false;
            }
            if (block.isLiquid() || block.getType() == EnumMaterial.ATTACHED_MELON_STEM.getType() || block.getType() == EnumMaterial.ATTACHED_PUMPKIN_STEM.getType() || block.getType().toString().toLowerCase().contains("banner") || block.getType() == EnumMaterial.BREWING_STAND.getType() || block.getType() == EnumMaterial.BROWN_MUSHROOM.getType() || block.getType() == EnumMaterial.CACTUS.getType() || block.getType() == EnumMaterial.CAKE.getType() || block.getType().toString().toLowerCase().contains("cake_block") || block.getType() == EnumMaterial.CARROTS.getType() || block.getType().toString().toLowerCase().contains("carpet") || block.getType() == EnumMaterial.CHEST.getType() || block.getType() == EnumMaterial.COBWEB.getType() || block.getType().toString().toLowerCase().contains("command") || block.getType().toString().toLowerCase().contains("crops") || block.getType() == EnumMaterial.DANDELION.getType() || block.getType() == EnumMaterial.DEAD_BUSH.getType() || block.getType().toString().toLowerCase().contains("double_plant") || block.getType() == EnumMaterial.FARMLAND.getType() || block.getType() == EnumMaterial.FERN.getType() || block.getType() == EnumMaterial.FIRE.getType() || block.getType() == EnumMaterial.FLOWER_POT.getType() || block.getType().toString().toLowerCase().contains("flower_pot") || block.getType() == EnumMaterial.GRASS.getType() || block.getType().toString().toLowerCase().contains("head") || block.getType() == EnumMaterial.LADDER.getType() || block.getType() == EnumMaterial.LARGE_FERN.getType() || block.getType() == EnumMaterial.LEVER.getType() || block.getType() == EnumMaterial.LILAC.getType() || block.getType() == EnumMaterial.LILY_PAD.getType() || block.getType().toString().toLowerCase().contains("long_grass") || block.getType() == EnumMaterial.MELON_STEM.getType() || block.getType() == EnumMaterial.MUSHROOM_STEM.getType() || block.getType() == EnumMaterial.NETHER_WART.getType() || block.getType() == EnumMaterial.PEONY.getType() || block.getType().toString().toLowerCase().contains("plate") || block.getType() == EnumMaterial.POTATOES.getType() || block.getType().toString().toLowerCase().contains("potted_") || block.getType() == EnumMaterial.PUMPKIN_STEM.getType() || block.getType().toString().toLowerCase().contains("redstone_comparator") || block.getType().toString().toLowerCase().contains("redstone_lamp") || block.getType().toString().toLowerCase().contains("redstone_torch") || block.getType() == EnumMaterial.REDSTONE_WIRE.getType() || block.getType() == EnumMaterial.REPEATER.getType() || block.getType() == EnumMaterial.RED_MUSHROOM.getType() || block.getType().toString().toLowerCase().contains("red_rose") || block.getType() == EnumMaterial.ROSE_BUSH.getType() || block.getType().toString().toLowerCase().contains("sapling") || block.getType().toString().toLowerCase().contains("seeds") || block.getType().toString().toLowerCase().contains("sign") || block.getType().toString().toLowerCase().contains("shulker_box") || block.getType().toString().toLowerCase().contains("skull") || block.getType() == EnumMaterial.SNOW.getType() || block.getType() == EnumMaterial.SUGAR_CANE.getType() || block.getType().toString().toLowerCase().contains("sugar_cane_block") || block.getType() == EnumMaterial.SUNFLOWER.getType() || block.getType() == EnumMaterial.TALL_GRASS.getType() || block.getType() == EnumMaterial.TORCH.getType() || block.getType() == EnumMaterial.TRIPWIRE.getType() || block.getType() == EnumMaterial.TRIPWIRE_HOOK.getType() || block.getType().toString().toLowerCase().contains("tulip") || block.getType() == EnumMaterial.VINE.getType() || block.getType() == EnumMaterial.WHEAT.getType() || block.getType().toString().toLowerCase().contains("_button") || BlockUtil.hasHangingEntities(block.getLocation()) || BlockUtil.isCocoaBlock(block)) {
                this.getPlayer().sendMessage(MessageType.NOT_ON_FLAT_GROUND.getFormatMessage());
                return false;
            }
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        this.location = this.getPlayer().getLocation().clone();
        final Location add = this.getPlayer().getLocation().getBlock().getLocation().clone().add(0.5, 2.0, 0.5);
        add.setYaw(-90.0f);
        this.getPlayer().teleport(add);
        this.genetateDanceFloor();
        int i = 0;
        Bukkit.getScheduler().runTaskTimer((Plugin)CookieGadgets.getInstance(), () -> {
            if (!this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() != this.getType() || !this.activated) {
                return;
            }
            else {
                while (i <= 6) {
                    this.changeDanceFloorBlock(this.getLocation(i, -1, -2), EnumMaterial.valueOf(35, CookieGadgets.random().nextInt(16)));
                    this.changeDanceFloorBlock(this.getLocation(i, -1, -1), EnumMaterial.valueOf(35, CookieGadgets.random().nextInt(16)));
                    this.changeDanceFloorBlock(this.getLocation(i, -1, 0), EnumMaterial.valueOf(35, CookieGadgets.random().nextInt(16)));
                    this.changeDanceFloorBlock(this.getLocation(i, -1, 1), EnumMaterial.valueOf(35, CookieGadgets.random().nextInt(16)));
                    this.changeDanceFloorBlock(this.getLocation(i, -1, 2), EnumMaterial.valueOf(35, CookieGadgets.random().nextInt(16)));
                    ++i;
                }
                return;
            }
        }, 10L, 10L);
        if (!GadgetDJBooth.DJ_BOOTH.containsKey(this)) {
            GadgetDJBooth.DJ_BOOTH.put(this, this.getPlayer().getLocation());
        }
        (this.positionSongPlayer = new PositionSongPlayer(NBSDecoder.parse(GadgetDJBooth.songs.get(new Random().nextInt(GadgetDJBooth.songs.size()))))).setTargetLocation(this.getLocation(1, 1, 0).getLocation().add(-0.5, -0.5, -0.5));
        this.positionSongPlayer.setPlaying(true);
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld() == this.location.getWorld() && player.isOnline()) {
                this.positionSongPlayer.addPlayer(player);
            }
        }
        this.positionSongPlayer.setVolume((byte)100);
        this.positionSongPlayer.getFadeIn().setFadeStart((byte)25);
        int duration = GadgetDJBooth.duration;
        if (!PermissionUtils.noPermission(this.getPlayer(), EnumPermission.BYPASS_DJ_BOOTH_DURATION.getPermission(), false)) {
            duration = this.positionSongPlayer.getSong().getLength() / 20;
        }
        if (duration > this.positionSongPlayer.getSong().getLength() / 20) {
            duration = this.positionSongPlayer.getSong().getLength() / 20;
        }
        new BukkitRunnable() {
            public void run() {
                if (!GadgetDJBooth.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetDJBooth.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetDJBooth.this.getPlayer()).getCurrentGadget().getType() != GadgetDJBooth.this.getType() || !GadgetDJBooth.this.activated) {
                    return;
                }
                GadgetDJBooth.this.clearAll();
            }
        }.runTaskLater((Plugin)CookieGadgets.getInstance(), (long)(20 * duration));
        this.activated = true;
    }
    
    @Override
    public void onUpdate() {
        if (this.activated) {
            final Iterator<Player> iterator;
            Player player;
            Bukkit.getScheduler().runTask((Plugin)CookieGadgets.getInstance(), () -> {
                if (this.getPlayer().isOnline() && CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget() != null && CookieGadgets.getPlayerManager(this.getPlayer()).getCurrentGadget().getType() == this.getType() && this.activated) {
                    Bukkit.getOnlinePlayers().iterator();
                    while (iterator.hasNext()) {
                        player = iterator.next();
                        if (player.isOnline() && player.getWorld() == this.location.getWorld() && !this.positionSongPlayer.getPlayerList().contains(player)) {
                            this.positionSongPlayer.addPlayer(player);
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
        final Iterator<ArmorStand> iterator2 = this.armorstands.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().remove();
        }
        this.armorstands.clear();
        if (this.positionSongPlayer != null && this.positionSongPlayer.isPlaying()) {
            this.positionSongPlayer.setPlaying(false);
        }
        this.positionSongPlayer = null;
        this.location = null;
        if (GadgetDJBooth.DJ_BOOTH.containsKey(this)) {
            GadgetDJBooth.DJ_BOOTH.remove(this);
        }
        this.activated = false;
    }
    
    private void genetateDanceFloor() {
        this.setBlock(this.getLocation(0, 0, 0), EnumMaterial.DARK_OAK_STAIRS, true, (byte)0);
        this.setBlock(this.getLocation(0, 0, -1), EnumMaterial.DARK_OAK_STAIRS, true, (byte)0);
        this.setBlock(this.getLocation(0, 0, -2), EnumMaterial.DARK_OAK_STAIRS, true, (byte)2);
        this.setBlock(this.getLocation(1, 0, -2), EnumMaterial.DARK_OAK_STAIRS, true, (byte)2);
        this.setBlock(this.getLocation(0, 0, 1), EnumMaterial.DARK_OAK_STAIRS, true, (byte)0);
        this.setBlock(this.getLocation(0, 0, 2), EnumMaterial.DARK_OAK_STAIRS, true, (byte)3);
        this.setBlock(this.getLocation(1, 0, 2), EnumMaterial.DARK_OAK_STAIRS, true, (byte)3);
        this.setBlock(this.getLocation(1, 0, -1), EnumMaterial.DARK_OAK_PLANKS, false, (byte)0);
        this.setBlock(this.getLocation(1, 0, 0), EnumMaterial.DARK_OAK_PLANKS, false, (byte)0);
        this.setBlock(this.getLocation(1, 0, 1), EnumMaterial.DARK_OAK_PLANKS, false, (byte)0);
        this.setBlock(this.getLocation(1, 1, 0), EnumMaterial.NOTE_BLOCK, false, (byte)0);
        final Location clone = this.getPlayer().getLocation().getBlock().getLocation().clone();
        clone.setYaw(180.0f);
        clone.add(1.3, -0.8, 1.1);
        final ArmorStand e = (ArmorStand)this.getPlayer().getWorld().spawnEntity(clone, EntityType.ARMOR_STAND);
        e.setVisible(false);
        e.setGravity(false);
        e.setSmall(false);
        e.setArms(true);
        e.setRightArmPose(new EulerAngle(0.0, 0.0, 0.0));
        try {
            if (VersionManager.is1_9OrAbove()) {
                e.setSilent(true);
            }
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        e.setItemInHand(new ItemStack(EnumMaterial.MUSIC_DISC_BLOCKS.getType()));
        e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.armorstands.add(e);
        final Location clone2 = this.getPlayer().getLocation().getBlock().getLocation().clone();
        clone2.setYaw(0.0f);
        clone2.add(2.015, -0.8, -0.1);
        final ArmorStand e2 = (ArmorStand)this.getPlayer().getWorld().spawnEntity(clone2, EntityType.ARMOR_STAND);
        e2.setVisible(false);
        e2.setGravity(false);
        e2.setSmall(false);
        e2.setArms(true);
        e2.setRightArmPose(new EulerAngle(0.0, 0.0, 0.0));
        try {
            if (VersionManager.is1_9OrAbove()) {
                e2.setSilent(true);
            }
        }
        catch (NoSuchMethodError noSuchMethodError2) {}
        e2.setItemInHand(new ItemStack(EnumMaterial.MUSIC_DISC_BLOCKS.getType()));
        e2.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.armorstands.add(e2);
        for (int i = 2; i <= 6; ++i) {
            this.setBlock(this.getLocation(i, -1, -2), EnumMaterial.valueOf(159, CookieGadgets.random().nextInt(16)), false, (byte)0);
            this.setBlock(this.getLocation(i, -1, -1), EnumMaterial.valueOf(159, CookieGadgets.random().nextInt(16)), false, (byte)0);
            this.setBlock(this.getLocation(i, -1, 0), EnumMaterial.valueOf(159, CookieGadgets.random().nextInt(16)), false, (byte)0);
            this.setBlock(this.getLocation(i, -1, 1), EnumMaterial.valueOf(159, CookieGadgets.random().nextInt(16)), false, (byte)0);
            this.setBlock(this.getLocation(i, -1, 2), EnumMaterial.valueOf(159, CookieGadgets.random().nextInt(16)), false, (byte)0);
        }
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
    
    private void changeDanceFloorBlock(final Block block, final EnumMaterial enumMaterial) {
        block.setType(enumMaterial.getType());
        ReflectionUtils.setData(block, enumMaterial.getData());
        block.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
    }
    
    private Block getLocation(final int n, final int n2, final int n3) {
        return this.location.getBlock().getRelative(n, n2, n3);
    }
}
