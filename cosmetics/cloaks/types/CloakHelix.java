

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;

public class CloakHelix extends Cloak
{
    private int step;
    float stepY;
    float radius;
    
    public CloakHelix(final UUID uuid) {
        super(uuid, CloakType.ARCHANGEL);
        this.stepY = 0.0f;
        this.radius = 2.3f;
    }
    
    @Override
    public void onUpdate() {
        this.active(this.getPlayer().getLocation());
    }
    
    private void active(final Location location) {
        if (this.step >= Integer.MAX_VALUE) {
            this.step = 0;
        }
        for (int i = 0; i < 6; ++i) {
            final double n = this.step * 0.031415926535897934 + this.stepY + i;
            final Vector vector = new Vector();
            vector.setX(Math.cos(n) * this.radius);
            vector.setZ(Math.sin(n) * this.radius);
            ParticleEffect.SNOW_SHOVEL.display(location.add(vector).add(0.0, (double)this.stepY, 0.0), 0.0f, 1);
            location.subtract(vector).subtract(0.0, (double)this.stepY, 0.0);
            if (this.stepY < 4.4) {
                this.radius -= (float)0.023;
                this.stepY += (float)0.045;
            }
            else {
                this.stepY = 0.0f;
                this.step = 0;
                this.radius = 2.3f;
                ParticleEffect.SNOW_SHOVEL.display(location.add(0.0, 3.6, 0.0), 0.0f, 0.0f, 0.0f, 0.3f, 40);
            }
        }
        this.step += 6;
    }
}
