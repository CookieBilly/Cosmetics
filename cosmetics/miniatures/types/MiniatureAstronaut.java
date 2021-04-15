

package ws.billy.CookieGadgets.cosmetics.miniatures.types;

import ws.billy.CookieGadgets.utils.ParticleEffect;
import ws.billy.CookieGadgets.cosmetics.miniatures.MiniatureType;
import java.util.UUID;

public class MiniatureAstronaut extends Miniature
{
    public MiniatureAstronaut(final UUID uuid) {
        super(uuid, MiniatureType.ASTRONAUT);
    }
    
    @Override
    public void onUpdate() {
        if (super.getMiniature() != null) {
            ParticleEffect.CLOUD.display(super.getMiniature().getLocationNMS(), this.getPlayer(), 0.05f, 0.05f, 0.05f, 1);
        }
    }
}
