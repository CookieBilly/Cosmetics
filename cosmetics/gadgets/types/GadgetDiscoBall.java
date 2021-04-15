

package ws.billy.CookieGadgets.cosmetics.gadgets.types;

import org.bukkit.event.Listener;
import org.bukkit.event.HandlerList;
import ws.billy.CookieGadgets.utils.ChatUtil;
import org.bukkit.util.Vector;
import org.bukkit.entity.Entity;
import ws.billy.CookieGadgets.utils.EntityUtils;
import ws.billy.CookieGadgets.utils.MathUtil;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.PermissionUtils;
import ws.billy.CookieGadgets.utils.EnumPermission;

import java.util.Random;
import ws.billy.CookieGadgets.utils.EnumMaterial;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.MessageType;
import ws.billy.CookieGadgets.cosmetics.gadgets.GadgetType;
import java.util.UUID;
import ws.billy.CookieGadgets.CookieGadgets;
import ws.billy.CookieGadgets.configuration.FileManager;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class GadgetDiscoBall extends Gadget
{
    private static int duration;
    public static HashMap<GadgetDiscoBall, Location> DISCO_BALLS;
    private boolean activated;
    private ArmorStand armorStand;
    private PositionSongPlayer positionSongPlayer;
    private static File files;
    private static List<File> songs;
    private ArrayList<Player> hideCBar;
    
    static {
        GadgetDiscoBall.duration = ((FileManager.getGadgetsFile().get("Gadgets.Musical.Types.Disco Ball.Duration-Seconds") == null) ? 60 : FileManager.getGadgetsFile().getInt("Gadgets.Musical.Types.Disco Ball.Duration-Seconds"));
        GadgetDiscoBall.DISCO_BALLS = new HashMap<GadgetDiscoBall, Location>();
        GadgetDiscoBall.files = new File(String.valueOf(CookieGadgets.getInstance().getDataFolder().getPath()) + "/songs/DiscoBallGadget/");
        GadgetDiscoBall.songs = new ArrayList<File>();
    }
    
    public GadgetDiscoBall(final UUID uuid) {
        super(uuid, GadgetType.DISCO_BALL);
        this.activated = false;
        this.hideCBar = new ArrayList<Player>();
        if (GadgetDiscoBall.songs.isEmpty()) {
            Bukkit.getScheduler().runTaskAsynchronously((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    if (GadgetDiscoBall.files.exists() && GadgetDiscoBall.files.listFiles().length > 0) {
                        File[] listFiles;
                        for (int length = (listFiles = GadgetDiscoBall.files.listFiles()).length, i = 0; i < length; ++i) {
                            final File file = listFiles[i];
                            if (file.getName().contains(".nbs") && !GadgetDiscoBall.songs.contains(file)) {
                                GadgetDiscoBall.songs.add(file);
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
        final Iterator<Location> iterator = GadgetDiscoBall.DISCO_BALLS.values().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getWorld().equals(this.getPlayer().getWorld())) {
                this.getPlayer().sendMessage(MessageType.SAME_TYPE_GADGET_ACTIVATED.getFormatMessage().replace("{GADGET}", this.getType().getDisplayNameStripColor()));
                return false;
            }
        }
        for (final Location location : GadgetDJBooth.DJ_BOOTH.values()) {
            if (location.getWorld().equals(this.getPlayer().getWorld()) && location.distanceSquared(this.getPlayer().getLocation()) <= 625.0) {
                this.getPlayer().sendMessage(MessageType.SAME_TYPE_GADGET_ACTIVATED.getFormatMessage().replace("{GADGET}", GadgetType.DJ_BOOTH.getDisplayNameStripColor()));
                return false;
            }
        }
        if (this.getPlayer().getLocation().add(0.0, 4.0, 0.0).getBlock() != null && this.getPlayer().getLocation().add(0.0, 4.0, 0.0).getBlock().getType() != Material.AIR) {
            this.getPlayer().sendMessage(MessageType.NOT_ENOUGH_SPACE.getFormatMessage());
            return false;
        }
        return super.checkRequirements();
    }
    
    @Override
    public void onClick() {
        final ArmorStand armorStand = (ArmorStand)this.getPlayer().getWorld().spawnEntity(this.getPlayer().getLocation().add(0.0, 3.0, 0.0), EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setSmall(false);
        try {
            if (VersionManager.is1_9OrAbove()) {
                armorStand.setSilent(true);
            }
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        armorStand.setHelmet(new ItemStack(EnumMaterial.LIGHT_BLUE_STAINED_GLASS.getType(), 1, (short)3));
        armorStand.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
        this.armorStand = armorStand;
        if (!GadgetDiscoBall.DISCO_BALLS.containsKey(this)) {
            GadgetDiscoBall.DISCO_BALLS.put(this, armorStand.getLocation());
        }
        final File file = GadgetDiscoBall.songs.get(new Random().nextInt(GadgetDiscoBall.songs.size()));
        (this.positionSongPlayer = new PositionSongPlayer(NBSDecoder.parse(file))).setTargetLocation(this.armorStand.getEyeLocation().add(-0.5, -0.5, -0.5));
        this.positionSongPlayer.setPlaying(true);
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld() == this.armorStand.getWorld() && player.isOnline()) {
                this.positionSongPlayer.addPlayer(player);
            }
        }
        this.positionSongPlayer.setVolume((byte)100);
        this.positionSongPlayer.getFadeIn().setFadeStart((byte)25);
        new BukkitRunnable() {
            int step = 0;
            
            public void run() {
                ++this.step;
                if (!GadgetDiscoBall.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetDiscoBall.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetDiscoBall.this.getPlayer()).getCurrentGadget().getType() != GadgetDiscoBall.this.getType()) {
                    this.step = GadgetDiscoBall.duration * 20 + 1;
                    GadgetDiscoBall.this.onClear();
                    this.cancel();
                    return;
                }
                if (this.step >= GadgetDiscoBall.this.positionSongPlayer.getSong().getLength()) {
                    this.step = GadgetDiscoBall.duration * 20 + 1;
                    GadgetDiscoBall.this.onClear();
                    this.cancel();
                    return;
                }
                if (this.step <= GadgetDiscoBall.duration * 20 || !PermissionUtils.noPermission(GadgetDiscoBall.this.getPlayer(), EnumPermission.BYPASS_DISCOBALL_DURATION.getPermission(), false)) {
                    GadgetDiscoBall.this.armorStand.setHeadPose(GadgetDiscoBall.this.armorStand.getHeadPose().add(0.0, 0.2, 0.0));
                    final int nextInt = CookieGadgets.random().nextInt(16);
                    GadgetDiscoBall.this.armorStand.setHelmet(new ItemStack(EnumMaterial.valueOf(95, nextInt).getType(), 1, (short)nextInt));
                    ParticleEffect.NOTE.display(GadgetDiscoBall.this.armorStand.getEyeLocation().add(MathUtil.randomDouble(-4.0, 4.0), MathUtil.randomDouble(-3.0, 3.0), MathUtil.randomDouble(-4.0, 4.0)), 1.0f, 5);
                    ParticleEffect.SPELL_INSTANT.display(GadgetDiscoBall.this.armorStand.getLocation(), 2.0f, 2.0f, 2.0f, 1.0f, 5);
                    for (final Entity entity : EntityUtils.getNearbyEntities(GadgetDiscoBall.this.armorStand.getEyeLocation().add(-0.5, -0.5, -0.5), 7.5)) {
                        if (entity.isOnGround()) {
                            MathUtil.applyVelocity(entity, new Vector(0.0, 0.3, 0.0));
                        }
                    }
                }
                else {
                    if (GadgetDiscoBall.this.positionSongPlayer.isPlaying()) {
                        GadgetDiscoBall.this.positionSongPlayer.setPlaying(false);
                    }
                    GadgetDiscoBall.this.clearAll();
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 1L);
        new BukkitRunnable() {
            int times = 0;
            
            public void run() {
                if (!GadgetDiscoBall.this.getPlayer().isOnline() || CookieGadgets.getPlayerManager(GadgetDiscoBall.this.getPlayer()).getCurrentGadget() == null || CookieGadgets.getPlayerManager(GadgetDiscoBall.this.getPlayer()).getCurrentGadget().getType() != GadgetDiscoBall.this.getType()) {
                    GadgetDiscoBall.this.showCooldownBar = true;
                    for (final Player player : GadgetDiscoBall.this.hideCBar) {
                        if (GadgetDiscoBall.hideCooldownBar.contains(player)) {
                            GadgetDiscoBall.hideCooldownBar.remove(player);
                        }
                    }
                    this.cancel();
                    return;
                }
                if (this.times > 5) {
                    GadgetDiscoBall.this.showCooldownBar = true;
                    for (final Player player2 : GadgetDiscoBall.this.hideCBar) {
                        if (GadgetDiscoBall.hideCooldownBar.contains(player2)) {
                            GadgetDiscoBall.hideCooldownBar.remove(player2);
                        }
                    }
                    this.cancel();
                    return;
                }
                GadgetDiscoBall.this.showCooldownBar = false;
                for (final Entity entity : EntityUtils.getNearbyEntities(GadgetDiscoBall.this.armorStand.getEyeLocation().add(-0.5, -0.5, -0.5), 7.5)) {
                    if (entity instanceof Player && !entity.hasMetadata("NPC")) {
                        CookieGadgets.getPlayerManager((Player)entity).sendActionbarMessage(MessageType.PLAYING_SONG.getFormatMessage().replace("{RANDOM_COLOR}", ChatUtil.getRandomColor()).replace("{SONG}", file.getName().replace(".nbs", "")));
                        if (!GadgetDiscoBall.hideCooldownBar.contains(entity)) {
                            GadgetDiscoBall.hideCooldownBar.add((Player)entity);
                        }
                        if (GadgetDiscoBall.this.hideCBar.contains(entity)) {
                            continue;
                        }
                        GadgetDiscoBall.this.hideCBar.add(entity);
                    }
                }
                ++this.times;
            }
        }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 10L);
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
                        if (this.armorStand == null) {
                            break;
                        }
                        else if (player.isOnline() && player.getWorld() == this.armorStand.getWorld() && !this.positionSongPlayer.getPlayerList().contains(player)) {
                            this.positionSongPlayer.addPlayer(player);
                        }
                        else {
                            continue;
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
        if (this.armorStand != null) {
            this.armorStand.remove();
        }
        this.armorStand = null;
        if (this.positionSongPlayer != null && this.positionSongPlayer.isPlaying()) {
            this.positionSongPlayer.setPlaying(false);
        }
        this.positionSongPlayer = null;
        if (GadgetDiscoBall.DISCO_BALLS.containsKey(this)) {
            GadgetDiscoBall.DISCO_BALLS.remove(this);
        }
        this.activated = false;
    }
}
