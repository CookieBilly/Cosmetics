

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.Location;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;

public class CloakIcewings extends Cloak
{
    private static boolean x;
    private static boolean o;
    private static boolean[][] shape;
    
    static {
        CloakIcewings.x = true;
        CloakIcewings.o = false;
        CloakIcewings.shape = new boolean[][] { { CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o }, { CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o }, { CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o }, { CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o }, { CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o }, { CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o }, { CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o }, { CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o }, { CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o }, { CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o }, { CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o }, { CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o }, { CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.x, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o, CloakIcewings.o } };
    }
    
    public CloakIcewings(final UUID uuid) {
        super(uuid, CloakType.ICEWINGS);
    }
    
    @Override
    public void onUpdate() {
        this.active(this.getPlayer().getLocation());
    }
    
    private void active(final Location location) {
        final double n = 0.2;
        double x;
        final double n2 = x = location.getX() - n * CloakIcewings.shape[0].length / 2.0 + n;
        double y = location.clone().getY() + 2.8;
        final double n3 = -((location.getYaw() + 180.0f) / 60.0f) + ((location.getYaw() < -180.0f) ? 3.25 : 2.985);
        for (int i = 0; i < CloakIcewings.shape.length; ++i) {
            for (int j = 0; j < CloakIcewings.shape[i].length; ++j) {
                if (CloakIcewings.shape[i][j]) {
                    final Location clone = location.clone();
                    clone.setX(x);
                    clone.setY(y);
                    final Vector subtract = clone.toVector().subtract(location.toVector());
                    final Vector backVector = MathUtil.getBackVector(location);
                    final Vector rotateAroundAxisY = MathUtil.rotateAroundAxisY(subtract, n3);
                    backVector.setY(0).multiply(-0.2 - i / 18.0);
                    location.add(rotateAroundAxisY);
                    location.add(backVector);
                    for (int k = 0; k < 3; ++k) {
                        if (this.showCloakEffectToEveryone) {
                            ParticleEffect.REDSTONE.displayColor(location, this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 224, 224, 224);
                        }
                        else {
                            ParticleEffect.REDSTONE.displayColor(location, this.getPlayer(), 224, 224, 224);
                        }
                    }
                    location.subtract(backVector);
                    location.subtract(rotateAroundAxisY);
                }
                x += n;
            }
            y -= n;
            x = n2;
        }
    }
}
