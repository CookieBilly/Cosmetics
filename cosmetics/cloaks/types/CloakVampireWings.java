

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.Location;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;

public class CloakVampireWings extends Cloak
{
    private static boolean x;
    private static boolean o;
    private static boolean[][] shape;
    
    static {
        CloakVampireWings.x = true;
        CloakVampireWings.o = false;
        CloakVampireWings.shape = new boolean[][] { { CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o }, { CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o }, { CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o }, { CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o }, { CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o }, { CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o }, { CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o }, { CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o }, { CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o }, { CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o }, { CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o }, { CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o }, { CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.x, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o, CloakVampireWings.o } };
    }
    
    public CloakVampireWings(final UUID uuid) {
        super(uuid, CloakType.VAMPIRE_WINGS);
    }
    
    @Override
    public void onUpdate() {
        this.active(this.getPlayer().getLocation());
    }
    
    private void active(final Location location) {
        final double n = 0.2;
        double x;
        final double n2 = x = location.getX() - n * CloakVampireWings.shape[0].length / 2.0 + n;
        double y = location.clone().getY() + 2.8;
        final double n3 = -((location.getYaw() + 180.0f) / 60.0f) + ((location.getYaw() < -180.0f) ? 3.25 : 2.985);
        for (int i = 0; i < CloakVampireWings.shape.length; ++i) {
            for (int j = 0; j < CloakVampireWings.shape[i].length; ++j) {
                if (CloakVampireWings.shape[i][j]) {
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
                            ParticleEffect.SMOKE_NORMAL.display(location, this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 1);
                        }
                        else {
                            ParticleEffect.SMOKE_NORMAL.display(location, this.getPlayer(), 0.0f, 1);
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
