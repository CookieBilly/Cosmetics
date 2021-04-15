

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import org.bukkit.Location;
import ws.billy.CookieGadgets.utils.ParticleEffect;
import org.bukkit.util.Vector;
import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public class MiniatureSwampMonster extends Miniature
{
    private int step;
    
    public MiniatureSwampMonster(final UUID uuid) {
        super(uuid, MiniatureType.SWAMP_MONSTER);
    }
    
    @Override
    public void onUpdate() {
        if (super.getMiniature() != null) {
            if (this.step >= Integer.MAX_VALUE) {
                this.step = 0;
            }
            final Location locationNMS = super.getMiniature().getLocationNMS();
            locationNMS.setY(super.getMiniature().getOriginalLocationYNMS());
            final double n = this.step * 0.07853981633974483;
            final Vector vector = new Vector();
            vector.setX(Math.cos(n) * 0.43);
            vector.setZ(Math.sin(n) * 0.43);
            ParticleEffect.SLIME.display(locationNMS.add(vector).add(0.0, 1.6, 0.0), 0.0f, 5);
            this.step += 4;
        }
    }
}
