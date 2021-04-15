

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.Location;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;

public class CloakSuperhero extends Cloak
{
    private static boolean x;
    private static boolean[][] shape;
    
    static {
        CloakSuperhero.x = true;
        CloakSuperhero.shape = new boolean[][] { { CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x }, { CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x }, { CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x }, { CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x }, { CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x }, { CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x }, { CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x, CloakSuperhero.x } };
    }
    
    public CloakSuperhero(final UUID uuid) {
        super(uuid, CloakType.SUPERHERO);
    }
    
    @Override
    public void onUpdate() {
        this.active(this.getPlayer().getLocation(), 20);
        if (this.showCloakEffectToEveryone) {
            ParticleEffect.CLOUD.display(this.getPlayer().getLocation(), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.18f, 0.1f, 0.18f, 5);
        }
        else {
            ParticleEffect.CLOUD.display(this.getPlayer().getLocation(), this.getPlayer(), 0.18f, 0.1f, 0.18f, 5);
        }
    }
    
    private void active(final Location location, final int n) {
        final double n2 = 0.2;
        double x;
        final double n3 = x = location.getX() - n2 * CloakSuperhero.shape[0].length / 2.0 + n2 / 2.0;
        double y2;
        final double y = y2 = location.getY() + 1.3;
        final double n4 = -((location.getYaw() + 180.0f) / 60.0f) + ((location.getYaw() < -180.0f) ? 3.25 : 2.985);
        for (int i = 0; i < CloakSuperhero.shape.length; ++i) {
            for (int j = 0; j < CloakSuperhero.shape[i].length; ++j) {
                if (CloakSuperhero.shape[i][j]) {
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
                            ParticleEffect.REDSTONE.displayColor(clone2, this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 255, 0, 0);
                        }
                        else {
                            ParticleEffect.REDSTONE.displayColor(clone2, this.getPlayer(), 255, 0, 0);
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
