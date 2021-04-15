

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import org.bukkit.World;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.MathUtil;
import java.util.ArrayList;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.Location;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;

public class CloakMystical extends Cloak
{
    private static boolean x;
    int i;
    private static boolean[][] shape;
    
    static {
        CloakMystical.x = true;
        CloakMystical.shape = new boolean[][] { { CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x }, { CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x }, { CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x }, { CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x }, { CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x }, { CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x }, { CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x, CloakMystical.x } };
    }
    
    public CloakMystical(final UUID uuid) {
        super(uuid, CloakType.MYSTICAL);
        this.i = 0;
    }
    
    @Override
    public void onUpdate() {
        this.active(this.getPlayer().getLocation());
        if (this.i == 0 || this.i == 10 || this.i == 20 || this.i == 29) {
            this.active2(this.getPlayer().getLocation(), 20);
        }
    }
    
    public void active(final Location location) {
        final ArrayList<Location> circle = getCircle(location.clone().add(0.0, 0.5, 0.0), 1.2, 30);
        final ArrayList<Location> circleReverse = getCircleReverse(location.clone().add(0.0, 0.5, 0.0), 1.2, 30);
        if (this.showCloakEffectToEveryone) {
            if (this.i <= 6) {
                ParticleEffect.SPELL_WITCH.display(circle.get(this.i), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i + 1), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 0.8, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i + 3).clone().add(0.0, 0.8, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            }
            else if (this.i >= 7 && this.i <= 12) {
                ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 0.8, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 0.8, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 1.3, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 1.3, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            }
            else if (this.i >= 13 && this.i <= 18) {
                ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 1.0, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 1.0, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 0.5, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 0.5, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            }
            else if (this.i >= 19 && this.i <= 24) {
                ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 0.5, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 0.5, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circle.get(this.i), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            }
            else if (this.i >= 25 && this.i < 30) {
                ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 1.3, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 1.3, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 0.5, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
                ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 0.5, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            }
        }
        else if (this.i <= 6) {
            ParticleEffect.SPELL_WITCH.display(circle.get(this.i), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i + 1), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 0.8, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i + 3).clone().add(0.0, 0.8, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
        }
        else if (this.i >= 7 && this.i <= 12) {
            ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 0.8, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 0.8, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 1.3, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 1.3, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
        }
        else if (this.i >= 13 && this.i <= 18) {
            ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 1.0, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 1.0, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 0.5, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 0.5, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
        }
        else if (this.i >= 19 && this.i <= 24) {
            ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 0.5, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 0.5, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circle.get(this.i), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
        }
        else if (this.i >= 25 && this.i < 30) {
            ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 1.3, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 1.3, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circle.get(this.i).clone().add(0.0, 0.5, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
            ParticleEffect.SPELL_WITCH.display(circleReverse.get(this.i).clone().add(0.0, 0.5, 0.0), this.getPlayer(), 0.0f, 0.0f, 0.0f, 0.0f, 1);
        }
        ++this.i;
        if (this.i >= 30) {
            this.i = 0;
        }
    }
    
    private void active2(final Location location, final int n) {
        final double n2 = 0.2;
        double x;
        final double n3 = x = location.getX() - n2 * CloakMystical.shape[0].length / 2.0 + n2 / 2.0;
        double y2;
        final double y = y2 = location.getY() + 1.3;
        final double n4 = -((location.getYaw() + 180.0f) / 60.0f) + ((location.getYaw() < -180.0f) ? 3.25 : 2.985);
        for (int i = 0; i < CloakMystical.shape.length; ++i) {
            for (int j = 0; j < CloakMystical.shape[i].length; ++j) {
                if (CloakMystical.shape[i][j]) {
                    final Location clone = location.clone();
                    clone.setX(x);
                    clone.setY(y2);
                    final Vector subtract = clone.toVector().subtract(location.toVector());
                    final Vector backVector = MathUtil.getBackVector(location);
                    final Vector rotateAroundAxisY = MathUtil.rotateAroundAxisY(subtract, n4);
                    backVector.setY(0).multiply(-0.2 - i / (double)n);
                    final Location clone2 = location.clone();
                    clone2.add(rotateAroundAxisY);
                    clone2.add(backVector);
                    if (this.isMoving()) {
                        clone2.setY(y);
                    }
                    for (int k = 0; k < 3; ++k) {
                        if (this.showCloakEffectToEveryone) {
                            if (this.hideCloakEffectForVanishedPlayer) {
                                ParticleEffect.ENCHANTMENT_TABLE.display(clone2, true, this.getPlayer());
                            }
                            else {
                                ParticleEffect.ENCHANTMENT_TABLE.display(clone2);
                            }
                        }
                        else {
                            ParticleEffect.ENCHANTMENT_TABLE.display(clone2, this.getPlayer());
                        }
                    }
                    clone2.subtract(backVector);
                    clone2.subtract(rotateAroundAxisY);
                }
                x += n2;
            }
            y2 -= n2;
            x = n3;
        }
    }
    
    private static ArrayList<Location> getCircle(final Location location, final double n, final int n2) {
        final World world = location.getWorld();
        final double n3 = 6.283185307179586 / n2;
        final ArrayList<Location> list = new ArrayList<Location>();
        for (int i = 0; i < n2; ++i) {
            final double n4 = i * n3;
            list.add(new Location(world, location.getX() + n * Math.cos(n4), location.getY(), location.getZ() + n * Math.sin(n4)));
        }
        return list;
    }
    
    private static ArrayList<Location> getCircleReverse(final Location location, final double n, final int n2) {
        final World world = location.getWorld();
        final double n3 = 6.283185307179586 / n2;
        final ArrayList<Location> list = new ArrayList<Location>();
        for (int i = n2; i >= 0; --i) {
            final double n4 = i * n3;
            list.add(new Location(world, location.getX() + n * Math.cos(n4), location.getY(), location.getZ() + n * Math.sin(n4)));
        }
        return list;
    }
}
