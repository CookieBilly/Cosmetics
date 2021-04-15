

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import org.bukkit.util.Vector;
import org.bukkit.Location;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;

public class CloakScanner extends Cloak
{
    private static double radius;
    private static double particles;
    private int step;
    private float stepY;
    private double locY;
    
    static {
        CloakScanner.radius = 0.6;
        CloakScanner.particles = 25.0;
    }
    
    public CloakScanner(final UUID uuid) {
        super(uuid, CloakType.SCANNER);
        this.step = 0;
        this.stepY = 0.0f;
    }
    
    @Override
    public void onUpdate() {
        this.active(this.getPlayer().getLocation());
    }
    
    private void active(final Location location) {
        if (this.step > 16) {
            this.locY = 0.0;
            this.step = 0;
        }
        for (int i = 0; i < 9; ++i) {
            final double n = this.step * (0.6283185307179586 / CloakScanner.particles) + this.stepY + 3.5 * i - 0.2;
            final Vector vector = new Vector();
            vector.setX(Math.cos(n) * CloakScanner.radius);
            vector.setZ(Math.sin(n) * CloakScanner.radius);
            if (this.showCloakEffectToEveryone) {
                this.getType().getEffect().display(location.clone().add(vector).add(0.0, this.locY, 0.0), this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 1);
            }
            else {
                this.getType().getEffect().display(location.clone().add(vector).add(0.0, this.locY, 0.0), this.getPlayer(), 0.0f, 1);
            }
        }
        if (this.stepY < 6.0f) {
            this.stepY += (float)0.045;
        }
        else {
            this.stepY = 0.0f;
        }
        if (this.step <= 8) {
            this.locY += 0.25;
        }
        else {
            this.locY -= 0.25;
        }
        ++this.step;
    }
}
