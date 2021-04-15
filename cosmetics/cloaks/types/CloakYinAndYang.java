

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;

public class CloakYinAndYang extends Cloak
{
    private int step;
    
    public CloakYinAndYang(final UUID uuid) {
        super(uuid, CloakType.YIN_AND_YANG);
    }
    
    @Override
    public void onUpdate() {
        this.active(this.getPlayer().getLocation());
    }
    
    private void active(final Location location) {
        if (this.step >= Integer.MAX_VALUE) {
            this.step = 0;
        }
        for (int i = 1; i < 3; ++i) {
            final double n = 180 * i;
            final Vector vector = new Vector();
            vector.setX(Math.cos(this.step * 3.141592653589793 / 18.0 + Math.toRadians(n)));
            vector.setY(0.1);
            vector.setZ(Math.sin(this.step * 3.141592653589793 / 18.0 + Math.toRadians(n)));
            ParticleEffect.FIREWORKS_SPARK.display(location, (float)vector.getX(), (float)vector.getY(), (float)vector.getZ(), 0.3f, 0);
            ParticleEffect.REDSTONE.displayColor(location.add(vector.multiply(2)), 0, 0, 0);
            location.subtract(vector);
        }
        ++this.step;
    }
}
