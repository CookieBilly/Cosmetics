

package ws.billy.CookieGadgets.cosmetics.cloaks.types;

import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.utils.MathUtil;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import ws.billy.CookieGadgets.cosmetics.cloaks.CloakType;
import java.util.UUID;

public class CloakFirerings extends Cloak
{
    private int step;
    
    public CloakFirerings(final UUID uuid) {
        super(uuid, CloakType.FIRERINGS);
    }
    
    @Override
    public void onUpdate() {
        this.active(this.getPlayer().getLocation());
    }
    
    private void active(final Location location) {
        if (this.step >= Integer.MAX_VALUE) {
            this.step = 0;
        }
        for (int i = 0; i < 2; ++i) {
            final double n = 0.07853981633974483;
            double n2 = 0.0;
            if (i == 1) {
                n2 = 3.5;
            }
            final double n3 = this.step * n + n2;
            final Vector vector = new Vector();
            vector.setX(Math.cos(n3));
            vector.setZ(Math.sin(n3));
            if (i == 0) {
                MathUtil.rotateAroundAxisZ(vector, 10.0);
            }
            else {
                MathUtil.rotateAroundAxisZ(vector, 100.0);
            }
            final Location add = location.clone().add(0.0, 1.0, 0.0).add(vector);
            if (this.showCloakEffectToEveryone) {
                ParticleEffect.FLAME.display(add, this.hideCloakEffectForVanishedPlayer, this.getPlayer(), 0.0f, 1);
            }
            else {
                ParticleEffect.FLAME.display(add, this.getPlayer(), 0.0f, 1);
            }
        }
        this.step += 3;
    }
}
