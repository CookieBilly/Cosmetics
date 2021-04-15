

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.Location;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;
import ws.billy.CookieGadgets.utils.GColor;

public class CloakArchangel extends Cloak
{
    private static GColor x;
    private static GColor e;
    private static GColor o;
    private static GColor[][] shape;
    
    static {
        CloakArchangel.x = new GColor(224, 224, 224);
        CloakArchangel.e = new GColor(255, 0, 0);
        CloakArchangel.o = new GColor(0, 0, 0);
        CloakArchangel.shape = new GColor[][] { { CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.e, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.e, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o }, { CloakArchangel.o, CloakArchangel.o, CloakArchangel.e, CloakArchangel.x, CloakArchangel.e, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.e, CloakArchangel.x, CloakArchangel.e, CloakArchangel.o, CloakArchangel.o }, { CloakArchangel.o, CloakArchangel.o, CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e, CloakArchangel.o, CloakArchangel.o }, { CloakArchangel.o, CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e, CloakArchangel.e, CloakArchangel.o, CloakArchangel.e, CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e, CloakArchangel.o }, { CloakArchangel.o, CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e, CloakArchangel.o }, { CloakArchangel.o, CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e, CloakArchangel.o }, { CloakArchangel.o, CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e, CloakArchangel.o }, { CloakArchangel.o, CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e, CloakArchangel.x, CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e, CloakArchangel.o }, { CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e, CloakArchangel.e, CloakArchangel.o, CloakArchangel.e, CloakArchangel.o, CloakArchangel.e, CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e }, { CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e, CloakArchangel.o, CloakArchangel.e, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.e, CloakArchangel.o, CloakArchangel.e, CloakArchangel.x, CloakArchangel.x, CloakArchangel.x, CloakArchangel.e }, { CloakArchangel.e, CloakArchangel.x, CloakArchangel.e, CloakArchangel.e, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.e, CloakArchangel.e, CloakArchangel.x, CloakArchangel.e }, { CloakArchangel.o, CloakArchangel.e, CloakArchangel.o, CloakArchangel.e, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.e, CloakArchangel.o, CloakArchangel.e, CloakArchangel.o }, { CloakArchangel.o, CloakArchangel.e, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o, CloakArchangel.o } };
    }
    
    public CloakArchangel(final UUID uuid) {
        super(uuid, CloakType.ARCHANGEL);
    }
    
    @Override
    public void onUpdate() {
        this.active(this.getPlayer().getLocation().add(0.0, 1.1, 0.0), 20);
        for (int i = 0; i <= 80; i += 4) {
            final double n = i * 0.07853981633974483;
            final Vector vector = new Vector();
            vector.setX(Math.cos(n) * 0.38);
            vector.setZ(Math.sin(n) * 0.38);
            if (this.showCloakEffectToEveryone) {
                ParticleEffect.REDSTONE.displayColor(this.getPlayer().getLocation().add(vector).add(0.0, 2.2, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 255, 255, 0);
            }
            else {
                ParticleEffect.REDSTONE.displayColor(this.getPlayer().getLocation().add(vector).add(0.0, 2.2, 0.0), this.getPlayer(), 255, 255, 0);
            }
        }
    }
    
    private void active(final Location location, final int n) {
        final double n2 = 0.2;
        double x;
        final double n3 = x = location.getX() - n2 * CloakArchangel.shape[0].length / 2.0 + n2 / 2.0;
        double y2;
        final double y = y2 = location.getY() + 1.3;
        final double n4 = -((location.getYaw() + 180.0f) / 60.0f) + ((location.getYaw() < -180.0f) ? 3.25 : 2.985);
        for (int i = 0; i < CloakArchangel.shape.length; ++i) {
            for (int j = 0; j < CloakArchangel.shape[i].length; ++j) {
                final GColor gColor = CloakArchangel.shape[i][j];
                if (gColor.getColor().getRed() != 0) {
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
                            ParticleEffect.REDSTONE.displayColor(clone2, this.hideCloakEffectForVanishedPlayer, this.getPlayer(), gColor);
                        }
                        else {
                            ParticleEffect.REDSTONE.displayColor(clone2, this.getPlayer(), gColor);
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
}
