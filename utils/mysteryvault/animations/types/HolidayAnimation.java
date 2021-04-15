

package ws.billy.CookieGadgets.utils.mysteryvault.animations.types;

import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.VersionManager;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.util.Vector;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.EntityType;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.GMiniBlock;
import org.bukkit.Location;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.AnimationType;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Quality;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.block.BlockFace;
import java.util.ArrayList;
import org.bukkit.entity.ArmorStand;

public class HolidayAnimation extends Animation
{
    private ArmorStand armorStand;
    private float volume;
    private ArrayList<ArmorStand> starArmorStand;
    private BlockFace blockFace;
    private static float[] pitch;
    
    static {
        HolidayAnimation.pitch = new float[] { 0.5f, 0.529732f, 0.561231f, 0.594604f, 0.629961f, 0.66742f, 0.707107f, 0.749154f, 0.793701f, 0.840896f, 0.890899f, 0.943874f, 1.0f, 1.059463f, 1.122462f, 1.189207f, 1.259921f, 1.33484f, 1.414214f, 1.498307f, 1.587401f, 1.681793f, 1.781797f, 1.887749f, 2.0f };
    }
    
    public HolidayAnimation(final PlayerManager playerManager, final MysteryVault mysteryVault, final Quality quality, final long n) {
        super(playerManager, mysteryVault, AnimationType.HOLIDAY, quality, n);
        this.volume = 0.05f;
        this.starArmorStand = new ArrayList<ArmorStand>();
        this.blockFace = this.getMysteryVault().getBlockFace();
    }
    
    @Override
    public void onStart() {
        final Location location = this.getMysteryVault().getLocation();
        for (int i = 1; i <= this.getQuality().getQuality(); ++i) {
            this.spawnMysteryBoxQualityBlock(this.getPlayer(), (i - 1) * 5, location);
        }
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 6, 0L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 9, 4L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 11, 8L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 13, 12L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 7, 16L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 11, 20L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 13, 24L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 14, 28L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 9, 32L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 12, 36L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 15, 40L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 16, 44L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 10, 48L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 14, 52L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 16, 56L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 18, 60L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 12, 64L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 15, 68L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 17, 72L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 19, 76L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 13, 80L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 17, 84L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 19, 88L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 20, 92L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 13, 96L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 17, 100L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 19, 104L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 20, 108L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 13, 112L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 17, 116L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 19, 120L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HARP, 20, 124L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 4L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 12L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 20L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 28L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 36L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 44L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 52L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 60L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 68L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 76L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 84L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 92L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 100L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 108L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 116L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 124L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 0L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 8L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 16L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 24L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 32L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 40L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 48L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 56L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 64L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 72L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 80L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 88L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 96L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 104L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 112L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 120L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 1, 0L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 2, 2L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 3, 4L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 2, 6L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 3, 8L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 5, 10L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 4, 12L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 3, 14L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 5, 16L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 4, 18L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 5, 20L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 4, 22L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 6, 24L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 5, 26L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 4, 28L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 5, 30L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 5, 32L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 4, 34L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 6, 36L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 5, 38L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 6, 40L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 7, 42L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 7, 44L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 6, 46L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 7, 48L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 8, 50L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 7, 52L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 8, 54L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 8, 56L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 9, 58L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 8, 60L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 7, 62L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 7, 64L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 8, 66L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 9, 68L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 8, 70L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 9, 72L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 74L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 10, 76L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 9, 78L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 80L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 10, 82L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 84L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 10, 86L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 12, 88L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 90L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 10, 92L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 94L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 96L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 10, 98L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 100L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 10, 102L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 12, 104L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 106L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 10, 108L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 110L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 112L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 10, 114L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 116L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 10, 118L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 12, 120L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 122L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 10, 124L);
        this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_BASS, 11, 126L);
    }
    
    @Override
    public void onUpdate() {
        if (this.steps == 60) {
            final Location location = this.getMysteryVault().getLocation();
            final ArmorStand armorStand = (ArmorStand)this.getPlayer().getWorld().spawnEntity(new Location(location.getWorld(), location.getX() + 0.5, location.getY() - 0.4, location.getZ() + 0.5, GMiniBlock.getYawByBlockFace(this.blockFace), 0.0f).clone(), EntityType.ARMOR_STAND);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            try {
                armorStand.setMarker(false);
                armorStand.setCollidable(false);
                armorStand.setSilent(true);
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            armorStand.setHelmet(this.getAnimation().getMiniBlock().getItemStack());
            armorStand.setBasePlate(false);
            armorStand.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
            this.armorStand = armorStand;
        }
        if (this.steps > 60 && this.steps <= 75) {
            final Location clone = this.armorStand.getLocation().clone();
            clone.setY(clone.getY() + 0.05);
            this.armorStand.teleport(clone);
        }
        if (this.steps > 75 && this.steps <= 115) {
            final Location clone2 = this.armorStand.getLocation().clone();
            final double n = this.steps * 3 * 0.05235987755982988;
            final Vector vector = new Vector();
            if (this.blockFace == BlockFace.EAST) {
                vector.setX(Math.sin(n) * 0.3 / 4.0);
                vector.setZ(-(Math.cos(n) * 0.3) / 4.0);
            }
            else if (this.blockFace == BlockFace.SOUTH) {
                vector.setX(Math.cos(n) * 0.3 / 4.0);
                vector.setZ(Math.sin(n) * 0.3 / 4.0);
            }
            else if (this.blockFace == BlockFace.WEST) {
                vector.setX(-(Math.sin(n) * 0.3) / 4.0);
                vector.setZ(Math.cos(n) * 0.3 / 4.0);
            }
            else if (this.blockFace == BlockFace.NORTH) {
                vector.setX(-(Math.cos(n) * 0.3) / 4.0);
                vector.setZ(-(Math.sin(n) * 0.3) / 4.0);
            }
            clone2.add(vector);
            clone2.setY(clone2.getY() + 0.05625);
            this.armorStand.teleport(clone2);
        }
    }
    
    @Override
    public void onUpdateParticleEffect() {
        if (this.steps > 60) {
            if (this.armorStand == null) {
                return;
            }
            if (this.steps > 60 && this.steps <= 115) {
                ParticleEffect.FIREWORKS_SPARK.display(this.armorStand.getEyeLocation().add(0.0, VersionManager.is1_13OrAbove() ? 0.15 : -0.4, 0.0), 1);
                if (this.steps % 4 == 0) {
                    ParticleEffect.REDSTONE.displayRandomColor(this.armorStand.getEyeLocation().add(0.0, VersionManager.is1_13OrAbove() ? 0.15 : -0.4, 0.0));
                }
            }
            if (this.steps > 115) {
                ParticleEffect.CRIT_MAGIC.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.22f, 0.22f, 0.22f);
            }
        }
    }
    
    @Override
    public void onUpdateSound() {
        if (this.steps == 61) {
            SoundEffect.ENTITY_FIREWORK_ROCKET_LAUNCH.playSound(this.getMysteryVault().getLocation().clone(), 0.4f, 1.0f);
        }
        if (this.steps > 60) {
            if (this.armorStand == null) {
                return;
            }
            if (this.steps <= 60 + this.getQuality().getQuality() * 10 && this.steps % 10 == 0) {
                SoundEffect.ENTITY_ITEM_PICKUP.playSound(this.armorStand.getLocation().clone(), 1.0f, 1.0f);
            }
        }
        if (this.steps > 115) {
            if (this.armorStand == null) {
                return;
            }
            if (this.steps <= 139) {
                ParticleEffect.ENCHANTMENT_TABLE.display(this.armorStand.getLocation().clone().add(0.0, 1.9, 0.0), 5.0f, 15);
                SoundEffect.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR.playSound(this.armorStand.getLocation().clone(), this.volume += 0.006f, 1.0f);
            }
            else if (this.steps <= 143) {
                SoundEffect.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR.playSound(this.armorStand.getLocation().clone(), 1.0f, 1.0f);
            }
            else if (this.steps == 189) {
                SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(this.armorStand.getLocation().clone());
            }
            else if (this.steps == 199) {
                ParticleEffect.LAVA.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.0f, 45);
                ParticleEffect.FLAME.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.45f, 40);
            }
            else if (this.steps == 214) {
                ParticleEffect.CLOUD.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.7f, 0.7f, 0.7f);
                SoundEffect.ENTITY_EXPERIENCE_ORB_PICKUP.playSound(this.armorStand.getLocation());
            }
            else if (this.steps > 214 && this.steps < 239) {
                ParticleEffect.CLOUD.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.7f, 0.7f, 0.7f);
            }
            else if (this.steps >= 239) {
                this.onClear();
                this.isContinue = false;
            }
        }
        if (this.steps == 60) {
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 16, 0L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 17, 2L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 18, 4L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 15, 6L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 18, 8L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 20, 10L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 19, 12L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 18, 14L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 20, 16L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 19, 18L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 20, 20L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 19, 22L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 21, 24L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 20, 26L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 19, 28L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 20, 30L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 20, 32L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 19, 34L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 21, 36L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 20, 38L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 21, 40L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 22, 42L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 22, 44L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 21, 46L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 22, 48L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 23, 50L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 22, 52L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 23, 54L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 23, 56L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 24, 58L);
            this.playSound(SoundEffect.ENTITY_EXPERIENCE_ORB_TOUCH, 23, 60L);
        }
    }
    
    @Override
    public void onClear() {
        if (this.armorStand != null && this.armorStand.isValid()) {
            this.armorStand.remove();
            this.armorStand = null;
        }
        for (final ArmorStand armorStand : this.starArmorStand) {
            if (armorStand != null && armorStand.isValid()) {
                armorStand.remove();
            }
        }
        this.starArmorStand.clear();
        this.getMysteryVault().setActivate(false);
        this.getPlayerManager().setOpeningMysteryBox(false);
    }
    
    private void spawnMysteryBoxQualityBlock(final Player player, final int n, final Location location) {
        Bukkit.getScheduler().runTaskLater((Plugin)CookieGadgets.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                location.clone();
                final Location location = new Location(location.getWorld(), location.getX() + 0.5, location.getY() - 1.75, location.getZ() + 0.5, GMiniBlock.getYawByBlockFace(HolidayAnimation.this.blockFace), 0.0f);
                if (HolidayAnimation.this.blockFace == BlockFace.EAST) {
                    location.setX(location.getX() + 0.75);
                }
                else if (HolidayAnimation.this.blockFace == BlockFace.WEST) {
                    location.setX(location.getX() + 0.25);
                }
                else if (HolidayAnimation.this.blockFace == BlockFace.SOUTH) {
                    location.setZ(location.getZ() + 0.75);
                }
                else if (HolidayAnimation.this.blockFace == BlockFace.NORTH) {
                    location.setZ(location.getZ() + 0.25);
                }
                final ArmorStand e = (ArmorStand)player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                e.setVisible(false);
                e.setGravity(false);
                e.setSmall(false);
                try {
                    e.setMarker(false);
                    e.setCollidable(false);
                    e.setSilent(true);
                }
                catch (NoSuchMethodError noSuchMethodError) {}
                e.setHelmet(HolidayAnimation.this.getAnimation().getQualityBlock().getItemStack());
                e.setMetadata(CookieGadgets.getInstance().getPluginName(), (MetadataValue)new FixedMetadataValue((Plugin)CookieGadgets.getInstance(), (Object)true));
                HolidayAnimation.this.starArmorStand.add(e);
                new BukkitRunnable() {
                    Location oldLoc = val$as.getLocation().clone();
                    int step = 0;
                    float step2 = 0.0f;
                    
                    public void run() {
                        ++this.step;
                        if (this.step <= 7) {
                            e.teleport(e.getLocation().add(0.0, 0.198, 0.0));
                        }
                        else if (this.step <= 40) {
                            this.step2 += 1.5f;
                            final double n = this.step2 * 0.12566370614359174;
                            final Vector vector = new Vector();
                            double n2 = 0.0;
                            double n3 = 0.0;
                            if (HolidayAnimation.this.blockFace == BlockFace.EAST) {
                                vector.setX(-Math.sin(n) * 0.22);
                                vector.setZ(-Math.cos(n) * 0.22);
                                n2 = 0.0;
                                n3 = 1.5;
                            }
                            else if (HolidayAnimation.this.blockFace == BlockFace.SOUTH) {
                                vector.setX(Math.cos(n) * 0.22);
                                vector.setZ(Math.sin(n) * 0.22);
                                n2 = 1.5;
                                n3 = 0.0;
                            }
                            else if (HolidayAnimation.this.blockFace == BlockFace.WEST) {
                                vector.setX(-Math.sin(n) * 0.22);
                                vector.setZ(Math.cos(n) * 0.22);
                                n2 = 0.0;
                                n3 = 1.5;
                            }
                            else if (HolidayAnimation.this.blockFace == BlockFace.NORTH) {
                                vector.setX(-Math.cos(n) * 0.22);
                                vector.setZ(Math.sin(n) * 0.22);
                                n2 = 1.5;
                                n3 = 0.0;
                            }
                            MathUtil.rotateVector(vector, n2, 0.0, n3);
                            final Location add = e.getLocation().add(vector);
                            if (HolidayAnimation.this.blockFace == BlockFace.EAST || HolidayAnimation.this.blockFace == BlockFace.WEST) {
                                add.setX(this.oldLoc.getX());
                            }
                            else if (HolidayAnimation.this.blockFace == BlockFace.SOUTH || HolidayAnimation.this.blockFace == BlockFace.NORTH) {
                                add.setZ(this.oldLoc.getZ());
                            }
                            e.teleport(add);
                        }
                        else if (this.step > 42) {
                            e.remove();
                            this.cancel();
                        }
                    }
                }.runTaskTimer((Plugin)CookieGadgets.getInstance(), 0L, 1L);
            }
        }, (long)n);
    }
    
    private void playSound(final SoundEffect soundEffect, final int n, final long n2) {
        new BukkitRunnable() {
            public void run() {
                soundEffect.playSound(HolidayAnimation.this.getMysteryVault().getLocation().clone(), 1.0f, HolidayAnimation.pitch[n]);
            }
        }.runTaskLater((Plugin)CookieGadgets.getInstance(), n2);
    }
}
