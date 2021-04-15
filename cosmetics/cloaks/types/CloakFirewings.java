

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.Location;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;

public class CloakFirewings extends Cloak
{
    private static boolean x;
    private static boolean O;
    private static boolean[][] shape;
    
    static {
        CloakFirewings.x = true;
        CloakFirewings.O = false;
        CloakFirewings.shape = new boolean[][] { { CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O }, { CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O }, { CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O }, { CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O }, { CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O }, { CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O }, { CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O }, { CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O }, { CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O }, { CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O }, { CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O }, { CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O }, { CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.x, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O, CloakFirewings.O } };
    }
    
    public CloakFirewings(final UUID uuid) {
        super(uuid, CloakType.FIREWINGS);
    }
    
    @Override
    public void onUpdate() {
        this.active(this.getPlayer().getLocation());
    }
    
    private void active(final Location location) {
        final double n = 0.2;
        double x;
        final double n2 = x = location.getX() - n * CloakFirewings.shape[0].length / 2.0 + n;
        double y = location.clone().getY() + 2.8;
        final double n3 = -((location.getYaw() + 180.0f) / 60.0f) + ((location.getYaw() < -180.0f) ? 3.25 : 2.985);
        for (int i = 0; i < CloakFirewings.shape.length; ++i) {
            for (int j = 0; j < CloakFirewings.shape[i].length; ++j) {
                if (CloakFirewings.shape[i][j]) {
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
                            ParticleEffect.FLAME.display(location, this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 1);
                        }
                        else {
                            ParticleEffect.FLAME.display(location, this.getPlayer(), 0.0f, 1);
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
