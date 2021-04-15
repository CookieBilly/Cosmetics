

package ws.billy.CookieGadgets.utils.mysteryvault.animations.types;

import org.bukkit.scheduler.BukkitRunnable;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.util.Iterator;
import ws.billy.CookieGadgets.utils.SoundEffect;
import ws.billy.CookieGadgets.utils.VersionManager;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.metadata.FixedMetadataValue;
import ws.billy.CookieGadgets.CookieGadgets;
import org.bukkit.entity.EntityType;
import org.bukkit.Location;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.GMiniBlock;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import java.awt.image.BufferedImage;
import ws.billy.CookieGadgets.utils.MathUtil;
import java.awt.Color;
import java.awt.Font;
import ws.billy.CookieGadgets.utils.mysteryvault.animations.AnimationType;
import ws.billy.CookieGadgets.utils.mysteryboxes.utils.Quality;
import ws.billy.CookieGadgets.utils.mysteryvault.MysteryVault;
import ws.billy.CookieGadgets.player.PlayerManager;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;
import java.util.HashSet;
import java.util.HashMap;
import org.bukkit.entity.ArmorStand;

public class CountdownAnimation extends Animation
{
    private ArmorStand armorStand;
    private float volume;
    private static HashMap<Integer, HashSet<Vector>> vectorList;
    private int count;
    private int pixelStepX;
    private int pixelStepY;
    private boolean x;
    private boolean O;
    private boolean[][] shape;
    private BlockFace blockFace;
    private static float[] pitch;
    
    static {
        CountdownAnimation.vectorList = new HashMap<Integer, HashSet<Vector>>();
        CountdownAnimation.pitch = new float[] { 0.5f, 0.529732f, 0.561231f, 0.594604f, 0.629961f, 0.66742f, 0.707107f, 0.749154f, 0.793701f, 0.840896f, 0.890899f, 0.943874f, 1.0f, 1.059463f, 1.122462f, 1.189207f, 1.259921f, 1.33484f, 1.414214f, 1.498307f, 1.587401f, 1.681793f, 1.781797f, 1.887749f, 2.0f };
    }
    
    public CountdownAnimation(final PlayerManager playerManager, final MysteryVault mysteryVault, final Quality quality, final long n) {
        super(playerManager, mysteryVault, AnimationType.COUNTDOWN, quality, n);
        this.volume = 0.05f;
        this.count = 5;
        this.pixelStepX = 1;
        this.pixelStepY = 1;
        this.x = true;
        this.O = false;
        this.shape = new boolean[][] { { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O }, { this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.O, this.x, this.O, this.O, this.O, this.O, this.O } };
        this.blockFace = this.getMysteryVault().getBlockFace();
        if (CountdownAnimation.vectorList.isEmpty()) {
            for (int i = 0; i <= 5; ++i) {
                final BufferedImage stringToBufferedImage = this.stringToBufferedImage(new Font("Tahoma", 0, 16), String.valueOf(i));
                CountdownAnimation.vectorList.put(i, new HashSet<Vector>());
                for (int j = 0; j < stringToBufferedImage.getHeight(); j += this.pixelStepY) {
                    for (int k = 0; k < stringToBufferedImage.getWidth(); k += this.pixelStepX) {
                        if (Color.black.getRGB() == stringToBufferedImage.getRGB(k, j)) {
                            CountdownAnimation.vectorList.get(i).add(MathUtil.rotateVector(new Vector(stringToBufferedImage.getWidth() / 2.0f - k, stringToBufferedImage.getHeight() / 2.0f - j, 0.0f).multiply(0.14285715f), 0.0, 0.0, 0.0));
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void onStart() {
    }
    
    @Override
    public void onUpdate() {
        if (this.steps <= 100 && this.steps % 20 == 0) {
            this.drawEffect(this.count, ParticleEffect.FLAME, this.getMysteryVault().getLocation(), this.blockFace);
            --this.count;
        }
        if (this.steps == 101) {
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
        if (this.steps > 101 && this.steps <= 116) {
            final Location clone = this.armorStand.getLocation().clone();
            clone.setY(clone.getY() + 0.05);
            this.armorStand.teleport(clone);
        }
        if (this.steps > 116 && this.steps <= 156) {
            final Location clone2 = this.armorStand.getLocation().clone();
            final double n = this.steps * 4 * 0.039269908169872414;
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
    
    public void playSound() {
    }
    
    @Override
    public void onUpdateParticleEffect() {
        if (this.steps > 101 && this.steps <= 156) {
            ParticleEffect.FIREWORKS_SPARK.display(this.armorStand.getEyeLocation().add(0.0, VersionManager.is1_13OrAbove() ? 0.15 : -0.4, 0.0), 1);
            if (this.steps % 4 == 0) {
                ParticleEffect.REDSTONE.displayRandomColor(this.armorStand.getEyeLocation().add(0.0, VersionManager.is1_13OrAbove() ? 0.15 : -0.4, 0.0));
            }
        }
        if (this.steps > 156) {
            ParticleEffect.CRIT_MAGIC.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.22f, 0.22f, 0.22f);
        }
    }
    
    @Override
    public void onUpdateSound() {
        if (this.steps <= 100 && this.steps % 20 == 0) {
            SoundEffect.BLOCK_LEVER_CLICK.playSound(this.getMysteryVault().getLocation(), 1.0f, 1.0f);
        }
        if (this.steps == 102) {
            SoundEffect.ENTITY_FIREWORK_ROCKET_LAUNCH.playSound(this.getMysteryVault().getLocation().clone(), 0.4f, 1.0f);
        }
        if (this.steps > 156) {
            if (this.armorStand == null) {
                return;
            }
            if (this.steps <= 180) {
                ParticleEffect.ENCHANTMENT_TABLE.display(this.armorStand.getLocation().clone().add(0.0, 1.9, 0.0), 5.0f, 15);
                SoundEffect.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR.playSound(this.armorStand.getLocation().clone(), this.volume += 0.006f, 1.0f);
            }
            else if (this.steps <= 184) {
                SoundEffect.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR.playSound(this.armorStand.getLocation().clone(), 1.0f, 1.0f);
            }
            else if (this.steps == 230) {
                SoundEffect.ENTITY_GENERIC_EXPLODE.playSound(this.armorStand.getLocation().clone());
            }
            else if (this.steps == 240) {
                ParticleEffect.LAVA.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.0f, 45);
                ParticleEffect.FLAME.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.45f, 40);
            }
            else if (this.steps == 255) {
                ParticleEffect.CLOUD.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.7f, 0.7f, 0.7f);
                SoundEffect.ENTITY_EXPERIENCE_ORB_PICKUP.playSound(this.armorStand.getLocation());
            }
            else if (this.steps > 255 && this.steps < 280) {
                ParticleEffect.CLOUD.display(this.armorStand.getLocation().clone().add(0.0, 0.8, 0.0), 0.7f, 0.7f, 0.7f);
            }
            else if (this.steps >= 280) {
                this.onClear();
                this.isContinue = false;
            }
        }
        if (this.steps == 101) {
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
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 4L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 12L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 20L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 28L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 36L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 44L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 52L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_HAT, 12, 60L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 0L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 8L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 16L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 24L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 32L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 40L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 48L);
            this.playSound(SoundEffect.BLOCK_NOTE_BLOCK_SNARE, 12, 56L);
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
    
    private void drawEffect(final int i, final ParticleEffect particleEffect, Location add, final BlockFace blockFace) {
        final Location add2 = add.clone().add(0.5, 1.1, 0.5);
        add2.setYaw(this.setYaw(GMiniBlock.getYawByBlockFace(blockFace)));
        add = add.clone().add(0.5, 2.3, 0.5);
        add.setYaw(GMiniBlock.getYawByBlockFace(blockFace) - 180.0f);
        try {
            final Iterator<Vector> iterator = CountdownAnimation.vectorList.get(i).iterator();
            while (iterator.hasNext()) {
                final Vector rotateVectorYX = MathUtil.rotateVectorYX(iterator.next(), add.getYaw(), 0.0f);
                particleEffect.display(add.add(rotateVectorYX), 0.0f, 1);
                add.subtract(rotateVectorYX);
            }
            final double n = 0.25;
            double x;
            final double n2 = x = add2.getX() - n * this.shape[0].length / 2.0 + n;
            double y = add2.clone().getY() + 2.8;
            final double n3 = -((add2.getYaw() + 180.0f) / 60.0f) + ((add2.getYaw() < -180.0f) ? 3.25 : 2.985);
            for (int j = 0; j < this.shape.length; ++j) {
                for (int k = 0; k < this.shape[j].length; ++k) {
                    if (this.shape[j][k]) {
                        final Location clone = add2.clone();
                        clone.setX(x);
                        clone.setY(y);
                        final Vector rotateAroundAxisY = MathUtil.rotateAroundAxisY(clone.toVector().subtract(add2.toVector()), n3);
                        add2.add(rotateAroundAxisY);
                        for (int l = 0; l < 3; ++l) {
                            ParticleEffect.CRIT.display(add2, 0.0f, 1);
                        }
                        add2.subtract(rotateAroundAxisY);
                    }
                    x += n;
                }
                y -= n;
                x = n2;
            }
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
    
    private float setYaw(final float n) {
        if (n == -90.0f) {
            return 90.0f;
        }
        if (n == 180.0f) {
            return 0.0f;
        }
        return n;
    }
    
    private BufferedImage stringToBufferedImage(final Font font, final String str) {
        final Graphics graphics = new BufferedImage(1, 1, 2).getGraphics();
        graphics.setFont(font);
        final Rectangle2D stringBounds = font.getStringBounds(str, graphics.getFontMetrics().getFontRenderContext());
        graphics.dispose();
        final BufferedImage bufferedImage = new BufferedImage((int)Math.ceil(stringBounds.getWidth()), (int)Math.ceil(stringBounds.getHeight()), 2);
        final Graphics graphics2 = bufferedImage.getGraphics();
        graphics2.setColor(Color.black);
        graphics2.setFont(font);
        graphics2.drawString(str, 0, graphics2.getFontMetrics().getAscent());
        graphics2.dispose();
        return bufferedImage;
    }
    
    @Override
    public void onClear() {
        if (this.armorStand != null && this.armorStand.isValid()) {
            this.armorStand.remove();
            this.armorStand = null;
        }
        this.getMysteryVault().setActivate(false);
        this.getPlayerManager().setOpeningMysteryBox(false);
    }
    
    private void playSound(final SoundEffect soundEffect, final int n, final long n2) {
        new BukkitRunnable() {
            public void run() {
                if (CountdownAnimation.this.armorStand == null) {
                    return;
                }
                soundEffect.playSound(CountdownAnimation.this.armorStand.getLocation().clone(), 1.0f, CountdownAnimation.pitch[n]);
            }
        }.runTaskLater((Plugin)CookieGadgets.getInstance(), n2);
    }
}
