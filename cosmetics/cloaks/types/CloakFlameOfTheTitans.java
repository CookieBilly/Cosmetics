

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;

public class CloakFlameOfTheTitans extends Cloak
{
    private static double radius;
    private int step;
    
    static {
        CloakFlameOfTheTitans.radius = 3.5;
    }
    
    public CloakFlameOfTheTitans(final UUID uuid) {
        super(uuid, CloakType.FLAME_OF_THE_TITANS);
    }
    
    @Override
    public void onUpdate() {
        this.active(this.getPlayer().getLocation());
    }
    
    private void active(final Location location) {
        final Location clone = location.clone();
        for (int i = 1; i < 4; ++i) {
            final double n = 120 * i;
            final Vector vector = new Vector();
            vector.setX(Math.cos(Math.toRadians(n)) * CloakFlameOfTheTitans.radius);
            vector.setY(0.1);
            vector.setZ(Math.sin(Math.toRadians(n)) * CloakFlameOfTheTitans.radius);
            MathUtil.rotateAroundAxisY(vector, this.step * 3.141592653589793 / 46.0);
            clone.add(vector);
            final Vector normalize = location.clone().toVector().subtract(clone.toVector()).normalize();
            ParticleEffect.FLAME.display(clone, (float)normalize.getX(), (float)normalize.getY(), (float)normalize.getZ(), 0.15f, 0);
            clone.subtract(vector);
            final Vector vector2 = new Vector();
            vector2.setX(Math.cos(Math.toRadians(n)) * CloakFlameOfTheTitans.radius);
            vector2.setY(0.1);
            vector2.setZ(Math.sin(Math.toRadians(n)) * CloakFlameOfTheTitans.radius);
            MathUtil.rotateAroundAxisY(vector2, this.step * 3.141592653589793 / 46.0 * -1.0);
            clone.add(vector2);
            final Vector normalize2 = location.clone().toVector().subtract(clone.toVector()).normalize();
            ParticleEffect.FLAME.display(clone, (float)normalize2.getX(), (float)normalize2.getY(), (float)normalize2.getZ(), 0.15f, 0);
            clone.subtract(vector2);
        }
        ++this.step;
    }
}
